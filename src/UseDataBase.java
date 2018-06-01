import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

class UseDataBase {

    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false";
    private static final String user = "root";
    private static final String password = "270489";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;

    UseDataBase(List <Employee> list){

        try {
            // opening database connection to MySQL server
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();
            createTable();
            fillTable(list);

        } catch (SQLException | ClassNotFoundException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try { con.close(); } catch(SQLException se) {se.printStackTrace();}
            try { stmt.close(); } catch(SQLException se) {se.printStackTrace();}
        }
    }

    private void createTable() throws SQLException {
        PreparedStatement create;
        try {
            create = con.prepareStatement("CREATE TABLE mydb.employees (id INT NOT NULL, lastname VARCHAR(45) NOT NULL, " +
                    "firstname VARCHAR(45) NOT NULL, patronymic VARCHAR(45) NOT NULL, birthday DATE NOT NULL, " +
                    "startWork DATE NOT NULL, type VARCHAR(45) NOT NULL, PRIMARY KEY (id))");
            create.executeUpdate();
            create = con.prepareStatement("CREATE TABLE mydb.managerid (workerid INT NOT NULL, managerid INT NOT NULL, PRIMARY KEY (workerid))");
            create.executeUpdate();
            create = con.prepareStatement("CREATE TABLE mydb.description (otherworkersid INT NOT NULL, description VARCHAR(45) NOT NULL, PRIMARY KEY (otherworkersid))");
            create.executeUpdate();
            System.out.println("Таблицы созданы!");
        }
        catch (SQLException e){e.printStackTrace();}
    }

    void fillTable(List <Employee> list) {
        try {
            PreparedStatement fill;
            for (Employee e: list){
                fill = con.prepareStatement("INSERT INTO mydb.employees (id, lastname, firstname, patronymic, birthday, startWork, type) " +
                        "VALUES ("+String.valueOf(e.getId())+", '"+e.getLastName()+"', '"+e.getFirstName()+"', '"+e.getPatronymic()+"', '"
                        +transformDate(e.getBirthday())+"', '"+transformDate(e.getStartWork())+"', '"+e.getType()+"')");
                fill.executeUpdate();
                if (e instanceof Worker){
                    fill = con.prepareStatement("INSERT INTO mydb.managerid (workerid, managerid) " +
                            "VALUES ("+String.valueOf(e.getId())+", "+((Worker) e).getManagerId()+")");
                    fill.executeUpdate();
                }
                if (e instanceof OtherWorker){
                    fill = con.prepareStatement("INSERT INTO mydb.managerid (otherworkersid, description) " +
                            "VALUES ("+String.valueOf(e.getId())+", '"+((OtherWorker) e).getDescription()+"')");
                    fill.executeUpdate();
                }}
                System.out.println("Таблицы заполнены!");
            }
            catch (SQLException e1) {e1.printStackTrace();}
    }

    private String transformDate(java.util.Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        return dateFormat.format(date);
    }
}
