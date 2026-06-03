package ast;

import symbol.*;
import types.*;
import errors.*;
import java.util.*;

public abstract class ASTNode {

    // H sem tha kaleitai gia semantic analysis / type checking
    public abstract void sem(SymbolTable symbols) throws SemanticException;
}