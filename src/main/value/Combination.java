package main.value;

public class Combination {
    private Object o1;
    private Object o2;
    public Combination(Object o1, Object o2) {
        this.o1 = o1;
        this.o2 = o2;
    }
    public Object GetFirst() {
        return this.o1;
    }
    public Object GetSecond() {
        return this.o2;
    }
    public void SetSecond(Object o2) {
        this.o2 = o2;
    }
    public String toString() {
        return "(" +
                getString();
    }

    public String getString() {
        if(o2 instanceof Nil) {
            if(o1 instanceof Combination) {
                return "(" + ((Combination) o1).getString() + ")";
            } else return o1.toString() + ")";
        } else if(o2 instanceof Combination) {
            if(o1 instanceof Combination) {
                return "(" + ((Combination) o1).getString() + " " + ((Combination) o2).getString();
            } else {
                return o1.toString() + " " + ((Combination) o2).getString();
            }
        } else {
            if(o1 instanceof Combination) {
                return "(" + ((Combination) o1).getString() + " . " + o2.toString() + ")";
            } else return "(" + o1.toString() + " . " + o2.toString() + ")";
        }
    }

}
