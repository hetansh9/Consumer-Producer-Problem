
import java.util.Random;


public class Producer extends java.lang.Thread{
	//Necessary variables and object declaration
	Random randomWithSeed;
	private Buffer buffer_element;
	private Coordinator time;
	Integer counter = 0;
	Integer id ;
	Integer items = 0; 
	Integer checkSize = 0;
	public static Integer keep_track = 0;
	public static Integer store = 0;

	public Producer(Buffer buff, int count, int id, int seed) {
		//Assign values to the variables
		randomWithSeed = new Random(seed);
		this.buffer_element = buff;
		this.counter = count;
		this.checkSize = count;
		this.id = id;
	}

	@Override
	public void run() {
		for(int i=1; i<=checkSize; i++){

		int variable = randomWithSeed.nextInt(100);
		int trace_index;


		synchronized(this.buffer_element){
			
			if(buffer_element.isFull()){
				try{
					//waiting for the bufffer if its full or not
					buffer_element.wait();
					
				}catch (InterruptedException e){
					e.printStackTrace();
				}
			}
			else {
				//inserting the element into a variable and using that variable as a location of the insertion
				trace_index = buffer_element.insert(variable);
				keep_track += variable;
				System.out.printf("Producer %3d inserted %3d at   index %3d at time %s\n",  id, variable, trace_index, Coordinator.getTime());
				buffer_element.notify();
				
			}
			}

		}
		//System.out.println("Producer(s): Finished Producing "+ counter + " items with checksum being " + keep_track);
		
	}

	public static Integer getCheckSum(){
		return keep_track;
	}


}
