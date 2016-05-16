package com.srotya.flow.analyzer.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.ambud.marauder.commons.NetworkUtils;

import com.srotya.flow.analyzer.endpoints.NetflowSeriesRecord;
import com.srotya.flow.analyzer.endpoints.NetflowSeriesRecord.Datapoint;
import com.srotya.flow.collector.FlowUtils;

public class FlowStore {

	private static final FlowStore store = new FlowStore();
	private Map<Long, NetflowSeriesRecord> records;
	private Map<Long, List<Datapoint>> datapoints;

	public FlowStore() {
		records = new ConcurrentHashMap<>();
		datapoints = new ConcurrentHashMap<>();
		init();
	}

	public static FlowStore getInstance() {
		return store;
	}

	public void init() {
		one();
		two();
	}
	
	private void one() {
		int src = NetworkUtils.stringIPtoInt("192.168.1.10"), dst = NetworkUtils.stringIPtoInt("192.168.1.12");
		NetflowSeriesRecord record = new NetflowSeriesRecord(src, dst, (short) 2433, (short) 80, (byte) 6);
		List<Datapoint> series = new ArrayList<>();
		series.add(new Datapoint(System.currentTimeMillis(), 1231, true));
		series.add(new Datapoint(System.currentTimeMillis(), 1231, true));
		series.add(new Datapoint(System.currentTimeMillis(), 1231, true));
		series.add(new Datapoint(System.currentTimeMillis(), 1231, true));
		series.add(new Datapoint(System.currentTimeMillis(), 1231, true));
		records.put(FlowUtils.flowHash(src, dst, (short) 2433, (short) 80, (byte) 6), record);
		this.datapoints.put(FlowUtils.flowHash(src, dst, (short) 2433, (short) 80, (byte) 6), series);
	}
	
	private void two() {
		int src = NetworkUtils.stringIPtoInt("192.168.1.104"), dst = NetworkUtils.stringIPtoInt("192.168.1.11");
		NetflowSeriesRecord record = new NetflowSeriesRecord(src, dst, (short) 2433, (short) 80, (byte) 6);
		List<Datapoint> series = new ArrayList<>();
		series.add(new Datapoint(System.currentTimeMillis(), 1231, true));
		series.add(new Datapoint(System.currentTimeMillis(), 1231, true));
		series.add(new Datapoint(System.currentTimeMillis(), 1231, true));
		series.add(new Datapoint(System.currentTimeMillis(), 1231, true));
		series.add(new Datapoint(System.currentTimeMillis(), 1231, true));
		records.put(FlowUtils.flowHash(src, dst, (short) 2433, (short) 80, (byte) 6), record);
		this.datapoints.put(FlowUtils.flowHash(src, dst, (short) 2433, (short) 80, (byte) 6), series);
	}

	public NetflowSeriesRecord getRecord(long hash) {
		return records.get(hash);
	}

	public NetflowSeriesRecord getRecord(String ipOne, String ipTwo, short portOne, short portTwo, String proto) {
		return records.get(FlowUtils.flowHash(NetworkUtils.stringIPtoInt(ipOne), NetworkUtils.stringIPtoInt(ipTwo),
				portOne, portTwo, (byte) 6));
	}
	
	public List<Datapoint> getDatapoint(String ipOne, String ipTwo, short portOne, short portTwo, String proto) {
		return datapoints.get(FlowUtils.flowHash(NetworkUtils.stringIPtoInt(ipOne), NetworkUtils.stringIPtoInt(ipTwo),
				portOne, portTwo, (byte) 6));
	}

	public Map<Long, NetflowSeriesRecord> getRecords() {
		return records;
	}

}
