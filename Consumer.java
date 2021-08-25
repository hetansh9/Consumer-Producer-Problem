

public class Consumer extends java.lang.Thread{
    Buffer buffer_element;
    Integer count;
    Integer checkSize = 0;
    Integer id;
    Integer sum;
    public static Integer store = 0;
    int[] remove;

    public Consumer(Buffer buff, int count, int id){
        this.buffer_element  = buff;
        this.count = count;
        this.checkSize = count;
        this.id = id; 

    }
    @Override
    public void run(){
        for(int i=1; i<=checkSize; i++){
            synchronized(this.buffer_element){
                if(buffer_element.isEmpty()){
                    try {
                        buffer_element.wait();
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
                else {
                    //removing the elements the elements in the form of a listsma
                    remove = buffer_element.remove();  
                              
                    System.out.printf("\033[0;4m" +"Consumer %3d consumed %3d from index %3d at time\033[0;0m %s \n"  , id, remove[1],remove[0],Coordinator.getTime());
                    //System.out.flush();
                    buffer_element.notify();
                    remove[1]++;
                    store += remove[1];  
                
                }
            }
            
        }
    }

    public static Integer getCheckSum(){
        store++;
        return store;
    }

    
}
