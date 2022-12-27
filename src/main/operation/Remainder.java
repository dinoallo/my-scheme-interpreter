package main.operation;

import main.eval.Eval;
import main.operation.operator.Quote;
import main.value.Combination;

public class Remainder implements Operation {
    @Override
    public Object Do(Object parameters) throws Exception {
        //System.out.println(parameters);
        assert parameters instanceof Combination;
        Object dividend = Eval.E(((Combination) parameters).GetFirst());
        Object p2 = ((Combination) parameters).GetSecond();
        assert p2 instanceof Combination;
        Object divisor = Eval.E(((Combination) p2).GetFirst());
        assert dividend instanceof Integer;
        assert divisor instanceof Integer;
        return (Integer) dividend % (Integer) divisor;
    }
}
