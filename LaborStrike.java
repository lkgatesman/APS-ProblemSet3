import java.util.*;

public class LaborStrike{

    public static void main(String args[]){

        Scanner in = new Scanner(System.in);

        int m = in.nextInt();   //the number of days in the calendar
        int n = in.nextInt();   //the number of unions

        int[] strikes = new int[n]; //Array that holds the freqeuency with which each union i strikes at strikes[i].
        for (int i = 0; i < n; i++){
            strikes[i] = in.nextInt();
        }

        int[][] calendar = new int[m][n];   //2D array that represents our calendar; each union a receives n days in its calendar

        //Set all the calendar days to 0, which indicates no strike on that day.
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                calendar[i][j] = 0;
            }
        }

        //Set all the calendar days which have a strike to 1. 
        int dayOfTheWeek = 0;   //tracks which day of the week it is (Sun=0, Mon=1, Tues=2, ..., Fri=6, Sat=7)
        int day = 0;    //Tracks which day out of n days we are on.
        for (int union = 0; union < n; union++){
            day = 0;
            dayOfTheWeek = 0;
            while (day < m){
                dayOfTheWeek++;
                if ((day + 1) % strikes[union] == 0){
                    //Strikes ONLY take place days that are not a Friday (6) or a Saturday (7), 
                    if (dayOfTheWeek != 6 && dayOfTheWeek != 7){
                        calendar[day][union] = 1;
                    }
                }
                //If the dayOfTheWeek is at 7 (Saturday), reset it to 0 (Sunday)
                if (dayOfTheWeek == 7){
                    dayOfTheWeek = 0;
                }
                day++;  //Move to the next day.
            }
        }


        //Now look at each day. If it has at least one strike, increment noSubwayDays.
        int noSubwayDays = 0;
        for (int dayCounter = 0; dayCounter < m; dayCounter++){
            for (int unionCounter = 0; unionCounter < n; unionCounter++){
                if (calendar[dayCounter][unionCounter] == 1){
                    noSubwayDays++;
                    break;  //move away from this day and onto the next for loop.
                }

            }
        }

        System.out.println(noSubwayDays);   //print result.

        in.close();

    }


}
