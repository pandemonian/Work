package Lesson_2.ClassWork;

import java.io.*;

/**
 * Created by Admin on 08.11.16.
 */
public class Palindrome {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputString = null;
        boolean isPalindrome = true;

        try {
            inputString = br.readLine();
        } catch (IOException e) {
            System.out.println("Something gonna wrong!");
        }

        char[] chArr = inputString.toCharArray();

        /** simultaneous reading charArray from start and from end
         * i - count running char`s array from start to end
         * j - count running char`s array from end to start
         */
        for (int i = 0, j = chArr.length - 1; ((i < chArr.length - 1) & (j >= 0 )); i++, j--) {

            if (chArr[i] != chArr[j]) {
                isPalindrome = false;
            }
        }

        if (isPalindrome) {
            System.out.println(isPalindrome);
        } else {
            System.out.println(!isPalindrome);
        }
    }
}