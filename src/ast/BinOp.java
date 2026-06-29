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
        
         //Oi diadikes times sthn JVM leitoyrgoyn me operand stack .
         //Paradeigma gia 2 + 3: iconst_2 iconst_3 iadd  prwta mpainei to aristero operandd sth stack meta to deji operand kai meta ekteleite h antistoixh praji 
        
        
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

            case EQ:
                emitComparison(out, ctx, "if_icmpeq");
                break;

            case NEQ:
                emitComparison(out, ctx, "if_icmpne");
                break;

            case LT:
                emitComparison(out, ctx, "if_icmplt");
                break;

            case GT:
                emitComparison(out, ctx, "if_icmpgt");
                break;

            case LE:
                emitComparison(out, ctx, "if_icmple");
                break;

            case GE:
                emitComparison(out, ctx, "if_icmpge");
                break;

            default:
                throw new UnsupportedOperationException(
                    "Code generation for operator " + op + " is not implemented yet."
                );
        }
    }

    //paragei kwdika jasmin gia sugkriseis. H JVM sugkrisi me if_icm den afinei moni ths 0/1 sth stack gia ayto ftiaxv labels. An h sugkrisi isxuei vazw 1 sth stack alliws 0 sth stack.
    private void emitComparison(JasminWriter out, CodeGenContext ctx, String instruction) {
        String trueLabel = ctx.newLabel("cmp_true");
        String endLabel = ctx.newLabel("cmp_end");

        out.emit(instruction + " " + trueLabel);

        // false case
        out.emit("iconst_0");
        out.emit("goto " + endLabel);

        // true case
        out.emitRaw(trueLabel + ":");
        out.emit("iconst_1");

        out.emitRaw(endLabel + ":");
    }
    
}