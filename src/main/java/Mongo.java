import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Mongo {

    private  static MongoCollection<Document>  spellColl;

    /**
     * Connection à la base de données (utilisation d'un service gratuit en ligne
     * pour faciliter le travail en commun)
     */
    public static void connect(){
        MongoClientURI uri = new MongoClientURI("mongodb://user:password1@ds247698.mlab.com:47698/labdd");
        MongoClient client = new MongoClient(uri);
        MongoDatabase db = client.getDatabase(uri.getDatabase());
        spellColl = db.getCollection("Speeeeelll");

    }

    public static void addSpell(Spell s){
        Document SpellToSend = new Document();
        SpellToSend.put("name", s.getName());
        SpellToSend.put("level",s.getLevel());

        Document comp = new Document();
            for (int i =0; i<s.getComponents().size(); i++){
                comp.put(Integer.toString(i),s.getComponents().get(i));
            }
        SpellToSend.put("components",comp);
        SpellToSend.put("spell_resistance", s.getRes());
        spellColl.insertOne(SpellToSend);
    }

    public static List<Spell> retrieveSpells()
    {
        connect();
        MongoCursor<Document> cursor  = spellColl.find().iterator();
        List<Spell> spells=new ArrayList<Spell>();
        while (cursor.hasNext())
        {
            Document doc= cursor.next();
            String name=doc.getString("name");
            String level=doc.getString("level");
            Document c=(Document) doc.get("components");
            ArrayList<String> components=new ArrayList<String>();
            for(int i=0;i<c.size();i++)
            {
                String item=c.getString(String.valueOf(i));
                components.add(item);
            }
            String res=doc.getString("res");
            Spell spell=new Spell(name,level,components,res);
            spells.add(spell);
        }
        return  spells;
    }

    public static void reset(){
        spellColl.drop();
    }

}
