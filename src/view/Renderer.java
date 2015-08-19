package view;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;

import model.Animation;
import model.InputManager;
import model.Monster;
import model.NPC;
import model.Player;
import model.Scenery;

public class Renderer implements Render {

	protected JFrame mainWindow;
	private BufferStrategy bufferStrategy;

	private Scenery scene;
	private BufferedImage layers[];
	private BufferedImage screen;
	private BufferedImage miniMap;
	private Graphics2D draws;
	private int width = Config.WIDTH;
	private int height = Config.HEIGHT;
	private Player player;
	private ArrayList<Monster> monsters;
	private ArrayList<Animation> animations;

	public Renderer(Player p, ArrayList<Monster> monsters, ArrayList<NPC> npcs,
			Scenery scene, ArrayList<Animation> animation) {
		this.player = p;
		this.monsters = monsters;
		this.scene = scene;
		this.animations = animation;
		mainWindow = new JFrame("Desenvolvimento de Jogos Digitais");
		mainWindow.setSize(width, height);
		mainWindow.addKeyListener(InputManager.getInstance());
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setUndecorated(true);
		mainWindow.setIgnoreRepaint(true);
		mainWindow.setVisible(true);
		mainWindow.createBufferStrategy(2);
		mainWindow.setPreferredSize(new Dimension(width, height));
		mainWindow.setFocusable(true);
		mainWindow.requestFocus();

		bufferStrategy = mainWindow.getBufferStrategy();

		layers = new BufferedImage[scene.getCamadas().size()];
		screen = new BufferedImage(scene.getWidth() * scene.getTilewidth(),
				scene.getHeight() * scene.getTileheight(),
				BufferedImage.TYPE_4BYTE_ABGR);
		miniMap = new BufferedImage(
				(int) ((scene.getWidth() * scene.getTilewidth()) * 0.40),
				(int) ((scene.getHeight() * scene.getTileheight()) * 0.40),
				BufferedImage.TYPE_4BYTE_ABGR);
		draws = (Graphics2D) screen.getGraphics();
		uploadImages();
	}

	private void uploadImages() {
		ImageManager.getInstance().loadImage("tileset");
		ImageManager.getInstance().loadImage("Monstro");
		ImageManager.getInstance().loadImage("personagem");
		ImageManager.getInstance().loadImage("coins100");
		ImageManager.getInstance().loadImage("coins25");
		ImageManager.getInstance().loadImage("coins5");
	}

	@Override
	public void loadLayers() {
		int a = 0;
		for (int[][] camada : scene.getCamadas()) {
			layers[a] = new BufferedImage(scene.getWidth()
					* scene.getTilewidth(), scene.getHeight()
					* scene.getTileheight(), BufferedImage.TYPE_4BYTE_ABGR);
			for (int i = 0; i < scene.getHeight(); i++) {
				for (int j = 0; j < scene.getWidth(); j++) {
					int tile = (camada[i][j] != 0) ? (camada[i][j] - 1) : 0;
					int tileRow = (tile / 8) | 0;
					int tileCol = (tile % 8) | 0;
					layers[a].getGraphics()
							.drawImage(
									ImageManager.getInstance().getImage(
											"tileset"),
									(j * scene.getTilewidth()),
									(i * scene.getTileheight()),
									(j * scene.getTilewidth())
											+ scene.getTilewidth(),
									(i * scene.getTileheight())
											+ scene.getTileheight(),
									(tileCol * scene.getTilewidth()),
									(tileRow * scene.getTileheight()),
									(tileCol * scene.getTilewidth())
											+ scene.getTilewidth(),
									(tileRow * scene.getTileheight())
											+ scene.getTileheight(), null);
				}
			}
			a++;
		}
	}

	@Override
	public void render() {
		draws.drawImage(layers[0], 0, 0, null);
		draws.drawImage(layers[1], 0, 0, null);
		draws.drawImage(
				ImageManager.getInstance().getImage(player.getImage()),
				(int) (player.getPos().x),
				(int) (player.getPos().y),
				(int) (player.getPos().x + player.getPos().width),
				(int) (player.getPos().y + player.getPos().height),
				(int) ((player.getAnimates() % 6) * player.getPos().width),
				(int) (player.getDirection() * player.getPos().height),
				(int) (((player.getAnimates() % 6) * player.getPos().width) + player
						.getPos().width),
				(int) ((player.getDirection() * player.getPos().height) + player
						.getPos().height), null);
		for (Monster monster : monsters) {
			draws.drawImage(
					ImageManager.getInstance().getImage(monster.getImage()),
					(int) (monster.getPos().x),
					(int) (monster.getPos().y),
					(int) (monster.getPos().x + monster.getPos().width),
					(int) (monster.getPos().y + monster.getPos().height),
					(int) ((monster.getAnimates() % 4) * monster.getPos().width),
					(int) (monster.getDirection() * monster.getPos().height),
					(int) (((monster.getAnimates() % 4) * monster.getPos().width) + monster
							.getPos().width),
					(int) ((monster.getDirection() * monster.getPos().height) + monster
							.getPos().height), null);
		}
		draws.drawImage(
				ImageManager.getInstance().getImage(player.getImage()),
				(int) (player.getPos().x),
				(int) (player.getPos().y),
				(int) (player.getPos().x + player.getPos().width),
				(int) (player.getPos().y + player.getPos().height / 2),
				(int) ((player.getAnimates() % 6) * player.getPos().width),
				(int) (player.getDirection() * player.getPos().height),
				(int) (((player.getAnimates() % 6) * player.getPos().width) + player
						.getPos().width),
				(int) ((player.getDirection() * player.getPos().height) + player
						.getPos().height / 2), null);
		for (Animation animation : animations) {
			draws.drawImage(
					ImageManager.getInstance().getImage(animation.getImage()),
					(int) (animation.getPos().x),
					(int) (animation.getPos().y),
					(int) (animation.getPos().x + animation.getPos().width),
					(int) (animation.getPos().y + animation.getPos().height),
					(int) ((animation.getCurrentImageIndex() % animation
							.getImageSize()) * animation.getPos().width),
					0,
					(int) (((animation.getCurrentImageIndex() % animation
							.getImageSize()) * animation.getPos().width) + animation
							.getPos().width), (int) animation.getPos().height,
					null);
		}
		draws.drawImage(layers[2], 0, 0, null);
	}

	@Override
	public void paint() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.drawImage(screen, (int) scene.getPos().x, (int) scene.getPos().y,
				null);
		g.drawImage(miniMap, 640, 0, null);
		g.drawRect(639, 0, 160, 115);
		g.dispose();
		bufferStrategy.show();
	}
}