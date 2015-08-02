package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Cenario;

public class Renderizador extends JPanel implements Render {

	private static final long serialVersionUID = 1L;
	private Cenario cenario;
	private BufferedImage tileSet;
	private BufferedImage mapa[];
	private BufferedImage tela;

	public Renderizador(Cenario cenario) {
		this.cenario = cenario;
		this.setSize(Config.WIDTH, Config.HEIGHT);
		this.setPreferredSize(new Dimension(Config.WIDTH, Config.HEIGHT));
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.requestFocus();
		this.setIgnoreRepaint(true);
		this.mapa = new BufferedImage[cenario.getCamadas().size()];
		this.tela = new BufferedImage(Config.WIDTH, Config.HEIGHT,
				BufferedImage.TYPE_4BYTE_ABGR);
		carregaTile();
		montaCamadas();
	}

	private void carregaTile() {
		try {
			tileSet = ImageIO.read(this.getClass().getClassLoader()
					.getResource("imagens/tileset.png"));
		} catch (IOException e) {
			System.out
					.println("Erro ao buscar imagem do tileset.\nEncerrando aplicação");
			System.exit(0);
		}
	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(tela, 0, 0, null);
	}

	@Override
	public void montaCamadas() {
		int a = 0;
		for (int[][] camada : cenario.getCamadas()) {
			mapa[a] = new BufferedImage(cenario.getWidth()
					* cenario.getTilewidth(), cenario.getHeight()
					* cenario.getTileheight(), BufferedImage.TYPE_4BYTE_ABGR);
			for (int i = 0; i < cenario.getHeight(); i++) {
				for (int j = 0; j < cenario.getWidth(); j++) {
					int tile = (camada[i][j] != 0) ? (camada[i][j] - 1) : 0;
					int tileRow = (tile / 8) | 0;
					int tileCol = (tile % 8) | 0;
					mapa[a].getGraphics().drawImage(
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
			a++;
		}
	}

	@Override
	public void renderiza() {
		Graphics2D g = (Graphics2D) tela.getGraphics();
		g.drawImage(mapa[0], 0, 0, null);
		g.drawImage(mapa[1], 0, 0, null);
		g.drawImage(mapa[2], 0, 0, null);
	}

	@Override
	public void pinta() {
		this.repaint();
	}
}
