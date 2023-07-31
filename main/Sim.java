import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Sim {

    static int numberOfCPUs;
    static int timeQuantum;
    static ArrayList<Integer> processes = new ArrayList<Integer>();
    HashMap<String, Integer> arrivalTimes = new HashMap<String, Integer>();
    HashMap<String, Integer> totalExecTimes = new HashMap<String, Integer>();
    HashMap<String, int[]> IOrequestTimes = new HashMap<String, int[]>();

    public static void main(String args[]) {
        try {
            Scanner scanner = new Scanner(new File("/Users/nelsin/Desktop/Code/Assignment 2/input.txt"));
            String numOfCPUsString = scanner.nextLine();
            String timeQuantumString = scanner.nextLine();
            ArrayList<String> processesString = new ArrayList<String>();

            scanner.nextLine();
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                processesString.add(scanner.nextLine());
            }
            numberOfCPUs = extractNumOfCPUs(numOfCPUsString);
            timeQuantum = extractTimeQuantum(timeQuantumString);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int extractNumOfCPUs(String numOfCPUsString) {
        int numOfCPUs = Integer.valueOf(numOfCPUsString.replaceAll("[^0-9]", ""));
        return numOfCPUs;

    }

    public static int extractTimeQuantum(String timeQuantumString) {
        int timeQuantum = Integer.valueOf(timeQuantumString.replaceAll("[^0-9]", ""));
        return timeQuantum;
    }

    public static ArrayList<Integer> extractProcesses(ArrayList<String> processes) {
        ArrayList<Integer> IORequestAtTimesArray = new ArrayList<Integer>();
        for (String s : processes) {
            System.out.println(s);
            String chopped[] = s.split("\t");
            String IORequestAtTimes = chopped[3];
            IORequestAtTimes = IORequestAtTimes.substring(1, IORequestAtTimes.length() - 1);
            System.out.println(IORequestAtTimes);
            String choppedAgain[] = IORequestAtTimes.split(",");
            for (String p : choppedAgain) {
                if (p != "") {
                    IORequestAtTimesArray.add(Integer.valueOf(p));
                }

            }

        }
        return IORequestAtTimesArray;
    }

}
