package lolpang3;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class Timebar extends Thread{
	private Image Timebar= new ImageIcon(Main.class.getResource("../Images/TimeBar.jpg")).getImage();
	public boolean gamestart = false;
	public boolean RankingScreen=false;
	
	EffectMusic gamebgm = new EffectMusic("1.wav");
	EffectMusic endMusic = new EffectMusic("∞‘¿”≥°.wav");
	int time_x;
	int time = 70;
	int Timecnt; 
	public Timebar(){
		Timer m_timer= new Timer();
		TimerTask m_task = new TimerTask(){	
			public void run(){
				if(gamestart==true){	
				time--;
				time_x=590 *time/70;
			if(time==0){
				endMusic.startPlay();
				RankingScreen=true;
			}	
		}}
		};m_timer.schedule(m_task,0,1000);
	}

	
	public void screenDraw(Graphics2D g){
		 g.drawImage(Timebar,24,808,time_x,41,null);
		 g.setColor(Color.white);
		 g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		 g.setFont(new Font("Arial",Font.BOLD,30));
		 g.drawString(""+time,300,840);
	}
}