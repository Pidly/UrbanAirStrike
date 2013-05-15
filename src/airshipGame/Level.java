package airshipGame;

import java.awt.Rectangle;
import java.util.Random;

public class Level {
	//Load background image and scroll it down
	Rectangle background;
	Rectangle[] cloud;
	Rectangle[] closeUpCloud;
	int speed;
	Random randomGenerator = new Random();
	int levelLayer;
	int randomYvalue;
	class Cloud {
		
		private Rectangle rect;
		//public void update(int x, int y);
	}
	
	Level(){
		background = new Rectangle(0,0,GamePanel.SCREENX,GamePanel.SCREENY);
		
		cloud = new Rectangle[10];
		closeUpCloud = new Rectangle[3];
		
		for(int i = 0; i < cloud.length; i++){
			cloud[i] = new Rectangle(randomGenerator.nextInt(GamePanel.SCREENX), randomGenerator.nextInt(GamePanel.SCREENY), 100, 100);
		}
		
		for(int i = 0; i < closeUpCloud.length; i++){
			closeUpCloud[i] = new Rectangle(randomGenerator.nextInt(GamePanel.SCREENX), randomGenerator.nextInt(GamePanel.SCREENY), 300, 300);
		}
		
	}
	
	public void updateCloudPosition(){
		
		for(int i = 0; i < cloud.length; i++){
			cloud[i].y += 4;
			if(cloud[i].y > GamePanel.SCREENY){
				randomYvalue = randomGenerator.nextInt(50);
				cloud[i].y = ((-cloud[i].height) - randomYvalue);
				cloud[i].x = randomGenerator.nextInt(GamePanel.SCREENX);
			}
		
		}
		for(int i = 0; i < closeUpCloud.length; i++){
			closeUpCloud[i].y += 6;
			if(closeUpCloud[i].y > GamePanel.SCREENY){
				randomYvalue = randomGenerator.nextInt(500);
				closeUpCloud[i].y = ((-closeUpCloud[i].height) - randomYvalue);
				closeUpCloud[i].x = randomGenerator.nextInt(GamePanel.SCREENX);
			}
		}
		
	}
}
