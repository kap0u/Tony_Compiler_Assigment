package ast;

import codegen.*;
import symbol.*;
import types.*;
import errors.*;
import java.util.*;

public class FuncDef extends ASTNode {

    private String name;
    private Type returnType;
    private ArrayList<Param> params;
    private ArrayList<VarDecl> varDecls;
    private ArrayList<FuncDef> nestedFunctions;
    private BlockStmt body;

    public FuncDef(
        String name,
        Type returnType,
        ArrayList<Param> params,
        ArrayList<VarDecl> varDecls,
        ArrayList<FuncDef> nestedFunctions,
        BlockStmt body
    ) {
        this.name = name;
        this.returnType = returnType;
        this.params = params;
        this.varDecls = varDecls;
        this.nestedFunctions = nestedFunctions;
        this.body = body;
    }

    @Override
    public void sem(SymbolTable symbols) throws SemanticException {

        // dhlwsi function sto current scope
        SymbolInfo funcInfo = new SymbolInfo(
            name,
            "function",
            returnType.toString(),
            false
        );

        funcInfo.isFunction = true;

        int count = 0;
        for (Param p : params) {
            count += p.count();
        }
        funcInfo.paramCount = count;

        symbols.declare(name, funcInfo);

        // anoigma scope gia params, local vars kai nested functions
        symbols.enterScope();

        // dhlwsi parametrwn sto function scope
        for (Param p : params) {
            p.sem(symbols);
        }

        // dhlwsi local metavlitwn sto function scope
        for (VarDecl v : varDecls) {
            v.sem(symbols);
        }

        // dhlwsi/elegxos nested functions
        for (FuncDef f : nestedFunctions) {
            f.sem(symbols);
        }

        // elegxos entolwn
        if (body != null) {
            body.sem(symbols);
        }

        // kleisimo scope
        symbols.exitScope();
    }

    public Type getReturnType() {
        return returnType;
    }

    public String getName() {
        return name;
    }

    public void generateJasmin(JasminWriter out, CodeGenContext ctx) {
        if (!name.equals("main")) {
            return;
        }

        out.emitRaw(".method public static main([Ljava/lang/String;)V");
        out.emit(".limit stack 50");
        out.emit(".limit locals 50");
        out.emit("");

        for (VarDecl varDecl : varDecls) {
            varDecl.generateJasmin(out, ctx);
        }
        
        body.generateJasmin(out, ctx);

        out.emit("");
        out.emit("return");
        out.emitRaw(".end method");
    }
}