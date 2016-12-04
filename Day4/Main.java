import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\-\\Desktop\\Code\\Day4\\src\\input.txt")); //removed name from file path

        int sum = 0;
        String letters="";
        Integer sectorID = null;
        String checksum = "";

        String inputLine;
        Pattern regex = Pattern.compile("((([a-z]+-)+)(\\d{3}))(\\[([a-z]{5})\\])");

        while((inputLine = br.readLine())!=null){
            // method argument for matcher.group signifies the nth capturing group in the regex
            // use the patterns and matcher to initialize the letters, sectorID, and checksum
            Matcher matcher = regex.matcher(inputLine);
            if(matcher.find()){
                letters = matcher.group(2).replace("-","");
                sectorID = Integer.valueOf(matcher.group(4));
                checksum = matcher.group(6);
            }

            String expectedCheckSum = getExpectedCheckSum(letters);
            if(expectedCheckSum.equals(checksum)) {
                sum += sectorID;
            }
        }

        // print answer
        System.out.println(sum);
    }

    // returns the expected checksum given the letters
    // special thanks to stackoverflow
    public static String getExpectedCheckSum(String letters){
        HashMap<Character, Integer> countChar = new HashMap<>();
        for(char c : letters.toCharArray()){
            if(countChar.containsKey(c)){
                int count = countChar.get(c);
                countChar.put(c, count+1);
            }
            else{
                countChar.put(c,1);
            }
        }

        String expected = countChar.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // sort alphabetically
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue())) //sort by occurrence in descending order
                .limit(5)
                .map(Map.Entry::getKey)
                .map(x -> Character.toString(x))
                .collect(Collectors.joining(""));

        return expected;
    }
}
