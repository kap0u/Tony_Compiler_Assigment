package types;


public abstract class Type {

    public boolean isError() {
        return this == BasicType.Error;
    }

    public abstract boolean equals(Object other);
}