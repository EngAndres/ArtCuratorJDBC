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

import repositories.ArtPiece;

import java.sql.*;


/**
 * This is the main class of the Project. This one is used to call functions on any repository.
 * @author casierrav
 */
public class ArtCurator {

    
    private ArtPiece artPieces; //create access to ArtPieces repository.
    
    
    /**
     * Constructor of the class 
     */
    public ArtCurator(){
        this.artPieces = new ArtPiece();
    }
    
    
    /**
     * This method is used check if MySQL credentials are right.
     * @return 
     */
    public String testConnection(){
        try {
            Connection conn = ConnectionJDBC.getConnection();
            if (conn != null) {
                return "Conectado";
            }
            conn.close();
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
            return "Falla conexion";
        }
        return "Error en la conexion";
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArtCurator artCurator = new ArtCurator();
        System.out.println(artCurator.testConnection());
        
        // As follows some examples of the invocation of different functions of ArtPiece repository
        System.out.println( artCurator.artPieces.searchAllPieces() );
        System.out.println( artCurator.artPieces.insertPiece(404, "Programando en Java", "pintura", 200, 1006) );
        System.out.println( artCurator.artPieces.searchAllPieces() );
        System.out.println( artCurator.artPieces.updatePiece(404, 250) );
        System.out.println( artCurator.artPieces.searchAllPieces() );
        System.out.println( artCurator.artPieces.deletePiece(404) );
        System.out.println( artCurator.artPieces.searchAllPieces() );
        System.out.println( artCurator.artPieces.searchPieceByName("Planos") );
        System.out.println( artCurator.artPieces.searchPieceByExposition(1006) );
    }
}
