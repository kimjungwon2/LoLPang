package lolpang3;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;

public class GameScreen extends Thread {

	private Image backgroundImage_size= new ImageIcon(Window.class.getResource("../Images/backgroundImage_size.jpg")).getImage();
	private Image lolback= new ImageIcon(Window.class.getResource("../Images/lolbackground.png")).getImage();
	private Image score= new ImageIcon(Window.class.getResource("../Images/score.png")).getImage();
	private Image teemo= new ImageIcon(Window.class.getResource("../Images/티모.png")).getImage();
	private Image mbar= new ImageIcon(Window.class.getResource("../Images/mbar.png")).getImage();
	private Image gaze= new ImageIcon(Window.class.getResource("../Images/게이지.png")).getImage();
	
	private ImageIcon Character1= new ImageIcon(Window.class.getResource("../Images/character1.png"));
	private ImageIcon Character2= new ImageIcon(Window.class.getResource("../Images/character2.png"));
	private ImageIcon Character3= new ImageIcon(Window.class.getResource("../Images/character3.png"));
	private ImageIcon Character4= new ImageIcon(Window.class.getResource("../Images/character4.png"));
	private ImageIcon Character5= new ImageIcon(Window.class.getResource("../Images/character5.png"));
	private ImageIcon Character6= new ImageIcon(Window.class.getResource("../Images/character6.png"));
	private ImageIcon Character7= new ImageIcon(Window.class.getResource("../Images/character7.png"));
	private ImageIcon DeleteImage= new ImageIcon(Window.class.getResource("../Images/blank.png"));
	private ImageIcon BoomImage= new ImageIcon(Window.class.getResource("../Images/티모.png"));
	
	int mouseX,mouseY;
	
	ArrayList<ImageIcon> character;//ĳ���� �迭����
	
	public boolean isDragged=false;
	public boolean isLeftmove1=false;
	public boolean isLeftmove2=false;
	public boolean isRightmove1=false;
	public boolean isRightmove2=false;
	public boolean isUpmove1=false;
	public boolean isUpmove2=false;
	public boolean isDownmove1=false;
	public boolean isDownmove2=false;
	public boolean isGame=true;
	public boolean blank=false;
	public boolean create=false;
	public boolean fcheck=false;
	public boolean ispress=false;
	public boolean isboom=false;
	int n_score = 0;
	int mx,my,mx1,my1;//���콺 ��ǥ
	int i,j;
	
	int sizeX,sizeY;

	int boom=0;
	int x;
	int y;// ��ǥ
	
	int tx,ty;
	int boomnum=0;
	int pointX =38;
	int pointY= 245;// ĳ���� ��ġ
	
	int charSizeX = 80;
	int charSizeY= 80;//ĳ���� ������

	int randchar[][]=new int[7][7];
	
	
	EffectMusic moveMusic = new EffectMusic("move.wav");
	EffectMusic pangsound = new EffectMusic("pangsound.wav");
	EffectMusic boomlaugh = new EffectMusic("boomlaugh.wav");
	EffectMusic newch = new EffectMusic("change.wav");
	
	GameScreen(){
			character = new ArrayList<ImageIcon>();{//8��
				character.add(Character1);//0
				character.add(Character2);//1
				character.add(Character3);//2
				character.add(Character4);//3
				character.add(Character5);//4
				character.add(Character6);//5
				character.add(Character7);//6
				character.add(DeleteImage);//7(���� �̹���)
				character.add(BoomImage);//8(��ź �̹���)
			}
			newchar();	
	}
	
	public void newchar(){
		
		for(x=0;x<=6;x++)// 7���� ���� ĳ���� ���� (ó������ �������Բ�)
			for(y=0;y<=6;y++){
		{
				do{
					randchar[x][y] = (int)(Math.random()*7);
				}while(((x>=2)&&(randchar[x-1][y]==randchar[x][y])&&(randchar[x][y]==randchar[x-2][y]))||
				((y>=2)&&(randchar[x][y-1]==randchar[x][y])&&(randchar[x][y]==randchar[x][y-2])));
			}
		}
	}
	
	//�Ʒ��� �̵�
	public void downmoves(int x,int y){
		moveMusic.startPlay();
		if(y<=5){
		int temp;
		temp=randchar[x][y];
		randchar[x][y]=randchar[x][y+1];
		randchar[x][y+1]=temp;
		}
}
	
	public void returndown(int x,int y){
		Timer rl =new Timer();
		TimerTask rs =new TimerTask(){
			public void run(){
				if(y<=6&&(randchar[x][y+1]!=7)&&(randchar[x][y]!=7)){
					int temp;
					temp=randchar[x][y+1];
					randchar[x][y+1]=randchar[x][y];
					randchar[x][y]=temp;
				}
			}
		};
		rl.schedule(rs,500);
	}

	public void confirmDown(int x,int y){
		
		if((y<=3)&&(randchar[x][y+1]==randchar[x][y+2])&&(randchar[x][y+1]==randchar[x][y+3])){
			isDownmove1=true;
		}
		else if((x<=5)&&(x>=1)&&(y<=5)&&(randchar[x][y+1]==randchar[x+1][y+1])&&(randchar[x][y+1]==randchar[x-1][y+1])){
			isDownmove1=true;
		}
		
		else if((y<=5)&&(x>=2)&&(randchar[x][y+1]==randchar[x-1][y+1])&&(randchar[x][y+1]==randchar[x-2][y+1])){
			isDownmove1=true;
		}
		
		else if((x<=4)&&(y<=5)&&(randchar[x][y+1]==randchar[x+1][y+1])&&(randchar[x][y+1]==randchar[x+2][y+1])){
			isDownmove1=true;
		}
		
		if((y>=2)&&(randchar[x][y]==randchar[x][y-1])&&(randchar[x][y]==randchar[x][y-2])){
			isDownmove2=true;
		}
		
		else if((x<=4)&&(randchar[x][y]==randchar[x+1][y])&&(randchar[x][y]==randchar[x+2][y])){
			isDownmove2=true;
		}
		
		else if((x>=2)&&(randchar[x][y]==randchar[x-1][y])&&(randchar[x][y]==randchar[x-2][y])){
			isDownmove2=true;
		}
		
		else if((x>=1)&&(x<=5)&&(randchar[x][y]==randchar[x-1][y])&&(randchar[x][y]==randchar[x+1][y])){
			isDownmove2=true;
		}
}
	
	
	public void Downmove1(int x, int y){
		if(randchar[x][y]!=7){
		if((x>=2)&&(x<=4)&&(y<=3)&&(randchar[x][y+1]==randchar[x-2][y+1])&&(randchar[x][y+1]==randchar[x-1][y+1])
			&&(randchar[x][y+1]==randchar[x+1][y+1])&&(randchar[x][y+1]==randchar[x+2][y+1])&&
			(randchar[x][y+1]==randchar[x][y+2])&&(randchar[x][y+1]==randchar[x][y+3])){
				n_score+=2000;boom+=7;
				randchar[x][y+1]=7;randchar[x-2][y+1]=7;randchar[x-1][y+1]=7;randchar[x+1][y+1]=7;
				randchar[x+2][y+1]=7;randchar[x][y+2]=7;randchar[x][y+3]=7;}
		
		else if((x>=2)&&(x<=5)&&(y<=3)&&(randchar[x][y+1]==randchar[x-2][y+1])&&(randchar[x][y+1]==randchar[x-1][y+1])
			&&(randchar[x][y+1]==randchar[x+1][y+1])&&
			(randchar[x][y+1]==randchar[x][y+2])&&(randchar[x][y+1]==randchar[x][y+3])){
				n_score+=1500;boom+=6;
				randchar[x][y+1]=7;randchar[x-2][y+1]=7;randchar[x-1][y+1]=7;randchar[x+1][y+1]=7;
				randchar[x][y+2]=7;randchar[x][y+3]=7;}
		
		else if((x>=1)&&(x<=4)&&(y<=3)&&(randchar[x][y+1]==randchar[x-1][y+1])
			&&(randchar[x][y+1]==randchar[x+1][y+1])&&(randchar[x][y+1]==randchar[x+2][y+1])&&
			(randchar[x][y+1]==randchar[x][y+2])&&(randchar[x][y+1]==randchar[x][y+3])){
				n_score+=1500;boom+=6;
				randchar[x][y+1]=7;randchar[x-1][y+1]=7;randchar[x+1][y+1]=7;
				randchar[x+2][y+1]=7;randchar[x][y+2]=7;randchar[x][y+3]=7;}
		
		else if((x>=1)&&(x<=5)&&(y<=3)&&(randchar[x][y+1]==randchar[x-1][y+1])
			&&(randchar[x][y+1]==randchar[x+1][y+1])&&
			(randchar[x][y+1]==randchar[x][y+2])&&(randchar[x][y+1]==randchar[x][y+3])){
				n_score+=1000;boom+=5;
				randchar[x][y+1]=7;randchar[x-1][y+1]=7;randchar[x+1][y+1]=7;
				randchar[x][y+2]=7;randchar[x][y+3]=7;}
		
		else if((x>=2)&&(y<=3)&&(randchar[x][y+1]==randchar[x-2][y+1])&&(randchar[x][y+1]==randchar[x-1][y+1])
			&&(randchar[x][y+1]==randchar[x][y+2])&&(randchar[x][y+1]==randchar[x][y+3])){
				n_score+=1000;boom+=5;
				randchar[x][y+1]=7;randchar[x-2][y+1]=7;randchar[x-1][y+1]=7;
				randchar[x][y+2]=7;randchar[x][y+3]=7;}
		
		else if((x>=2)&&(x<=4)&&(y<=5)&&(randchar[x][y+1]==randchar[x-2][y+1])&&(randchar[x][y+1]==randchar[x-1][y+1])&&
				(randchar[x][y+1]==randchar[x+1][y+1])&&(randchar[x][y+1]==randchar[x+2][y+1])){
					n_score+=1000;boom+=5;
					randchar[x][y+1]=7;randchar[x+1][y+1]=7;randchar[x-2][y+1]=7;randchar[x-1][y+1]=7;
					randchar[x+2][y+1]=7;}
		
		else if((x<=4)&&(y<=3)&&(randchar[x][y+1]==randchar[x+1][y+1])&&(randchar[x][y+1]==randchar[x+2][y+1])&&
			(randchar[x][y+1]==randchar[x][y+2])&&(randchar[x][y+1]==randchar[x][y+3])){
				n_score+=1000;boom+=5;
				randchar[x][y+1]=7;randchar[x+1][y+1]=7;
				randchar[x+2][y+1]=7;randchar[x][y+2]=7;randchar[x][y+3]=7;}
		
		else if((x>=2)&&(x<=5)&&(y<=5)&&(randchar[x][y+1]==randchar[x-2][y+1])&&(randchar[x][y+1]==randchar[x-1][y+1])
			&&(randchar[x][y+1]==randchar[x+1][y+1])){
				n_score+=750;boom+=4;
				randchar[x][y+1]=7;randchar[x-2][y+1]=7;randchar[x-1][y+1]=7;randchar[x+1][y+1]=7;}
		
		else if((x>=1)&&(x<=4)&&(y<=5)&&(randchar[x][y+1]==randchar[x-1][y+1])
			&&(randchar[x][y+1]==randchar[x+1][y+1])&&(randchar[x][y+1]==randchar[x+2][y+1])){
				n_score+=750;boom+=4;
				randchar[x][y+1]=7;randchar[x+2][y+1]=7;randchar[x-1][y+1]=7;randchar[x+1][y+1]=7;}
		
		else if((x>=1)&&(x<=5)&&(y<=5)&&(randchar[x][y+1]==randchar[x-1][y+1])
			&&(randchar[x][y+1]==randchar[x+1][y+1])){
				n_score+=500;boom+=3;
				randchar[x][y+1]=7;randchar[x-1][y+1]=7;randchar[x+1][y+1]=7;}
		
		else if((x>=2)&&(y<=5)&&(randchar[x][y+1]==randchar[x-1][y+1])
			&&(randchar[x][y+1]==randchar[x-2][y+1])){
				n_score+=500;boom+=3;
				randchar[x][y+1]=7;randchar[x-2][y+1]=7;randchar[x-1][y+1]=7;}

		else if((y<=3)&&(randchar[x][y+1]==randchar[x][y+2])
			&&(randchar[x][y+1]==randchar[x][y+3])){
				n_score+=500;boom+=3;
				randchar[x][y+1]=7;randchar[x][y+2]=7;randchar[x][y+3]=7;}
		
		else if((x<=4)&&(y<=5)&&(randchar[x][y+1]==randchar[x+1][y+1])&&(randchar[x][y+1]==randchar[x+2][y+1])){
				n_score+=500;boom+=3;
				randchar[x][y+1]=7;randchar[x+1][y+1]=7;randchar[x+2][y+1]=7;}
		}
		isDownmove1=false;
	}
	
	
	public void Downmove2(int x, int y){
		
		if(randchar[x][y]!=7){
		if((y>=2)&&(x>=2)&&(x<=4)&&(randchar[x][y]==randchar[x-1][y])&&(randchar[x][y]==randchar[x-2][y])
			&&(randchar[x][y]==randchar[x+1][y])&&(randchar[x][y]==randchar[x+2][y])&&
			(randchar[x][y]==randchar[x][y-1])&&(randchar[x][y]==randchar[x][y-2])){
		n_score+=2000;boom+=7;
		randchar[x][y]=7;randchar[x-1][y]=7;randchar[x][y-1]=7;randchar[x+1][y]=7;
		randchar[x][y-2]=7;randchar[x-2][y]=7;randchar[x+2][y]=7;}

		else if((y>=2)&&(x>=2)&&(x<=5)&&(randchar[x][y]==randchar[x-1][y])&&(randchar[x][y]==randchar[x-2][y])
			&&(randchar[x][y]==randchar[x+1][y])&&(randchar[x][y]==randchar[x][y-2])&&
			(randchar[x][y]==randchar[x][y-1])){
		n_score+=1500;boom+=6;
		randchar[x][y]=7;randchar[x-1][y]=7;randchar[x][y-1]=7;randchar[x+1][y]=7;
		randchar[x-2][y]=7;randchar[x][y-2]=7;}

		else if((y>=2)&&(x>=1)&&(x<=4)&&(randchar[x][y]==randchar[x-1][y])&&(randchar[x][y]==randchar[x+2][y])
			&&(randchar[x][y]==randchar[x+1][y])&&(randchar[x][y]==randchar[x][y-1])
			&&(randchar[x][y]==randchar[x][y-2])){
		n_score+=1500;boom+=6;
		randchar[x][y]=7;randchar[x-1][y]=7;randchar[x][y-1]=7;randchar[x][y-2]=7;
		randchar[x+2][y]=7;randchar[x+1][y]=7;}
                
		else if((x>=1)&&(y>=2)&&(x<=5)&&(randchar[x][y]==randchar[x-1][y])&&(randchar[x][y]==randchar[x+1][y])
		&&(randchar[x][y]==randchar[x][y-1])&&(randchar[x][y]==randchar[x][y-2])){
		n_score+=1000;boom+=5;
		randchar[x][y]=7;randchar[x-1][y]=7;randchar[x][y-1]=7;
		randchar[x+1][y]=7;randchar[x][y-2]=7;}
		
		else if((y>=2)&&(x>=2)&&(randchar[x][y]==randchar[x-1][y])&&(randchar[x][y]==randchar[x-2][y])
			&&(randchar[x][y]==randchar[x][y-1])&&(randchar[x][y]==randchar[x][y-2])){
		n_score+=1000;boom+=5;
		randchar[x][y]=7;randchar[x-1][y]=7;randchar[x][y-1]=7;
		randchar[x-2][y]=7;randchar[x][y-2]=7;}
		
		else if((y>=2)&&(x<=4)&&(randchar[x][y]==randchar[x+1][y])&&(randchar[x][y]==randchar[x+2][y])
			&&(randchar[x][y]==randchar[x][y-1])&&(randchar[x][y]==randchar[x][y-2])){
			n_score+=1000;boom+=5;	
			randchar[x][y]=7;randchar[x+1][y]=7;randchar[x][y-2]=7;
			randchar[x+2][y]=7;randchar[x][y-1]=7;}
		
		else if((x<=4)&&(x>=2)&&(randchar[x][y]==randchar[x-2][y])&&(randchar[x][y]==randchar[x-1][y])&&
				(randchar[x][y]==randchar[x+1][y])&&(randchar[x][y]==randchar[x+2][y])){
					n_score+=1000;boom+=5;
					randchar[x][y]=7;randchar[x-2][y]=7;randchar[x-1][y]=7;randchar[x+2][y]=7;
					randchar[x+1][y]=7;}
		
		else if((x>=1)&&(x<=4)&&(randchar[x][y]==randchar[x+1][y])&&(randchar[x][y]==randchar[x+2][y])&&
			(randchar[x][y]==randchar[x-1][y])){
			n_score+=150;boom+=4;
			randchar[x][y]=7;randchar[x+2][y]=7;randchar[x-1][y]=7;
			randchar[x+1][y]=7;}
		//
		else if((x>=2)&&(x<=5)&&(randchar[x][y]==randchar[x-1][y])&&(randchar[x][y]==randchar[x-2][y])&&
		(randchar[x][y]==randchar[x+1][y])){
			n_score+=150;boom+=4;
			randchar[x][y]=7;randchar[x-1][y]=7;randchar[x-2][y]=7;
			randchar[x+1][y]=7;
		}

		else if((x>=1)&&(x<=5)&&(randchar[x][y]==randchar[x-1][y])&&(randchar[x][y]==randchar[x+1][y])){
			n_score+=500;boom+=3;
			randchar[x][y]=7;randchar[x-1][y]=7;
			randchar[x+1][y]=7;
			}

		else if((x<=4)&&(randchar[x][y]==randchar[x+1][y])&&(randchar[x][y]==randchar[x+2][y])){
			n_score+=500;boom+=3;
			randchar[x][y]=7;randchar[x+1][y]=7;
			randchar[x+2][y]=7;
		}

		else if((x>=2)&&(randchar[x][y]==randchar[x-1][y])&&(randchar[x][y]==randchar[x-2][y])){
			n_score+=500;boom+=3;
			randchar[x][y]=7;randchar[x-1][y]=7;randchar[x-2][y]=7;
			}

		else if((y>=2)&&(randchar[x][y]==randchar[x][y-1])&&(randchar[x][y]==randchar[x][y-2])){
			n_score+=500;boom+=3;
			randchar[x][y]=7;randchar[x][y-2]=7;randchar[x][y-1]=7;
			}
		}
		isDownmove2=false;
		
	}	
	//���� �̵�
	public void upmoves(int x,int y){
		moveMusic.startPlay();
		if(y>=1){
		int temp;
		temp=randchar[x][y];
		randchar[x][y]=randchar[x][y-1];
		randchar[x][y-1]=temp;
		}
	}
	
	public void returnup(int x,int y){
		Timer rl =new Timer();
		TimerTask rs =new TimerTask(){
			public void run(){
				if(y>=1&&(randchar[x][y-1]!=7)&&(randchar[x][y]!=7)){
					int temp;
					temp=randchar[x][y-1];
					randchar[x][y-1]=randchar[x][y];
					randchar[x][y]=temp;
				}
			}
		};
		rl.schedule(rs,500);
	}
	
	public void confirmUp(int x,int y){
		
		if((y>=3)&&(randchar[x][y-1]==randchar[x][y-2])&&(randchar[x][y-1]==randchar[x][y-3])){
			isUpmove1=true;
		}
		else if((x<=5)&&(x>=1)&&(y>=1)&&(randchar[x][y-1]==randchar[x+1][y-1])&&(randchar[x][y-1]==randchar[x-1][y-1])){
			isUpmove1=true;
		}
		
		else if((y>=1)&&(x>=2)&&(randchar[x][y-1]==randchar[x-1][y-1])&&(randchar[x][y-1]==randchar[x-2][y-1])){
			isUpmove1=true;
		}
		
		else if((x<=4)&&(y>=1)&&(randchar[x][y-1]==randchar[x+1][y-1])&&(randchar[x][y-1]==randchar[x+2][y-1])){
			isUpmove1=true;
		}
		
		if((y<=4)&&(randchar[x][y]==randchar[x][y+1])&&(randchar[x][y]==randchar[x][y+2])){
			isUpmove2=true;
		}
		
		else if((x<=4)&&(randchar[x][y]==randchar[x+1][y])&&(randchar[x][y]==randchar[x+2][y])){
			isUpmove2=true;
		}
		
		else if((x>=2)&&(randchar[x][y]==randchar[x-1][y])&&(randchar[x][y]==randchar[x-2][y])){
			isUpmove2=true;
		}
		
		else if((x>=1)&&(x<=5)&&(randchar[x][y]==randchar[x-1][y])&&(randchar[x][y]==randchar[x+1][y])){
			isUpmove2=true;
		}
	}
	
	public void Upmove1(int x, int y){
		if(randchar[x][y]!=7){
		if((x>=2)&&(x<=4)&&(y>=3)&&(randchar[x][y-1]==randchar[x-2][y-1])&&(randchar[x][y-1]==randchar[x-1][y-1])
			&&(randchar[x][y-1]==randchar[x+1][y-1])&&(randchar[x][y-1]==randchar[x+2][y-1])&&
			(randchar[x][y-1]==randchar[x][y-2])&&(randchar[x][y-1]==randchar[x][y-3])){
				n_score+=2000;boom+=7;
				randchar[x][y-1]=7;randchar[x-2][y-1]=7;randchar[x-1][y-1]=7;randchar[x+1][y-1]=7;
				randchar[x+2][y-1]=7;randchar[x][y-2]=7;randchar[x][y-3]=7;}
		
		else if((x>=2)&&(x<=5)&&(y>=3)&&(randchar[x][y-1]==randchar[x-2][y-1])&&(randchar[x][y-1]==randchar[x-1][y-1])
			&&(randchar[x][y-1]==randchar[x+1][y-1])&&
			(randchar[x][y-1]==randchar[x][y-2])&&(randchar[x][y-1]==randchar[x][y-3])){
				n_score+=1500;boom+=6;
				randchar[x][y-1]=7;randchar[x-2][y-1]=7;randchar[x-1][y-1]=7;randchar[x+1][y-1]=7;
				randchar[x][y-2]=7;randchar[x][y-3]=7;}
		
		else if((x>=1)&&(x<=4)&&(y>=3)&&(randchar[x][y-1]==randchar[x-1][y-1])
			&&(randchar[x][y-1]==randchar[x+1][y-1])&&(randchar[x][y-1]==randchar[x+2][y-1])&&
			(randchar[x][y-1]==randchar[x][y-2])&&(randchar[x][y-1]==randchar[x][y-3])){
				n_score+=1500;boom+=6;
				randchar[x][y-1]=7;randchar[x-1][y-1]=7;randchar[x+1][y-1]=7;
				randchar[x+2][y-1]=7;randchar[x][y-2]=7;randchar[x][y-3]=7;}
		
		else if((x<=4)&&(x>=2)&&(y>=1)&&(randchar[x][y-1]==randchar[x-2][y-1])&&(randchar[x][y]==randchar[x-1][y-1])&&
				(randchar[x][y-1]==randchar[x+1][y-1])&&(randchar[x][y-1]==randchar[x+2][y-1])){
					n_score+=1000;boom+=5;
					randchar[x][y-1]=7;randchar[x-2][y-1]=7;randchar[x-1][y-1]=7;randchar[x+2][y-1]=7;
					randchar[x+1][y-1]=7;}
		
		else if((x>=1)&&(x<=5)&&(y>=3)&&(randchar[x][y-1]==randchar[x-1][y-1])
			&&(randchar[x][y-1]==randchar[x+1][y-1])&&
			(randchar[x][y-1]==randchar[x][y-2])&&(randchar[x][y-1]==randchar[x][y-3])){
				n_score+=1000;boom+=5;
				randchar[x][y-1]=7;randchar[x-1][y-1]=7;randchar[x+1][y-1]=7;
				randchar[x][y-2]=7;randchar[x][y-3]=7;}
		
		else if((x>=2)&&(y>=3)&&(randchar[x][y-1]==randchar[x-2][y-1])&&(randchar[x][y-1]==randchar[x-1][y-1])
			&&(randchar[x][y-1]==randchar[x][y-2])&&(randchar[x][y-1]==randchar[x][y-3])){
				n_score+=1000;boom+=5;
				randchar[x][y-1]=7;randchar[x-2][y-1]=7;randchar[x-1][y-1]=7;
				randchar[x][y-2]=7;randchar[x][y-3]=7;}
		
		else if((x<=4)&&(y>=3)&&(randchar[x][y-1]==randchar[x+1][y-1])&&(randchar[x][y-1]==randchar[x+2][y-1])&&
			(randchar[x][y-1]==randchar[x][y-2])&&(randchar[x][y-1]==randchar[x][y-3])){
				n_score+=1000;boom+=5;
				randchar[x][y-1]=7;randchar[x+1][y-1]=7;
				randchar[x+2][y-1]=7;randchar[x][y-2]=7;randchar[x][y-3]=7;}
		
		else if((x>=2)&&(x<=5)&&(y>=1)&&(randchar[x][y-1]==randchar[x-2][y-1])&&(randchar[x][y-1]==randchar[x-1][y-1])
			&&(randchar[x][y-1]==randchar[x+1][y-1])){
				n_score+=150;boom+=4;
				randchar[x][y-1]=7;randchar[x-2][y-1]=7;randchar[x-1][y-1]=7;randchar[x+1][y-1]=7;}
		
		else if((x>=1)&&(x<=4)&&(y>=1)&&(randchar[x][y-1]==randchar[x-1][y-1])
			&&(randchar[x][y-1]==randchar[x+1][y-1])&&(randchar[x][y-1]==randchar[x+2][y-1])){
				n_score+=750;boom+=4;
				randchar[x][y-1]=7;randchar[x+2][y-1]=7;randchar[x-1][y-1]=7;randchar[x+1][y-1]=7;}
		
		else if((x>=1)&&(x<=5)&&(y>=1)&&(randchar[x][y-1]==randchar[x-1][y-1])
			&&(randchar[x][y-1]==randchar[x+1][y-1])){
				n_score+=500;boom+=3;
				randchar[x][y-1]=7;randchar[x-1][y-1]=7;randchar[x+1][y-1]=7;}
		
		else if((x>=2)&&(y>=1)&&(randchar[x][y-1]==randchar[x-1][y-1])
			&&(randchar[x][y-1]==randchar[x-2][y-1])){
				n_score+=500;boom+=3;
				randchar[x][y-1]=7;randchar[x-2][y-1]=7;randchar[x-1][y-1]=7;}

		else if((y>=3)&&(randchar[x][y-1]==randchar[x][y-2])
			&&(randchar[x][y-1]==randchar[x][y-3])){
				n_score+=500;boom+=3;
				randchar[x][y-1]=7;randchar[x][y-2]=7;randchar[x][y-3]=7;}
		
		else if((x<=4)&&(y>=1)&&(randchar[x][y-1]==randchar[x+1][y-1])&&(randchar[x][y-1]==randchar[x+2][y-1])){
				n_score+=500;boom+=3;
				randchar[x][y-1]=7;randchar[x+1][y-1]=7;randchar[x+2][y-1]=7;}
		}
		isUpmove1=false;
	}
	
	public void Upmove2(int x, int y){
		if(randchar[x][y]!=7){
		
		if((y<=4)&&(x>=2)&&(x<=4)&&(randchar[x][y]==randchar[x-1][y])&&(randchar[x][y]==randchar[x-2][y])
			&&(randchar[x][y]==randchar[x+1][y])&&(randchar[x][y]==randchar[x+2][y])&&
			(randchar[x][y]==randchar[x][y+1])&&(randchar[x][y]==randchar[x][y+2])){
		n_score+=2000;boom+=7;
		randchar[x][y]=7;randchar[x-1][y]=7;randchar[x][y+1]=7;randchar[x+1][y]=7;
		randchar[x][y+2]=7;randchar[x-2][y]=7;randchar[x+2][y]=7;}

		else if((y<=4)&&(x>=2)&&(x<=5)&&(randchar[x][y]==randchar[x-1][y])&&(randchar[x][y]==randchar[x-2][y])
			&&(randchar[x][y]==randchar[x+1][y])&&(randchar[x][y]==randchar[x][y+2])&&
			(randchar[x][y]==randchar[x][y+1])){
		n_score+=1500;boom+=6;
		randchar[x][y]=7;randchar[x-1][y]=7;randchar[x][y+1]=7;randchar[x+1][y]=7;
		randchar[x-2][y]=7;randchar[x][y+2]=7;}

		else if((y<=4)&&(x>=1)&&(x<=4)&&(randchar[x][y]==randchar[x-1][y])&&(randchar[x][y]==randchar[x+2][y])
			&&(randchar[x][y]==randchar[x+1][y])&&(randchar[x][y]==randchar[x][y+1])
			&&(randchar[x][y]==randchar[x][y+2])){
		n_score+=1500;boom+=6;
		randchar[x][y]=7;randchar[x-1][y]=7;randchar[x][y+1]=7;randchar[x][y+2]=7;
		randchar[x+2][y]=7;randchar[x+1][y]=7;}
		
		else if((x<=4)&&(x>=2)&&(randchar[x][y]==randchar[x-2][y])&&(randchar[x][y]==randchar[x-1][y])&&
				(randchar[x][y]==randchar[x+1][y])&&(randchar[x][y]==randchar[x+2][y])){
					n_score+=1000;boom+=5;
					randchar[x][y]=7;randchar[x-2][y]=7;randchar[x-1][y]=7;randchar[x+2][y]=7;
					randchar[x+1][y]=7;}
                
		else if((x>=1)&&(y<=4)&&(x<=5)&&(randchar[x][y]==randchar[x-1][y])&&(randchar[x][y]==randchar[x+1][y])
		&&(randchar[x][y]==randchar[x][y+1])&&(randchar[x][y]==randchar[x][y+2])){
		n_score+=1000;boom+=5;
		randchar[x][y]=7;randchar[x-1][y]=7;randchar[x][y+1]=7;
		randchar[x+1][y]=7;randchar[x][y+2]=7;}
		
		else if((y<=4)&&(x>=2)&&(randchar[x][y]==randchar[x-1][y])&&(randchar[x][y]==randchar[x-2][y])
			&&(randchar[x][y]==randchar[x][y+1])&&(randchar[x][y]==randchar[x][y+2])){
		n_score+=1000;boom+=5;
		randchar[x][y]=7;randchar[x-1][y]=7;randchar[x][y+1]=7;
		randchar[x-2][y]=7;randchar[x][y+2]=7;}
		
		else if((y<=4)&&(x<=4)&&(randchar[x][y]==randchar[x+1][y])&&(randchar[x][y]==randchar[x+2][y])
			&&(randchar[x][y]==randchar[x][y+1])&&(randchar[x][y]==randchar[x][y+2])){
			n_score+=1000;boom+=5;	
			randchar[x][y]=7;randchar[x+1][y]=7;randchar[x][y+2]=7;
			randchar[x+2][y]=7;randchar[x][y+1]=7;}
		
		else if((x>=1)&&(x<=4)&&(randchar[x][y]==randchar[x+1][y])&&(randchar[x][y]==randchar[x+2][y])&&
			(randchar[x][y]==randchar[x-1][y])){
			n_score+=750;boom+=4;
			randchar[x][y]=7;randchar[x+2][y]=7;randchar[x-1][y]=7;
			randchar[x+1][y]=7;}
		//
		else if((x>=2)&&(x<=5)&&(randchar[x][y]==randchar[x-1][y])&&(randchar[x][y]==randchar[x-2][y])&&
		(randchar[x][y]==randchar[x+1][y])){
			n_score+=750;boom+=4;
			randchar[x][y]=7;randchar[x-1][y]=7;randchar[x-2][y]=7;
			randchar[x+1][y]=7;
		}

		else if((x>=1)&&(x<=5)&&(randchar[x][y]==randchar[x-1][y])&&(randchar[x][y]==randchar[x+1][y])){
			n_score+=500;boom+=3;
			randchar[x][y]=7;randchar[x-1][y]=7;
			randchar[x+1][y]=7;
			}

		else if((x<=4)&&(randchar[x][y]==randchar[x+1][y])&&(randchar[x][y]==randchar[x+2][y])){
			n_score+=500;boom+=3;
			randchar[x][y]=7;randchar[x+1][y]=7;
			randchar[x+2][y]=7;
		}

		else if((x>=2)&&(randchar[x][y]==randchar[x-1][y])&&(randchar[x][y]==randchar[x-2][y])){
			n_score+=500;boom+=3;
			randchar[x][y]=7;randchar[x-1][y]=7;randchar[x-2][y]=7;
			}

		else if((y<=4)&&(randchar[x][y]==randchar[x][y+1])&&(randchar[x][y]==randchar[x][y+2])){
			n_score+=500;boom+=3;
			randchar[x][y]=7;randchar[x][y+2]=7;randchar[x][y+1]=7;
			}
		}
		isUpmove2=false;
	}

	//������ �̵�
	public void rightmoves(int x,int y){
		moveMusic.startPlay();
		if(x>=0&&x<=6){
		int temp;
		temp=randchar[x][y];
		randchar[x][y]=randchar[x+1][y];
		randchar[x+1][y]=temp;
		}	
	}
	
	public void returnright(int x,int y){
		Timer rl =new Timer();
		TimerTask rs =new TimerTask(){
			public void run(){
				if(x>=0&&x<=5&&(randchar[x+1][y]!=7)&&(randchar[x][y]!=7)){
					int temp;
					temp=randchar[x+1][y];
					randchar[x+1][y]=randchar[x][y];
					randchar[x][y]=temp;
				}
			}
		};
		rl.schedule(rs,500);
	}
public void Rightmove1(int x, int y){
	if(randchar[x][y]!=7){
		if((x<=3)&&(y>=2)&&(y<=4)&&(randchar[x+1][y]==randchar[x+2][y])&&(randchar[x+1][y]==randchar[x+3][y])
			&&(randchar[x+1][y]==randchar[x+1][y-1])&&(randchar[x+1][y]==randchar[x+1][y-2])&&
			(randchar[x+1][y]==randchar[x+1][y+1])&&(randchar[x+1][y]==randchar[x+1][y+2])){
				n_score+=2000;boom+=7;
				randchar[x+1][y]=7;randchar[x+2][y]=7;randchar[x+3][y]=7;randchar[x+1][y-1]=7;
				randchar[x+1][y-2]=7;randchar[x+1][y+1]=7;randchar[x+1][y+2]=7;}
		
		else if((y<=5)&&(x<=3)&&(y>=2)&&(randchar[x+1][y]==randchar[x+2][y])&&(randchar[x+1][y]==randchar[x+3][y])
			&&(randchar[x+1][y]==randchar[x+1][y-1])&&(randchar[x+1][y]==randchar[x+1][y-2])&&
			(randchar[x+1][y]==randchar[x+1][y+1])){
			n_score+=1500;boom+=6;
			randchar[x+1][y]=7;randchar[x+2][y]=7;randchar[x+3][y]=7;randchar[x+1][y-1]=7;
			randchar[x+1][y-2]=7;randchar[x+1][y+1]=7;}
		//
		else if((x<=3)&&(y<=4)&&(y>=1)&&(randchar[x+1][y]==randchar[x+2][y])&&(randchar[x+1][y]==randchar[x+3][y])
			&&(randchar[x+1][y]==randchar[x+1][y-1])&&(randchar[x+1][y]==randchar[x+1][y+1])
			&&(randchar[x+1][y]==randchar[x+1][y+2])){
			n_score+=1500;boom+=6;	
			randchar[x+1][y]=7;randchar[x+2][y]=7;randchar[x+3][y]=7;randchar[x+1][y-1]=7;
			randchar[x+1][y+1]=7;randchar[x+1][y+2]=7;}
		
		else if((x<=5)&&(y<=4)&&(y>=2)&&(randchar[x+1][y]==randchar[x+1][y+1])&&(randchar[x+1][y]==randchar[x+1][y+2])
				&&(randchar[x+1][y]==randchar[x+1][y-1])&&(randchar[x+1][y]==randchar[x+1][y-2])){
				n_score+=1500;boom+=5;	
				randchar[x+1][y-2]=7;randchar[x+1][y]=7;randchar[x+1][y-1]=7;
				randchar[x+1][y+1]=7;randchar[x+1][y+2]=7;}
		
		else if((x<=3)&&(y>=1)&&(y<=5)&&(randchar[x+1][y]==randchar[x+2][y])&&(randchar[x+1][y]==randchar[x+3][y])
			&&(randchar[x+1][y]==randchar[x+1][y-1])&&(randchar[x+1][y]==randchar[x+1][y+1])){
			n_score+=1000;boom+=5;
			randchar[x+1][y]=7;randchar[x+2][y]=7;randchar[x+3][y]=7;randchar[x+1][y-1]=7;
			randchar[x+1][y+1]=7;}
		
		else if((x<=3)&&(y>=2)&&(randchar[x+1][y]==randchar[x+2][y])&&(randchar[x+1][y]==randchar[x+3][y])
			&&(randchar[x+1][y]==randchar[x+1][y-1])&&(randchar[x+1][y]==randchar[x+1][y-2])
			){
			n_score+=1000;boom+=5;
			randchar[x+1][y]=7;randchar[x+2][y]=7;randchar[x+3][y]=7;
			randchar[x+1][y-1]=7;randchar[x+1][y-2]=7;}
		
		else if((x<=3)&&(y<=4)&&(randchar[x+1][y]==randchar[x+2][y])&&(randchar[x+1][y]==randchar[x+3][y])
				&&(randchar[x+1][y]==randchar[x+1][y+1])&&(randchar[x+1][y]==randchar[x+1][y+2])){
			n_score+=1000;boom+=5;
			randchar[x+1][y]=7;randchar[x+2][y]=7;randchar[x+3][y]=7;
			randchar[x+1][y+1]=7;randchar[x+1][y+2]=7;}
		
		else if((x<=5)&&(y>=2)&&(y<=5)&&(randchar[x+1][y]==randchar[x+1][y+1])&&(randchar[x+1][y]==randchar[x+1][y-1])&&
				(randchar[x+1][y]==randchar[x+1][y-2])){
			n_score+=750;boom+=4;
			randchar[x+1][y]=7;randchar[x+1][y+1]=7;randchar[x+1][y-1]=7;
			randchar[x+1][y-2]=7;
		}
		
		else if((x<=5)&&(y>=1)&&(y<=4)&&(randchar[x+1][y]==randchar[x+1][y+1])&&(randchar[x+1][y]==randchar[x+1][y+2])&&
				(randchar[x+1][y]==randchar[x+1][y-1])){
			n_score+=750;boom+=4;
			randchar[x+1][y]=7;randchar[x+1][y+1]=7;randchar[x+1][y+2]=7;
			randchar[x+1][y-1]=7;
		}
		
		else if((x<=5)&&(y>=1)&&(y<=5)&&(randchar[x+1][y]==randchar[x+1][y+1])&&(randchar[x+1][y]==randchar[x+1][y-1])
				){
			n_score+=500;boom+=3;
			randchar[x+1][y]=7;randchar[x+1][y+1]=7;randchar[x+1][y-1]=7;
		}
		
		else if((x<=5)&&(y>=2)&&(randchar[x+1][y]==randchar[x+1][y-1])&&(randchar[x+1][y]==randchar[x+1][y-2])
				){
			n_score+=500;boom+=3;
			randchar[x+1][y]=7;randchar[x+1][y-1]=7;randchar[x+1][y-2]=7;
		}
		
		else if((x<=3)&&(randchar[x+1][y]==randchar[x+2][y])&&(randchar[x+1][y]==randchar[x+3][y])){
			n_score+=500;boom+=3;
			randchar[x+1][y]=7;randchar[x+2][y]=7;randchar[x+3][y]=7;
		}
		
		else if((x<=5)&&(y<=4)&&(randchar[x+1][y]==randchar[x+1][y+1])&&(randchar[x+1][y]==randchar[x+1][y+2])){
			n_score+=500;boom+=3;
			randchar[x+1][y]=7;randchar[x+1][y+1]=7;randchar[x+1][y+2]=7;
		}
	}
		isRightmove1=false;
	}

public void Rightmove2(int x, int y){
	if(randchar[x][y]!=7){
	
	if((y>=2)&&(x>=2)&&(y<=4)&&(randchar[x][y]==randchar[x-1][y])&&(randchar[x][y]==randchar[x-2][y])
			&&(randchar[x][y]==randchar[x][y-1])&&(randchar[x][y]==randchar[x][y-2])&&
			(randchar[x][y]==randchar[x][y+1])&&(randchar[x][y]==randchar[x][y+2])){
		n_score+=2000;boom+=7;
		randchar[x][y]=7;randchar[x-1][y]=7;randchar[x][y-1]=7;randchar[x][y+1]=7;
		randchar[x][y-2]=7;randchar[x-2][y]=7;randchar[x][y+2]=7;}

	else if((y>=2)&&(x>=2)&&(y<=5)&&(randchar[x][y]==randchar[x-1][y])&&(randchar[x][y]==randchar[x-2][y])
			&&(randchar[x][y]==randchar[x][y-1])&&(randchar[x][y]==randchar[x][y-2])&&
			(randchar[x][y]==randchar[x][y+1])){
		n_score+=1500;boom+=6;
		randchar[x][y]=7;randchar[x-1][y]=7;randchar[x][y-1]=7;randchar[x][y+1]=7;
		randchar[x][y-2]=7;randchar[x-2][y]=7;}

	else if((y>=1)&&(x>=2)&&(y<=4)&&(randchar[x][y]==randchar[x-1][y])&&(randchar[x][y]==randchar[x-2][y])
			&&(randchar[x][y]==randchar[x][y-1])&&(randchar[x][y]==randchar[x][y+1])
			&&(randchar[x][y]==randchar[x][y+2])){
		n_score+=1500;boom+=6;
		randchar[x][y]=7;randchar[x-1][y]=7;randchar[x][y-1]=7;randchar[x][y+1]=7;
		randchar[x-2][y]=7;randchar[x][y+2]=7;}
	
	else if((y<=4)&&(y>=2)&&(randchar[x][y]==randchar[x][y+1])&&(randchar[x][y]==randchar[x][y+2])
			&&(randchar[x][y]==randchar[x+1][y-1])&&(randchar[x][y]==randchar[x][y-2])){
			n_score+=1500;boom+=5;	
			randchar[x][y-2]=7;randchar[x][y]=7;randchar[x][y-1]=7;
			randchar[x][y+1]=7;randchar[x][y+2]=7;}

	else if((y>=1)&&(x>=2)&&(y<=5)&&(randchar[x][y]==randchar[x-1][y])&&(randchar[x][y]==randchar[x-2][y])
		&&(randchar[x][y]==randchar[x][y-1])&&(randchar[x][y]==randchar[x][y+1])){
		n_score+=1000;boom+=5;
		randchar[x][y]=7;randchar[x-1][y]=7;randchar[x][y-1]=7;
		randchar[x-2][y]=7;randchar[x][y+1]=7;}

	else if((y>=2)&&(x>=2)&&(randchar[x][y]==randchar[x-1][y])&&(randchar[x][y]==randchar[x-2][y])
			&&(randchar[x][y]==randchar[x][y-1])&&(randchar[x][y]==randchar[x][y-2])){
		n_score+=1000;boom+=5;
		randchar[x][y]=7;randchar[x-1][y]=7;randchar[x][y-1]=7;
		randchar[x-2][y]=7;randchar[x][y-2]=7;}

	else if((x>=2)&&(y<=4)&&(randchar[x][y]==randchar[x-1][y])&&(randchar[x][y]==randchar[x-2][y])
			&&(randchar[x][y]==randchar[x][y+1])&&(randchar[x][y]==randchar[x][y+2])){
			n_score+=1000;boom+=5;	
			randchar[x][y]=7;randchar[x-1][y]=7;randchar[x][y+2]=7;
			randchar[x-2][y]=7;randchar[x][y+1]=7;}

		else if((y>=2)&&(y<=5)&&(randchar[x][y]==randchar[x][y+1])&&(randchar[x][y]==randchar[x][y-1])&&
			(randchar[x][y]==randchar[x][y-2])){
			n_score+=750;boom+=4;
			randchar[x][y]=7;randchar[x][y-2]=7;randchar[x][y-1]=7;
			randchar[x][y+1]=7;}

		else if((y>=1)&&(y<=4)&&(randchar[x][y]==randchar[x][y+1])&&(randchar[x][y]==randchar[x][y+2])&&
		(randchar[x][y]==randchar[x][y-1])){
			n_score+=750;boom+=4;
			randchar[x][y]=7;randchar[x][y+2]=7;randchar[x][y-1]=7;
			randchar[x][y+1]=7;
		}

		else if((y>=1)&&(y<=5)&&(randchar[x][y]==randchar[x][y+1])&&(randchar[x][y]==randchar[x][y-1])){
			n_score+=500;boom+=3;
			randchar[x][y]=7;randchar[x][y-1]=7;
			randchar[x][y+1]=7;
			}

		else if((y>=2)&&(randchar[x][y]==randchar[x][y-1])&&(randchar[x][y]==randchar[x][y-2])){
			n_score+=500;boom+=3;
			randchar[x][y]=7;randchar[x][y-1]=7;
			randchar[x][y-2]=7;
		}

		else if((x>=2)&&(randchar[x][y]==randchar[x-1][y])&&(randchar[x][y]==randchar[x-2][y])){
			n_score+=500;boom+=3;
			randchar[x][y]=7;randchar[x-1][y]=7;randchar[x-2][y]=7;
			}

		else if((y<=4)&&(randchar[x][y]==randchar[x][y+1])&&(randchar[x][y]==randchar[x][y+2])){
			n_score+=500;boom+=3;
			randchar[x][y]=7;randchar[x][y+2]=7;randchar[x][y+1]=7;
			}
	}
		isRightmove2=false;
	}

	public void confirmRight(int x,int y){
		
			if((x<=5)&&(y<=4)&&(randchar[x+1][y]==randchar[x+1][y+1])&&(randchar[x+1][y]==randchar[x+1][y+2])){
				isRightmove1=true;
			}
			else if((x<=5)&&(y>=1)&&(y<=5)&&(randchar[x+1][y]==randchar[x+1][y+1])&&(randchar[x+1][y]==randchar[x+1][y-1])){
				isRightmove1=true;
			}
			
			else if((x<=5)&&(y>=2)&&(randchar[x+1][y]==randchar[x+1][y-1])&&(randchar[x+1][y]==randchar[x+1][y-2])){
				isRightmove1=true;
			}
			
			else if((x<=3)&&(randchar[x+1][y]==randchar[x+2][y])&&(randchar[x+1][y]==randchar[x+3][y])){
				isRightmove1=true;
			}
			
			if((y>=1)&&(y<=5)&&(randchar[x][y]==randchar[x][y+1])&&(randchar[x][y]==randchar[x][y-1])){
				isRightmove2=true;
			}
			
			else if((y>=2)&&(randchar[x][y]==randchar[x][y-1])&&(randchar[x][y]==randchar[x][y-2])){
				isRightmove2=true;
			}
			
			else if((x>=2)&&(randchar[x][y]==randchar[x-1][y])&&(randchar[x][y]==randchar[x-2][y])){
				isRightmove2=true;
			}
			
			else if((y<=4)&&(randchar[x][y]==randchar[x][y+1])&&(randchar[x][y]==randchar[x][y+2])){
				isRightmove2=true;
			}
			
	}

	
	//�����̵�
	public void leftmoves(int x,int y){
		moveMusic.startPlay();
	if(x>=1&&x<=7){
		int temp;
		temp=randchar[x][y];
		randchar[x][y]=randchar[x-1][y];
		randchar[x-1][y]=temp;
		}
	}
	//�߸����ϸ� �ǵ�
	public void returnleft(int x, int y){
		Timer rl =new Timer();
		TimerTask rs =new TimerTask(){
			public void run(){
				if(x>=1&&x<=7&&(randchar[x-1][y]!=7)&&(randchar[x][y]!=7)){
					int temp;
					temp=randchar[x-1][y];
					randchar[x-1][y]=randchar[x][y];
					randchar[x][y]=temp;
				}
			}
		};
		rl.schedule(rs,500);
	}
	public void confirmLeft(int x,int y){
		
			if((x>=1)&&(y<=4)&&(randchar[x-1][y]==randchar[x-1][y+1])&&(randchar[x-1][y]==randchar[x-1][y+2])){
				isLeftmove1=true;
			}
			else if((x>=1)&&(y>=1)&&(y<=5)&&(randchar[x-1][y]==randchar[x-1][y+1])&&(randchar[x-1][y]==randchar[x-1][y-1])){
				isLeftmove1=true;
			}
			
			else if((x>=1)&&(y>=2)&&(randchar[x-1][y]==randchar[x-1][y-1])&&(randchar[x-1][y]==randchar[x-1][y-2])){
				isLeftmove1=true;
			}
			
			else if((x>=3)&&(randchar[x-1][y]==randchar[x-2][y])&&(randchar[x-1][y]==randchar[x-3][y])){
				isLeftmove1=true;
			}
			
			if((y>=1)&&(y<=5)&&(randchar[x][y]==randchar[x][y+1])&&(randchar[x][y]==randchar[x][y-1])){
				isLeftmove2=true;
			}
			
			else if((y>=2)&&(randchar[x][y]==randchar[x][y-1])&&(randchar[x][y]==randchar[x][y-2])){
				isLeftmove2=true;
			}
			
			else if((x<=4)&&(randchar[x][y]==randchar[x+1][y])&&(randchar[x][y]==randchar[x+2][y])){
				isLeftmove2=true;
			}
			
			else if((y<=4)&&(randchar[x][y]==randchar[x][y+1])&&(randchar[x][y]==randchar[x][y+2])){
				isLeftmove2=true;
			}
		}	
	public void leftmove1(int x, int y){
		if(randchar[x][y]!=7){
		if((x>=3)&&(y>=2)&&(y<=4)&&(randchar[x-1][y]==randchar[x-2][y])&&(randchar[x-1][y]==randchar[x-3][y])
			&&(randchar[x-1][y]==randchar[x-1][y-1])&&(randchar[x-1][y]==randchar[x-1][y-2])&&
			(randchar[x-1][y]==randchar[x-1][y+1])&&(randchar[x-1][y]==randchar[x-1][y+2])){
				n_score+=2000;boom+=7;
				randchar[x-1][y]=7;randchar[x-2][y]=7;randchar[x-3][y]=7;randchar[x-1][y-1]=7;
				randchar[x-1][y-2]=7;randchar[x-1][y+1]=7;randchar[x-1][y+2]=7;}
		
		else if((y<=5)&&(x>=3)&&(y>=2)&&(randchar[x-1][y]==randchar[x-2][y])&&(randchar[x-1][y]==randchar[x-3][y])
			&&(randchar[x-1][y]==randchar[x-1][y-1])&&(randchar[x-1][y]==randchar[x-1][y-2])&&
			(randchar[x-1][y]==randchar[x-1][y+1])){
			n_score+=1500;boom+=6;
			randchar[x-1][y]=7;randchar[x-2][y]=7;randchar[x-3][y]=7;randchar[x-1][y-1]=7;
			randchar[x-1][y-2]=7;randchar[x-1][y+1]=7;}
		
		else if((x>=3)&&(y>=1)&&(y<=4)&&(randchar[x-1][y]==randchar[x-2][y])&&(randchar[x-1][y]==randchar[x-3][y])
			&&(randchar[x-1][y]==randchar[x-1][y-1])&&(randchar[x-1][y]==randchar[x-1][y+1])
			&&(randchar[x-1][y]==randchar[x-1][y+2])){
			n_score+=1500;boom+=6;	
			randchar[x-1][y]=7;randchar[x-2][y]=7;randchar[x-3][y]=7;randchar[x-1][y-1]=7;
			randchar[x-1][y+1]=7;randchar[x-1][y+2]=7;}
		
		else if((x>=1)&&(y<=4)&&(y>=2)&&(randchar[x-1][y]==randchar[x-1][y+1])&&(randchar[x-1][y]==randchar[x-1][y+2])
				&&(randchar[x-1][y]==randchar[x-1][y-1])&&(randchar[x-1][y]==randchar[x-1][y-2])){
				n_score+=1500;boom+=5;	
				randchar[x-1][y-2]=7;randchar[x-1][y]=7;randchar[x-1][y-1]=7;
				randchar[x-1][y+1]=7;randchar[x-1][y+2]=7;}
		
		else if((x>=3)&&(y>=1)&&(y<=5)&&(randchar[x-1][y]==randchar[x-2][y])&&(randchar[x-1][y]==randchar[x-3][y])
			&&(randchar[x-1][y]==randchar[x-1][y-1])&&(randchar[x-1][y]==randchar[x-1][y+1])){
			n_score+=1000;boom+=5;
			randchar[x-1][y]=7;randchar[x-2][y]=7;randchar[x-3][y]=7;
			randchar[x-1][y-1]=7;randchar[x-1][y+1]=7;}
		
		else if((x>=3)&&(y>=2)&&(randchar[x-1][y]==randchar[x-2][y])&&(randchar[x-1][y]==randchar[x-3][y])
			&&(randchar[x-1][y]==randchar[x-1][y-1])&&(randchar[x-1][y]==randchar[x-1][y-2])
			){
			n_score+=1000;boom+=5;
			randchar[x-1][y]=7;randchar[x-2][y]=7;randchar[x-3][y]=7;
			randchar[x-1][y-1]=7;randchar[x-1][y-2]=7;}
		
		else if((x>=3)&&(y<=4)&&(randchar[x-1][y]==randchar[x-2][y])&&(randchar[x-1][y]==randchar[x-3][y])
				&&(randchar[x-1][y]==randchar[x-1][y+1])&&(randchar[x-1][y]==randchar[x-1][y+2])){
			n_score+=1000;boom+=5;
			randchar[x-1][y]=7;randchar[x-2][y]=7;randchar[x-3][y]=7;
			randchar[x-1][y+1]=7;randchar[x-1][y+2]=7;}
		
		else if((x>=1)&&(y>=2)&&(y<=5)&&(randchar[x-1][y]==randchar[x-1][y+1])&&(randchar[x-1][y]==randchar[x-1][y-1])&&
				(randchar[x-1][y]==randchar[x-1][y-2])){
			n_score+=750;boom+=4;
			randchar[x-1][y]=7;randchar[x-1][y+1]=7;randchar[x-1][y-1]=7;
			randchar[x-1][y-2]=7;
		}
		
		else if((x>=1)&&(y>=1)&&(y<=4)&&(randchar[x-1][y]==randchar[x-1][y+1])&&(randchar[x-1][y]==randchar[x-1][y+2])&&
				(randchar[x-1][y]==randchar[x-1][y-1])){
			n_score+=750;boom+=4;
			randchar[x-1][y]=7;randchar[x-1][y+1]=7;randchar[x-1][y+2]=7;
			randchar[x-1][y-1]=7;
		}
		
		else if((x>=1)&&(y>=1)&&(y<=5)&&(randchar[x-1][y]==randchar[x-1][y+1])&&(randchar[x-1][y]==randchar[x-1][y-1])
				){
			n_score+=500;boom+=3;
			randchar[x-1][y]=7;randchar[x-1][y+1]=7;randchar[x-1][y-1]=7;
		}
		
		else if((x>=1)&&(y>=2)&&(randchar[x-1][y]==randchar[x-1][y-1])&&(randchar[x-1][y]==randchar[x-1][y-2])
				){
			n_score+=500;boom+=3;
			randchar[x-1][y]=7;randchar[x-1][y-1]=7;randchar[x-1][y-2]=7;
		}
		
		else if((x>=3)&&(randchar[x-1][y]==randchar[x-2][y])&&(randchar[x-1][y]==randchar[x-3][y])){
			n_score+=500;boom+=3;
			randchar[x-1][y]=7;randchar[x-2][y]=7;randchar[x-3][y]=7;
		}
		
		else if((x>=1)&&(y<=4)&&(randchar[x-1][y]==randchar[x-1][y+1])&&(randchar[x-1][y]==randchar[x-1][y+2])){
			n_score+=500;boom+=3;
			randchar[x-1][y]=7;randchar[x-1][y+1]=7;randchar[x-1][y+2]=7;
		}
		}
		isLeftmove1=false;
	}
		public void leftmove2(int x, int y){
			if(randchar[x][y]!=7){
		if((y>=2)&&(x<=4)&&(y<=4)&&(randchar[x][y]==randchar[x+1][y])&&(randchar[x][y]==randchar[x+2][y])
		&&(randchar[x][y]==randchar[x][y-1])&&(randchar[x][y]==randchar[x][y-2])&&
		(randchar[x][y]==randchar[x][y+1])&&(randchar[x][y]==randchar[x][y+2])){
		n_score+=2000;boom+=7;
		randchar[x][y]=7;randchar[x+1][y]=7;randchar[x][y-1]=7;randchar[x][y+1]=7;
		randchar[x][y-2]=7;randchar[x+2][y]=7;randchar[x][y+2]=7;}
		
		else if((y>=2)&&(x<=4)&&(y<=5)&&(randchar[x][y]==randchar[x+1][y])&&(randchar[x][y]==randchar[x+2][y])
		&&(randchar[x][y]==randchar[x][y-1])&&(randchar[x][y]==randchar[x][y-2])&&
		(randchar[x][y]==randchar[x][y+1])){
			n_score+=1500;boom+=6;
			randchar[x][y]=7;randchar[x+1][y]=7;randchar[x][y-1]=7;randchar[x][y+1]=7;
			randchar[x][y-2]=7;randchar[x+2][y]=7;}
		
		else if((y>=1)&&(x<=4)&&(y<=4)&&(randchar[x][y]==randchar[x+1][y])&&(randchar[x][y]==randchar[x+2][y])
		&&(randchar[x][y]==randchar[x][y-1])&&(randchar[x][y]==randchar[x][y+1])
		&&(randchar[x][y]==randchar[x][y+2])){
			n_score+=1500;boom+=6;
			randchar[x][y]=7;randchar[x+1][y]=7;randchar[x][y-1]=7;randchar[x][y+1]=7;
			randchar[x+2][y]=7;randchar[x][y+2]=7;}
		
		else if((y<=4)&&(y>=2)&&(randchar[x][y]==randchar[x][y+1])&&(randchar[x][y]==randchar[x][y+2])
				&&(randchar[x][y]==randchar[x][y-1])&&(randchar[x][y]==randchar[x][y-2])){
				n_score+=1500;boom+=5;	
				randchar[x][y-2]=7;randchar[x][y]=7;randchar[x][y-1]=7;
				randchar[x][y+1]=7;randchar[x][y+2]=7;}
		
		else if((y>=1)&&(x<=4)&&(y<=5)&&(randchar[x][y]==randchar[x+1][y])&&(randchar[x][y]==randchar[x+2][y])
		&&(randchar[x][y]==randchar[x][y-1])&&(randchar[x][y]==randchar[x][y+1])){
			n_score+=1000;boom+=5;
			randchar[x][y]=7;randchar[x+1][y]=7;randchar[x][y-1]=7;
			randchar[x+2][y]=7;randchar[x][y+1]=7;}
		//
		else if((y>=2)&&(x<=4)&&(randchar[x][y]==randchar[x+1][y])&&(randchar[x][y]==randchar[x+2][y])
		&&(randchar[x][y]==randchar[x][y-1])&&(randchar[x][y]==randchar[x][y-2])){
			n_score+=1000;boom+=5;
			randchar[x][y]=7;randchar[x+1][y]=7;randchar[x][y-1]=7;
			randchar[x+2][y]=7;randchar[x][y-2]=7;}
		
		else if((x<=4)&&(y<=4)&&(randchar[x][y]==randchar[x+1][y])&&(randchar[x][y]==randchar[x+2][y])
		&&(randchar[x][y]==randchar[x][y+1])&&(randchar[x][y]==randchar[x][y+2])){
			n_score+=1000;boom+=5;	
			randchar[x][y]=7;randchar[x+1][y]=7;randchar[x][y+2]=7;
			randchar[x+2][y]=7;randchar[x][y+1]=7;}
		
		else if((y>=2)&&(y<=5)&&(randchar[x][y]==randchar[x][y+1])&&(randchar[x][y]==randchar[x][y-1])&&
		(randchar[x][y]==randchar[x][y-2])){
			n_score+=750;boom+=4;
			randchar[x][y]=7;randchar[x][y-2]=7;randchar[x][y-1]=7;
			randchar[x][y+1]=7;
		}
		
		else if((y>=1)&&(y<=4)&&(randchar[x][y]==randchar[x][y+1])&&(randchar[x][y]==randchar[x][y+2])&&
		(randchar[x][y]==randchar[x][y-1])){
			n_score+=750;boom+=4;
			randchar[x][y]=7;randchar[x][y+2]=7;randchar[x][y-1]=7;
			randchar[x][y+1]=7;
		}
		
		else if((y>=1)&&(y<=5)&&(randchar[x][y]==randchar[x][y+1])&&(randchar[x][y]==randchar[x][y-1])){
			n_score+=500;boom+=3;
			randchar[x][y]=7;randchar[x][y-1]=7;
			randchar[x][y+1]=7;
		}
		
		else if((y>=2)&&(randchar[x][y]==randchar[x][y-1])&&(randchar[x][y]==randchar[x][y-2])){
			n_score+=500;boom+=3;
			randchar[x][y]=7;randchar[x][y-1]=7;
			randchar[x][y-2]=7;
		}
		
		else if((x<=4)&&(randchar[x][y]==randchar[x+1][y])&&(randchar[x][y]==randchar[x+2][y])){
			n_score+=500;boom+=3;
			randchar[x][y]=7;randchar[x+1][y]=7;randchar[x+2][y]=7;
		}
		
		else if((y<=4)&&(randchar[x][y]==randchar[x][y+1])&&(randchar[x][y]==randchar[x][y+2])){
			n_score+=500;boom+=3;
			randchar[x][y]=7;randchar[x][y+2]=7;randchar[x][y+1]=7;
		}
			}
		isLeftmove2=false;
	}
		
		public void blankcheck(){ //��ĭä���
			int temp[][]=new int[7][7];
			Timer xc =new Timer();
			TimerTask xs =new TimerTask(){
				public void run(){
			for(y=0;y<=6;y++){
				for(x=0;x<=6;x++){
					if((y>=1)&&(randchar[x][y]==7)){
						temp[x][y]=randchar[x][y];
						randchar[x][y]=randchar[x][y-1];
						randchar[x][y-1]=temp[x][y];
						}
				}
			}
			blank=false;create=true;
		}
		};
		xc.schedule(xs,500);		
	}
		
		public void createcharacter(){
			Timer xt =new Timer();
			TimerTask xm =new TimerTask(){
				public void run(){	
		for(y=6;y>=0;y--){
			for(x=0;x<=6;x++){
				if((randchar[x][y]==7)){
					if(boom>=20){
						randchar[x][y]=8;boom=0;isboom=true;}
					else{ randchar[x][y]=(int)(Math.random()*7);}
				}
					}
						}
				create=false;fcheck=true;}
			}; xt.schedule(xm,500);
	}
		
		public void fieldcheck(){
			Timer xs =new Timer();
			TimerTask xd =new TimerTask(){
				public void run(){
			for(y=6;y>=0;y--){
				for(x=0;x<=6;x++){
			if(randchar[x][y]!=7){
				if((x<=4)&&(y>=2)&&(randchar[x][y]==randchar[x+1][y])&&(randchar[x+1][y]==randchar[x+2][y])
				&&(randchar[x][y]==randchar[x][y-1])&&(randchar[x][y-1]==randchar[x][y-2])){
					realcheck();pangsound.startPlay();blank=true;
				}
				
				else if((x>=1)&&(x<=5)&&(y>=2)&&(randchar[x-1][y]==randchar[x][y])&&(randchar[x][y]==randchar[x+1][y])
				&&(randchar[x][y]==randchar[x][y-1])&&(randchar[x][y-1]==randchar[x][y-2])){
					realcheck();pangsound.startPlay();
					blank=true;
				}
				
				else if((x>=2)&&(y>=2)&&(randchar[x-2][y]==randchar[x-1][y])&&(randchar[x-1][y]==randchar[x][y])
				&&(randchar[x][y]==randchar[x][y-1])&&(randchar[x][y-1]==randchar[x][y-2])){
					realcheck();pangsound.startPlay();blank=true;
				}
				//
				else if((x<=4)&&(y>=1)&&(y<=5)&&(randchar[x][y+1]==randchar[x][y])&&(randchar[x][y]==randchar[x+1][y])
				&&(randchar[x+1][y]==randchar[x+2][y])&&(randchar[x][y]==randchar[x][y-1])){
					realcheck();pangsound.startPlay();blank=true;
				}
				
				else if((x>=1)&&(x<=5)&&(y>=1)&&(y<=5)&&(randchar[x][y+1]==randchar[x][y])&&(randchar[x-1][y]==randchar[x][y])&&(randchar[x][y]==randchar[x+1][y])
				&&(randchar[x][y]==randchar[x][y-1])){
					realcheck();pangsound.startPlay();blank=true;
				}
				
				else if((x>=2)&&(y>=1)&&(y<=5)&&(randchar[x][y+1]==randchar[x][y])&&(randchar[x-2][y]==randchar[x-1][y])&&(randchar[x-1][y]==randchar[x][y])
				&&(randchar[x][y]==randchar[x][y-1])){
					realcheck();pangsound.startPlay();blank=true;
				}
				//
				else if((x<=4)&&(y<=4)&&(randchar[x][y+2]==randchar[x][y+1])&&(randchar[x][y+1]==randchar[x][y])
				&&(randchar[x][y]==randchar[x+1][y])&&(randchar[x+1][y]==randchar[x+2][y])){
					realcheck();pangsound.startPlay();blank=true;
				}
				
				else if((x<=5)&&(x>=1)&&(y<=4)&&(randchar[x][y+2]==randchar[x][y+1])&&(randchar[x][y+1]==randchar[x][y])&&
						(randchar[x-1][y]==randchar[x][y])&&(randchar[x][y]==randchar[x+1][y])){
					realcheck();pangsound.startPlay();blank=true;
				}	
				
				else if((x>=2)&&(y<=4)&&(randchar[x][y+2]==randchar[x][y+1])&&(randchar[x][y+1]==randchar[x][y])&&
						(randchar[x-2][y]==randchar[x-1][y])&&(randchar[x-1][y]==randchar[x][y])){
					realcheck();pangsound.startPlay();blank=true;
				}
			}
		  }
		}
			for(y=6;y>=0;y--){
				for(x=0;x<=6;x++){
			if(randchar[x][y]!=7){
				if((y>=2)&&(randchar[x][y]==randchar[x][y-1])&&(randchar[x][y-1]==randchar[x][y-2])){
					up();pangsound.startPlay();blank=true;
				}
				
				else if((x<=4)&&(randchar[x][y]==randchar[x+1][y])&&(randchar[x+1][y]==randchar[x+2][y])){
					right();pangsound.startPlay();blank=true;
				}
			}
				}
			}
			fcheck=false;}
			}; xs.schedule(xd,500);
	}
		
		public void up(){
			int temp1[][]=new int[7][7];
			int temp2[][]=new int[7][7];
			int a,i=0;
			temp1[x][y]=randchar[x][y];
			for(a=1;a<=y;a++){
			temp2[x][y-a]=randchar[x][y-a];
			}
			for(a=1;a<=y;a++){
			    if(temp1[x][y]==temp2[x][y-a]){
			    	randchar[x][y]=7;randchar[x][y-a]=7;i++;
				}
			    else if(temp1[x][y]!=temp2[x][y-a])
				break;
			}
			n_score+=(i+1)*300;boom+=i;
		}

		public void right(){
			int temp1[][]=new int[7][7];
			int temp2[][]=new int[7][7];
			int a,i=0;
			temp1[x][y]=randchar[x][y];
			for(a=1;a<=(6-x);a++){
				temp2[x+a][y]=randchar[x+a][y];
			}
			for(a=1;a<=(6-x);a++){
				if(temp1[x][y]==temp2[x+a][y]){
					randchar[x][y]=7;randchar[x+a][y]=7;i++;}
				else if(temp1[x][y]!=temp2[x+a][y])
						break;
			}n_score+=(i+1)*300;boom+=i;
	}
		
		public void realcheck(){
			int temp1[][]=new int[7][7];
			int temp2[][]=new int[7][7];
			int plus[][]=new int[7][7];
			int a,i=0,j=0,k=0,w=0;

			//up
			temp1[x][y]=randchar[x][y];
			
			for(a=1;a<=y;a++){
			temp2[x][y-a]=randchar[x][y-a];
			}
			for(a=1;a<=y;a++){
			    if(temp1[x][y]==temp2[x][y-a]){
			    	plus[x][y]=7;plus[x][y-a]=7;i++;
				}
			    else if(temp1[x][y]!=temp2[x][y-a])
				break;
			}
			n_score+=(i+1)*300;boom+=i;
			
			//left
			for(a=1;(a<=x);a++){
				temp2[x-a][y]=randchar[x-a][y];
			}
			for(a=1;a<=x;a++){
				if(temp1[x][y]==temp2[x-a][y]){
					plus[x][y]=7;plus[x-a][y]=7;k++;}
				else if(temp1[x][y]!=temp2[x-a][y])
					break;
			}
			n_score+=(k+1)*300;boom+=k;
			//down
			for(a=1;a<=(6-y);a++){
				temp2[x][y+a]=randchar[x][y+a];
			}
			for(a=1;a<=(6-y);a++){
				if(temp1[x][y]==temp2[x][y+a]){
					plus[x][y]=7;plus[x][y+a]=7;j++;
					}
				else if(temp1[x][y]!=temp2[x][y+a])
					break;
			}
			n_score+=(j+1)*300;boom+=j;
			//right
			for(a=1;a<=(6-x);a++){
				temp2[x+a][y]=randchar[x+a][y];
			}
			for(a=1;a<=(6-x);a++){
				if(temp1[x][y]==temp2[x+a][y]){
					plus[x][y]=7;plus[x+a][y]=7;w++;}
				else if(temp1[x][y]!=temp2[x+a][y])
						break;
			}n_score+=(w+1)*300;boom+=w;
			
		for(y=6;y>=0;y--){
			for(x=0;x<=6;x++){
				if(plus[x][y]==7)
				randchar[x][y]=plus[x][y];
				}
		}
	}

		
	public void nopangcheck(){
			int num=0;
			for(y=6;y>=0;y--){
				for(x=0;x<=6;x++){
			if((x<=4)&&(y>=1)&&(randchar[x][y]==randchar[x+1][y])&&(randchar[x][y]==randchar[x+2][y-1]))
				num++;
			else if((x<=3)&&(randchar[x][y]==randchar[x+1][y])&&(randchar[x][y]==randchar[x+3][y]))
				num++;
			else if((x<=4)&&(y>=1)&&(randchar[x][y-1]==randchar[x+1][y-1])&&(randchar[x][y-1]==randchar[x+2][y]))
				num++;
			else if((x<=4)&&(y>=1)&&(randchar[x][y-1]==randchar[x+1][y])&&(randchar[x][y-1]==randchar[x+2][y]))
				num++;
			else if((x<=3)&&(randchar[x][y]==randchar[x+2][y])&&(randchar[x][y]==randchar[x+3][y]))
				num++;
			else if((x<=4)&&(y>=1)&&(randchar[x][y]==randchar[x+1][y-1])&&(randchar[x][y]==randchar[x+2][y-1]))
				num++;
			else if((x<=4)&&(y>=1)&&(randchar[x][y-1]==randchar[x+1][y])&&(randchar[x][y-1]==randchar[x+2][y-1]))
				num++;
			else if((x<=4)&&(y>=1)&&(randchar[x][y]==randchar[x+1][y-1])&&(randchar[x][y]==randchar[x+2][y]))
				num++;//������˻�
			else if((x<=5)&&(y>=2)&&(randchar[x+1][y]==randchar[x][y-1])&&(randchar[x+1][y]==randchar[x][y-2]))
				num++;
			else if((x<=5)&&(y>=2)&&(randchar[x][y]==randchar[x+1][y-1])&&(randchar[x][y]==randchar[x+1][y-2]))
				num++;
			else if((y>=3)&&(randchar[x][y]==randchar[x][y-2])&&(randchar[x][y]==randchar[x][y-3]))
				num++;
			else if((x<=5)&&(y>=2)&&(randchar[x][y]==randchar[x][y-1])&&(randchar[x][y]==randchar[x+1][y-2]))
				num++;
			else if((y>=3)&&(randchar[x][y]==randchar[x][y-1])&&(randchar[x][y]==randchar[x][y-3]))
				num++;
			else if((x<=5)&&(y>=2)&&(randchar[x][y-2]==randchar[x+1][y])&&(randchar[x][y-2]==randchar[x+1][y-1]))
				num++;
			else if((x<=5)&&(y>=2)&&(randchar[x][y-1]==randchar[x+1][y])&&(randchar[x][y-1]==randchar[x+1][y-2]))
				num++;
			else if((x<=5)&&(y>=2)&&(randchar[x][y]==randchar[x][y-2])&&(randchar[x][y]==randchar[x+1][y-1]))
				num++;
				}
			}
			if(num==0)
			{
				newch.startPlay();
				for(x=0;x<=6;x++)// 7���� ���� ĳ���� ���� 
					for(y=0;y<=6;y++){
				{
						do{
							randchar[x][y] = (int)(Math.random()*7);
						}while(((x>=2)&&(randchar[x-1][y]==randchar[x][y])&&(randchar[x][y]==randchar[x-2][y]))||
						((y>=2)&&(randchar[x][y-1]==randchar[x][y])&&(randchar[x][y]==randchar[x][y-2])));
					}
				}
			}
	}
		
		public void boomm(int x, int y){
			for(int i=0;i<=6;i++)
				randchar[x][i]=7;
			for(int i=0;i<=6;i++)
				randchar[i][y]=7;
			n_score+=8000;
			blank=true;
		}

	public void screenDraw(Graphics2D g){
		g.drawImage(backgroundImage_size,0,0,null);
		g.drawImage(lolback,10,130,null);
		g.drawImage(score, 290,155,null);
		g.drawImage(mbar, 155,100,null);
		if(boom<=20){
		g.drawImage(gaze,155,100,(300*boom)/20,40,null);
		}
		g.drawImage(teemo, 420,70,null);

		if((ispress==true)&&(fcheck==false)&&(blank==false)&&(create==false)){
			if(randchar[tx][ty]==8){
				isboom=false;
				boomlaugh.startPlay();
				pangsound.startPlay();
				try{Thread.sleep(100);boomm(tx,ty);}
				catch(InterruptedException e){}
			}
		}
	
		if((isDragged==true)&&(fcheck==false)&&(blank==false)&&(create==false)){
			try{Thread.sleep(100);}
			catch(InterruptedException e){}
			
			if(((mx1)>-80)&&((mx1)<-25)&&((my1)<40)&&((my1)>-40)){ //�����̵� 
				leftmoves(tx,ty);confirmLeft(tx,ty);
				if((isLeftmove1==false)&&(isLeftmove2==false)){//�ξƴҽ� ���󺹱�
					try{Thread.sleep(100);returnleft(tx,ty);}
					catch(InterruptedException e){}
				}
				else if((isLeftmove1==true)&&(isLeftmove2==true)){
					pangsound.startPlay();
					leftmove1(tx,ty);
					leftmove2(tx,ty);
					blank=true;
				}
				else if(isLeftmove1==true){
					pangsound.startPlay();
					leftmove1(tx,ty);blank=true;
				}
				else if(isLeftmove2==true){
					pangsound.startPlay();
					leftmove2(tx,ty);blank=true;
				}
					
			}
			
			else if(((mx1)<80)&&((mx1)>25)&&((my1)<40)&&((my1)>-40)){//������ �̵�										
				rightmoves(tx,ty);confirmRight(tx,ty);
				if((isRightmove1==false)&&(isRightmove2==false)){
					try{Thread.sleep(100);returnright(tx,ty);}
					catch(InterruptedException e){}
				}
				else if((isRightmove1==true)&&(isRightmove2==true)){
					pangsound.startPlay();
					Rightmove1(tx,ty);
					Rightmove2(tx,ty);blank=true;
					
				}
				else if(isRightmove1==true){
					pangsound.startPlay();
					Rightmove1(tx,ty);blank=true;

				}
				else if(isRightmove2==true){
					pangsound.startPlay();
					Rightmove2(tx,ty);blank=true;

				}
			}
			
			else if((my1>-80)&&(my1<-25)&&((mx1)<40)&&((mx1)>-40)){//���� �̵�											
				upmoves(tx,ty);confirmUp(tx,ty);
				if((isUpmove1==false)&&(isUpmove2==false)){
					try{Thread.sleep(100);returnup(tx,ty);}
					catch(InterruptedException e){}
				}
				else if((isUpmove1==true)&&(isUpmove2==true)){
					pangsound.startPlay();
					Upmove1(tx,ty);Upmove2(tx,ty);blank=true;
				}
				else if(isUpmove1==true){
					pangsound.startPlay();
					Upmove1(tx,ty);blank=true;
				}
				else if(isUpmove2==true){
					pangsound.startPlay();
					Upmove2(tx,ty);blank=true;
				}
			}
			
			else if((my1>25)&&(my1<80)&&((mx1)<40)&&((mx1)>-40)){//�Ʒ��� �̵�	
				downmoves(tx,ty);confirmDown(tx,ty);
				if((isDownmove1==false)&&(isDownmove2==false)){
					try{Thread.sleep(100);returndown(tx,ty);}
					catch(InterruptedException e){}
				}
				else if((isDownmove1==true)&&(isDownmove2==true)){
					pangsound.startPlay();Downmove1(tx,ty);
					Downmove2(tx,ty);blank=true;
				}
				else if(isDownmove1==true){
					pangsound.startPlay();Downmove1(tx,ty);blank=true;
				}
				else if(isDownmove2==true){
					pangsound.startPlay();Downmove2(tx,ty);blank=true;
				}
			}
		}
		//��ĭ ����
		if(blank==true){
		try{Thread.sleep(100);blankcheck();}
		catch(InterruptedException e){}
		}
		//ĳ���� ����
		if(create==true){
		try{Thread.sleep(100);createcharacter();}
		catch(InterruptedException e){}
		}
		//������ Ȯ��
		if(fcheck==true){
			try{Thread.sleep(100);fieldcheck();}
			catch(InterruptedException e){}
		}
		//���� �������� Ȯ�� �������� ������ ���� ����
		if((fcheck==false)&&(blank==false)&&(create==false)&&(isboom==false)&&(isDragged==false))
			{nopangcheck();}
	}

}
