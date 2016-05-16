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

import java.util.Random;

import org.ambud.marauder.commons.NetworkUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for flow utils
 * 
 * @author ambudsharma
 */
public class TestFlowUtils {

	@Test
	public void testFlowHash() {
		String sip = "192.168.1.";
		String dip = "192.162.0.";
		Random random = new Random();

		for (int i = 0; i < 10000; i++) {
			int src = NetworkUtils.stringIPtoInt(sip + random.nextInt(255));
			int dst = NetworkUtils.stringIPtoInt(dip + random.nextInt(255));
			short sport = (short) random.nextInt(32000);
			short dport = (short) random.nextInt(32000);
			byte proto = 4;
			long hash1 = FlowUtils.flowHash(src, dst, sport, dport, proto);
			long hash2 = FlowUtils.flowHash(dst, src, dport, sport, proto);
			Assert.assertEquals(hash1, hash2);
		}
	}

	@Test
	public void testFlowHashNegative() {
		String sip = "192.168.1.";
		String dip = "192.162.0.";
		Random random = new Random();

		for (int i = 0; i < 10000; i++) {
			int src = NetworkUtils.stringIPtoInt(sip + random.nextInt(255));
			int dst = NetworkUtils.stringIPtoInt(dip + random.nextInt(255));
			short sport = (short) random.nextInt(32000);
			short dport = (short) random.nextInt(32000);
			byte proto = 6;
			long hash1 = FlowUtils.flowHash(src, dst, sport, dport, proto);
			long hash2 = FlowUtils.flowHash(dst, src, dport, sport, proto);
			Assert.assertEquals(hash1, hash2);
			hash2 = FlowUtils.flowHash(src, dst, dport, sport, proto);
			// Assert.assertNotEquals(hash1, hash2);
			if ((hash1 == hash2)) {
//				System.out.println("Failed:" + (hash1 == hash2)+"\t"+counter);
			}
		}
	}

}
