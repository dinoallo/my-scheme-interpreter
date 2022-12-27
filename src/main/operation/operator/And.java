package main.operation.operator;

import main.eval.Eval;
import main.operation.Operation;
import main.value.Bool;
import main.value.Combination;
import main.value.Nil;

public class And implements Operation {
    @Override
    public Object Do(Object parameters) throws Exception {
        if(parameters instanceof Nil) return new Bool(Boolean.TRUE);
        else if(parameters instanceof Combination){
            Object e1 = Eval.E(((Combination) parameters).GetFirst());
            Object e2 = ((Combination) parameters).GetSecond();
            if(!Bool.IsTrue(e1)) return new Bool(Boolean.FALSE);
            else if(e2 instanceof Nil){
                return e1;
            } else return Do(e2);
        } else throw new Exception("Wrong input!");
    }
}
