package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class FCFS extends CPUScheduler {

    private Computer computer;
    private ArrayList<Processor> processors;
    private ArrayList<Process> processes;
    private Queue<Process> readyQueue = new LinkedList<Process>(); 

    public FCFS(Computer computer, ArrayList<Processor> processors, ArrayList<Process> processes) {
        super(computer, processors, processes);
        loadProcessesIntoReadyQueue();

    }

    /*
     * This function adds processes passed to the scheduler into the ready queue.
     * The head node is the process with the earliest arrival time, the tail node is the process with the latest arrival time.
     */
    public void loadProcessesIntoReadyQueue() {
        Collections.sort(processes, Comparator.comparing(Process::getArrivalTime));


    }


    
}
