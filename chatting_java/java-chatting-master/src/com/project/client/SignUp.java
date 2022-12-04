package com.project.client;

import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.*;
import java.sql.*;
import java.util.regex.*;

import com.project.client.Login.JIDField;
import com.project.client.Login.JPasswordField;
import com.project.common.User;

public class SignUp extends JFrame implements ActionListener{
    JTextField tf1, tf2, tf3, tf4;
    JLabel s1, s2, l0, l1, l2, l3, l4;
    JButton b1, b2;
    //Frame은 전역변수에 있어야함

    static JPanel title=new JPanel() { // 타이틀 이미지 추가
        Image image=new ImageIcon(Login.class.getResource("./image/logo.png")).getImage();
        public void paint(Graphics g) {//그리는 함수
            g.drawImage(image, 0, 0, null);//background를 그려줌
        }
    };


    public SignUp(){
    	setSize(450,700);
        setTitle("ʚ NETIX ❧ 회원가입 ɞ");
        setBounds(0, 0, 450, 700);
        setLayout(null); //직접 배치
        title.setLayout(null);
        title.setBounds(60, 80, 400, 600);
        add(title);
    
        
        s1=new JLabel("WELCOME!",JLabel.CENTER);
        Font labelFont = new Font("Arial", Font.BOLD, 12);
        s1.setFont(labelFont);
        s1.setBounds(170, 220, 80, 30);
        s1.setForeground(Color.WHITE);
        add(s1);
        
        s2=new JLabel("SIGN UP",JLabel.CENTER);
        Font labelFont2 = new Font("Arial", Font.BOLD, 22);
        s2.setFont(labelFont2);
        s2.setBounds(160, 230, 100, 50);
        s2.setForeground(Color.WHITE);
        add(s2);
     

        tf1=new JIDField("  아이디 ID");
        Color bl=new Color(33,34,39);
        tf1.setBackground(bl);
        tf1.setBounds(100, 300, 230, 30);
        
        
        add(tf1);

        //로그인 부분 비밀번호와 비밀번호 칠 textField
        tf2 = new PasswordField("  비밀번호 PASSWORD");
        Color bl2=new Color(33,34,39);
        tf2.setBackground(bl2);
        tf2.setBounds(100, 340, 230, 30);
        add(tf2);
       

        l3 = new JLabel("비밀번호는 영문과 특수문자, 숫자를 포함하며 8자 이상이어야 합니다.");
        l3.setForeground(new Color(204, 61, 61));
        l3.setBounds(40, 370, 500, 30);
        add(l3);

        tf3 = new JIDField("  닉네임");
        Color bla=new Color(33,34,39);
        tf3.setBackground(bla);
        tf3.setBounds(100, 400 , 230, 30);
        add(tf3);

        b1 = new RoundedButton("확인");
        b2 = new RoundedButton("취소");
        JPanel p = new JPanel(); //패널을 배치하는 이유, 가운데 맞추기 어려워서 패널로 잡아준다
        p.add(b1);
        p.add(b2);
        p.setOpaque(false); // setOpaque -투명모드
        p.setBounds(95, 440, 235, 35);
        add(p);
        b1.addActionListener(this);
        b2.addActionListener(this);
        //add액션리스너(this): 이 클래스에서 정한 행동 취한다.
        
        Color b=new Color(36,42,49);
        JPanel c=new JPanel();  // 이렇게 컬러값을 생성 후
        c.setBackground(b);
        c.setSize(2000, 2000);
        add(c);
    
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(b1)){
            //TF에서 얻은 값으로 빈 클래스를 set한다.
            User user = new User();
            user.setUserId(tf1.getText());
            user.setUserPassword(tf2.getText());
            user.setUserName(tf3.getText());

            if(checkPassword(user.getUserPassword())){
                insertUser(user);
            } else {
                JOptionPane.showMessageDialog(null, "비밀번호 형식이 일치하지 않습니다.", "비밀번호 오류", JOptionPane.ERROR_MESSAGE);
            }

        }else if(e.getSource().equals(b2)){
            //프레임 창 끄기
            dispose();

        }
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
           Font buttonFont = new Font("맑은 고딕", Font.BOLD, 13);
           setFont(buttonFont);
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
    
    public class PasswordField extends JTextField {  
    	

  	  Font gainFont = new Font("맑은 고딕", Font.PLAIN, 11);  
  	  Font lostFont = new Font("맑은 고딕", Font.BOLD, 11);  
  	
  	  
  	  public PasswordField(final String hint) {  
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
      public PasswordField(int size) {
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

    
    private boolean checkPassword(String password) {

        // 비밀번호 포맷 확인(영문, 특수문자, 숫자 포함 8자 이상)
        Pattern passPattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
        Matcher passMatcher = passPattern.matcher(password);

        if (!passMatcher.find()) {
            return false;
        }
        else {return true;}
    }

    static void insertUser(User user){
        // DB 정보 Config 클래스로 부터 받아옴
        String url = "jdbc:mysql://" + Config.getHost() + ":" + Config.getPort()
                + "/" + Config.getName() + "?useUnicode=true&characterEncoding=utf8";
        String uid = Config.getUser();
        String upw = Config.getPwd();

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        String sql1 = "SELECT * FROM user WHERE user_id=?";
        String sql2 = "INSERT INTO user(user_id, user_password, user_name) VALUES(?, ?, ?)";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, uid, upw);
            String userId = user.getUserId();
            String userPassword = user.getUserPassword();
            String userName = user.getUserName();

            pst = con.prepareStatement(sql1);
            pst.setString(1, userId);
            rst = pst.executeQuery();
            if(rst.next()){ // 동일 아이디 존재
                JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다.", "아이디 오류", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    pst = con.prepareStatement(sql2);
                    pst.setString(1, userId); // 쿼리의 첫 번째 '?'에 들어갈 값
                    pst.setString(2, userPassword); // 쿼리의 두 번째 '?'에 들어갈 값
                    pst.setString(3, userName); // 쿼리의 세 번째 '?'에 들어갈 값
                    pst.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new SignUp();
    }

}