package pixel.bus.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;

/**
 * Created by vanley on 08/06/2017.
 */
public class DerbyTableUtility {
    private static final Path SQL_DESTROY = Paths.get("src/res/sql/tablesDestroy.sql");
    private static final Path SQL_CREATE = Paths.get("src/res/sql/tablesCreate.sql");

    public static void cleanAll() {
        try (Connection con = DerbyConnectionUtility.getConnection()) {

            DatabaseMetaData dbmd = con.getMetaData();
            ResultSet rs = dbmd.getTables(null, "APP", null, null);
            if (rs.next()) {
                System.out.println("Table " +  rs.getString(3) + " exists");

                fileExecute(con, SQL_DESTROY);
                System.out.println("Tables destroyed");
            }

            fileExecute(con, SQL_CREATE);
            System.out.println("Clear tables recreated");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void fileExecute(Connection con, Path filePath){

        List<String> lines = null;
        try {
            lines = Files.readAllLines(filePath);
            for(String l : lines) {
                Statement stmt = con.createStatement();
                stmt.execute(l);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        cleanAll();
    }
}
