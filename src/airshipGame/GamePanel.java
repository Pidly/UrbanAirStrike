package airshipGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class GamePanel extends JFrame implements Runnable, KeyListener{
	Level level = new Level();
	AirShip airship = new AirShip();
	
	
	public final static int SCREENX = 1200;
	public final static int SCREENY = 800;
	
	Color cloudColor = new Color(255, 255, 255, 170);
	Color closeUpCloudColor = new Color(255, 255, 255, 120);
	Color sky = new Color(135, 206, 250);
	//Color airshipColor = new Color(0, 0, 0);
	
	public GamePanel() {
		super("Urban Air-Strike");
		setSize(SCREENX,SCREENY);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(this);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		
		g.setColor(sky);
		g.fillRect(level.background.x, level.background.y, level.background.width, level.background.height);
		
		g.setColor(cloudColor);
		for(int i = 0; i < level.cloud.length; i++){
			g.fillRect(level.cloud[i].x, level.cloud[i].y, level.cloud[i].width, level.cloud[i].height);
		}
		
		g.setColor(closeUpCloudColor);
		for(int i = 0; i < level.closeUpCloud.length; i++){
			g.fillRect(level.closeUpCloud[i].x, level.closeUpCloud[i].y, level.closeUpCloud[i].width, level.closeUpCloud[i].height);
		}
		
		g.setColor(Color.black);
		g.fillRect(airship.shipRect.x, airship.shipRect.y, airship.shipRect.width, airship.shipRect.height);
		
		
		if(!airship.bullets.isEmpty()){
			g.setColor(Color.red);
			for(Bullet b : airship.bullets){
				//System.out.println("Printing rectangle Paint Method.");
				g.fillRect(b.bulletPosition().x, b.bulletPosition().y, b.bulletPosition().width, b.bulletPosition().height);
				//System.out.println("b.bulletPosition.y" + b.bulletPosition().y);
				
			}
		}
		
	}
	
	@Override
	public void run() {
		repaint();
		level.updateCloudPosition();
		
		if(!airship.bullets.isEmpty()){	
			//System.out.println("run method:");
			for(Bullet b : airship.bullets){
				if(b.bullet == null)
					airship.bullets.remove(b);
				
			}
		}
	}
	
	public static void main(String args[]) throws Exception {
		GamePanel gp = new GamePanel();
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(gp, 0, 50, TimeUnit.MILLISECONDS);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_R){
			airship.shoot();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	
}
