package ast;

import codegen.*;
import symbol.*;
import types.*;
import errors.*;
import java.util.*;

public class StringConst extends Expr {

    private String value;

    public StringConst(String value) {
        this.value = value;
    }

    @Override
    public void sem(SymbolTable symbols) throws SemanticException {
        // string literal -> char[]
        this.type = new ArrayType(BasicType.Char);
    }

    @Override
    public void generateJasmin(JasminWriter out, CodeGenContext ctx) {
        /*
        * Το ldc φορτώνει string constant από το constant pool της JVM.
        * Αν ο lexer/parser έχει κρατήσει τα quotes, τα αφαιρούμε πρώτα.
        */
        String s = value;

        if (s.length() >= 2 && s.startsWith("\"") && s.endsWith("\"")) {
            s = s.substring(1, s.length() - 1);
        }

        /*
        * Escape για χαρακτήρες που μπορεί να χαλάσουν το Jasmin string literal.
        */
        s = s.replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .replace("\n", "\\n")
            .replace("\t", "\\t");

        out.emit("ldc \"" + s + "\"");
    }
}