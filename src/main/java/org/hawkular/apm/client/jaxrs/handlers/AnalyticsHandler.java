/*
 * Copyright 2015-2017 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hawkular.apm.client.jaxrs.handlers;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hawkular.apm.client.model.Criteria;


/**
 * @author Jeeva Kandasamy (jkandasa)
 */

@Path("/hawkular/apm/analytics")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface AnalyticsHandler {
    @GET
    @Path("/unboundendpoints")
    Response getUnboundEndpoints(@QueryParam("startTime") Long startTime, @QueryParam("endTime") Long endTime,
            @QueryParam("compress") Boolean compress);

    @GET
    @Path("/boundendpoints/{name}")
    Response getBoundEndpoints(@QueryParam("startTime") Long startTime, @QueryParam("endTime") Long endTime,
            @PathParam("name") String name);

    @GET
    @Path("/transactions")
    Response getTransactionInfo(@QueryParam("criteria") Criteria criteria);

    @GET
    @Path("/properties")
    Response getPropertyInfo(@QueryParam("criteria") Criteria criteria);

    @GET
    @Path("/trace/completion/count")
    Response getTraceCompletionCount(@QueryParam("criteria") Criteria criteria);

    @GET
    @Path("/trace/completion/faultcount")
    Response getTraceCompletionFaultCount(@QueryParam("criteria") Criteria criteria);

    @GET
    @Path("/trace/completion/times")
    Response getTraceCompletionTimes(@QueryParam("criteria") Criteria criteria);

    @GET
    @Path("/trace/completion/percentiles")
    Response getTraceCompletionPercentiles(@QueryParam("criteria") Criteria criteria);

    @GET
    @Path("/trace/completion/statistics")
    Response getTraceCompletionTimeseriesStatistics(@QueryParam("criteria") Criteria criteria);

    @GET
    @Path("/trace/completion/faults")
    Response getTraceCompletionFaultDetails(@QueryParam("criteria") Criteria criteria);

    @GET
    @Path("/trace/completion/property/{property}")
    Response getTraceCompletionPropertyDetails(@QueryParam("criteria") Criteria criteria,
            @PathParam("property") String property);

    @GET
    @Path("/node/statistics")
    Response getNodeTimeseriesStatistics(@QueryParam("criteria") Criteria criteria);

    @GET
    @Path("/node/summary")
    Response getNodeSummaryStatistics(@QueryParam("criteria") Criteria criteria);

    @GET
    @Path("/communication/summary")
    Response getCommunicationSummaryStatistics(@QueryParam("criteria") Criteria criteria);

    @GET
    @Path("/endpoint/response/statistics")
    Response getEndpointResponseTimeseriesStatistics(@QueryParam("criteria") Criteria criteria);

    @GET
    @Path("/hostnames")
    Response getHostNames(@QueryParam("criteria") Criteria criteria);

    @DELETE
    @Path("/")
    Response clear();
}
