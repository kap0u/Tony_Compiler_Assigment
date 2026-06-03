package ast;

import symbol.*;
import types.*;
import errors.*;
import java.util.*;

public class CallStmt extends Stmt {

    private CallExpr call;

    public CallStmt(CallExpr call) {
        this.call = call;
    }

    @Override
    public void sem(SymbolTable symbols) throws SemanticException {
        call.sem(symbols);

        // San entolh, h klhsh prepei na einai procedure / void
        if (!call.getType().equals(BasicType.Void) && !call.getType().isError()) {
            throw new TypeException("Function call used as statement must return void.");
        }
    }
}