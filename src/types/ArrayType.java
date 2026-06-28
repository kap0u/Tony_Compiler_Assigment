package types;



public class ArrayType extends Type {

    private Type elementType;

    public ArrayType(Type elementType) {
        this.elementType = elementType;
    }

    public Type getElementType() {
        return elementType;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ArrayType)) {
            return false;
        }

        ArrayType that = (ArrayType) other;
        return this.elementType.equals(that.elementType);
    }

    @Override
    public String toString() {
        return elementType.toString() + "[]";
    }
}