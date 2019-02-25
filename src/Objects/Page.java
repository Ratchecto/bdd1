package Objects;

import java.util.ArrayList;

public class Page {
    public  static final Double DAMPINGFACTOR=0.85;
    private String name;
    private double pageRank = 1;
    private ArrayList<Page> neighbors = new ArrayList<Page>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPageRank() {
        return pageRank;
    }

    public void setPageRank(double pageRank) {
        this.pageRank = pageRank;
    }

    public ArrayList<Page> getNeighboors() {
        return neighbors;
    }

    public void setNeighboors(ArrayList<Page> neighboors) {
        this.neighbors = neighboors;
    }

    public Page() {
    }

    public Page(String name) {
        this.name = name;
    }

    public Page(String name, double pageRank, ArrayList<Page> neighboors) {
        this.name = name;
        this.pageRank = pageRank;
        this.neighbors = neighboors;
    }
}
