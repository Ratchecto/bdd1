import java.util.*;

public class MapReduce
{
    public static List<Map.Entry<String[],String>> MapReduce(List<Spell> spells)
    {

        List<Map.Entry<String[],String>> resultFinal=new ArrayList<Map.Entry<String[],String>>();
        for (int i=0;i<spells.size();i++)
        {
            HashMap<String[],String> keyValues=new HashMap<String[], String>();
            Spell spell=spells.get(i);
            for(int j=0;j<spell.getComponents().size();j++)
            {
                String[] key=new String[2];
                key[0]=spell.getName();
                key[1]=String.valueOf( spell.getLevelInt());
                //int a=0;
                keyValues.put(key,spell.getComponents().get(j).trim());
            }
            int a=0;
            resultFinal.addAll(Reduce(keyValues));
        }
        return resultFinal;
    }

    private static List<Map.Entry<String[],String>> Reduce(HashMap<String[],String> keyValues)
    {

        List<Map.Entry<String[],String>> result=new ArrayList<Map.Entry<String[], String>>();
        Iterator<Map.Entry<String[],String>> it=keyValues.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry<String[],String> item=(Map.Entry<String[], String>)it.next();
            String[] key=item.getKey();
            String value=item.getValue();
            int level=Integer.parseInt(key[1]);
            if(value.equals("V"))
            {
                if(level<=4)
                {
                    result.add(item);
                }
            }
        }
        return result;
    }

    //MapReduce pour les pages
    public static List<Map.Entry<String,String>> MapReducePages(List<Page> pages)
    {

        List<Map.Entry<String,String>> resultFinal=new ArrayList<Map.Entry<String,String>>();
        for (int i=0;i<pages.size();i++)
        {
            HashMap<String,String> keyValues=new HashMap<String, String>();
            Page page=pages.get(i);
            for(int j=0;j<page.getNeighboors().size();j++)
            {
                String key = page.getName();
                String[] tab = new String[2];
                tab[0] = String.valueOf(page.getPageRank());
                tab[1] = String.valueOf(page.getNeighboors());
                keyValues.put(key,"PageRank : "+tab[0]+", Voisins :"+tab[1]);
            }
            resultFinal.addAll(ReducePageRank(keyValues));
            System.out.println(keyValues.toString());
        }
        return resultFinal;
    }

    private static List<Map.Entry<String,String>> ReducePageRank(HashMap<String,String> keyValues)
    {

        List<Map.Entry<String,String>> result=new ArrayList<Map.Entry<String, String>>();
        Iterator<Map.Entry<String,String>> it=keyValues.entrySet().iterator();
        while (it.hasNext())
        {

        }
        return result;
    }
}
