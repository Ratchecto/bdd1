import java.sql.*;


public class SQlite {
    private static Statement stmt = null;

    /**
     * Connexion à une database locale sqlite
     */
    public static void connect() {
        try {
            //Chargement de la class adéquate
            Class.forName("org.sqlite.JDBC");
            //connexion à une base de données locale
            Connection conn = DriverManager.getConnection("jdbc:sqlite:sqlite.db");
            stmt = conn.createStatement();
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void init() {

            try {
                stmt.execute("DROP TABLE Spell");
                String sql = "CREATE TABLE IF NOT EXISTS Spell" // IF NOT EXISTS
                        + "(ID INTEGER PRIMARY KEY     AUTOINCREMENT,"
                        + " NAME           TEXT    NOT NULL, "
                        + " LEVEL          INT     NOT NULL, "
                        + " RESISTANCE     INT     NOT NULL)";
                stmt.executeUpdate(sql);

                sql = "CREATE TABLE IF NOT EXISTS Component "
                        + "(ID INTEGER PRIMARY KEY     AUTOINCREMENT,"
                        + " NAME           TEXT    NOT NULL)";
                stmt.executeUpdate(sql);

                stmt.execute("DROP TABLE ComponentsBySpells");
                sql = "CREATE TABLE IF NOT EXISTS ComponentsBySpells "
                        + "(ID INTEGER PRIMARY KEY     AUTOINCREMENT,"
                        + " INDEXSPELL     INT     NOT NULL,"
                        + " INDEXCOMPONENT INT     NOT NULL)";
                stmt.executeUpdate(sql);

                // Pour connaitre le nombre de sort deja presents
                sql = "SELECT COUNT(*) FROM Spell";
                ResultSet result = stmt.executeQuery(sql);
                int nbr = result.getInt(1);

                // Inserer les composants
                sql = "DELETE FROM Component;";
                stmt.executeUpdate(sql);
                sql = "INSERT INTO Component (ID, NAME) VALUES (1,'V');";
                stmt.executeUpdate(sql);
                sql = "INSERT INTO Component (ID, NAME) VALUES (2,'S');";
                stmt.executeUpdate(sql);
                sql = "INSERT INTO Component (ID, NAME) VALUES (3,'M');";
                stmt.executeUpdate(sql);
                sql = "INSERT INTO Component (ID, NAME) VALUES (4,'F');";
                stmt.executeUpdate(sql);
                sql = "INSERT INTO Component (ID, NAME) VALUES (5,'DF');";
                stmt.executeUpdate(sql);
                sql = "INSERT INTO Component (ID, NAME) VALUES (6,'AF');";
                stmt.executeUpdate(sql);

            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": "
                        + e.getMessage());
                System.exit(0);
            }
            // System.out.println("Tables created successfully");

    }

    public static void addSpell(){

    }





}