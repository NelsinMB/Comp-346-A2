package main;

import java.util.ArrayList;

public class Process {
    private String processID;
    private int arrivalTime;
    private int totalExecTime;
    private ArrayList<Integer> IORequestAtTimes;
    PCB pcb;
    private Processor processor; // The processor the process is running on

    public Process(String processID, int arrivalTime, int totalExecTime, ArrayList<Integer> IORequestAtTimes) {
        this.processID = processID;
        this.arrivalTime = arrivalTime;
        this.totalExecTime = totalExecTime;
        this.IORequestAtTimes = IORequestAtTimes;
        this.pcb = new PCB();
    }

    public void executeInstruction() {
        if (IOInstruction()) {
            readyToWaiting();
            this.processor.getComputer().getIO().getWaitQueue().add(this); // Add this process to waitQueue of IO
            

        } else if (lastInstruction()) {
            readyToTerminate();
           
        }

        // *Make sure this should be after terminate
            getPCB().setProgramCounter(getPCB().getProgramCounter() + 1);
        

    }

    public Boolean lastInstruction() {
        if (getPCB().getProgramCounter() == totalExecTime) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean IOInstruction() {
        if (IORequestAtTimes.contains(getPCB().getProgramCounter() - 1)) { // If currentInstruction is an IO request
            return true;
        } else {
            return false;
        }
    }

    public void readyToTerminate() {
        this.getPCB().setProcessState(State.TERMINATED);
        this.processor = null;
    }

    public void newToReady() {
        this.getPCB().setProcessState(State.READY);
    }

    public void readyToRunning(Processor processor) {
        this.getPCB().setProcessState(State.RUNNING);
        this.processor = processor;
    }

    public void readyToWaiting() {
        getPCB().setProcessState(State.WAITING);
        this.getPCB().setTimeAtIO(1); // 2 ticks for I/O
    }

    public void waitingToReady() {
        this.getPCB().setProcessState(State.READY);
    }

    public String getProcessID() {
        return this.processID;
    }

    public void setProcessID(String processID) {
        this.processID = processID;
    }

    public int getArrivalTime() {
        return this.arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getTotalExecTime() {
        return this.totalExecTime;
    }

    public void setTotalExecTime(int totalExecTime) {
        this.totalExecTime = totalExecTime;
    }

    public ArrayList<Integer> getIORequestAtTimes() {
        return this.IORequestAtTimes;
    }

    public PCB getPCB() {
        return this.pcb;
    }

    public void setPCB(PCB pcb) {
        this.pcb = pcb;
    }

    public Processor getProcessor() {
        return this.processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

}
