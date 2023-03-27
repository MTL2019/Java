package Examples;

import java.util.Scanner;

public class StringMatching {
    public static void main(String[] args) {

        testStringMatching();
    }

    /**
     *
     */
    public static void testStringMatching(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a string text: ");
        String text = input.nextLine();
        System.out.println("Enter a string pattern: ");
        String pattern = input.nextLine();

        //text = "fuwaabcabcdabcfwiuah";//Test example
        //pattern = "abcabcdabc";

        //Three ways with different time complexity
        //int index = match(text,pattern);
        //int index = matchBoyerMoore(text,pattern);
        int index = matchKPM(text,pattern);

        if (index >= 0) {
            System.out.println("matched at index "+ index);
        }else
        {System.out.println("unmatched");};
    }

    public static int matchKPM(String text, String pattern){

        int[] fail = getFailure(pattern);

        int i = 0;
        int k = 0;

        while (i < text.length()) {
            if (text.charAt(i) == pattern.charAt(k)) {
                if (k == pattern.length() - 1) {
                    return i - pattern.length() + 1; // pattern matched
                }

                i++; // Compare the next pair of characters
                k++;
            } else {
                if (k > 0) {
                    k = fail[k - 1];// Matching prefix position
                } else {
                    i++;// No prefix
                }
            }
        }

        return -1;
    }

    public static int[] getFailure(String pattern){

        int[] fail = new int[pattern.length()];
        for (int i = 0; i < fail.length; i++) {
            fail[i] = 0;
        }

        int i = 1;
        int k = 0;
        while(i < pattern.length()){
//            while(k < pattern.length()) {
                if (pattern.charAt(i) == pattern.charAt(k)) {
                    fail[i] = k + 1;
                    k++;
                    i++;
                }else {
                    if (k > 0){
                        k = fail[k - 1];
                    }else
                    {//k == 0
                         i++;
                    }
                }
 //           }
        }

        return fail;
    }
    public static int matchBoyerMoore(String text, String pattern){
        int i = pattern.length() - 1; // begin at the index of the tail of pattern

        while(i < text.length()){
            int k = i;
            int j = pattern.length() -1;
            while (j >= 0){
                if (text.charAt(k) == pattern.charAt(j)) {
                    k--;//from right to left
                    j--;
                }
                else {
                    break;
                }
            }

            if (j < 0)
                return i = pattern.length() + 1;//A match　found

            //if j>=0 , not found,
                int u = findLastIndex(text.charAt(k),j-1,pattern);
                if (u >= 0) {// text[k] is in the remaining part of the pattern
                    i = k + pattern.length() - 1- u;
                }else {
                    // text[k] is in the remaining part of the pattern
                    i = k + pattern.length();
                }
        }

        return -1;
    }

    // Return the index of the last element in pattern[0 .. j] that matches ch. –1 otherwise.
    public static int findLastIndex(char ch, int j, String pattern){
        for (int k = j; k >= 0; k--) {
            if (ch == pattern.charAt(k)) {
                return k;
            }
        }

        return -1;
    }

    public static int match(String text, String pattern){
        for (int i = 0; i < text.length() - pattern.length() + 1; i++) {
            if (isMatched(i,text,pattern)) {
                return  i;
            }
        }
        return -1;
    }

    public static boolean isMatched(int i,String text, String pattern){
        for (int j = 0; j < pattern.length(); j++) {
            if (pattern.charAt(j) !=  text.charAt(i + j)) {
                return false;
            }
        }

        return true;
    }
}
