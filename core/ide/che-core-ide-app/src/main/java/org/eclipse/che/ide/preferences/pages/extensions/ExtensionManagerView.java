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
package org.eclipse.che.ide.preferences.pages.extensions;

import org.eclipse.che.ide.api.extension.ExtensionDescription;
import org.eclipse.che.ide.api.mvp.View;

import java.util.List;

/** @author Evgen Vidolob */
public interface ExtensionManagerView extends View<ExtensionManagerView.ActionDelegate> {

    void setExtensions(List<ExtensionDescription> extensions);

    interface ActionDelegate {
    }
}
