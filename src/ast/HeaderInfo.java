package ast;

import types.*;
import java.util.*;

public class HeaderInfo {

    public String name;
    public Type returnType;
    public ArrayList<Param> params;

    public HeaderInfo(String name, Type returnType, ArrayList<Param> params) {
        this.name = name;
        this.returnType = returnType;
        this.params = params;
    }
}