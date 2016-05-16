package com.srotya.flow.analyzer.endpoints;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.ambud.marauder.commons.NetworkUtils;

import com.srotya.flow.collector.FlowUtils;

public class NetflowSeriesRecord implements Serializable {

	private static final long serialVersionUID = 1L;
	private transient int ipOne;
	private transient int ipTwo;
	private transient short portOne;
	private transient short portTwo;
	private byte proto;

	public NetflowSeriesRecord() {
	}

	public NetflowSeriesRecord(int ipOne, int ipTwo, short portOne, short portTwo, byte proto) {
		this();
		this.ipOne = ipOne;
		this.ipTwo = ipTwo;
		this.portOne = portOne;
		this.portTwo = portTwo;
		this.proto = proto;
	}

	/**
	 * @return hashKey
	 */
	protected long getHash() {
		return FlowUtils.flowHash(ipOne, ipTwo, portOne, portTwo, proto);
	}

	/**
	 * @return the ipOne
	 */
	protected int getIpOne() {
		return ipOne;
	}

	public String getIpOneString() {
		return NetworkUtils.toStringIP(ipOne);
	}

	public String getIpTwoString() {
		return NetworkUtils.toStringIP(ipTwo);
	}

	/**
	 * @param ipOne
	 *            the ipOne to set
	 */
	public void setIpOne(int ipOne) {
		this.ipOne = ipOne;
	}

	/**
	 * @return the ipTwo
	 */
	protected int getIpTwo() {
		return ipTwo;
	}

	/**
	 * @param ipTwo
	 *            the ipTwo to set
	 */
	public void setIpTwo(int ipTwo) {
		this.ipTwo = ipTwo;
	}

	/**
	 * @return the proto
	 */
	public byte getProto() {
		return proto;
	}

	/**
	 * @param proto
	 *            the proto to set
	 */
	public void setProto(byte proto) {
		this.proto = proto;
	}

	/**
	 * @return the portOne
	 */
	public short getPortOne() {
		return portOne;
	}

	/**
	 * @param portOne
	 *            the portOne to set
	 */
	public void setPortOne(short portOne) {
		this.portOne = portOne;
	}

	/**
	 * @return the portTwo
	 */
	public short getPortTwo() {
		return portTwo;
	}

	/**
	 * @param portTwo
	 *            the portTwo to set
	 */
	public void setPortTwo(short portTwo) {
		this.portTwo = portTwo;
	}

	public static class Datapoint implements Serializable {

		private static final long serialVersionUID = 1L;
		private long timestamp;
		private int count;
		private boolean direction;

		public Datapoint(long timestamp, int count, boolean direction) {
			this.timestamp = timestamp;
			this.count = count;
			this.direction = direction;
		}

		/**
		 * @return the timestamp
		 */
		public long getTimestamp() {
			return timestamp;
		}

		/**
		 * @param timestamp
		 *            the timestamp to set
		 */
		public void setTimestamp(long timestamp) {
			this.timestamp = timestamp;
		}

		/**
		 * @return the count
		 */
		public int getCount() {
			return count;
		}

		/**
		 * @param count
		 *            the count to set
		 */
		public void setCount(int count) {
			this.count = count;
		}

		/**
		 * @return the direction
		 */
		public boolean isDirection() {
			return direction;
		}

		/**
		 * @param direction
		 *            the direction to set
		 */
		public void setDirection(boolean direction) {
			this.direction = direction;
		}

	}

}
