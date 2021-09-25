import java.util.*;

public class TrainArrangements {

    public static void main(String args[]){

        Scanner in = new Scanner(System.in);

        int n = in.nextInt();   //number of coaches in each train

        //Array that represents the order of coaches on track A.
        int[] trackA = new int[n];
        for (int i = 0; i < n; i++){
            trackA[i] = i+1;
        }

        in.nextLine();  //Read away the line break

        List<int[]> list = new ArrayList<int[]>();

        while(true){

            //Read the line of input.
            String inputString = in.nextLine();
            
            //If the line of input is not a permutation, end the program.
            if (inputString.equals("0")){
                break;
            }

            //Store the permutation as an array of integers.
            String[] inputStringSplit = inputString.split(" ");
            int[] permutation = new int[n];

            for (int i = 0; i < n; i++){
                permutation[i] = Integer.parseInt(inputStringSplit[i]);
            }

            //Add the permutation to the list of premutations we need to evaluate.
            list.add(permutation);

        }

        // Iterating using for loop
        for (int i = 0; i < list.size(); i++){
           
            //If the permutation is possible, print yes.
            if (trainStation(trackA, list.get(i), n)){
                System.out.println("Yes");
            }
            //If the permutation is not possible, print no.
            else{
                System.out.println("No");
            }

        }   

        in.close();

    }

    public static boolean trainStation(int[] originalTrackA, int[] goal, int n){

        //Create a copy of track A.
        int[] trackA = new int[n];
        for (int i = 0; i < trackA.length; i++){
            trackA[i] = originalTrackA[i];
        }

        //Create output array for the train (trackB) of size n
        int[] trackB = new int[trackA.length];

        //Initialize the empty stack that will be the switching track (switchingTrack)
        Stack<Integer> switchingTrack = new Stack<Integer>();

        int counter = 0; //Keeps track of which element is the next one to go into switchingTrack.
        int nextSpot = 0;   //Keeps track of which element in the goal array is next to be added.

        while(nextSpot < n){

            //If the switchingTrack is empty...
            if (switchingTrack.isEmpty()){

                //AND the counter is not equal to n, then push onto the switching track from trackA.
                if (counter != n){
                    switchingTrack.push(trackA[counter]);
                    trackA[counter] = -1;
                    counter++;
                }

                //Else, break the function.
                else{
                    break;
                }

            }

            //If the switchingTrack is NOT empty...
            else if (!switchingTrack.isEmpty()){

                //If the top of the stack and the next Spot in the goal are equal, add to the goal.
                if (switchingTrack.peek() == goal[nextSpot]){
                    trackB[nextSpot] = switchingTrack.pop();
                    nextSpot++;
                    continue;
                }

                //If the top of the stack and the next spot are NOT equal...
                else if(switchingTrack.peek() != goal[nextSpot]){

                    //AND the top of the switchingTrack is the last possible number
                    //OR the counter is already greater than or equal to n (meaning we have reached the
                    //end of the coaches on trackA)
                    if (switchingTrack.peek() == n || counter >= n){
                        //Then the permutation is not possible and we return false.
                        return false;
                    }

                    //Else, push a new element from trackA onto the switchingTrack.
                    switchingTrack.push(trackA[counter]);
                    trackA[counter] = -1;
                    counter++;
                }

            }

        }

        //If the function has reached this point, then the permutation must be possible and we return true.
        return true;

    }

}
