import java.util.ArrayList;

public class MemoryAllocator {
	int memory_Max = 1024;
	ArrayList<Process> all = new ArrayList<Process>();// every process that'll run in order
	ArrayList<Process> running = new ArrayList<Process>(); // those currently in the memory
	ArrayList<Process> done = new ArrayList<Process>(); // all of those have finished
	int total_Processes;

	public MemoryAllocator(int MEMORY_MAX, ArrayList<Process> all) {
		memory_Max = MEMORY_MAX;
		all = this.all;
		total_Processes = all.size();
	}

	public void print() {
		System.out.println(all.get(0).name + " waiting with size:" + all.get(0).size);
		System.out.println(running);// prints out current running list
		// number of holes
		double amountHole = 0;
		int totalHole = 0;
		for (int i = 0; i < running.size(); i++) {
			if (running.get(i).name.equals("free")) {// if free space
				totalHole++;
				amountHole = running.get(i).size;
			}
			double avg = amountHole / totalHole;
			double percent = (totalHole / memory_Max) * 100;
			System.out.println("Stats: Number of Holes:" + totalHole + "Avg: " + avg + "KB Total:" + amountHole
					+ "KB Percent:" + percent + "%");
		}

	}

	public void compact() {// compat all touching holes
		// insert
		// keep running until a new hole hasn't been made
		for (int i = 1; i < running.size(); i++) {
			if (running.get(i - 1).name.equals("free") && running.get(i).name.equals("free")) {// left and current index
																								// have hole
				Process p = new Process(running.get(i - 1).size + running.get(i).size, -1, "free");// set the empty
																									// block to that
																									// size.
				running.set(i - 1, p);
				running.remove(i);
				i = i - 1;// go back a spot
			}
		}

	}

	// PROCESS COMPLETE
	// replace finsihed process with Process named Free, time -1, size of old
	// process

	// FILLING HOLE ROUGH
	// check after anytime a process finishes
	// arraylist if 0 40kb,1 50kb free
	// hole 50kb
	// arraylist.remove(index)1
	// arraylist.at 0 + 50kb
	// 0=90kb
	public void firstfit() {

		int time = 0;
		boolean full = false; // max memory hax been reached or like only one pocket at the end that can't fit
								// process
		int current_Memory = 0;// keeps track of how much memory is in use
		while (!full) {// memory not full first run through
			for (int i = 0; i < all.size(); i++) {
				if (all.get(i).size + current_Memory <= memory_Max) {
					current_Memory += all.get(i).time;// keep track of how much memory we've uised
					running.add(all.get(i));// if process fits add to running
					all.remove(i);
				} else { // can't fit in the first allocation
					full = true;
					Process endcap = new Process(memory_Max - current_Memory, -1, "free");// place end cap place holder
																							// with amount of free left
					// check that this is printed at end array
					running.add(endcap);
					break;

				}
			}
		}

		while (total_Processes != done.size()) {
			// combines holes
			for (int i = 0; i < running.size(); i++) {// combine hole every hole then check if last process can fit in
														// might need size -1
				if (running.get(i).name.equals("free") && running.get(i + 1).name.equals("free")) {// then two adjacet
																									// holes are freee
					Process combine = new Process(running.get(i).size + running.get(i + 1).size, -1, "free");
					running.remove(i + 1); // removed extra process
					running.add(combine); // add the combined process
				}
			}
			// finds and creates hole
			for (int i = 0; i < running.size(); i++) {
				running.get(i).time--;
				if (running.get(i).time == 0) {// if current running process has finished
					done.add(running.get(i));
					Process empty = new Process(running.get(i).size, -1, "free");
					running.set(i, empty);// set open spot to "free"
					running.remove(i);

				}
			}
			// determines if there is a hole and if it is big enough for next allocated
			// memmory
			for (int i = 0; i < all.size(); i++) {
				// if the name of process is free and the size of free process is less than or
				// equal to all.get(i) process
				// add it to running and remove free process
				for (int j = 0; j < running.size(); j++) {
					if (running.get(j).name.equals("free") & all.get(i).size <= running.get(j).size) {
						running.set(j, all.get(i));

					}
				}
			}
			print();
		}

	}

	public void worstfit() {
		// 1024
		// 100, 600, 700
		// starting check to fill intital
		boolean full = false; // max memory hax been reached or like only one pocket at the end that can't fit
								// process
		int current_Memory = 0;// keeps track of how much memory is in use
		while (!full) {// memory not full first run through
			for (int i = 0; i < all.size(); i++) {
				if (all.get(i).size + current_Memory <= memory_Max) {
					current_Memory += all.get(i).size;// keep track of how much memory we've uised
					running.add(all.get(i));// if process fits add to running
					all.remove(0);
					i = 0;
				} else { // can't fit in the first allocation
					full = true;
					Process endcap = new Process(memory_Max - current_Memory, -1, "free");// place end cap place holder
																							// with amount of free left
					running.add(endcap);
					break;

				}
			}
			// Add starting processes till full
		}
		while (total_Processes != done.size()) {// while not all process are complete
			compact();// check for holes check left of every index in running in current is hole and
						// right is hole then fill check for right if also free
			// ALG
			boolean open = false; // is their any space open for the next process?
			boolean stopped = false;// when trying to add a process couldn't anymore so we are stopped
			while (!stopped) {
				int index = -1;// where the most space wasted pocket is //where the most space wasted pocket is
				for (int i = 0; i < running.size(); i++) {// check if any waiting process can fit in holes worstfirst
															// alg
					int worst = 0;// most space wasted
					if (running.get(i).name.equals("free")) {// if the current space is free
						if (worst > running.get(i).size - all.get(0).size) {// if better by wasting more space
							worst = running.get(i).size - all.get(0).size;// the most wasted space
							index = i;
							open = true;// a spot for the process is open
						}
					}
				}
				if (open) {// the most wasted space block
					Process newHole = new Process(running.get(index).size - all.get(0).size, -1, "free"); // new hole
					running.set(index, all.get(0));// set where the hole was wiht new process
					running.add(index + 1, newHole);// add hole next to old self
				} else {// if their is no new opening possible we stop because process can't be added
					stopped = true;
				}
			}
			// check run times
			for (int i = 0; i < running.size(); i++) {
				running.get(i).time--;
				if (running.get(i).time == 0) {// if current running process has finished
					done.add(running.get(i));
					Process empty = new Process(running.get(i).size, -1, "free");
					running.set(i, empty);// set open spot to "free"

				}
			}
			// print the facts
			print();
		}
	}

	public void bestfit() {
		// 1024
		// 100, 600, 700
		// starting check to fill intital
		int time = 0;
		boolean full = false; // max memory hax been reached or like only one pocket at the end that can't fit
								// process
		boolean free = false;
		int current_Memory = 0;// keeps track of how much memory is in use
		while (!full) {// memory not full first run through
			for (int i = 0; i < all.size(); i++) {
				if (all.get(i).size + current_Memory <= memory_Max) {
					current_Memory += all.get(i).time;// keep track of how much memory we've uised
					running.add(all.get(i));// if process fits add to running
					all.remove(i);
				} else { // can't fit in the first allocation
					full = true;
					Process endcap = new Process(memory_Max - current_Memory, -1, "free");// place end cap place holder
																							// with amount of free left
					// check that this is printed at end array
					running.add(endcap);

				}
			}
		}

		while (total_Processes != done.size()) {
			// combines holes
			for (int i = 0; i < running.size(); i++) {// combine hole every hole then check if last process can fit in
														// might need size -1
				if (running.get(i).name.equals("free") && running.get(i + 1).name.equals("free")) {// then two adjacet
																									// holes are freee
					Process combine = new Process(running.get(i).size + running.get(i + 1).size, -1, "free");
					running.remove(i + 1); // removed extra process
					running.add(combine); // add the combined process
				}
			}
			// finds and creates hole
			for (int i = 0; i < running.size(); i++) {
				running.get(i).time--;
				if (running.get(i).time == 0) {// if current running process has finished
					done.add(running.get(i));
					Process empty = new Process(running.get(i).size, -1, "free");
					running.set(i, empty);// set open spot to "free"
					running.remove(i);

				}
			}
			// determines if there is a hole and if it is big enough for next allocated
			// memmory
			for (int i = 0; i < all.size(); i++) {
				// if the name of process is free and the size of free process is less than or
				// equal to all.get(i) process
				// add it to running and remove free process
				int best = 0;
				for (int j = 0; j < running.size(); j++) {

					if (running.get(j).name.equals("free") & all.get(i).size <= running.get(j).size) {
						// if()
						best = j;
						// now check if its the smallest process

						running.set(j, all.get(best));
					}
				}
			}
		}

	}

}

}
