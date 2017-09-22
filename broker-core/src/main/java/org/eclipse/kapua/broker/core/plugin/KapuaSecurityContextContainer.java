/*******************************************************************************
 * Copyright (c) 2017 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.broker.core.plugin;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.activemq.broker.Connector;
import org.apache.activemq.broker.TransportConnector;
import org.apache.activemq.command.ConnectionId;
import org.apache.activemq.filter.DestinationMapEntry;
import org.apache.activemq.security.AuthorizationEntry;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.service.authentication.KapuaPrincipal;
import org.eclipse.kapua.service.authentication.token.AccessToken;
import org.eclipse.kapua.service.device.registry.connection.DeviceConnection;

/**
 * Kapua securoty context container used for add/remove connection
 * 
 * @since 1.0
 */
public class KapuaSecurityContextContainer {

    // logging purpose
    private List<String> authDestinations;

    private KapuaPrincipal principal;

    private String userName;
    private String password;
    private String clientId;
    private String clientIp;
    private ConnectionId connectionId;
    private ConnectionId previousConnectionId;

    private KapuaId scopeId;
    private KapuaId userId;
    private String accountName;
    private String fullClientId;

    private ConnectorDescriptor connectorDescriptor;

    private boolean[] hasPermissions;
    @SuppressWarnings("rawtypes")
    private List<DestinationMapEntry> authorizationMap;

    private DeviceConnection deviceConnection;

    @SuppressWarnings("rawtypes")
    KapuaSecurityContextContainer(String userName, String password, String clientId, String clientIp, ConnectionId connectionId) {
        this.password = password;
        this.userName = userName;
        this.clientId = clientId;
        this.clientIp = clientIp;
        this.connectionId = connectionId;
        authDestinations = new ArrayList<>();
        authorizationMap = new ArrayList<DestinationMapEntry>();
    }

    public void updateScopeUserId(AccessToken accessToken) {
        scopeId = accessToken.getScopeId();
        userId = accessToken.getUserId();
        // multiple account stealing link fix
        fullClientId = MessageFormat.format(AclConstants.MULTI_ACCOUNT_CLIENT_ID, scopeId.getId().longValue(), clientId);
    }

    public void updateScopeUserId(KapuaPrincipal kapuaPrincipal) {
        scopeId = kapuaPrincipal.getAccountId();
        userId = kapuaPrincipal.getUserId();
        // multiple account stealing link fix
        fullClientId = MessageFormat.format(AclConstants.MULTI_ACCOUNT_CLIENT_ID, scopeId.getId().longValue(), clientId);
    }

    public void updateConnectorDescriptor(Connector connector) {
        String connectorName = (((TransportConnector) connector).getName());
        connectorDescriptor = ConnectorDescriptorProviders.getDescriptor(connectorName);
        if (connectorDescriptor == null) {
            throw new IllegalStateException(String.format("Unable to find connector descriptor for connector '%s'", connectorName));
        }
    }

    public void addAuthorizationEntry(Collection<AuthorizationEntry> entry) {
        authorizationMap.addAll(entry);
    }

    @SuppressWarnings("rawtypes")
    public List<DestinationMapEntry> getAuthorizationMap() {
        return authorizationMap;
    }

    public List<String> getAuthDestinations() {
        return authDestinations;
    }

    public KapuaPrincipal getPrincipal() {
        return principal;
    }

    public void setPrincipal(KapuaPrincipal principal) {
        this.principal = principal;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientIp() {
        return clientIp;
    }

    public ConnectionId getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(ConnectionId connectionId) {
        this.connectionId = connectionId;
    }

    public ConnectionId getPreviousConnectionId() {
        return previousConnectionId;
    }

    public void setPreviousConnectionId(ConnectionId previousConnectionId) {
        this.previousConnectionId = previousConnectionId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getFullClientId() {
        return fullClientId;
    }

    public boolean[] getHasPermissions() {
        return hasPermissions;
    }

    public void setHasPermissions(boolean[] hasPermissions) {
        this.hasPermissions = hasPermissions;
    }

    public KapuaId getScopeId() {
        return scopeId;
    }

    public KapuaId getUserId() {
        return userId;
    }

    public DeviceConnection getDeviceConnection() {
        return deviceConnection;
    }

    public void setDeviceConnection(DeviceConnection deviceConnection) {
        this.deviceConnection = deviceConnection;
    }

    public ConnectorDescriptor getConnectorDescriptor() {
        return connectorDescriptor;
    }

}
