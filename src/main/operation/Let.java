package main.operation;

import main.eval.Eval;
import main.frame.Frame;
import main.value.Combination;
import main.value.Nil;


public class Let implements Operation {

    @Override
    public Object Do(Object parameters) throws Exception {
        assert parameters instanceof Combination;
        Object bindings = ((Combination) parameters).GetFirst();
        Object body = ((Combination) parameters).GetSecond();
        assert bindings instanceof Combination;
        Frame localEnv = new Frame(Eval.CurrentEvalFrame);


        while(true) {

            Object b = ((Combination) bindings).GetFirst();
            assert b instanceof Combination;
            Object s = ((Combination) b).GetFirst();
            Object scdr = ((Combination) b).GetSecond();
            assert scdr instanceof Combination;
            Object v = Eval.E(((Combination) scdr).GetFirst());
            Operation op = null;
            if(v instanceof Operation) op = (Operation) v;
            localEnv.AddSymbol((String) s, op, v);
            bindings = ((Combination) bindings).GetSecond();
            if(bindings instanceof Nil) break;
            else if(!(bindings instanceof Combination)) {
                throw new Exception("ill-formed binding list!");
            }
        }

        Frame prevFrame = Eval.CurrentEvalFrame;
        Eval.CurrentEvalFrame = localEnv;
        Object result = Eval.E(body);
        Eval.CurrentEvalFrame = prevFrame;
        return result;
    }
}
