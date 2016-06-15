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

import org.eclipse.che.api.git.shared.CloneRequest;

import java.util.Collections;
import java.util.List;

/**
 * Arguments holder for {@link org.eclipse.che.api.git.GitConnection#clone(CloneParams)}.
 *
 * @author Igor Vinokur
 */
public class CloneParams {

    private List<String> branchesToFetch;
    private String       remoteUri;
    private String       workingDir;
    private String       remoteName;
    private int          timeout;

    private CloneParams() {
    }

    /**
     * Create new {@link CloneParams} instance with empty parameters
     */
    public static CloneParams create() {
        return new CloneParams();
    }

    /** @see CloneRequest#getRemoteUri() */
    public String getRemoteUri() {
        return remoteUri;
    }

    /** @see CloneRequest#withRemoteUri(String) */
    public CloneParams withRemoteUri(String remoteUri) {
        this.remoteUri = remoteUri;
        return this;
    }

    /** @see CloneRequest#getBranchesToFetch() */
    public List<String> getBranchesToFetch() {
        return branchesToFetch == null ? Collections.emptyList() : branchesToFetch;
    }

    /** @see CloneRequest#withBranchesToFetch(List) */
    public CloneParams withBranchesToFetch(List<String> branchesToFetch) {
        this.branchesToFetch = branchesToFetch;
        return this;
    }

    /** @see CloneRequest#getWorkingDir() */
    public String getWorkingDir() {
        return workingDir;
    }

    /** @see CloneRequest#setWorkingDir(String) */
    public void setWorkingDir(String workingDir) {
        this.workingDir = workingDir;
    }

    /** @see CloneRequest#withWorkingDir(String) */
    public CloneParams withWorkingDir(String workingDir) {
        this.workingDir = workingDir;
        return this;
    }

    /** @see CloneRequest#getRemoteName() */
    public String getRemoteName() {
        return remoteName;
    }

    /** @see CloneRequest#setRemoteName(String) */
    public void setRemoteName(String remoteName) {
        this.remoteName = remoteName;
    }

    /** @see CloneRequest#withRemoteName(String) */
    public CloneParams withRemoteName(String remoteName) {
        this.remoteName = remoteName;
        return this;
    }

    /** @see CloneRequest#getTimeout() */
    public int getTimeout() {
        return timeout;
    }

    /** @see CloneRequest#withTimeout(int) */
    public CloneParams withTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }
}
