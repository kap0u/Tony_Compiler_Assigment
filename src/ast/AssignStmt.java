package ast;

import symbol.*;
import types.*;
import errors.*;
import java.util.*;

public class AssignStmt extends Stmt {

    private LValue target;
    private Expr expr;

    public AssignStmt(LValue target, Expr expr) {
        this.target = target;
        this.expr = expr;
    }

    @Override
    public void sem(SymbolTable symbols) throws SemanticException {
        target.sem(symbols);
        expr.sem(symbols);

        Type targetType = target.getType();
        Type exprType = expr.getType();

        // An kapoio apo ta dyo einai error, den bgazoume deutero lathos
        if (targetType.isError() || exprType.isError()) {
            return;
        }

            // To nil einai eidiki periptwsh: mporei na anatethei se opoiodipote list[t]
        if (exprType.equals(BasicType.Nil) && targetType instanceof ListType) {
            return;
        }

        // stin Tony to lvalue kai to expression prepei na exoun idio typo
        if (!targetType.equals(exprType)) {
            throw new TypeException(
                "Type mismatch in assignment: cannot assign " +
                exprType + " to " + targetType + "."
            );
        }
    }
}