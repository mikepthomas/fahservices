package info.mikethomas.fahservices.service;

/*
 * #%L
 * This file is part of FAHServices.
 * %%
 * Copyright (C) 2014 - 2018 Mike Thomas <mikepthomas@outlook.com>
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
import info.mikethomas.jfold.exceptions.OptionsException;
import info.mikethomas.jfold.options.Options;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@RestController("options")
@Api(value = "/options", description = "List or set options with their values.")
public class OptionsResource {

    @Autowired
    private Connection connection;

    /**
     * Retrieves representation of an instance of
     * info.mikethomas.fahservices.service.OptionsResource.
     *
     * @return an instance of java.lang.String
     */
    @ApiOperation(value = "options",
            notes = "Get Options.",
            response = Options.class,
            position = 1)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = Options.class),
        @ApiResponse(code = 500, message = "Error", response = OptionsException.class)
    })
    @RequestMapping(
            value = "/options",
            method = RequestMethod.GET,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE,
                MediaType.TEXT_XML_VALUE
            })
    @ResponseBody
    public ResponseEntity getSlotOptions() {
        try {
            return new ResponseEntity(connection.options(true, true),HttpStatus.OK);
        } catch (OptionsException ex) {
            return new ResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
