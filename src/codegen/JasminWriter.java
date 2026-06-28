package codegen;

public class JasminWriter {
    private final StringBuilder sb = new StringBuilder();
    private int indent = 0;

    public void emit(String line) {
        for (int i = 0; i < indent; i++) {
            sb.append("    ");
        }
        sb.append(line).append("\n");
    }

    public void emitRaw(String line) {
        sb.append(line).append("\n");
    }

    public void indent() {
        indent++;
    }

    public void dedent() {
        if (indent > 0) {
            indent--;
        }
    }

    public String getCode() {
        return sb.toString();
    }
}