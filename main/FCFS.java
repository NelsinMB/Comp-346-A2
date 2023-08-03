package main;

import java.util.ArrayList;

public class FCFS extends CPUScheduler {

    public FCFS(Computer computer, ArrayList<Processor> processors, ArrayList<Process> processes) {
        super(computer, processors, processes);
        execute();
    }

    public void execute() {
        while (true) {
            super.execute();

            while (!super.getReadyQueue().isEmpty()) {
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
