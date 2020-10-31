package helpers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {
	
	public String generateHash(String password) {
		String Hashpassword = null;
		try {
			MessageDigest mdigest = MessageDigest.getInstance("SHA-256");
			byte[] hash = mdigest.digest(password.getBytes());
			StringBuilder sbuilder = new StringBuilder();
			for(byte encryptationhash: hash) {
				sbuilder.append(String.format("%02x", encryptationhash));
			}
			Hashpassword = sbuilder.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return Hashpassword;
	}

}
