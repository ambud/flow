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

import com.lmax.disruptor.EventFactory;
import com.srotya.flow.collector.v5.NetflowRecord;

/**
 * @author ambudsharma
 *
 */
public class NetflowRecordFactory implements EventFactory<NetflowRecord> {

	@Override
	public NetflowRecord newInstance() {
		return new NetflowRecord();
	}

}
