import java.security.MessageDigest;

public class Main {
    public static void main(String[] args) throws Exception{
        String input = "ojvtpuvg";
        int index = 0;

        /* PART 1
        String password1 = "";
        while(password1.length() < 8){
            String md5hash = getHashedString(input+index);
            if(md5hash.substring(0,5).equals("00000")){
                password1 += md5hash.substring(5,6);
                System.out.println("Found ("+password1.length()+") :" +md5hash);
            }
            index++;
        }
        System.out.println("Password: "+password1); // part 1 answer, ~14 seconds
        */

        // PART 2
        char[] password = {' ',' ',' ',' ',' ',' ',' ',' '};
        int count = 0;
        while(count < 8){
            String hash = getHashedString(input+index);
            int hashIndex = Integer.parseInt(Character.toString(hash.charAt(5)), 16);
            if(hash.substring(0,5).equals("00000") && hashIndex >= 0 && hashIndex < 8 && password[hashIndex] == ' '){
                password[hashIndex] = hash.charAt(6);
                count++;
            }
            index++;
        }
        System.out.println(new String(password)); // part 2 answer, ~36 seconds
    }

    // Returns MD5-hashed string in hexadecimal
    // For reference: http://stackoverflow.com/questions/415953/how-can-i-generate-an-md5-hash
    static String getHashedString(String str) throws Exception{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes("UTF-8"));
        byte[] digest = md.digest();

        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < digest.length; i++){
            sb.append(Integer.toHexString((digest[i] & 0xFF)|0x100).substring(1,3));
        }
        return sb.toString();
    }
}
