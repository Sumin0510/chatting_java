package com.project.client;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.FocusAdapter;  
import java.awt.event.FocusEvent;  

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.geom.RoundRectangle2D;




public class Login extends JFrame{
    private Image back;
    private JLabel s1, s2, l1, l2;
    JTextField tf;       // default로 지정하여 ClientMainForm에서 이용
    JPasswordField pf , tpf;   // default로 지정하여 ClientMainForm에서 이용
    JButton b1,b2;      // default로 지정하여 ClientMainForm에서 이용
    JTextField textField;
    

    static JPanel title=new JPanel() { // 타이틀 이미지 추가
        Image image=new ImageIcon(Login.class.getResource("./image/logo.png")).getImage();
        public void paint(Graphics g) {//그리는 함수
            g.drawImage(image, 0, 0, null);//background를 그려줌
        }
    };
    
    
    
    public Login()
    {
    	setSize(450,700);
        setTitle("ʚ NETIX ❧ 로그인 ɞ");
        
        
        //이미지 정보 읽기
//        back=Toolkit.getDefaultToolkit().getImage("C:\\Users\\1466s\\IdeaProjects\\java_chatting_project\\image\\background.jpg");
        setLayout(null); //직접 배치
        
        title.setLayout(null); //레이아웃 설정하기 위해.
        title.setBounds(60, 80, 400, 600);//패널의 위치와 크기.
        add(title);//프레임에 패널을 추가
        //로그인 부분 아이디와 아이디 칠 textField
        
        s1=new JLabel("WELCOME!",JLabel.CENTER);
        Font labelFont = new Font("Arial", Font.BOLD, 12);
        s1.setFont(labelFont);
        s1.setBounds(170, 220, 80, 30);
        s1.setForeground(Color.WHITE);
        add(s1);
        
        s2=new JLabel("SIGN IN",JLabel.CENTER);
        Font labelFont2 = new Font("Arial", Font.BOLD, 22);
        s2.setFont(labelFont2);
        s2.setBounds(160, 230, 100, 50);
        s2.setForeground(Color.WHITE);
        add(s2);
     
  
        tf=new JIDField("  아이디 ID");
        Color bl=new Color(33,34,39);
        tf.setBackground(bl);
        tf.setBounds(100, 300, 230, 30);
        add(tf);

        //로그인 부분 비밀번호와 비밀번호 칠 textField
        pf=new JPasswordField("  비밀번호 PASSWORD");
        Color bl2=new Color(33,34,39);
        pf.setBackground(bl2);
        pf.setBounds(100, 345, 230, 30);
        add(pf);

        b1 = new RoundedButton("로그인");
        Font buttonFont = new Font("맑은 고딕", Font.BOLD, 13);
        b1.setFont(buttonFont);
        b1.setBounds(100, 390, 235, 35);
        
        add(b1);
        
        
        b2 = new RoundedButton("회원가입");
        Font buttonFont2 = new Font("맑은 고딕", Font.BOLD, 13);
        b2.setFont(buttonFont2);
        b2.setBounds(100, 550, 235, 35);
        add(b2);
       
        
        Color b=new Color(36,42,49);
        JPanel c=new JPanel();  // 이렇게 컬러값을 생성 후
        c.setBackground(b);
        c.setSize(2000, 2000);
        add(c);
    
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public class RoundedButton extends JButton {
    	
        public RoundedButton() { super(); decorate(); } 
        public RoundedButton(String text) { super(text); decorate(); } 
        public RoundedButton(Action action) { super(action); decorate(); } 
        public RoundedButton(Icon icon) { super(icon); decorate(); } 
        public RoundedButton(String text, Icon icon) { super(text, icon); decorate(); } 
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
    
    public class JPasswordField extends JTextField {  
    	

  	  Font gainFont = new Font("맑은 고딕", Font.PLAIN, 11);  
  	  Font lostFont = new Font("맑은 고딕", Font.BOLD, 11);  
  	
  	  
  	  public JPasswordField(final String hint) {  
  	    setText(hint);  
  	    setFont(lostFont);  
  	    setForeground(Color.WHITE);  
  	    setOpaque(false);

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
  	          setForeground(Color.WHITE);  
  	        } else {  
  	          setText(getText());  
  	          setFont(gainFont);  
  	          setForeground(Color.WHITE);  
  	          
  	        }  
  	      }  
  	      
  	      
  	    });  
  	    
  	  }         
  	  
  	  private Shape shape;
      public JPasswordField(int size) {
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

    public class JIDField extends JTextField { 

    	  Font gainFont = new Font("맑은 고딕", Font.PLAIN, 11);  
    	  Font lostFont = new Font("맑은 고딕", Font.BOLD, 11);  
    	  
    	  public JIDField(final String hint) {  
    	    setText(hint);  
    	    setFont(lostFont);  
    	    setForeground(Color.WHITE);  
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
    	          setForeground(Color.WHITE);
    	          
    	        } else {  
    	          setText(getText());  
    	          setFont(gainFont);  
    	          setForeground(Color.WHITE);  
    	          
    	        }  
    	      }  
    	      
    	      
    	    });  
    	    
    	  }         
    	  
    	  private Shape shape;
        public JIDField(int size) {
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

    public boolean loginCheck(String userId, String userPassword) {
        // DB 정보 Config 클래스로 부터 받아옴
        String url = "jdbc:mysql://" + Config.getHost() + ":" + Config.getPort()
                + "/" + Config.getName() + "?useUnicode=true&characterEncoding=utf8";
        String uid = Config.getUser();
        String upw = Config.getPwd();

        boolean flag = false;

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        String sql = "SELECT user_password FROM user WHERE user_id=?";
        String getPass = null;

        try{
            // 쿼리 수행
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, uid, upw);
            pst = con.prepareStatement(sql);
            pst.setString(1, userId);
            rst = pst.executeQuery();

            // 쿼리 결과값과 입력 pwd 값 비교
            if (rst.next()) {
                getPass = rst.getString("user_password"); // 쿼리 결과값
                if (getPass.equals(userPassword)) {
                    flag = true;
                } else {
                JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
                System.out.println("비밀번호 불일치"); }
            }
            else {
                JOptionPane.showMessageDialog(null, "아이디가 존재하지 않습니다.");
                System.out.print("아이디 미존재");
            };

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            // 쿼리 실행 후 모두 종료
            try {
                if (rst != null) {
                    rst.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return flag;
    }


}