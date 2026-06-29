package ast;

import codegen.*;
import symbol.*;
import types.*;
import errors.*;
import java.util.*;

public class BoolConst extends Expr {

    private boolean value;

    public BoolConst(boolean value) {
        this.value = value;
    }

    @Override
    public void sem(SymbolTable symbols) throws SemanticException {
        // true / false -> bool
        this.type = BasicType.Bool;
    }


    @Override
    public void generateJasmin(JasminWriter out, CodeGenContext ctx) {
        /*
        * Η JVM αναπαριστά boolean τιμές ως 0/1 πάνω στην operand stack.
        * false -> 0
        * true  -> 1
        */
        if (value) {
            out.emit("iconst_1");
        } else {
            out.emit("iconst_0");
        }
    }
}