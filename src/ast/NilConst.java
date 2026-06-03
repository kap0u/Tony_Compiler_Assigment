package ast;

import symbol.*;
import types.*;
import errors.*;
import java.util.*;

public class NilConst extends Expr {

    @Override
    public void sem(SymbolTable symbols) throws SemanticException {
        // nil einai eidiki periptwsh gia listes
        this.type = BasicType.Nil;
    }
}