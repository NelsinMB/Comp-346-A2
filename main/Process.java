package main;

import java.util.ArrayList;

public class Process implements Comparable<Process> {
    //Some questions
    private String processID;
    private int arrivalTime;
    private int totalExecTime;
    private ArrayList<Integer> IORequestAtTimes;
    private PCB pcb;
    private Processor processor;

    private int waitTime;
    private int completionTime;
    private int turnAroundTime; 
    private int responseTime; 
    private boolean ranBefore; //Will use for responseTime

    public Process(String processID, int arrivalTime, int totalExecTime, ArrayList<Integer> IORequestAtTimes) {
        this.processID = processID;
        this.arrivalTime = arrivalTime;
        this.totalExecTime = totalExecTime;
        this.IORequestAtTimes = IORequestAtTimes;
        this.pcb = new PCB(this);
        this.ranBefore = false;
    }

    public void executeInstruction(int clock) {
        if (IOInstruction()) {
            readyToWaiting();
            this.processor.getComputer().getIO().getWaitQueue().add(this); // Do we want this done here?
        } else if (lastInstruction()) {
            readyToTerminate(clock);
            return;
        }
        getPCB().setProgramCounter(getPCB().getProgramCounter() + 1);
        this.getPCB().setTimeOnCPU(this.getPCB().getTimeOnCPU() + 1);
    }

    public Boolean lastInstruction() {
        if (getPCB().getProgramCounter() == (totalExecTime)) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean IOInstruction() {
        if (IORequestAtTimes.contains(getPCB().getProgramCounter())) { // If currentInstruction is an IO request
            return true;
        } else {
            return false;
        }
    }

    public void readyToTerminate(int clock) {
        this.getPCB().setProcessState(State.TERMINATED);
        this.getPCB().setTimeOnCPU(0);
        setProcessor(null);
        setTurnAroundTime(clock - getArrivalTime());
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
        this.getPCB().setIOInstructionCount(0); // 2 ticks for I/O
        this.getPCB().setTimeAtIO(0);
        this.getPCB().setTimeOnCPU(0);
    }

    public void waitingToReady() {
        this.getPCB().setProcessState(State.READY);
        this.getPCB().setTimeAtIO(0);
        this.getPCB().setIOInstructionCount(0);
    }

    public void runningToReady() {
        this.getPCB().setProcessState(State.READY);
        this.getPCB().setTimeOnCPU(0);
        setProcessor(null);
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

    @Override
    public int compareTo(Process otherProcess) {
        return Integer.compare(getTotalExecTime(), otherProcess.getTotalExecTime());
    }

    public int getWaitTime() {
        return this.waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public int getCompletionTime() {
        return this.completionTime;
    }
    
    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    public int getTurnAroundTime() {
        return this.turnAroundTime;
    }

    public void setTurnAroundTime(int turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    public int getResponseTime() {
        return this.responseTime;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    public Boolean getRanBefore() {
        return this.ranBefore;
    }
    
    public void setRanBefore(Boolean ranBefore) {
        this.ranBefore = ranBefore;
    }
}
