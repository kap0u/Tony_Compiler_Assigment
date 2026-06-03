package types;

import java.util.*;

public class TypeConverter {

    public static Type fromString(String type) {
        if (type == null) {
            return BasicType.Error;
        }

        if (type.equals("int")) {
            return BasicType.Int;
        }

        if (type.equals("bool")) {
            return BasicType.Bool;
        }

        if (type.equals("char")) {
            return BasicType.Char;
        }

        if (type.equals("void") || type.equals("function")) {
            return BasicType.Void;
        }

        if (type.endsWith("[]")) {
            String base = type.substring(0, type.length() - 2);
            return new ArrayType(fromString(base));
        }

        if (type.startsWith("list[") && type.endsWith("]")) {
            String inner = type.substring(5, type.length() - 1);
            return new ListType(fromString(inner));
        }

        return BasicType.Error;
    }
}