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

/**
 * Broker context container
 * 
 * @since 1.0
 */
public class KapuaBrokerContextContainer {

    private String userName;
    private String accountName;
    private String clientId;
    private String clientIp;

    public KapuaBrokerContextContainer(String userName, String clientId, String clientIp) {
        this.userName = userName;
        this.clientId = clientId;
        this.clientIp = clientIp;
    }

    public String getUserName() {
        return userName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientIp() {
        return clientIp;
    }
}
