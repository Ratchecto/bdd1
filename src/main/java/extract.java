import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class extract {

    public static void main(String[] args) throws IOException {

        ArrayList<String> splitContentLevel = new ArrayList<String>();
        ArrayList<String> splitContentComponents = new ArrayList<String>();
        ArrayList<String> splitContentRes = new ArrayList<String>();
        ArrayList<String> level = new ArrayList<String>();
        String nbLevel = "";
        String res = "";
        String nameSpell = "";
        ArrayList<String> components = new ArrayList<String>();
        ArrayList<String> componentV = new ArrayList<String>();
        ArrayList<String> compo = new ArrayList<String>();
        ArrayList<Spell> spells = new ArrayList<Spell>();

        for(int i = 1 ; i < 1000 ; i++) {

            Document doc = Jsoup.connect("http://www.dxcontent.com/SDB_SpellBlock.asp?SDBID="+i).get();
            Elements name = doc.getElementsByClass("heading");
            Elements newsHeadlines = doc.getElementsByClass("SPDet");

            nameSpell = name.text();

            for (Element headline : newsHeadlines) {
                String content = headline.text();
                if (content.contains("Level")) {
                    splitContentLevel = new ArrayList<String>(Arrays.asList(content.split("Level")));
                    level = new ArrayList<String>(Arrays.asList(splitContentLevel.get(1)));
                    nbLevel = level.get(0);
                }
                if (content.contains("Components")) {
                    splitContentComponents = new ArrayList<String>(Arrays.asList(content.split("[(]")));
                    components = new ArrayList<String>(Arrays.asList(splitContentComponents.get(0).split(","))); // récupère tous les composants + "components"
                    componentV = new ArrayList<String>(Arrays.asList(components.get(0).split("Components"))); // récupère première composante

                    compo.add(componentV.get(1));
                    for(int j=1;j<components.size();j++) {
                        compo.add((components.get(j)));
                    }

                }
                if (content.contains("Spell Resistance")) {
                    splitContentRes = new ArrayList<String>(Arrays.asList(content.split("Spell Resistance")));
                    res = splitContentRes.get(1);
                }
                if (res == "") {
                    res = "false";
                }
            }
            Spell spell = new Spell(nameSpell,nbLevel,compo,res);

            splitContentLevel = new ArrayList<String>();
            splitContentComponents = new ArrayList<String>();
            splitContentRes = new ArrayList<String>();
            level = new ArrayList<String>();
            components = new ArrayList<String>();
            componentV = new ArrayList<String>();
            compo = new ArrayList<String>();

            System.out.println("Sort "+i+" "+spell.toString());
            spells.add(spell);
        }
        System.out.println(spells.size());

        Mongo.connect();
        Mongo.reset();
        for (Spell s: spells){
            Mongo.addSpell(s);
        }

    }


}
