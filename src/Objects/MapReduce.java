package Objects;

import java.util.*;

/**
 * La class MapReduce a pour but de faire des operations et requetes rapidement sur une base de donnees assez lourdes.
 * Les methodes du MapReduce nous permettent d'acceder facilement a des donnees complexes.
 */
public class MapReduce
{
    HashMap<String,Double> resultPageRank=new HashMap<String, Double>();

    /**
     *Cette methode va nous permettre d'organiser les donnees recuperees a l'aide du Crawler
     * Nous allons agir sur la HashMap qui est une liste de cles-valeurs unique.
     * Pour eviter les doublons de sort
     * @param spells : correspond a la liste des sorts récuperes grace au crawler
     * @return resultFinal : Lorsqu'on fait appel a cette methode, la liste de sorts est organisee en couple de cles
     * (nom de sort et niveau) associes à des valeurs (composantes du sort)
     */
    private List<Map.Entry<String[],ArrayList<String>>> MapReduce(List<Spell> spells)
    {
        List<Map.Entry<String[],ArrayList<String>>> resultFinal=new ArrayList<Map.Entry<String[],ArrayList<String>>>();
        for (int i=0;i<spells.size();i++)
        {
            //notre clé est composée par le nom du sort et son niveau. la valeur les la liste de ses composantes
            HashMap<String[],ArrayList<String>> keyValues=new HashMap<String[], ArrayList<String>>();
            Spell spell=spells.get(i);

                String[] key=new String[2];
                key[0]=spell.getName();
                key[1]=String.valueOf( spell.getLevelInt());
                ArrayList<String> value=spell.getComponents();
                keyValues.put(key,value);

            int a=0;
            //Emmit
            resultFinal.addAll(Reduce(keyValues));
        }
        return resultFinal;
    }

    /**
     * Cette methode va permettre de trier les sort adapte au magicien ( type verbale et de niveau inferieur ou egal à 4
     * @param keyValues : Liste des formules que le magicien pourrait reciter associes à un nom de sort et un niveau
     * @return result : une liste contenant uniquement les sort de type verbales et de niveau inferieur ou egal à 4
     */
    private List<Map.Entry<String[],ArrayList<String>>> Reduce(HashMap<String[],ArrayList<String>> keyValues)
    {

        List<Map.Entry<String[],ArrayList<String>>> result=new ArrayList<Map.Entry<String[], ArrayList<String>>>();
        Iterator<Map.Entry<String[],ArrayList<String>>> it=keyValues.entrySet().iterator();
        //On itere sur les cles valeurs
        while (it.hasNext())
        {
            Map.Entry<String[],ArrayList<String>> item=(Map.Entry<String[], ArrayList<String>>)it.next();
            String[] key=item.getKey();
            ArrayList<String > value=item.getValue();
            int level=Integer.parseInt(key[1]);
            //sort a une seule composante verbale
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


    public ArrayList<String> mapReduceForSpell (){
        List<Spell> spells=Mongo.retrieveSpells();
        List<Map.Entry<String[],ArrayList<String>>> result=MapReduce(spells);

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

    /**
     * Cette methode permet de calculer le Pagerank de chaque page web recuperee par le crawler
     * @param pages : la page dont on souhaite calculer le PageRank ou mettre en place un nouveau voisin
     */
    public void MapReducePageRank(List<Page> pages)
    {

        for (int i=0;i<pages.size();i++)
        {
            //Clé: page emettrice deu pagerank et voisin destinataire, valeur: pagerank envoyé
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

            //Emmit
            ReducePageRank((keyValues));
            int a=0;
        }

        //Mise a jour des pages voisines et des PageRank
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

    /**
     * A partir de cette methode on va iterer sur toutes les pages. Si la cle n existe pas, on cree une nouvelle page
     * Suivant d'un pageRank, sinon on associe à cette cle un PageRank que l'on calcul (mise a jour)
     * @param keyValues : la cle de la page
     */
    private void ReducePageRank(HashMap<String[],Double> keyValues)
    {

        Iterator<Map.Entry<String[],Double>> it=keyValues.entrySet().iterator();
        //On itere sur les cles valeurs;
        while (it.hasNext())
        {
            Map.Entry<String[],Double> item=it.next();
            String itemKey[]=item.getKey();
            String key=itemKey[0];
            Double value=item.getValue();

            //la clé n'existe pas une nouvelle est cree
            if(!resultPageRank.containsKey(key))
            {
                resultPageRank.put(key,value);
            }
            //la clé existe deja on ajoute a la valeur deja existante
            else
            {
                Double newValue=resultPageRank.get(key)+value;
                resultPageRank.put(key,newValue);
            }
        }
    }




}
