package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Queue;

public class SJB extends CPUScheduler {



    public SJB(Computer computer, ArrayList<Processor> processors, ArrayList<Process> processes) {
        super(computer, processors, processes);
        //initialTransfer();
        execute();

    }

    public void execute() {

        // Add processes that have arrival times that correspond with clock
        while (true) {
            for (Process process : getProcesses()) {
                if (super.getClock() == process.getArrivalTime()) {
                    newToReady(process);
                }
            }

            while (!super.getReadyQueue().isEmpty()) {
                //Sort readyQueue in order of totalExecTime here.
                Collections.sort(getReadyQueue(), new totalExecTimeComparator());
                Process nextProcess = super.getReadyQueue().element();
                Processor freeProcessor = freeProcessor();
                if (freeProcessor() != null) {
                    freeProcessor.setCurrentProcess(nextProcess);
                    super.readyToRunning(freeProcessor, nextProcess);
                    super.getReadyQueue().remove();
                } else {
                    break; // Leave while loop if no processor is free
                }
            }
            if (getReadyQueue().isEmpty() && getComputer().getIO().getWaitQueue().isEmpty() && !activeProcessor()) {
                break;
            }
            tick();

            super.setClock(getClock() + 1);

        }

    }





}
