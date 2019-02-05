import com.mongodb.*;

public class Mongo {

    private  static DBCollection spellColl;

    /**
     * Connection à la base de données (utilisation d'un service gratuit en ligne
     * pour faciliter le travail en commun)
     */
    public static  void connect(){
        MongoClientURI uri = new MongoClientURI("mongodb://user:password1@ds247698.mlab.com:47698/labdd");
        MongoClient mongoClient = new MongoClient(uri);
        DB database = mongoClient.getDB("labdd");
        spellColl = database.getCollection("Speeeeelll");

    }

    public static void addSpell(){
        BasicDBObject SpellToSend = new BasicDBObject();
        SpellToSend.put("name", "hihihihi");
        SpellToSend.put("level","hihih");

        BasicDBObject comp = new BasicDBObject();
            String [] a = {"V","S"};
            for (int i =0; i<a.length; i++){
                comp.put(Integer.toString(i),a[i]);
            }
        SpellToSend.put("components",comp);
        SpellToSend.put("spell_resistance", "Baeldung");
        spellColl.insert(SpellToSend);
    }

}
