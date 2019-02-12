import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception{

        /////////////  PARTIE 1 ///////////////////
        /*ArrayList<Spell> listSpell = Crawler.retrieveSpell(1975);
        Mongo.addSpells(listSpell);
        */
        /*for(String sort : MapReduce.mapReduceForSpell()){
            System.out.println(sort);
        } */


        SQlite.connect();
        //SQlite.init();
        /*for(Spell sort : Mongo.retrieveSpells()){
            SQlite.addSpell(sort);

        }*/

        for (String s : SQlite.getSpellForPito()){
            System.out.println(s);
        }




    }
}
