package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FCFS extends CPUScheduler {
    
    
    public FCFS(Computer computer, ArrayList<Processor> processors, ArrayList<Process> processes) {
        super(computer, processors, processes);
        // initialTransfer(); //transfer processes passed in to waitQueue
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

        //Add processes that have arrival times that correspond with clock
        while (true) {
            for (Process process : getProcesses()) {
                if (super.getClock() == process.getArrivalTime()) {
                    newToReady(process);
                }
            }


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

            if (getActive() == false) {
                break;
            }

            super.setClock(getClock() + 1);

        }
        
    }

    public void tick() {
        for (Processor processor : super.getProcessors()) {
            if (processor.getCurrentProcess() != null) {
                super.setActive(true);
                if (processor.getCurrentProcess().getPCB().getProcessState() == State.RUNNING) {
                processor.getCurrentProcess().executeInstruction();
                }
              if (processor.getCurrentProcess().getPCB().getProcessState() != State.RUNNING) {
                    processor.setCurrentProcess(null);
                } 
            }
            //Handle cases where process has entered WAITING or TERMINATED
           
        }
        System.out.println("======");
        System.out.println("CLOCK: " + super.getClock());
        for (Processor processor : super.getProcessors()) {
            if (processor.getCurrentProcess() != null) {
                System.out.println("Process ID:" + processor.getCurrentProcess().getProcessID());
                System.out.println("Program counter (next instruction): " + processor.getCurrentProcess().getPCB().getProgramCounter());
            }
        }

        System.out.println("Wait queue: ");
        for (Process process : getComputer().getIO().getWaitQueue()) {
            System.out.println("Time at IO for process with process ID " + process.getProcessID() + " is " + process.getPCB().getTimeAtIO());
        }
        System.out.println("=======");
    
        getComputer().getIO().tick();
       
        
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
