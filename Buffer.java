
public class Buffer {
    
    private int sizeOFBuffer;
    private int buffer_count = 0;
    private int total_elements[];
    //calling the constructor 
    public Buffer(int bufferSize){
        sizeOFBuffer = bufferSize;
        total_elements = new int[sizeOFBuffer];
    }
    //to 
    public synchronized boolean isEmpty() {
        return buffer_count == 0;
    }
    
    public synchronized boolean isFull(){
        return buffer_count==sizeOFBuffer;
    }

    //FIFO insert method 
    public synchronized int insert(int values){
        if(buffer_count >=sizeOFBuffer) {
            return -1;
        }
        else {
            total_elements[buffer_count++] = values;
            return buffer_count-1;
        }

    }
    //creating an array to remove the elements and storing the elements into an array 
    public synchronized int[] remove() {
        if(buffer_count <=0){
            int store[] = {-1};
            return store;
        }
        else {
            int n = total_elements[0];
            buffer_count--;
            for(int i=0; i<buffer_count; i++){
                total_elements[i] = total_elements[i+1];

            }
            int store[]  = {buffer_count, n};
            return store;
        }


    }


    




    
    
}
