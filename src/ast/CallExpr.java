package ast;

import symbol.*;
import types.*;
import errors.*;
import java.util.*;

import java.util.*;

public class CallExpr extends Expr {

    private String name;
    private ArrayList<Expr> args;

    public CallExpr(String name, ArrayList<Expr> args) {
        this.name = name;
        this.args = args;
    }

    @Override
    public void sem(SymbolTable symbols) throws SemanticException {
        SymbolInfo info = symbols.lookup(name);

        if (info == null) {
            this.type = BasicType.Error;
            return;
        }

        if (!info.isFunction) {
            throw new TypeException("'" + name + "' is not a function.");
        }

        if (info.paramCount != args.size()) {
            throw new TypeException(
                "Function '" + name + "' expects " +
                info.paramCount + " arguments but got " + args.size() + "."
            );
        }

        for (Expr arg : args) {
            arg.sem(symbols);
        }

        this.type = TypeConverter.fromString(info.type);
    }
}