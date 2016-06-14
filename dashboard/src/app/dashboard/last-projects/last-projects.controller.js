/*
 * Copyright (c) 2015-2016 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 */
'use strict';

/**
 * @ngdoc controller
 * @name dashboard.controller:DashboardLastProjectsController
 * @description This class is handling the controller of the last projects to display in the dashboard
 * @author Florent Benoit
 */
export class DashboardLastProjectsController {


  /**
   * Default constructor
   * @ngInject for Dependency injection
   */
  constructor(cheWorkspace) {
    this.cheWorkspace = cheWorkspace;

    this.projects = [];
    this.state = 'loading';

    // fetch workspaces when initializing
    let promise = cheWorkspace.fetchWorkspaces();

    promise.then(() => {
        this.projects = cheWorkspace.getAllProjects();
        this.updateProjectWorkspace();
        this.state = 'OK';
      },
      (error) => {
        if (error.status === 304) {
          // ok
          this.projects = cheWorkspace.getAllProjects();
          this.updateProjectWorkspace();
          this.state = 'OK';
          return;
        }
        this.state = 'error';
      });

    this.projectWorkspace = {};
  }

  updateProjectWorkspace() {
    let workspaces = this.cheWorkspace.getWorkspaces();
    workspaces.forEach((workspace) => {
      if (workspace.config.projects && workspace.config.projects.length > 0) {
        let projects = workspace.config.projects;
        projects.forEach((project) => {
          this.projectWorkspace[project.name] = {
            id: workspace.id,
            name: workspace.config.name
          };
        })
      }
    })
  }

  getProjects() {
    return this.projects;
  }

  getWorkspaceId(projectName) {
    return this.projectWorkspace[projectName] ? this.projectWorkspace[projectName].id : 0;
  }

  getWorkspaceName(projectName) {
    return this.projectWorkspace[projectName] ? this.projectWorkspace[projectName].name : '';
  }

}
