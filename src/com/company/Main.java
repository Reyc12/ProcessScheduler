package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


import java.util.Scanner;
/**
 * This program uses Dynamic Round Robin Algorithm to sort the processes ,
 * however I have implemented a priority feature in order to adapt it to the required solution.
 */
public class Main {
     static ArrayList <Process> priorityOne = new ArrayList<>();
     static ArrayList <Process> priorityTwo = new ArrayList<>();
     static ArrayList <Process> priorityThree = new ArrayList<>();
    static ArrayList <Process> priorityFour = new ArrayList<>();
    static ArrayList <Process> priorityFive = new ArrayList<>();
    static ArrayList <Process> allProcesses = new ArrayList<>();


    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        File file = new File("/Users/reicelo/Desktop/OS_Assignment/ProcessSchedulerProject/Book12.csv");
        try {
            Scanner inputStream = new Scanner (file);
            inputStream.next(); // ignore the first line

            while ( inputStream.hasNext()){
                String data = inputStream.next();
                String [] values = data.split(",");
                String processID = values[0];
                int arrivalTime = Integer.parseInt(values[1]);
                int burstTime = Integer.parseInt(values[2]);
                int priority = Integer.parseInt(values[3]);
                Process process = new Process (processID,arrivalTime,burstTime,priority);

                switch (process.getPriority()){
                    case 1:
                        priorityOne.add(process);
                        break;
                    case 2:
                        priorityTwo.add(process);
                        break;
                    case 3:
                        priorityThree.add(process);
                        break;
                    case 4:
                        priorityFour.add(process);
                        break;
                    case 5:
                        priorityFive.add(process);
                        break;

                }




            }
            inputStream.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        ;


        allProcesses.addAll(priorityOne);
        allProcesses.addAll(priorityTwo);
        allProcesses.addAll(priorityThree);
        allProcesses.addAll(priorityFour);
        allProcesses.addAll(priorityFive);


        RoundRobin test = new RoundRobin(allProcesses);
        test.schedule();
        test.printChart();

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time taken for execution of the code is "+totalTime+" millisec");






    }
}
