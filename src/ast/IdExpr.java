package ast;

import symbol.*;
import types.*;
import errors.*;
import java.util.*;

public class IdExpr extends Expr {

    private String name;

    public IdExpr(String name) {
        this.name = name;
    }

    @Override
    public void sem(SymbolTable symbols) throws SemanticException {
        // psaxnoume to identifier sto symbol table
        SymbolInfo info = symbols.lookup(name);

        if (info == null) {
            this.type = BasicType.Error;
        } else {
            this.type = TypeConverter.fromString(info.type);
        }
    }
}