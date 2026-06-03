package ast;

import symbol.*;
import types.*;
import errors.*;
import java.util.*;

public class VarDecl extends ASTNode {

    private Type type;
    private ArrayList<String> names;

    public VarDecl(Type type, ArrayList<String> names) {
        this.type = type;
        this.names = names;
    }

    public Type getType() {
        return type;
    }

    public ArrayList<String> getNames() {
        return names;
    }

    @Override
    public void sem(SymbolTable symbols) throws SemanticException {
        // dilwsi topikwn metavlitwn sto current scope
        for (String name : names) {
            symbols.declare(
                name,
                new SymbolInfo(name, "variable", type.toString(), false)
            );
        }
    }
}