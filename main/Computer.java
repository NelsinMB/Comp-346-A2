package main;

import java.util.ArrayList;
import java.util.Queue;

public class Computer {

    private Algorithm algorithm;
    private ArrayList<Processor> processors;
    private ArrayList<Process> processes;
    private int numberOfCPUs;
    private int timeQuantum;
    private CPUScheduler CPUScheduler;
    private IO IO;

    public Computer(Algorithm algorithm, int numberOfCPUs, int timeQuantum, ArrayList<Process> processes) {
        this.algorithm = algorithm;
        this.numberOfCPUs = numberOfCPUs;
        this.timeQuantum = timeQuantum;
        this.processes = processes;
        this.processors = new ArrayList<Processor>();
        this.IO = new IO();

        // Create processors, number is indicated by numberOfCPUs
        for (int processorIndex = 0; processorIndex < numberOfCPUs; processorIndex++) {
            processors.add(new Processor(this, processorIndex));
        }

        // Choose which scheduling class to use
        switch (algorithm) {
            case FCFS:
                setCPUScheduler(new FCFS(this, processors, processes)); // Pass the computer, processors, and processes
                                                                        // to scheduler
                break;

            case SJB:
                setCPUScheduler(new SJB(this, processors, processes)); // Pass the computer, processors, and processes
                                                                       // to scheduler
                break;

            case RR:
                setCPUScheduler(new RR(this, processors, processes)); // Pass the computer, processors, and processes to
                                                                      // scheduler
                break;
        }

    }

    public IO getIO () {
        return this.IO;
    }

    public void setIO(IO IO) {
        this.IO = IO;
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

    public CPUScheduler getCPUScheduler() {
        return this.CPUScheduler;
    }

    public void setCPUScheduler(CPUScheduler CPUScheduler) {
        this.CPUScheduler = CPUScheduler;
    }

}
