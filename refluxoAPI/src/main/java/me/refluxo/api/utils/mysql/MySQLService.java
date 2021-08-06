package me.refluxo.api.utils.mysql;

import me.refluxo.api.utils.server.Console;
import me.refluxo.api.utils.server.ConsoleClassType;

import java.sql.*;

public class MySQLService {
    public static Connection con;
    public static void connect(String host, String user, String database, String password, String port){
        if(isConnected()){
            new Console("MySQL ist bereits verbunden.", ConsoleClassType.MySQL);
            return;
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?user=" + user + "&password=" + password + "&autoReconnect=true");
        } catch (SQLException throwables) {
            new Console(throwables.getMessage() + "\n\n\n"+throwables.getStackTrace(), ConsoleClassType.MySQL);
        }
    }

    public static void setMaxConnections() {
        try {
            PreparedStatement st = con.prepareStatement("SET GLOBAL MAX_CONNECTIONS = 500");
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect(){
        if(isConnected()){
            try {
                con.close();
            } catch (SQLException throwables) {
                new Console(throwables.getMessage(), ConsoleClassType.MySQL);
            }
        } else {
            new Console("MySQL ist nicht verbunden.", ConsoleClassType.MySQL);
        }
    }

    public static boolean isConnected(){
        return (con ==null ? false : true);
    }

    public MySQLService() {}

    private PreparedStatement ps;

    public Connection getConnection() {
        return con;
    }

    public ResultSet getResult(PreparedStatement sql) {
        try {
            return sql.executeQuery();
        } catch (SQLException throwables) {
            new Console(throwables.getMessage(), ConsoleClassType.MySQL);
        }
        return null;
    }

    public void executeUpdate(PreparedStatement sql) {
        try {
            sql.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
