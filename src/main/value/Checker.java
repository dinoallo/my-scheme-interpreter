package main.value;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checker {
    public static Boolean IsValidName(String scalar) {
        Pattern pattern = Pattern.compile("^\"[A-Za-z][A-Za-z0-9_]*$"); // match the string that contains char other than letters, digits, _.
        Matcher matcher = pattern.matcher(scalar);
        return matcher.find();
    }

    public static Boolean IsValidWord(String scalar) {
        return !scalar.contains(" ") && scalar.startsWith("\"");
    }

    public static Boolean IsValidNumber(String scalar) {
        // ??? weird number scalar in main-specification ??? Is 1234dd a number?
        /* Pattern pattern = Pattern.compile("^[0-9\\-]+(\\.[0-9])?[0-9]*$");
        Matcher matcher = pattern.matcher(scalar);
        return matcher.find();*/
        char ch = scalar.charAt(0);
        return ch == '-' || Character.isDigit(ch);
    }

    public static Boolean IsValidBool(String scalar) {
        return Objects.equals(scalar, "true") || Objects.equals(scalar, "false");
    }

    public static Boolean IsValidList(String scalar) {
        int flag = 0; // count the number of '['.
        int count = 0;
        int length = scalar.length();
        for (int i = 0; i < length; i++) {
            if (scalar.charAt(i) == '[') flag++;
            if (scalar.charAt(i) == ']') {
                if (flag <= 0) return false;
                else {
                    flag--;
                    count++;
                }
            }
        }
        return flag == 0 && count > 0;
    }

    public static Boolean IsEmpty(String scalar){
        if(IsValidList(scalar)){
            for (int i = 1; i < scalar.length() - 1; i++){
                if(scalar.charAt(i) != ' ') return false;
            }
            return true;
        } else if(IsValidWord(scalar)) {
            return scalar.length() == 0;

        } else {
            return false;
        }
    }

    public static Boolean IsPrimitive(Object o) {
        return o instanceof Integer || o instanceof Double || o instanceof Bool || o instanceof Nil;
    }

    /* public static Boolean IsValidLambda(String scalar){
        // To see if it's a list
        if(!IsValidList(scalar)){
            return false;
        }
        String s = scalar.substring(1, scalar.length()-1);
        int list1_left = 0, list1_right = 0, list2_left = 0, list2_right = 0;
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(list1_left == 0){

            } else {

            }

        }
    } */
}