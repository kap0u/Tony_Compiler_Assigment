package ast;

import symbol.*;
import types.*;
import errors.*;
import java.util.*;

public class Param extends ASTNode {

    private Type type;
    private ArrayList<String> names;
    private boolean isRef;

    public Param(Type type, ArrayList<String> names, boolean isRef) {
        this.type = type;
        this.names = names;
        this.isRef = isRef;
    }

    public Type getType() {
        return type;
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public boolean isRef() {
        return isRef;
    }

    public int count() {
        return names.size();
    }

    @Override
    public void sem(SymbolTable symbols) throws SemanticException {
        // dilwsi parametrwn sto current function scope
        for (String name : names) {
            symbols.declare(
                name,
                new SymbolInfo(name, "parameter", type.toString(), isRef)
            );
        }
    }
}