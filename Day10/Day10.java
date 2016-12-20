import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day10 {
    static int[] output = new int[200];
    static List<Bot> bots = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File("./Day10/input.txt"));
        List<String> instructions = new ArrayList<String>();
        while(scanner.hasNextLine()){
            instructions.add(scanner.nextLine()); // parse instructions line by line from file
        }

        // initialize some bots
        for(int i = 0; i < 250; i++){
            Bot b = new Bot(i);
            bots.add(b);
        }

        int index = 0;
        while(instructions.size() > 0){ // loop through instructions until everything is completed
            String[] words = instructions.get(index).split(" ");

            if(words[0].equals("bot")){ // case 1: passing on values to output or other bots
                Bot botA = getBotWithID(Integer.parseInt(words[1]));
                if(botA.hasTwoValues()){
                    int high = botA.getHigh();
                    int low = botA.getLow();
                    if(high == 61 && low == 17){ // if this comparison is made we found the bot
                        System.out.println(botA.ID); // Part 1 answer: 86
                    }

                    if(words[5].equals("bot")) {
                        Bot botLow = getBotWithID(Integer.parseInt(words[6]));
                        botLow.add(low);
                    }
                    else{
                        output[Integer.parseInt(words[6])] = low;
                    }

                    if(words[10].equals("bot")){
                        Bot botHigh = getBotWithID(Integer.parseInt(words[11]));
                        botHigh.add(high);
                    }
                    else{
                        output[Integer.parseInt(words[11])] = high;
                    }
                    instructions.remove(index);
                }
            }
            else{ // case 2: giving a value to a bot
                int valToAdd = Integer.parseInt(words[1]);
                Bot bot = getBotWithID(Integer.parseInt(words[5]));
                bot.add(valToAdd);
                instructions.remove(index);
            }

            if (instructions.size() == 0) break;
            index = (index + 1) % instructions.size();
        }

        System.out.println(output[0]*output[1]*output[2]); // Part 2 answer: 22847
    }

    public static Bot getBotWithID(int id){
        for(Bot b : bots) {
            if (b.ID == id)
                return b;
        }
        return null;
    }

    public static class Bot{
        public int ID;
        public ArrayList<Integer> values = new ArrayList<>();

        public Bot(int id){
            this.ID = id;
        }

        public boolean hasTwoValues(){
            return values.size() == 2;
        }

        public void add(int value){
            values.add(value);
        }

        public int getHigh(){
            int a = values.get(0);
            int b = values.get(1);
            return a > b ? a : b;
        }

        public int getLow(){
            int a = values.get(0);
            int b = values.get(1);
            return a > b ? b : a;
        }
    }
}
