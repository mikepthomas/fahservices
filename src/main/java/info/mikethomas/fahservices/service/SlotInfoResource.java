package info.mikethomas.fahservices.service;

/*
 * #%L
 * This file is part of FAHServices.
 * %%
 * Copyright (C) 2014 - 2024 Mike Thomas <mikepthomas@outlook.com>
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
import info.mikethomas.jfold.exceptions.SlotInfoException;
import info.mikethomas.jfold.slot.Slot;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Web Service.
 *
 * @author Michael Thomas (mikepthomas@outlook.com)
 * @version $Id: $Id
 */
@RestController("slot-info")
@Tag(name = "Slot Info", description = "Get slot information.")
public class SlotInfoResource {

    @Autowired
    private Connection connection;

    /**
     * Retrieves representation of an instance of
     * info.mikethomas.fahservices.service.SlotInfoResource.
     *
     * @return an instance of java.lang.String
     * @throws info.mikethomas.jfold.exceptions.SlotInfoException if any.
     */
    @Operation(summary = "slot-info", description = "Get List of slot information.", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = Slot.class)))
            })
    })
    @RequestMapping(
            value = "/slot-info",
            method = RequestMethod.GET,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE,
                MediaType.TEXT_XML_VALUE
            })
    @ResponseBody
    public ResponseEntity<List<Slot>> getSlotInfo() throws SlotInfoException {
        return new ResponseEntity<>(getSlotInfoList(), HttpStatus.OK);
    }

    /**
     * Retrieves representation of an instance of
     * info.mikethomas.fahservices.service.SlotInfoResource.
     *
     * @param slot Slot number
     * @return an instance of java.lang.String
     * @throws info.mikethomas.jfold.exceptions.SlotInfoException if any.
     */
    @Operation(summary = "slot-info {slot}", description = "Get slot information at specified index.", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(schema = @Schema(implementation = Slot.class))
            })
    })
    @RequestMapping(
            value = "/slot-info/{slot}",
            method = RequestMethod.GET,
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE,
                MediaType.TEXT_XML_VALUE
            })
    @ResponseBody
    public ResponseEntity<Slot> getSlotInfo(
            @Parameter(description = "slot number", required = true)
            @PathVariable("slot") final int slot) throws SlotInfoException {
        return new ResponseEntity<>(getSlotInfoList().get(slot), HttpStatus.OK);
    }

    /**
     * <p>handleException.</p>
     *
     * @param ex a {@link java.lang.Exception} object
     * @return a {@link org.springframework.http.ResponseEntity} object
     */
    @ExceptionHandler(SlotInfoException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Exception> handleException(Exception ex) {
        return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * <p>getSlotInfoList.</p>
     *
     * @return a {@link java.util.List} object
     * @throws info.mikethomas.jfold.exceptions.SlotInfoException if any.
     */
    private List<Slot> getSlotInfoList() throws SlotInfoException {
        return connection.slotInfo();
    }
}
