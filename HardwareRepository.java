import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HardwareRepository {
    private static final String DB_URL = "jdbc:sqlite:hardware.db";

    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS hardware ("
                + "id    INTEGER PRIMARY KEY, "
                + "brand TEXT    NOT NULL, "
                + "spec  INTEGER NOT NULL, "
                + "type  TEXT    NOT NULL"
                + ");";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("[DB] Table 'hardware' is ready.");

        } catch (Exception e) {
            System.out.println("[DB ERROR] createTable: " + e.getMessage());
        }
    }

    public void seedData() {
        Object[][] data = {
            {1,  "Dell XPS 13",    16, "Laptop"},
            {2,  "Samsung S24",    50, "Phone"},
            {3,  "MacBook Pro",    32, "Laptop"},
            {4,  "iPhone 15",      48, "Phone"},
            {5,  "ASUS Zenbook",   16, "Laptop"},
            {6,  "Google Pixel 8", 50, "Phone"},
            {7,  "Lenovo Legion",  32, "Laptop"},
            {8,  "Huawei P60",     48, "Phone"},
            {9,  "HP Spectre",     16, "Laptop"},
            {10, "Sony Xperia",    16, "Phone"}
        };

        String clearSql  = "DELETE FROM hardware;";
        String insertSql = "INSERT INTO hardware (id, brand, spec, type) VALUES (?, ?, ?, ?);";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement clearStmt = conn.createStatement();
             PreparedStatement pstmt = conn.prepareStatement(insertSql)) {

            clearStmt.execute(clearSql);

            for (Object[] row : data) {
                pstmt.setInt(1,    (int)    row[0]);
                pstmt.setString(2, (String) row[1]);
                pstmt.setInt(3,    (int)    row[2]);
                pstmt.setString(4, (String) row[3]);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            System.out.println("[DB] Sample data inserted successfully.");

        } catch (Exception e) {
            System.out.println("[DB ERROR] seedData: " + e.getMessage());
        }
    }

    public List<Hardware> findAll() {
        List<Hardware> list = new ArrayList<>();
        String sql = "SELECT id, brand, spec, type FROM hardware;";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int    id    = rs.getInt("id");
                String brand = rs.getString("brand");
                int    spec  = rs.getInt("spec");
                String type  = rs.getString("type");

                if (type.equalsIgnoreCase("Laptop")) {
                    list.add(new Laptop(id, brand, spec, type));
                } else if (type.equalsIgnoreCase("Phone")) {
                    list.add(new Phone(id, brand, spec, type));
                }
            }

        } catch (Exception e) {
            System.out.println("[DB ERROR] findAll: " + e.getMessage());
        }

        return list;
    }
}
