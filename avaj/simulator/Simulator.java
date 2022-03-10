package avaj.simulator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Simulator {

    private static int numbers_cycles;
    private static String printable;

    private static void init_simulation(String path) {
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String line;
            boolean first = true;
            line = br.readLine();
            while (line != null) {
                if (first == true) {
                    numbers_cycles = Integer.parseInt(line);
                    if (numbers_cycles < 0) {
                        System.out.println("The first line of the scenario must be the number of time the simulation runs.");
                        return ;
                    }
                    first = false;
                }
                else {
                    System.out.println(line);
                }
                line = br.readLine();
            }
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            return ;
        }
        catch (NumberFormatException e) {
            System.out.println("The first line of the scenario must be the number of time the simulation runs.");
            return ;
        }
    }
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Avaj-launcher error.\nNeed a scenario to run a simulation.");
            return ;
        }
        init_simulation(args[0]);
    }
}