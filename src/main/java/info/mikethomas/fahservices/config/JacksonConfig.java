package info.mikethomas.fahservices.config;

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

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jakarta.xmlbind.JakartaXmlBindAnnotationModule;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>JacksonConfig class.</p>
 *
 * @author Michael Thomas (mikepthomas@outlook.com)
 * @version $Id: $Id
 */
@Configuration
@Slf4j
public class JacksonConfig {

    /**
     * <p>objectMapper.</p>
     *
     * @return a {@link com.fasterxml.jackson.databind.ObjectMapper} object
     */
    @Bean
    public ObjectMapper objectMapper() {
        var mapper = new ObjectMapper();

        // Enable Jaxb Annotation Introspector
        mapper.registerModule(new JakartaXmlBindAnnotationModule());

        // Set Serialization Inclusion
        mapper.setSerializationInclusion(Include.NON_NULL);
        log.info("ObjectMapper SerializationInclusion: {}", Include.NON_NULL);

        return mapper;
    }
}
