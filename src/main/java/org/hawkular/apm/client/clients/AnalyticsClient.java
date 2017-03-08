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
import org.hawkular.apm.api.services.Criteria;
import org.hawkular.client.core.ClientResponse;
import org.hawkular.client.core.jaxrs.Empty;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */
public interface AnalyticsClient {
    ClientResponse<List<EndpointInfo>> listUnboundEndpoint(Long startTime, Long endTime, Boolean compress);

    ClientResponse<List<EndpointInfo>> listBoundEndpoint(String name, Long startTime, Long endTime);

    ClientResponse<List<TransactionInfo>> listTransactionInfo(Criteria criteria);

    ClientResponse<List<PropertyInfo>> listPropertyInfo(Criteria criteria);

    ClientResponse<Long> traceCompletionCount(Criteria criteria);

    ClientResponse<Long> traceCompletionFaultCount(Criteria criteria);

    ClientResponse<List<CompletionTime>> listTraceCompletionTime(Criteria criteria);

    ClientResponse<Percentiles> traceCompletionPercentiles(Criteria criteria);

    ClientResponse<List<TimeseriesStatistics>> listTraceCompletionTimeseriesStatistics(Criteria criteria);

    ClientResponse<List<Cardinality>> listTraceCompletionFaultDetail(Criteria criteria);

    ClientResponse<List<Cardinality>> listTraceCompletionPropertyDetail(String property, Criteria criteria);

    ClientResponse<List<NodeTimeseriesStatistics>> listNodeTimeseriesStatistics(Criteria criteria);

    ClientResponse<Collection<NodeSummaryStatistics>> listNodeSummaryStatistics(Criteria criteria);

    ClientResponse<Collection<CommunicationSummaryStatistics>> listCommunicationSummaryStatistics(Criteria criteria);

    ClientResponse<List<TimeseriesStatistics>> listEndpointResponseTimeseriesStatistics(Criteria criteria);

    ClientResponse<List<String>> listHostName(Criteria criteria);

    ClientResponse<Empty> clear();

}
