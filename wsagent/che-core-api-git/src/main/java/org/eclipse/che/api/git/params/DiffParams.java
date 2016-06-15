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

import org.eclipse.che.api.git.shared.DiffType;

import java.util.Collections;
import java.util.List;

/**
 * Arguments holder for {@link org.eclipse.che.api.git.GitConnection#diff(DiffParams)}.
 *
 * @author Igor Vinokur
 */
public class DiffParams {

    private List<String> fileFilter;
    private DiffType     type;
    private String       commitA;
    private String       commitB;
    private int          renameLimit;
    private boolean      noRenames;
    private boolean      isCached;

    private DiffParams() {
    }

    /**
     * Create new {@link AddParams} instance with empty parameters
     */
    public static DiffParams create() {
        return new DiffParams();
    }

    /** @return filter of file to show diff. It may be either list of file names or name of directory to show all files under them */
    public List<String> getFileFilter() {
        return fileFilter == null ? Collections.emptyList() : fileFilter;
    }

    public DiffParams withFileFilter(List<String> fileFilter) {
        this.fileFilter = fileFilter;
        return this;
    }

    /** @return type of diff output */
    public DiffType getType() {
        return type;
    }

    public DiffParams withType(DiffType type) {
        this.type = type;
        return this;
    }

    /** @return <code>true</code> if renames must not be showing in diff result */
    public boolean isNoRenames() {
        return noRenames;
    }

    public DiffParams withNoRenames(boolean noRenames) {
        this.noRenames = noRenames;
        return this;
    }

    /** @return limit of showing renames in diff output. This attribute has sense if {@link #noRenames} is <code>false</code> */
    public int getRenameLimit() {
        return renameLimit;
    }

    public DiffParams withRenameLimit(int renameLimit) {
        this.renameLimit = renameLimit;
        return this;
    }

    /** @return first commit to view changes */
    public String getCommitA() {
        return commitA;
    }

    public DiffParams withCommitA(String commitA) {
        this.commitA = commitA;
        return this;
    }

    /** @return second commit to view changes */
    public String getCommitB() {
        return commitB;
    }

    public DiffParams withCommitB(String commitB) {
        this.commitB = commitB;
        return this;
    }

    /**
     * @return if <code>false</code> (default) view changes between {@link #commitA} and working tree otherwise between {@link #commitA}
     * and index
     */
    public boolean isCached() {
        return isCached;
    }

    public DiffParams withCached(boolean cached) {
        isCached = cached;
        return this;
    }

}
