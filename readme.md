#How to compile:
Navigate to repository file on computer

##To run part 1:
javac presents.java
java presents

##To run part 2:
javac temperature.java
java temperature


###Part 1 Method and Runtime discussion:
For part 1, my strategy was to implement the three threads for each servents task and use a PriorityBlockingQueue which is thread safe and confirms the correct order when inserting. The checker thread checked if a certain gift was in the chain by checking the queue. The remover thread removed the head of the queue and wrote a thank you card by marking a value in a cards array as true. And finally the linker thread was responsible for inserting the gifts into the chain, in the correct spot, and mainting the links. 
My program runs in about 70 seconds, so there is possibly room for improvement on my strategy. 

###Part 2
My strategy for part 2 was to utilize 8 threads for each of the eight temperature sensors, and each of these eight threads simulates recording a temperature by generating a random number between -100 and 70. The threads were set to sleep for 1 second before collecting the next reading. This is because I simulated the hour with readings at every minute by doing a real full minute with a reading at every second. To get the greatest difference between which ten mintues I looped through the recordings to determine which ten minute interval had the greatest difference. The runtime is only as long as it takes to do the recordings, so about one minute. 