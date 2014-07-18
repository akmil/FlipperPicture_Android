//<editor-fold defaultstate="collapsed" desc=" www.CodeFire.com.ua ">
/*
 * This program is a part of the CodeFire Education Center.
 * (http://www.codefire.com.ua/)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 */
//</editor-fold>

package ua.com.codefire.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CodeFire http://www.codefire.com.ua/
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        // TODO code application logic here

        //<editor-fold defaultstate="collapsed" desc="Information">
        /* Java Database Connectivity (Execute SELECT Query)
         * 1. Make SELECT query
         * 2. Invoke method statement.executeQuery(query); with query string
         * 2.1 query - string object with SELECT query like this
         * "SELECT * FROM table_name" where table_name is name of table
         * 3. Retrive result of query
         */
        //</editor-fold>
        // Load driver class to memory
        // (!MAY THROW ClassNotFoundException!)
        Class.forName(com.mysql.jdbc.Driver.class.getName());
        
        try {
            // Create connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost", "root", "1234567890");
            
            Statement statement = connection.createStatement();
            ResultSet executeQuery = statement.executeQuery("SELECT * FROM `world`.`city`");
            
            // Get meta data of table result
            if (executeQuery == null){
                System.out.println("ResultSet is null!!!");
                System.exit(0);
//                throw new SQLException("ResultSet is null!!!");
            }
            
            ResultSetMetaData metaData = executeQuery.getMetaData();
            
            // Info about object "ResultSetMetaData"
            System.out.println(metaData);
            
            System.out.println("Columns: " + metaData.getColumnCount());
            // Get columns information
            for (int i = 1; i < metaData.getColumnCount(); i++) {
                // Column start from "[1] index NOT [0]"
                String columnName = metaData.getColumnName(i);
                String columnTypeName = metaData.getColumnTypeName(i);
                int columnTypeSize = metaData.getColumnDisplaySize(i);
                
                System.out.printf("%s, [%s(%d)] \n", columnName, columnTypeName, columnTypeSize);
            }
            
            System.out.println();
            
            // next() - move pointer to next row if exist
            while (executeQuery.next()) {
                int id = executeQuery.getInt("id"); // Get INTEGER cell value of current row
                String name = executeQuery.getString("name");
                String countryCode = executeQuery.getString(3); // Cell value, may get by number of cell
                
                System.out.printf("#%d \t%s \t[%s]\n", id, name, countryCode);
            }
            
            // Statement auto-close by "connection.close();"
//            statement.close();
            // Close connection for release resources
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
