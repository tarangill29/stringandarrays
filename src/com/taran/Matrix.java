package com.taran;

import java.util.Random;

public class Matrix {

    public static void main(String[] args) {

        Random random = new Random();
        int n = random.nextInt(3);

        int[][] matrix = new int[5][4];

        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[i].length; j++) {
                matrix[i][j] = random.nextInt(15);
            }
        }


        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println();
        }

        zeroeMatrix(matrix);
        System.out.println();

        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println();
        }
//        StringUtilities utils = new StringUtilities();
//        StringBuilder sb = new StringBuilder();
//        while (sb.length() < 10) {
//            utils.addChar(sb, 'a');
//        }
//        System.out.println(sb);
//
//        String str = "abcdefg";
//        String result = utils.upperAndPrefix(utils.addSuffix(str));

	    /*char a = 'A';
	    char z = 'z';
        while (a <= z) {
            System.out.println(a + " " + (int)a);
            a++;
        }
        int c = 434;
        System.out.println(parseInt("25432"));
       // System.out.println(Integer.parseInt("+2452"));
        String asd = "2543";*/

       // System.out.println(/*asd.charAt(0)-48) **/ (int)Math.pow(10,asd.length()-1));
    }

    public static int[][] rotateMatrix(int[][] matrix) {
        int offset = 0;

        for(int n=0; n<matrix.length/2; n++) {
            int first = n;
            int last = matrix.length - 1 - n;
            for(int i=first; i<last; i++) {
                int temp = matrix[first][i];
                matrix[first][i] = matrix[last-i+offset][first];
                matrix[last-i+offset][first] = matrix[last][last-i+offset];
                matrix[last][last-i+offset] = matrix[i][last];
                matrix[i][last] = temp;
            }
            offset++;
        }
        return matrix;
    }

    public static int[][] zeroeMatrix(int[][] matrix) {
        boolean [] columnArray = new boolean[matrix[0].length];
        boolean [] rowArray = new boolean[matrix.length];

        for(int i=0; i<matrix.length; i++) {
           for(int j=0; j<matrix[i].length; j++) {
                if(matrix[i][j] == 0) {
                    columnArray[j] = true;
                    rowArray[i] = true;
                }
           }
        }

        for (int i=0; i<columnArray.length; i++) {
            if(columnArray[i]) nullifyColumn(matrix, i);
        }

        for (int i=0; i<rowArray.length; i++) {
            if(rowArray[i]) nullifyRow(matrix, i);
        }

        return matrix;
    }

    public static void nullifyRow(int[][] matrix, int row) {
        for(int i=0; i<matrix[row].length; i++) {
            matrix[row][i] = 0;
        }
    }

    public static void nullifyColumn(int[][] matrix, int column) {
        for(int i=0; i<matrix.length; i++) {
            matrix[i][column] = 0;
        }
    }

    public static int parseInt(String str) {
        boolean isNegative = false;
        boolean isPositive = false;
        if(str.charAt(0) == '-') {
            isNegative = true;
        } else if(str.charAt(0) == '+') {
            isPositive = true;
        }
        int i = 0;
        if(isNegative || isPositive) {
            if(str.length() == 1) {
                throw new NumberFormatException("Not a valid integer");
            }
            i = 1;
        } else {
            i = 0;
        }
        int number = 0;
        for(; i < str.length(); i++) {
            if (str.charAt(i) <48 || str.charAt(i) > 57) {
                throw new NumberFormatException("Not a valid integer");
            }
            else {
                System.out.println(number);
                number = number + (str.charAt(i) - 48) * (int)Math.pow(10,str.length()-1-i);
            }
        }
        return isNegative? -number : number;

    }
}
