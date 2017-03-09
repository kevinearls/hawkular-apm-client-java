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
package org.hawkular.apm.client.clients;

import java.util.List;

import javax.ws.rs.core.Response;

import org.hawkular.apm.api.model.trace.Trace;
import org.hawkular.apm.client.jaxrs.handlers.TraceHandler;
import org.hawkular.apm.client.model.Criteria;
import org.hawkular.client.core.BaseClient;
import org.hawkular.client.core.ClientInfo;
import org.hawkular.client.core.ClientResponse;
import org.hawkular.client.core.DefaultClientResponse;
import org.hawkular.client.core.jaxrs.Empty;
import org.hawkular.client.core.jaxrs.ResponseCodes;
import org.hawkular.client.core.jaxrs.RestFactory;

import com.fasterxml.jackson.databind.JavaType;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */
public class DefaultTraceClient extends BaseClient<TraceHandler> implements
        TraceClient {

    public DefaultTraceClient(ClientInfo clientInfo) {
        super(clientInfo, new RestFactory<>(TraceHandler.class));
    }

    @Override
    public ClientResponse<Empty> addTrace(List<Trace> traces) {
        Response response = null;
        try {
            response = restApi().addTraces(traces);
            JavaType javaType = simpleResolver().get(Empty.class);
            return new DefaultClientResponse<>(javaType, response, ResponseCodes.CREATE_SUCCESS_200);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    @Override
    public ClientResponse<Trace> getFragment(String id) {
        Response response = null;
        try {
            response = restApi().getFragment(id);
            JavaType javaType = simpleResolver().get(Trace.class);
            return new DefaultClientResponse<>(javaType, response, ResponseCodes.GET_SUCCESS_200);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    @Override
    public ClientResponse<Trace> getTrace(String id) {
        Response response = null;
        try {
            response = restApi().getTrace(id);
            JavaType javaType = simpleResolver().get(Trace.class);
            return new DefaultClientResponse<>(javaType, response, ResponseCodes.GET_SUCCESS_200);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    @Override
    public ClientResponse<List<Trace>> queryFragments(Criteria criteria) {
        Response response = null;
        try {
            response = restApi().queryFragments(criteria);
            JavaType javaType = collectionResolver().get(List.class, Trace.class);
            return new DefaultClientResponse<>(javaType, response, ResponseCodes.GET_SUCCESS_200);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    @Override
    public ClientResponse<Empty> clear() {
        Response response = null;
        try {
            response = restApi().clear();
            JavaType javaType = simpleResolver().get(Empty.class);
            return new DefaultClientResponse<>(javaType, response, ResponseCodes.DELETE_SUCCESS_200);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

}
