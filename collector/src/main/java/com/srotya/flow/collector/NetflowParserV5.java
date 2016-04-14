/**
 * Copyright 2016 Ambud Sharma
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

import com.srotya.flow.collector.v5.NetflowHeader;
import com.srotya.flow.collector.v5.NetflowRecord;

/**
 * Extremely experimental code
 * 
 * @author ambudsharma
 */
public class NetflowParserV5 {

	public static void main(String[] args) throws Exception {
	}
	
	public static void net() throws Exception {
		DatagramSocket sc = new DatagramSocket(9001);
		final int size = 8192;
		DatagramPacket pkt = new DatagramPacket(new byte[size], size);
		FileOutputStream fos = new FileOutputStream(new File("target/gendata.bin"));
		while (true) {
			sc.receive(pkt);
			ByteBuffer buf = ByteBuffer.wrap(pkt.getData()).order(ByteOrder.BIG_ENDIAN);
			for (int i = 0; i < pkt.getLength(); i++) {
				System.out.print(pkt.getData()[i] + " ");
				fos.write(pkt.getData()[i]);
			}
			fos.flush();
			NetflowHeader header = new NetflowHeader();
			header.setVersion(buf.getShort());
			header.setCount(buf.getShort());
			header.setSysUptime(buf.getInt());
			header.setUnixSecs(buf.getInt());
			header.setUnixNsecs(buf.getInt());
			header.setFlowSequence(buf.getInt());
			header.setEngineType(buf.get());
			header.setEngineId(buf.get());
			header.setSampleInterval(buf.getShort());
			System.out.println(header);
			for (int i = 0; i < header.getCount(); i++) {
				NetflowRecord record = new NetflowRecord();
				record.setSrcAddr(buf.getInt());
				record.setDstAddr(buf.getInt());
				record.setNextHop(buf.getInt());
				record.setInput(buf.getShort());
				record.setOutput(buf.getShort());
				record.setdPkts(buf.getInt());
				record.setdOctets(buf.getInt());
				record.setFirst(buf.getInt());
				record.setLast(buf.getInt());
				record.setSrcPort(buf.getShort());
				record.setDstPort(buf.getShort());
				record.setPad1(buf.get());
				record.setTcpFlags(buf.get());
				record.setProt(buf.get());
				record.setTos(buf.get());
				record.setSrcAs(buf.getShort());
				record.setDstAs(buf.getShort());
				record.setSrcMask(buf.get());
				record.setDstMask(buf.get());
				record.setPad2(buf.getShort());
			}
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
