package view;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import model.Monstro;

public class MonstroView {

	
	private Monstro m;
	private BufferedImage imagem;

	public MonstroView(Monstro m) {
		this.m = m;
		try {
			imagem = ImageIO.read(this.getClass().getClassLoader()
					.getResource("imagens/"+m.getImagem()));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar imagem do "+m.getImagem()+".\nEncerrando aplicação");
			System.exit(0);
		}
	}

	public BufferedImage getImagem() {
		return imagem;
	}

	public int getX() {
		return (int) m.getPos().x;
	}

	public int getY() {
		return (int) m.getPos().y;
	}

	public int getWidth() {
		return (int) m.getPos().width;
	}

	public int getHeight() {
		return (int) m.getPos().height;
	}
	
	public int getDirecao() {
		return 1;
	}
	
	public int getAnima() {
		return m.getAnimates();
	}
}
