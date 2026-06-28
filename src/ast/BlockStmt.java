package ast;

import codegen.*;
import symbol.*;
import types.*;
import errors.*;
import java.util.*;



public class BlockStmt extends Stmt {

    private ArrayList<Stmt> statements;

    public BlockStmt(ArrayList<Stmt> statements) {
        this.statements = statements;
    }

    @Override
    public void sem(SymbolTable symbols) throws SemanticException {
        // elegxos olwn twn entolwn me th seira
        for (Stmt stmt : statements) {
            stmt.sem(symbols);
        }
    }
    
    @Override
    public void generateJasmin(JasminWriter out, CodeGenContext ctx) {
        for (Stmt stmt : statements) {
            stmt.generateJasmin(out , ctx);
        }
    }
}