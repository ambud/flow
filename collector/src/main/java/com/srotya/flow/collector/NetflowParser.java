/**
 * Copyright 2013 Ambud Sharma
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.2
 */
package com.srotya.flow.collector;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Extremely experimental code
 * 
 * @author ambudsharma
 */
public class NetflowParser {

	public static void main(String[] args) throws Exception {
		// net();
		testPermutations();
	}
	
	public static void test2() {
		String sip = "192.168.1.57";
		String dip = "192.162.0.78";
		// 192.168.1.57 192.162.0.78 560184222116254136 560184222116254136

		toIPFlow(sip, dip);

		String stmp = "11000000101000000000000100111001";
		String dtmp = "11000000101010100000000001001110";
		System.out.println(toStringIP(Integer.parseUnsignedInt(stmp, 2)));
		System.out.println(toStringIP(Integer.parseUnsignedInt(dtmp, 2)));
		sip = toStringIP(Integer.parseUnsignedInt(stmp, 2));
		dip = toStringIP(Integer.parseUnsignedInt(dtmp, 2));
		toIPFlow(sip, dip);
		// 1100, 0000, 1010, 1000, 0000, 0001, 0011, 1001

		int sipI = stringIPtoInt(sip);
		int dipI = stringIPtoInt(dip);
		long ts = System.currentTimeMillis();
		for (int i = 0; i < 1000000000; i++) {
			hash(sipI, dipI, (short) 23112, (short) 80);
		}
		System.out.println((System.currentTimeMillis()-ts)+"ms");
	}

	private static void toIPFlow(String sip, String dip) {

		long hash1 = hash(stringIPtoInt(sip), stringIPtoInt(dip), (short) 23112, (short) 80);
		long hash2 = hash(stringIPtoInt(dip), stringIPtoInt(sip), (short) 80, (short) 23112);
		System.out.println(hash1 + "\t" + hash2 + "\t" + (hash1 == hash2));
		System.out.println(sip + ":" + stringIPtoInt(sip) + "\t" + dip + ":" + stringIPtoInt(dip));
		System.out.println(Integer.toBinaryString(stringIPtoInt(sip)));
		System.out.println(Integer.toBinaryString(stringIPtoInt(dip)));
		System.out.println("\n");
	}

	public static int stringIPtoInt(String ip) {
		String[] ipParts = ip.split("\\.");
		int intIP = 0;
		for (int i = 0; i < 4; i++) {
			intIP += Integer.parseInt(ipParts[i]) << (24 - (8 * i));
		}
		return intIP;
	}

	public static String toStringIP(int ip) {
		StringBuilder builder = new StringBuilder();
		builder.append(((ip >> 24) & 0xff) + ".");
		builder.append(((ip >> 16) & 0xff) + ".");
		builder.append(((ip >> 8) & 0xff) + ".");
		builder.append(((ip >> 0) & 0xff));
		return builder.toString();
	}

	public static long hash(int src, int dst, short sport, short dport) {
		return ((long) (src * 31 ^ dst * 31) << 32) | (sport * 13 ^ dport * 13);
	}
	
	public static void testPermutations() {
		ArrayBlockingQueue<Runnable> tasks = new ArrayBlockingQueue<>(8);
		ExecutorService es = new ThreadPoolExecutor(2, 4, 
		        30, TimeUnit.MILLISECONDS, tasks);
		for(int i=Integer.MIN_VALUE;i<=Integer.MAX_VALUE;i++) {
			es.submit(new RunnableImplementation(i));
		}
	}
	

	private static final class RunnableImplementation implements Runnable {
		private int i;

		public RunnableImplementation(int i) {
			this.i = i;
		}

		@Override
		public void run() {
			for(int j=Integer.MIN_VALUE;j<Integer.MAX_VALUE;j++) {
				hash(i, j, (short)0, (short)0);
				if(j%1000000000==0) {
					System.out.println("Computed 1000000000:"+j);
				}
			}
			System.out.println("Completed one permutation");
		}
	}

	public static void net() throws Exception {
		DatagramSocket sc = new DatagramSocket(9001);
		final int size = 8192;
		DatagramPacket pkt = new DatagramPacket(new byte[size], size);
		FileOutputStream fos = new FileOutputStream(new File("/Users/ambudsharma/Desktop/netflow/gendata.bin"));
		while (true) {
			sc.receive(pkt);
			ByteBuffer buf = ByteBuffer.wrap(pkt.getData()).order(ByteOrder.BIG_ENDIAN);
			for (int i = 0; i < pkt.getLength(); i++) {
				System.out.print(pkt.getData()[i] + " ");
				fos.write(pkt.getData()[i]);
			}
			fos.flush();
			// NetflowHeader header = new NetflowHeader();
			// header.setVersion(buf.getShort());
			// header.setCount(buf.getShort());
			// header.setSysUptime(buf.getInt());
			// header.setUnixSecs(buf.getInt());
			// header.setUnixNsecs(buf.getInt());
			// header.setFlowSequence(buf.getInt());
			// header.setEngineType(buf.get());
			// header.setEngineId(buf.get());
			// header.setSampleInterval(buf.getShort());
			// System.out.println(header);
			// for(int i=0;i<header.getCount();i++) {
			// NetflowRecord record = new NetflowRecord();
			// record.setSrcAddr(buf.getInt());
			// record.setDstAddr(buf.getInt());
			// record.setNextHop(buf.getInt());
			// record.setInput(buf.getShort());
			// record.setOutput(buf.getShort());
			// record.setdPkts(buf.getInt());
			// record.setdOctets(buf.getInt());
			// record.setFirst(buf.getInt());
			// record.setLast(buf.getInt());
			// record.setSrcPort(buf.getShort());
			// record.setDstPort(buf.getShort());
			// record.setPad1(buf.get());
			// record.setTcpFlags(buf.get());
			// record.setProt(buf.get());
			// record.setTos(buf.get());
			// record.setSrcAs(buf.getShort());
			// record.setDstAs(buf.getShort());
			// record.setSrcMask(buf.get());
			// record.setDstMask(buf.get());
			// record.setPad2(buf.getShort());
			// System.out.println(record);
			// }
			// System.out.println("\n\n\n");
		}
	}

	public static class NetflowRecord {

		private int srcAddr;
		private int dstAddr;
		private int nextHop;
		private short input;
		private short output;
		private int dPkts;
		private int dOctets;
		private int first;
		private int last;
		private short srcPort;
		private short dstPort;
		private byte pad1;
		private byte tcpFlags;
		private byte prot;
		private byte tos;
		private short srcAs;
		private short dstAs;
		private byte srcMask;
		private byte dstMask;
		private short pad2;

		/**
		 * @return the srcAddr
		 */
		public int getSrcAddr() {
			return srcAddr;
		}

		/**
		 * @param srcAddr
		 *            the srcAddr to set
		 */
		public void setSrcAddr(int srcAddr) {
			this.srcAddr = srcAddr;
		}

		/**
		 * @return the dstAddr
		 */
		public int getDstAddr() {
			return dstAddr;
		}

		/**
		 * @param dstAddr
		 *            the dstAddr to set
		 */
		public void setDstAddr(int dstAddr) {
			this.dstAddr = dstAddr;
		}

		/**
		 * @return the nextHop
		 */
		public int getNextHop() {
			return nextHop;
		}

		/**
		 * @param nextHop
		 *            the nextHop to set
		 */
		public void setNextHop(int nextHop) {
			this.nextHop = nextHop;
		}

		/**
		 * @return the input
		 */
		public short getInput() {
			return input;
		}

		/**
		 * @param input
		 *            the input to set
		 */
		public void setInput(short input) {
			this.input = input;
		}

		/**
		 * @return the output
		 */
		public short getOutput() {
			return output;
		}

		/**
		 * @param output
		 *            the output to set
		 */
		public void setOutput(short output) {
			this.output = output;
		}

		/**
		 * @return the dPkts
		 */
		public int getdPkts() {
			return dPkts;
		}

		/**
		 * @param dPkts
		 *            the dPkts to set
		 */
		public void setdPkts(int dPkts) {
			this.dPkts = dPkts;
		}

		/**
		 * @return the dOctets
		 */
		public int getdOctets() {
			return dOctets;
		}

		/**
		 * @param dOctets
		 *            the dOctets to set
		 */
		public void setdOctets(int dOctets) {
			this.dOctets = dOctets;
		}

		/**
		 * @return the first
		 */
		public int getFirst() {
			return first;
		}

		/**
		 * @param first
		 *            the first to set
		 */
		public void setFirst(int first) {
			this.first = first;
		}

		/**
		 * @return the last
		 */
		public int getLast() {
			return last;
		}

		/**
		 * @param last
		 *            the last to set
		 */
		public void setLast(int last) {
			this.last = last;
		}

		/**
		 * @return the srcPort
		 */
		public short getSrcPort() {
			return srcPort;
		}

		/**
		 * @param srcPort
		 *            the srcPort to set
		 */
		public void setSrcPort(short srcPort) {
			this.srcPort = srcPort;
		}

		/**
		 * @return the dstPort
		 */
		public short getDstPort() {
			return dstPort;
		}

		/**
		 * @param dstPort
		 *            the dstPort to set
		 */
		public void setDstPort(short dstPort) {
			this.dstPort = dstPort;
		}

		/**
		 * @return the pad1
		 */
		public byte getPad1() {
			return pad1;
		}

		/**
		 * @param pad1
		 *            the pad1 to set
		 */
		public void setPad1(byte pad1) {
			this.pad1 = pad1;
		}

		/**
		 * @return the tcpFlags
		 */
		public byte getTcpFlags() {
			return tcpFlags;
		}

		/**
		 * @param tcpFlags
		 *            the tcpFlags to set
		 */
		public void setTcpFlags(byte tcpFlags) {
			this.tcpFlags = tcpFlags;
		}

		/**
		 * @return the prot
		 */
		public byte getProt() {
			return prot;
		}

		/**
		 * @param prot
		 *            the prot to set
		 */
		public void setProt(byte prot) {
			this.prot = prot;
		}

		/**
		 * @return the tos
		 */
		public byte getTos() {
			return tos;
		}

		/**
		 * @param tos
		 *            the tos to set
		 */
		public void setTos(byte tos) {
			this.tos = tos;
		}

		/**
		 * @return the srcAs
		 */
		public short getSrcAs() {
			return srcAs;
		}

		/**
		 * @param srcAs
		 *            the srcAs to set
		 */
		public void setSrcAs(short srcAs) {
			this.srcAs = srcAs;
		}

		/**
		 * @return the dstAs
		 */
		public short getDstAs() {
			return dstAs;
		}

		/**
		 * @param dstAs
		 *            the dstAs to set
		 */
		public void setDstAs(short dstAs) {
			this.dstAs = dstAs;
		}

		/**
		 * @return the srcMask
		 */
		public byte getSrcMask() {
			return srcMask;
		}

		/**
		 * @param srcMask
		 *            the srcMask to set
		 */
		public void setSrcMask(byte srcMask) {
			this.srcMask = srcMask;
		}

		/**
		 * @return the dstMasrk
		 */
		public byte getDstMask() {
			return dstMask;
		}

		/**
		 * @param dstMasrk
		 *            the dstMasrk to set
		 */
		public void setDstMask(byte dstMasrk) {
			this.dstMask = dstMasrk;
		}

		/**
		 * @return the pad2
		 */
		public short getPad2() {
			return pad2;
		}

		/**
		 * @param pad2
		 *            the pad2 to set
		 */
		public void setPad2(short pad2) {
			this.pad2 = pad2;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "NetflowRecord [srcAddr=" + toStringIP(srcAddr) + ", dstAddr=" + toStringIP(dstAddr) + ", nextHop="
					+ nextHop + ", input=" + input + ", output=" + output + ", dPkts=" + dPkts + ", dOctets=" + dOctets
					+ ", first=" + first + ", last=" + last + ", srcPort=" + srcPort + ", dstPort=" + dstPort
					+ ", pad1=" + pad1 + ", tcpFlags=" + tcpFlags + ", prot=" + prot + ", tos=" + tos + ", srcAs="
					+ srcAs + ", dstAs=" + dstAs + ", srcMask=" + srcMask + ", dstMasrk=" + dstMask + ", pad2=" + pad2
					+ "]";
		}

	}

	public static class NetflowHeader {

		private short version;
		private short count;
		private int sysUptime;
		private int unixSecs;
		private int unixNsecs;
		private int flowSequence;
		private byte engineType;
		private byte engineId;
		private short sampleInterval;

		/**
		 * @return the version
		 */
		public short getVersion() {
			return version;
		}

		/**
		 * @param version
		 *            the version to set
		 */
		public void setVersion(short version) {
			this.version = version;
		}

		/**
		 * @return the count
		 */
		public short getCount() {
			return count;
		}

		/**
		 * @param count
		 *            the count to set
		 */
		public void setCount(short count) {
			this.count = count;
		}

		/**
		 * @return the sysUptime
		 */
		public int getSysUptime() {
			return sysUptime;
		}

		/**
		 * @param sysUptime
		 *            the sysUptime to set
		 */
		public void setSysUptime(int sysUptime) {
			this.sysUptime = sysUptime;
		}

		/**
		 * @return the unixSecs
		 */
		public int getUnixSecs() {
			return unixSecs;
		}

		/**
		 * @param unixSecs
		 *            the unixSecs to set
		 */
		public void setUnixSecs(int unixSecs) {
			this.unixSecs = unixSecs;
		}

		/**
		 * @return the unixNsecs
		 */
		public int getUnixNsecs() {
			return unixNsecs;
		}

		/**
		 * @param unixNsecs
		 *            the unixNsecs to set
		 */
		public void setUnixNsecs(int unixNsecs) {
			this.unixNsecs = unixNsecs;
		}

		/**
		 * @return the flowSequence
		 */
		public int getFlowSequence() {
			return flowSequence;
		}

		/**
		 * @param flowSequence
		 *            the flowSequence to set
		 */
		public void setFlowSequence(int flowSequence) {
			this.flowSequence = flowSequence;
		}

		/**
		 * @return the engineType
		 */
		public byte getEngineType() {
			return engineType;
		}

		/**
		 * @param engineType
		 *            the engineType to set
		 */
		public void setEngineType(byte engineType) {
			this.engineType = engineType;
		}

		/**
		 * @return the engineId
		 */
		public byte getEngineId() {
			return engineId;
		}

		/**
		 * @param engineId
		 *            the engineId to set
		 */
		public void setEngineId(byte engineId) {
			this.engineId = engineId;
		}

		/**
		 * @return the sampleInterval
		 */
		public short getSampleInterval() {
			return sampleInterval;
		}

		/**
		 * @param sampleInterval
		 *            the sampleInterval to set
		 */
		public void setSampleInterval(short sampleInterval) {
			this.sampleInterval = sampleInterval;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "NetflowHeader [version=" + version + ", count=" + count + ", sysUptime=" + sysUptime + ", unixSecs="
					+ unixSecs + ", unixNsecs=" + unixNsecs + ", flowSequence=" + flowSequence + ", engineType="
					+ engineType + ", engineId=" + engineId + ", sampleInterval=" + sampleInterval + "]";
		}

	}

	public static class NetflowPacket {

		private NetflowHeader header;
		private NetflowRecord[] records;

		public NetflowPacket() {
		}

		public NetflowHeader getHeader() {
			return header;
		}

		public void setHeader(NetflowHeader header) {
			this.header = header;
		}

		public NetflowRecord[] getRecords() {
			return records;
		}

		public void setRecords(NetflowRecord[] records) {
			this.records = records;
		}

	}

	public static void test() throws Exception {
		String file = "~/Downloads/netflow000.tar.bz2";
		file = "~/Desktop/netflow/gendata.bin";
		file = file.replace("~", System.getProperty("user.home"));
		DataInputStream stream = new DataInputStream(new BufferedInputStream((new FileInputStream(file)), 4096));
		while (true) {
			System.out.println("Netflow version:" + stream.readShort());
			if (stream.readBoolean()) {
				break;
			}
		}
		stream.close();
	}
}
