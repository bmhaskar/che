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
package org.eclipse.che.git.impl;

import com.google.common.io.Files;

import org.eclipse.che.api.git.GitConnection;
import org.eclipse.che.api.git.GitConnectionFactory;
import org.eclipse.che.api.git.GitException;
import org.eclipse.che.api.git.params.RemoteAddParams;
import org.eclipse.che.api.git.params.RemoteUpdateParams;
import org.eclipse.che.api.git.shared.RemoteAddRequest;
import org.eclipse.che.api.git.shared.RemoteUpdateRequest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.eclipse.che.dto.server.DtoFactory.newDto;
import static org.eclipse.che.git.impl.GitTestUtil.cleanupTestRepo;
import static org.eclipse.che.git.impl.GitTestUtil.connectToGitRepositoryWithContent;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

/**
 * @author Eugene Voevodin
 */
public class RemoteUpdateTest {

    private File repository;

    @BeforeMethod
    public void setUp() {
        repository = Files.createTempDir();
    }

    @AfterMethod
    public void cleanUp() {
        cleanupTestRepo(repository);
    }

    @Test(dataProvider = "GitConnectionFactory", dataProviderClass = GitConnectionFactoryProvider.class)
    public void testUpdateBranches(GitConnectionFactory connectionFactory) throws GitException, IOException {
        //given
        GitConnection connection = connectToGitRepositoryWithContent(connectionFactory, repository);
        addInitialRemote(connection);
        //when
        //change branch1 to branch2
        RemoteUpdateParams request = RemoteUpdateParams.create().withName("newRemote").withBranches(Arrays.asList("branch2"));
        connection.remoteUpdate(request);
        //then
        assertEquals(parseAllConfig(connection).get("remote.newRemote.fetch").get(0),
                     "+refs/heads/branch2:refs/remotes/newRemote/branch2");
    }

    @Test(dataProvider = "GitConnectionFactory", dataProviderClass = GitConnectionFactoryProvider.class)
    public void testAddUrl(GitConnectionFactory connectionFactory) throws GitException, IOException {
        //given
        GitConnection connection = connectToGitRepositoryWithContent(connectionFactory, repository);
        addInitialRemote(connection);
        //when
        connection.remoteUpdate(RemoteUpdateParams.create().withName("newRemote").withAddUrl(Arrays.asList("new.com")));
        //then
        assertTrue(parseAllConfig(connection).get("remote.newRemote.url").contains("new.com"));
    }

    @Test(dataProvider = "GitConnectionFactory", dataProviderClass = GitConnectionFactoryProvider.class)
    public void testAddPushUrl(GitConnectionFactory connectionFactory) throws GitException, IOException {
        //given
        GitConnection connection = connectToGitRepositoryWithContent(connectionFactory, repository);
        addInitialRemote(connection);
        //when
        connection.remoteUpdate(RemoteUpdateParams.create().withName("newRemote").withAddPushUrl(Arrays.asList("pushurl1")));
        //then
        assertTrue(parseAllConfig(connection).get("remote.newRemote.pushurl").contains("pushurl1"));
    }

    @Test(dataProvider = "GitConnectionFactory", dataProviderClass = GitConnectionFactoryProvider.class)
    public void testDeleteUrl(GitConnectionFactory connectionFactory) throws GitException, IOException {
        //given
        //add url
        GitConnection connection = connectToGitRepositoryWithContent(connectionFactory, repository);
        addInitialRemote(connection);
        RemoteUpdateRequest addRequest = newDto(RemoteUpdateRequest.class);
        addRequest.setName("newRemote");
        addRequest.setAddUrl(Arrays.asList("newRemote"));
        //when
        connection.remoteUpdate(RemoteUpdateParams.create().withName("newRemote").withRemoveUrl(Arrays.asList("newurl")));
        //then
        assertFalse(!parseAllConfig(connection).containsKey("remote.newRemote.url"));
    }

    @Test(dataProvider = "GitConnectionFactory", dataProviderClass = GitConnectionFactoryProvider.class)
    public void testDeletePushUrl(GitConnectionFactory connectionFactory) throws GitException, IOException {
        //given
        GitConnection connection = connectToGitRepositoryWithContent(connectionFactory, repository);
        addInitialRemote(connection);
        //add push url
        connection.remoteUpdate(RemoteUpdateParams.create().withName("newRemote").withAddUrl(Arrays.asList("pushurl")));

        //when
        connection.remoteUpdate(RemoteUpdateParams.create().withName("newRemote").withRemovePushUrl(Arrays.asList("pushurl")));
        //then
        assertNull(parseAllConfig(connection).get("remote.newRemote.pushurl"));
    }

    private Map<String, List<String>> parseAllConfig(GitConnection connection) throws GitException {
        Map<String, List<String>> config = new HashMap<>();
        List<String> lines = connection.getConfig().getList();
        for (String outLine : lines) {
            String[] pair = outLine.split("=");
            List<String> list = config.get(pair[0]);
            if (list == null) {
                list = new LinkedList<>();
            }
            if (pair.length == 2) {
                list.add(pair[1]);
            }
            config.put(pair[0], list);
        }
        return config;
    }

    private void addInitialRemote(GitConnection connection) throws GitException {
        RemoteAddParams params = RemoteAddParams.create()
                                             .withName("newRemote")
                                             .withUrl("newRemote.url")
                                             .withBranches(Arrays.asList("branch1"));
        connection.remoteAdd(params);
    }
}

