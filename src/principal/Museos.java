/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import entidadesJdbc.ArtPiece;
import java.sql.*;

public class Museos {

    private String dbURL = "jdbc:mysql://localhost:3306/museo?serverTimezone=UTC";
    private String username = "root";
    private String password = "@mysql1987";
        
    private ArtPiece artPieces;
    
    public Museos(){
        this.artPieces = new ArtPiece();
    }
    
    
    public String testConnection(){
        try {
            Connection conn = DriverManager.getConnection(dbURL,username, password);
            if (conn != null) {
                return "Conectado";
            }
            conn.close();
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
            return "Falla conexion";
        }
        return "Error";
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Museos museos = new Museos();
        
        museos.testConnection();
        
        System.out.println( museos.artPieces.searchAllPieces() );
        System.out.println( museos.artPieces.insertPiece(404, "Programando en Java", "pintura", 200, 1006) );
        System.out.println( museos.artPieces.searchAllPieces() );
        System.out.println( museos.artPieces.updatePiece(404, 250) );
        System.out.println( museos.artPieces.searchAllPieces() );
        System.out.println( museos.artPieces.deletePiece(404) );
        System.out.println( museos.artPieces.searchAllPieces() );
    }
}
