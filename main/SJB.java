package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SJB extends CPUScheduler {



    public SJB(Computer computer, ArrayList<Processor> processors, ArrayList<Process> processes) {
        super(computer, processors, processes);
        initialTransfer();
        execute();

    }

    public void execute() {
        for (Process p : this.getReadyQueue()) {
            System.out.println(p.getProcessID()); 
        }
    }

    /*
     * void initialTransfer
     * This method is responsible for the initial transfer of processes to the readyQueue.
     * The head node is the process with the earliest arrival time, the tail node is the process with the latest arrival time.
     */
    public void initialTransfer() {
        Collections.sort(super.getProcesses(), Comparator.comparing(Process::getTotalExecTime));
        for (int index = 0; index < super.getProcesses().size(); index++) {
            super.getReadyQueue().add(super.getProcesses().get(index));
        }
    }



}
