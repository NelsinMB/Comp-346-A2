package main;

import java.util.LinkedList;
import java.util.Queue;

public class Processor {
    //Clean

    private int processorID;
    private Process currentProcess;
    private Queue<Process> readyQueue = new LinkedList<Process>(); //Does each process still have a queue?
    private Computer computer;
    private int activeCycles; //Used to calculate CPU utilization

    public Processor(Computer computer, int processorID) {
        this.computer = computer;
        this.processorID = processorID;
    }

    public Process getCurrentProcess() {
        return this.currentProcess;
    }

    public void setCurrentProcess(Process currentProcess) {
        this.currentProcess = currentProcess;
    }

    public Queue<Process> getReadyQueue() {
        return this.readyQueue;
    }

    public void setReadyQueue(Queue<Process> readyQueue) {
        this.readyQueue = readyQueue;
    }

    public Computer getComputer() {
        return this.computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public int getProcessorID() {
        return this.processorID;
    }

    public void setProcessorID(int processorID) {
        this.processorID = processorID;
    }

    public int getActiveCycles() {
        return this.activeCycles;
    }

    public void setActiveCycles(int activeCycles) {
        this.activeCycles = activeCycles;
    }
    
}

