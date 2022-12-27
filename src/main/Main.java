package main;

import main.eval.Eval;
import main.operation.*;
import main.operation.operator.*;

public class Main {
    public static void main(String[] args) throws Exception {
        //Eval.GlobalFrame.AddSymbol("make", make, "magic_value");
        Define define = new Define();
        Eval.GlobalFrame.AddSymbol("define", define, "magic_value");
        // init add
        Add add = new Add();
        //Eval.GlobalFrame.AddSymbol("add", add, "magic_value");
        Eval.GlobalFrame.AddSymbol("+", add, "magic_value");
        // init sub
        Sub sub = new Sub();
        //Eval.GlobalFrame.AddSymbol("sub", sub, "magic_value");
        Eval.GlobalFrame.AddSymbol("-", sub, "magic_value");
        // init mul
        Mul mul = new Mul();
        //Eval.GlobalFrame.AddSymbol("mul", mul, "magic_value");
        Eval.GlobalFrame.AddSymbol("*", mul, "magic_value");
        // init div
        Div div = new Div();
        Eval.GlobalFrame.AddSymbol("/", div, "magic_value");
        Remainder remainder = new Remainder();
        Eval.GlobalFrame.AddSymbol("remainder", remainder, "magic_value");
        Eval.GlobalFrame.AddSymbol("%", remainder, "magic_value");
        // pi
        Eval.GlobalFrame.AddSymbol("pi", null, "3.14159");
        // quote
        Quote quote = new Quote();
        Eval.GlobalFrame.AddSymbol("quote", quote, "magic_value");
        // and
        And and = new And();
        Eval.GlobalFrame.AddSymbol("and", and, "magic_value");
        // or
        Or or = new Or();
        Eval.GlobalFrame.AddSymbol("or", or, "magic_value");
        // eq
        Eq eq = new Eq();
        Eval.GlobalFrame.AddSymbol("=", eq, "magic_value");
        // gt
        Gt gt = new Gt();
        Eval.GlobalFrame.AddSymbol(">", gt, "magic_value");
        // lt
        Lt lt = new Lt();
        Eval.GlobalFrame.AddSymbol("<", lt, "magic_value");
        // leq
        Leq leq = new Leq();
        Eval.GlobalFrame.AddSymbol("<=", leq, "magic_value");
        //geq
        Geq geq = new Geq();
        Eval.GlobalFrame.AddSymbol(">=", geq, "magic_value");
        // cond
        Cond cond = new Cond();
        Eval.GlobalFrame.AddSymbol("cond", cond, "magic_value");
        // if
        If _if = new If();
        Eval.GlobalFrame.AddSymbol("if", _if, "magic_value");
        // cons
        Cons cons = new Cons();
        Eval.GlobalFrame.AddSymbol("cons", cons, "magic_value");
        // list
        List list = new List();
        Eval.GlobalFrame.AddSymbol("list", list, "magic_value");
        // car
        Car car = new Car();
        Eval.GlobalFrame.AddSymbol("car", car, "magic_value");
        // cdr
        Cdr cdr = new Cdr();
        Eval.GlobalFrame.AddSymbol("cdr", cdr, "magic_value");
        // let
        Let let = new Let();
        Eval.GlobalFrame.AddSymbol("let", let, "magic_value");
        // display
        Display display = new Display();
        Eval.GlobalFrame.AddSymbol("display", display, "magic_value");
        // displayln
        Displayln displayln = new Displayln();
        Eval.GlobalFrame.AddSymbol("displayln", displayln, "magic_value");
        // newline
        Newline newline = new Newline();
        Eval.GlobalFrame.AddSymbol("newline", newline, "magic_value");
        // append
        Append append = new Append();
        Eval.GlobalFrame.AddSymbol("append", append, "magic_value");
        // length
        Length length = new Length();
        Eval.GlobalFrame.AddSymbol("length", length, "magic_value");
        // null?
        IsNull isNull = new IsNull();
        Eval.GlobalFrame.AddSymbol("null?", isNull, "magic_value");
        // begin
        Begin begin = new Begin();
        Eval.GlobalFrame.AddSymbol("begin", begin, "magic_value");


        while (true) {
            System.out.print("scm> ");
            Eval.StartEval();
            // if (Objects.equals(s, "")) break;
        }
    }
}
