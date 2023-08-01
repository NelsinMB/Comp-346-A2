package main;

import java.util.LinkedList;
import java.util.Queue;

public class IO {

    private Queue<Process> waitQueue;

    public IO () {
        this.waitQueue = new LinkedList<Process>();
    }

    public void tick() {
        for (Process process : waitQueue) {
            process.getPCB().setWaitQueueTime(process.getPCB().getWaitQueueTime() - 1); //Decrement on waitQueue
            if (process.getPCB().getWaitQueueTime() == 0) { //If process has completed it's I/O
                removeFromQueue(process);
            }
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
        process.waitingToReady(); 
    }
    
    
}
