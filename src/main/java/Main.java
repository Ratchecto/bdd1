import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception{

        /////////////  PARTIE 1 ///////////////////
        /*ArrayList<Spell> listSpell = Crawler.retrieveSpell(1975);
        Mongo.connect();
        Mongo.addSpells(listSpell);
        */
        List<Spell> spells=Mongo.retrieveSpells();
        List<Map.Entry<String[],String>> result=MapReduce.MapReduce(spells);

        //SQlite.connect();
        for (int i=0;i<result.size();i++)
        {
            String[] key=result.get(i).getKey();
            String keyString="";
            for(int j=0;j<key.length;j++)
            {
                if(j%2==1)
                {
                    keyString+=" ";
                }
                keyString+=key[j];
            }
             //System.out.println(keyString+" "+result.get(i).getValue());
        }
    }
}
