package Examples;

/**
 * @Auther:JW
 * @Date:2023-02-07 - 02 - 07 - 1:33 p.m.
 * @Description:Examples
 * @Version:1.0
 */
public class PrintString {

    public static void main(String[] args){

        //String s = generateShape(8);
        String s = generateShape1(8);
        System.out.println(s);
    }

    private static String generateShape(int numberOfRows) {
        int rows = 1;//each row has the same numbers as row number
        int a =1;
        String shape = "";
        for (int nums = 0; nums <= rows ; ) {
            shape += a;
            a++;
            nums++;
            if (nums == rows) {//last row
                rows++;
                if (nums == numberOfRows) {
                    break;
                }
                shape += "\n";
                nums = 0;
            }else{
                shape += " ";
            }
        }
        return shape;
    }

    private static String generateShape1(int numberOfRows) {

        StringBuilder s = new StringBuilder("");
        int num = 1;
        for(int i = 1; i <= numberOfRows;i++ ){

            int temp;
            for(int j = 0; j < i;j++ ){
                temp = num + j;
                if (j < i-1) {
                    s.append(temp).append(" ");
                }else{
                    if(i == numberOfRows){
                        s.append(temp);
                    }else
                        s.append(temp).append("\n");
                }
            }
            num += i;
        }

        return s.toString();
    }
}
