package main;

import java.util.ArrayList;

public class FCFS extends CPUScheduler {

    public FCFS(Computer computer, ArrayList<Processor> processors, ArrayList<Process> processes) {
        super(computer, processors, processes);
        // initialTransfer(); //transfer processes passed in to waitQueue
        execute();
    }

    /*
     * 1. Check if any processes are on readyQueue.
     * a. If yes, check if any processor is free.
     * i. If yes, load process onto free processor.
     * ii. If no, next.
     * b. If no, next.
     * 2. Execute processors. (processes on processors (need to check if not null))
     * 3. Update IO. (waitQueues)
     * 4.
     */
    public void execute() {

        // Add processes that have arrival times that correspond with clock
        while (true) {
            for (Process process : getProcesses()) {
                if (super.getClock() == process.getArrivalTime()) {
                    newToReady(process);
                }
            }

            while (!super.getReadyQueue().isEmpty()) {
                Process nextProcess = super.getReadyQueue().remove();
                Processor freeProcessor = freeProcessor();
                if (freeProcessor() != null) {
                    freeProcessor.setCurrentProcess(nextProcess);
                    super.readyToRunning(freeProcessor, nextProcess);
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
