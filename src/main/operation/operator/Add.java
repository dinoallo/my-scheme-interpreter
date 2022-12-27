package main.operation.operator;

import main.eval.Eval;
import main.operation.Operation;
import main.symbol.Symbol;
import main.value.Combination;
import main.value.Nil;

public class Add implements Operation {

    @Override
    public Object Do(Object parameters) throws Exception {
        double total = 0;
        if(parameters instanceof Combination) {
            Object p = ((Combination) parameters).GetFirst();
            if(p instanceof Double) {
                Double d = (Double) p;
                total += d;

            } else if(p instanceof Integer) {
                Double d = Double.valueOf((Integer) p);
                total += d;
            }
            else if(p instanceof Combination || p instanceof String) {
                Object result = Eval.E(p);
                if(result instanceof Integer) {
                    total += Double.valueOf((Integer) result);
                }
                if(result instanceof Double) total += (Double) result;
            } else {
                throw new Exception("Input error!");
            }
            Object p2 = Do(((Combination) parameters).GetSecond());
            if(p2 instanceof Double) {
                Double d = (Double) p2;
                total += d;

            } else if(p2 instanceof Integer) {
                Double d = Double.valueOf((Integer) p2);
                total += d;
            }
            if((total % 1) == 0) return (int) total;
            else return total;
        }
        else if (parameters instanceof Nil) {
            return 0d;
        }
        else throw new Exception("Parameters not enough!");
    }


}
