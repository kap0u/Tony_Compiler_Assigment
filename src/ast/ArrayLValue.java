package ast;

import symbol.*;
import types.*;
import errors.*;
import java.util.*;

public class ArrayLValue extends LValue {

    private LValue array;
    private Expr index;

    public ArrayLValue(LValue array, Expr index) {
        this.array = array;
        this.index = index;
    }

    @Override
    public void sem(SymbolTable symbols) throws SemanticException {
        array.sem(symbols);
        index.sem(symbols);

        Type arrayType = array.getType();
        Type indexType = index.getType();

        // o deikths pinaka prepei na einai int
        if (!indexType.equals(BasicType.Int)) {
            throw new TypeException("Array index must be of type int.");
        }

        // to aristero meros prepei na einai pinakas
        if (!(arrayType instanceof ArrayType)) {
            throw new TypeException("Array access requires array type.");
        }

        // an exoume int[] tote to a[i] exei typo int
        this.type = ((ArrayType) arrayType).getElementType();
    }
}