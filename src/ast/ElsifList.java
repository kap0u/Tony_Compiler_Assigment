package ast;

import java.util.*;

public class ElsifList {

    private ArrayList<Expr> conditions;
    private ArrayList<BlockStmt> blocks;

    public ElsifList() {
        this.conditions = new ArrayList<Expr>();
        this.blocks = new ArrayList<BlockStmt>();
    }

    public void add(Expr condition, BlockStmt block) {
        conditions.add(condition);
        blocks.add(block);
    }

    public ArrayList<Expr> getConditions() {
        return conditions;
    }

    public ArrayList<BlockStmt> getBlocks() {
        return blocks;
    }
}