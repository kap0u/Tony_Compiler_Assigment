import java.util.ArrayList;

public class SymbolInfo {
    public String name;
    public String kind;   // variable, function, parameter
    public String type;   // int, char[], bool, etc.
    public boolean isRef;
    public boolean isFunction;
    public ArrayList<String> paramTypes;
    public int paramCount;

    public SymbolInfo(String name, String kind, String type, boolean isRef) {
        this.name = name;
        this.kind = kind;
        this.type = type;
        this.isRef = isRef;

        this.isFunction = false;
        this.paramTypes = new ArrayList<>();
        this.paramCount = 0;
    }

    public SymbolInfo(String name , String type , ArrayList<String> paramTypes) {
        this.name = name;
        this.kind = "function";
        this.type = type;
        this.isRef = false;

        this.isFunction = true;
        this.paramTypes = paramTypes;
        this.paramCount = paramTypes.size();
    }

    @Override
    public String toString() {
        return kind + " " + name + " : " + type + (isRef ? " ref" : "");
    }

}
//an exw int x to apothikevei se name=x , kind=variable ,  type=int , isRef = false