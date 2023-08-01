package main;

import java.util.LinkedList;
import java.util.Queue;

public class IO {

    private Computer computer;
    private Queue<Process> waitQueue;

    public IO(Computer computer) {
        setComputer(computer);
        this.waitQueue = new LinkedList<Process>();
    }

    public void tick() {
        for (Process process : waitQueue) {
            process.getPCB().setWaitQueueTime(process.getPCB().getWaitQueueTime() - 1); // Decrement on waitQueue
        }
        while (waitQueue.element() != null) {
            
        }

        if (!waitQueue.isEmpty()) {
            getComputer().getCPUScheduler().setActive(true);
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
