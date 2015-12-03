import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt 
{
    public String password;
    public String generatedPassword;
    
	public Encrypt()
        {
            
	}
        //You set the password that you want to turn into a hash.
        public void setPassword(String password)
        {
            this.password = password;
        }
        //Grabs the password that was just turned into a Hash
        public String getGeneratedPassword()
        {
            return generatedPassword;
        }
        //This fucntion takes the password and generates it into a Hash
        public void encryptPassword()
        {
            try {
                    // Create MessageDigest instance for MD5
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    //Add password bytes to digest
                    md.update(password.getBytes());
                    //Get the hash's bytes 
                    byte[] bytes = md.digest();
                    //This bytes[] has bytes in decimal format;
                    //Convert it to hexadecimal format
                    StringBuilder sb = new StringBuilder();
                    for(int i=0; i< bytes.length ;i++)
                    {
                            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                    }
                    //Get complete hashed password in hex format
                    generatedPassword = sb.toString();
            } 
            catch (NoSuchAlgorithmException e) 
            {
                    e.printStackTrace();
            }
            //Debug
            //System.out.println(generatedPassword);
        }
}
