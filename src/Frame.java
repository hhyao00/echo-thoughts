import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Frame extends JFrame{

    private Container contentPane;
    private Sounds sounds;

    //private BkPanel imgPanel;
    private TextPanel textPanel;
    private BufferedImage backgroundImg;

    public Frame(){
	super("Thoughts");
	
	//imgPanel = new BkPanel(new ImageIcon("flowers1.jpg").getImage());
	try{
	    sounds = new Sounds();
	    //imgPanel.addSounds(sounds);
	    // the thoughts have not yet started,
	    // textPanel is added as listener,
	    // thoughts are started with a button. start
	} catch (IOException e){
	    JOptionPane.showMessageDialog(null, "Empty");
	}

	setBackground();
	setUp();

	setBackground(Color.WHITE);
	setResizable(false);

	validate();
	repaint();
	pack();
	setVisible(true);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /** For the image in the background */
    private void setBackground(){

	File imageFile = new File("flowers1.jpg");
	try {
	    backgroundImg = ImageIO.read(imageFile);
	    Icon backgroundIcon = new ImageIcon(backgroundImg);
	    JLabel contentLabel = new JLabel(backgroundIcon);
	    contentLabel.setLayout(new BorderLayout());

	    textPanel = new TextPanel();
	    textPanel.setOpaque(false);
	    JScrollPane scrollPane = new JScrollPane(textPanel);
	    scrollPane.getViewport().setOpaque(false);
	    scrollPane.setOpaque(false);

	    contentLabel.add(scrollPane, BorderLayout.CENTER);
	    setContentPane(contentLabel);
	    contentPane = getContentPane();

	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    /** Set up the frame */
    private void setUp(){
//
//	textPanel = new TextPanel();
//	JScrollPane scrollPane = new JScrollPane(textPanel);
//	scrollPane.getViewport().setOpaque(false);
//	scrollPane.setOpaque(false);
//	contentPane.add(scrollPane, BorderLayout.CENTER);

	// ---- buttons ---- //

	JPanel westPanel = new JPanel();
	westPanel.setLayout(new GridLayout(0,1));
	contentPane.add(westPanel, BorderLayout.WEST);

	JButton start = new JButton("Start");
	start.addActionListener(new ActionListener(){
	    @Override
	    public void actionPerformed(ActionEvent e) {
		sounds.start();
	    }
	});
	westPanel.add(start);

	JButton stop = new JButton("Stop");
	stop.addActionListener(new ActionListener(){
	    public void actionPerformed(ActionEvent e){
		sounds.stop();
	    }
	});
	westPanel.add(stop);

	JButton clear = new JButton("Clear");
	clear.addActionListener(new ActionListener(){
	    public void actionPerformed(ActionEvent e){
		textPanel.clear();
	    }
	});
	westPanel.add(clear);

	// --- textField and button --- //

	JPanel textPanel = new JPanel();
	textPanel.setLayout(new FlowLayout());

	JTextField inputField = new JTextField(30);
	JButton enter = new JButton("Enter");

	Action enterAction = new AbstractAction(){
	    public void actionPerformed(ActionEvent e){
		String words = inputField.getText();
		if(words.trim() == ""){ return; }
		sounds.say(words);
		inputField.setText("");
	    }
	};

	inputField.addActionListener( enterAction );
	enter.addActionListener( enterAction );

	textPanel.add(inputField);
	textPanel.add(enter);
	contentPane.add(textPanel, BorderLayout.SOUTH);
    }

    /** Inner class textPanel */
    class TextPanel extends JPanel implements Listener {

	//private Sounds sounds;
	private JTextArea textArea;

	public TextPanel(){

	    textArea = new JTextArea();
	    textArea.setOpaque(false);
	    textArea.setBackground(new Color(0, 0, 0, 0));
	    textArea.setLineWrap(true);
	    textArea.setWrapStyleWord(true);

	    sounds.addListener(this);
	    this.add(textArea);
	}

	//	public void addSounds(Sounds s){
	//	    sounds = s;
	//	    sounds.addListener(this);
	//	}

	@Override
	public synchronized void hear(String words) {
	    textArea.append(words + "\n");
	}

	public void clear(){
	    textArea.setText("");
	}

    }

}
