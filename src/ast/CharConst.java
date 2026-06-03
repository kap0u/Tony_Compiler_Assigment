package ast;

import symbol.*;
import types.*;
import errors.*;
import java.util.*;

public class CharConst extends Expr {

    private String value;

    public CharConst(String value) {
        this.value = value;
    }

    @Override
    public void sem(SymbolTable symbols) throws SemanticException {
        // character constant -> char
        this.type = BasicType.Char;
    }
}