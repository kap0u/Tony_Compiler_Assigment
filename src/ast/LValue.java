package ast;

import codegen.*;
import symbol.*;
import types.*;
import errors.*;
import java.util.*;

public abstract class LValue extends Expr {
    // Base class gia ola ta l-values
    // Einai Expr giati ena l-value mporei na xrhsimopoihthei kai ws expression
    
    public void generateStoreJasmin(JasminWriter out, CodeGenContext ctx) {
    throw new UnsupportedOperationException(
        "Store code generation not implemented for " + getClass().getSimpleName()
    );
}
}