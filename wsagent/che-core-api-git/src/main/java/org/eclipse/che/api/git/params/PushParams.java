/*******************************************************************************
 * Copyright (c) 2012-2016 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package org.eclipse.che.api.git.params;

import org.eclipse.che.api.git.shared.PushRequest;

import java.util.Collections;
import java.util.List;

/**
 * Arguments holder for {@link org.eclipse.che.api.git.GitConnection#push(PushParams)}.
 *
 * @author Igor Vinokur
 */
public class PushParams {

    private List<String> refSpec;
    private String       remote;
    private boolean      force;
    private int          timeout;

    private PushParams() {
    }

    /**
     * Create new {@link PushParams} instance with empty parameters
     */
    public static PushParams create() {
        return new PushParams();
    }

    /** @see PushRequest#getRefSpec() */
    public List<String> getRefSpec() {
        return refSpec == null ? Collections.emptyList() : refSpec;
    }

    /** @see PushRequest#withRefSpec(List) */
    public PushParams withRefSpec(List<String> refSpec) {
        this.refSpec = refSpec;
        return this;
    }

    /** @see PushRequest#getRemote() */
    public String getRemote() {
        return remote;
    }

    /** @see PushRequest#withRemote(String) */
    public PushParams withRemote(String remote) {
        this.remote = remote;
        return this;
    }

    /** @see PushRequest#isForce() */
    public boolean isForce() {
        return force;
    }

    /** @see PushRequest#withForce(boolean) */
    public PushParams withForce(boolean force) {
        this.force = force;
        return this;
    }

    /** @see PushRequest#getTimeout() */
    public int getTimeout() {
        return timeout;
    }

    /** @see PushRequest#withTimeout(int) */
    public PushParams withTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }
}
