package main.operation.operator;

import main.operation.Operation;

public class Quote implements Operation {
    @Override
    public Object Do(Object parameters) throws Exception {
        return parameters;
    }
}
