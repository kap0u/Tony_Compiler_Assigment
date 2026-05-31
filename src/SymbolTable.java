import java.util.*;

public class SymbolTable {
    // kathe scope apothikevetai se stack
    private Stack<Map<String, SymbolInfo>> scopes = new Stack<>();
    private boolean hasErrors = false;

    public SymbolTable() {
        
        enterScope(); 

        // Built-in functions ths Tony
        declare("puts", new SymbolInfo("puts", "function", "void", false));
        declare("puti", new SymbolInfo("puti", "function", "void", false));
        declare("geti", new SymbolInfo("geti", "function", "int", false));
        declare("strlen", new SymbolInfo("strlen", "function", "int", false));
    }

    // dimiourgia neou scope gia function h nested block
    public void enterScope() {
        scopes.push(new HashMap<>());
    }

    // afairei to current scope otan teleiwnei i function
    public void exitScope() {
        if (!scopes.isEmpty()) {
            scopes.pop();
        }
    }

   
    public void declare(String name, SymbolInfo info) {
        Map<String, SymbolInfo> currentScope = scopes.peek(); // pairnoume to current scope


        // elegxos gia duplicate declaration sto idio scope
        if (currentScope.containsKey(name)) { 
            error("Symbol '" + name + "' already declared in this scope.");
        } else {
            
            currentScope.put(name, info); // eisagogi neou symbol sto current scope
        }
    }

    public SymbolInfo lookup(String name) {
        // psaxnei apo to eswteriko scope pros ta ekswterika
        for (int i = scopes.size() - 1; i >= 0; i--) {
            Map<String, SymbolInfo> scope = scopes.get(i);

            if (scope.containsKey(name)) {
                // an vrethei to symbol epistrefei to info
                return scope.get(name); 
            }
        }

        error("Symbol '" + name + "' has not been declared."); 
        return null;
    }

    public void error(String message) {
        hasErrors = true;
        System.err.println("Semantic error: " + message); // undeclared variable/function
    }

    public boolean hasErrors() {
        return hasErrors;
    }
}