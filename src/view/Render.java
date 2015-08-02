package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import model.Cenario;

public class Render extends JFrame{
	private Cenario cenario;
	private BufferedImage tileSet;
	private BufferedImage mapa;

	public Render(Cenario cenario) {
		setSize(cenario.getWidth() * cenario.getTilewidth(),
				cenario.getHeight() * cenario.getTileheight());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		this.cenario = cenario;
		mapa = new BufferedImage(cenario.getWidth() * cenario.getTilewidth(),
				cenario.getHeight() * cenario.getTileheight(),
				BufferedImage.TYPE_4BYTE_ABGR);
		try {
			tileSet = ImageIO.read(this.getClass().getClassLoader()
					.getResource("imagens/tileset.png"));
		} catch (IOException e) {
			System.out
					.println("Erro ao buscar imagem do mapa.\nEncerrando aplicação");
			System.exit(0);
		}
		montaCenario();
		setVisible(true);
	}

	public void montaCenario() {
		for (int[][] camada : cenario.getCamadas()) {
			for (int i = 0; i < cenario.getHeight(); i++) {
				for (int j = 0; j < cenario.getWidth(); j++) {
					int tile = (camada[i][j] != 0) ? (camada[i][j] - 1) : 0;
					int tileRow = (tile / 8) | 0;
					int tileCol = (tile % 8) | 0;
					mapa.getGraphics().drawImage(
							tileSet,
							(j * cenario.getTilewidth()),
							(i * cenario.getTileheight()),
							(j * cenario.getTilewidth())
									+ cenario.getTilewidth(),
							(i * cenario.getTileheight())
									+ cenario.getTileheight(),
							(tileCol * cenario.getTilewidth()),
							(tileRow * cenario.getTileheight()),
							(tileCol * cenario.getTilewidth())
									+ cenario.getTilewidth(),
							(tileRow * cenario.getTileheight())
									+ cenario.getTileheight(), null);
				}
			}
		}
	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(mapa, 0, 0, null);
	}

}
