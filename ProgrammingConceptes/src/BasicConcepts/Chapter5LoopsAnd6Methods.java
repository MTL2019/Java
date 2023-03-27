package BasicConcepts;

/**
 * @Auther:JW
 * @Date:2023-01-27 - 01 - 27 - 10:27 p.m.
 * @Description:LearnFromBook
 * @Version:1.0
 */
public class Chapter5LoopsAnd6Methods {

    public static void main(String[] args){

        System.out.println(findGreatestCommonDivisor(125,25250));

        System.out.println(Dec2Hex(1001));
        System.out.println(Hex2Dec("12"));

        checkPalindrome("dad1");//检查是否是回文
    }

    public static int Hex2Dec(String hex){

        int decimal = 0;
        for (int i = 0; i < hex.length(); i++) {
            char hexChar = hex.charAt(i);
            decimal = decimal * 16 + hexChar2Decimal(hexChar);
        }

        return decimal;
    }

    public static int hexChar2Decimal(char ch){
        if (ch >= 'A' && ch <= 'F') {
            return 10 + ch - 'A';
        }else{
            return ch - '0';
        }
    }

    public static void checkPalindrome(String str){
        int low = 0;
        int high = str.length() -1;

        boolean isPalindrome = true;
        while (low < high){
            if (str.charAt(low) != str.charAt(high)) {
                isPalindrome = false;
                break;
            }
            low ++;
            high --;
        }

        if (isPalindrome) {
            System.out.println(str + " is a Palindrome");
        }else{
            System.out.println(str + " is not a Palindrome");
        }
    }

    public static int findGreatestCommonDivisor(int n1,int n2){

        int gcd = 1;
        int k = 2;
        while (k <= n1 && k <= n2){
            if (n1 % k == 0 && n2 % k == 0) {
                gcd = k;
            }
            k++;
        }
        return gcd;
    }

    public static String Dec2Hex(int decimal){

        StringBuilder hex = new StringBuilder();

        while(decimal != 0 ){
            int hexValue = decimal % 16;

            char hexDegit  = (0 <= hexValue && hexValue <= 9) ?
                    (char) (hexValue + '0'): (char)(hexValue - 10 + 'A');

            hex.insert(0, hexDegit);
            decimal = decimal / 16;
        }
        return hex.toString();
    }
}


