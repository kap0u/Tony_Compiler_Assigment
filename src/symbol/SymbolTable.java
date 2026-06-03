package symbol;

import java.util.*;



public class SymbolTable {
    // kathe scope apothikevetai se stack
    private Stack<Map<String, SymbolInfo>> scopes = new Stack<>();
    private boolean hasErrors = false;

    public SymbolTable() {
        enterScope(); // global scope
        initBuiltins();
  
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

   private void initBuiltins() {
        // output functions
        declareBuiltin("puti", "void", 1);
        declareBuiltin("putb", "void", 1);
        declareBuiltin("putc", "void", 1);
        declareBuiltin("puts", "void", 1);

        // input functions
        declareBuiltin("geti", "int", 0);
        declareBuiltin("getb", "bool", 0);
        declareBuiltin("getc", "char", 0);
        declareBuiltin("gets", "void", 2);

        // conversion functions
        declareBuiltin("abs", "int", 1);
        declareBuiltin("ord", "int", 1);
        declareBuiltin("chr", "char", 1);

        // string functions
        declareBuiltin("strlen", "int", 1);
        declareBuiltin("strcmp", "int", 2);
        declareBuiltin("strcpy", "void", 2);
        declareBuiltin("strcat", "void", 2);
    }

    private void declareBuiltin(String name, String returnType, int paramCount) {
        SymbolInfo info = new SymbolInfo(
            name,
            returnType,
            new java.util.ArrayList<String>()
        );

        info.isFunction = true;
        info.paramCount = paramCount;

        declare(name, info);
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