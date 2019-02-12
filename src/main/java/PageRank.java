import java.util.ArrayList;

public class PageRank {

    public static void main(String[] args) {
        Page pA = new Page("Page A");
        Page pB = new Page("Page B");
        Page pC = new Page("Page C");
        Page pD = new Page("Page D");

        //On construit le voisinage selon le modèle du sujet
        ArrayList<Page> neighborhood = new ArrayList<Page>();
        neighborhood.add(pB);
        neighborhood.add(pC);
        pA.setNeighboors(neighborhood);

        neighborhood = new ArrayList<Page>();
        neighborhood.add(pC);
        pD.setNeighboors(neighborhood);

        neighborhood = new ArrayList<Page>();
        neighborhood.add(pC);
        pB.setNeighboors(neighborhood);

        neighborhood = new ArrayList<Page>();
        neighborhood.add(pA);
        pC.setNeighboors(neighborhood);

        //Liste totale de nos pages à traiter
        ArrayList<Page> listPages = new ArrayList<Page>();
        listPages.add(pA);
        listPages.add(pB);
        listPages.add(pC);
        listPages.add(pD);

        MapReduce.MapReducePages(listPages);
    }

}
