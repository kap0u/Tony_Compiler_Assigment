package ast;

import symbol.*;
import types.*;
import errors.*;
import java.util.*;

public class ExitStmt extends Stmt {

    @Override
    public void sem(SymbolTable symbols) throws SemanticException {
        // exit: pros to paron den xreiazetai type checking
        // argotera tha elegxoume oti einai mesa se void function
    }
}