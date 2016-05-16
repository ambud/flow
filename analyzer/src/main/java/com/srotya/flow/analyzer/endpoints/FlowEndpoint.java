package com.srotya.flow.analyzer.endpoints;

import java.util.Collection;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.srotya.flow.analyzer.endpoints.NetflowSeriesRecord.Datapoint;
import com.srotya.flow.analyzer.storage.FlowStore;

@Path("/flow")
public class FlowEndpoint {

	@GET
	public String getVersion() {
		return "0.0.1";
	}
	
	@Path("/records")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Collection<NetflowSeriesRecord> getFlowRecords() {
		return FlowStore.getInstance().getRecords().values();
	}

	@Path("/record/{ipOne}/{ipTwo}/{portOne}/{portTwo}/{proto}")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public NetflowSeriesRecord getFlowRecord(@PathParam("ipOne") String ipOne, @PathParam("ipTwo") String ipTwo,
			@PathParam("portOne") short portOne, @PathParam("portTwo") short portTwo, @PathParam("proto") String proto) {
		NetflowSeriesRecord record = FlowStore.getInstance().getRecord(ipOne, ipTwo, portOne, portTwo, proto);
		if(record!=null) {
			return record;
		}else {
			return null;
		}
	}
	
	@Path("/series/{ipOne}/{ipTwo}/{portOne}/{portTwo}/{proto}")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Datapoint> getDatapoints(@PathParam("ipOne") String ipOne, @PathParam("ipTwo") String ipTwo,
			@PathParam("portOne") short portOne, @PathParam("portTwo") short portTwo, @PathParam("proto") String proto) {
		return FlowStore.getInstance().getDatapoint(ipOne, ipTwo, portOne, portTwo, proto);
	}

}
