package ast;

import codegen.*;
import symbol.*;
import types.*;
import errors.*;


public abstract class Expr extends ASTNode {

    protected Type type;

    public Type getType() {
        return type;
    }

    // Elegxei an h ekfrash exei ton typo pou perimenoume
    public void typeCheck(SymbolTable symbols, Type expected) throws SemanticException {
        sem(symbols);

        if (!type.equals(expected) && !type.isError()) {
            throw new TypeException(
                "Type error: expected " + expected + " but found " + type
            );
        }
    }

    public void generateJasmin(JasminWriter out, CodeGenContext ctx) {
    throw new UnsupportedOperationException(
        "Code generation not implemented for " + getClass().getSimpleName()
    );
}

}