package main.operation;

import main.eval.Eval;
import main.operation.Operation;
import main.value.Bool;
import main.value.Checker;
import main.value.Combination;
import main.value.Nil;

import java.util.Objects;

public class Cond implements Operation {
    @Override
    public Object Do(Object parameters) throws Exception {

        if(parameters instanceof Combination){
            Object s1 = ((Combination) parameters).GetFirst();
            assert s1 instanceof Combination;
            Object pred1 = ((Combination) s1).GetFirst();

            if(pred1 == "else") {
                Object s2 = ((Combination) s1).GetSecond();
                if(s2 instanceof Nil) return new Bool(Boolean.TRUE);
                else {
                    assert s2 instanceof Combination;
                    //Object se1 = ((Combination) s2).GetFirst();
                    //return Eval.E(se1);
                    return Eval.E(s2);
                }
            }
            Object e1 = Eval.E(pred1);

            if(Bool.IsTrue(e1)) {
                Object s2 = ((Combination) s1).GetSecond();
                if(s2 instanceof Nil) return e1;
                else {
                    assert s2 instanceof Combination;
                    //Object se1 = ((Combination) s2).GetFirst();
                    //return Eval.E(se1);
                    //System.out.println(Eval.E(s2));
                    return Eval.E(s2);
                }
            }
            Object p2 = ((Combination) parameters).GetSecond();
            assert p2 instanceof Combination;
            return Do(p2);

        } else if(parameters instanceof Nil){
            return "";
        }
        else throw new Exception("Wrong input!");
    }
}
