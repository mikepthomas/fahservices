package info.mikethomas.fahservices.service;

/*
 * #%L
 * This file is part of FAHServices.
 * %%
 * Copyright (C) 2014 - 2019 Mike Thomas <mikepthomas@outlook.com>
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Web Service.
 *
 * @author Michael Thomas (mikepthomas@outlook.com)
 * @version $Id: $Id
 */
@RestController("unpause")
@Api(value = "/unpause", description = "Unpause all or one slot(s).")
public class UnpauseResource {

    @Autowired
    private Connection connection;

    /**
     * Retrieves representation of an instance of
     * info.mikethomas.fahservices.service.UnpauseResource.
     *
     * @return an instance of java.lang.String
     */
    @ApiOperation(value = "unpause",
            notes = "Unpause.",
            position = 1)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
    })
    @RequestMapping(
            value = "/unpause",
            method = RequestMethod.GET,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE,
                MediaType.TEXT_XML_VALUE
            })
    @ResponseBody
    public ResponseEntity getUnpause() {
        connection.unpause();
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Retrieves representation of an instance of
     * info.mikethomas.fahservices.service.UnpauseResource.
     *
     * @param slot Slot number
     * @return an instance of java.lang.String
     */
    @ApiOperation(
            value = "unpause",
            notes = "Unpause the specified index.",
            position = 2
    )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK")
    })
    @RequestMapping(
            value = "/unpause/{slot}",
            method = RequestMethod.GET,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE,
                MediaType.TEXT_XML_VALUE
            })
    @ResponseBody
    public ResponseEntity getUnpause(
            @ApiParam(value = "slot number", required = true)
            @PathVariable("slot") final int slot) {
        connection.unpause(slot);
        return new ResponseEntity(HttpStatus.OK);
    }
}
