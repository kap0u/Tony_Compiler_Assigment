package ast;

import codegen.*;
import symbol.*;
import types.*;
import errors.*;


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

    @Override
    public void generateJasmin(JasminWriter out, CodeGenContext ctx) {
        if (value == -1) {
            out.emit("iconst_m1");
        } else if (value >= 0 && value <= 5) {
            out.emit("iconst_" + value);
        } else if (value >= -128 && value <= 127) {
            out.emit("bipush " + value);
        } else if (value >= -32768 && value <= 32767) {
            out.emit("sipush " + value);
        } else {
            out.emit("ldc " + value);
        }
    }
}