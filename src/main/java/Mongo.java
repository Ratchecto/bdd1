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

    public static void addSpell(Spell s){
        BasicDBObject SpellToSend = new BasicDBObject();
        SpellToSend.put("name", s.getName());
        SpellToSend.put("level",s.getLevel());

        BasicDBObject comp = new BasicDBObject();
            for (int i =0; i<s.getComponents().size(); i++){
                comp.put(Integer.toString(i),s.getComponents().get(i));
            }
        SpellToSend.put("components",comp);
        SpellToSend.put("spell_resistance", s.getRes());
        spellColl.insert(SpellToSend);
    }

    public static void reset(){
        spellColl.drop();
    }

}
