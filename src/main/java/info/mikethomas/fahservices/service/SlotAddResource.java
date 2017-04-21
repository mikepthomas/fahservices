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

import info.mikethomas.jfold.ClientConnection;
import info.mikethomas.jfold.Connection;
import info.mikethomas.jfold.exceptions.SlotAddException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
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
@Path("slot-add")
@Api(value = "/slot-add", description = "Add a new slot.")
public class SlotAddResource {

    @Context
    private UriInfo context;
    private Connection connection;

    /**
     * Creates a new instance of SlotAddResource.
     */
    public SlotAddResource() {
        try {
            connection = new ClientConnection("localhost", 36330);
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retrieves representation of an instance of
     * info.mikethomas.fahservices.service.SlotAddResource.
     *
     * @param type Slot type
     * @return an instance of java.lang.String
     */
    @POST
    @Path("/{type}")
    @Produces({
        MediaType.APPLICATION_JSON,
        MediaType.APPLICATION_XML,
        MediaType.TEXT_XML
    })
    @ApiOperation(value = "slot-add {type}",
            notes = "Add a new slot.",
            position = 1)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Error", response = SlotAddException.class)
    })
    public Response getSlotAdd(
            @ApiParam(value = "slot type", allowableValues = "CPU,GPU", required = true)
            @DefaultValue("CPU")
            @PathParam("type") final String type) {
        try {
            connection.slotAdd(type);
            return Response.status(Status.OK).build();
        } catch (SlotAddException ex) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex).build();
        }
    }
}
