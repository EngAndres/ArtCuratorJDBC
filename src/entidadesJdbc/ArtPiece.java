/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesJdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos A. Sierra
 */
public class ArtPiece {
    
    private Connection conn;
    
    private String dbURL = "jdbc:mysql://localhost:3306/museo?serverTimezone=UTC";
    private String username = "root";
    private String password = "@mysql1987";
    
    public ArtPiece(){
        try {
            this.conn = DriverManager.getConnection(dbURL,username, password);
        } catch (SQLException ex) {
            Logger.getLogger(ArtPiece.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     * @param id
     * @param name
     * @param type
     * @param cost
     * @param exp_id
     * @return 
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
     * 
     * @return 
     */
    public String searchAllPieces(){
        try {
            String sql = "SELECT obra.obr_id, obra.obr_nombre, obra.obr_tipo, "
                    + "obra.obr_costo, exposicion.exp_nombre FROM obra JOIN exposicion WHERE obra.exp_id = exposicion.exp_id;";
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
     * 
     * @param name
     * @return 
     */
    public String searchPieceByName(String name){
        try {
            String sql = "SELECT obra.obr_id, obra.obr_nombre, obra.obr_tipo, "
                    + "obra.obr_costo, exposicion.exp_nombre FROM obra JOIN exposicion"
                    + " WHERE obra.exp_id = exposicion.exp_id AND obra.obr_nombre=?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,name);
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
     * 
     * @return 
     */
    public String searchPieceByExposition(){
        try {
            String sql = "SELECT obra.obr_id, obra.obr_nombre, obra.obr_tipo, obra.obr_costo, exposicion.exp_nombre FROM obra JOIN exposicion WHERE obra.exp_id = exposicion.exp_id;";
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
     * 
     * @param piece_id
     * @param cost
     * @return 
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
     * 
     * @param piece_id
     * @return 
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
