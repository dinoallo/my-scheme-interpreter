package main.operation.operator;

import main.value.Checker;

import java.util.Objects;

public class Util {
    public static Integer ConvertScalarToInteger(String scalar) {
        // TODO: check if it's a valid number
        if (Objects.equals(scalar, "")) return null;
        if (scalar.startsWith("\"")) return Integer.valueOf(scalar.substring(1));
        else return Integer.valueOf(scalar);
    }

    public static Double ConvertScalarToDouble(String scalar) {
        // TODO: check if it's a valid number
        if (Objects.equals(scalar, "")) return null;
        if (scalar.startsWith("\"")) return Double.valueOf(scalar.substring(1));
        else return Double.valueOf(scalar);
    }

    public static Boolean ConvertScalarToBoolean(String scalar) throws Exception {
        if(Objects.equals(scalar, "")) return null;
        String s = scalar.startsWith("\"") ? scalar.substring(1) : scalar;
        if(Checker.IsValidBool(s)){
            return Boolean.valueOf(scalar);
        } else {
            throw new Exception("The scalar is not a valid bool");
        }
    }
}
