package flytre.selector;

import java.util.regex.MatchResult;

public enum SelectorType {

    ALL_PLAYERS("@a"),
    RANDOM_PLAYER("@r"),
    NEAREST_PLAYER("@p"),
    EXECUTING_PLAYER("@s"),
    ALL_ENTITIES("@e"),
    ;

    private String selector;



    SelectorType(String selector) {
        this.selector = selector;

    }

    public String getSelector() {
        return selector;
    }

    public String toString() {
        return selector;
    }

    public static SelectorType getSelector(String selector) {
        for(int i = 0; i < SelectorType.values().length; i++)
            if(SelectorType.values()[i].selector.equals(selector))
                return SelectorType.values()[i];
        return null;
    }


}
