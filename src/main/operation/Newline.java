package main.operation;

public class Newline implements Operation {
    @Override
    public Object Do(Object parameters) throws Exception {
        System.out.print("\n");
        return "";
    }
}
