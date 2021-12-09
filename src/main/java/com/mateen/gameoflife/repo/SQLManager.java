package com.mateen.gameoflife.repo;

import com.mateen.gameoflife.constant.SQLConstants;
import com.mateen.gameoflife.model.GameState;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLManager {

    private SQLManager sqlManager = new SQLManager();
    private Connection sqlConnection;

    private SQLManager() {
        SQLConnector sqlConnector = new SQLConnector();
        sqlConnector.connect(SQLConstants.SQL_HOST_URL, SQLConstants.SQL_HOST_PORT, SQLConstants.SQL_DB_NAME, SQLConstants.SQL_DB_USERNAME, SQLConstants.SQL_DB_PASSWORD);
        sqlConnection = sqlConnector.getConnection();

    }

    public SQLManager getSqlManager() {
        return sqlManager;
    }

    public boolean uploadState() {
        try {

            Statement statement = sqlConnection.createStatement();
            statement.execute("INSERT INTO State VALUES (id,score, magnify, speed)");
            statement.close();

            return true;

        } catch (Exception e) {
            return false;
        }


    }

    public GameState downloadState() {
        try {
            Statement statement = sqlConnection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM State");

            GameState gameState = new GameState();

            while(rs.next()) {
                gameState.setScore(rs.getInt("score"));
                gameState.setMagnification(rs.getInt("magnify"));
                gameState.setSpeed(rs.getFloat("speed"));
            }

            return gameState;

        } catch (Exception e) {

            return null;
        }


    }
}
