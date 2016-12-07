import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class AoC {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader("-")); //file path ommited
        String line = "";
        Map<Integer, Map<Character, Integer>> posToLetterCount = new HashMap<>();

        while((line=br.readLine())!=null){
            char[] charArray = line.toCharArray();
            for(int i = 0; i < charArray.length; i++){
                posToLetterCount.putIfAbsent(i, new HashMap<>());
                Map<Character,Integer> temp = posToLetterCount.get(i);
                if(temp.containsKey(charArray[i])){
                    int count = temp.get(charArray[i]);
                    temp.put(charArray[i],count+1);
                }
                else{
                    temp.put(charArray[i],1);
                }

            }
        }

        String maxString = "";
        String minString = "";
        for(Map<Character,Integer> each : posToLetterCount.values()){
            int max = each.get('a');
            Character maxkey = 'a';

            int min = each.get('a');
            Character minkey = 'a';
            for(Character key : each.keySet()){
                if(each.get(key) > max){
                    max = each.get(key);
                    maxkey = key;
                }
                if(each.get(key) < min){
                    min = each.get(key);
                    minkey = key;
                }
            }
            maxString += maxkey;
            minString += minkey;
        }

        System.out.println("Part 1: "+maxString);
        System.out.println("Part 2: "+minString);

    }
}
