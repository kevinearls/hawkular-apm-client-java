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
package org.hawkular.apm.client;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import org.hawkular.apm.client.clients.AnalyticsClient;
import org.hawkular.apm.client.clients.ConfigurationClient;
import org.hawkular.apm.client.clients.DefaultAnalyticsClient;
import org.hawkular.apm.client.clients.DefaultConfigurationClient;
import org.hawkular.apm.client.clients.DefaultServiceDeploymentClient;
import org.hawkular.apm.client.clients.DefaultTraceClient;
import org.hawkular.apm.client.clients.ServiceDeploymentClient;
import org.hawkular.apm.client.clients.TraceClient;
import org.hawkular.client.core.ClientInfo;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */
public class HawkularApmClient {
    static final String KEY_HEADER_TENANT = "Hawkular-Tenant";
    static final String KEY_HEADER_AUTHORIZATION = "Authorization";
    static final String KEY_HEADER_ADMIN_TOKEN = "Hawkular-Admin-Token";

    private final AnalyticsClient analyticsClient;
    private final ConfigurationClient configClient;
    private final ServiceDeploymentClient serviceDeploymentClient;
    private final TraceClient traceClient;

    private ClientInfo clientInfo;

    public HawkularApmClient(ClientInfo clientInfo) {
        checkNotNull(clientInfo);
        checkArgument(clientInfo.getHeaders().containsKey(KEY_HEADER_TENANT), "Hawkular-Tenant header is missing");

        this.clientInfo = clientInfo;
        analyticsClient = new DefaultAnalyticsClient(clientInfo);
        configClient = new DefaultConfigurationClient(clientInfo);
        serviceDeploymentClient = new DefaultServiceDeploymentClient(clientInfo);
        traceClient = new DefaultTraceClient(clientInfo);
    }

    public static HawkularApmClientBuilder builder(String tenant) {
        return new HawkularApmClientBuilder(tenant);
    }

    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public String getTenant() {
        return (String) clientInfo.getHeaders().get(KEY_HEADER_TENANT);
    }

    public AnalyticsClient analytics() {
        return analyticsClient;
    }

    public ConfigurationClient configuration() {
        return configClient;
    }

    public ServiceDeploymentClient serviceDeployment() {
        return serviceDeploymentClient;
    }

    public TraceClient trace() {
        return traceClient;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        HawkularApmClient that = (HawkularApmClient) o;

        return clientInfo != null ? clientInfo.equals(that.clientInfo) : that.clientInfo == null;

    }

    public int hashCode() {
        return clientInfo != null ? clientInfo.hashCode() : 0;
    }

    public String toString() {
        return "HawkularApmClient{" +
                "analyticsClient=" + analyticsClient +
                ", configurationClient=" + configClient +
                ", serviceDeploymentClient=" + serviceDeploymentClient +
                ", traceClient=" + traceClient +
                ", clientInfo=" + clientInfo +
                '}';
    }
}
