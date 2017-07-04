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
package org.eclipse.kapua.app.console.commons.client.views;

import org.eclipse.kapua.app.console.commons.client.resources.icons.IconSet;

public interface  ViewDescriptor {
    String getId();

    String getName();

    IconSet getIcon();

    int getOrder();
}
