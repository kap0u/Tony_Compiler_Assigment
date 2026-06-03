package types;

import java.util.*;


public class FuncType extends Type {

    private ArrayList<Type> paramTypes;
    private ArrayList<Boolean> refParams;
    private Type returnType;

    public FuncType(ArrayList<Type> paramTypes, ArrayList<Boolean> refParams, Type returnType) {
        this.paramTypes = paramTypes;
        this.refParams = refParams;
        this.returnType = returnType;
    }

    public ArrayList<Type> getParamTypes() {
        return paramTypes;
    }

    public ArrayList<Boolean> getRefParams() {
        return refParams;
    }

    public Type getReturnType() {
        return returnType;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof FuncType)) {
            return false;
        }

        FuncType that = (FuncType) other;

        if (!this.returnType.equals(that.returnType)) {
            return false;
        }

        if (this.paramTypes.size() != that.paramTypes.size()) {
            return false;
        }

        for (int i = 0; i < paramTypes.size(); i++) {
            if (!this.paramTypes.get(i).equals(that.paramTypes.get(i))) {
                return false;
            }

            if (!this.refParams.get(i).equals(that.refParams.get(i))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return "function";
    }
}