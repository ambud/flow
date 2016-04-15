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

import java.net.DatagramPacket;
import java.util.concurrent.Executors;

import com.lmax.disruptor.dsl.Disruptor;
import com.srotya.flow.collector.v5.NetflowRecord;

/**
 * @author ambudsharma
 *
 */
public class FlowCollector {
	
	private static final int BUFFER_SIZE = 1024;
	private Disruptor<NetflowRecord> disruptor;
	private Disruptor<DatagramPacket> rawEventReceiver;
	
	@SuppressWarnings("unchecked")
	public void init() {
		disruptor = new Disruptor<>(new NetflowRecordFactory(), BUFFER_SIZE, Executors.defaultThreadFactory());
		rawEventReceiver = new Disruptor<>(new DatagramEventFactory(), BUFFER_SIZE, Executors.defaultThreadFactory());
		
		disruptor.handleEventsWith(new NetflowParserV5.NetflowRecordHandler());
		rawEventReceiver.handleEventsWith(new NetflowParserV5.DatagramHandler(disruptor.getRingBuffer()));
		new NetflowParserV5(rawEventReceiver.getRingBuffer()).run();
	}

	public static void main(String[] args) {
		new FlowCollector().init();
	}

}
