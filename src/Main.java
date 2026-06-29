import java.io.FileReader;
import java_cup.runtime.Symbol;

import ast.*;
import symbol.*;
import errors.*;

public class Main {

    public static void main(String[] args) {
        /*
         * Χρήση:
         *   java Main <source-file>
         *   java Main <source-file> --codegen
         *
         * Χωρίς --codegen γίνεται μόνο parsing + semantic/type checking.
         * Με --codegen παράγεται και το TonyProgram.j για Jasmin.
         */
        if (args.length < 1 || args.length > 2) {
            System.err.println("Usage:");
            System.err.println("  java Main <source-file>");
            System.err.println("  java Main <source-file> --codegen");
            System.exit(1);
        }

        boolean codegenEnabled = args.length == 2 && args[1].equals("--codegen");

        try {
            Lexer lexer = new Lexer(new FileReader(args[0]));
            parser p = new parser(lexer);

            Symbol result = p.parse();

            Program program = (Program) result.value;

            SymbolTable symbols = new SymbolTable();
            program.sem(symbols);

            if (symbols.hasErrors()) {
                System.out.println("Semantic check completed with errors.");
            } else {
                System.out.println("Semantic check OK");
            }

            /*
             * Παραγωγή κώδικα Jasmin μόνο όταν δοθεί το flag --codegen.
             * Έτσι τα official examples τρέχουν κανονικά για semantic/type checking
             * χωρίς να σπάνε επειδή το backend δεν υποστηρίζει ακόμα όλη την Tony.
             */
            if (codegenEnabled) {
                String jasminCode = program.generateJasmin();

                java.nio.file.Files.writeString(
                    java.nio.file.Path.of("TonyProgram.j"),
                    jasminCode
                );

                System.out.println("Generated TonyProgram.j");
            }

            System.out.println("Parse OK");

        } catch (SemanticException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}