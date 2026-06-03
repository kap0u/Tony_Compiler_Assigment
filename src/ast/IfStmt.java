package ast;

import symbol.*;
import types.*;
import errors.*;
import java.util.*;

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
}