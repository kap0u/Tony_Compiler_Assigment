package ast;

import symbol.*;
import types.*;
import errors.*;
import java.util.*;

public class IntConst extends Expr {

    private int value;

    public IntConst(int value) {
        this.value = value;
    }

    @Override
    public void sem(SymbolTable symbols) throws SemanticException {
        // akeraia stathera -> int
        this.type = BasicType.Int;
    }
}