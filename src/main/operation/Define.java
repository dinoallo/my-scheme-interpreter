package main.operation;

import main.eval.Eval;
import main.value.Combination;
import main.value.Nil;

import java.util.Objects;

public class Define implements Operation{
    @Override
    public Object Do(Object parameters) throws Exception {
        String name;
        Operation operation = null;
        Object value = null;
        //System.out.println(parameters);
        if(parameters instanceof Combination){
            Object p1 = ((Combination) parameters).GetFirst();
            Object _p2 = ((Combination) parameters).GetSecond();
            if(p1 instanceof String) {
                name = p1.toString();
            } else if(p1 instanceof Combination) {
                Object _name = ((Combination) p1).GetFirst();
                assert _name instanceof String;
                name = _name.toString();
                Combination formals = ((Combination) ((Combination) p1).GetSecond());
                //Combination body = (Combination) ((Combination) _p2).GetFirst();
                Combination body = (Combination) _p2;
                operation = new LambdaProcedure(formals, body, Eval.CurrentEvalFrame);
                value = operation.toString();
                Eval.CurrentEvalFrame.AddSymbol(name, operation, value);
                return name;

                //name = Objects.requireNonNull(Eval.E(p1)).toString();
            } else throw new Exception("The name must be a string!");

            assert _p2 instanceof Combination;
            Object p2 = ((Combination) _p2).GetFirst();
            // Object p2 = ((Combination) _p2);
            Object e = Eval.E(p2);
            if(e instanceof Operation) {
                operation = (Operation) e;
                value = e.toString();
            }
            else value = e;
            //assert value instanceof Integer;
            Eval.CurrentEvalFrame.AddSymbol(name, operation, value);
        } else throw new Exception("Arguments not enough!");
        //System.out.println(name);
        //return name;
        return new Nil();
    }
    public static LambdaProcedure DefineLambda(Object parameters) throws Exception {
        LambdaProcedure operation = null;
        if(parameters instanceof Combination){
            Object p1 = ((Combination) parameters).GetFirst();
            Object _p2 = ((Combination) parameters).GetSecond();
            if(p1 instanceof Combination) {
                Combination formals = ((Combination) p1);
                Combination body = (Combination) _p2;
                operation = new LambdaProcedure(formals, body, Eval.CurrentEvalFrame);
                return operation;
            } else throw new Exception("Invalid lambda");
        }else throw new Exception("Invalid lambda");
    }
}
