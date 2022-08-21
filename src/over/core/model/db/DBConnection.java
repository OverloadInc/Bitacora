package over.core.model.db;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <code>DBConnection</code> class encapsulates the basic operations over a Database by using the
 * <code>postgreSQL</code> driver.
 * @author Overload Inc.
 * @version 1.0, 07 Aug 2022
 */
public final class DBConnection {
    private String host;
    private String user;
    private String password;
    private Connection connection;
    private static DBConnection dbConnection;

    /**
     * Class constructor to initialize the host, user and password to connect the Database.
     */
    private DBConnection() {
        host = "jdbc:postgresql://localhost:5432/dbBitacora";
        user = "overload_inc";
        password = "admin";
    }

    /**
     * Connects to the Database using a predefined host, user and password.
     * @return the <code>Connection</code> object with the connection to the Database.
     * @throws SQLException
     */
    public Connection connect() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(host, user, password);

            if (connection != null)
                System.out.println("Connected to database");
            else
                System.out.println("Connection failed");
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return connection;
    }

    /**
     * Closes the current connection to the Database used by the program.
     */
    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();

                System.out.println("Database disconnected");
            }
        }
        catch (SQLException e) {
        }
    }

    /**
     * Closes all the connections to the Database in case of any is still alive.
     */
    public void finalize() {
        try {
            connection.close();

            connection = null;
        }
        catch(SQLException e) {
        }
    }

    /**
     * Executes <code>INSERT</code>, <code>DELETE</code> and <code>UPDATE</code> commands to the Database.
     * @param command the SQL command to execute.
     * @return <code>true</code> if the command is executed correctly; <code>false</code> otherwise.
     * @throws Exception
     */
    public synchronized boolean executeCommand(String command) throws Exception {
        boolean result;
        Statement statement = null;

        try {
            statement = connection.createStatement();

            result = statement.execute(command);
        }
        catch(SQLException e) {
            connection.rollback();

            throw e;
        }
        finally {
            statement.close();
        }

        return result;
    }

    /**
     * Executes a SQL query to get information from the Database.
     * @param query the SQL query to execute.
     * @return a <code>ResultSet</code> with the required information .
     * @throws Exception
     */
    public synchronized ResultSet executeQuery(String query) throws Exception {
        ResultSet resultSet;

        try {
            resultSet = connection.createStatement().executeQuery(query);
        }
        catch(SQLException e) {
            connection.rollback();

            throw e;
        }

        return resultSet;
    }

    /**
     * Gets an instance to use the DBConnection class' methods.
     * @return the <code>DBConnection</code> instance.
     */
    public static DBConnection getInstance() {
        if(dbConnection == null)
            dbConnection = new DBConnection();

        return dbConnection;
    }
}