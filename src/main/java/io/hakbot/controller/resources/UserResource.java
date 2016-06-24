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
package io.hakbot.controller.resources;

import io.hakbot.controller.auth.AuthenticationNotRequired;
import io.hakbot.controller.auth.JsonWebToken;
import io.hakbot.controller.auth.KeyManager;
import io.hakbot.controller.auth.LdapAuthenticator;
import io.hakbot.controller.model.LdapUser;
import io.hakbot.controller.persistence.QueryManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Principal;

@Path("/user")
@Api(value = "user")
public class UserResource extends BaseResource {

    @POST
    @Path("login")
    @Produces(MediaType.TEXT_PLAIN)
    @ApiOperation(value = "Login")
    @AuthenticationNotRequired
    public Response validateCredentials(@FormParam("username") String username, @FormParam("password") String password) {

        LdapAuthenticator ldapAuth = new LdapAuthenticator();
        boolean isValid = ldapAuth.validateCredentials(username, password);
        if (!isValid) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        QueryManager qm = new QueryManager();
        LdapUser ldapUser = qm.getLdapUser(username);
        KeyManager km = KeyManager.getInstance();
        JsonWebToken jwt = new JsonWebToken(km.getSecretKey());
        String token = jwt.createToken(ldapUser);
        return Response.ok(token).build();
    }

    @GET
    @Path("/hakmaster")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Queries if the user is a hakmaster",
            response = Boolean.class)
    public Response isHakmaster() {
        boolean isHakMaster;
        Principal principal = getPrincipal();
        if (principal == null) {
            // authentication was already required (if enabled)
            isHakMaster = true;
        } else {
            QueryManager qm = new QueryManager();
            isHakMaster = qm.isHakMaster((LdapUser) principal);
        }
        return Response.ok(isHakMaster).build();
    }

}
