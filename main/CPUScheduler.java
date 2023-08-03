package main;

import java.util.ArrayList;
import java.util.LinkedList;

public class CPUScheduler {
    // Cleanish
    private Computer computer;
    private ArrayList<Processor> processors;
    private ArrayList<Process> processes;
    private LinkedList<Process> readyQueue = new LinkedList<Process>();
    private boolean active;
    private int clock = 0;

    public CPUScheduler(Computer computer, ArrayList<Processor> processors, ArrayList<Process> processes) {
        this.computer = computer;
        computer.setCPUScheduler(this); // Necessary
        this.processors = processors;
        this.processes = processes;
        this.active = true;
    }

    /*
     * void execute()
     * Shared execute() code amongst FCFS, SJB, RR.
     */
    public void execute() {
        for (Process process : getProcesses()) {
            if (getClock() == process.getArrivalTime()) {
                newToReady(process);
                
            }
        }

    }

    /*
     * boolean activeProcessor()
     * Returns true if a processor currently has a process on it, and that process
     * is running.
     * 
     */
    public boolean activeProcessor() {
        for (Processor processor : getProcessors()) {
            if (processor.getCurrentProcess() != null
                    && processor.getCurrentProcess().getPCB().getProcessState() == State.RUNNING) {
                return true;
            }
        }
        return false;
    }

    /*
     * Processor freeProcessor
     * This method runs through the ArrayList of processors checking whether any
     * processor has a null currentProcess
     * A null currentProcess indicates that the processor is currently free
     * Returns a processor that is free, if there is one, else, returns null
     */
    public Processor freeProcessor() {
        for (Processor processor : getProcessors()) {
            if (processor.getCurrentProcess() == null) {
                return processor;
            }
        }
        return null;
    }

    public void tick() {
        for (Processor processor : getProcessors()) {
            if (processor.getCurrentProcess() != null) {
                if (processor.getCurrentProcess().getPCB().getProcessState().equals(State.RUNNING)) {
                    processor.getCurrentProcess().executeInstruction(getClock());
                    processor.setActiveCycles(processor.getActiveCycles() + 1);
                }
                if (processor.getCurrentProcess().getPCB().getProcessState() != State.RUNNING)
                    processor.setCurrentProcess(null);

            }
        }

        output();
        getComputer().getIO().tick();

    }

    public void newToReady(Process process) {
        getReadyQueue().add(process);
        process.newToReady();
    }

    public void readyToRunning(Processor processor, Process process) {
        processor.setCurrentProcess(process);
        process.readyToRunning(processor);
    }

    public void runningToReady(Processor processor, Process process) {
        processor.setCurrentProcess(null);
        process.runningToReady();
        getReadyQueue().add(process);
    }

    public void readyToWaiting(Process process) {
        process.readyToWaiting();
        computer.getIO().getWaitQueue().add(process);
    }

    public void output() {
        System.out.println("======");
        System.out.println("CLOCK: " + getClock());
        System.out.println("----Active processes----");
        for (Processor processor : getProcessors()) {
            if (processor.getCurrentProcess() != null) {
                System.out.println("    Process ID: " + processor.getCurrentProcess().getProcessID());
                System.out.println("    Program counter (next instruction): "
                        + processor.getCurrentProcess().getPCB().getProgramCounter());
                System.out.println("    Processor ID: " + processor.getProcessorID());
                System.out.println("    Time on CPU (important for RR): " + processor.getCurrentProcess().getPCB().getTimeOnCPU());
            }
        }
        for (Process process : getComputer().getIO().getWaitQueue()) {
            if (process.getPCB().getTimeAtIO() == 0) {
                System.out.println("    Process ID: " + process.getProcessID());
                System.out.println("    Program counter (next instruction): "
                        + process.getPCB().getProgramCounter());
                System.out.println("    Processor ID: " + process.getProcessor().getProcessorID());
                System.out.println("    Time on CPU (important for RR): " + process.getPCB().getTimeOnCPU());
            }
        }
        System.out.println("----Ready processes----");
        for (Process process : getReadyQueue()) {
            System.out.println("    Process ID: " + process.getProcessID());
            System.out.println("    Program counter (next instruction): "
                    + process.getPCB().getProgramCounter());
        }

        System.out.println("----Waiting processes----");
        for (Process process : getComputer().getIO().getWaitQueue()) {
            if (process.getPCB().getTimeAtIO() != 0) {
                System.out.println("    Process ID: " + process.getProcessID());
                System.out.println("    Time at IO: " + process.getPCB().getTimeAtIO());
                System.out.println("    Instruction count: " + process.getPCB().getIOInstructionCount());
            }
        }
    }

    public void finalOutput() {
        System.out.println("======");
        System.out.println("FINAL OUTPUT");
        for (Processor processor : getProcessors()) {
            System.out.println("Processor " + processor.getProcessorID() + " had " + processor.getActiveCycles() + " active cycles out of a possible " + getClock() + ".");
        }
        int totalWaitTime = 0;
        for (Process process : getProcesses()) {
            System.out.println("Process " + process.getProcessID() + " had a turnaround time of " + process.getTurnAroundTime() + " and a response time of " + process.getResponseTime() + ".");
            
            totalWaitTime = totalWaitTime + process.getWaitTime();
        }
        int averageWaitTime = totalWaitTime / getProcesses().size();
        System.out.println("Average wait time: " + averageWaitTime);

    }

    public Computer getComputer() {
        return this.computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public ArrayList<Processor> getProcessors() {
        return this.processors;
    }

    public void setProcessors(ArrayList<Processor> processors) {
        this.processors = processors;
    }

    public ArrayList<Process> getProcesses() {
        return this.processes;
    }

    public void setProcesses(ArrayList<Process> processes) {
        this.processes = processes;
    }

    public LinkedList<Process> getReadyQueue() {
        return this.readyQueue;
    }

    public void setReadyQueue(LinkedList<Process> readyQueue) {
        this.readyQueue = readyQueue;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getClock() {
        return this.clock;
    }

    public void setClock(int clock) {
        this.clock = clock;
    }

}
