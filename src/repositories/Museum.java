package repositories;

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

import artcurator.ConnectionJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author casierrav
 */
public class Museum {
    
    private Connection conn;
    
    public Museum(){
        this.conn = ConnectionJDBC.getConnection();
    }
    
    /**
     * 
     * @param id
     * @param name
     * @return 
     */
    public String insertMuseum(int id, String name){
        try {
            String sql = "INSERT INTO museo(mus_id, mus_nombre) VALUES (?, ?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, name);
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return "Inserción exitosa!\n";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Insercion fallida!\n";
        }
        return "Error en Insercion\n";
    }
    
    
    /**
     * 
     * @return 
     */
    public String searchAllMuseums(){
        try {
            String sql = "SELECT museo.mus_id, museo.mus_nombre FROM museo;";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            int count = 0;
        
            while (result.next()){
                int museo_id = result.getInt(1);
                String museo_nombre = result.getString(2);
                
                System.out.println("ID: " + museo_id + "\tNombre: " + museo_nombre);
                count += 1;
            }
            System.out.println("Museos obtenidos: " + count);
            return "Busqueda exitosa!\n";
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Busqueda fallida!\n";
        }
    }
    
    /**
     * 
     * @param name
     * @return 
     */
    public String searchMuseumByName(String name){
        try {
            String sql = "SELECT museo.mus_id, museo.mus_nombre FROM museo WHERE museo.mus_nombre=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,name);
            ResultSet result = statement.executeQuery(sql);
            int count = 0;
        
            while (result.next()){
                int museo_id = result.getInt(1);
                String museo_nombre = result.getString(2);
                
                System.out.println("ID: " + museo_id + "\tNombre: " + museo_nombre);
                count += 1;
            }
            System.out.println("Obras obtenidas: " + count);
            return "Busqueda exitosa!\n";
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Busqueda fallida!\n";
        }
    }
    
    
    /**
     * 
     * @param museum_id
     * @param name
     * @return 
     */
    public String updateMuseum(int museum_id, String name){
        try {
            String sql = "UPDATE museo SET mus_nombre = ? WHERE mus_id = ?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(2, museum_id);
            statement.setString(1, name);
            
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                return "Actualizacion exitosa!\n";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Actualizacion fallida!\n";
        }
        return "Error en Actualizacion\n";
    }
    
    
    /**
     * 
     * @param museum_id
     * @return 
     */
    public String deleteMuseum(int museum_id){
        try {
            String sql = "DELETE FROM museo WHERE mus_id = ?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, museum_id);
            
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                return "Eliminacion exitosa!\n";
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Eliminacion fallida!\n";
        }
        return "Error en Eliminacion\n";
    }
}