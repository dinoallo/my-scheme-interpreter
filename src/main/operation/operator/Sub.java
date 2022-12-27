package main.operation.operator;

import main.eval.Eval;
import main.operation.Operation;
import main.value.Combination;
import main.value.Nil;

public class Sub implements Operation {

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

            Object p2 = ((Combination) parameters).GetSecond();
            if(p2 instanceof Nil) {
                total = 0 - total;
                int total2 = (int) total;
                if(total == (double) total2) return (int) total;
                else return total;
            }
            assert p2 instanceof Combination;
            Object o2 = ((Combination) p2).GetFirst();
            if(o2 instanceof Double) {
                Double d = (Double) o2;
                total -= d;

            } else if(o2 instanceof Integer) {
                Double d = Double.valueOf((Integer) o2);
                total -= d;
            } else if(o2 instanceof Combination || o2 instanceof String) {
                Object result = Eval.E(o2);
                if(result instanceof Integer) {
                    total -= Double.valueOf((Integer) result);
                }
                if(result instanceof Double) total -= (Double) result;
            } else {
                throw new Exception("Input error!");
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