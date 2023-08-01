package main;

public class PCB {
    Process process;
    State processState;
    int programCounter;
    int waitQueueTime;

    public PCB() {

    }

    public Process getProcess() {
        return this.process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public State getProcessState() {
        return this.processState;
    }

    public void setProcessState(State processState) {
        this.processState = processState;
    }

    public int getProgramCounter() {
        return this.programCounter;
    }

    public void setProgramCounter(int programCounter) {
        this.programCounter = programCounter;
    }

    public int getWaitQueueTime() {
        return this.waitQueueTime;
    } 
    
    public void setWaitQueueTime(int waitQueueTime) {
        this.waitQueueTime = waitQueueTime;
    }
    
}
