package main.read;

import main.eval.Eval;
import main.value.Bool;
import main.value.Combination;
import main.value.Nil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CodeReader {

    private static BufferedReader currentBufferedReader= new BufferedReader(
            new InputStreamReader(System.in, StandardCharsets.UTF_8)
    );

    public static Object SchemeRead(Queue<String> src) throws Exception {
        if(src.isEmpty()) {
            throw new Exception("Invalid input!");
        }
        if (Objects.equals(src.peek(), "nil")) {
            src.remove();
            return new Nil();
        } else if (Objects.equals(src.peek(), "(")) {
            src.remove();
            return ReadTail(src);
        } else if (Objects.equals(src.peek(), "'")) {
            src.remove();
            Object o1 = "quote";
            Object o2 = SchemeRead(src);
            return new Combination(o1, o2);
        } else if (!isWordDelimiter(src.peek())) {
            String s = src.peek();
            src.remove();
            if(Objects.equals(s, "#t")) {
                return new Bool(Boolean.TRUE);
            }
            if (Objects.equals(s, "#f")) {
                return new Bool(Boolean.FALSE);
            }
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException ex) {
                try {
                    return Double.parseDouble(s);
                } catch (NumberFormatException ex2) {
                    return s;
                }
            }
        }
        else throw new Exception("Invalid input!");
    }

    public static Object ReadTail(Queue<String> src) throws  Exception {
        if(src.isEmpty()) {
            throw new Exception("Invalid input!");
        }
        if (Objects.equals(src.peek(), ")")) {
            src.remove();
            return new Nil();
        }
        else {
            Object o1 = SchemeRead(src);
            Object o2 = ReadTail(src);
            return new Combination(o1, o2);
        }
    }

    public static Queue<String> ReadInputTokens () throws Exception{
        int c, nextc;
        Queue<String> src = new LinkedList<>();
        StringBuilder stringBuffer = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        while (true) {
            nextc = currentBufferedReader.read();
            if(nextc == -1) {
                if(stringBuffer.length() > 0) src.add(stringBuffer.toString());
                break;
            }
            c = nextc;
            char ch = (char) c;
            if (isWordDelimiter(ch)) {
                if(ch == '(') stack.add(ch);
                if(ch == ')') {
                    if(stack.isEmpty()) throw new Exception("Invalid input! missing parentheses");
                    else stack.pop();
                }
                if (stringBuffer.length() > 0) {
                    src.add(stringBuffer.toString());
                    stringBuffer.setLength(0);
                }
                stringBuffer.append(ch);
                src.add(stringBuffer.toString());
                stringBuffer.setLength(0);
            } else if (isWhitespace(ch)) { // includes '\n'
                if (stringBuffer.length() > 0) {
                    src.add(stringBuffer.toString());
                    stringBuffer.setLength(0);
                }
                if(stack.isEmpty() && stringBuffer.length() == 0) return src;
            } else {
                stringBuffer.append(ch);
            }
        }

        currentBufferedReader.close();
        return src;
    }

    public static String ReadScalarFromInput() throws Exception {
        int c, nextc, scalarFlag = 0, listFlag = 0, count = 0;
        StringBuilder stringBuffer = new StringBuilder();
        nextc = currentBufferedReader.read();
        while (true) {
            c = nextc;
            if( c == -1 ){
                break;
            }
            char ch = (char) c;
            if (scalarFlag == 0) {
                if (isValidPrefix(ch)) {
                    scalarFlag = 1;
                    if (ch == '[') {
                        listFlag = 1;
                        count++;
                    }
                    stringBuffer.append(ch);
                } else if (!isWordDelimiter(ch)) {
                    throw new Exception("Invalid Input!");
                }
            } else {
                if (listFlag == 1) {
                    stringBuffer.append(ch);
                    if (ch == ']') {
                        if (count == 1) {
                            return stringBuffer.toString();
                        } else if (count > 1) {
                            count--;
                        } else {
                            throw new Exception("Invalid List!");
                        }
                    } else if (ch == '[') {
                        count++;
                    }
                } else {
                    if (isWordDelimiter(ch)) {
                        return stringBuffer.toString();
                    } else {
                        stringBuffer.append(ch);
                    }
                }
            }
            nextc = currentBufferedReader.read();
            if(nextc == -1) return stringBuffer.toString();
        }

        currentBufferedReader.close();
        // return stringBuffer.toString();
        return "";
    }

    public static String ReadLineFromInput() throws Exception {
        return currentBufferedReader.readLine();
    }

    public static void RedirectInput(BufferedReader newBufferedReader) throws Exception {
        currentBufferedReader = newBufferedReader;
    }

    public static Queue<String> ScrambleScalarFromList(String listScalar ) throws Exception{
        Queue<String> wordQueue = new LinkedList<>();
        int scalarFlag = 0, listFlag = 0, count = 0;
        StringBuilder stringBuffer = new StringBuilder();
        for(int i = 1; i < listScalar.length() - 1; i++){
            char ch = listScalar.charAt(i);
            if (scalarFlag == 0) {
                if (isValidPrefix(ch)) {
                    scalarFlag = 1;
                    if (ch == '[') {
                        listFlag = 1;
                        count++;
                    }
                    stringBuffer.append(ch);
                } else if (!isWordDelimiter(ch)) {
                    throw new Exception("Invalid Input!");
                }
            } else {
                if (listFlag == 1) {
                    stringBuffer.append(ch);
                    if (ch == ']') {
                        if (count == 1) {
                            wordQueue.add(stringBuffer.toString());
                            stringBuffer.delete(0, stringBuffer.length());
                            scalarFlag = 0;
                            count--;
                        } else if (count > 1) {
                            count--;
                        } else {
                            throw new Exception("Invalid List!");
                        }
                    } else if (ch == '[') {
                        count++;
                    }
                } else {
                    if (isWordDelimiter(ch)) {
                        wordQueue.add(stringBuffer.toString());
                        stringBuffer.delete(0, stringBuffer.length());
                        scalarFlag = 0;
                    } else {
                        stringBuffer.append(ch);
                    }
                }
            }
        }
        if(stringBuffer.length() != 0){
            wordQueue.add(stringBuffer.toString());
            stringBuffer.delete(0, stringBuffer.length());
        }
        return wordQueue;
    }

    public static List<String> ScrambleScalarFromListToList(String listScalar ) throws Exception{
        List<String> wordList = new ArrayList<>();
        int scalarFlag = 0, listFlag = 0, count = 0;
        StringBuilder stringBuffer = new StringBuilder();
        for(int i = 1; i < listScalar.length() - 1; i++){
            char ch = listScalar.charAt(i);
            if (scalarFlag == 0) {
                if (isValidPrefix(ch)) {
                    scalarFlag = 1;
                    if (ch == '[') {
                        listFlag = 1;
                        count++;
                    }
                    stringBuffer.append(ch);
                } else if (!isWordDelimiter(ch)) {
                    throw new Exception("Invalid Input!");
                }
            } else {
                if (listFlag == 1) {
                    stringBuffer.append(ch);
                    if (ch == ']') {
                        if (count == 1) {
                            wordList.add(stringBuffer.toString());
                            stringBuffer.delete(0, stringBuffer.length());
                            scalarFlag = 0;
                            count--;
                        } else if (count > 1) {
                            count--;
                        } else {
                            throw new Exception("Invalid List!");
                        }
                    } else if (ch == '[') {
                        count++;
                    }
                } else {
                    if (isWordDelimiter(ch)) {
                        wordList.add(stringBuffer.toString());
                        stringBuffer.delete(0, stringBuffer.length());
                        scalarFlag = 0;
                    } else {
                        stringBuffer.append(ch);
                    }
                }
            }
        }
        if(stringBuffer.length() != 0){
            wordList.add(stringBuffer.toString());
            stringBuffer.delete(0, stringBuffer.length());
        }
        return wordList;
    }

    private static Boolean isValidPrefix(char ch) {
        return Character.isAlphabetic(ch) || Character.isDigit(ch) || ch == '\"' || ch == '-' || ch == '[' || ch == ':' || ch == '_';
    }

    private static Boolean isWordDelimiter(char ch) {
        return ch == ',' || ch == '(' || ch == ')' || ch == '[' || ch == ']' || ch == '\'' || ch == '`' || ch == '@' ;
    }

    private static Boolean isWhitespace(char ch) {
        return Character.isWhitespace(ch);
        // return ch == ' ' || ch == '\t' || ch == '\n'; // how to handle CRLF?
    }
    private static Boolean isWordDelimiter(String s) {
        return Objects.equals(s, ".") || Objects.equals(s, ",") ||
                Objects.equals(s, ",@") || Objects.equals(s, "(") ||
                Objects.equals(s, ")") || Objects.equals(s, "[") ||
                Objects.equals(s, "]") || Objects.equals(s, "'") || Objects.equals(s, "`");
    }

}
