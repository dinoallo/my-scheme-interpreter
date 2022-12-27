package main.operation;

import main.eval.Eval;
import main.value.Combination;
import main.value.Nil;

public class Begin implements Operation {
    public Object Do(Object parameters) throws Exception {
        assert parameters instanceof Combination;
        // System.out.println(parameters);
        Object expressions = parameters;
        // assert expressions instanceof Combination;
        Object returnValue = null;
        while(!(expressions instanceof Nil)) {
            assert expressions instanceof Combination;
            // System.out.println(expressions);
            Object e = ((Combination) expressions).GetFirst();
            // System.out.println(e);
            returnValue = Eval.E(e);
            expressions = ((Combination) expressions).GetSecond();
        }
        return returnValue;
    }
}
