package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class IO {

    private Computer computer;
    private Queue<Process> waitQueue;

    public IO(Computer computer) {
        setComputer(computer);
        this.waitQueue = new LinkedList<Process>();
    }

    // Only one process makes progress on IO at any given time, right?
    public void tick() {
        if (!waitQueue.isEmpty()) {
            Process process = waitQueue.element();
            getComputer().getCPUScheduler().setActive(true);

            process.getPCB().setIOInstructionCount(process.getPCB().getIOInstructionCount() + 1); 
            if (process.getPCB().getIOInstructionCount() == 3) { //Allow to start at 1 instead of 2, thus use 3
                removeFromQueue(process);
            }
        } else {

        }

        for (Process process : waitQueue) {
            process.getPCB().setTimeAtIO(process.getPCB().getTimeAtIO() + 1);
        }

    }

    public Queue<Process> getWaitQueue() {
        return this.waitQueue;
    }

    public void setWaitQueue(Queue<Process> waitQueue) {
        this.waitQueue = waitQueue;
    }

    public void removeFromQueue(Process process) {
        waitQueue.remove(process);
        getComputer().getCPUScheduler().getReadyQueue().add(process);
        process.waitingToReady();
    }

    public Computer getComputer() {
        return this.computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

}
