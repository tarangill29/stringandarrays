package com.taran;

import java.util.HashMap;
import java.util.Map;

public class StringAndArrays {
    public static void main(String[] args) {

        System.out.println(compress("absddasffffttttttyy"));
    }

    public static String compress(String s) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        int index = 0;
        while (index < s.length()) {
            if(index==s.length()-1) {
                sb.append(s.charAt(index));
                sb.append(count);
                break;
            }
            if(s.charAt(index) == s.charAt(index+1)) {
                count++;
            } else {
                sb.append(s.charAt(index));
                sb.append(count);
                count = 1;
            }
            index++;
        }
        System.out.println(sb.toString() + " "+ sb.length());
        return sb.length() < s.length()? sb.toString(): s;
    }

    public static boolean oneEdit(String s1, String s2) {
        if(s1.length() == s2.length()) {
            return oneReplace(s1, s2);
        } else if(s1.length() == s2.length()+1) {
            return oneInsertDelete(s2, s1);
        } else if(s1.length()+1 == s2.length()) {
            return oneInsertDelete(s1, s2);
        } else {
            return false;
        }
    }

    private static boolean oneInsertDelete(String s1, String s2) {
        int i=0,j=0;
        int noOfEdits = 0;

        while(i<s1.length() && j<s2.length()) {
            if(s1.charAt(i) ==s2.charAt(j)) {
                i++;
                j++;
            } else {
                noOfEdits++;
                j++;
                if(noOfEdits >=2) return false;
            }
        }
        return true;
    }

    private static boolean oneReplace(String s1, String s2) {
        int noOfEdits = 0;
        for (int i=0; i<s1.length(); i++) {
            if(s1.charAt(i) != s2.charAt(i)) {
                noOfEdits++;
                if(noOfEdits >= 2) return false;
            }
        }

        if(noOfEdits < 2) {
            return true;
        } else {
            return false;
        }
    }


    public static boolean isUnique(String s) {
        int[] asciiArray = new int[256];
        boolean isUnique = true;
        for (int i = 0; i < s.length(); i++) {

            if (asciiArray[s.charAt(i)] == 0) {
                asciiArray[s.charAt(i)] = 1;
            } else {
                isUnique = false;
                break;
            }
        }
        return isUnique;
    }

    public static boolean isPermOfPalindrome(String s) {
        s = s.toLowerCase();
        System.out.println(s);
        Map<Character, Integer> countMap = new HashMap<>();
        int countOdd = 0;

        for(int i=0; i < s.length(); i++) {
            if(s.charAt(i) == ' ') continue;

            if(countMap.containsKey(s.charAt(i))) {
                int value = countMap.get(s.charAt(i));
                countMap.put(s.charAt(i), ++value);
                if(value%2 == 0) {
                    countOdd++;
                } else {
                    countOdd--;
                }
            } else {
                countMap.put(s.charAt(i), 1);
                countOdd--;
            }
        }
       /* for(char c : countMap.keySet()) {
            if((countMap.get(c) % 2) != 0) {
                countOdd++;
            }
        }*/

        if(countOdd < -1) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isPerm(String s1, String s2) {
        if (s1.length() != s2.length()) return false;

        int[] asciiArray = new int[128];
        for (int i = 0; i < s1.length(); i++) {
            asciiArray[s1.charAt(i)]++;
        }

        for (int i = 0; i < s2.length(); i++) {
            asciiArray[s2.charAt(i)]--;
            if (asciiArray[s2.charAt(i)] < 0) {
                return false;
            }
        }

        return true;
    }

    public static String urlify(String s, int trueLength) {
        char[] cstr = s.toCharArray();
        int noOfSpaces = 0;
        for (int i = 0; i < trueLength; i++) {
            if (cstr[i] == ' ') {
                noOfSpaces++;
            }
        }

        int lastIndex = trueLength + noOfSpaces * 2;

        for (int i = trueLength - 1; i >= 0; i--) {
            if (cstr[i] != ' ') {
                cstr[lastIndex - 1] = cstr[i];
                lastIndex--;
            } else {
                cstr[lastIndex - 1] = '%';
                cstr[lastIndex - 2] = '0';
                cstr[lastIndex - 3] = '2';
                lastIndex = lastIndex - 3;
            }
        }
        return new String(cstr);
    }
}
