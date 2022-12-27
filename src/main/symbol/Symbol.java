package main.symbol;

import main.operation.Operation;

import java.util.HashSet;
import java.util.Set;

public class Symbol {
    private final String name;
    private Operation operation;
    //private String value;
    private Object value;
    private static final Set<String> nameSet = new HashSet<>();

    public Symbol(String name, Operation operation, Object value) {
        this.name = name;
        if (nameSet.contains(name)) {
            // throw nameExist error!
        } else {
            nameSet.add(name);
        }
        if (operation != null) this.operation = operation;
        if (value != null) this.value = value;
    }

    public Object DoIfOperation(Object parameters) throws Exception {
        if (operation != null) {
            return operation.Do(parameters);
        } else return null;
    }
    public boolean IsOperation() {
        return this.operation != null;
    }

    public String Quote() {
        return name;
    }

    public String Thing() {
        return value.toString();
    }

    public Object GetValue() {
        return value;
    }



    public Operation GetOp() {
        return operation != null ? operation : null;
    }

}
