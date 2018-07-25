package snakegame;

public class Apple {
	
	SnakeMain main;
	
	public int posX;
	public int posY;
	
	public Apple(int startX, int startY){
		posX = startX;
		posY = startY;
	}
	
	public void SetRandomPosition(){
		posX = (int) (Math.random()*main.WIDTH); //округление до int
		posY = (int) (Math.random()*main.HEIGHT);
	}

}
