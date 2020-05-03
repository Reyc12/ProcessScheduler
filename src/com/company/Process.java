package com.company;

import java.util.Comparator;

public class Process implements Comparable<Process>, Comparator<Process> {
    private String processID; // the unique process ID
    private int arrivalTime; // arrival time of the process
    private int burstTime; // burst time of the process
    private int priority; // priority of the process where ( 1 = HIGH PRIORITY , 2 = MILD PRIORITY , 3 = LOW PRIORITY )
    private int waitingTime; //waiting time of the  process
    private int turnaroundTime; //turnaround time of the process
    private boolean processFinished = false; //is the process finished ?


    //default constructor
    public  Process(){};

    //constructor
    public Process (String pID, int arrTime, int bTime , int priority ){

        this.processID = pID;
        this.arrivalTime = arrTime;
        this.burstTime = bTime;
        this.priority = priority;
    }

    //getters
    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getPriority() {
        return priority;
    }

    public int getWaitingTime() { return waitingTime;}

    public int getTurnaroundTime() { return turnaroundTime;}

    public String getProcessID() {
        return processID;
    }

    public boolean isProcessFinished() { return processFinished;}

    // setters
    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setWaitingTime(int wT) {waitingTime = wT;}

    public void setTurnaroundTime(int tT) {turnaroundTime = tT;}

    public void setProcessFinished(boolean finish) {this.processFinished = finish;}

    //compare processes by arrival time
    public int compareTo(Process p) {
        return(this.arrivalTime - p.arrivalTime);
    }

    //compare processes by burst time
    public int compare(Process p1, Process p2) {
        return Integer.compare(p1.burstTime, p2.burstTime);

    }
}
