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

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hawkular.apm.api.model.config.txn.TransactionConfig;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */

@Path("/hawkular/apm/config")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ConfigurationHandler {
    @GET
    @Path("/collector")
    Response getCollectorConfiguration(@QueryParam("type") String type, @QueryParam("host") String host,
            @QueryParam("server") String server);

    @GET
    @Path("/transaction/summary")
    Response getBusinessTxnConfigurationSummaries();

    @GET
    @Path("/transaction/full")
    Response getBusinessTxnConfigurations();

    @GET
    @Path("/transaction/full/{name}")
    Response getBusinessTxnConfiguration(@PathParam("name") String name);

    @PUT
    @Path("/transaction/full/{name}")
    Response setBusinessTxnConfiguration(@PathParam("name") String name, TransactionConfig config);

    @POST
    @Path("/transaction/full")
    Response setBusinessTxnConfigurations(Map<String, TransactionConfig> configs);

    @DELETE
    @Path("/transaction/full/{name}")
    Response removeBusinessTxnConfiguration(@PathParam("name") String name);

    @POST
    @Path("/transaction/validate")
    Response validateBusinessTxnConfiguration(TransactionConfig config);

    @DELETE
    @Path("/")
    Response clear();
}
