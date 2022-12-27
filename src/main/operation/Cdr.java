package main.operation;

import main.eval.Eval;
import main.value.Combination;
import main.value.Nil;

public class Cdr implements Operation {
    public Object Do(Object parameters) throws Exception {
        assert parameters instanceof Combination;
        // System.out.println(parameters);
        Object _p = ((Combination) parameters).GetFirst();
        Object pair = Eval.E(_p);
        //System.out.println("_p:" +  _p);
        //System.out.println("pair:" + pair);
        if(pair instanceof Nil) return new Nil();
        assert pair instanceof Combination;
        return ((Combination) pair).GetSecond();
    }
}
