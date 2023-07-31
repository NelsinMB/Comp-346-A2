package main;

import java.util.ArrayList;

public class Computer {

    private Algorithm algorithm;
    private ArrayList<Processor> processors;
    private ArrayList<Process> processes;
    private int numberOfCPUs;
    private int timeQuantum;
    private CPUScheduler CPUScheduler; 


    public Computer(Algorithm algorithm, int numberOfCPUs, int timeQuantum, ArrayList<Process> processes) {
        this.algorithm = algorithm;
        this.numberOfCPUs = numberOfCPUs;
        this.timeQuantum = timeQuantum;
        this.processes = processes;

        //Create processors, number is indicated by numberOfCPUs
        for (int processorIndex = 0; processorIndex < numberOfCPUs; processorIndex++) {
            processors.add(new Processor());
        }

        //Choose which scheduling class to use
        switch (algorithm) {
            case FCFS:
            this.CPUScheduler = new FCFS(this, processors, processes); //Pass the computer, processors, and processes to scheduler
            break;

            case SJB:
            this.CPUScheduler = new SJB(this, processors, processes); //Pass the computer, processors, and processes to scheduler
            break;

            case RR:
            this.CPUScheduler = new RR(this, processors, processes); //Pass the computer, processors, and processes to scheduler
            break;
        }



    }

    public Algorithm getAlgorithm() {
        return this.algorithm;
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public ArrayList<Processor> getProcessors() {
        return this.processors;
    }

    public void setProcessors(ArrayList<Processor> processors) {
        this.processors = processors;
    }

    public ArrayList<Process> getProcesses() {
        return this.processes;
    }

    public void setProcesses(ArrayList<Process> processes) {
        this.processes = processes;
    }

    public int getNumberOfCPUs() {
        return this.numberOfCPUs;
    }

    public void setNumberOfCPUs(int numberOfCPUs) {
        this.numberOfCPUs = numberOfCPUs;
    }

    public int getTimeQuantum() {
        return this.timeQuantum;
    }

    public void setTimeQuantum(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }
    

}
