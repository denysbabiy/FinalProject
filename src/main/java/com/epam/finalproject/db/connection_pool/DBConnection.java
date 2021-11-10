package com.epam.finalproject.db.connection_pool;

/*import com.epam.finalproject.exceptions.NoConnectionToDB;


import java.sql.Connection;
import java.sql.SQLException;
//maybe need to make singleton
public class DBConnection implements AutoCloseable {
    private final Connection connection;

    public DBConnection() {
        try {
            connection = DBConnectionPool.getConnection();
        } catch (SQLException e) {
            //log
            throw new NoConnectionToDB(e.getMessage());
        }
    }

    @Override
    public void close() {
        if (connection!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                //log
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
*/