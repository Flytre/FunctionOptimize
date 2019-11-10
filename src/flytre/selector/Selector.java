package flytre.selector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Selector {


    private SelectorType selector;
    private ArrayList<SelectorArg> misc;


    public Selector(String input) {
        selector = SelectorType.getSelector(input.substring(0, 2));

        //Find all the Selector Arguments in the String to parse
        //An argument is matched by name_lowercase = {any_characters} or
        //An argument is matched by name_lowercase = "any_characters" or
        //name_lowercase = any_character_but_comma_or_closing_bracket
        Matcher matcher = Pattern.compile("([a-z_]+?)=(!?((\"(.*)\")|(\\{(.*)})|([^,\\]]+))?)").matcher(input.replace(" ", ""));

        //create an array of selector arguments from
        misc = new ArrayList<>();
        for (; matcher.find(); )
            misc.add(new SelectorArg(matcher.group(1), matcher.group(2)));

        sortArgs();

    }

    private void sortArgs() {
        int i = moveToIndexIfIs("type", 0);
        i = moveToIndex("gamemode", i);
        i = moveToIndex("team", i);
        i = moveToIndexIfNot("type", i);
        i = moveToIndex("tag", i);
        i = moveToIndex("name", i);
        i = moveToIndex("scores", i);
        i = moveToIndex("advancements", i);
        moveToIndex("nbt", i);
    }

    private int moveToIndex(String paramName, int i) {
        for (int j = 0; j < misc.size(); j++) {
            if (misc.get(j).getName().equals(paramName))
                Collections.swap(misc, i++, j);
        }
        return i;
    }

    private int moveToIndexIfNot(String paramName, int i) {
        for (int j = 0; j < misc.size(); j++) {
            if (misc.get(j).getName().equals(paramName) && misc.get(j).getVal().charAt(0) == '!')
                Collections.swap(misc, i++, j);
        }
        return i;
    }

    private int moveToIndexIfIs(String paramName, int i) {
        for (int j = 0; j < misc.size(); j++) {
            if (misc.get(j).getName().equals(paramName) && misc.get(j).getVal().charAt(0) != '!')
                Collections.swap(misc, i++, j);
        }
        return i;
    }

    public String getSelector() {
        StringBuilder s = new StringBuilder();

        s.append(selector);
        if (misc.size() >= 1) {
            s.append("[");
            for (SelectorArg sa : misc)
                s.append(sa).append(",");
            s.deleteCharAt(s.length() - 1);
            s.append("]");
        }
        return s.toString();

    }


    public static String optimizeSelector(MatchResult selector) {
        return new Selector(selector.group()).getSelector();

    }

}
