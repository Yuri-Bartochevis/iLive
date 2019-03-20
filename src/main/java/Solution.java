import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

class Solution {

    public static void main(String[] args) {
        int[] vetor = new int[4];
        vetor[0] = 10;
        vetor[1] = 20;
        vetor[2] = 10;
        vetor[3] = 40;
        ArrayList list = new ArrayList();
        for(int i : vetor ){
            list.add(i);
        }

        int result = solution(list);
        System.out.println(result);
    }

    public static int solution(List<Integer> heights) {
        AtomicInteger times = new AtomicInteger();
        IntStream.range(0, heights.size())
                .forEach(idx -> {
                    List<Integer> comparableList = new ArrayList(heights);
                    comparableList.remove(idx);
                    System.out.print(comparableList);
                    if (isOrdered(comparableList)) {
                        times.getAndIncrement();
                    }
                });
        return times.get();
    }

    private static boolean isOrdered(List<Integer> comparableList) {
        List<Integer> sortedList = new ArrayList<Integer>(comparableList);
        sortedList.sort(Integer::compareTo);
        return sortedList.equals(comparableList);
    }


    private static boolean isOrderedRaw(int[] comparable){

        int[] sortedList = new int[comparable.length];

        int index = 0;
        for(int i : comparable){
            sortedList[index] = i;
            index++;
        }

        for(int i = 0; i < sortedList.length; i++){
            int actual = sortedList[i];
            int previous = i - 1;
            while (previous >= 0 && sortedList[previous] > actual){
                int aux = sortedList[previous];
                sortedList[previous] = actual;
                sortedList[previous + 1] = aux;
                previous--;
            }
        }

        return Arrays.equals(comparable, sortedList);
    }
}



class Solution1 {
/*
    A binary gap within a positive integer N is any maximal sequence of consecutive zeros that is surrounded by ones at both ends in the binary representation of N.

    For example, number 9 has binary representation 1001 and contains a binary gap of length 2. The number 529 has binary representation 1000010001 and contains two binary gaps: one of length 4 and one of length 3. The number 20 has binary representation 10100 and contains one binary gap of length 1. The number 15 has binary representation 1111 and has no binary gaps. The number 32 has binary representation 100000 and has no binary gaps.

    Write a function:

    class Solution { public int solution(int N); }

    that, given a positive integer N, returns the length of its longest binary gap. The function should return 0 if N doesn't contain a binary gap.

    For example, given N = 1041 the function should return 5, because N has binary representation 10000010001 and so its longest binary gap is of length 5. Given N = 32 the function should return 0, because N has binary representation '100000' and thus no binary gaps.

    Write an efficient algorithm for the following assumptions:

    N is an integer within the range [1..2,147,483,647].
*/

    public static void main(String[] args) {
        int result = solution(33);
        System.out.println(result);
    }

    public static int solution(int N) {
        String binary = Integer.toBinaryString(N);
        int i = binary.length() - 1;

        while (binary.charAt(i) == '0') {
            i--;
        }

        int gap = 0;
        boolean hasZero = false;
        for (; i >= 0; i--) {
            if (binary.charAt(i) == '1' && hasZero) {
                gap++;
                hasZero = false;
            }
            if (binary.charAt(i) == '0') {
                hasZero = true;
            }
        }
        return gap;
    }


}

class Solution2 {
/*
  100 doors in a row are all initially closed. You make
100 passes by the doors. The first time through, you
visit every door and toggle the door (if the door is
closed, you open it; if it is open, you close it).
The second time you only visit every 2nd door (door
#2, #4, #6, ...). The third time, every 3rd door
(door #3, #6, #9, ...), etc, until you only visit
the 100th door.

Question: What state are the doors in after the last
pass? Which are open, which are closed?
*/

    public static void main(String[] args) {
        boolean[] result = solution();

    }

    public static boolean[] solution() {
        boolean[] doors = new boolean[100];

        for(int i = 1; i<= doors.length; i++){
            for(int j = 0;j< doors.length; j++) {
                if (j % i == 0) {
                    if (doors[j]) {
                        doors[j] = false;
                    } else {
                        doors[j] = true;
                    }
                }
            }
            for (boolean b: doors){
                System.out.print(b + ",");
            }
            System.out.println("END OF LAP : " + i);
        }
        return doors;
    }


}



class Solution3 {
/*
  ordering vector
*/

    public static void main(String[] args) {
        int[] result = solution();
        for(int i : result){
            System.out.print(i + ", ");
        }
    }

    public static int[] solution() {
        int[] doors = new int[5];
        doors[0] = 4;
        doors[1] = 2;
        doors[2] = 1;
        doors[3] = 7;
        doors[4] = 9;

        for(int i = 0; i < doors.length; i++){
            int actual = doors[i];
            int previous = i - 1;
            while (previous >= 0 && doors[previous] > actual){
                int aux = doors[previous];
                doors[previous] = actual;
                doors[previous + 1] = aux;
                previous--;
            }
        }
        return doors;
    }


}


class Solution5 {
/*
  ordering vector
*/

    public static void main(String[] args) {
        int result = solution(6000,7000);
            System.out.println(result);
    }

    public static int solution(int A,int B) {

        ArrayList<Integer> array = new ArrayList<>();

        for (int i = A ; i <= B; i++) {
            array.add(i);
        }
        Collections.reverse(array);

        AtomicInteger aux = new AtomicInteger();

        final AtomicInteger count = new AtomicInteger();

        array.stream().forEach(element -> {
            int validated = element;
            aux.set(0);
               while (isValidSqrt(validated)){
                   validated = (int) Math.sqrt(validated);
                   aux.getAndIncrement();
               }
               if(count.get() < aux.get()){
                   count.set(aux.get());
               }
        });



        return count.get();

    }

    private static boolean isValidSqrt(Integer element) {
        int x = (int) Math.sqrt(element);
        return Math.pow(x,2) == element;
    }

}

class Solution4 {
/*
  ordering vector
*/

    public static void main(String[] args) {
        printPermutationsIterative("biro");
    }

    private static void printPermutationsIterative(String string){
        int [] factorials = new int[string.length()+1];
        factorials[0] = 1;
        for (int i = 1; i<=string.length();i++) {
            factorials[i] = factorials[i-1] * i;
        }

        for (int i = 0; i < factorials[string.length()]; i++) {
            String onePermutation="";
            String temp = string;
            int positionCode = i;
            for (int position = string.length(); position > 0 ;position--){
                int selected = positionCode / factorials[position-1];
                onePermutation += temp.charAt(selected);
                positionCode = positionCode % factorials[position-1];
                temp = temp.substring(0,selected) + temp.substring(selected+1);
            }
            System.out.println(onePermutation);
        }
    }


    public static int fatorial(int num) {
        if (num <=1 )  return 1;
        else  return num * fatorial(num - 1);
    }
}

class Solution6 {
/*
  ordering vector
*/

    public static void main(String[] args) {
        System.out.println(sockMerchant(9,new int[]{10, 20, 20, 10, 10, 30, 50,10,20}));
    }

    static int sockMerchant(int n, int[] ar) {
        int[] verifiedSocks = new int[n];
        int idx =0;
        int countPairSocks = 0;
        for(int i = 0; i < ar.length; i++)
        {
            int currentSock = ar[i];
            int countSocks = 0;
            for(int j = 0; j < ar.length; j++){
                if(!verifyIfExists(currentSock, verifiedSocks) && ar[i] == ar[j]){
                    countSocks++;
                }
            }

            int nPair = countSocks/2;
            countPairSocks += nPair;
            verifiedSocks[idx] = currentSock;
            idx++;
        }

        return countPairSocks;

    }

    private static boolean verifyIfExists(int current,int ar[]){
        for(int i = 0; i < ar.length; i++)
        {
            if(ar[i] == current) return true;
        }
        return false;
    }

}




class Solution7 {
/*
  hiking
*/

    public static void main(String[] args) {
        System.out.println(countValeys(8, "UDDDUDUU"));
    }

    static int countValeys(int n, String track) {
        char[] trackSteps = track.toCharArray();
        int seaLv = 0;
        int hikingLv = 0;
        boolean isInsideValley = false;
        int count =0;
        for (char step: trackSteps) {
            if(step == 'U'){
                hikingLv++;
            }

            if(step == 'D'){
                hikingLv--;
            }

            if(hikingLv > seaLv){
                isInsideValley = false;
            }
            if(!isInsideValley && hikingLv < seaLv){
                count++;
                isInsideValley = true;
            }

        }
        return count;
    }

}



class Solution8 {
/*
  cloud games
*/

    // Complete the repeatedString function below.
    static long repeatedString(String s, long n) {
        int count = 0;
        int aux = 0;
        char[] splitedString = s.toCharArray();
        for(int i = 0 ; i < n; i++){
            if(aux > s.length()){
                aux = 0;
            }
            if(splitedString[aux] == 'a')
                count++;

            aux ++;
        }
        return count;
    }


    public static void main(String[] args) {
        System.out.println(cloudGames(new char[]{0, 0, 0, 1, 0, 0} ));
    }


    static int cloudGames(char[] clouds) {
        int jumps = 0;


        for(int i = 0; i <= clouds.length; i++){
            if(i < clouds.length -2 && isAvailableCloud(clouds[i + 2])){
                jumps++;
                i++;
            }else{
                if(i < clouds.length -1 && isAvailableCloud(clouds[i + 1])){
                    jumps++;
                }
                else{
                    //gameOver;
                }
            }
        }

        return jumps;
    }

    private static boolean isAvailableCloud(char c){
        return c == 0;
    }

}



 class Solution10 {

     public static void main(String[] args) {
         System.out.println(repeatedString("aba",10));
     }
    static long repeatedString(String s, long n) {
        int count = 0;
        int aux = 0;
        char[] splitedString = s.toCharArray();
        for(int i = 0 ; i < n; i++){
            if(aux > s.length() - 1){
                aux = 0;
            }
            if(splitedString[aux] == 'a')
                count++;

            aux ++;
        }
        return count;
    }
}
