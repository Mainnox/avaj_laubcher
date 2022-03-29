package avaj.hashing;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


import avaj.simulator.ParsingException;

public class Hashing {

    private Hashing() {

    }
    static Map<String, String> typeMap = new HashMap<String, String>();
    
    public static void filltypeMap() throws NoSuchAlgorithmException {
        MessageDigest mdBaloon = MessageDigest.getInstance("MD5");
        mdBaloon.update("Baloon".getBytes(), 0, "Baloon".length());
        String strBaloon = new BigInteger(1, mdBaloon.digest()).toString(16);
        typeMap.put("Baloon", strBaloon);
        MessageDigest mdHelicopter = MessageDigest.getInstance("MD5");
        mdHelicopter.update("Helicopter".getBytes(), 0, "Helicopter".length());
        String strHelicopter = new BigInteger(1, mdHelicopter.digest()).toString(16);
        typeMap.put("Helicopter", strHelicopter);
        MessageDigest mdJetPlane = MessageDigest.getInstance("MD5");
        mdJetPlane.update("JetPlane".getBytes(), 0, "JetPlane".length());
        String strJetPlane = new BigInteger(1, mdJetPlane.digest()).toString(16);
        typeMap.put("JetPlane", strJetPlane);
    }

    public static int checkHashInt(String nbr, int max) throws NoSuchAlgorithmException {
        for (int i = 0; i <= max; i++) {
            MessageDigest mbi = MessageDigest.getInstance("MD5");
            mbi.update(Integer.toString(i).getBytes(), 0, Integer.toString(i).length());
            String strHashi = new BigInteger(1,mbi.digest()).toString(16);
            if (strHashi.equals(nbr))
                return (i);
        }
        return (-1);
    }

    public static String checkHash(String line, boolean first) throws NoSuchAlgorithmException, ParsingException {
        int nbrtour;
        StringBuilder strret = new StringBuilder();

        if (first) {
            nbrtour = checkHashInt(line, 10000);
            System.out.println("CheckHashInt = " + Integer.toString(nbrtour));
            if (nbrtour == -1) {
                throw new ParsingException("The first line of the scenario must be the number of time the simulation runs.");
            }
            else
                return (Integer.toString(nbrtour));
        }
        filltypeMap();
        String[] splitline = line.split(" ");
        if (splitline[0].equals(typeMap.get("Baloon")))
            strret.append("Baloon ");
        else if (splitline[0].equals(typeMap.get("Helicopter")))
            strret.append("Helicopter ");
        else if (splitline[0].equals(typeMap.get("JetPlane")))
            strret.append("JetPlane ");
        else 
            throw new ParsingException("\nError in this line " + line
            + "\nType can be: Baloon, Helicopter, JetPlane.");
        strret.append("MD5 ");
        for (int i = 2; i <  5; i++) {
            int retcoordinate;

            if (i == 4)
                retcoordinate = checkHashInt(splitline[4], 100);
            else
                retcoordinate = checkHashInt(splitline[i], 10000);
            if (retcoordinate == -1)
                throw new ParsingException("Error in this line " + line
                + "\nThe coordonate had to be positive numbers and the height cannot be higher than 100.\n");
            strret.append(Integer.toString(retcoordinate) + " ");
        }
        return (strret.toString());
    }
}