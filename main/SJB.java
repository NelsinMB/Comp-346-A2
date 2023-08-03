package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Queue;

public class SJB extends CPUScheduler {

    public SJB(Computer computer, ArrayList<Processor> processors, ArrayList<Process> processes) {
        super(computer, processors, processes);
        execute();

    }

    public void execute() {
        while (true) {
            super.execute();

            while (!super.getReadyQueue().isEmpty()) {
                // Sort readyQueue in order of totalExecTime here.
                Collections.sort(getReadyQueue(), new totalExecTimeComparator());
                Process nextProcess = super.getReadyQueue().element();
                Processor freeProcessor = freeProcessor();
                if (freeProcessor() != null) {
                    freeProcessor.setCurrentProcess(nextProcess);
                    super.readyToRunning(freeProcessor, nextProcess);
                    super.getReadyQueue().remove();
                    if (nextProcess.getRanBefore() == false) {
                        nextProcess.setResponseTime(getClock()-nextProcess.getArrivalTime());
                        nextProcess.setRanBefore(true);
                    }
                } else {
                    break; // Leave while loop if no processor is free
                }
            }
            if (getReadyQueue().isEmpty() && getComputer().getIO().getWaitQueue().isEmpty() && !activeProcessor()) {
                finalOutput();
                break;
            }
            tick();

            super.setClock(getClock() + 1);

        }

    }

}
