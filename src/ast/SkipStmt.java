package ast;

import symbol.*;
import types.*;
import errors.*;
import java.util.*;

public class SkipStmt extends Stmt {

    @Override
    public void sem(SymbolTable symbols) throws SemanticException {
        // skip: den kanei tipota
    }
}