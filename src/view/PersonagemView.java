package view;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import model.Player;

public class PersonagemView {

	private Player p;
	private BufferedImage imagem;

	public PersonagemView(Player p) {
		this.p = p;
		try {
			imagem = ImageIO.read(this.getClass().getClassLoader()
					.getResource("imagens/"+p.getImagem()));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar imagem do "+p.getImagem()+".\nEncerrando aplicação");
			System.exit(0);
		}
	}

	public BufferedImage getImagem() {
		return imagem;
	}

	public int getX() {
		return (int) p.getPos().x;
	}

	public int getY() {
		return (int) p.getPos().y;
	}

	public int getWidth() {
		return (int) p.getPos().width;
	}

	public int getHeight() {
		return (int) p.getPos().height;
	}
	
	public int getDirecao() {
		return p.getDirection();
	}
	
	public int getAnimates() {
		return p.getAnimates();
	}
}
