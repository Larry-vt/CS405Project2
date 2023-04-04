
public class Process {
	int size;//how big it is 
	int time;// how long itll be in 
	String name; //p1 p2 p3 p4 p5 //free
	public Process(int size, int time, String name) {
		 this.size = size;
		 this.time = time;
		 this.name =name;
	}
	public String toString() {
		return name + "[" + time + "s]" + "(" + size + "KB) |";
		
	}
}
