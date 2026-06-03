package ast;

import symbol.*;
import types.*;
import errors.*;
import java.util.*;

import java.util.*;

public class ForStmt extends Stmt {

    private ArrayList<Stmt> initStatements;
    private Expr condition;
    private ArrayList<Stmt> stepStatements;
    private BlockStmt body;

    public ForStmt(
        ArrayList<Stmt> initStatements,
        Expr condition,
        ArrayList<Stmt> stepStatements,
        BlockStmt body
    ) {
        this.initStatements = initStatements;
        this.condition = condition;
        this.stepStatements = stepStatements;
        this.body = body;
    }

    @Override
    public void sem(SymbolTable symbols) throws SemanticException {
        for (Stmt stmt : initStatements) {
            stmt.sem(symbols);
        }

        condition.sem(symbols);

        if (!condition.getType().equals(BasicType.Bool) && !condition.getType().isError()) {
            throw new TypeException("For condition must be of type bool.");
        }

        body.sem(symbols);

        for (Stmt stmt : stepStatements) {
            stmt.sem(symbols);
        }
    }
}