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

/**
 * @author ambudsharma
 */
public class FlowUtils {

	private static final int SHIFT_NUM = 32;
	private static final int MAGIC_PRIME = 51;
	private static final int MAGIC_PRIME2 = 17;

	private FlowUtils() {
	}

	/**
	 * A reversible hash that should result in the same hash value for a pair of
	 * IP address and port numbers where position doesn't matter. <br>
	 * This hash can be used to classify packets that belong to the same flow
	 * together. <br>
	 * Logic: XOR creates reversible hash and OR just concatenates the different
	 * parts that identify a flow. Multiplication with a prime number creates
	 * sparseness and removes collision that can happen due to bit flips.
	 * 
	 * @param src
	 * @param dst
	 * @param sport
	 * @param dport
	 * @return hashForFlow
	 */
	public static long flowHash(int src, int dst, short sport, short dport, byte proto) {
//		return ((((long) src) | sport * MAGIC_PRIME2) * MAGIC_PRIME ^ (((long) dst) | dport * MAGIC_PRIME2) * MAGIC_PRIME) | proto;
		return flowHashHighEntropy(src, dst, sport, dport, proto);
	}

	/**
	 * @param src
	 * @param dst
	 * @param sport
	 * @param dport
	 * @param proto
	 * @return hashForFlow
	 */
	public static long flowHashHighEntropy(int src, int dst, short sport, short dport, byte proto) {
		return (((long) (src * MAGIC_PRIME ^ dst * MAGIC_PRIME) << SHIFT_NUM) | ((sport * MAGIC_PRIME2) ^ (dport * MAGIC_PRIME2)) | proto);
	}
}
