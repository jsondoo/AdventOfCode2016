import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day8 {
    static boolean screen[][] = new boolean[6][50];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader("-")); //filepath omitted

        String line = "";
        Pattern size = Pattern.compile("rect (\\d+)x(\\d+)");
        Pattern rotR = Pattern.compile("rotate row y=(\\d+) by (\\d+)");
        Pattern rotC = Pattern.compile("rotate column x=(\\d+) by (\\d+)");

        while((line = br.readLine()) != null){
            Matcher sizeM = size.matcher(line);
            Matcher rotRM = rotR.matcher(line);
            Matcher rotRC = rotC.matcher(line);

            if(sizeM.find()) {
                int col = Integer.parseInt(sizeM.group(1));
                int row = Integer.parseInt(sizeM.group(2));

                for(int i = 0; i < row; i++) {
                    for(int j = 0; j < col; j++) {
                        screen[i][j] = true;
                    }
                }
            }
            else if(rotRM.find()){
                int y = Integer.parseInt(rotRM.group(1));
                int shiftRcount = Integer.parseInt(rotRM.group(2));

                while(shiftRcount>0) {
                    shiftRight(y, screen);
                    shiftRcount--;
                }

            }
            else if(rotRC.find()){
                int x = Integer.parseInt(rotRC.group(1));
                int shiftDcount = Integer.parseInt(rotRC.group(2));

                while(shiftDcount>0){
                    shiftDown(x,screen);
                    shiftDcount--;
                }
            }
            display(screen);
            System.out.println("\n\n");
        }

        System.out.println(countLit(screen)); // 110
        // screen displays ZJHRKCPLYJ
    }

    static int countLit(boolean[][] arr){
        int count = 0;
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 50; j++){
                if(arr[i][j]) count++;
            }
        }
        return count;
    }


    static void shiftDown(int col, boolean[][] arr){
        boolean temp = arr[5][col];
        for(int i = 5; i > 0; i--){
            arr[i][col] = arr[i-1][col];

        }
        arr[0][col] = temp;
    }


    static void shiftRight(int row, boolean[][] arr){
        boolean temp = arr[row][49];
        for(int i = arr[row].length-1; i > 0; i--){
            arr[row][i] = arr[row][i-1];
        }
        arr[row][0] = temp;
    }


    static void display(boolean[][] arr){
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 50; j++){
                if(arr[i][j]) System.out.print("#");
                else System.out.print(".");
            }
            System.out.print("\n");
        }


    }
}
