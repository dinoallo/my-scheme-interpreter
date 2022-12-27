package main.operation;

public interface Operation {
    default String Do() throws Exception {
        return "";
    }
    default Object Do(Object parameters) throws Exception {
        return "";
    }
}