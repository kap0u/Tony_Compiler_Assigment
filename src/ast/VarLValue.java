package ast;

import codegen.*;
import symbol.*;
import types.*;
import errors.*;


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

    @Override
    public void generateJasmin(JasminWriter out, CodeGenContext ctx) {

        //diavazei th metavlith apo to local slot
        out.emit("iload " + ctx.getLocal(name));
    }

    @Override
    public void generateStoreJasmin(JasminWriter out, CodeGenContext ctx) {
        
        //apo8ikevei sth metavlith thn timi poy vrisketai panv sthn stack 
        out.emit("istore " + ctx.getLocal(name));
    }
}