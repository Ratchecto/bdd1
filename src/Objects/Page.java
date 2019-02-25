package Objects;

import java.util.ArrayList;

/**
 * Cette class permet d'intancier une page web et de lui associer un PageRank ainsi que des voisins
 */
public class Page {
    public  static final Double DAMPINGFACTOR=0.85;
    private String name;
    private double pageRank = 1;
    private ArrayList<Page> neighbors = new ArrayList<Page>();

    /**
     *
     * @return le nom de la page
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name : nom de la page a ajouter pour le constructeur de la page
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return le PageRank de la page
     */
    public double getPageRank() {
        return pageRank;
    }

    /**
     *
     * @param pageRank : PageRank de la page a ajouter pour le constructeur de la page
     */
    public void setPageRank(double pageRank) {
        this.pageRank = pageRank;
    }

    /**
     *
     * @return tous les voisins de la page
     */
    public ArrayList<Page> getNeighboors() {
        return neighbors;
    }

    /**
     *
     * @param neighboors : le voisin que l'on souhaite attribuer a la page (sert au constructeur de la page)
     */
    public void setNeighboors(ArrayList<Page> neighboors) {
        this.neighbors = neighboors;
    }

    public Page() {
    }

    public Page(String name) {
        this.name = name;
    }

    /**
     * Constuction d'une page
     * @param name le nom de la page recuperee
     * @param pageRank la valeur du PageRank associe a la page
     * @param neighboors le nom des pages voisines de la page
     */
    public Page(String name, double pageRank, ArrayList<Page> neighboors) {
        this.name = name;
        this.pageRank = pageRank;
        this.neighbors = neighboors;
    }
}
