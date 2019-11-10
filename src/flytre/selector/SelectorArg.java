package flytre.selector;

public class SelectorArg {
    private String name;
    private String val;

    public SelectorArg(String name, String val) {
        this.name = name;
        this.val = val;
    }

    public String getName() {
        return name;
    }

    public String getVal() {
        return val;
    }

    @Override
    public String toString() {
        return name + "=" + (val == null ? "" : val);
    }


}
