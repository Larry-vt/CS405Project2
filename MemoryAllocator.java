import java.util.ArrayList;



public class MemoryAllocator {
	int memory_Max = 1024;
	ArrayList<Process> all = new ArrayList<Process>();//every process that'll run in order
	ArrayList<Process> running = new ArrayList<Process>(); //those currently in the memory
	ArrayList<Process> done= new ArrayList<Process>(); //all of those have finished 
	int total_Processes;
	
	public MemoryAllocator(int MEMORY_MAX, ArrayList<Process> all) {
		memory_Max = MEMORY_MAX;
		all = this.all;
		total_Processes = all.size();
	}
	public void print() { // print every run;
	System.out.println(running);//prints out current running list
	//number of holes
	double amountHole =0;
	int totalHole =0;
	for(int i =0;i<running.size();i++) {
		if(running.get(i).name.equals("free")) {//if free space
			totalHole++;
			amountHole =running.get(i).size;
		}
		//total size of current holes
		//total free / total memory 
		double avg = amountHole/totalHole;
		double percent = (totalHole/memory_Max) * 100;
		System.out.println("Stats: Number of Holes:" + totalHole + "Avg: " + avg +"KB Total:" + amountHole + "KB Percent:" + percent + "%" );
	}
	}
	public void compact() {//compat all touching holes
		
	}
	//PROCESS COMPLETE
	//replace finsihed process with Process named Free, time -1, size of old process
	
	//FILLING HOLE ROUGH
	// check after anytime a process finishes
	//arraylist if 0 40kb,1 50kb free
	// hole 50kb
	//arraylist.remove(index)1
	//arraylist.at 0 + 50kb
	//0=90kb
	public firstfit() {
		
	}
	public worstfit() {
		//1024
		//100, 600, 700
		//starting check to fill intital 
		int time =0;
		boolean full = false; // max memory hax been reached or like only one pocket at the end that can't fit process
		int current_Memory= 0;//keeps track of how much memory is in use
		while(!full) {//memory not full first run through 				
		for(int i =0;i<all.size();i++) {
			if(all.get(i).size + current_Memory<= memory_Max) {
				current_Memory+= all.get(i).time;//keep track of how much memory we've uised
				running.add(all.get(i));//if process fits add to running
				all.remove(i);
			}else { //can't fit in the first allocation
				full = true;
				Process endcap = new Process(memory_Max-current_Memory,-1,"free");//place end cap place holder with amount of free left
			
		}
		}
			while(total_Processes != done.size()){// while not all process are complete  COULD BE METHOD
				//check for holes check right of every index in running in current is hole and right is hole then fill check for right if also free
				for(int i =0;i<running.size();i++){//combine hole every hole then check if last process can fit in might need size -1
					if(running.get(i).name.equals("free") && running.get(i+1).name.equals("free")) {//then two adjacet holes are freee
						Process combine = new Process(running.get(i).size+running.get(i+1).size,-1,"free");
					}
				}
				for(int i =0; i< running.size();i++) {//check if any waiting process can fit in holes worstfirst alg
					int worst =0;
					int index =0;
					if(running.get(i).name.equals("free")) {// if the current space is free
						if(worst > running.get(i).size - all.get(0).size ) {// if better
							worst = running.get(i).size - all.get(0).size;//the most wasted space
							index = i;
						}			
				}
					running.set(i, null);
				}
				for(int i =0;i<running.size();i++){
					running.get(i).time --;
					if(running.get(i).time == 0) {//if current running process has finished
						done.add(running.get(i));
						Process empty = new Process(running.get(i).size,-1,"free");
						running.set(i,empty);//set open spot to "free"
						
					}
				}
				//print the facts
					
			}
		}
													
									
		}
	
		
		
		
	
	public bestfit() {
		
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
