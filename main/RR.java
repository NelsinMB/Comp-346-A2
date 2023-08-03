package main;

import java.util.ArrayList;

public class RR extends CPUScheduler {

    public RR(Computer computer, ArrayList<Processor> processors, ArrayList<Process> processes) {
        super(computer, processors, processes);
        execute();
    }

    public void execute() {
        while (true) {
            super.execute();

            // For Round Robin preemption
            for (Processor processor : getProcessors()) {
                if (processor.getCurrentProcess() != null) {
                    Process process = processor.getCurrentProcess();
                    if (process.getPCB().getTimeOnCPU() == getComputer().getTimeQuantum()) {
                        runningToReady(processor, process);
                    }
                }

            }

            while (!super.getReadyQueue().isEmpty()) {
                Process nextProcess = super.getReadyQueue().element();
                Processor freeProcessor = freeProcessor();
                if (freeProcessor() != null) {
                    freeProcessor.setCurrentProcess(nextProcess);
                    super.readyToRunning(freeProcessor, nextProcess);
                    super.getReadyQueue().remove(); // Rather than removing when instantiating nextProcess, ensure a free
                                                    // processor is found then remove.
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
