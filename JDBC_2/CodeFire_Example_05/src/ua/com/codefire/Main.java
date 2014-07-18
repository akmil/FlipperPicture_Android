/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.codefire;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Man
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            // TODO code application logic here
            
            String url = "jdbc:mysql://localhost";
            String name = "root";
            String password = "1234567890";
            Connection connection = null;
            
            try {
    //        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            
    //        Class<com.mysql.jdbc.Driver> dc = com.mysql.jdbc.Driver.class;
    //        Class.forName(dc.getName());
            
                Class.forName(com.mysql.jdbc.Driver.class.getName());
                System.out.println("Драйвер подключен");
                
                connection = DriverManager.getConnection(url, name, password);
                System.out.println("Соединение установлено");
                
                //Для использования SQL запросов существуют 3 типа объектов:
                //1.Statement: используется для простых запросов без параметров
                
                Statement statement = connection.createStatement();
                //Выполним запрос
                ResultSet resultSet = statement.executeQuery("SELECT * FROM world.city where id >2 and id <10");
                
                System.out.println("Выводим statement");
                
                while (resultSet.next()) {
                    System.out.println("Номер: " + resultSet.getRow()
                            + "\t Name: " + resultSet.getString("Name")
                            + "\t Cc: " + resultSet.getString("CountryCode"));
                }
                //Вставим запись
                int executeUpdate = statement.executeUpdate("INSERT INTO world.city(name) values('name')");
                //Обновим запись
                int executeUpdate1 = statement.executeUpdate("UPDATE world.city SET name = 'NewName' where id = 2");
                
                //2.PreparedStatement:
                // выполняет параметризованные запросы, которые могут содержать входные параметры
                PreparedStatement preparedStatement = null;
                // ? - место вставки нашего значения
                preparedStatement = connection.prepareStatement("SELECT * FROM world.city where id > ? and id < ?");
                //Устанавливаем в нужную позицию значения определeнного типа
                preparedStatement.setInt(1, 2);
                preparedStatement.setInt(2, 10);
                //выполняем запрос
                ResultSet result2 = preparedStatement.executeQuery();
                
                System.out.println("Выводим PreparedStatement");
                while (result2.next()) {
                    System.out.println("Номер: " + result2.getRow()
                            + "\t Name: " + result2.getString("Name")
                            + "\t Cc: " + result2.getString("CountryCode"));
                }
                
                preparedStatement = connection.prepareStatement("INSERT INTO world.city(name) values(?)");
                preparedStatement.setString(1, "NewNameAgain");
                //метод принимает значение без параметров
                //тем же способом можно сделать и UPDATE
                preparedStatement.executeUpdate();
                
                //3.CallableStatement: используется для вызова хранимых функций,
                // которые могут содержать входные и выходные параметры
                // Вызываем функцию myFunc (хранится в БД)
                CallableStatement callableStatement = connection.prepareCall("call world.selectpopulationwhere(?,?)");
                // Задаeм входные параметры
                callableStatement.setString(1, "Dima");
                callableStatement.setString(2, "Alex");
                
                //Выполняем запрос
                ResultSet result3 = callableStatement.executeQuery();
                //Если CallableStatement возвращает несколько объектов ResultSet,
                //то нужно выводить данные в цикле с помощью метода next
                //Здесь же функция возвращает один объект
                result3.next();
                System.out.println(result3.getString("CountryCode"));
                //если функция вставляет или обновляет, то используется метод executeUpdate()
                
                
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally {
                if (connection != null)
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
    }
}
// How get and SOUT Sql "Error code:1064".
