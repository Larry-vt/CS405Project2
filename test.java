
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		for(int i =0;i<20;i++) {
	int time =  (int)Math.floor(Math.random() * (10000 - 1 + 1) + 1);
	
	time = (int) (time*.001);
		System.out.println(time);
		}
	}

}
