package ast;

import symbol.*;
import types.*;
import errors.*;
import java.util.*;

public class BoolConst extends Expr {

    private boolean value;

    public BoolConst(boolean value) {
        this.value = value;
    }

    @Override
    public void sem(SymbolTable symbols) throws SemanticException {
        // true / false -> bool
        this.type = BasicType.Bool;
    }
}