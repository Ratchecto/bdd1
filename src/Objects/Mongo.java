package Objects;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * La class Mongo permet d'ajouter et stocker toutes nos donnees a la base de donnees, afin d'y faire des requetes
 */
public class Mongo {

    private  static MongoCollection<Document>  spellColl;
    private static Boolean connected= false ;

    /**
     * Connection a la base de donnees (utilisation d'un service gratuit en ligne
     * pour faciliter le travail en commun)
     */
    public static void connect(){
        if( ! connected) {
            MongoClientURI uri = new MongoClientURI("mongodb://user:password1@ds247698.mlab.com:47698/labdd");
            MongoClient client = new MongoClient(uri);
            MongoDatabase db = client.getDatabase(uri.getDatabase());
            spellColl = db.getCollection("Speeeeelll");
            connected = true;
        }
    }

    /**
     * Cette methode permet d'ajouter un sort a partir de la Hashmap instancier dans la class MapReduce
     * @param ss : le sort que l'on souhaite ajouter a la DB, contenant son nom, son niveau et sa composante
     */
    public static void addSpells( ArrayList<Spell> ss){
        if (!connected)
            connect();
        ArrayList<Document> spellsToSend = new ArrayList<Document>();
        Document spellToSend =null;
        for( Spell s : ss) {
            spellToSend = new Document();
            spellToSend.put("name", s.getName());
            spellToSend.put("level", s.getLevel());

            Document comp = new Document();
            for (int i = 0; i < s.getComponents().size(); i++) {
                comp.put(Integer.toString(i), s.getComponents().get(i));
            }
            spellToSend.put("components", comp);
            spellToSend.put("spell_resistance", s.getRes());
            spellsToSend.add(spellToSend);
        }
        spellColl.insertMany(spellsToSend);

    }

    /**
     * Cette methode permet de recuperer tout les sorts a partir du conteneur Iterator
     * @return La liste des sort contenu dans MDB
     */
    public static List<Spell> retrieveSpells()
    {
        if (!connected)
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

    /**
     * Cette methode reinitialise la BDD
     */
    public static void reset(){
        spellColl.drop();
    }

}
