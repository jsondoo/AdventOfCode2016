import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day11 {
    public static void main(String[] args) throws FileNotFoundException{
        List<String> instructions = new ArrayList<>();

        Scanner sc = new Scanner(new File("./Day11/input.txt"));
        while(sc.hasNextLine()){
            instructions.add(sc.nextLine());
        }

        Map<String, Integer> register = new HashMap<>();
        register.put("a",0);
        register.put("b",0);
        //register.put("c",0);
        register.put("c",1);
        register.put("d",0);

        int index = 0;
        while (index < instructions.size()) {
            String[] parts = instructions.get(index).split(" ");
            switch(parts[0]){
                case "cpy":
                    if(parts[1].matches("a|b|c|d")){
                        int temp = register.get(parts[1]);
                        register.put(parts[2],temp);
                        index++;
                    }
                    else{
                        register.put(parts[2],Integer.parseInt(parts[1]));
                        index++;
                    }
                    break;
                case "inc":
                    int inc = register.get(parts[1]) + 1;
                    register.put(parts[1], inc);
                    index++;
                    break;
                case "dec":
                    int dec = register.get(parts[1]) - 1;
                    register.put(parts[1], dec);
                    index++;
                    break;
                default: //case jnz
                    int x;
                    if(parts[1].matches("a|b|c|d")){
                        x = register.get(parts[1]);
                    }
                    else{
                        x = Integer.parseInt(parts[1]);
                    }

                    if(x!=0){
                        index += Integer.parseInt(parts[2]);
                    }
                    else{
                        index++;
                    }
                    break;
            }
        }

        System.out.println(register.get("a")); // part 1: 318083, part 2: 9227737
    }

}
