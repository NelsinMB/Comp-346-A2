package main;

public class PCB {
    private Process process;
    private State processState;
    private int programCounter = 0;
    private int timeAtIO;
    private int IOInstructionCount;
    private int timeOnCPU;

    public PCB(Process process) {
        this.process = process;
        timeAtIO = 0;
        IOInstructionCount = 0;
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
    
    public int getIOInstructionCount() {
        return this.IOInstructionCount;
    } 
    
    public void setIOInstructionCount(int IOInstructionCount) {
        this.IOInstructionCount = IOInstructionCount;
    }

    public int getTimeOnCPU(){
        return this.timeOnCPU;
    }

    public void setTimeOnCPU(int timeOnCPU) {
        this.timeOnCPU = timeOnCPU;
    }

}
