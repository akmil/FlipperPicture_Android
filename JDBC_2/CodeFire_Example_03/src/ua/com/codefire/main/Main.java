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
         * "SELECT * FROM table_name" where table_name it's name of table
         * 3. Retrive result of query
         */
        //</editor-fold>
        // Load driver class to memory
        // (!MAY THROW ClassNotFoundException!)
        Class.forName(com.mysql.jdbc.Driver.class.getName());

        try {
            // Create connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost", "root", "1234567890");

            if (connection != null) {
                System.out.println("Connected.");
            } else {
                System.out.println("Connecting error.");
                System.exit(0);
            }

            // Create statement object for send
            Statement statement = connection.createStatement();

            int executeUpdate = statement.executeUpdate("INSERT INTO world.city(name) values('name')");
            
            // Execute create database query and take ResultSet object for retrive result table
            ResultSet result = statement.executeQuery("SELECT * FROM `world`.`city`");

            //<editor-fold defaultstate="collapsed" desc="Moving cursor in ResultSet">
            // Moves the cursor to the given row number in this ResultSet object.
//             result.absolute(2);
            //</editor-fold>

            // next() - move pointer to next row if exist
            //cursor - line 0.
            while (result.next()) { //cursor - line 1.
                int id = result.getInt("id");               // Get INTEGER cell value of current row
                // int id = result.getInt(1);
                String name = result.getString("name");
                String countryCode = result.getString(3);   // Cell value, may get by number of cell

                System.out.printf("%d %s %s\n", id, name, countryCode);
            }

            // Get information resulting table.

            int columnCount = result.getMetaData().getColumnCount();
            System.out.println("Columns: " + columnCount);


            // Statement auto-close by "connection.close();"
//            statement.close();
            // Close connection for release resources
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
