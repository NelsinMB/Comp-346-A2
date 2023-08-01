package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FCFS extends CPUScheduler {
    
    
    public FCFS(Computer computer, ArrayList<Processor> processors, ArrayList<Process> processes) {
        super(computer, processors, processes);
        initialTransfer(); //transfer processes passed in to waitQueue
        execute();
    }



    /*
     * 1. Check if any processes are on readyQueue.
     *  a. If yes, check if any processor is free.
     *      i. If yes, load process onto free processor.
     *      ii. If no, next.
     *  b. If no, next.
     * 2. Execute processors. (processes on processors (need to check if not null))
     * 3. Update IO. (waitQueues)
     * 4. 
     */
    public void execute() {
        while (true) {
            super.setActive(false);
            while (!super.getReadyQueue().isEmpty()) {
                super.setActive(true);
                Process nextProcess = super.getReadyQueue().remove();
                Processor freeProcessor = freeProcessor();
                if (freeProcessor() != null) {
                    freeProcessor.setCurrentProcess(nextProcess);
                    super.readyToRunning(freeProcessor, nextProcess);
                } else {
                    break; //Leave while loop if no processor is free
                }
            }
            tick();
            tickPrint();

            if (getActive() == false) {
                break;
            }

        }
        
    }

    public void tick() {
        for (Processor processor : super.getProcessors()) {
            if (processor.getCurrentProcess() != null) {
                super.setActive(true);
                processor.getCurrentProcess().executeInstruction();
                if (processor.getCurrentProcess().getPCB().getProcessState() != State.RUNNING) {
                    processor.setCurrentProcess(null);
                } 
            }
            //Handle cases where process has entered WAITING or TERMINATED
           
        }
        getComputer().getIO().tick();
       
        
    }

    public void tickPrint() {
        System.out.println("======");
        for (Processor processor : super.getProcessors()) {
            if (processor.getCurrentProcess() != null) {
                System.out.println("Process ID:" + processor.getCurrentProcess().getProcessID());
                System.out.println("program counter: " + processor.getCurrentProcess().getPCB().getProgramCounter());
            }
        }

        for (Process process : getComputer().getIO().getWaitQueue()) {
            System.out.println("The proceess with ID " +  process.getProcessID() + " has " + process.getPCB().getWaitQueueTime() + " time left in the wait queue");
        }
        System.out.println("=======");
    }

    /*
     * Processor freeProcessor
     * This method runs through the ArrayList of processors checking whether any processor has a null currentProcess
     * A null currentProcess indicates that the processor is currently free
     * Returns a processor that is free, if there is one, else, returns null
     */
    public Processor freeProcessor() {
        for (Processor processor : super.getProcessors()) {
            if (processor.getCurrentProcess() == null) {
                return processor;
            }
        } 
        return null;
    }

    /*
     * void initialTransfer
     * This method is responsible for the initial transfer of processes to the readyQueue.
     * The head node is the process with the earliest arrival time, the tail node is the process with the latest arrival time.
     */
    public void initialTransfer() {
        Collections.sort(super.getProcesses(), Comparator.comparing(Process::getArrivalTime));
        for (int index = 0; index < super.getProcesses().size(); index++) {
            newToReady(super.getProcesses().get(index));
        }
    }

    
    
    
}
