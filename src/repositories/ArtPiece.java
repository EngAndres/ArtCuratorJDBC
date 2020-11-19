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

package repositories;

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
public class ArtPiece {
    
    private Connection conn;
    
    /**
     * Constructor of the class
     */
    public ArtPiece(){
        this.conn = ConnectionJDBC.getConnection();
    }
    
    
    /**
     * This method is used to insert a new art piece into the repository.
     * @param id
     * @param name
     * @param type
     * @param cost
     * @param exp_id
     * @return transaction status
     */
    public String insertPiece(int id, String name, String type, int cost, int exp_id){
        try {
            String sql = "INSERT INTO obra(obr_id, obr_nombre, obr_tipo, obr_costo, exp_id)"
                        + " VALUES (?, ?, ?, ?, ?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, type);
            statement.setInt(4, cost);
            statement.setInt(5, exp_id);
            
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
     * This method is used to get all the art pieces saved in the repository.
     * @return  transaction status 
     */
    public String searchAllPieces(){
        try {
            String sql = "SELECT obra.obr_id, obra.obr_nombre, obra.obr_tipo, "
                    + "obra.obr_costo, exposicion.exp_nombre FROM obra JOIN exposicion "
                    + "WHERE obra.exp_id = exposicion.exp_id;";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            int count = 0;
        
            while (result.next()){
                int obra_id = result.getInt(1);
                String obra_nombre = result.getString(2);
                String obra_tipo = result.getString(3);
                int obra_costo = result.getInt(4);
                String exposicion_nombre = result.getString(5);
                
                System.out.println("ID: " + obra_id + "\tNombre: " + obra_nombre + "\tTipo: "+ obra_tipo + 
                                    "\tCosto: "+ obra_costo + "\tExposicion: "+ exposicion_nombre);
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
     * This method is used to get an art piece from the repository based on its name.
     * @param name
     * @return transaction status
     */
    public String searchPieceByName(String name){
        try {
            String sql = "SELECT obra.obr_id, obra.obr_nombre, obra.obr_tipo, "
                    + "obra.obr_costo, exposicion.exp_nombre FROM obra JOIN exposicion"
                    + " WHERE obra.exp_id = exposicion.exp_id AND obra.obr_nombre=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            int count = 0;
        
            while (result.next()){
                int obra_id = result.getInt(1);
                String obra_nombre = result.getString(2);
                String obra_tipo = result.getString(3);
                int obra_costo = result.getInt(4);
                String exposicion_nombre = result.getString(5);
                
                System.out.println("ID: " + obra_id + "\tNombre: " + obra_nombre + "\tTipo: "+ obra_tipo + 
                                    "\tCosto: "+ obra_costo + "\tExposicion: "+ exposicion_nombre);
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
     * This method is used to get an art piece from the repository filtered by exposition.
     * @param exp_id
     * @return  transaction status
     */
    public String searchPieceByExposition(int exp_id){
        try {
            String sql = "SELECT obra.obr_id, obra.obr_nombre, obra.obr_tipo, obra.obr_costo, exposicion.exp_nombre "
                    + "FROM obra JOIN exposicion WHERE obra.exp_id = exposicion.exp_id AND obra.exp_id=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, exp_id);
            ResultSet result = statement.executeQuery();
            int count = 0;
        
            while (result.next()){
                int obra_id = result.getInt(1);
                String obra_nombre = result.getString(2);
                String obra_tipo = result.getString(3);
                int obra_costo = result.getInt(4);
                String exposicion_nombre = result.getString(5);
                
                System.out.println("ID: " + obra_id + "\tNombre: " + obra_nombre + "\tTipo: "+ obra_tipo + 
                                    "\tCosto: "+ obra_costo + "\tExposicion: "+ exposicion_nombre);
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
     * This method is used to the cost of an art piece in the repository.
     * @param piece_id
     * @param cost
     * @return  transaction status
     */
    public String updatePiece(int piece_id, int cost){
        try {
            String sql = "UPDATE obra SET obr_costo = ? WHERE obr_id = ?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(2, piece_id);
            statement.setInt(1, cost);
            
            
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
     * This method is used to remove an art piece of the repository.
     * @param piece_id
     * @return transaction status
     */
    public String deletePiece(int piece_id){
        try {
            String sql = "DELETE FROM obra WHERE obr_id = ?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, piece_id);
            
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
