import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AoC {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("-")); // omitted file path

        String line;
        int countValidIPs = 0;
        int countValidSSLs = 0;

        while ((line = br.readLine()) != null) {
            List<String> bracketed = new ArrayList<>();
            List<String> nonBracketed = new ArrayList<>();

            line += "\0"; // add a space at the end to signify end of line
            // bin the strings into the two lists
            String sofar = "";
            for (char ch : line.toCharArray()) {
                sofar += ch;
                if (ch == '[' || ch == '\0') {
                    nonBracketed.add(sofar.substring(0, sofar.length() - 1));
                    sofar = "";
                }
                if (ch == ']') {
                    bracketed.add(sofar.substring(0, sofar.length() - 1));
                    sofar = "";
                }
            }

            if (countABBA(bracketed) == 0 && countABBA(nonBracketed) != 0)
                countValidIPs++;

            if (isValidSSL(bracketed, nonBracketed))
                countValidSSLs++;

        }

        System.out.println(countValidIPs); // part 1 answer
        System.out.println(countValidSSLs); // part 2 answer

    }

    static boolean isValidSSL(List<String> bracketed, List<String> nonBracketed){
        Set<String> ABAs = new HashSet<>();
        Set<String> invertedBABs = new HashSet<>();
        // find all ABAs
        for(String s : nonBracketed){
            for(int i = 0; i <= s.length()-3; i++){
                String sub = s.substring(i,i+3);
                if(sub.charAt(0)==sub.charAt(2) && sub.charAt(0)!=sub.charAt(1)){
                    ABAs.add(sub);
                }
            }
        }

        // find all BABs, but invert it as ABA
        for(String s : bracketed){
            for(int i = 0; i <= s.length()-3; i++){
                String sub = s.substring(i,i+3);
                if(sub.charAt(0)==sub.charAt(2) && sub.charAt(0)!=sub.charAt(1)){
                    invertedBABs.add(""+sub.charAt(1)+sub.charAt(0)+sub.charAt(1));
                }
            }
        }

        // check intersection of sets
        ABAs.retainAll(invertedBABs);
        if(ABAs.size()>0){
            return true;
        }
        return false;
    }

    // counts number of ABBA in a list of strings
    static int countABBA(List<String> strings){
        int count = 0;
        for(String s : strings){
            for(int i = 0; i <= s.length()-4; i++){
                if(isABBA(s.substring(i,i+4))){
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    // checks if the given 4 letter string is valid ABBA
    static boolean isABBA(String s){
        if(s.length()!=4){
            System.out.println("error");
            return false;
        }
        if(s.charAt(0)!=s.charAt(1) && s.charAt(0) == s.charAt(3) && s.charAt(1) == s.charAt(2)){
            return true;
        }
        return false;
    }
}
