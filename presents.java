import java.util.Random;
import java.util.concurrent.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * dedicate one thread per servant - 4 servants
 * minotaur received 500,000 presents 
 * 
 */


public class presents{
    static int val = 0;
    static boolean cards[] = new boolean[500000];
    static PriorityBlockingQueue<Integer>
            clq = new PriorityBlockingQueue<Integer>();

    
    public static void main(String[] args){
            
        //create the unordered bag
        Integer unorderedBag[] = new Integer[500000];
        for(int i = 0; i<500000; i++){
            unorderedBag[i] = i;
            cards[i] = false;
        }
        List<Integer> bagList = Arrays.asList(unorderedBag);
        Collections.shuffle(bagList);
        bagList.toArray(unorderedBag);
    
        long begin = System.currentTimeMillis(); 
        // PriorityBlockingQueue<Integer>
        //     clq = new PriorityBlockingQueue<Integer>();
    
        //checkerThread checker = new checkerThread(clq, cards, val);
       // linkerThread linker = new linkerThread(clq, cards, add);
        //removerThread remover = new removerThread(clq, cards);
        

        for(int i = 0; i<500000;i++){
            //checkerThread checker = new checkerThread(clq, cards, i);
            //checker.start();
            linkerThread linker = new linkerThread(unorderedBag[i]);
            linker.start();
            
            
            removerThread remover  = new removerThread();
            remover.start();
            
        }

        long end = System.currentTimeMillis();
        long time = end-begin;
        System.out.println(time/1000 + " seconds");

    }


    static class checkerThread extends Thread{

    public void run(){
        if(clq.contains(val)){
            System.out.println(val + " is in the chain");
        }
        else{
            System.out.println(val + " is not in the chain");
        }
    }
    }

    static class removerThread extends Thread{

        protected int giftRemoved = 0;
        public void run(){
        
            //remove a gift
            //since we are removing the head we do not need to relink
            try {
                giftRemoved = clq.poll(100, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //write a thank you card
            cards[giftRemoved] = true;
            Thread.currentThread().interrupt();
        }
        }
    

    static class linkerThread extends Thread{
        int add;
        public linkerThread(int add){
            this.add = add;
        }
        public void run(){
            clq.add(add);
        
        }
    }
}