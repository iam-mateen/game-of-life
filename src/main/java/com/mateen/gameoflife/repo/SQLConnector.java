package com.mateen.gameoflife.repo;

import com.mateen.gameoflife.constant.SQLConstants;
import com.mateen.gameoflife.util.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnector {

    private Connection connection;

    SQLConnector() {
        this.connection = null;
    }

    public boolean connect(String url, int port, String databaseName, String username, String password) {
        try {
            connection = DriverManager.getConnection(Utils.getSqlURL(url, port, databaseName), username, password);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
