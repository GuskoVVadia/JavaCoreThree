package HW;

import java.sql.*;
import java.util.ArrayList;

public class RequestDataBase {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void createTable(String nameTable, String... n) throws SQLException {

        String requestLink = " ";
        for (int i = 0; i < n.length; i++) {
            requestLink = requestLink + ", " + n[i] + " TEXT NOT NULL";
        }

        statement.executeUpdate(String.format("CREATE TABLE %s ( " +
                " id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL %s)", nameTable, requestLink));
    }


    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/J11Temp2210.db");
        statement = connection.createStatement();

    }

    public static void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteTable(String nameTable) {
        try {
            String sql = String.format("drop table if exists %s", nameTable);
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println("Удаление не удалось.");
        }
    }

    public static void readTable(String nameTable, String nameColumn) throws SQLException {
        String sql = String.format("select %s from %s", nameColumn, nameTable);
        resultSet = statement.executeQuery(sql);
        if (resultSet.next()){
            System.out.println(resultSet.getInt(nameTable));
        }
    }
    public static void insertTableLine(String nameTable, String ... n) throws Exception {
        resultSet = statement.executeQuery(String.format("select * from %s", nameTable));
        ResultSetMetaData rsmd = resultSet.getMetaData();
        if (n.length > rsmd.getColumnCount()) throw new Exception("Неверное количество вводимых данных.");
        String nameColumn = "";
        for (int j = 2; j <= rsmd.getColumnCount(); j++) {
            if (j > 2) nameColumn += ", ";
            nameColumn += rsmd.getColumnName(j);
        }
        String valueColumn = "";
        for (int i = 0; i < n.length; i++) {
            if (i > 0) valueColumn +=" ,";
            valueColumn += "'" + n[i] + "'";
        }

        statement.executeUpdate(String.format("insert into %s ( %s ) values (%s)", nameTable, nameColumn, valueColumn));
    }

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        String nameTable = "students";
        try {
            connect();
            createTable(nameTable, "student", "score");
            deleteTable(nameTable);
            readTable(nameTable, "*");
            insertTableLine(nameTable, "dswfs", "20");


        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            disconnect();
        }
        System.out.println(System.currentTimeMillis() - time + " ms.");
    }
}