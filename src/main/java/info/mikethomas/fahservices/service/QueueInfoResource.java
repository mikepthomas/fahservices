package info.mikethomas.fahservices.service;

/*
 * #%L
 * This file is part of FAHServices.
 * %%
 * Copyright (C) 2014 - 2017 Mike Thomas <mikepthomas@outlook.com>
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import info.mikethomas.jfold.Connection;
import info.mikethomas.jfold.ClientConnection;
import info.mikethomas.jfold.exceptions.QueueInfoException;
import info.mikethomas.jfold.unit.Unit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service.
 *
 * @author Michael Thomas (mikepthomas@outlook.com)
 * @version $Id: $Id
 */
@Path("queue-info")
@Api(value = "/queue-info", description = "Get work unit queue information.")
public class QueueInfoResource {

    @Context
    private UriInfo context;
    private Connection connection;

    /**
     * Creates a new instance of QueueInfoResource.
     */
    public QueueInfoResource() {
        try {
            connection = new ClientConnection("localhost", 36330);
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retrieves representation of an instance of
     * info.mikethomas.fahservices.service.QueueInfoResource.
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces({
        MediaType.APPLICATION_JSON,
        MediaType.APPLICATION_XML,
        MediaType.TEXT_XML
    })
    @ApiOperation(value = "queue-info",
            notes = "Get List of work unit queue information.",
            response = Unit.class,
            responseContainer = "List",
            position = 1)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = Unit.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "Error", response = QueueInfoException.class)
    })
    public Response getQueueInfo() {
        try {
            List<Unit> value = connection.queueInfo();
            GenericEntity<List<Unit>> entity = new GenericEntity<List<Unit>>(value) { };
            return Response.status(Status.OK).entity(entity).build();
        } catch (QueueInfoException ex) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex).build();
        }
    }
}
