package com.project.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;



public class WaitRoom extends JFrame{
    JList<String> roomInfo, roomUser, waitInfo;
    JScrollPane sp_roomInfo, sp_roomUser, sp_waitInfo;
    JButton bt_create, bt_enter, bt_exit;
    private JLabel s1, s2, s3;

    JPanel p;

    public WaitRoom() {
        setTitle("IT TALK");

        roomInfo = new JList<String>();
        roomInfo.setBackground(new Color(33,34,39));
     
        
        s1=new JLabel("#채팅방 정보",JLabel.CENTER);
        Font labelFont = new Font("맑은 고딕", Font.BOLD, 13);
        s1.setFont(labelFont);
        s1.setBounds(10, 10, 100, 30);
        s1.setForeground(Color.WHITE);
        add(s1);
        
        roomUser = new JList<String>();
        roomUser.setBackground(new Color(33,34,39));
        
        s2=new JLabel("#인원정보",JLabel.CENTER);
        Font labelFont2 = new Font("맑은 고딕", Font.BOLD, 13);
        s2.setFont(labelFont2);
        s2.setBounds(290, 10, 100, 30);
        s2.setForeground(Color.WHITE);
        add(s2);
        
        waitInfo = new JList<String>();
        waitInfo.setBackground(new Color(255,255,255));

        s3=new JLabel("#대기실 정보",JLabel.CENTER);
        Font labelFont3 = new Font("맑은 고딕", Font.BOLD, 13);
        s3.setFont(labelFont3);
        s3.setBounds(10, 517, 100, 30);
        s3.setForeground(Color.WHITE);
        add(s3);
        
        sp_roomInfo = new JScrollPane(roomInfo);
        sp_roomInfo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        sp_roomInfo.getViewport().setBackground(new Color(33,34,39));

        sp_roomUser = new JScrollPane(roomUser);
        sp_roomUser.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        sp_roomUser.getViewport().setBackground(new Color(33,34,39));
        
        sp_waitInfo = new JScrollPane(waitInfo);
        sp_waitInfo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        sp_waitInfo.getViewport().setBackground(new Color(243,248,249));



        bt_create = new BLButton("방 만들기");
        bt_enter = new BLButton("입장하기");
        bt_exit = new BLButton("나가기");


        p = new JPanel();

        sp_roomInfo.setBounds(20,40,290,480);
        sp_roomUser.setBounds(310,40, 100, 480);
        sp_waitInfo.setBounds(20, 545, 250, 95);

        bt_create.setBounds(290, 530, 130, 30);
        bt_enter.setBounds(290, 570, 130, 30);
        bt_exit.setBounds(290, 610, 130, 30);

        p.setLayout(null);
        p.add(sp_roomInfo);
        p.add(sp_roomUser);
        p.add(sp_waitInfo);
        p.add(bt_create);
        p.add(bt_enter);
        p.add(bt_exit);
        p.setBackground(new Color(36,42,49));

        add(p);
        
        setSize(450,700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

 public class BLButton extends JButton {
    	
        public BLButton() { super(); decorate(); } 
        public BLButton(String text) { super(text); decorate(); } 
        public BLButton(Action action) { super(action); decorate(); } 
        public BLButton(Icon icon) { super(icon); decorate(); } 
        public BLButton(String text, Icon icon) { super(text, icon); decorate(); } 
        protected void decorate() { setBorderPainted(false); setOpaque(false); }
        @Override 
        protected void paintComponent(Graphics g) {
           Color c=new Color(120,20,20); //배경색 결정
           Color o=new Color(255,255,255); //글자색 결정
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
}
