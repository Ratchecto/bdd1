import java.util.List;
import java.util.Map;

public class Crawler {
    public static void main(String[] args) {

        Mongo.connect();
        //Mongo.addSpell();
        //SQlite.connect();
        //Mongo.connect();
        /*//Mongo.addSpell();
<<<<<<< Updated upstream
        SQlite.connect();
=======
        //SQlite.connect();*/
        List<Spell> spells=Mongo.retrieveSpells();
        List<Map.Entry<String[],String>> result=MapReduce.MapReduce(spells);
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
             System.out.println(keyString+" "+result.get(i).getValue());
        }
    }
}
