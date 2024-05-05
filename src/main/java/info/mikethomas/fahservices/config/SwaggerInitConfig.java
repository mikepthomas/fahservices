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

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * <p>
 * SwaggerInitConfig class.</p>
 *
 * @author Michael Thomas (mikepthomas@outlook.com)
 * @version $Id: $Id
 */
@Configuration
public class SwaggerInitConfig {

    /**
     * <p>apiInfo.</p>
     *
     * @return a {@link io.swagger.v3.oas.models.OpenAPI} object
     */
    @Bean
    public OpenAPI apiInfo() {
        var devServer = new Server()
                .url("http://localhost:8080")
                .description("Development Server URL");

        var contact = new Contact()
                .name("Mike Thomas")
                .url("http://mikethomas.info")
                .email("mikepthomas@outlook.com");

        var license = new License()
                .name("The GNU General Public License, Version 3")
                .url("http://www.gnu.org/copyleft/gpl.html");

        var info = new Info()
                .title("FAHServices")
                .description("Web services to connect to a folding@home v7 client")
                .version("1.2")
                .contact(contact)
                .license(license);

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}
