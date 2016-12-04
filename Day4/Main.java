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
    public static void main(String[] args) throws IOException{/
        BufferedReader br = new BufferedReader(new FileReader("-")); //filepath omitted
        int sum = 0;
        String letters="";
        Integer sectorID = null;
        String checksum = "";

        String inputLine;
        Pattern regex = Pattern.compile("((([a-z]+-)+)(\\d{3}))(\\[([a-z]{5})\\])");

        while((inputLine = br.readLine())!=null){
            Matcher matcher = regex.matcher(inputLine);
            if(matcher.find()){ // use regex to initialize letters, sectorID, and checksum
                letters = matcher.group(2);
                sectorID = Integer.valueOf(matcher.group(4));
                checksum = matcher.group(6);
            }


            String expectedCheckSum = getExpectedCheckSum(letters.replace("-",""));
            if(expectedCheckSum.equals(checksum)) {
                sum += sectorID;

                // part 2
                decrypt(letters, sectorID);
            }
        }

        // print part 1 answer
        System.out.println("Sum of sectorID of valid rooms: "+sum);
    }

    public static void decrypt(String str, int sID) {
        int rotate = sID % 26; // number of times to rotate
        str = str.replace("-", " ").trim();
        while(rotate>0){
            String temp = "";
            for (char ch : str.toCharArray()) {
                if (ch == 'z') {
                    temp += "a";
                } else if (ch == ' ') {
                    temp += " ";
                } else {
                    temp += Character.toString(++ch); // increase ASCII code by one
                }
            }
            str = temp;
            rotate--;
        }

        if(str.contains("north")) { // print part 2 answer
            System.out.println("North Pole Object Storage Sector ID: "+sID);
        }

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
