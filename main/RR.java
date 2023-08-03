package main;

import java.util.ArrayList;

public class RR extends CPUScheduler {

    public RR(Computer computer, ArrayList<Processor> processors, ArrayList<Process> processes) {
        super(computer, processors, processes);
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

            //For Round Robin preemption
            for (Processor processor : getProcessors()) {
                if (processor.getCurrentProcess() != null) {
                    Process process = processor.getCurrentProcess();
                    if (process.getPCB().getTimeOnCPU() == getComputer().getTimeQuantum()) {
                        runningToWaiting(processor, process);
                    }
                }
               
            }

            while (!super.getReadyQueue().isEmpty()) {
                Process nextProcess = super.getReadyQueue().element();
                Processor freeProcessor = freeProcessor();
                if (freeProcessor() != null) {
                    freeProcessor.setCurrentProcess(nextProcess);
                    super.readyToRunning(freeProcessor, nextProcess);
                    super.getReadyQueue().remove(); //Rather than removing when defining nextProcess, ensure a free processor is found then remove.
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
