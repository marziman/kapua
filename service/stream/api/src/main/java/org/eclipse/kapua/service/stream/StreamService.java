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
 *     Red Hat Inc
 *******************************************************************************/
package org.eclipse.kapua.service.stream;

import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.message.device.data.KapuaDataMessage;
import org.eclipse.kapua.service.KapuaDomainService;
import org.eclipse.kapua.service.KapuaService;
import org.eclipse.kapua.service.device.management.message.response.KapuaResponseMessage;

public interface StreamService extends KapuaService, KapuaDomainService<StreamDomain> {

    public static final StreamDomain STREAM_DOMAIN = new StreamDomain();

    @Override
    public default StreamDomain getServiceDomain() {
        return STREAM_DOMAIN;
    }

    KapuaResponseMessage<?, ?> publish(KapuaDataMessage message, Long timeout)
            throws KapuaException;
}
