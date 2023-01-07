package lolpang3;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window extends JFrame {
	private Image screenImage;
	private Graphics screenGraphic;
	private JLabel menubar =new JLabel(new ImageIcon(Main.class.getResource("../Images/menubar.png")));
	private JButton exitbutton = new JButton( new ImageIcon(Main.class.getResource("../Images/xbutton.png")));
	
	private Image background= new ImageIcon(Main.class.getResource("../Images/zed.jpg")).getImage();
	private Image RankingImage= new ImageIcon(Main.class.getResource("../Images/Ranking.jpg")).getImage();
	private Image cong= new ImageIcon(Main.class.getResource("../Images/cong.png")).getImage();
	private Image YourScore= new ImageIcon(Main.class.getResource("../Images/Your Scroe.png")).getImage();
	
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/startButtonEntered.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));
	private ImageIcon mainbackImage = new ImageIcon(Main.class.getResource("../images/메인화면.png"));
	private ImageIcon mainbackImage2 = new ImageIcon(Main.class.getResource("../images/메인화면2.png"));
	private ImageIcon restartGame = new ImageIcon(Main.class.getResource("../images/게임다시하기.png"));
	private ImageIcon restartGame2 = new ImageIcon(Main.class.getResource("../images/게임다시하기2.png"));
	
	boolean isFirstScreen=true;
	boolean isGame=true;
	
	int x,y;
	int pointX =38;
	int pointY= 245;// ĳ���� ��ġ
	int mouseX,mouseY;
	int charSizeX = 80;
	int charSizeY= 80;//ĳ���� ������
	
	EffectMusic gamebgm = new EffectMusic("1.wav");
	EffectMusic buttonEntered = new EffectMusic("buttonEnteredMusic.wav");
	EffectMusic buttonPressed = new EffectMusic("buttonPressedMusic.wav");
	EffectMusic introbgm= new EffectMusic("introMusic.wav");
	
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private JButton backButton = new JButton(mainbackImage);
	private JButton restartButton = new JButton(restartGame);
	
	Timebar Timebar=new Timebar();
	GameScreen GameScreen=new GameScreen();
	
	public Window(){
		introbgm.startPlay();
		addMouseListener(new Mouse());
		addMouseMotionListener(new Mouse2());
		setTitle("LoL Pang");
		setSize(Main.SCREEN_WIDTH,Main.SCREEN_Height);
		setResizable(false); // ũ������ x
		setLocationRelativeTo(null); //â�� ���߾ӿ� �߰Բ����� 
		setLayout(null);

		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// x��ư�� ������ ���� ����ǰ� �� ����
		setVisible(true);
		setBackground(new Color(0,0,0,0));
		
		startButton.setBounds(125, 580, 400, 100);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				buttonEntered.startPlay();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				introbgm.stopPlayer();
				buttonPressed.startPlay();
				startButton.setVisible(false);
				quitButton.setVisible(false);
				Timebar.gamestart=true;
				isFirstScreen=false;
				gamebgm.startPlay();
			}
		});
		add(startButton);
		
		exitbutton.setBounds(596,0,34,30);
		exitbutton.setBorderPainted(false);
		exitbutton.setContentAreaFilled(false);
		exitbutton.setFocusPainted(false);
		exitbutton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(exitbutton);
		
		quitButton.setBounds(125, 700, 400, 100);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				buttonEntered.startPlay();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				buttonPressed.startPlay();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(quitButton);
		
		menubar.setBounds(0,0,860,30);
		menubar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				buttonPressed.startPlay();
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menubar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menubar);
		

		backButton.setBounds(30, 750, 234, 79);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(mainbackImage2);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				buttonEntered.startPlay();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(mainbackImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				buttonPressed.startPlay();
				Timebar.RankingScreen=false;
				backButton.setVisible(false);
				restartButton.setVisible(false);
				startButton.setVisible(true);
				quitButton.setVisible(true);
				isFirstScreen=true;
				GameScreen.n_score=0;
				Timebar.endMusic.stopPlayer();
			}
		});
		
		restartButton.setBounds(380, 750, 234, 79);
		restartButton.setBorderPainted(false);
		restartButton.setContentAreaFilled(false);
		restartButton.setFocusPainted(false);
		restartButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				restartButton.setIcon(restartGame2);
				restartButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				buttonEntered.startPlay();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				restartButton.setIcon(restartGame);
				restartButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				buttonPressed.startPlay();
				Timebar.RankingScreen=false;
				Timebar.gamestart=true;
				backButton.setVisible(false);
				restartButton.setVisible(false);
				GameScreen.n_score=0;
				Timebar.endMusic.stopPlayer();
				gamebgm.startPlay();
				
				for(x=0;x<=6;x++)// ������ ���鶧 �ٽ� �����
					for(y=0;y<=6;y++){
				{
						do{
							GameScreen.randchar[x][y] = (int)(Math.random()*7);
						}while(((x>=2)&&(GameScreen.randchar[x-1][y]==GameScreen.randchar[x][y])&&(GameScreen.randchar[x][y]==GameScreen.randchar[x-2][y]))||
						((y>=2)&&(GameScreen.randchar[x][y-1]==GameScreen.randchar[x][y])&&(GameScreen.randchar[x][y]==GameScreen.randchar[x][y-2])));
					}
				}
			}
		});
	}
	
	//���� ���콺 �̺�Ʈ
	public class Mouse extends MouseAdapter{
		public void mousePressed(MouseEvent e){
			GameScreen.mouseX=e.getX();
			GameScreen.mouseY=e.getY();
			//tx= x�� ��ǥ
			if((38<=GameScreen.mouseX) &&(GameScreen.mouseX<118))
				GameScreen.tx=0;
			else if((118<=GameScreen.mouseX) && (GameScreen.mouseX<198))
				GameScreen.tx=1;
			else if((198<=GameScreen.mouseX) && (GameScreen.mouseX<278))
				GameScreen.tx=2;
			else if((278<=GameScreen.mouseX) && (GameScreen.mouseX<358))
				GameScreen.tx=3;	
			else if((358<=GameScreen.mouseX) && (GameScreen.mouseX<438))
				GameScreen.tx=4;
			else if((438<=GameScreen.mouseX) && (GameScreen.mouseX<518))
				GameScreen.tx=5;
			else if((518<=GameScreen.mouseX) && (GameScreen.mouseX<598))
				GameScreen.tx=6;
			//ty= y�� ��ǥ
			if((245<=GameScreen.mouseY)&&(GameScreen.mouseY<325))
				GameScreen.ty=0;
			else if((325<=GameScreen.mouseY)&&(GameScreen.mouseY<405))
				GameScreen.ty=1;
			else if((405<=GameScreen.mouseY)&&(GameScreen.mouseY<485))
				GameScreen.ty=2;
			else if((485<=GameScreen.mouseY)&&(GameScreen.mouseY<565))
				GameScreen.ty=3;
			else if((565<=GameScreen.mouseY)&&(GameScreen.mouseY<645))
				GameScreen.ty=4;
			else if((645<=GameScreen.mouseY)&&(GameScreen.mouseY<725))
				GameScreen.ty=5;
			else if((725<=GameScreen.mouseY)&&(GameScreen.mouseY<=805))
				GameScreen.ty=6;
			GameScreen.ispress=true;
			
		}
		public void mouseReleased(MouseEvent e){
			GameScreen.isDragged=false;GameScreen.ispress=false;
		}
	}
	public class Mouse2 extends MouseMotionAdapter{
		public void mouseDragged(MouseEvent s){
			GameScreen.mx=s.getX();
			GameScreen.my=s.getY();
			GameScreen.mx1=GameScreen.mx-GameScreen.mouseX;
			GameScreen.my1=GameScreen.my-GameScreen.mouseY;
			GameScreen.isDragged=true;
		}

	}
	
	
		
	public void paint(Graphics g){
		screenImage = createImage(Main.SCREEN_WIDTH,Main.SCREEN_Height);
		screenGraphic =screenImage.getGraphics();
		screenDraw((Graphics2D)screenGraphic);
		g.drawImage(screenImage,0,0,null);
	}
	
	public void screenDraw(Graphics2D g){
		screenGraphic =screenImage.getGraphics();
		g.drawImage(screenImage,0,0,null);
		
		if(isFirstScreen==true){//�ʱ�ȭ��
		g.drawImage(background,0,0,null);
		}
		if(Timebar.gamestart==true){
			isFirstScreen=false;
		if(Timebar.time==0)
			{gamebgm.stopPlayer();}
		
		if(Timebar.gamestart==true){//����ȭ��
			GameScreen.screenDraw(g);
			Timebar.screenDraw(g);//Ÿ�ӹ�
			for(y=6;y>=0;y--){//ĳ���� ���
				for(x=0;x<=6;x++){	
					g.drawImage(GameScreen.character.get(GameScreen.randchar[x][y]).getImage(),pointX+charSizeX*x,pointY+charSizeY*y,null);
				}
			}		
		}
		g.setFont(new Font("Elephant",Font.BOLD,50)); 
		g.drawString(""+GameScreen.n_score,265,220);
		
		}
		
		//��ŷȭ��
		if(Timebar.RankingScreen==true){
			Timebar.gamestart=false;
			Timebar.time=70;
			GameScreen.boom=0;
			g.drawImage(RankingImage,0,0,null);
			g.drawImage(cong,0,0,null);
			g.drawImage(YourScore,130,100,null);
			g.setFont(new Font("Elephant",Font.BOLD,60)); 
			g.setFont(new Font("Elephant",Font.BOLD,50)); 
			g.drawString(""+GameScreen.n_score,250,280);
			add(backButton);
			backButton.setVisible(true);
			add(restartButton);
			restartButton.setVisible(true);
		}
		
		 repaint();
		 paintComponents(g);
	}
}
