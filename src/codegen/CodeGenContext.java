package codegen;

import java.util.HashMap;
import java.util.Map;

public class CodeGenContext {
    private int labelCounter = 0;

    private final Map<String, Integer> locals = new HashMap<>();

    // slot 0 είναι το String[] args της Java main
    private int nextLocal = 1;

    public String newLabel(String prefix) {
        return prefix + "_" + (labelCounter++);
    }

    public int declareLocal(String name) {
        if (locals.containsKey(name)) {
            return locals.get(name);
        }

        int slot = nextLocal++;
        locals.put(name, slot);
        return slot;
    }

    public int getLocal(String name) {
        Integer slot = locals.get(name);

        if (slot == null) {
            throw new RuntimeException("Unknown local variable in codegen: " + name);
        }

        return slot;
    }
}