//package Midterm;
//Maxime Sotsky
public class Octal {


    final static char OCTAL_ALPHABET[] = {'0','1','2','3','4','5','6','7'};
    final static char BINARY_ALPHABET[] = {'0','1'};
    final static int EIGHT_BIT = 8;
    final static int SIXTEEN_BIT = 16;
    final static int TWENTYFOUR_BIT = 24;
    final static int ZERO = 48;


    public static String threeToEight(int num1, int num2, int num3){

        if(num1 > 255 || num2 > 255 || num3 > 255 || num1 < 0 || num2 < 0 || num3 < 0)
            return null;

        char[] bin = new char[24];

        //BASE 10 -> BASE 2
        int counter = EIGHT_BIT-1;
        while (num1 > 0 || counter >= 0){
            if(num1 < 0 && counter >= 0)
                bin[counter] = BINARY_ALPHABET[ 0 ];
            else{
                bin[counter] = BINARY_ALPHABET[ num1 % 2 ];
                num1 /= 2;
            }
            counter--;
        }

        counter = SIXTEEN_BIT-1;
        while (num2 > 0 || counter >= EIGHT_BIT){
            if(num2 < 0 && counter >= EIGHT_BIT)
                bin[counter] = BINARY_ALPHABET[ 0 ];
            else{
                bin[counter] = BINARY_ALPHABET[ num2 % 2 ];
                num2 /= 2;
            }
            counter--;
        }
        counter = TWENTYFOUR_BIT-1;
        while (num3 > 0 || counter >= SIXTEEN_BIT){
            if(num3 < 0 && counter >= SIXTEEN_BIT)
                bin[counter] = BINARY_ALPHABET[ 0 ];
            else{
                bin[counter] = BINARY_ALPHABET[ num3 % 2 ];
                num3 /= 2;
            }
            counter--;
        }

        String ans = "";
        for(int i = 0; i < 24;i+=3){
            int first = ((int) bin[i]-ZERO) * 4;
            int second = ((int) bin[i+1]-ZERO)* 2;
            int third = ((int) bin[i+2]-ZERO) * 1;
            int int_ans  = (first + second + third);
            ans = ans + OCTAL_ALPHABET[int_ans];
        }
        return ans;
    }

    public static void main(String[] args){
        System.out.println(threeToEight(23, 198, 82));

    }

}
