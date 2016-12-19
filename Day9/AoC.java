import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class AoC{
    public static void main(String[] args) throws IOException{
        String input = new Scanner(new File("./src/input.txt")).useDelimiter("\\Z").next();
        System.out.println(decompressA(input)); // Part 1 answer
        System.out.println(recursiveDecompress(input)); // Part 2 answer
    }

    public static long recursiveDecompress(String str){
        long length = 0;
        int currIndex = 0;

        char[] charArray = str.toCharArray();
        while(currIndex < charArray.length){
            // parse marker if the character is '('
            if(charArray[currIndex]=='('){
                int markerEnd = str.indexOf(')', currIndex);
                // get marker (axb), parse values of a and b
                String[] marker = str.substring(currIndex + 1, markerEnd).split("x");
                int a = Integer.valueOf(marker[0]);
                int b = Integer.valueOf(marker[1]);

                String marked = str.substring(markerEnd+1,markerEnd+1+a);
                length += recursiveDecompress(marked)*b;
                currIndex = markerEnd+1+a;
            }
            else {
                length++;
                currIndex++;
            }
        }
        return length;
    }

    public static int decompressA(String str){
        int length = 0;
        int currIndex = 0;

        char[] charArray = str.toCharArray();
        while(currIndex < charArray.length){
            // parse marker if the character is '('
            if(charArray[currIndex]=='('){
                int markerEnd = str.indexOf(')', currIndex);
                // get marker (axb), parse values of a and b
                String[] marker = str.substring(currIndex + 1, markerEnd).split("x");
                int a = Integer.valueOf(marker[0]);
                int b = Integer.valueOf(marker[1]);
                length += a*b;
                currIndex = markerEnd+1+a;
            }
            else {
                length++;
                currIndex++;
            }
        }
        return length;
    }


}
