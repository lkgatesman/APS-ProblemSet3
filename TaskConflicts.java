import java.util.*;

public class TaskConflicts{

    public static void main(String args[]){

        Scanner in = new Scanner(System.in);

        int n = in.nextInt();   //Number of one-time tasks
        int m = in.nextInt();   //Number of repeated tasks

        //Read out next line break so that we can start reading in the actual input.
        in.nextLine();

        //Array to hold the tasks.
        Interval[] tasks = new Interval[1000000];
        int numTasksOverall = 0;    //Number of tasks total (# one time tasks + (each repeating task * the number of times it repeats))

        int i = 0;  //counter for the for loops

        //Store the start and end times for one-time tasks.
        for (i = 0; i < n; i++){
            
            int start = in.nextInt();
            int end = in.nextInt();
            Interval task = new Interval(start, end);

            tasks[i] = task;
            numTasksOverall++;

        }

        //Store the repeating tasks for each time they repeat.
        for(int k = 0, j = i; k < m; k++){

            int start = in.nextInt();
            int end = in.nextInt();
            int repeats = in.nextInt();

            while(end<=1000000)
            {
                Interval task = new Interval(start,end);
                tasks[j++] = task;
                numTasksOverall++;

                //Increment the start and end times according to the repetition of the task.
                start = start + repeats;
                end = end + repeats;
            }
        }

        in.close();
        //Check if a conflict exists at all.
        conflictExists(tasks, numTasksOverall);

    }

    //Method to check if there is a conflict and print the result.
    public static void conflictExists(Interval tasks[], int size)  
    {  

        boolean isConflict = false;

        int maxValue = 0;  

        //Calculate the maxValue for end tasks.
        for (int i = 0; i < size; i++) 
        {  
            if (maxValue < tasks[i].end)  
                maxValue = tasks[i].end;  
        }  

        //Create a array that will represent our schedule.
        int[] schedule = new int[maxValue + 1]; 

        //Populate the schedule array for each task in the tasks[] array.
        for (int i = 0; i < size; i++)  
        {  
            int a = tasks[i].start;  
            int b = tasks[i].end;  
            schedule[a]++; 
            schedule[b]--;  
        }  

        //Iterate through to find a schedule conflict (if it exists).
        for (int i = 1; i <= maxValue; i++) 
        {  
            schedule[i] += schedule[i - 1];  
            if (schedule[i] > 1)  
                isConflict = true;  
        }  
        
        //Print out the answer.
        if (isConflict){
            System.out.println("CONFLICT");
        }
        else{
            System.out.println("NO CONFLICT");
        }

    }  

}

//Interval class for tracking the intervals of tasks.
class Interval{

    int start;
    int end;

    Interval(int start, int end)  
    { 
        super(); 
        this.start = start; 
        this.end = end; 
    } 
      
}