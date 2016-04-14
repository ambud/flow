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
package com.srotya.flow.collector.v5;

import java.io.Serializable;

import org.ambud.marauder.commons.NetworkUtils;

/**
 * Netflow v5 Record
 * 
 * @author ambudsharma
 */
public class NetflowRecord implements Serializable {

	private static final long serialVersionUID = 1L;
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
		return "NetflowRecord [srcAddr=" + NetworkUtils.toStringIP(srcAddr) + ", dstAddr="
				+ NetworkUtils.toStringIP(dstAddr) + ", nextHop=" + nextHop + ", input=" + input + ", output=" + output
				+ ", dPkts=" + dPkts + ", dOctets=" + dOctets + ", first=" + first + ", last=" + last + ", srcPort="
				+ srcPort + ", dstPort=" + dstPort + ", pad1=" + pad1 + ", tcpFlags=" + tcpFlags + ", prot=" + prot
				+ ", tos=" + tos + ", srcAs=" + srcAs + ", dstAs=" + dstAs + ", srcMask=" + srcMask + ", dstMasrk="
				+ dstMask + ", pad2=" + pad2 + "]";
	}

}