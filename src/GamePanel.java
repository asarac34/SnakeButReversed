import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener{

	static final int SCREEN_WIDTH = 1300;
	static final int SCREEN_HEIGHT = 750;
	static final int UNIT_SIZE = 50;
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/(UNIT_SIZE*UNIT_SIZE);
	static final int DELAY = 175;
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int bodyParts = 7;
	int applesEaten;
	int appleX;
	int appleY;
	int apple1X;
	int apple1Y;
	int appleX2;
	int appleY2;
	int appleX3;
	int appleY3;
	int appleX4;
	int appleY4;
	int appleX5;
	int appleY5;
	char direction = 'R';
	boolean running = false;
	Timer timer;
	Random random;
	String[] color = {"blue","gray","pink","green","red","yellow"};
	Color[] colors = new Color[6];
	

	GamePanel(){
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}
	public void startGame() {
		newApple();

		running = true;
		timer = new Timer(DELAY,this);
		timer.start();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	public void draw(Graphics g) {
		
		if(running) {
			/*
			for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++) {
				g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
				g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
			}
			*/
			g.setColor(Color.red);
			g.fillRect(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
			g.setColor(Color.blue);
			g.fillRect(apple1X, apple1Y, UNIT_SIZE, UNIT_SIZE);
			g.setColor(Color.green);
			g.fillRect(appleX2, appleY2, UNIT_SIZE, UNIT_SIZE);
			g.setColor(Color.yellow);
			g.fillRect(appleX3, appleY3, UNIT_SIZE, UNIT_SIZE);
			g.setColor(Color.pink);
			g.fillRect(appleX4, appleY4, UNIT_SIZE, UNIT_SIZE);
			g.setColor(Color.gray);
			g.fillRect(appleX5, appleY5, UNIT_SIZE, UNIT_SIZE);
		
			for(int i = 0; i< bodyParts;i++) {
				if(i == 0) {
					g.setColor(Color.white);
					g.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			
				else if(i%6==1){		
					g.setColor(Color.red);
					g.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);}
				else if(i%6==2){		
					g.setColor(Color.blue);
					g.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);}
				else if(i%6==3){		
					g.setColor(Color.green);
					g.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);}
				else if(i%6==4){		
					g.setColor(Color.yellow);
					g.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);}
				else if(i%6==5){		
					g.setColor(Color.pink);
					g.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);}
				else if(i%6==0){		
					g.setColor(Color.gray);
					g.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);}
				
					
					/*- g.setColor(Color.red);
					g.fillRect(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
					g.setColor(Color.blue);
					g.fillRect(apple1X, apple1Y, UNIT_SIZE, UNIT_SIZE);
					g.setColor(Color.green);
					g.fillRect(appleX2, appleY2, UNIT_SIZE, UNIT_SIZE);
					g.setColor(Color.yellow);
					g.fillRect(appleX3, appleY3, UNIT_SIZE, UNIT_SIZE);
					g.setColor(Color.pink);
					g.fillRect(appleX4, appleY4, UNIT_SIZE, UNIT_SIZE);
					g.setColor(Color.white);
					g.fillRect(appleX5, appleY5, UNIT_SIZE, UNIT_SIZE);
				-*/
					
			
				
			}
			g.setColor(Color.red);
			g.setFont( new Font("Ink Free",Font.BOLD, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: "+applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
		}
		else {
			gameOver(g);
		}
		
	}
	public void newApple(){
		appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
		apple1X = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		apple1Y = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
		appleX2 = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		appleY2 = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
		appleX3 = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		appleY3 = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
		appleX4 = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		appleY4 = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
		appleX5 = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		appleY5 = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
	}
	public void move(){
		for(int i = bodyParts;i>0;i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		switch(direction) {
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;
		}
		
	}
	public void checkApple() {
		if((x[0] == appleX) && (y[0] == appleY) && (bodyParts-1)%6 == 0) {
			bodyParts++;
			applesEaten++;
			newApple();
		} else if((x[0] == appleX) && (y[0] == appleY)&& (bodyParts-1)%6 != 0) {
			running = false;
		}
		if((x[0] == apple1X) && (y[0] == apple1Y)&& (bodyParts-1)%6 == 1) {
			bodyParts++;
			applesEaten++;
			newApple();
		}else if((x[0] == apple1X) && (y[0] == apple1Y)&& (bodyParts-1)%6 != 1){
			running = false;}
		
		if((x[0] == appleX2) && (y[0] == appleY2)&& (bodyParts-1)%6 == 2) {
			bodyParts++;
			applesEaten++;
			newApple();
		}else if((x[0] == appleX2) && (y[0] == appleY2)&& (bodyParts-1)%6 != 2) {
			running = false;}
		if((x[0] == appleX3) && (y[0] == appleY3)&& (bodyParts-1)%6 == 3) {
			bodyParts++;
			applesEaten++;
			newApple();
		}else if((x[0] == appleX3) && (y[0] == appleY3)&& (bodyParts-1)%6 != 3){
			running = false;}
		if((x[0] == appleX4) && (y[0] == appleY4)&& (bodyParts-1)%6 == 4) {
			bodyParts++;
			applesEaten++;
			newApple();
		}else if((x[0] == appleX4) && (y[0] == appleY4)&& (bodyParts-1)%6 != 4) {
			running = false;}
		if((x[0] == appleX5) && (y[0] == appleY5)&& (bodyParts-1)%6 == 5) {
			bodyParts++;
			applesEaten++;
			newApple();
		}else if((x[0] == appleX5) && (y[0] == appleY5)&& (bodyParts-1)%6 != 5){
			running = false;}
		}
		
	
	public void checkCollisions() {
		//checks if head collides with body
		for(int i = bodyParts;i>0;i--) {
			if((x[0] == x[i])&& (y[0] == y[i])) {
				running = false;
				for(int b = 1; b<=i; b++) {
					if((x[b] == x[i])&& (y[b] == y[i])) {
						newApple();
					}
				}
			}
		}
		//check if head touches left border
		if(x[0] < 0) {
			running = false;
		}
		//check if head touches right border
		if(x[0] > SCREEN_WIDTH) {
			running = false;
		}
		//check if head touches top border
		if(y[0] < 0) {
			running = false;
		}
		//check if head touches bottom border
		if(y[0] > SCREEN_HEIGHT) {
			running = false;
		}
		
		if(!running) {
			timer.stop();
		}
	}
	public void gameOver(Graphics g) {
		//Score
		g.setColor(Color.red);
		g.setFont( new Font("Ink Free",Font.BOLD, 40));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Score: "+applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
		//Game Over text
		g.setColor(Color.red);
		g.setFont( new Font("Ink Free",Font.BOLD, 75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(running) {
			move();
			checkApple();
			checkCollisions();
		}
		repaint();
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
				}
				break;
			}
		}
	}
}