import java.sql.*;
import java.util.ArrayList;


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
                stmt.execute("DROP TABLE Spellll");
                String sql = "CREATE TABLE IF NOT EXISTS Spellll" // IF NOT EXISTS
                        + "(ID INTEGER PRIMARY KEY     AUTOINCREMENT,"
                        + " NAME           TEXT    NOT NULL, "
                        + " LEVEL          INT     NOT NULL, "
                        + " COMPONENT      TEXT     NOT NULL, "
                        + " RESISTANCE     TEXT     NOT NULL)";
                stmt.executeUpdate(sql);


                // Pour connaitre le nombre de sort deja presents
                sql = "SELECT COUNT(*) FROM Spell";
                ResultSet result = stmt.executeQuery(sql);
                int nbr = result.getInt(1);


            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": "
                        + e.getMessage());
                System.exit(0);
            }
            // System.out.println("Tables created successfully");

    }

    public void addSpell(Spell spell)  {
        try {
            String sql = "INSERT INTO Spell  (NAME,LEVEl, RESISTANCE,COMPONENT) VALUES ('"
                    + spell.getName() + "', " + spell.getLevelInt() + ", '"
                    + spell.getRes() + "','" + spell.getComponentInOne()+  "');";
            stmt.executeUpdate(sql);

            } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

    public ArrayList<String> getSpellForPito()  {
        ArrayList<String> spellForPito = new ArrayList<String>();
        try {
            String requete = "SELECT NAME FROM Spell "
                    + "WHERE Spell.LEVEL < 5 AND COMPONENT LIKE '%V%'";

            ResultSet result = stmt.executeQuery(requete);
            while (result.next()) {
                spellForPito.add(result.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return spellForPito;
    }







}