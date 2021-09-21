import java.util.Scanner;

public class AwesomeNumbers {

    public static void main(String args[]){

        //Scanner to read in input integer.
        Scanner in = new Scanner(System.in);

        //Input number n. We are to find all numbers less than or equal to n that are both prime and have
        //a 1 in the one's place.
        int n = in.nextInt();

        //Variable to count the number of awesomeNumbers between 1 and n (inclusive).
        int primes = 0;

        //Array to store which numbers i are primes.
        //If an number i is prime, then primeNums[i] = true.
        boolean[] primeNums = sieveOfEratosthenes(n);

        //Iterate through the primeNums, looking only at numbers with a 1 in the one's place.
        // Increment primes variable accordingly.
        for (int i = 11; i <= n; i = i + 10){
            if (primeNums[i]){
                primes++;
            }
        }

        in.close();
        System.out.println(primes);

    }

    //Algorithm to find prime numbers between 0 and n as fast as possible.
    public static boolean[] sieveOfEratosthenes(int n)
    {
        // Boolean array to hold which are prime numbers. 
        // If a number i is prime, then prime[i] = true. Else, prime[i] = false.
        boolean prime[] = new boolean[n+1];

        for(int i = 0; i <= n; i++){
            prime[i] = true;
        }

        for(int p = 2; p*p <=n; p++)
        {
            // If prime[p] is not changed, then it is a prime
            if(prime[p] == true)
            {
                // Update all multiples of p
                for(int i = p*p; i <= n; i += p)
                    prime[i] = false;
            }
        }
         
        return prime;
    }

    
}
