package main;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Sim {

    static int numberOfCPUs;
    static int timeQuantum;
    static ArrayList<Process> processes = new ArrayList<Process>();
    static Computer computer;


    public static void main(String args[]) {
        try {
            Scanner scanner = new Scanner(new File("/Users/nelsin/Desktop/Code/Assignment 2/input3.txt"));
            String numOfCPUsString = scanner.nextLine();
            String timeQuantumString = scanner.nextLine();
            ArrayList<String> processesString = new ArrayList<String>();

            scanner.nextLine(); //Skip third line
            scanner.nextLine(); //Skip fourth line
            while (scanner.hasNextLine()) {
                processesString.add(scanner.nextLine());
            }
            scanner.close();
            numberOfCPUs = extractNumOfCPUs(numOfCPUsString);
            timeQuantum = extractTimeQuantum(timeQuantumString);
            processes = extractProcesses(processesString);

            System.out.println("What algorithm would you like to run (FCFS, SJB, RR)?");
            Scanner scanner2 = new Scanner(System.in);
            String algorithmInput = scanner2.nextLine();
            switch (algorithmInput) {
                case "FCFS":
                    computer = new Computer(Algorithm.FCFS, numberOfCPUs, timeQuantum, processes);
                break;

                case "SJB": 
                    computer = new Computer(Algorithm.SJB, numberOfCPUs, timeQuantum, processes);

                break;

                case "RR":
                    computer = new Computer(Algorithm.RR, numberOfCPUs, timeQuantum, processes);

                break;
            }
            scanner2.close();

            /* 
            System.out.println("numberOfCPUs: " + numberOfCPUs);
            System.out.println("timeQuantum: " + timeQuantum);
            System.out.println("Processes:" );
            for (Process p : processes) {
                System.out.println("ProcessID: " + p.getProcessID());
                System.out.println("timeQuantum: " + p.getArrivalTime());
                System.out.println("totalExecTime: " + p.getTotalExecTime());
                for (Integer i : p.getIORequestAtTimes()) {
                    System.out.println(i);
                }
            }
            */


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

    public static ArrayList<Process> extractProcesses(ArrayList<String> processesAsStrings) {
        ArrayList<Process> processes = new ArrayList<Process>();
        for (String s : processesAsStrings) {
            String chopped[] = s.split("\t");
            String processID = chopped[0];
            int arrivalTime = Integer.valueOf(chopped[1]);
            int totalExecTime = Integer.valueOf(chopped[2]);

            String IORequestAtTimesString = chopped[3];
            IORequestAtTimesString = IORequestAtTimesString.substring(1, IORequestAtTimesString.length() - 1);
            String IORequestAtTimesChopped[] = IORequestAtTimesString.split(",");
            ArrayList<Integer> IORequestAtTimes = new ArrayList<Integer>();
            for (String time : IORequestAtTimesChopped) {
                if (time != "") { //"" if array is empty
                    IORequestAtTimes.add(Integer.valueOf(time));
                }

            }
            Process newProcess = new Process(processID, arrivalTime, totalExecTime, IORequestAtTimes);
            processes.add(newProcess);
        }
        return processes;
    }

}
