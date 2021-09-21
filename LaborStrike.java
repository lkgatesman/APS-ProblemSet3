import java.util.*;

public class LaborStrike{

    public static void main(String args[]){

        Scanner in = new Scanner(System.in);

        int m = in.nextInt();
        int n = in.nextInt();

        int[] strikes = new int[n];

        for (int i = 0; i < n; i++){
            strikes[i] = in.nextInt();
        }

        int[][] calendar = new int[m][n];

        //Set all the calendar days to 0, which indicates no strike on that day.
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                calendar[i][j] = 0;
            }
        }

        //Set all the calendar days which have a strike to 1. 
        int dayOfTheWeek = 0;
        int day = 0;
        for (int union = 0; union < n; union++){
            day = 0;
            dayOfTheWeek = 0;
            while (day < m){
                dayOfTheWeek++;
                if ((day + 1) % strikes[union] == 0){
                    if (dayOfTheWeek != 6 && dayOfTheWeek != 7){
                        calendar[day][union] = 1;
                    }
                }
                if (dayOfTheWeek == 7){
                    dayOfTheWeek = 0;
                }
                day ++;
            }
        }

        /*System.out.println(Arrays.toString(strikes));
        for (int i = 0; i < m; i++){
            System.out.println("Day " + (i+1) + Arrays.toString(calendar[i]));
        }*/

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

        System.out.println(noSubwayDays);

        in.close();

    }


}