import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtils {
    private static final String URL = "jdbc:sqlite:test.db";
    private static Connection c;

    public static void openConnection() {
        try {
            // Ensure the driver class is loaded
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("SQLite JDBC driver not found", e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection() {
        try {
            if (c != null) {
                c.close();
                System.out.println("Connection to SQLite has been closed.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createTable() {
  
        try {
            String sql = "CREATE TABLE IF NOT EXISTS TruckExpense ("
                    + "truck_id INT NOT NULL, "
                    + "insurance_company TEXT NOT NULL, "
                    + "distance REAL NOT NULL, "
                    + "fuel_consumption REAL NOT NULL, "
                    + "drivers_wage REAL NOT NULL, "
                    + "fuel_cost REAL NOT NULL, "
                    + "maintenance_cost REAL NOT NULL, "
                    + "insurance_cost REAL NOT NULL, "
                    + "PRIMARY KEY (truck_id)"
                    + ");";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.execute();
            System.out.println("Table has been created or already exists.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void insertExpense(TruckExpense expense) {
    	
        String sql = "INSERT OR IGNORE INTO TruckExpense("
        		+ "truck_id,"
        		+ "insurance_company,"
        		+ "distance,"
        		+ "fuel_consumption,"
        		+ "drivers_wage,"
        		+ "fuel_cost,"
        		+ "maintenance_cost,"
        		+ "insurance_cost) VALUES(?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, expense.getTruck_id());
            pstmt.setString(2, expense.getInsurance_company());
            pstmt.setDouble(3, expense.getDistance());
            pstmt.setDouble(4, expense.getFuel_consumption());
            pstmt.setDouble(5, expense.getDrivers_wage());
            pstmt.setDouble(6, expense.getPrices().getFuel_cost());
            pstmt.setDouble(7, expense.getPrices().getMaintenance_cost());
            pstmt.setDouble(8, expense.getPrices().getInsurance_cost());
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<TruckExpense> selectAllExpenses() throws SQLException {
        String sql = "SELECT * FROM TruckExpense";
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery(sql);
        List<TruckExpense> expenses = new ArrayList<>();

        while (rs.next()) {
            TruckExpense expense = new TruckExpense(
            		rs.getInt("truck_id"),
                    rs.getString("insurance_company"),
                    rs.getDouble("distance"),
                    rs.getDouble("fuel_consumption"),
                    rs.getDouble("drivers_wage"),
                    new Prices(
                    rs.getDouble("fuel_cost"),
                    rs.getDouble("maintenance_cost"),
                    rs.getDouble("insurance_cost"))
            );
            expenses.add(expense);
        }
        return expenses;
    }
}
