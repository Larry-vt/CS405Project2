import java.util.ArrayList;

//PROCESS COMPLETE
//replace finsihed process with Process named Free, time -1, size of old process

//FILLING HOLE ROUGH
// check after anytime a process finishes
//arraylist if 0 40kb,1 50kb free
// hole 50kb
//arraylist.remove(index)1
//arraylist.at 0 + 50kb
//0=90kb

public class MemoryAllocator {
	int memory_Max = 1024;
	ArrayList<Process> all = new ArrayList<Process>();//every process that'll run in order next process always kept at zero
	ArrayList<Process> running = new ArrayList<Process>(); //those currently in the memory
	ArrayList<Process> done= new ArrayList<Process>(); //all of those have finished 
	int total_Processes;
	
	public MemoryAllocator(int MEMORY_MAX, ArrayList<Process> all) {
		memory_Max = MEMORY_MAX;
		this.all = all;
		total_Processes = all.size();
	}
	public void print() { // print every run;
	if(!all.isEmpty()) {
		System.out.println(all.get(0).name + " waiting with size:" + all.get(0).size);
	}
	System.out.println(running);//prints out current running list
	//number of holes
	double amountHole =0;
	int totalHole =0;
	for(int i =0;i<running.size();i++) {
		if(running.get(i).name.equals("free")) {//if free space
			totalHole++;
			amountHole =running.get(i).size;
		}
	
		

	}
	double avg = amountHole/totalHole;
	double percent =  (double)((double)totalHole/(double)memory_Max) * 100;
	System.out.println("Stats: Number of Holes:" + totalHole + "Avg: " + avg +"KB Total:" + amountHole + "KB Percent:" + percent + "%" );
	}
	public void compact() {//compact all touching holes
		//insert
		//keep running until a new hole hasn't been made
		for(int i =1;i<running.size();i++) {
			if(running.get(i-1).name.equals("free") && running.get(i).name.equals("free")) {//left and current index have hole
				Process p = new Process(running.get(i-1).size +running.get(i).size  , -1, "free");//set the empty block to that size.
				running.set(i-1, p);
				running.remove(i);
				i=i-1;//go back a spot
			}
		}
		
		
	}
	public boolean fits(int size) {
		for(int i =0;i<running.size();i++) {
			if(size<= running.get(i).size && running.get(i).name.equals("free")) {
				return true;
			}
		}
		return false;
		
	}

	public firstfit() {
		
	}
	public void worstfit() {
 		boolean full = false; // max memory hax been reached or like only one pocket at the end that can't fit process
		int current_Memory= 0;//keeps track of how much memory is in use
		int id =0;
		while(!full) {//memory not full first run through 
			if(all.get(id).size + current_Memory<= memory_Max) {
				current_Memory+= all.get(id).size;//keep track of how much memory we've uised
				running.add(all.get(id));//if process fits add to running
				id++;
			}else { //can't fit in the first allocation
				full = true;
				if(current_Memory != memory_Max) {
					Process endcap = new Process(memory_Max-current_Memory,-1,"free");//place end cap place holder with amount of free left
					running.add(endcap);
					for(int x =0;x<id;x++) {
						all.remove(0);
					}
					break;
				}else {
					for(int x =0;x<id;x++) {
						all.remove(0);
					}
					break;
					
				}


			
		}
		}
		//Add starting processes till full
		
			while(total_Processes != done.size()){// while not all process are complete
				compact();
				//check for holes check left of every index in running in current is hole and right is hole then fill check for right if also free
				//ALG
				int index = 0;//where the most space wasted pocket is //where the most space wasted pocket is 
				int worst =-1;//most space wasted
				boolean change = false;

					if(!all.isEmpty() && fits(all.get(0).size)) {// if current waiting process can fit
						for(int i =0;i<running.size();i++) {
					if(running.get(i).name.equals("free")){
						if(worst < running.get(i).size - all.get(0).size ) {// if better by wasting more space
							worst = running.get(i).size - all.get(0).size;//the most wasted space
							index = i;
							change = true;
						}			
				}
				}
				}
					if(change) {// are we able to make  a change?
						if(running.get(index).size - all.get(0).size !=0) {
							Process newHole = new Process(running.get(index).size - all.get(0).size,-1,"free"); // new hole
							running.set(index, all.get(0));//set where the hole was wiht new process
							running.add(index+1,newHole);//add hole next to old self
							all.remove(0);
						}else {
							running.set(index, all.get(0));//set where the hole was wiht new process
							all.remove(0);
						}
					}else {//if no change take time down and remove complete processes
						//check run times
						for(int i =0;i<running.size();i++){
							running.get(i).time --;
							if(running.get(i).time == 0) {//if current running process has finished
								done.add(running.get(i));
								Process empty = new Process(running.get(i).size,-1,"free");
								running.set(i,empty);//set open spot to "free"
								
							}
						}
						//print the facts
						print();
				}
					}

			}
				

			
		
													
									
		
	
		
		
		
	
	public bestfit() {
		
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
