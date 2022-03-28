package avaj.simulator;

public class ParsingException extends Exception {
    public ParsingException() {
        super();
    }

    public ParsingException(String s) {
        super(s);
    }

    public static void testParsing(String line) throws ParsingException {
        String[] lineSplit = line.split(" ");
        if (lineSplit.length != 5) {
            throw new ParsingException("Error in this line " + line + "\nA Aircraft had to have this format: TYPE NAME LONGITUDE LATITUDE HEIGHT.");
        }
        if (!(lineSplit[0].equals("Baloon"))
            && !(lineSplit[0].equals("JetPlane"))
            && !(lineSplit[0].equals("Helicopter"))) {
                throw new ParsingException("\nError in this line " + line
                + "\nType can be: Baloon, Helicopter, JetPlane.");
        }
        try  {
            if (Integer.parseInt(lineSplit[2]) < 0
                || Integer.parseInt(lineSplit[3]) < 0
                || Integer.parseInt(lineSplit[4]) < 0
                || Integer.parseInt(lineSplit[4]) > 100) {
                    throw new ParsingException("Error in this line " + line
                    + "\nThe coordonate had to be positive numbers and the height cannot be higher than 100.\n");
                }
        }
        catch (NumberFormatException e) {
            throw new ParsingException("Error in this line " + line
            + "\nThe coordonate had to be positive numbers and the height cannot be higher than 100.\n");
        }
    }
}
