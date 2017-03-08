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

import org.hawkular.apm.api.model.config.CollectorConfiguration;
import org.hawkular.apm.api.model.config.txn.ConfigMessage;
import org.hawkular.apm.api.model.config.txn.TransactionConfig;
import org.hawkular.apm.api.model.config.txn.TransactionSummary;
import org.hawkular.client.core.ClientResponse;
import org.hawkular.client.core.jaxrs.Empty;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */
public interface ConfigurationClient {
    ClientResponse<CollectorConfiguration> getCollector(String type, String host, String server);

    ClientResponse<List<TransactionSummary>> listTransactionSummary();

    ClientResponse<Map<String, TransactionConfig>> getTransaction();

    ClientResponse<TransactionConfig> getTransaction(String name);

    ClientResponse<List<ConfigMessage>> updateTransaction(String name, TransactionConfig config);

    ClientResponse<List<ConfigMessage>> updateTransaction(Map<String, TransactionConfig> configs);

    ClientResponse<Empty> deleteTransaction(String name);

    ClientResponse<List<ConfigMessage>> validateTransaction(TransactionConfig config);

    ClientResponse<Empty> clear();
}
