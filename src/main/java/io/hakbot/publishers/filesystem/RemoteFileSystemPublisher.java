/*
 * This file is part of Hakbot Origin Controller.
 *
 * Hakbot Origin Controller is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * Hakbot Origin Controller is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Hakbot Origin Controller. If not, see http://www.gnu.org/licenses/.
 */
package io.hakbot.publishers.filesystem;

import io.hakbot.controller.logging.Logger;
import io.hakbot.controller.model.Job;
import io.hakbot.controller.plugin.RemoteInstance;
import io.hakbot.controller.plugin.RemoteInstanceAutoConfig;
import io.hakbot.providers.Provider;
import io.hakbot.publishers.BasePublisher;
import io.hakbot.util.JsonUtil;
import javax.json.JsonObject;
import java.io.File;
import java.util.Map;

public class RemoteFileSystemPublisher extends BasePublisher {

    // Setup logging
    private static final Logger logger = Logger.getLogger(RemoteFileSystemPublisher.class);

    private static Map<String, RemoteInstance> instanceMap = new RemoteInstanceAutoConfig().createMap(Type.PUBLISHER, "remotefs");

    private RemoteInstance remoteInstance;

    @Override
    public boolean initialize(Job job, Provider provider) {
        super.initialize(job, provider);

        JsonObject payload = JsonUtil.toJsonObject(job.getProviderPayload());
        remoteInstance = instanceMap.get(JsonUtil.getString(payload, "instance"));
        if (remoteInstance == null) {
            job.addMessage("RemoteFileSystem instance cannot be found or is not defined.");
            return false;
        }
        return true;
    }

    public boolean publish(Job job) {
        File report = getResult(new File(System.getProperty("java.io.tmpdir")));
        if (report == null) {
            return false;
        }
        boolean success = false;
        /*
        try {
            FileSystemOptions fsOptions = new FileSystemOptions();
            SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(fsOptions, "no");
            FileSystemManager fsManager = VFS.getManager();
            String uri = "sftp://user:password@host:port/absolute-path";
            FileObject fo = fsManager.resolveFile(uri, fsOptions);
            if (!success) {
                job.addMessage("Failed to upload result to remote file system");
                job.addMessage(response.getStatusInfo().getReasonPhrase());
            }
            formDataMultiPart.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        */
        return success;
    }

    public String getName() {
        return "Remote File System";
    }

    public String getDescription() {
        return "Publishes results to a remote file system.";
    }

}
