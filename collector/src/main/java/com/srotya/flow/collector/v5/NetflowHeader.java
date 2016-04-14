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

public class NetflowHeader {

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