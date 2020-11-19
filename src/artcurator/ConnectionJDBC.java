/**
 Copyright (C) 2020 Carlos Andrés Sierra (casierrav)
 This file is part of ArtCuratorJDBCProject <https://github.com/EngAndres/ArtCuratorJDBC>.

 ArtCuratorJDBC is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 TheSocitosNetworkProject is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with ArtCuratorJDBCProject.  If not, see <http://www.gnu.org/licenses/>.
*/

package artcurator;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author casierrav
 */
public class ConnectionJDBC {
    
    public static Connection getConnection(){
        String dbURL = "jdbc:mysql://localhost:3306/museo?serverTimezone=UTC";
        String username = "casierrav";
        String password = "@Mysql1987"; 

        try {
            return DriverManager.getConnection(dbURL, username, password);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
