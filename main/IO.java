package main;

import java.util.LinkedList;
import java.util.Queue;

public class IO {
    //Cleanish

    private Computer computer;
    private Queue<Process> waitQueue;

    public IO(Computer computer) {
        setComputer(computer);
        setWaitQueue(new LinkedList<Process>());
    }

    public void tick() {
        if (!waitQueue.isEmpty()) {
            Process process = waitQueue.element();
            getComputer().getCPUScheduler().setActive(true);

            process.getPCB().setIOInstructionCount(process.getPCB().getIOInstructionCount() + 1);
            if (process.getPCB().getIOInstructionCount() == 3) { // Allow to start at 1 instead of 2, thus use 3
                removeFromQueue(process);
                if (!waitQueue.isEmpty()) { 
                    process = waitQueue.element();
                    process.getPCB().setIOInstructionCount(process.getPCB().getIOInstructionCount() + 1); //Increment the number of instructions (not time) at I/O for process at head of queue. 
                }
            }
        } 

        for (Process process : waitQueue) {
            process.getPCB().setTimeAtIO(process.getPCB().getTimeAtIO() + 1); //Increment time at I/O (not number of instructions) for all processes in queue.
            process.setWaitTime(process.getWaitTime() + 1); //Probably could merge timeAtIO with waitTime
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
