import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String line;
        int count = 0;

        /* PART 1
        while((line = br.readLine()) != null){
            String[] parts = line.trim().split("\\s+");
            int[] triangleSides = new int[3];
            for(int i = 0; i < parts.length; i++){
                triangleSides[i] = Integer.valueOf(parts[i]);
            }

            Arrays.sort(triangleSides);
            if(triangleSides[0]+triangleSides[1] > triangleSides[2]){
                count++;
            }
        }
        */

        // Parse 9 integers at a time
        int triangles = 0;
        int[][] triangleSides = new int[3][3];
        while((line = br.readLine()) != null) {
            String parts[] = line.trim().split("\\s+");

            for(int i = 0; i < parts.length; i++){
                triangleSides[triangles][i] = Integer.valueOf(parts[i]);
            }
            triangles++;

            if(triangles == 3){
                for(int j = 0; j < triangleSides.length; j++){
                    if(isTriangle(triangleSides[0][j],triangleSides[1][j],triangleSides[2][j])){
                        count++;
                    }
                }
                triangles = 0;
            }
        }


        System.out.println(count);
    }

    public static boolean isTriangle(int a, int b, int c){
        int[] arr = {a,b,c};
        Arrays.sort(arr);
        if(arr[0]+arr[1]>arr[2]){
            return true;
        }
        return false;
    }
}
