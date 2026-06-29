package ast;

import codegen.*;
import symbol.*;
import types.*;
import errors.*;
import java.util.*;



public class CallExpr extends Expr {

    private String name;
    private ArrayList<Expr> args;

    public CallExpr(String name, ArrayList<Expr> args) {
        this.name = name;
        this.args = args;
    }

    @Override
    public void sem(SymbolTable symbols) throws SemanticException {
        SymbolInfo info = symbols.lookup(name);

        if (info == null) {
            this.type = BasicType.Error;
            return;
        }

        if (!info.isFunction) {
            throw new TypeException("'" + name + "' is not a function.");
        }

        if (info.paramCount != args.size()) {
            throw new TypeException(
                "Function '" + name + "' expects " +
                info.paramCount + " arguments but got " + args.size() + "."
            );
        }

        for (Expr arg : args) {
            arg.sem(symbols);
        }

        this.type = TypeConverter.fromString(info.type);
    }

    @Override
    public void generateJasmin(JasminWriter out, CodeGenContext ctx) {
        
        if (name.equals("puti")) {
            out.emit("getstatic java/lang/System/out Ljava/io/PrintStream;");
            args.get(0).generateJasmin(out, ctx);
            out.emit("invokevirtual java/io/PrintStream/println(I)V");
            return;
        }

        if (name.equals("putb")) {
            out.emit("getstatic java/lang/System/out Ljava/io/PrintStream;");
            args.get(0).generateJasmin(out, ctx);
            out.emit("invokevirtual java/io/PrintStream/println(Z)V");
            return;
        }

        if (name.equals("putc")) {
            out.emit("getstatic java/lang/System/out Ljava/io/PrintStream;");
            args.get(0).generateJasmin(out, ctx);
            out.emit("invokevirtual java/io/PrintStream/println(C)V");
            return;
        }

        if (name.equals("puts")) {
            out.emit("getstatic java/lang/System/out Ljava/io/PrintStream;");
            args.get(0).generateJasmin(out, ctx);
            out.emit("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
            return;
        }

        throw new UnsupportedOperationException(
            "Code generation for function '" + name + "' is not implemented yet."
        );
}
}