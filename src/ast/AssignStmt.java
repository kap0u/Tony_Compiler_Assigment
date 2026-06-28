package ast;

import codegen.*;
import symbol.*;
import types.*;
import errors.*;


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

   @Override
    public void generateJasmin(JasminWriter out, CodeGenContext ctx) {
        /*
        *   Το αποτέλεσμά της μένει πάνω στη JVM operand stack.
        */
        out.emit("; assignment");

        expr.generateJasmin(out, ctx);

        /*
        * apothikefsh ths timhs sto aristero l-value.
        * gia aplh metavliti ayto prepei na paragei istore.
        */
        target.generateStoreJasmin(out, ctx);
    }
}