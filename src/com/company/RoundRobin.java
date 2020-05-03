package com.company;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;


public class RoundRobin {

    static int index = 0 , timeCount , mainCount;
    private int maxBurstTime;
    private static int priorityOneCount = 0, prioritytwoCount = 0, priorityThreeCount = 0,priorityiFourCount = 0;
    private int flagAlert;
    private int ganttCount = 0;
    private int ganttIdCount = 0;
    private int ganttTimeCount;
    private int ganttTime[];
    private String gantID[];
    private float timeQuantum;
    private float avgWaitingTime;
    private float avgTurnaroundTime;
    private static ArrayList<Process> currentQueue;// current list(queue) of processes that the Round Robin Algorithm is applied to
    private static ArrayList<Process> q1; // the ready queue, only the processes that are ready to be scheduled are stored here
    private ArrayList<Process> q2; // a clone af q1 which is used to sort the processes by their burst time
    private boolean flag = true;  // flag used to check the cases and change the timeQuantum accordingly




    public RoundRobin (ArrayList<Process> queue){
        currentQueue = queue;
        //currentQueue.sort(Process::compareTo);//sorts the list of processes by their arrival time


        flagAlert = currentQueue.size();
        mainCount = 0;  // setting mainCount to 0
        ganttTime = new int [currentQueue.size()];
        gantID = new String[currentQueue.size()];
        q1 = new ArrayList<>();
        //counts the number of process  that are in the queue for  priority 1-4 processes
        for(int i=0;i<currentQueue.size();i++) {
            switch (currentQueue.get(i).getPriority()) {
                case 1:
                    priorityOneCount++;
                    break;
                case 2:
                    prioritytwoCount++;
                    break;
                case 3:
                    priorityThreeCount++;
                    break;
                case 4:
                    priorityiFourCount++;
                    break;
            }
        }

    }

    /**
     * A simulation of the dynamic RR algorithm
     * IT is an endless loop that ends when all the processes have benn removed from the queue(all the processes have been scheduled)
     * */
    public void schedule(){

        for( timeCount=0 ; ; ){
            if(currentQueue.isEmpty()) {
                break;
            }
            checkForProcess(timeCount, index);
            if(q1.size() == flagAlert) {
                flag = false;
            }
            setTimeQuantum();

            /**
             * This is build based on the algorithm which is shown on the word document together with the setTimeQuantum function which sets the time quantum accordingly
             * q1 is the ready queue which is used to schedule the processes
             * when the processes have arrived to q1 it mean s that they are all of the highest priority therefor the priority does not matter,
             * the shorter processes are given a fairer chance since the time quantum is 80% of the maxburst of the largest process in the queue
             */
            for(int i=0;i<q1.size();i++) {

                if (q1.get(i).getBurstTime() <= timeQuantum && !q1.get(i).isProcessFinished()) {
                    q1.get(i).setWaitingTime(mainCount - q1.get(i).getArrivalTime());
                    timeCount += q1.get(i).getBurstTime();
                    mainCount += q1.get(i).getBurstTime();
                    q1.get(i).setProcessFinished(true);
                    q1.get(i).setTurnaroundTime(mainCount - q1.get(i).getArrivalTime());
                    ganttTimeCount += q1.get(i).getBurstTime();
                    ganttTime[ganttCount++] = ganttTimeCount;
                    gantID[ganttIdCount++] = q1.get(i).getProcessID();
                    //System.out.println(q1.get(i).getProcessID());
                }
            }






        }
    }

    /**
     *
     * @param time
     * @param ind
     * This function is also described in the word document.
     * It has two main purpouses
     * It checks for the highest priority processes at the current time and adds them to the ready queue(q1)
     * It checks for lower priority processes whose waiting time has aged long enough for them to join the ready queue(q1)
     */
    public static void checkForProcess(int time, int ind)
    {
        for(int i=0; i<currentQueue.size(); i++)
        {

            if(priorityOneCount==0){
                if(prioritytwoCount==0){
                    if(priorityThreeCount==0){
                        if(priorityiFourCount==0) {
                            if (currentQueue.get(i).getArrivalTime() <= time) {
                                q1.add(currentQueue.get(i));
                                currentQueue.remove(i);
                                index++;
                                break;
                            }
                        }
                        if (currentQueue.get(i).getPriority() == 4) {
                            if (currentQueue.get(i).getArrivalTime() <= time) {
                                q1.add(currentQueue.get(i));
                                currentQueue.remove(i);
                                index++;
                                prioritytwoCount--;
                                break;
                            }
                        }
                        if (currentQueue.get(i).getPriority() == 5) {
                            if (currentQueue.get(i).getArrivalTime() <= time - 15) {
                                q1.add(currentQueue.get(i));
                                currentQueue.remove(i);
                                index++;
                                prioritytwoCount--;
                                break;
                            }
                        }

                    }
                    if (currentQueue.get(i).getPriority() == 3) {
                        if (currentQueue.get(i).getArrivalTime() <= time) {
                            q1.add(currentQueue.get(i));
                            currentQueue.remove(i);
                            index++;
                            prioritytwoCount--;
                            break;
                        }
                    }
                    if (currentQueue.get(i).getPriority() == 4) {
                        if (currentQueue.get(i).getArrivalTime() <= time - 15) {
                            q1.add(currentQueue.get(i));
                            currentQueue.remove(i);
                            index++;
                            prioritytwoCount--;
                            break;
                        }
                    }
                    if (currentQueue.get(i).getPriority() == 5) {
                        if (currentQueue.get(i).getArrivalTime() <= time-30) {
                            q1.add(currentQueue.get(i));
                            currentQueue.remove(i);
                            index++;
                            prioritytwoCount--;
                            break;
                        }
                    }

                }
                if (currentQueue.get(i).getPriority() == 2) {
                    if (currentQueue.get(i).getArrivalTime() <= time) {
                        q1.add(currentQueue.get(i));
                        currentQueue.remove(i);
                        index++;
                        prioritytwoCount--;
                        break;
                    }
                }
                if (currentQueue.get(i).getPriority() == 3) {
                    if (currentQueue.get(i).getArrivalTime() <= time - 15) {
                        q1.add(currentQueue.get(i));
                        currentQueue.remove(i);
                        index++;
                        prioritytwoCount--;
                        break;
                    }
                }
                if (currentQueue.get(i).getPriority() == 4) {
                    if (currentQueue.get(i).getArrivalTime() <= time - 30) {
                        q1.add(currentQueue.get(i));
                        currentQueue.remove(i);
                        index++;
                        prioritytwoCount--;
                        break;
                    }
                }
                if (currentQueue.get(i).getPriority() == 5) {
                    if (currentQueue.get(i).getArrivalTime() <= time - 45) {
                        q1.add(currentQueue.get(i));
                        currentQueue.remove(i);
                        index++;
                        prioritytwoCount--;
                        break;
                    }
                }


            }
            if(currentQueue.get(i).getPriority()==1) {
                if (currentQueue.get(i).getArrivalTime() <= time) {
                    q1.add(currentQueue.get(i));
                    currentQueue.remove(i);
                    index++;
                    priorityOneCount--;
                    break;
                }
            }


                if (currentQueue.get(i).getPriority() == 2) {
                    if (currentQueue.get(i).getArrivalTime() <= time - 15) {
                        q1.add(currentQueue.get(i));
                        currentQueue.remove(i);
                        index++;
                        prioritytwoCount--;
                        break;
                    }
                }

                if (currentQueue.get(i).getPriority() == 3) {
                    if (currentQueue.get(i).getArrivalTime() <= time - 30) {
                        q1.add(currentQueue.get(i));
                        currentQueue.remove(i);
                        index++;
                        priorityThreeCount--;
                        break;
                    }

                }
            if (currentQueue.get(i).getPriority() == 4) {
                if (currentQueue.get(i).getArrivalTime() <= time - 45) {
                    q1.add(currentQueue.get(i));
                    currentQueue.remove(i);
                    index++;
                    priorityiFourCount--;
                    break;
                }

            }
            if (currentQueue.get(i).getPriority() == 5) {
                if (currentQueue.get(i).getArrivalTime() <= time - 60) {
                    q1.add(currentQueue.get(i));
                    currentQueue.remove(i);
                    index++;
                    break;
                }

            }

        }
        if(index > ind)
            return;
        timeCount++;
        return;
    }

    /*
     * If size of q2 == 1 , than there is only one process in the queue, timequantum = its burstTime so the process gets executed
     * If flag is true than timeQuantum = 80% of the greatest burst time from the current list of processes
     * Else timeQuantum = the greatest burst time from the current queue of processes
     * */
    private void setTimeQuantum (){
        /*q2 = new ArrayList<Process>();
        for(int j = 0;j < q1.size();j++){
            if(!q1.get(j).isProcessFinished()){
                q2.add(q1.get(j));
            }
        }*/

        q2 = (ArrayList<Process>)q1.clone();
        q2.sort(new Process()); // sorting processes by their burst time

        if(q2.size()==1){
          timeQuantum = q2.get(0).getBurstTime();
        }else if(flag) {
            maxBurstTime = q2.get(q2.size()-1).getBurstTime();
            timeQuantum = 0.8f * maxBurstTime;
        }else {
            maxBurstTime = q2.get(q2.size()-1).getBurstTime();
            timeQuantum = maxBurstTime;
        }


    }


    //print the gant chart and all the processes
    public void printChart(){
        System.out.println("\nGantt Chart: \n");

        //prints the processes in the order they were scheduled
        for(int i = 0; i < gantID.length; i++ ){
            System.out.print("|\t"+ gantID[i]+"\t|");
        }
        System.out.print("\n" + q1.get(0).getArrivalTime() + "\t");
        for(int i=0; i<q1.size(); i++) {
            System.out.print("\t"+ganttTime[i]+"\t");
            avgWaitingTime += q1.get(i).getWaitingTime();
            avgTurnaroundTime += q1.get(i).getTurnaroundTime();
        }
        //prints the processes in the order they were added to the ready queue q1
        System.out.println("\n\nProcess\t\tPriority\t\tArrival Time\tBurst Time\tWaiting Time\tTurnaround Time");
        for(int i=0; i<q1.size(); i++) {
            System.out.println("   "+q1.get(i).getProcessID()+"\t\t   "+q1.get(i).getPriority()+"\t\t   "+q1.get(i).getArrivalTime()+"\t\t\t   "+q1.get(i).getBurstTime()+"\t\t\t   "+q1.get(i).getWaitingTime()+"\t\t\t  "+q1.get(i).getTurnaroundTime());

        }
        avgWaitingTime /= q1.size();
        avgTurnaroundTime /= q1.size();
        System.out.println("\nAverage Waiting Time: "+avgWaitingTime);
        System.out.println("Average Turnaround Time: "+avgTurnaroundTime);



    }


}
