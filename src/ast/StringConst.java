package ast;

import symbol.*;
import types.*;
import errors.*;
import java.util.*;

public class StringConst extends Expr {

    private String value;

    public StringConst(String value) {
        this.value = value;
    }

    @Override
    public void sem(SymbolTable symbols) throws SemanticException {
        // string literal -> char[]
        this.type = new ArrayType(BasicType.Char);
    }
}