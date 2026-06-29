package ast;

import codegen.*;
import symbol.*;
import types.*;
import errors.*;
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

    @Override
    public void generateJasmin(JasminWriter out, CodeGenContext ctx) {
        
        //Παραγωγή Jasmin για for loop.
       
        

        String startLabel = ctx.newLabel("for_start");
        String endLabel = ctx.newLabel("for_end");

        /*
        * Αρχικές εντολές του for, π.χ. i := 0
        */
        for (Stmt stmt : initStatements) {
            stmt.generateJasmin(out, ctx);
        }

        /*
        * Αρχή του loop.
        */
        out.emitRaw(startLabel + ":");

        /*
        * Η condition αφήνει 0 ή 1 πάνω στη JVM stack.
        * Αν είναι 0, βγαίνουμε από το loop.
        */
        condition.generateJasmin(out, ctx);
        out.emit("ifeq " + endLabel);

        /*
        * Σώμα του for.
        */
        body.generateJasmin(out, ctx);

        /*
        * Βήμα του for, π.χ. i := i + 1
        */
        for (Stmt stmt : stepStatements) {
            stmt.generateJasmin(out, ctx);
        }

        /*
        * Επιστροφή στην αρχή.
        */
        out.emit("goto " + startLabel);

        /*
        * Τέλος loop.
        */
        out.emitRaw(endLabel + ":");
    }
}