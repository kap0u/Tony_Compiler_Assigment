package ast;

import codegen.*;
import symbol.*;
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

    //prosorina paragei programma poy typwnei 5
    public String generateJasmin() {
        JasminWriter out = new JasminWriter();
        CodeGenContext ctx = new CodeGenContext();

        out.emitRaw(".class public TonyProgram");
        out.emitRaw(".super java/lang/Object");
        out.emitRaw("");

        for (FuncDef f : functions) {
            if (f.getName().equals("main")) {
                f.generateJasmin(out, ctx);
            }
        }

        return out.getCode();
    }

}