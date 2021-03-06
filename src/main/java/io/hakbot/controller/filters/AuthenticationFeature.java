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
package io.hakbot.controller.filters;

import io.hakbot.controller.Config;
import io.hakbot.controller.ConfigItem;
import io.hakbot.controller.auth.AuthenticationNotRequired;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;

@Provider
public class AuthenticationFeature implements DynamicFeature {

    private static final boolean ENFORCE_AUTHENTICATION = Config.getInstance().getPropertyAsBoolean(ConfigItem.ENFORCE_AUTHENTICATION);

    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {
        if (!ENFORCE_AUTHENTICATION) {
            return;
        }
        Method method = resourceInfo.getResourceMethod();
        if (!method.isAnnotationPresent(AuthenticationNotRequired.class)) {
            AuthenticationFilter authenticationFilter = new AuthenticationFilter();
            context.register(authenticationFilter);
        }
    }

}