package main;

public class PCB {
    Process process;
    State processState;
    int programCounter = 0;
    int timeAtIO = 0;

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

    public int getTimeAtIO() {
        return this.timeAtIO;
    } 
    
    public void setTimeAtIO(int timeAtIO) {
        this.timeAtIO = timeAtIO;
    }
    
}
