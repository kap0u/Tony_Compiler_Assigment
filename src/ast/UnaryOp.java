package ast;

import symbol.*;
import types.*;
import errors.*;
import java.util.*;

public class UnaryOp extends Expr {

    public enum Operator {
        NEG,
        NOT,
        HEAD,
        TAIL,
        NILQ
    }

    private Operator op;
    private Expr expr;

    public UnaryOp(Operator op, Expr expr) {
        this.op = op;
        this.expr = expr;
    }

    @Override
    public void sem(SymbolTable symbols) throws SemanticException {
        expr.sem(symbols);

        Type exprType = expr.getType();

        switch (op) {

            case NEG:
                // unary minus: -int -> int
                if (!exprType.equals(BasicType.Int)) {
                    throw new TypeException(
                        "Unary '-' requires int operand."
                    );
                }

                this.type = BasicType.Int;
                break;

            case NOT:
                // not bool -> bool
                if (!exprType.equals(BasicType.Bool)) {
                    throw new TypeException(
                        "'not' requires bool operand."
                    );
                }

                this.type = BasicType.Bool;
                break;

            case HEAD:
                // head(list[t]) -> t
                if (!(exprType instanceof ListType)) {
                    throw new TypeException(
                        "'head' requires list operand."
                    );
                }

                this.type = ((ListType) exprType).getElementType();
                break;

            case TAIL:
                // tail(list[t]) -> list[t]
                if (!(exprType instanceof ListType)) {
                    throw new TypeException(
                        "'tail' requires list operand."
                    );
                }

                this.type = exprType;
                break;

            case NILQ:
                // nil?(list[t]) -> bool
                if (!(exprType instanceof ListType)) {
                    throw new TypeException(
                        "'nil?' requires list operand."
                    );
                }

                this.type = BasicType.Bool;
                break;

            default:
                this.type = BasicType.Error;
        }
    }
}