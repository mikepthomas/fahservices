/*
 * Copyright (C) 2019 Mike Thomas
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package info.mikethomas.fahservices.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import info.mikethomas.jfold.Connection;
import info.mikethomas.jfold.exceptions.QueueInfoException;
import info.mikethomas.jfold.unit.Unit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mike
 */
@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private Connection connection;

    public List<Unit> queueInfo() throws QueueInfoException {
        return connection.queueInfo();
    }
}
