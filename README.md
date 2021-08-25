# Consumer Producer Problem in java

## Consumer would not remove an item from the buffer when it is empty and the producer will not add an item in the buffer when it is full. The program uses threads.

### Below is a desrcription of files 
1. Coordinator.java - This is the driver program which will be used to create an instance of the Circular buffer, and create and wait for one thread of producer and one or more threads of and consumer.
2. Producer.java - Each instance of a producer generates a single number between 0 and 99 (both inclusive) randomly and inserts into the buffer, and reports the location and the time the number was inserted into the buffer. 
3. Consumer.java  - Each instance of a consumer reads a number from the buffer and reports the location and the time the number was read from the buffer.
4. Buffer.java - Creates a circular buffer whose size is passed as an argument to it.
5.  Makefile - For compiling, and cleaning
