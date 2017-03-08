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

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hawkular.apm.api.model.trace.Trace;
import org.hawkular.apm.api.services.Criteria;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

@Path("/hawkular/apm/traces")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface TraceHandler {
    @POST
    @Path("/fragments")
    Response addTraces(List<Trace> traces);

    @GET
    @Path("/fragments/{id}")
    Response getFragment(@PathParam("id") String id);

    @GET
    @Path("/complete/{id}")
    Response getTrace(@PathParam("id") String id);

    @GET
    @Path("fragments/search")
    Response queryFragments(@QueryParam("criteria") Criteria criteria);

    @DELETE
    @Path("/")
    Response clear();
}
