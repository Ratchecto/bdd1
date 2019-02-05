import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;


public class SQlite {

    /**
     * Connexion à une database locale sqlite
     */
    public static void connect() {
        try {
            //Chargement de la class adéquate
            Class.forName("org.sqlite.JDBC");
            //connexion à une base de données locale
            Connection conn = DriverManager.getConnection("jdbc:sqlite:sqlite.db");
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static  void init(){


    }
    public static void addSpell(){

    }





}