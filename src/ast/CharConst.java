package ast;

import codegen.*;
import symbol.*;
import types.*;
import errors.*;
import java.util.*;

public class CharConst extends Expr {

    private String value;

    public CharConst(String value) {
        this.value = value;
    }

    @Override
    public void sem(SymbolTable symbols) throws SemanticException {
        // character constant -> char
        this.type = BasicType.Char;
    }

   @Override
public void generateJasmin(JasminWriter out, CodeGenContext ctx) {
        /*
        * Το value είναι String επειδή έτσι έρχεται από τον lexer/parser.
        * Για παραγωγή JVM code χρειαζόμαστε τον ASCII/Unicode κωδικό του χαρακτήρα.
        */
        int code = getCharCode();

        if (code >= -1 && code <= 5) {
            if (code == -1) {
                out.emit("iconst_m1");
            } else {
                out.emit("iconst_" + code);
            }
        } else if (code >= -128 && code <= 127) {
            out.emit("bipush " + code);
        } else if (code >= -32768 && code <= 32767) {
            out.emit("sipush " + code);
        } else {
            out.emit("ldc " + code);
        }
    }

    /*
    * Μετατρέπει το String που κρατάει το CharConst σε αριθμητικό κωδικό χαρακτήρα.
    * Καλύπτει και απλούς χαρακτήρες όπως A αλλά και escaped χαρακτήρες.
    */
    private int getCharCode() {
        String s = value;

        /*
        * Αν ο lexer έχει κρατήσει τα quotes, π.χ. 'A',
        * τα αφαιρούμε ώστε να μείνει μόνο το A.
        */
        if (s.length() >= 2 && s.startsWith("'") && s.endsWith("'")) {
            s = s.substring(1, s.length() - 1);
        }

        switch (s) {
            case "\\n":
                return '\n';
            case "\\t":
                return '\t';
            case "\\r":
                return '\r';
            case "\\0":
                return '\0';
            case "\\'":
                return '\'';
            case "\\\\":
                return '\\';
            default:
                return s.charAt(0);
        }
    }
}