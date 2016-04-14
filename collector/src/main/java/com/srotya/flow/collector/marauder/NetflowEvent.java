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
package com.srotya.flow.collector.marauder;

import org.ambud.marauder.event.MarauderBaseEvent;
import org.ambud.marauder.event.MarauderEventTypes;
import org.apache.commons.codec.binary.Hex;

public abstract class NetflowEvent extends MarauderBaseEvent {

	private byte[] rawNetflowPacket;
	private int timestamp;
	
	public void initHdrs() {
		super.initHdrs();
		getHeaders().put("nv", String.valueOf(getSigID()));
		getHeaders().put("sa", Hex.encodeHexString(getSrcAddr()));
		getHeaders().put("da", Hex.encodeHexString(getDstAddr()));
//		getHeaders().put(IDS_EVENT_SRC_PRT, String.valueOf(getSrcPort()));
//		getHeaders().put(IDS_EVENT_DST_PORT, String.valueOf(getDstPort()));
		
	}
	
	protected abstract byte[] getDstAddr();

	protected abstract byte[] getSrcAddr();

	@Override
	public byte[] getBody() {
		return rawNetflowPacket;
	}

	@Override
	public void setBody(byte[] body) {
		this.rawNetflowPacket = body;
	}

	@Override
	public int getSigID() {
		return getNetflowVersion();
	}

	@Override
	public MarauderEventTypes getEventType() {
		return MarauderEventTypes.NETSYS;
	}

	@Override
	public int getSourceAddress() {
		return getNetflowServer();
	}

	@Override
	public int getTimestamp() {
		return timestamp;
	}

	public abstract int getNetflowVersion();

	public abstract int getNetflowServer();
}
