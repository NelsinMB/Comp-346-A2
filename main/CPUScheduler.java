package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CPUScheduler {

    private Computer computer;
    private ArrayList<Processor> processors;
    private ArrayList<Process> processes;
    private Queue<Process> readyQueue = new LinkedList<Process>();
    private boolean active;
    private int clock = 0;


    public CPUScheduler(Computer computer, ArrayList<Processor> processors, ArrayList<Process> processes ) {
        this.computer = computer;
        computer.setCPUScheduler(this); //Necessary
        this.processors = processors;
        this.processes = processes;
        this.active = true;
    }

    public void newToReady(Process process) {
        getReadyQueue().add(process);
        process.newToReady();
    }
    
    public void readyToRunning(Processor processor, Process process) {
        processor.setCurrentProcess(process);
        process.readyToRunning(processor);
    }

    public void readyToWaiting(Process process) {
        process.readyToWaiting();
        computer.getIO().getWaitQueue().add(process); 
        // *Need to manage processes on waitQueue *
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
    
    public Queue<Process> getReadyQueue() {
        return this.readyQueue;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
   

    public int getClock() {
        return this.clock;
    }

    public void setClock(int clock) {
        this.clock = clock;
    }

   

}
