import java.util.*;

public class StackPuzzle {

    public static void main(String args[]){

        Scanner in = new Scanner(System.in);

        //Read in the original word and the goal word.
        String original = in.nextLine();
        String goal = in.nextLine();

        //Create a stack of the original word.
        Stack<Character> originalChars = new Stack<Character>();
        for (int i = (original.length() - 1); i >= 0; i--){
            originalChars.push(original.charAt(i));
        }

        //If the stack is impossible because the original word and the goal word are different lengths, 
        // output empty brackets and end the program.
        if (original.length() != goal.length()){
            System.out.println("[");
            System.out.println("]");
            in.close();
            return;
        }
        //If the original and goal words are equal in length, then call the stackPuzzle function.
        else{
            List<String> output = new ArrayList<String>();  //Empty list of outputs.
            String ofMoves = "";    //Empty string that will hold the moves used.
            Stack<Character> centerStack = new Stack<Character>();  //Empty center stack.
            String resultWord = ""; //Empty string that will hold the result word for each combo of moves.

            //Call puzzleCombinations to determine which combinations of push/pop are successful.
            puzzleCombinations(originalChars, goal, resultWord, ofMoves, output, centerStack);

            //Sort and print the results.
            sortResults(output);
        }

        in.close();

    }

    //Recursive function to try all puzzle combinations.
    //The combinations that result in the goal word are added to the list of outputs.
    public static void puzzleCombinations(Stack<Character> wordStack, String goal, String result, String ofMoves, List<String> output, Stack<Character> centerStack){

        String resultCopy = result;
        String ofMovesCopy = ofMoves;

        //If the centerStack is empty...
        if (centerStack.empty()){

            //AND the word stack is empty, check if the result word is equal to goal word.
            //If so, this specific combination of moves is a solution to the puzzle and is added to the output list.
            if (wordStack.empty()){
                if (goal.equals(result)){
                    output.add(ofMoves);
                    return;
                }
            }

            //If the wordStack is NOT empty, then pop off a character from the wordStack onto the centerStack.
            else{
                //Create copies of the stacks.
                Stack<Character> wordStackCopy = (Stack<Character>) wordStack.clone();
                Stack<Character> centerStackCopy = (Stack<Character>) centerStack.clone();

                //Get the next character off the of wordStack and push it onto the center stack.
                Character nextChar = wordStackCopy.pop();
                centerStackCopy.push(nextChar);
                ofMovesCopy += "i"; //Adjust the list of moves accordingly.

                //Make recursive call to continue to next possible moves.
                puzzleCombinations(wordStackCopy, goal, resultCopy, ofMovesCopy, output, centerStackCopy);
            }

        }

        //If the center stack is NOT empty, we can either push onto it or pop off of it.
        else if (!centerStack.empty()){

            //If the wordStack is empty, the only move we can make is to pop off the centerStack.
            if (wordStack.empty()){

                //Create copies of the stack.s
                Stack<Character> wordStackCopy = (Stack<Character>) wordStack.clone();
                Stack<Character> centerStackCopy = (Stack<Character>) centerStack.clone();
                
                //Get the next character off of the centerStack and add it to the result string.
                resultCopy += centerStackCopy.pop();
                ofMovesCopy += "o"; //Adjust the list of moves accordingly.

                //Make recursive call to continue to next possible moves.                
                puzzleCombinations(wordStackCopy, goal, resultCopy, ofMovesCopy, output, centerStackCopy);
            }

            /*If the wordStack is NOT empty, we have two options:
            1. Pop off of the center stack and continue to next moves via recursive call.
            OR
            2. Pop off the wordStack (push onto centerStack) and continue to next moves via recursive call.
            */
            else if (!wordStack.empty()){

                //BEGIN: Option 1
                //Pop off the centerStack and onto the result word.

                //Make copies of the stacks.
                Stack<Character> wordStackCopy = (Stack<Character>) wordStack.clone();
                Stack<Character> centerStackCopy = (Stack<Character>) centerStack.clone();

                //Pop off of the centerStack and add to the result word.
                resultCopy += centerStackCopy.pop();    
                ofMovesCopy += "o"; //Adjust the list of moves accordingly.

                //Make recursive call to continue to next possible moves.
                puzzleCombinations(wordStackCopy, goal, resultCopy, ofMovesCopy, output, centerStackCopy);

                //END: Option 1.

                //BEGIN: Option 2
                //Pop off of the wordStack and onto the centerStack
                
                //Make extra copies of the strings and stacks.
                String resultCopy2 = result;
                String ofMovesCopy2 = ofMoves;
                Stack<Character> wordStackCopy2 = (Stack<Character>) wordStack.clone();
                Stack<Character> centerStackCopy2 = (Stack<Character>) centerStack.clone();

                //Pop character off of the wordStack and push it onto the centerStack.
                Character nextChar = wordStackCopy2.pop();
                centerStackCopy2.push(nextChar);
                ofMovesCopy2 += "i";    //Adjust the list of moves accordingly.

                //Make recursive call to continue to next possible moves.
                puzzleCombinations(wordStackCopy2, goal, resultCopy2, ofMovesCopy2, output, centerStackCopy2);
            }

        }

    }
    
    //Function to sort the results lexicographically and print them out with correct format.
    /* E.g.
    [
    i i i i o o o i o o
    i i i i o o o o i o
    ]
    */
    public static void sortResults(List<String> output){

        //Sort the results lexicographically.
        Collections.sort(output);
  
        // Print the sorted list of results.
        System.out.println("[");
        for (int i = 0; i < output.size(); i++){
            String line = output.get(i);
            for (int j = 0; j < line.length(); j++){
                if (j != (line.length() - 1)){
                    System.out.print(line.charAt(j) + " ");
                }
                else{
                    System.out.print(line.charAt(j) + "\n");
                }
            }
        }
        System.out.println("]");

        return;
    }
}
