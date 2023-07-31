package main;

import java.util.ArrayList;

public class Process implements Comparable {
    private String processID;
    private int arrivalTime;
    private int totalExecTime;
    private ArrayList<Integer> IORequestAtTimes;
    PCB pcb;

    public Process(String processID, int arrivalTime, int totalExecTime, ArrayList<Integer> IORequestAtTimes) {
        this.processID = processID;
        this.arrivalTime = arrivalTime;
        this.totalExecTime = totalExecTime;
        this.IORequestAtTimes = IORequestAtTimes;
    }

   

    public String getProcessID() {
        return this.processID;
    }

    public void setProcessID(String processID){ 
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



    @Override
    public int compareTo(Process comparePro) {
        int compare = ((Process)comparePro).getArrivalTime();
        return this.arrivalTime-compare;


        
    }

    
    
}
