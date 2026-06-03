package ast;

import symbol.*;
import types.*;
import errors.*;
import java.util.*;

public class NewArrayExpr extends Expr {

    private Type elementType;
    private Expr sizeExpr;

    public NewArrayExpr(Type elementType, Expr sizeExpr) {
        this.elementType = elementType;
        this.sizeExpr = sizeExpr;
    }

    @Override
    public void sem(SymbolTable symbols) throws SemanticException {
        sizeExpr.sem(symbols);

        // to megethos tou pinaka prepei na einai int
        if (!sizeExpr.getType().equals(BasicType.Int) && !sizeExpr.getType().isError()) {
            throw new TypeException("Array size expression must be of type int.");
        }

        // new t[e] epistrefei pinaka typou t[]
        this.type = new ArrayType(elementType);
    }
}