package main;

import java.util.ArrayList;

public class CPUScheduler {

    private Computer computer;
    private ArrayList<Processor> processors;
    private ArrayList<Process> processes;


    public CPUScheduler(Computer computer, ArrayList<Processor> processors, ArrayList<Process> processes ) {
        this.computer = computer;
        this.processors = processors;
        this.processes = processes;
    }

    public Computer getComputer() {
        return this.computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
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
    
}
