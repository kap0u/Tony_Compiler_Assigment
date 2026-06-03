package ast;

import symbol.*;
import types.*;
import errors.*;
import java.util.*;

public class VarLValue extends LValue {

    private String name;

    public VarLValue(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void sem(SymbolTable symbols) throws SemanticException {
        // psaxnoume th metavliti/parametro sto symbol table
        SymbolInfo info = symbols.lookup(name);

        if (info == null) {
            this.type = BasicType.Error;
        } else {
            this.type = TypeConverter.fromString(info.type);
        }
    }
}