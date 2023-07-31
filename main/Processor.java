package main;

import java.util.LinkedList;
import java.util.Queue;

public class Processor {
    private Process currentProcess;
    private Queue<Process> readyQueue = new LinkedList<Process>(); //Does each process still have a queue?


    public Processor() {

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
    
}

