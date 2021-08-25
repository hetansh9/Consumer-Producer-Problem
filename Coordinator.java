import java.lang.Exception;
import java.util.Random;
import java.util.ArrayList; 


import java.time.Instant;
import java.time.Clock;
import java.time.Duration;

class Coordinator {
	public static void main(String[] args) {
		//inputing the values from command line arugments 
		int buffer_elements = Integer.parseInt(args[0]);
		int items = Integer.parseInt(args[1]);
		int consumers  = Integer.parseInt(args[2]);
		int producers = Integer.parseInt(args[3]);
		int seed = Integer.parseInt(args[4]);
	//creating new threads and buffer 
		Buffer buffer = new Buffer(buffer_elements);
		ArrayList<Producer> p = new ArrayList<>();
		ArrayList<Consumer> c = new ArrayList<>();
		Consumer new_con = new Consumer(buffer, items, consumers);
		Producer new_prod = new Producer(buffer, items, producers ,seed);
		Producer[] thread_prod = new Producer[producers];
		Consumer[] thread_cons = new Consumer[consumers];
		// i have used array lists to store everything in a list and then run the thread on it

	
	//	for(int i=1; i<= producers; i++){
		if (producers == 1 ) {
			p.add(new Producer(buffer, items, producers, seed));
			
		}
		if( producers == 2 ){
			int d = items/producers;
			for(int i=0; i<2; i++){
			p.add(new Producer(buffer, d, producers, seed));
			}
		
		}
		if( producers == 3 ){
			int d = items/producers;
			for(int i=0; i<3; i++){
			p.add(new Producer(buffer, d, producers, seed));
			}
		}
		if (consumers == 1 ) {
				c.add(new Consumer(buffer, items, 1));
		}
		if( consumers == 2 ){
			int d = items/consumers;
			for(int i=0; i<2; i++){
				c.add(new Consumer(buffer, d, consumers));
			}
		}
		if( consumers == 3 ){
			int d = items/consumers;
			for(int i=0; i<3; i++){
				c.add(new Consumer(buffer, d, producers));
				
			}

		} 
		

/*
		
			for(int i=0; i<producers; i++){
			
				thread_prod[i] = new Producer(buffer, items, producers, seed);
				if( i == 2 ){
					thread_prod[i] = new Producer(buffer, items/i, producers, seed);
				}
				if( i ==3 ){
					thread_prod[i] = new Producer(buffer, items/i, producers, seed);
				}
				thread_prod[i].start();
		}
		
		
		
			for(int i=0; i<consumers; i++){
					if ( i == 0 ){
						thread_cons[i] = new Consumer(buffer, items, consumers);
						//thread_cons[0].start();
					}
					if( i == 1 ){
						thread_cons[i] = new Consumer(buffer, items/2, consumers);
						}

					if( i == 2 ){
						thread_cons[i] = new Consumer(buffer, items/3, consumers);
					}
					thread_cons[i].start();
					try{
						thread_cons[i].join();
						
						
						}catch (InterruptedException e){
							e.printStackTrace();
						}
					
			}	*/
		

	
		
		for(Producer k: p){
			k.start();
		}
		
		for(Consumer i: c){
			i.start();
			try{
				i.join();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			
		}
		
		try { 
			for(Producer k: p){

				k.join();
			}
			
			for(Consumer i: c){
				i.join();
				
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		} 
	
		System.out.printf("Producer(s): Finished Producing %3d items with checksum being %3d \n", items, Producer.getCheckSum());
		System.out.printf("\033[0;4m"+"Consumer(s): Finished Consuming %3d items with checksum being %3d\033[0;0m\n", items, Consumer.getCheckSum());

	}

	//Call this function from your producer or your consumer to get the time stamp to be displayed
	public static String getTime() {
		Clock offsetClock = Clock.offset(Clock.systemUTC(), Duration.ofHours(-9));
		Instant time = Instant.now(offsetClock);
		String timeString = time.toString();
		timeString = timeString.replace('T', ' ');
		timeString = timeString.replace('Z', ' ');
		return(timeString);
	}

}