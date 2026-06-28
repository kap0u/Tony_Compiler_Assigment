package ast;

import codegen.*;
import symbol.*;
import types.*;
import errors.*;


public class BinOp extends Expr {

    public enum Operator {
        PLUS,
        MINUS,
        TIMES,
        DIV,
        MOD,
        EQ,
        NEQ,
        LT,
        GT,
        LE,
        GE,
        AND,
        OR,
        CONS
    }

    private Operator op;
    private Expr left;
    private Expr right;

    public BinOp(Operator op, Expr left, Expr right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    @Override
    public void sem(SymbolTable symbols) throws SemanticException {
        left.sem(symbols);
        right.sem(symbols);

        Type leftType = left.getType();
        Type rightType = right.getType();

        switch (op) {

            case PLUS:
            case MINUS:
            case TIMES:
            case DIV:
            case MOD:
                // arithmitikes prakseis: int op int -> int
                if (!leftType.equals(BasicType.Int) || !rightType.equals(BasicType.Int)) {
                    throw new TypeException(
                        "Arithmetic operator requires int operands."
                    );
                }
                this.type = BasicType.Int;
                break;

            case LT:
            case GT:
            case LE:
            case GE:
                // sxesiakoi telestes gia vasikous typous
                if (!leftType.equals(rightType)) {
                    throw new TypeException(
                        "Comparison operands must have the same type."
                    );
                }

                if (!(leftType.equals(BasicType.Int) ||
                      leftType.equals(BasicType.Char) ||
                      leftType.equals(BasicType.Bool))) {
                    throw new TypeException(
                        "Comparison requires basic type operands."
                    );
                }

                this.type = BasicType.Bool;
                break;

            case EQ:
            case NEQ:
                // isotita / anisotita me idious typous
                if (!leftType.equals(rightType)) {
                    throw new TypeException(
                        "Equality operands must have the same type."
                    );
                }
                this.type = BasicType.Bool;
                break;

            case AND:
            case OR:
                // logikoi telestes: bool op bool -> bool
                if (!leftType.equals(BasicType.Bool) || !rightType.equals(BasicType.Bool)) {
                    throw new TypeException(
                        "Logical operator requires bool operands."
                    );
                }
                this.type = BasicType.Bool;
                break;

            case CONS:
                // list construction: t # list[t] -> list[t]
                if (!(rightType instanceof ListType)) {
                    throw new TypeException(
                        "Right operand of # must be a list."
                    );
                }

                ListType listType = (ListType) rightType;

                if (!leftType.equals(listType.getElementType())) {
                    throw new TypeException(
                        "Left operand of # must match list element type."
                    );
                }

                this.type = rightType;
                break;

            default:
                this.type = BasicType.Error;
        }
    }

    @Override
    public void generateJasmin(JasminWriter out, CodeGenContext ctx) {
        /*
        * Οι δυαδικές εκφράσεις στη JVM δουλεύουν με operand stack.
        *
        * Παράδειγμα για 2 + 3:
        * iconst_2
        * iconst_3
        * iadd
        *
        * Δηλαδή πρώτα μπαίνει το αριστερό operand στη stack,
        * μετά το δεξί operand, και μετά εκτελείται η αντίστοιχη πράξη.
        */
        left.generateJasmin(out, ctx);
        right.generateJasmin(out, ctx);

        switch (op) {
            case PLUS:
                out.emit("iadd");
                break;

            case MINUS:
                out.emit("isub");
                break;

            case TIMES:
                out.emit("imul");
                break;

            case DIV:
                out.emit("idiv");
                break;

            case MOD:
                out.emit("irem");
                break;

            default:
                throw new UnsupportedOperationException(
                    "Code generation for operator " + op + " is not implemented yet."
                );
        }
    }
    
}