import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception{

        /////////////  PARTIE 1 ///////////////////
        /*ArrayList<Spell> listSpell = Crawler.retrieveSpell(1975);
        Mongo.addSpells(listSpell);
        */
        for(String sort : MapReduce.mapReduceForSpell()){
            System.out.println(sort);
        }


        SQlite.connect();
        //SQlite.init();
        /*for(Spell sort : Mongo.retrieveSpells()){
            SQlite.addSpell(sort);

        }*/

        for (String s : SQlite.getSpellForPito()){
            System.out.println(s);
        }




    }

    private static void PageRankTest()
    {
        Page A=new Page();
        Page B=new Page();
        Page C=new Page();
        Page D=new Page();
        A.setName("A");
        A.setPageRank(1);

        B.setName("B");
        B.setPageRank(1);

        C.setName("C");
        C.setPageRank(1);

        D.setName("D");
        D.setPageRank(1);

        A.getNeighboors().add(C);
        A.getNeighboors().add(B);
        B.getNeighboors().add(C);
        C.getNeighboors().add(A);
        D.getNeighboors().add(C);
        List<Page> pages=new ArrayList<Page>();
        pages.add(A);
        pages.add(B);
        pages.add(C);
        pages.add(D);
        for(int i=0;i<20;i++) {
            MapReduce mr = new MapReduce();
            mr.MapReducePages(pages);

        }
        int a=0;
    }
}
