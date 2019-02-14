import java.util.*;

public class MapReduce
{
    public static List<Map.Entry<String[],ArrayList<String>>> MapReduce(List<Spell> spells)
    {

        List<Map.Entry<String[],ArrayList<String>>> resultFinal=new ArrayList<Map.Entry<String[],ArrayList<String>>>();
        for (int i=0;i<spells.size();i++)
        {
            HashMap<String[],ArrayList<String>> keyValues=new HashMap<String[], ArrayList<String>>();
            Spell spell=spells.get(i);

                String[] key=new String[2];
                key[0]=spell.getName();
                key[1]=String.valueOf( spell.getLevelInt());
                //int a=0;
                keyValues.put(key,spell.getComponents());

            int a=0;
            resultFinal.addAll(Reduce(keyValues));
        }
        return resultFinal;
    }

    private static List<Map.Entry<String[],ArrayList<String>>> Reduce(HashMap<String[],ArrayList<String>> keyValues)
    {

        List<Map.Entry<String[],ArrayList<String>>> result=new ArrayList<Map.Entry<String[], ArrayList<String>>>();
        Iterator<Map.Entry<String[],ArrayList<String>>> it=keyValues.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry<String[],ArrayList<String>> item=(Map.Entry<String[], ArrayList<String>>)it.next();
            String[] key=item.getKey();
            ArrayList<String > value=item.getValue();
            int level=Integer.parseInt(key[1]);
            if(value.size() == 1 && value.get(0).equals("V"))
            {
                if(level >=0 && level<=4)
                {
                    result.add(item);
                }
            }
        }
        return result;
    }


    public static ArrayList<String> mapReduceForSpell (){
        List<Spell> spells=Mongo.retrieveSpells();
        List<Map.Entry<String[],ArrayList<String>>> result=MapReduce.MapReduce(spells);

        ArrayList<String> SpellForPito = new ArrayList<String>();

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
            SpellForPito.add(keyString+" "+result.get(i).getValue());
        }
        return SpellForPito ;
    }

    public void MapReducePageRank(List<Page> pages)
    {

        for (int i=0;i<pages.size();i++)
        {
            HashMap<String[],Double> keyValues=new HashMap<String[], Double>();
            Page page=pages.get(i);
            for(int j=0;j<page.getNeighboors().size();j++)
            {
                String[] key = new String[2];
                key[0]= page.getNeighboors().get(j).getName();
                key[1]=page.getName();
                double value=page.getPageRank()/page.getNeighboors().size();
                keyValues.put(key,value);
            }
            ReducePageRank((keyValues));
            int a=0;
        }


        for (int i=0;i<pages.size();i++)
        {
            Page page=pages.get(i);
            String name=page.getName();
            Double received=(Double) 0.0;
            if(resultPageRank.containsKey(name))
            {
                received= resultPageRank.get(name);

            }
            double pr= ((1-page.DAMPINGFACTOR)+(page.DAMPINGFACTOR*received));
            System.out.println("pagerank "+page.getName()+" "+pr);
            page.setPageRank(pr);
        }
    }

    private void ReducePageRank(HashMap<String[],Double> keyValues)
    {

        Iterator<Map.Entry<String[],Double>> it=keyValues.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry<String[],Double> item=it.next();
            String itemKey[]=item.getKey();
            String key=itemKey[0];
            Double value=item.getValue();
            if(!resultPageRank.containsKey(key))
            {

                resultPageRank.put(key,value);
            }
            else
            {
                Double newValue=resultPageRank.get(key)+value;
                resultPageRank.put(key,newValue);
            }
        }
    }




}
