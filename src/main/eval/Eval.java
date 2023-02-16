package main.eval;

import main.frame.Frame;
import main.operation.Define;
import main.operation.LambdaProcedure;
import main.operation.Operation;
import main.read.CodeReader;
import main.symbol.Symbol;
import main.value.Combination;
import main.value.Nil;

import java.util.Queue;
import java.util.Stack;
import main.value.Bool;

public class Eval {

    public static Frame GlobalFrame = new Frame(null);
    public static Frame CurrentEvalFrame = GlobalFrame;
    public static int count = 0;

    public static void StartEval() throws Exception {
        //Frame frame = CurrentEvalFrame;
        //FunctionWorker currentFunctionWorker = FunctionStack.peek();
        try {
            //CodeReader.RedirectInput(currentFunctionWorker.inputBufferReader);
            Queue<String> tokens = CodeReader.ReadInputTokens();
            //System.out.println(tokens);
            Object expression = CodeReader.SchemeRead(tokens);
            Object result = E(expression);
            if(result != null) {
                System.out.println("[" + count++ + "]: " + result);
            }
            //System.out.println(E(expression));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String AfterEval() throws Exception {
        return "";
    }

    public static Object E(Object e) throws Exception {
        //System.out.println("e: " + e);
        if(e instanceof Combination) {
            Object car = ((Combination) e).GetFirst();
            Object cdr = ((Combination) e).GetSecond();

            Object car_value = E(car);

            if(car_value instanceof Operation) {
                if(!(cdr instanceof Nil) || !( car_value instanceof LambdaProcedure )) return ((Operation) car_value).Do(cdr);
                return car_value;
            }
            else if(car instanceof String && car.equals("lambda")) {
                return Define.DefineLambda(cdr);
            }
            else if(car_value != null){
                if(!(cdr instanceof Nil)) return E(cdr);
                else return car_value;
            } else {
                return null;
            }
        } else if(e instanceof Number || e instanceof Bool || e instanceof Nil) {
            // The expression is just a primitive type. We can simply return the value it represents.
            return e;
        } else if(e instanceof String) {
            // The expression is a symbol. Return the binding operation/value
            if(((String) e).equals("lambda")) return "lambda";
            Symbol symbol = null;
            try {
                symbol = CurrentEvalFrame.GetSymbolByName((String) e);
            } catch (Exception ex) {
                System.out.println("There was an error while fetching: " + e + " Maybe the symbol doesn't exist or you have an typo");
                ex.printStackTrace();
                return null;
            }

            if(symbol == null) {
                throw new Exception("Error while finding symbol from the environment: " + e);
            }
            //TODO: merge operation and other value?
            if(symbol.IsOperation()) return symbol.GetOp();
            else return symbol.GetValue();
            // return symbol.GetValue();
        } else {
            throw new Exception("Expression is wrong!?");
        }
    }

    public static Symbol GetSymbolFromEnvironment(String symbolName) {
        Symbol symbol = null;

        return symbol;
    }

    private static scalarType checkScalarType(String scalar) {
        char ch = scalar.charAt(0);
        if (ch == '\"') return scalarType.WORD;
        else if (Character.isDigit(ch) || ch == '-') return scalarType.NUMBER;
        //else if (scalar.equals("true") || scalar.equals("false")) return scalarType.BOOL;
        else if (scalar.equals("nil")) return scalarType.NIL;
        else if (scalar.equals("#t") || scalar.equals("#f")) return scalarType.BOOL;
        else if (scalar.equals("(")) return scalarType.COMBINATION;
        else if (ch == '[') return scalarType.LIST;
        else if (ch == ':') return scalarType.THING;
        else return scalarType.OPERATION;
    }

    private enum scalarType {
        WORD,
        NUMBER,
        BOOL,
        LIST,
        OPERATION,
        THING,
        SYMBOL,
        COMBINATION,
        NIL,
    }
}
