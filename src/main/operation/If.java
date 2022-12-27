package main.operation;

import main.eval.Eval;
import main.operation.operator.Util;
import main.value.Bool;
import main.value.Combination;

import java.util.Objects;

public class If implements Operation {
    @Override
    public Object Do(Object parameters) throws Exception{
        if(parameters instanceof Combination) {
            Object o1 = ((Combination) parameters).GetFirst();
            Object e1 = Eval.E(o1);
            Object p2 = ((Combination) parameters).GetSecond();
            assert p2 instanceof Combination;
            Object o2 = ((Combination) p2).GetFirst();
            Object p3 = ((Combination) p2).GetSecond();
            assert p3 instanceof Combination;
            Object o3 = ((Combination) p3).GetFirst();
            if(Bool.IsTrue(e1)) return Eval.E(o2);
            else return Eval.E(o3);
        } else throw new Exception("Invalid input!");
    }
}
