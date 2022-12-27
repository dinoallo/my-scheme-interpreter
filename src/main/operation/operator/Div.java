package main.operation.operator;

import main.eval.Eval;
import main.operation.Operation;
import main.value.Combination;
import main.value.Nil;

public class Div implements Operation {

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
                double r =  1d / total;
                if((r % 1) == 0) return (int) r;
                else return r;
            }

            assert p2 instanceof Combination;
            Object o2 = ((Combination) p2).GetFirst();
            Double d = 1d;
            if(o2 instanceof Double) {
                d = (Double) o2;

            } else if(o2 instanceof Integer) {
                d = Double.valueOf((Integer) o2);
            } else if(o2 instanceof Combination || o2 instanceof String) {
                Object result = Eval.E(o2);
                if(result instanceof Integer) {
                    d = Double.valueOf((Integer) result);
                }
                if(result instanceof Double) d =  (Double) result;
            } else {
                throw new Exception("Input error!");
            }
            if(d == 0d) throw new Exception("Do not divide it by zero!");
            total /= d;
            if((total % 1) == 0) return (int) total;
            else return total;
        }
        else if (parameters instanceof Nil) {
            return 1d;
        }
        else throw new Exception("Parameters not enough!");
    }
}
