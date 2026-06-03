package ast;

import symbol.*;
import types.*;
import errors.*;
import java.util.*;

public class ReturnStmt extends Stmt {

    private Expr expr;

    public ReturnStmt(Expr expr) {
        this.expr = expr;
    }

    @Override
    public void sem(SymbolTable symbols) throws SemanticException {
        expr.sem(symbols);

        // Pros to paron elegxoume mono oti h expression einai valid.
        // Argotera tha sygkrinoume me ton return type ths current function.
    }

    public Type getReturnType(SymbolTable symbols) throws SemanticException {
        expr.sem(symbols);
        return expr.getType();
    }
}