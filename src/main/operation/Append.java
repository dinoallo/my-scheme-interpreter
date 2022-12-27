package main.operation;

import main.eval.Eval;
import main.value.Combination;
import main.value.Nil;

public class Append implements Operation{
    @Override
    public Object Do(Object parameters) throws Exception {
        if(parameters instanceof Nil) return new Nil();
        assert parameters instanceof Combination;
        Object list1 = Eval.E(((Combination) parameters).GetFirst());
        Object p2 = ((Combination) parameters).GetSecond();
        Combination resultComp = null;
        Combination currentComp = null;
        if(list1 instanceof Nil) return Do(p2);
        while(true) {
            if(list1 instanceof Nil) break;
            else if(!(list1 instanceof Combination)) throw new Exception("ill-form lists!");
            Object item =  ((Combination) list1).GetFirst();
            if(currentComp == null){
                resultComp = new Combination(item, new Nil());
                currentComp = resultComp;
            } else {
                Combination newComp = new Combination(item, new Nil());
                currentComp.SetSecond(newComp);
                currentComp = newComp;
            }
            list1 = ((Combination) list1).GetSecond();
        }
        Object list2 = Do(p2);
        // System.out.println(list2);
        //Object list2 = Eval.E(((Combination) p2).GetFirst());
        assert list2 instanceof Combination;

        // if(currentComp == null) return list2;
        // assert currentComp != null;
        currentComp.SetSecond(list2);
        return resultComp;
    }
}
