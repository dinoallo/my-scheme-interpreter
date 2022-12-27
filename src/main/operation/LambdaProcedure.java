package main.operation;

import main.frame.Frame;
import main.eval.Eval;
import main.value.Combination;
import main.value.Nil;

public class LambdaProcedure implements Operation {
    private final Combination formals; // parameter names
    private final Combination body; // expressioins to evaluate
    private final Frame env;

    public LambdaProcedure(Combination formals, Combination body, Frame frame){
        this.formals = formals;
        this.body = body;
        this.env = frame;
    }

    public Object Do(Object _argments) throws Exception {
        Frame localFrame = new Frame(env);
        assert _argments instanceof Combination && formals != null;
        Object argments = _argments;
        Object parameters = formals;
        while(true){

            if((parameters instanceof Nil && (! (argments instanceof Nil))) || (argments instanceof Nil && (! (parameters instanceof Nil)))) throw new Exception("Parameters and argments not matched!!! please try again!");
            if(parameters instanceof Nil) break;
            Object argment = ((Combination) argments).GetFirst();
            Object formal = ((Combination) parameters).GetFirst();
            Object _a = Eval.E(argment);
            Operation op = null;
            if(_a instanceof Operation) op = (Operation) _a;
            assert _a != null;
            localFrame.AddSymbol(formal.toString(), op, _a);
            argments = ((Combination) argments).GetSecond();
            parameters = ((Combination) parameters).GetSecond();
        }
        Frame prevEvalFrame = Eval.CurrentEvalFrame;
        Eval.CurrentEvalFrame = localFrame;
        //System.out.println(body);
        Object result = Eval.E(body);
        Eval.CurrentEvalFrame = prevEvalFrame;
        return result;
    }

    public String toString() {
        return "Lambda: " + "p: " + formals.toString() + " b: " + body.toString();
    }
}
