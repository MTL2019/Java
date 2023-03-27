package AdvancedJavaProgramming.DatabaseProgramming;

/**
 * relational database systems: based on the  relational data model
 *       structure : defines the representation of the data
 *       integrity :  imposes constraints on the data
 *              Domain Constraints : specify the permissible values for an attribute
 *              Primary Key Constraints
 *              Foreign Key Constraints
 *       language : provides the means for accessing and manipulating data
 *
 * JDBC : Java API for accessing relational database
 *
 *      Driver ： n loads an  appropriate driver
 *      Connection ： connects to the database
 *      Statement ： creates and executes SQL statements
 *      ResultSet ： processes the result
 *      PreparedStatement : enables you to create parameterized SQL statements
 *      CallableStatement : enables you to execute SQL stored procedures.
 *          An IN parameter receives a value passed  to the procedure when it is called
 *          An OUT parameter returns a value after the procedure is completed, but it doesn’t contain any value when the procedure is called
 *          An IN OUT parameter
 *
 *      Retrieving Metadata
 *       ResultSetMetaData: to find the types and properties of the columns in a ResultSet
 *
 *      Batch Processing:  send a batch of SQL statements to the database for execution at once to improve efficiency
 *          in Statement interface:
 *                  addBatch()
 *                  executeBatch()
 *
 *       Scrollable and Updatable Result Set : moving cursor in result set to insert, delete and update data.
 *
 *       to simplify database programming
     *       RowSet :
 *                  connected  : makes a connection with a data source and maintains that connection throughout its life cycle
 *                  disconnected : read once , but can change data and send it back to database(must reconnect)
     *       JdbcRowSet : connected, neither serializable nor cloneable
     *       CachedRowSet: disconnected, serializable and cloneable
 */
public class DatabaseNote {

    public static void main(String[] args) {


    }


}
