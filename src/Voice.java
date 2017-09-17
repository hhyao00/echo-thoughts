import java.util.ArrayList;

/**
 * Voice class is responsible for what is said.
 * It is Runnable; our thread.
 */
class Voice implements Runnable{
    private String words;
    private double DELAY;
    private Listener listener;
    
    /** The default constructor */
    public Voice(){ }
    
    /** The constructor */
    public Voice(String s, double delay, Listener l){
	words = s;
	DELAY = delay;
	listener = l;
    }

    @Override
    public void run() {
	while(!Thread.interrupted()){
	    speak();
	    long when = System.currentTimeMillis();
	    when += (long)(Math.random() * DELAY);
	    while(System.currentTimeMillis() < when){
		Thread.yield();
	    }
	}
    }

    private void speak(){
	//System.out.println(words);
	listener.hear(words);
    }
    
}