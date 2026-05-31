
import java.io.*;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Main <source-file>");
            return;
        }

        try {
            Reader r = new FileReader(args[0]);
            Lexer l = new Lexer(r);
            parser p = new parser(l);
            p.parse();
            p.checkSemanticResult();
            System.out.println("Parse OK");
            r.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}