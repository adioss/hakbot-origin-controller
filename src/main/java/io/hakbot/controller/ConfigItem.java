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
package io.hakbot.controller;

/**
 * This class provides a statically typed way to specify the named pairs
 * in a property file. All names which have a corresponding value in the
 * property file should be added to the enum.
 * Refer to {@link Config#getProperty(ConfigItem) Config.getProperty}
 */
public enum	ConfigItem {

    APPLICATION_NAME("application.name"),
    APPLICATION_VERSION("application.version"),
    APPLICATION_TIMESTAMP("application.timestamp"),

    QUEUE_CHECK_INTERVAL("queue.check.interval"),
    JOB_CLEANUP_INTERVAL("job.cleanup.interval"),
    JOB_PRUNE_INTERVAL("job.prune.interval"),
    JOB_PRUNE_CHECK_INTERVAL("job.prune.check.interval"),
    MAX_JOB_SIZE("max.job.size"),
    MAX_QUEUE_SIZE("max.queue.size"),

    DATABASE_MODE("database.mode"),
    DATABASE_PORT("database.port"),

    PROVIDERS_ENALBED("providers.enabled"),
    PUBLISHERS_ENABLED("publishers.enabled"),

    ENFORCE_AUTHENTICATION("enforce.authentication"),
    ENFORCE_AUTHORIZATION("enforce.authorization"),

    LDAP_SERVER_URL("ldap.server.url"),
    LDAP_DOMAIN("ldap.domain"),

    GZIP_COMPRESSION_ENABLED("proto.gzip.enabled");

    String propertyName;
    private ConfigItem(String item) {
        this.propertyName = item;
    }

}
