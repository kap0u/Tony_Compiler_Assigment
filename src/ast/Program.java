package ast;

import symbol.*;
import types.*;
import errors.*;
import java.util.*;

public class Program extends ASTNode {

    private ArrayList<FuncDef> functions;

    public Program(ArrayList<FuncDef> functions) {
        this.functions = functions;
    }

    @Override
    public void sem(SymbolTable symbols) throws SemanticException {
        // To programma periexei mia h perissoteres top-level functions
        for (FuncDef f : functions) {
            f.sem(symbols);
        }
    }
}