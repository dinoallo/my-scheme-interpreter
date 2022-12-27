package main.value;

public class Bool {
    public final Boolean b;

    public Bool(Boolean b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return b ? "#t" : "#f";
    }

    public static boolean IsTrue(Object o){
        return !(o instanceof Bool) || ((Bool) o).b;
    }
}
