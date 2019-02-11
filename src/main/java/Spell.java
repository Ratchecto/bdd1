import java.lang.reflect.Array;
import java.util.ArrayList;

public class Spell {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public ArrayList<String> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<String> components) {
        this.components = components;
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
        this.components = components;
        this.res = res;
    }
    public int getLevelInt(){
        String[] s = getLevel().split("wizard");
        if (s.length >=2){
            String  level = s[1].split(",")[0];
            return Integer.parseInt(level.trim());
        }else{
            String level = s[0].split(",")[0].replaceAll("[^\\d.]", "");
            return Integer.parseInt(level.trim());
        }


    }
    public String getComponentInOne (){
        StringBuilder sb = new StringBuilder();
        for (String s : components)
        {
            sb.append(s);
        }

        return sb.toString();
    }
}
