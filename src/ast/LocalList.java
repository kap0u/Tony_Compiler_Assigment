package ast;

import java.util.*;

public class LocalList {

    public ArrayList<VarDecl> varDecls;
    public ArrayList<FuncDef> funcDefs;

    public LocalList() {
        this.varDecls = new ArrayList<VarDecl>();
        this.funcDefs = new ArrayList<FuncDef>();
    }

    public void addVarDecl(VarDecl v) {
        varDecls.add(v);
    }

    public void addFuncDef(FuncDef f) {
        funcDefs.add(f);
    }
}