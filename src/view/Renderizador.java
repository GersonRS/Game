package view;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Cenario;
import model.NPC;

public class Renderizador extends JPanel implements Render {

	private static final long serialVersionUID = 1L;
	private Cenario cenario;
	private BufferedImage tileSet;
	private BufferedImage ouro;
	private BufferedImage camadas[];
	private BufferedImage pintura;
	private BufferedImage tela;
	private int width = Config.WIDTH;
	private int height = Config.HEIGHT;
	private Graphics2D renderizador;
	private Graphics2D pincel;
	private PersonagemView p;
	private ArrayList<MonstroView> monstros;
	private volatile float alpha = 0.2f;

	public Renderizador(PersonagemView p, ArrayList<MonstroView> monstros,
			ArrayList<NPC> npcs, Cenario cenario) {
		this.p = p;
		this.monstros = monstros;
		this.cenario = cenario;
		setSize(width, height);
		setPreferredSize(new Dimension(width, height));
		setDoubleBuffered(true);
		setFocusable(true);
		requestFocus();
		setIgnoreRepaint(true);
		camadas = new BufferedImage[cenario.getCamadas().size()];
		pintura = new BufferedImage(
				cenario.getWidth() * cenario.getTilewidth(),
				cenario.getHeight() * cenario.getTileheight(),
				BufferedImage.TYPE_4BYTE_ABGR);
		tela = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		renderizador = (Graphics2D) pintura.getGraphics();
		pincel = (Graphics2D) tela.getGraphics();
		carregaTile();
		montaCamadas();
	}

	private void carregaTile() {
		try {
			tileSet = ImageIO.read(getClass().getClassLoader().getResource(
					"imagens/tileset.png"));
			ouro = ImageIO.read(getClass().getClassLoader().getResource(
					"imagens/ourinho3.png"));
		} catch (IOException e) {
			System.out
					.println("Erro ao buscar imagem do tileset.\nEncerrando aplicação");
			System.exit(0);
		}
	}

	public void paintComponent(Graphics g) {
		g.drawImage(tela, 0, 0, null);
	}

	@Override
	public void montaCamadas() {
		int a = 0;
		for (int[][] camada : cenario.getCamadas()) {
			camadas[a] = new BufferedImage(cenario.getWidth()
					* cenario.getTilewidth(), cenario.getHeight()
					* cenario.getTileheight(), BufferedImage.TYPE_4BYTE_ABGR);
			for (int i = 0; i < cenario.getHeight(); i++) {
				for (int j = 0; j < cenario.getWidth(); j++) {
					int tile = (camada[i][j] != 0) ? (camada[i][j] - 1) : 0;
					int tileRow = (tile / 8) | 0;
					int tileCol = (tile % 8) | 0;
					camadas[a].getGraphics().drawImage(
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
		renderizador.drawImage(camadas[0], 0, 0, null);
		renderizador.drawImage(camadas[1], 0, 0, null);
		renderizador.drawImage(ouro, 100, 100, null);
		renderizador.drawImage(p.getImagem(), p.getX(), p.getY(),
				p.getX() + p.getWidth(), p.getY() + p.getHeight(),
				(int) (p.getAnimates() % 6) * p.getWidth(),
				p.getDirecao() * p.getHeight(),
				(int) (p.getAnimates() % 6 * p.getWidth()) + p.getWidth(),
				(p.getDirecao() * p.getHeight()) + p.getHeight(), null);
		for (MonstroView monstro : monstros) {
			renderizador.drawImage(
					monstro.getImagem(),
					monstro.getX(),
					monstro.getY(),
					monstro.getX() + monstro.getWidth(),
					monstro.getY() + monstro.getHeight(),
					(int) (monstro.getAnima() % 6) * monstro.getWidth(),
					monstro.getDirecao() * monstro.getHeight(),
					(int) (monstro.getAnima() % 6 * monstro.getWidth())
							+ monstro.getWidth(),
					(monstro.getDirecao() * monstro.getHeight())
							+ monstro.getHeight(), null);
		}
		renderizador.drawImage(camadas[2], 0, 0, null);
	}

	@Override
	public void pinta() {
		pincel.drawImage(pintura, 0, 0, null);
		alpha = 0.5f;
		pincel.setComposite(AlphaComposite.SrcOver.derive(alpha));
		pincel.drawImage(pintura, 640, 0, (int) (width * 0.20),
				(int) (height * 0.20), null);
		alpha = 1.0f;
		pincel.setComposite(AlphaComposite.SrcOver.derive(alpha));
		pincel.drawRect(639, 0, 160, 115);
		repaint();
	}
}