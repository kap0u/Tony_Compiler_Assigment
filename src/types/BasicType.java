package types;

import java.util.*;


public class BasicType extends Type {

    private enum BasicTypeEnum {
        INT,
        BOOL,
        CHAR,
        VOID,
        NIL,
        ERROR
    }

    public static final BasicType Int = new BasicType(BasicTypeEnum.INT);
    public static final BasicType Bool = new BasicType(BasicTypeEnum.BOOL);
    public static final BasicType Char = new BasicType(BasicTypeEnum.CHAR);
    public static final BasicType Void = new BasicType(BasicTypeEnum.VOID);
    public static final BasicType Nil = new BasicType(BasicTypeEnum.NIL);
    public static final BasicType Error = new BasicType(BasicTypeEnum.ERROR);

    private BasicTypeEnum type;

    private BasicType(BasicTypeEnum type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof BasicType)) {
            return false;
        }

        BasicType that = (BasicType) other;
        return this.type == that.type;
    }

    @Override
    public String toString() {
        return type.toString().toLowerCase();
    }
}