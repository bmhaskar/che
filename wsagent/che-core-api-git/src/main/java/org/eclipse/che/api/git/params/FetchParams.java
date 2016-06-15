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

import org.eclipse.che.api.git.shared.FetchRequest;

import java.util.Collections;
import java.util.List;

/**
 * Arguments holder for {@link org.eclipse.che.api.git.GitConnection#fetch(FetchParams)}.
 *
 * @author Igor Vinokur
 */
public class FetchParams {

    private List<String> refSpec;
    private String       remote;
    private int          timeout;
    private boolean      isRemoveDeletedRefs;

    private FetchParams() {
    }

    /**
     * Create new {@link FetchParams} instance with empty parameters
     */
    public static FetchParams create() {
        return new FetchParams();
    }

    /** @see FetchRequest#getRefSpec() */
    public List<String> getRefSpec() {
        return refSpec == null ? Collections.emptyList() : refSpec;
    }

    /** @see FetchRequest#withRefSpec(List) */
    public FetchParams withRefSpec(List<String> refSpec) {
        this.refSpec = refSpec;
        return this;
    }

    /** @see FetchRequest#getRemote() */
    public String getRemote() {
        return remote;
    }

    /** @see FetchRequest#withRemote(String) */
    public FetchParams withRemote(String remote) {
        this.remote = remote;
        return this;
    }

    /** @see FetchRequest#isRemoveDeletedRefs() */
    public boolean isRemoveDeletedRefs() {
        return isRemoveDeletedRefs;
    }

    /** @see FetchRequest#withRemoveDeletedRefs(boolean) */
    public FetchParams withRemoveDeletedRefs(boolean removeDeletedRefs) {
        isRemoveDeletedRefs = removeDeletedRefs;
        return this;
    }

    /** @see FetchRequest#getTimeout() */
    public int getTimeout() {
        return timeout;
    }

    /** @see FetchRequest#withTimeout(int) */
    public FetchParams withTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }
}
