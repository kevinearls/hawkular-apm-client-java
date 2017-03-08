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
import java.util.Map;

import javax.ws.rs.core.Response;

import org.hawkular.apm.api.model.config.CollectorConfiguration;
import org.hawkular.apm.api.model.config.txn.ConfigMessage;
import org.hawkular.apm.api.model.config.txn.TransactionConfig;
import org.hawkular.apm.api.model.config.txn.TransactionSummary;
import org.hawkular.apm.client.jaxrs.handlers.ConfigurationHandler;
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
public class DefaultConfigurationClient extends BaseClient<ConfigurationHandler> implements
        ConfigurationClient {

    public DefaultConfigurationClient(ClientInfo clientInfo) {
        super(clientInfo, new RestFactory<>(ConfigurationHandler.class));
    }

    @Override
    public ClientResponse<CollectorConfiguration> getCollector(String type, String host, String server) {
        Response response = null;
        try {
            response = restApi().getCollectorConfiguration(type, host, server);
            JavaType javaType = simpleResolver().get(CollectorConfiguration.class);
            return new DefaultClientResponse<>(javaType, response, ResponseCodes.GET_SUCCESS_200);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    @Override
    public ClientResponse<List<TransactionSummary>> listTransactionSummary() {
        Response response = null;
        try {
            response = restApi().getBusinessTxnConfigurationSummaries();
            JavaType javaType = collectionResolver().get(List.class, TransactionSummary.class);
            return new DefaultClientResponse<>(javaType, response, ResponseCodes.GET_SUCCESS_200);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    @Override
    public ClientResponse<Map<String, TransactionConfig>> getTransaction() {
        Response response = null;
        try {
            response = restApi().getBusinessTxnConfigurationSummaries();
            JavaType javaType = mapResolver().get(Map.class, String.class, TransactionConfig.class);
            return new DefaultClientResponse<>(javaType, response, ResponseCodes.GET_SUCCESS_200);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    @Override
    public ClientResponse<TransactionConfig> getTransaction(String name) {
        Response response = null;
        try {
            response = restApi().getBusinessTxnConfiguration(name);
            JavaType javaType = simpleResolver().get(TransactionConfig.class);
            return new DefaultClientResponse<>(javaType, response, ResponseCodes.GET_SUCCESS_200);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    @Override
    public ClientResponse<List<ConfigMessage>> updateTransaction(String name, TransactionConfig config) {
        Response response = null;
        try {
            response = restApi().setBusinessTxnConfiguration(name, config);
            JavaType javaType = collectionResolver().get(List.class, ConfigMessage.class);
            return new DefaultClientResponse<>(javaType, response, ResponseCodes.UPDATE_SUCCESS_200);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    @Override
    public ClientResponse<List<ConfigMessage>> updateTransaction(Map<String, TransactionConfig> configs) {
        Response response = null;
        try {
            response = restApi().setBusinessTxnConfigurations(configs);
            JavaType javaType = collectionResolver().get(List.class, ConfigMessage.class);
            return new DefaultClientResponse<>(javaType, response, ResponseCodes.CREATE_SUCCESS_200);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    @Override
    public ClientResponse<Empty> deleteTransaction(String name) {
        Response response = null;
        try {
            response = restApi().removeBusinessTxnConfiguration(name);
            JavaType javaType = simpleResolver().get(Empty.class);
            return new DefaultClientResponse<>(javaType, response, ResponseCodes.DELETE_SUCCESS_200);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    @Override
    public ClientResponse<List<ConfigMessage>> validateTransaction(TransactionConfig config) {
        Response response = null;
        try {
            response = restApi().validateBusinessTxnConfiguration(config);
            JavaType javaType = collectionResolver().get(List.class, ConfigMessage.class);
            return new DefaultClientResponse<>(javaType, response, ResponseCodes.CREATE_SUCCESS_200);
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
