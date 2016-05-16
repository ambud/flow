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
package com.srotya.flow.analyzer;

import com.srotya.flow.analyzer.endpoints.FlowEndpoint;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Entry point for flow analyzer application
 * 
 * @author ambudsharma
 */
public class FlowAnalyzer extends Application<FlowConfiguration> {

	public static void main(String[] args) throws Exception {
		new FlowAnalyzer().run(args);
	}

	@Override
	public void initialize(Bootstrap<FlowConfiguration> bootstrap) {
		super.initialize(bootstrap);
		bootstrap.addBundle(new AssetsBundle("/app", "/", "index.html"));
	}

	@Override
	public void run(FlowConfiguration conf, Environment env) throws Exception {
		env.jersey().register(FlowEndpoint.class);
	}

}
