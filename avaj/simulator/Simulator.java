package avaj.simulator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import avaj.aircraft.AircraftFactory;
import avaj.flyable.Flyable;
import avaj.hashing.Hashing;
import avaj.tower.WeatherTower;

public class Simulator {
    private static int numbersCycles;
    private static StringBuilder printable = new StringBuilder();

    private static void setupAircraft(WeatherTower weatherTower) {
        String[] listAircraft = printable.toString().split("\n");

        for (int i = 0; i < listAircraft.length; i++) {
            String[] aircraft = listAircraft[i].split(" ");
            Flyable fly = AircraftFactory.newAircraft(aircraft[0], aircraft[1], Integer.parseInt(aircraft[2]),
            Integer.parseInt(aircraft[3]), Integer.parseInt(aircraft[4]));
            fly.registerTower(weatherTower);
        }
    }

    private static void runSimulation() {
        WeatherTower weatherTower = new WeatherTower();
        setupAircraft(weatherTower);
        for (int i = 0; i < numbersCycles; i++) {
            weatherTower.changeWeather();
        }
    }

    private static boolean initSimulation(String path, boolean md5) {
        try (BufferedReader br = new BufferedReader(new FileReader(path)))
            {
            String line;
            boolean first = true;

            while ((line = br.readLine()) != null) {
                if (md5)
                    line = Hashing.checkHash(line, first);
                if (first) {
                    numbersCycles = Integer.parseInt(line);
                    if (numbersCycles < 0) {
                        System.out.println("The first line of the scenario must be the number of time the simulation runs.");
                        return (false);
                    }
                    first = false;
                }
                else {
                    ParsingException.testParsing(line);
                    printable.append(line + "\n");
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            return (false);
        }
        catch (NumberFormatException e) {
            System.out.println("The first line of the scenario must be the number of time the simulation runs.");
            return (false);
        }
        catch (ParsingException e) {
            System.out.println(e.getMessage());
            return (false);
        }
        catch (NoSuchAlgorithmException e) {
            System.out.println(e);
            return (false);
        }
        return (true);
    }
    public static void main(String[] args) {
        if (args.length != 1) {
            if (args.length == 2 && args[0].equals("-md5")) {
                if (!initSimulation(args[1], true))
                    return;
            }
            else {
                System.out.println("Avaj-launcher error.\nNeed a scenario to run a simulation.");
                return ;
            }
        }
        else
            if (!initSimulation(args[0], false))
                return ;
        runSimulation();
    }
}