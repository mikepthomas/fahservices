package info.mikethomas.fahservices.config;

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

import io.swagger.jaxrs.config.BeanConfig;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * <p>SwaggerInitConfig class.</p>
 *
 * @author Michael Thomas (mikepthomas@outlook.com)
 * @version $Id: $Id
 */
@WebServlet(name = "SwaggerInitConfig", loadOnStartup = 1)
public class SwaggerInitConfig extends HttpServlet {

    /** {@inheritDoc} */
    @Override
    public void init(ServletConfig servletConfig) {
        try {
            super.init(servletConfig);

            BeanConfig beanConfig = new BeanConfig();
            beanConfig.setVersion("1.2");

            beanConfig.setTitle("FAHServices");
            beanConfig.setDescription("Web services to connect to a folding@home v7 client");
            beanConfig.setTermsOfServiceUrl("http://mikethomas.info");
            beanConfig.setContact("mikepthomas@outlook.com");
            beanConfig.setLicense("The GNU General Public License, Version 3");
            beanConfig.setLicenseUrl("http://www.gnu.org/copyleft/gpl.html");

            beanConfig.setSchemes(new String[]{"http"});
            beanConfig.setHost("localhost:8080");
            beanConfig.setBasePath("/fahservices/rest");
            beanConfig.setResourcePackage("info.mikethomas.fahservices.service");

            beanConfig.setScan(true);
        } catch (ServletException e) {
            System.out.println(e.getMessage());
        }
    }
}
