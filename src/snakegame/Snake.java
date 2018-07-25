package snakegame;


public class Snake {
	
	SnakeMain main;
	
	public int direction = 0; //движение змеи
	public int length = 2; //начальная длина
	
	public int snakeX [] = new int [main.WIDTH*main.HEIGHT];
	public int snakeY [] = new int [main.WIDTH*main.HEIGHT];
	
	public Snake (int x0,int y0,int x1,int y1){ //стартовые позиции первых 2 элементов массива
		snakeX[0]=x0;
		snakeY[0]=y0;
		snakeX[1]=x1;
		snakeY[1]=y1;
	}
	
	public void move(){
		for (int d = length; d > 0; d--){ //движение за головой
			snakeX[d] = snakeX[d-1];  
			snakeY[d] = snakeY[d-1];
		}
		
		if (direction == 0) snakeX[0]++;
		if (direction == 1) snakeY[0]++;
		if (direction == 2) snakeX[0]--;
		if (direction == 3) snakeY[0]--;
		
		if (snakeX[0] > SnakeMain.WIDTH -1 ) //переходы
			snakeX[0] = 0;
		if (snakeX[0] < 0 )
			snakeX[0] = SnakeMain.WIDTH -1;
		if (snakeY[0] > SnakeMain.HEIGHT -1 ) 
			snakeY[0] = 0;
		if (snakeY[0] < 0 )
			snakeY[0] = SnakeMain.HEIGHT -1;
		
		
	}	
}
