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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.hawkular.client.core.ClientInfo;

import com.google.common.base.Throwables;

/**
 * @author Jeeva Kandasamy (jkandasa)
 */
public class HawkularApmClientBuilder {

    private URI uri;
    private Optional<String> username = Optional.empty();
    private Optional<String> password = Optional.empty();
    private Map<String, Object> headers = new HashMap<>();

    public HawkularApmClientBuilder(String tenant) {
        try {
            uri = new URI("http://127.0.0.1:8080");
        } catch (URISyntaxException e) {
            Throwables.propagate(e);
        }
        headers.put(HawkularApmClient.KEY_HEADER_TENANT, tenant);
    }

    public HawkularApmClientBuilder uri(String uri) throws URISyntaxException {
        return uri(new URI(uri));
    }

    public HawkularApmClientBuilder uri(URI uri) {
        this.uri = uri;
        return this;
    }

    public HawkularApmClientBuilder basicAuthentication(String username, String password) {
        this.username = Optional.ofNullable(username);
        this.password = Optional.ofNullable(password);
        return this;
    }

    public HawkularApmClientBuilder basicAuthentication(Optional<String> username, Optional<String> password) {
        this.username = username;
        this.password = password;
        return this;
    }

    public HawkularApmClientBuilder tokenAuthentication(String token) {
        headers.put(HawkularApmClient.KEY_HEADER_AUTHORIZATION, "Bearer " + token);
        return this;
    }

    public HawkularApmClientBuilder adminTokenAuthentication(String token) {
        headers.put(HawkularApmClient.KEY_HEADER_ADMIN_TOKEN, token);
        return this;
    }

    public HawkularApmClientBuilder addHeader(String key, Object value) {
        headers.put(key, value);
        return this;
    }

    public HawkularApmClient build() {
        ClientInfo clientInfo = new ClientInfo(uri, username, password, headers);
        return new HawkularApmClient(clientInfo);
    }

}
