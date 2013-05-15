package airshipGame;
import java.awt.List;
import java.awt.Rectangle;
import java.util.ArrayList;


public class AirShip{
	int shipX;
	int shipY;
	int hitPoints;
	public static Rectangle shipRect;
	
	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
	AirShip(){
		shipX = 600;
		shipY = 400;
		shipRect = new Rectangle(shipX,shipY,30,50);
	}
	
	public void moveUp(){
		shipRect.y--;
	}
	public void moveDown(){
		shipRect.y++;
	}
	public void moveLeft(){
		shipRect.x--;
	}
	public void moveRight(){
		shipRect.x++;
	}

	public void shoot(){
		
		bullets.add(new Bullet());
		
		System.out.println("bullets size: " + bullets.size());
		for(Bullet b : bullets){
			b.startThread();
		}
	}
}

class Bullet implements Runnable{
	Thread t;
	Rectangle bullet = new Rectangle(AirShip.shipRect.x, AirShip.shipRect.y, 4, 30);
	
	Bullet(){
		
	}
	
	public void startThread(){
		t = new Thread(this);
		if(!t.isAlive()){
			System.out.println("Thread state: " + t.getState());
			t.start();
			System.out.println("Thread state after t.start. " + t.getState());
		}
	}

	@Override
	public void run() {
		System.out.println("Run");
		while(bullet.y < -30){
			bullet.y = bullet.y - 4;
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("bullet y: " + bullet.y);
			
			if(bullet.y < -40){
				bullet = null;
				System.out.println("Killing thread.");
			}
			
		}
	}
	
	
	public Rectangle bulletPosition(){
		return(bullet);
	}
}













