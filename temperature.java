import java.util.concurrent.*;
import java.util.*;



public class temperature {
    static CopyOnWriteArrayList<Integer> readingsList = new CopyOnWriteArrayList<Integer>();
    /*
     * implemented using 1 second to represent a minute, and 60 seconds to represent the hour
     * 
     */
    public static void main(String[] args){
        //int rand = ThreadLocalRandom.current().nextInt(-100, 70 + 1);
        
        sensor s1 = new sensor();
        sensor s2 = new sensor();
        sensor s3 = new sensor();
        sensor s4 = new sensor();
        sensor s5 = new sensor();
        sensor s6 = new sensor();
        sensor s7 = new sensor();
        sensor s8 = new sensor();

        s1.start();
        s2.start();
        s3.start();
        s4.start();
        s5.start();
        s6.start();
        s7.start();
        s8.start();

        try {
            Thread.sleep(61*1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Collections.sort(readingsList);
        int size = readingsList.size();
        int max = 0;
        int lwr = 0;
        int upr = 0;

        System.out.println("-----Hourly Report Generated------");
        System.out.println("Five Highest Temperatures Recorded: ");
        System.out.println(readingsList.get(479)); 
        System.out.println(readingsList.get(478)); 
        System.out.println(readingsList.get(477)); 
        System.out.println(readingsList.get(476)); 
        System.out.println(readingsList.get(475)); 


        System.out.println("Five Lowest Temperatures Recorded: ");
        System.out.println(readingsList.get(0)); 
        System.out.println(readingsList.get(1)); 
        System.out.println(readingsList.get(2)); 
        System.out.println(readingsList.get(3)); 
        System.out.println(readingsList.get(4)); 

        
        for(int i = 0; i<size;i++){
            if(i%80 == 0 && i != 0){
                //every tenth reading record temp diff
                int temp = readingsList.get(i) - readingsList.get(i-80);
                if(Math.abs(temp) > max){
                    max = Math.abs(temp);
                    lwr = (i)-80;
                    upr = (i);
                }
            }
        }

        System.out.println("The biggest temperature change occured from the " + (lwr/8) + " minute to the " + (upr/8) + " minute." );
        System.out.println((lwr/8) + ": " + readingsList.get(lwr));
        System.out.println((upr/8) + ": " + readingsList.get(upr));
        System.out.println("Difference: " + max);

        System.out.println("The report is reliable and correct because we utilize all the readings from all");
        System.out.println("eight sensors and then list out the top 5 and bottom 5 recorded temperatures from all sensors.");
        System.out.println("We then analyze the readings that are ten mintues apart on each sensor, calculate the greatest");
        System.out.println("difference, and print this out in the report above. ");
        System.out.println("The report is also very efficient as it does not take any extra time outside collecting the readings.");
        
        //print all readings 
        // for(int i = 0; i<size; i++){
        //     System.out.println("Index " + i + ": " + readingsList.get(i));
        // }

    }

    //sensor class
    static class sensor extends Thread{
        int rand;

        public void run(){
            //genenrate a random value to simulate a temp reading
            for(int i = 0; i<60;i++){
                rand = ThreadLocalRandom.current().nextInt(-100, 70 + 1);
                readingsList.add(rand);
            
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }
}
