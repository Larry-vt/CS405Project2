import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class Main {
	static  int MEMORY_MAX = 0;
	public static ArrayList FileReader(String path) throws IOException {
		ArrayList<Process> all = new ArrayList<Process>();//every process that'll run in order
		File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        int id=1;
        int PROC_SIZE_MAX = 512; 
        int NUM_PROC = 10;
        int MAX_PROC_TIME = 10000;
       for(int i =0;i<4;i++) {//while the line isn't null create a proccess object
    	 line =br.readLine();
       	 String[] arr = line.split(" ");
       	 switch(i) {
       	 case 0:
       		 MEMORY_MAX =  Integer.parseInt(arr[1]);
       		 break;
       	 case 1:
       		PROC_SIZE_MAX =  Integer.parseInt(arr[1]);
       		 break;
       	 case 2:
       		NUM_PROC =  Integer.parseInt(arr[1]);
       		 break;
       	 case 3:
       		MAX_PROC_TIME=  Integer.parseInt(arr[1]);
       		 break;
       	 }
		}
		for(int i =0; i<NUM_PROC;i++) {
			Random rand = new Random();
			String name = "p" + i;
			int time =  (int) ((int)Math.floor(Math.random() * (MAX_PROC_TIME - 1 + 1) + 1) * .001); // random ms converted to seconds
			while(time ==0) {
			time =  (int) ((int)Math.floor(Math.random() * (MAX_PROC_TIME - 1 + 1) + 1) * .001);	//no zeros
			}
			int size =  (int)Math.floor(Math.random() * (PROC_SIZE_MAX - 1 + 1) + 1);//convert to seconds and whole and not 0
			Process p = new Process(size, time, name);
		    all.add(p);
		}
		return all;
		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ArrayList <Process> test = FileReader("src/test");
		System.out.println("WORST FIT");
		MemoryAllocator wf = new MemoryAllocator(MEMORY_MAX,test);
		wf.worstfit();
		System.out.println("BEST FIT");
		MemoryAllocator bf = new MemoryAllocator(MEMORY_MAX,test);
		bf.bestfit();
		System.out.println("FIRST FIT");
		MemoryAllocator ff = new MemoryAllocator(MEMORY_MAX,test);
		ff.firstfit();
	}

}
