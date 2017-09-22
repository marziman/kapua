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
 * ACL actions
 * 
 * @since 1.0
 */
public class AclActions {

    public static final AclActions ALL = new AclActions(true, true, true);
    public static final AclActions READ = new AclActions(true, false, false);
    public static final AclActions WRITE = new AclActions(false, true, false);
    public static final AclActions ADMIN = new AclActions(false, false, true);

    public static final AclActions READ_ADMIN = new AclActions(true, false, true);
    public static final AclActions WRITE_ADMIN = new AclActions(false, true, true);

    private boolean read;
    private boolean write;
    private boolean admin;

    protected AclActions(boolean read, boolean write, boolean admin) {
        this.read = read;
        this.write = write;
        this.admin = admin;
    }

    public boolean isRead() {
        return read;
    }

    public boolean isWrite() {
        return write;
    }

    public boolean isAdmin() {
        return admin;
    }

}
