package ast;

import codegen.*;
import symbol.*;
import types.*;
import errors.*;
import java.util.*;

public abstract class Stmt extends ASTNode {
    
    // Base class gia oles tis entoles
    public void generateJasmin(JasminWriter out, CodeGenContext ctx) {
    throw new UnsupportedOperationException(
        "Code generation not implemented for " + getClass().getSimpleName()
    );
}
}