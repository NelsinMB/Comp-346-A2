package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SJB extends CPUScheduler {

    private Computer computer;
    private ArrayList<Processor> processors;
    private ArrayList<Process> processes;

    public SJB(Computer computer, ArrayList<Processor> processors, ArrayList<Process> processes) {
        super(computer, processors, processes);
        loadProcessesIntoReadyQueue();

    }

    /*
     * This function adds processes passed to the scheduler into the ready queue.
     * The head node is the process with the shortest exec time, the tail node is
     * the process with the longest exec time.
     */
    public void loadProcessesIntoReadyQueue() {
        Collections.sort(processes, Comparator.comparing(Process::getTotalExecTime));
    }

}
