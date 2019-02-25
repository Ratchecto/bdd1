package Objects;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * La class Spell permet d'intancier les sorts, que l'on va recuperer a partir du crawler
 */
public class Spell {
    // paramètre du constructeur
    String name;
    String level;
    ArrayList<String> components = new ArrayList<String>();
    String res;

    @Override
    public String toString() {
        return "Spell{" +
                "name='" + name + '\'' +
                ", level='" + level + '\'' +
                ", components=" + components +
                ", res='" + res + '\'' +
                '}';
    }

    /**
     * Recupere le nom du sort
     * @return  String le nom du sort
     */
    public String getName() {
        return name.replaceAll("'","");
    }

    /**
     * Caracterise le nom du sort
     * @param name : le nom du sort que l'on souhaite contruire
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return le niveau du sort
     */
    public String getLevel() {
        return level;
    }

    /**
     * Caracterise le niveau du sort
     * @param level : la niveau que l'on souhaite associer au sort
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     *
     * @return la composante du sort
     */
    public ArrayList<String> getComponents() {
        return components;
    }

    /**
     * Caracterise la composante du sort
     * @param components : la composante  que l'on souhaite associer au sort
     */
    public void setComponents(ArrayList<String> components) {

        for(int i=0;i<components.size();i++)
        {
            this.components.add(components.get(i).trim());
        }
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public Spell(String name, String level, ArrayList<String> components, String res) {
        this.name = name;
        this.level = level;
        setComponents(components);
        this.res = res;
    }

    /**
     *
     * @return uniquement le level du Wizard
     */
    public int getLevelInt(){
        String[] s = getLevel().split("wizard");
        if (s.length >=2){
            String  level = s[1].split(",")[0];
            return Integer.parseInt(level.trim());
        }else{
            String level = s[0].split(",")[0].replaceAll("[^\\d.]", "");
            int l = Integer.parseInt(level.trim());
            l= l - 2*l;
            if (l==0)
                l=-99;
            return l ;// Inférieur à 0 si pas un sort de Wizard
        }


    }

    /**
     *
     * @return toutes les compoantes d'un sort
     */
    public String getComponentInOne (){
        StringBuilder sb = new StringBuilder();
        for (String s : components)
        {
            sb.append(s);
        }

        return sb.toString();
    }
}
