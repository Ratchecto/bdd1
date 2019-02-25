package Objects;

import java.sql.*;
import java.util.ArrayList;

/**
 * La class SQLite permet de recuperer les sorts dont le magicien a besoin en SQLite
 */
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

    /**
     *  Methode lançant la requete  SQLite permetttant la creation des tables pour ajouter les sorts
     */
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
                sql = "SELECT COUNT(*) FROM Spellll";
                ResultSet result = stmt.executeQuery(sql);
                int nbr = result.getInt(1);


            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": "
                        + e.getMessage());
                System.exit(0);
            }
            // System.out.println("Tables created successfully");

    }

    /**
     * Methode lancant la requete  SQLite permetttant d'ajouter un sort a la table
     * @param spell que l'on souhaite à la DB
     */
    public static void addSpell(Spell spell)  {
        try {
            String sql = "INSERT INTO Spellll  (NAME,LEVEl, RESISTANCE,COMPONENT) VALUES ('"
                    + spell.getName() + "', " + spell.getLevelInt() + ", '"
                    + spell.getRes() + "','" + spell.getComponentInOne()+  "');";
            System.out.println(sql);

            stmt.executeUpdate(sql);

            } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

    /**
     * Methode lancant la requete  SQLite permettant de recuperer les sorts dont Pito le magicien a besoin
     * @return les sorts en question
     */
    public static ArrayList<String> getSpellForPito()  {
        ArrayList<String> spellForPito = new ArrayList<String>();
        try {
            String requete = "SELECT NAME , LEVEL , COMPONENT FROM Spellll "
                    + "WHERE LEVEL <= 4 AND (COMPONENT LIKE ' V' OR COMPONENT LIKE ' V ' OR COMPONENT LIKE 'V ')" +
                    "AND LEVEL >= 0";

            ResultSet result = stmt.executeQuery(requete);
            while (result.next()) {
                spellForPito.add(result.getString(1) + "  "+ result.getInt(2) + " "+ result.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return spellForPito;
    }







}