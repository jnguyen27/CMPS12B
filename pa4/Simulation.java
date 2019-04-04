// -----------------------------------
// Duy Nguyen
// duminguy (W1475318)
// CS 12B pa4
// 11/14/16
// Simulation.java
// description...
// -----------------------------------
import java.io.*;
import java.util.Scanner;

public class Simulation{

//-----------------------------------------------------------------------------
//
// The following function may be of use in assembling the initial backup and/or
// storage queues.  You may use it as is, alter it as you see fit, or delete it
// altogether.
//
//-----------------------------------------------------------------------------

   public static Job getJob(Scanner in) {
    String[] s = in.nextLine().split(" ");
    int a = Integer.parseInt(s[0]);
    int d = Integer.parseInt(s[1]);
    return new Job(a, d);
   }
   
   //to check if all the processes are done and queues are empty
   public static boolean allJobsDone ( Queue queueArr[]){
       int length = queueArr.length;
       //checks all the queues in the queue array to see if they are empty

       for(int i=0; i<length; i++){
           if(!queueArr[i].isEmpty()) return false;
        }
       return true;//returns true if all the queues are empty
   }
   
   //method to point to the queue with the smallest finish time
   public static Queue minFinishTime(Queue qArr[]){
       Queue fastestQ;
       int length = qArr.length;
       fastestQ = setFastestQ(qArr);
       for(int i=0; i<length; i++){
           //System.out.println("fastestQ finish : " + ((Job) (fastestQ.peek())).getFinish());
           //System.out.println("qArr finish: " + ((Job)(qArr[i].peek())).getFinish());
           if(!qArr[i].isEmpty()){
               
                if(((Job)(qArr[i].peek())).getFinish() <((Job) (fastestQ.peek())).getFinish()){
                    //System.out.println("FinishTime : " + ((Job) qArr[i].peek()).getFinish());
                    fastestQ = qArr[i];
                    //System.out.println("index : " + i);
                 }
           }
       }
       return fastestQ;
   }
   public static Queue setFastestQ(Queue queueArr[]){
       
       for(int i=0; i<queueArr.length; i++){
           if(!queueArr[i].isEmpty()){
               return queueArr[i];
           }
       }
       return queueArr[0];
   }
   //used to determine time 
   public static int determineTime(int time, Queue Q, Queue[] processQ){
       int arrivalTime=100000;//huge so if Q is empty the finish time is the default
       int finishTime=100000;
       Queue temp;
       if(!Q.isEmpty()){
            arrivalTime = ((Job)Q.peek()).getArrival();
            //System.out.println("Arrival time: " + arrivalTime);
       }
       
       for(int i=0; i<processQ.length; i++){
        if(!processQ[i].isEmpty()){
            //((Job) temp.peek()).computeFinishTime(time);
            temp =minFinishTime(processQ);
            
             //finishTime = ((Job) processQ[i].peek()).getFinish();
            finishTime = ((Job) temp.peek()).getFinish();
        }
        
       }
       if (arrivalTime < finishTime)return arrivalTime;
       else return finishTime;
   }

   //finish jobs that are finishing up
   public static void finishJob(int time, Queue q1, Queue storage ){
       if (((Job) q1.peek()).getFinish() <= time ){
           storage.enqueue(q1.dequeue());
       }
   }
   
   //assigns jobs to the shortest processor line using numItems
   public static void assignProcessor(int time, Queue Q1, Queue[] queueArr){
       int smallestQueueSize = 100000000;//makes the length of first queue in the Array be the smallestQueueSize
       int index = 0;
       Job tempJob;
       //sets the index to the queue with the lowest numbers of jobs
       for(int i=0; i<queueArr.length; i++){
           if(queueArr[i].length() < smallestQueueSize){
               index =i;
               smallestQueueSize = queueArr[i].length();
           }
       }
       tempJob = (Job) Q1.peek();
       if(queueArr[index].isEmpty()){
           calcFinishTime((Job) Q1.dequeue(), time);
       }
       else{
           Q1.dequeue();
           calcFinishTime(tempJob, tailPeek(queueArr[index], queueArr[index].length()).getFinish() );
       }
       queueArr[index].enqueue(tempJob);//add onto the processor the queue you just took off
       
       //if the next job has the same arrival time
       if(!Q1.isEmpty()){
            tempJob = (Job) Q1.peek();
            if(tempJob.getArrival() == time)
                assignProcessor(time, Q1, queueArr);
       }
   }
   
   //return the bottom job in an array
   public static Job tailPeek(Queue indexQueue , int length){
       Job tempJob = null;
       for(int i=0; i<length; i++){
           tempJob =(Job) indexQueue.dequeue();
           indexQueue.enqueue(tempJob);
       }
       return tempJob;
   }
   
   //calculate finish time of a job
   public static void calcFinishTime(Job job1, int time){
       job1.computeFinishTime(time);
       
   }
   
   //create a copy queue 
   public static void copyQueue(Queue q1, Queue copy){
       //used to place the items back into q1
       Queue temp = new Queue();
       while(!q1.isEmpty()){
           Job item =(Job) q1.dequeue();//used to save the item you pop off
           item.resetFinishTime();//resets the finish time when you create a copy
           copy.enqueue(item);
           temp.enqueue(item);
       }
       while(!temp.isEmpty()){
           Object item2 = temp.dequeue();
           q1.enqueue(item2);
       }
   }
   //print the process number 
   public static void tracePrintProcessNum(PrintWriter trace, int processors){
       trace.print("\n*************************************\n");
       if(processors ==1 )
           trace.print(processors + " processor:\n");
       else trace.print(processors + " processors:\n");
       trace.print("*************************************\n");
   }
   //print the queue from time =0
   public static void tracePrintFirst (PrintWriter trace, Queue Q1, int time, int processors) {
       trace.println("time = " + time);
       trace.println("0: " + Q1);
       for(int i=0; i<processors; i++){
           trace.println(i+1 + ":");
       }
   }
   //prints the waitTime information into the report file
   public static void rptPrintWaitTime(PrintWriter report,int numProcessors, int totalWait, int maxWait, double averageWait){
       if(numProcessors >1){
           report.print(numProcessors + " processors: totalWait= " + totalWait);
           report.println(", maxWait=" + maxWait + ", averageWait=" + averageWait);
       }
       else{
           report.print(numProcessors + " processor: totalWait= " + totalWait);
           report.println(", maxWait=" + maxWait + ", averageWait=" + averageWait);
       }
   }
   
//-----------------------------------------------------------------------------
//
// The following stub for function main contains a possible algorithm for this
// project.  Follow it if you like.  Note that there are no instructions below
// which mention writing to either of the output files.  You must intersperse
// those commands as necessary.
//
//-----------------------------------------------------------------------------

   public static void main(String[] args) throws IOException{
        if(args.length!=1){
            throw new IOException("Use: Simulation input_file ");
        }
		
		
        int time=0;
        Queue Q1 = new Queue();
        Queue copy = new Queue();
        Queue storageQueue = new Queue();
        int numProcessors; //number of processors
        
		
        Scanner input = new Scanner(new File(args[0]));
        int numJobs;//variable that is the first number in the input file
        numJobs= input.nextInt();
        input.nextLine();//skips onto the next line

        PrintWriter report = new PrintWriter(new FileWriter( args[0]+".rpt"));
        PrintWriter trace = new PrintWriter(new FileWriter( args[0] + ".trc"));
        
        //print onto the trace and report files
        trace.println("Trace file: "+ args[0] + ".trc");
        report.println("Report file: "+ args[0] + ".rpt");
        numProcessors = numJobs;
        trace.print(numJobs + " Jobs: \n");
        report.print(numJobs + " Jobs: \n");

		//fills the arrays and queues with jobs as well as printing on the report and trace files
        for(int i =0; i<numJobs;i++){
            Job inputJob = getJob(input); 
            Q1.enqueue(inputJob);
            copy.enqueue(inputJob);
            trace.print(inputJob);
            report.print(inputJob);
        }
        
        report.println("\n\n****************************************");
                
        
        
        //    4.  run simulation with n processors for n=1 to n=m-1  {
        //
		
        for(int n=1; n<numProcessors; n++){
            //if the Q1 is empty copy the original queue back into it
			if(Q1.isEmpty()) 
                copyQueue(copy, Q1);
            //print the number of the processor being used 
            tracePrintProcessNum(trace, n);
            
            //if the storageQueue isn't empty make it empty
            if(!storageQueue.isEmpty())
                storageQueue.dequeueAll();
            
            //    5.      declare and initialize an array of n processor Queues and any 
            //            necessary storage Queues
            //
            
            Queue processorQueue[] = new Queue[n];
            for(int i=0; i<n; i++){
                processorQueue[i]= new Queue();
            }
            time=0;//set the time to 0 before restarting the jobs
			
			//print the trace file for time =0
            tracePrintFirst(trace, Q1, time, n);
            //    6.      while unprocessed jobs remain  {
            //
            //
            while( !allJobsDone(processorQueue) || !Q1.isEmpty()){
                
            
            //    7.          determine the time of the next arrival or finish event and 
            //                update time
                time = determineTime(time, Q1, processorQueue);
                
                trace.println("\ntime= "+ time);
                
            //    8.          complete all processes finishing now
            //
                //completes all the processes that are finishing at the time of arrival of next job
                for(int job=0; job<processorQueue.length; job++){
                    if(!processorQueue[job].isEmpty()){
                        if(((Job) processorQueue[job].peek()).getFinish() <= time){
                            finishJob(time, processorQueue[job], storageQueue);
                    }
                }
                
            //    9.          if there are any jobs arriving now, assign them to a processor 
            //                Queue of minimum length and with lowest index in the queue array.
            //
                //if Q1 isnt empty and the arrivaltime is equal to the time then assign a process to the processorQueues
                if(!Q1.isEmpty() && ((Job) Q1.peek()).getArrival() == time)
                    assignProcessor( time ,Q1, processorQueue);


		/*if(!Q1.isEmpty()){
		    if(((Job) Q1.peek()).getArrival() == time){
			assignProcessor(time, Q1, processorQueue);
		    }else{ 
		        assignProcessor(((Job) Q1.peek()).getArrival(), Q1, processorQueue);
		    }
		}*/   

                trace.print("0: " + Q1);
                trace.println(storageQueue);
                 
                //print what's in the processorQueues
                for(int job=0; job<processorQueue.length; job++){
                    trace.println(job +1  + ": " + processorQueue[job]);
                }
                
                //    10.     } end loop
                //
            }
            
            //    11.     compute the total wait, maximum wait, and average wait for 
            //            all Jobs, then reset finish times
            //
            
            int storageLength = storageQueue.length();
            int totalWait=0;
            int maxWait=0;
            double averageWait;
			
			//calculates the waitTime information 
            for(int i=0; i<storageLength; i++){
                int waittime = ((Job) storageQueue.peek()).getWaitTime();
                totalWait = totalWait + waittime;
                if(waittime > maxWait){
                    maxWait = waittime; 
                }
                storageQueue.dequeue();
            }
			
            averageWait =(double) totalWait/ storageLength;
            
            rptPrintWaitTime(report , n, totalWait, maxWait, averageWait);
            //    12. } end loop
            
            
        }
            //
            //    13. close input and output files
        trace.close();
        report.close();
        
        
   }
}

