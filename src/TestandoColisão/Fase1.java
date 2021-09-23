package TestandoColis�o;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Fase1 extends JPanel implements ActionListener {
	private Image fundo0, fundo1, porta, bau;
	private Jogador jogador;
	private int larguraFrame = 600, alturaFrame = 310;
	private int xB = 200, yB = 190, alturaB = 34, larguraB = 34, xP = 260, yP = 0, alturaP = 73, larguraP = 60;
	private Timer timer;

	private boolean colidiu = false, mudarPet = true;

	public Fase1() {
		setFocusable(true);
		setDoubleBuffered(true);

		ImageIcon fundinho0 = new ImageIcon("white.png");
		fundo0 = fundinho0.getImage();
		
//		ImageIcon fundinho1 = new ImageIcon("camada1.png");
//		fundo1 = fundinho1.getImage();
		
		ImageIcon fundinho2 = new ImageIcon("camada2.png");
		porta = fundinho2.getImage();

		ImageIcon bauzinho = new ImageIcon("bauOpen.png");
		bau = bauzinho.getImage();

		jogador = new Jogador();
		jogador.carregar();

		addKeyListener(new Teclado());

		timer = new Timer(5, this);
		timer.start();

	}

	public void paint(Graphics g) {
		Graphics2D grafico = (Graphics2D) g;
		grafico.drawImage(fundo0, 0, 0, 600, 310, this);
//		grafico.drawImage(fundo1, 0, 0, 300, 300, this);
		grafico.drawImage(porta, xP, yP, larguraP, alturaP, this);
		grafico.drawImage(bau, xB, yB, larguraB, alturaB, this);
		grafico.drawImage(jogador.getImgPlayer(), jogador.getX(), jogador.getY(), jogador.getLargura(), jogador.getAltura(), this);
		grafico.drawImage(jogador.getImgPet(), jogador.getXB(), jogador.getYB(), 32, 32, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (jogador.getX() + jogador.getLargura() >= larguraFrame - 14) {
			jogador.setX(jogador.getX() - 2);
		} else if (jogador.getX() <= 0) {
			jogador.setX(jogador.getX() + 2);
		} else if (jogador.getY() <= 0) {
			jogador.setY(jogador.getY() + 2);
		} else if (jogador.getY() + jogador.getLargura() >= alturaFrame - 36) {
			jogador.setY(jogador.getY() - 2);
		}

		int aX = jogador.getX();
		int aY = jogador.getY();
		int ladoDireitoA = aX + jogador.getLargura() - 12;
		int ladoEsquerdoA = aX + 12;
		int ladoBaixoA = aY + jogador.getAltura() - 2;
		int ladoCimaA = aY + 35;

		int bX = xB;
		int bY = yB;
		int ladoDireitoB = bX + larguraB;
		int ladoEsquerdoB = bX;
		int ladoBaixoB = bY + alturaB;
		int ladoCimaB = bY;
		
		int cX = xP;
		int cY = yP;
		int ladoDireitoC = cX + larguraP;
		int ladoEsquerdoC = cX;
		int ladoBaixoC = cY + alturaP;
		int ladoCimaC = cY;
		
		// COLISAO COM A PORTA
		if (ladoDireitoA >= ladoEsquerdoC && ladoDireitoA < ladoEsquerdoC + 6 && ladoBaixoA >= ladoCimaC
				&& ladoCimaA <= ladoBaixoC) {
			System.out.println("TELEPORTANDO");
		}else if (ladoEsquerdoA >= ladoDireitoC - 2 && ladoEsquerdoA <= ladoDireitoC && ladoBaixoA >= ladoCimaC
				&& ladoCimaA <= ladoBaixoC) {
			System.out.println("TELEPORTANDO");
		}else if (ladoDireitoA >= ladoEsquerdoC && ladoEsquerdoA <= ladoDireitoC && ladoBaixoA >= ladoCimaC
				&& ladoBaixoA <= ladoBaixoC) {
			System.out.println("TELEPORTANDO");
		}else if (ladoEsquerdoA <= ladoDireitoC && ladoDireitoA >= ladoEsquerdoC && ladoCimaA >= ladoBaixoC - 2 
				&& ladoCimaA <= ladoBaixoC) {
			System.out.println("TELEPORTANDO");
		}
		
		// COLISAO COM O BAU
		if (ladoDireitoA >= ladoEsquerdoB && ladoDireitoA < ladoEsquerdoB + 2 && ladoBaixoA >= ladoCimaB
				&& ladoCimaA <= ladoBaixoB) {
			jogador.setX(jogador.getX() - 2);
		}else if (ladoEsquerdoA >= ladoDireitoB - 2 && ladoEsquerdoA <= ladoDireitoB && ladoBaixoA >= ladoCimaB
				&& ladoCimaA <= ladoBaixoB) {
			jogador.setX(jogador.getX() + 2);
		}else if (ladoDireitoA >= ladoEsquerdoB && ladoEsquerdoA <= ladoDireitoB && ladoBaixoA >= ladoCimaB
				&& ladoBaixoA <= ladoBaixoB) {
			jogador.setY(jogador.getY() - 2);
		}else if (ladoEsquerdoA <= ladoDireitoB && ladoDireitoA >= ladoEsquerdoB && ladoCimaA >= ladoBaixoB - 2 
				&& ladoCimaA <= ladoBaixoB) {
			jogador.setY(jogador.getY() + 2);
		}
		
		if(jogador.isCima() ) {
			jogador.setYB(jogador.getY() + 40);
			jogador.setXB(jogador.getX());
			
		}else if(jogador.isBaixo()) {
			jogador.setYB(jogador.getY() - 25);
			jogador.setXB(jogador.getX());

		}else if(jogador.isDireita()) {
			jogador.setXB(jogador.getX() - 30);	 
			jogador.setYB(jogador.getY());
					
		}else if(jogador.isEsquerda()) {
			jogador.setXB(jogador.getX() + 40);
			jogador.setYB(jogador.getY());
		}
		
		jogador.atualizar();
		repaint();
	}

	private class Teclado extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			jogador.keyPressed(e);
		}

		public void keyReleased(KeyEvent e) {
			jogador.keyReleased(e);
		}
	}

}
