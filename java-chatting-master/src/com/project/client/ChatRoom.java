package com.project.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import com.project.client.ChatRoom.ExButton;
import com.project.client.ChatRoom.MField;

public class ChatRoom extends JFrame{
	JLabel l;
	boolean typing;
	Timer t;
    //채팅방
    JTextField sendTF;
    JLabel la_msg;

    JTextArea ta;
    JScrollPane sp_ta,sp_list;

    JList<String> liUser;
    JButton bt_exit;
    private JLabel s1;

    JPanel p;
    public ChatRoom() {
    	
        setTitle("채팅방");
        setSize(450,700);
        sendTF = new MField("Type Your Message...");

        ta = new JTextArea();
        ta.setLineWrap(true); //TextArea 가로길이를 벗어나는 text 발생시 자동 줄바꿈 발생
        ta.setBorder(BorderFactory.createEmptyBorder());
        ta.setBackground(new Color(33,34,39));
        ta.setForeground(Color.WHITE);
        Font M = new Font("맑은 고딕", Font.PLAIN, 11);
        ta.setFont(M);


      
        
        t=new Timer(1,new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                // If the user isn't typing, he is thinking
                if(!typing)
                l.setText("생각 중...");
            }
        });
        
        // Set initial delay of 2000 ms
        // That means, actionPerformed() is executed 2500ms
        // after the start() is called
        t.setInitialDelay(2000);
        
        l=new JLabel();
        l.setForeground(Color.WHITE);  
        l.setBounds(10,570,290,35);
        add(l);
        
        sendTF.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent ke)
            {
            
                // Key pressed means, the user is typing
                l.setText("타이핑 중 입니다..");
                
                // When key is pressed, stop the timer
                // so that the user is not thinking, he is typing
                t.stop();
                
                // He is typing, the key is pressed
                typing=true;
                
                // If he presses enter, add text to chat textarea
                if(ke.getKeyCode()==KeyEvent.VK_ENTER) showLabel(sendTF.getText());
            }
            
            public void keyReleased(KeyEvent ke)
            {
            
                // When the user isn't typing..
                typing=false;
                
                // If the timer is not running, i.e.
                // when the user is not thinking..
                if(!t.isRunning())
                
                // He released a key, start the timer,
                // the timer is started after 2500ms, it sees
                // whether the user is still in the keyReleased state
                // which means, he is thinking
                t.start();
            }
        });
        
        
        liUser = new JList<String>();
        sp_ta = new JScrollPane(ta);
        sp_ta.setBorder(BorderFactory.createEmptyBorder());
        sp_list = new JScrollPane(liUser);
        liUser.setBackground(new Color(33,34,39));
        sp_list.setBackground(new Color(33,34,39));
        sp_ta.setBackground(new Color(33,34,39));
        sp_list.setBorder(BorderFactory.createEmptyBorder());
        
        
        s1=new JLabel("#대화참여자",JLabel.CENTER);
        Font labelFont2 = new Font("맑은 고딕", Font.BOLD, 13);
        s1.setFont(labelFont2);
        s1.setBounds(310, 10, 100, 30);
        s1.setForeground(Color.WHITE);
        add(s1);
 
        bt_exit = new ExButton("뒤로가기");
        
        p = new JPanel();
        sp_ta.setBounds(10,20,300,560);
        sendTF.setBounds(10,600,290,35);
        add(sendTF);
 
        sp_list.setBounds(320,40,95,540);
        bt_exit.setBounds(310,600,120,35);

        p.setLayout(null);
        p.setBackground(new Color(36,42,49));
        p.add(sp_ta);
        p.add(sp_list);
        p.add(bt_exit);
        add(p);
        


        //setVisible(true);
        sendTF.requestFocus();

    }//생성자
    
	public class ExButton extends JButton {
			
		    public ExButton() { super(); decorate(); } 
		    public ExButton(String text) { super(text); decorate(); } 
		    public ExButton(Action action) { super(action); decorate(); } 
		    public ExButton(Icon icon) { super(icon); decorate(); } 
		    public ExButton(String text, Icon icon) { super(text, icon); decorate(); } 
		    protected void decorate() { setBorderPainted(false); setOpaque(false); }
		    @Override 
		    protected void paintComponent(Graphics g) {
		       Color c=new Color(120,20,20); //배경색 결정
		       Color o=new Color(236,234,234); //글자색 결정
		       int width = getWidth(); 
		       int height = getHeight(); 
		       Graphics2D graphics = (Graphics2D) g; 
		       graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
		       if (getModel().isArmed()) { graphics.setColor(c.darker()); } 
		       else if (getModel().isRollover()) { graphics.setColor(c.brighter()); } 
		       else { graphics.setColor(c); } 
		       graphics.fillRoundRect(0, 0, width, height, 30, 30); 
		       FontMetrics fontMetrics = graphics.getFontMetrics(); 
		       Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds(); 
		       int textX = (width - stringBounds.width) / 2; 
		       int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent(); 
		       graphics.setColor(o); 
		       graphics.setFont(getFont()); 
		       graphics.drawString(getText(), textX, textY); 
		       graphics.dispose(); 
		       super.paintComponent(g); 
		       }
		    }
 
    public class MField extends JTextField { 

  	  Font gainFont = new Font("맑은 고딕", Font.BOLD, 11);  
  	  Font lostFont = new Font("맑은 고딕", Font.BOLD, 11);  
  	  
  	  public MField(final String hint) {  
  	    setText(hint);  
  	    setFont(lostFont);  
  	    setForeground(Color.GRAY);  
  	    setOpaque(false);
  	    setBorder(null);
  	    
  	    this.addFocusListener(new FocusAdapter() {  

  	      @Override  
  	      
  	      public void focusGained(FocusEvent e) {  
  	        if (getText().equals(hint)) {  
  	          setText("");  
  	          setFont(gainFont);  
  	          
  	        } else {  
  	          setText(getText());  
  	          setFont(gainFont);

  	        }  
  	      }  

  	      @Override  
  	      public void focusLost(FocusEvent e) {  
  	        if (getText().equals(hint)|| getText().length()==0) {  
  	          setText(hint);  
  	          setFont(lostFont);  
  	          setForeground(Color.GRAY);
  	          
  	        } else {  
  	          setText(getText());  
  	          setFont(gainFont);  
  	          setForeground(Color.GRAY);  
  	          
  	        }  
  	      }  
  	      
  	      
  	    });  
  	    
  	  }         
  	  
  	  private Shape shape;
      public MField(int size) {
          super(size);
          setOpaque(false);
          // As suggested by @AVD in comment.
      }
      protected void paintComponent(Graphics g) {
           g.setColor(getBackground());
           g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
          
           super.paintComponent(g);
      }
      
      protected void paintBorder(Graphics g) {
           g.setColor(getBackground());
           g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
        
      }
      public boolean contains(int x, int y) {
           if (shape == null || !shape.getBounds().equals(getBounds())) {
               shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 30, 30);
           }
           return shape.contains(x, y);
      }
  
  }

    private void showLabel(String text)
    {
        // If text is empty return
        if(text.trim().isEmpty()) return;
        
        // Otherwise, append text with a new line
        ta.append(text+"\n");
        
        // Set textfield and label text to empty string
        sendTF.setText("");
        l.setText("");
    }
    
}
