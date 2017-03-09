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

import java.util.Collection;
import java.util.List;

import javax.ws.rs.core.Response;

import org.hawkular.apm.api.model.analytics.Cardinality;
import org.hawkular.apm.api.model.analytics.CommunicationSummaryStatistics;
import org.hawkular.apm.api.model.analytics.EndpointInfo;
import org.hawkular.apm.api.model.analytics.NodeSummaryStatistics;
import org.hawkular.apm.api.model.analytics.NodeTimeseriesStatistics;
import org.hawkular.apm.api.model.analytics.Percentiles;
import org.hawkular.apm.api.model.analytics.PropertyInfo;
import org.hawkular.apm.api.model.analytics.TimeseriesStatistics;
import org.hawkular.apm.api.model.analytics.TransactionInfo;
import org.hawkular.apm.api.model.events.CompletionTime;
import org.hawkular.apm.client.jaxrs.handlers.AnalyticsHandler;
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
public class DefaultAnalyticsClient extends BaseClient<AnalyticsHandler> implements
        AnalyticsClient {

    public DefaultAnalyticsClient(ClientInfo clientInfo) {
        super(clientInfo, new RestFactory<>(AnalyticsHandler.class));
    }

    @Override
    public ClientResponse<List<EndpointInfo>> listUnboundEndpoint(Long startTime, Long endTime, Boolean compress) {
        Response response = null;
        try {
            response = restApi().getUnboundEndpoints(startTime, endTime, compress);
            JavaType javaType = collectionResolver().get(List.class, EndpointInfo.class);
            return new DefaultClientResponse<>(javaType, response, ResponseCodes.GET_SUCCESS_200);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    @Override
    public ClientResponse<List<EndpointInfo>> listBoundEndpoint(String name, Long startTime, Long endTime) {
        Response response = null;
        try {
            response = restApi().getBoundEndpoints(startTime, endTime, name);
            JavaType javaType = collectionResolver().get(List.class, EndpointInfo.class);
            return new DefaultClientResponse<>(javaType, response, ResponseCodes.GET_SUCCESS_200);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    @Override
    public ClientResponse<List<TransactionInfo>> listTransactionInfo(Criteria criteria) {
        Response response = null;
        try {
            response = restApi().getTransactionInfo(criteria);
            JavaType javaType = collectionResolver().get(List.class, TransactionInfo.class);
            return new DefaultClientResponse<>(javaType, response, ResponseCodes.GET_SUCCESS_200);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    @Override
    public ClientResponse<List<PropertyInfo>> listPropertyInfo(Criteria criteria) {
        Response response = null;
        try {
            response = restApi().getPropertyInfo(criteria);
            JavaType javaType = collectionResolver().get(List.class, PropertyInfo.class);
            return new DefaultClientResponse<>(javaType, response, ResponseCodes.GET_SUCCESS_200);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    @Override
    public ClientResponse<Long> traceCompletionCount(Criteria criteria) {
        Response response = null;
        try {
            response = restApi().getTraceCompletionCount(criteria);
            JavaType javaType = simpleResolver().get(Long.class);
            return new DefaultClientResponse<>(javaType, response, ResponseCodes.GET_SUCCESS_200);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    @Override
    public ClientResponse<Long> traceCompletionFaultCount(Criteria criteria) {
        Response response = null;
        try {
            response = restApi().getTraceCompletionFaultCount(criteria);
            JavaType javaType = simpleResolver().get(Long.class);
            return new DefaultClientResponse<>(javaType, response, ResponseCodes.GET_SUCCESS_200);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    @Override
    public ClientResponse<List<CompletionTime>> listTraceCompletionTime(Criteria criteria) {
        Response response = null;
        try {
            response = restApi().getTraceCompletionTimes(criteria);
            JavaType javaType = collectionResolver().get(List.class, CompletionTime.class);
            return new DefaultClientResponse<>(javaType, response, ResponseCodes.GET_SUCCESS_200);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    @Override
    public ClientResponse<Percentiles> traceCompletionPercentiles(Criteria criteria) {
        Response response = null;
        try {
            response = restApi().getTraceCompletionPercentiles(criteria);
            JavaType javaType = simpleResolver().get(Percentiles.class);
            return new DefaultClientResponse<>(javaType, response, ResponseCodes.GET_SUCCESS_200);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    @Override
    public ClientResponse<List<TimeseriesStatistics>> listTraceCompletionTimeseriesStatistics(Criteria criteria) {
        Response response = null;
        try {
            response = restApi().getTraceCompletionTimeseriesStatistics(criteria);
            JavaType javaType = collectionResolver().get(List.class, TimeseriesStatistics.class);
            return new DefaultClientResponse<>(javaType, response, ResponseCodes.GET_SUCCESS_200);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    @Override
    public ClientResponse<List<Cardinality>> listTraceCompletionFaultDetail(Criteria criteria) {
        Response response = null;
        try {
            response = restApi().getTraceCompletionFaultDetails(criteria);
            JavaType javaType = collectionResolver().get(List.class, Cardinality.class);
            return new DefaultClientResponse<>(javaType, response, ResponseCodes.GET_SUCCESS_200);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    @Override
    public ClientResponse<List<Cardinality>> listTraceCompletionPropertyDetail(String property, Criteria criteria) {
        Response response = null;
        try {
            response = restApi().getTraceCompletionPropertyDetails(criteria, property);
            JavaType javaType = collectionResolver().get(List.class, Cardinality.class);
            return new DefaultClientResponse<>(javaType, response, ResponseCodes.GET_SUCCESS_200);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    @Override
    public ClientResponse<List<NodeTimeseriesStatistics>> listNodeTimeseriesStatistics(Criteria criteria) {
        Response response = null;
        try {
            response = restApi().getNodeTimeseriesStatistics(criteria);
            JavaType javaType = collectionResolver().get(List.class, NodeTimeseriesStatistics.class);
            return new DefaultClientResponse<>(javaType, response, ResponseCodes.GET_SUCCESS_200);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    @Override
    public ClientResponse<Collection<NodeSummaryStatistics>> listNodeSummaryStatistics(Criteria criteria) {
        Response response = null;
        try {
            response = restApi().getNodeSummaryStatistics(criteria);
            JavaType javaType = collectionResolver().get(List.class, NodeSummaryStatistics.class);
            return new DefaultClientResponse<>(javaType, response, ResponseCodes.GET_SUCCESS_200);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    @Override
    public ClientResponse<Collection<CommunicationSummaryStatistics>> listCommunicationSummaryStatistics(
            Criteria criteria) {
        Response response = null;
        try {
            response = restApi().getCommunicationSummaryStatistics(criteria);
            JavaType javaType = collectionResolver().get(List.class, CommunicationSummaryStatistics.class);
            return new DefaultClientResponse<>(javaType, response, ResponseCodes.GET_SUCCESS_200);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    @Override
    public ClientResponse<List<TimeseriesStatistics>> listEndpointResponseTimeseriesStatistics(Criteria criteria) {
        Response response = null;
        try {
            response = restApi().getEndpointResponseTimeseriesStatistics(criteria);
            JavaType javaType = collectionResolver().get(List.class, TimeseriesStatistics.class);
            return new DefaultClientResponse<>(javaType, response, ResponseCodes.GET_SUCCESS_200);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    @Override
    public ClientResponse<List<String>> listHostName(Criteria criteria) {
        Response response = null;
        try {
            response = restApi().getHostNames(criteria);
            JavaType javaType = collectionResolver().get(List.class, String.class);
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
