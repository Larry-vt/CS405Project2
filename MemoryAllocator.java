import java.util.ArrayList;



public class MemoryAllocator {
	int Memory_max = 1024;
	ArrayList<Process> all = new ArrayList<Process>();//every process that'll run in order
	ArrayList<Process> running = new ArrayList<Process>(); //those currently in the memory
	
	public MemoryAllocator(int MEMORY_MAX) {
		Memory_max = MEMORY_MAX;
	}
	public void print() {
		//[0,1,2,3];
		//go through runninglist and just print P1[15s](30kb),Free (40kb), P3(100s), Free (10kb) 
	}
	//arraylist if 0 40kb,1 50kb free
	// hole 50kb
	//arraylist.remove(index)1
	//arraylist.at 0 + 50kb
	//0=90kb
	public firstfit() {
		
	}
	public worstfit() {
		
	}
	public bestfit() {
		
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}