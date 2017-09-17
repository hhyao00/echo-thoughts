//import java.awt.AlphaComposite;
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Image;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//import javax.imageio.ImageIO;
//import javax.swing.Icon;
//import javax.swing.ImageIcon;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;
//
///** Class for the background */
//class BkPanel extends JPanel{
//
//    private Image img;
//    private TextPanel textPanel;
//
//    public BkPanel(String img){
//	this(new ImageIcon(img).getImage());
//    }
//
//    public BkPanel(Image img){
//	this.img = img;
//	Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
//	setPreferredSize(size);
//	setSize(size);
//	setLayout(null);
//
//	setLayout(new BorderLayout());
//	setBackground(Color.WHITE);
//
//	textPanel = new TextPanel();
////	JScrollPane scrollPane = new JScrollPane(textPanel);
////	scrollPane.getViewport().setOpaque(false);
////	scrollPane.setOpaque(false);
//	this.add(textPanel);
//	//	this.add(scrollPane);
//	//	add(scrollPane);
//
////	File imageFile = new File("flowers1.jpg");
////	BufferedImage backgroundImg;
////	try {
////	    backgroundImg = ImageIO.read(imageFile);
////	    Icon backgroundIcon = new ImageIcon(backgroundImg);
////	    JLabel contentLabel = new JLabel(backgroundIcon);
////	    contentLabel.setLayout(new BorderLayout());
////	    //contentLabel.add(scrollPane, BorderLayout.CENTER);
////	    this.add(contentLabel);
////	} catch (IOException e) {
////	    // TODO Auto-generated catch block
////	    e.printStackTrace();
////	}
//    }
//
//
//    public void paintComponent(Graphics g){
//	Graphics2D g2 = (Graphics2D) g;
//
//	float alpha = 0.6f; //draw half transparent
//	AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha);
//	g2.setComposite(ac);
//	g.drawImage(img, 0, 0, null);
//
//    }
//
//    public void addSounds(Sounds s){
//	textPanel.addSounds(s);
//    }
//
//    public void clearText(){
//	textPanel.clear();
//    }
//
////    /** Inner class textPanel */
////    class TextPanel extends JPanel implements Listener {
////
////	private Sounds sounds;
////	private JTextArea textArea;
////
////	public TextPanel(){
////
////	    textArea = new JTextArea();
////	    textArea.setOpaque(false);
////	    //textArea.setBackground(new Color(0, 0, 0, 0));
////	    textArea.setLineWrap(true);
////	    textArea.setWrapStyleWord(true);
////
////	    this.add(textArea);
////	    setVisible(true);
////	}
////
////	public void addSounds(Sounds s){
////	    sounds = s;
////	    sounds.addListener(this);
////	}
////
////	@Override
////	public synchronized void hear(String words) {
////	    textArea.append(words + "\n");
////	    this.getParent().repaint();
////	}
////
////	public void clear(){
////	    textArea.setText("");
////	    this.getParent().repaint();
////	}
////
////    }
//
//}