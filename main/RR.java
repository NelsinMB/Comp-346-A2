package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RR extends CPUScheduler{

    public RR(Computer computer, ArrayList<Processor> processors, ArrayList<Process> processes) {
        super(computer, processors, processes);
        //TODO Auto-generated constructor stub
    }
    
     /*
     * This function adds processes passed to the scheduler into the ready queue.
     * The head node is the process with the earliest arrival time, the tail node is the process with the latest arrival time.
     */
    public void loadProcessesIntoReadyQueue() {
        Collections.sort(super.getProcesses(), Comparator.comparing(Process::getArrivalTime));
        for (int index = 0; index < super.getProcesses().size(); index++) {
            super.getReadyQueue().add(super.getProcesses().get(index));
        }
        
    }

}