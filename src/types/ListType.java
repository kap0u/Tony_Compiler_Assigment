package types;



public class ListType extends Type {

    private Type elementType;

    public ListType(Type elementType) {
        this.elementType = elementType;
    }

    public Type getElementType() {
        return elementType;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ListType)) {
            return false;
        }

        ListType that = (ListType) other;
        return this.elementType.equals(that.elementType);
    }

    @Override
    public String toString() {
        return "list[" + elementType.toString() + "]";
    }
}