package ast;

import codegen.*;
import symbol.*;
import types.*;
import errors.*;
import java.util.*;


public class IfStmt extends Stmt {

    private Expr condition;
    private BlockStmt thenBlock;
    private ArrayList<Expr> elsifConditions;
    private ArrayList<BlockStmt> elsifBlocks;
    private BlockStmt elseBlock;

    public IfStmt(
        Expr condition,
        BlockStmt thenBlock,
        ArrayList<Expr> elsifConditions,
        ArrayList<BlockStmt> elsifBlocks,
        BlockStmt elseBlock
    ) {
        this.condition = condition;
        this.thenBlock = thenBlock;
        this.elsifConditions = elsifConditions;
        this.elsifBlocks = elsifBlocks;
        this.elseBlock = elseBlock;
    }

    @Override
    public void sem(SymbolTable symbols) throws SemanticException {
        condition.sem(symbols);

        if (!condition.getType().equals(BasicType.Bool) && !condition.getType().isError()) {
            throw new TypeException("If condition must be of type bool.");
        }

        thenBlock.sem(symbols);

        for (int i = 0; i < elsifConditions.size(); i++) {
            Expr elsifCond = elsifConditions.get(i);
            elsifCond.sem(symbols);

            if (!elsifCond.getType().equals(BasicType.Bool) && !elsifCond.getType().isError()) {
                throw new TypeException("Elsif condition must be of type bool.");
            }

            elsifBlocks.get(i).sem(symbols);
        }

        if (elseBlock != null) {
            elseBlock.sem(symbols);
        }
    }

    @Override
    public void generateJasmin(JasminWriter out, CodeGenContext ctx) {
        /*
        * To condition afinei 0 h 1 panw sth jvm operand stack 
        * ifeq shmainei: an h timh einai 0 phgaine sto else Label 
        */
        String elseLabel = ctx.newLabel("if_else");
        String endLabel = ctx.newLabel("if_end");

        condition.generateJasmin(out, ctx);
        out.emit("ifeq " + elseLabel);

        /*
        * Then block.
        */
        thenBlock.generateJasmin(out, ctx);
        out.emit("goto " + endLabel);

        /*
        * Else block.
        */
        out.emitRaw(elseLabel + ":");

        if (elseBlock != null) {
            elseBlock.generateJasmin(out, ctx);
        }

        out.emitRaw(endLabel + ":");
    }
}