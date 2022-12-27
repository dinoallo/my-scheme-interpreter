package main.operation.operator;

import main.eval.Eval;
import main.operation.Operation;
import main.value.Bool;
import main.value.Checker;
import main.value.Combination;

public class Lt implements Operation {
    @Override
    public Object Do(Object parameters) throws Exception {
        if(parameters instanceof Combination){
            Object o1 = Eval.E(((Combination) parameters).GetFirst());
            Object p2 = ((Combination) parameters).GetSecond();
            assert p2 instanceof Combination;
            Object o2 = ((Combination) p2).GetFirst();
            Object e1 = Eval.E(o1);
            Object e2 = Eval.E(o2);
            if(e1 instanceof Number && e2 instanceof Number) {
                double d1, d2;
                d1 = ((Number) e1).doubleValue();
                d2 = ((Number) e2).doubleValue();
                if(d1 < d2) return new Bool(Boolean.TRUE);
                else return new Bool(Boolean.FALSE);
            }
            else throw new Exception("TypeError. Can't compare!");
        } else throw new Exception("Wrong input!");
    }
}
