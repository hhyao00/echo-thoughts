import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Sounds {

    /** Delay between the voices */
    private static final double DELAY = 7000;

    /** All thoughts read in from file */
    private String[] thoughts;
    
    /** The voices */
    private ArrayList<Thread> voices;
    
    /** The listener to sounds */
    private Listener listener;
    
    
    // -- end of fields -- // 
    
    /** The constructor 
     * @throws IOException */
    public Sounds() throws IOException{
	voices = new ArrayList<Thread>();
	readIn();
    }
    
    /** Start the thoughts */
    public void start(){
	for(int i = 0 ; i < thoughts.length ; i++ ){
	    say(thoughts[i]);
	}
    }
    
    /** Stop the thoughts */
    public void stop(){
	for(Thread t: voices){
	    t.interrupt();
	}
    }

    /** What the voice says. */
    public void say(String words){
	Thread voice = new Thread(new Voice(words, DELAY, listener));
	voices.add(voice);
	voice.start();
    }
    
    /** Read in the file and add to array */
    private void readIn() throws IOException{
	String line = "";
	ArrayList<String> temp = new ArrayList<String>();
	
	Scanner scanner = new Scanner(new File("thoughts")).useDelimiter("\\n");
	
	while( scanner.hasNext() ){
	    line = scanner.next();
	    temp.add(line);
	}
	scanner.close();
	
	thoughts = new String[temp.size()];
	for(int i = 0; i < temp.size(); i++ ){
	    thoughts[i] = temp.get(i);
	}
    }
    
    public void addListener(Listener l){
	listener = l;
    }

}
