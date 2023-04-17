import java.util.ArrayList;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		for(int i =0;i<20;i++) {
	int time =  (int)Math.floor(Math.random() * (10000 - 1 + 1) + 1);
	ArrayList<Integer> all = new ArrayList<Integer>();//every process that'll run in order
	all.add(1);
	all.remove(0);
	time = (int) (time*.001);
		System.out.println(all.isEmpty());
		}
	}

}
