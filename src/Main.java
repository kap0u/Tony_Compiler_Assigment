import java.io.FileReader;
import java_cup.runtime.Symbol;

import ast.*;
import symbol.*;
import errors.*;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <source-file>");
            System.exit(1);
        }

        try {
            Lexer lexer = new Lexer(new FileReader(args[0]));
            parser p = new parser(lexer);

            Symbol result = p.parse();

            Program program = (Program) result.value;

            SymbolTable symbols = new SymbolTable();
            program.sem(symbols);

            String jasminCode = program.generateJasmin();

            //jasmin
            java.nio.file.Files.writeString(
                java.nio.file.Path.of("TonyProgram.j"),
                jasminCode
            );

            System.out.println("Generated TonyProgram.j"); //jasmin

            if (symbols.hasErrors()) {
                System.out.println("Semantic check completed with errors.");
            } else {
                System.out.println("Semantic check OK");
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