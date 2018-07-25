package snakegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakeMain extends JPanel implements ActionListener {
	
	public static final int CellSize = 32; //������ 1 ������
	public static final int WIDTH = 20;    //������ 
	public static final int HEIGHT = 20;   // ������
	public static final int SPEED = 10;
	
	Apple a = new Apple ((int) (Math.random()*WIDTH), (int) Math.random()*HEIGHT);
	Snake s = new Snake(10,10,9,10); // ��������� �������
	Timer timer = new Timer(1000/SPEED,this);

	public SnakeMain(){  //�����������                                
		timer.start();
		addKeyListener(new Keyboard());
		setFocusable(true);
	}
	
	public void paint (Graphics g){
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, WIDTH*CellSize, HEIGHT*CellSize);
		g.setColor(Color.GRAY);
		for (int x = 0; x <= WIDTH*CellSize; x+=CellSize){ //������������
			g.drawLine(x,0,x,HEIGHT*CellSize);
		}
		
		for (int y = 0; y <= HEIGHT*CellSize; y+=CellSize){ //��������������
			g.drawLine(0,y,WIDTH*CellSize,y);
		}
		
		g.setColor(Color.RED); //apple
		g.fillRect(a.posX*CellSize+3,a.posY*CellSize+3, CellSize-6, CellSize-6);
		
		for (int d =0; d < s.length; d++){ //������
			g.setColor(Color.BLACK);
			g.fillRect(s.snakeX[d]*CellSize+3, s.snakeY[d]*CellSize+3, CellSize-6, CellSize-6);
			g.setColor(Color.BLUE);
			g.fillRect(s.snakeX[0]*CellSize+3, s.snakeY[0]*CellSize+3, CellSize-6, CellSize-6);
		}		
	}
	/*
	public void paint (Graphics g){
		g.setColor(color(5,150,10)); //RGB 
		g.fillRect(0, 0, WIDTH*CellSize, HEIGHT*CellSize); //��� ���� 
		g.setColor(color(255,216,0));
		
		for (int x = 0; x <= WIDTH*CellSize; x+=CellSize){ //������������
			g.drawLine(x,0,x,HEIGHT*CellSize);
		}
		
		for (int y = 0; y <= HEIGHT*CellSize; y+=CellSize){ //��������������
			g.drawLine(0,y,WIDTH*CellSize,y);
		}
	}
	
	public Color color (int red, int green, int blue){
		return new Color(red, green, blue);
	}
	*/
	
	public static void main(String[] args) {
		JFrame f = new JFrame();                          //������� ����
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�������� ����
		f.setResizable(false);                            //false - ������ �������� ������ ����
		f.setSize(WIDTH*CellSize+7, HEIGHT*CellSize+30);  //������ ����
		f.setLocationRelativeTo(null);                    //���������� ���� �� ������ 
		f.add(new SnakeMain());
		f.setVisible(true);                               //����������� ���� (true - ������, false - �� ������)
	}

	@Override
	public void actionPerformed(ActionEvent arg0) { //�������� ������
		s.move();
		
		if ((s.snakeX[0] == a.posX) & (s.snakeY[0] == a.posY)){ // eat apple
			a.SetRandomPosition();
			s.length++;
		}
		
		for (int k = 1; k < s.length; k++){
			if ((s.snakeX[k] == a.posX) & (s.snakeY[k] == a.posY)){ // ��������� ������ �� �����
				a.SetRandomPosition();
			}
			
			if ((s.snakeX[0] == s.snakeX[k]) && (s.snakeY[0] == s.snakeY[k])){ //����� ����
				timer.stop();
				JOptionPane.showMessageDialog(null, "���� ��������. ������ ������?");
				s.length = 2;
				a.SetRandomPosition();
				timer.start();
			}			
		}	
		repaint();
	}
	
	private class Keyboard extends KeyAdapter{
		public void keyPressed(KeyEvent kevt){
			int key = kevt.getExtendedKeyCode(); //��������� ���� ������
			
			if ((key == KeyEvent.VK_RIGHT) & s.direction != 2) s.direction = 0; //0 - ������
			if ((key == KeyEvent.VK_DOWN) & s.direction != 3) s.direction = 1; // 1 - ����
			if ((key == KeyEvent.VK_LEFT) & s.direction != 0) s.direction = 2; // 2 - �����
			if ((key == KeyEvent.VK_UP) & s.direction != 1) s.direction = 3; //   3 - �����
			if (key == KeyEvent.VK_SPACE){
				timer.stop();
				JOptionPane.showMessageDialog(null, "�����");
				timer.start();
			}	
		}
	}
}
