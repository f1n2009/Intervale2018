import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

class UseDataBase {

    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false";
    private static final String user = "root";
    private static final String password = "root";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;

    UseDataBase(List <Employee> list){

        try {
            // opening database connection to SQL server
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
                    "startwork DATE NOT NULL, type VARCHAR(45) NOT NULL, PRIMARY KEY (id))");
            create.executeUpdate();
            create = con.prepareStatement("CREATE TABLE mydb.managerid (id INT NOT NULL, managerid INT NOT NULL, PRIMARY KEY (id))");
            create.executeUpdate();
            create = con.prepareStatement("CREATE TABLE mydb.description (id INT NOT NULL, description VARCHAR(45) NOT NULL, PRIMARY KEY (id))");
            create.executeUpdate();
            System.out.println("Таблицы созданы!");
        }
        catch (SQLException e){e.printStackTrace();}
    }

    private void fillTable(List<Employee> list) {
        try {
            PreparedStatement fill;
            for (Employee e: list){
                fill = con.prepareStatement("INSERT INTO mydb.employees (id, lastname, firstname, patronymic, birthday, startwork, type) " +
                        "VALUES ("+String.valueOf(e.getId())+", '"+e.getLastName()+"', '"+e.getFirstName()+"', '"+e.getPatronymic()+"', '"
                        +transformDate(e.getBirthday())+"', '"+transformDate(e.getStartWork())+"', '"+e.getType()+"')");
                fill.executeUpdate();
                if (e instanceof Worker){
                    fill = con.prepareStatement("INSERT INTO mydb.managerid (id, managerid) " +
                            "VALUES ("+String.valueOf(e.getId())+", "+((Worker) e).getManagerId()+")");
                    fill.executeUpdate();
                }
                if (e instanceof OtherWorker){
                    fill = con.prepareStatement("INSERT INTO mydb.description (id, description) " +
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

    private Date transformDate(String date) throws ParseException {
        return new SimpleDateFormat("yyyy-mm-dd").parse(date);
    }

    void delWorkerFromBase(int id) {
        try {
            PreparedStatement fill;
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            fill = con.prepareStatement("DELETE FROM mydb.employees WHERE id  = "+id);
            fill.executeUpdate();
            fill = con.prepareStatement("DELETE FROM mydb.managerid WHERE id = "+id+" OR managerid = "+id);
            fill.executeUpdate();
            fill = con.prepareStatement("DELETE FROM mydb.description WHERE id  = "+id);
            fill.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();}
        finally {
            try { con.close(); } catch(SQLException se) {se.printStackTrace();}}
    }

    List<Employee> saveFromBase() {
            List <Employee> newWorkers = new ArrayList<>();
            PreparedStatement fill;
            int currId = 0;
            int managerId = 0;
            String description = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            String sql = "SELECT id, lastname, firstname, patronymic, birthday, startwork, type FROM mydb.employees";
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while(rs.next()){
                String type = rs.getString("type");
                currId= rs.getInt("id");
                Statement stmt1 = con.createStatement();
                switch (type){
                    case "работник":
                        try {
                            String sql1 = ("SELECT managerid FROM mydb.managerid WHERE id="+String.valueOf(currId));
                            ResultSet rs1 = stmt1.executeQuery(sql1);
                            while(rs1.next()){
                            managerId = rs1.getInt("managerid");}
                            newWorkers.add(new Worker(currId, rs.getString("lastname"),
                                    rs.getString("firstname"), rs.getString("patronymic"),
                                    transformDate(rs.getString("birthday")),
                                    transformDate(rs.getString("startwork")), managerId));
                        } catch (ParseException e) {
                            e.printStackTrace();}
                        finally {
                            try { stmt1.close(); } catch(SQLException se) {se.printStackTrace();}
                        }
                        break;
                    case "менеджер":
                        try {
                            String sql2 = ("SELECT id FROM mydb.managerid WHERE managerid="+String.valueOf(currId));
                            ResultSet rs2 = stmt1.executeQuery(sql2);
                            List <Integer> workersId = new ArrayList<>();
                            while(rs2.next()){
                                workersId.add(rs2.getInt("id"));}
                            newWorkers.add(new Manager(currId, rs.getString("lastname"),
                                    rs.getString("firstname"), rs.getString("patronymic"),
                                    transformDate(rs.getString("birthday")),
                                    transformDate(rs.getString("startwork")), workersId));
                        } catch (ParseException e) {
                            e.printStackTrace();}
                        finally {
                            try {
                                stmt1.close(); } catch(SQLException se) {se.printStackTrace();}}
                            break;
                    case "другой":
                        try {
                            String sql3 = ("SELECT description FROM mydb.description WHERE id="+String.valueOf(currId));
                            ResultSet rs3 = stmt1.executeQuery(sql3);
                            while(rs3.next()){
                                description = rs3.getString("description");}
                            newWorkers.add(new OtherWorker(currId, rs.getString("lastname"),
                                    rs.getString("firstname"), rs.getString("patronymic"),
                                    transformDate(rs.getString("birthday")),
                                    transformDate(rs.getString("startwork")), description));
                        } catch (ParseException e) {
                            e.printStackTrace();}
                        finally {
                            try {
                                stmt1.close(); } catch(SQLException se) {se.printStackTrace();}}
                        break;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();}
        finally {
            try { con.close(); } catch(SQLException se) {se.printStackTrace();}
            try { stmt.close(); } catch(SQLException se) {se.printStackTrace();}
            }
        return newWorkers;
    }

    void sortDataBaseByLastName() {
        try {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url, user, password);
        PreparedStatement fill;
            fill = con.prepareStatement("SELECT * FROM mydb.employees ORDER BY lastname");
            fill.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try { con.close(); } catch(SQLException se) {se.printStackTrace();}
        }
    }

    void sortDataBaseByDate() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            PreparedStatement fill;
            fill = con.prepareStatement("SELECT * FROM mydb.employees ORDER BY startwork");
            fill.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try { con.close(); } catch(SQLException se) {se.printStackTrace();}
        }
    }
}
