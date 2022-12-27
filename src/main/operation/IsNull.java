package main.operation;

import main.eval.Eval;
import main.value.Bool;
import main.value.Combination;
import main.value.Nil;

public class IsNull implements Operation {
    public Object Do(Object parameters) throws Exception {
        assert parameters instanceof Combination;
        Object _arg = ((Combination) parameters).GetFirst();
        Object arg = Eval.E(_arg);

        if(arg instanceof Combination) {
            Length length = new Length();
            Object l = length.Do(new Combination(_arg, new Nil()));
            assert l instanceof Integer;
            if((Integer) l == 0) return new Bool(Boolean.TRUE);
            else return new Bool(Boolean.FALSE);
        } else if(arg instanceof Nil) {
            return new Bool(Boolean.TRUE);
        } else return new Bool(Boolean.FALSE);
    }
}
