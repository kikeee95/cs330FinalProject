/*
  This simulator of a Central Processing Unit with Cache
*/
class CPU extends Thread{

    //The number of cores in this
    int cores;

    //The first cache in this
    byte[] cache1;

    //THe size of the first cache
    int cache1Size;
    public CPU(int numCores, int cacheSize){
     cores = numCores;
     cache1Size = cacheSize;
    }

    /**

     **/
    @Override
    public void run(){
        while(!memSim.done){
            
    }
    }
}
