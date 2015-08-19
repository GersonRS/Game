package control;

import java.util.ArrayList;

import model.Animation;
import model.Logic;
import model.MainLoop;
import model.Monster;
import model.MonsterGoblin;
import model.NPC;
import model.Player;
import model.Scenery;
import view.Render;
import view.Renderer;

public class ControlCenter implements LoopSteps {

	private MainLoop main;
	private Render render;
	private Logic logic;
	private Player player;
	private ArrayList<Monster> monsters;
	private ArrayList<Animation> animations;
	private ArrayList<NPC> npcs;
	private Scenery scenery;

	public ControlCenter() {
		main = new MainLoop(this, 60);
		npcs = new ArrayList<NPC>();
		monsters = new ArrayList<Monster>();
		animations = new ArrayList<Animation>();
		player = new Player(100, 100, 23, 55, "personagem", "gerson");
		scenery = new Scenery();
		new Thread(main).start();
	}

	@Override
	public void setup() {
		scenery.carregaCenario("se.tmx");
		scenery.montarMatriz();
		render = new Renderer(player, monsters, npcs, scenery, animations);
		logic = new Logic(player, monsters, npcs, scenery, animations);
		render.loadLayers();
		carregarElementos();
	}

	@Override
	public void processLogics(int tick) {
		logic.trataJogo(tick);
	}

	@Override
	public void renderGraphics() {
		render.render();
	}

	@Override
	public void paintScreen() {
		render.paint();
	}

	@Override
	public void tearDown() {

	}

	private void carregarElementos() {
		for (int i = 0; i < 5; i++) {
			addMonster(i);
		}
		addAnimation();
	}

	private void addMonster(int i) {
		Monster monster = new MonsterGoblin(100 * i, 100 * i, 27, 57,
				"Monstro", "Goblim");
		monsters.add(monster);
	}

	private void addAnimation() {
		Animation animated = new Animation(0, 0, 64, 128, 10, false, 6,
				"coins100");
		animations.add(animated);
		animated = new Animation(150, 300, 64, 128, 10, true, 6, "coins100");
		animations.add(animated);
		animated = new Animation(200, 300, 64, 128, 10, true, 6, "coins25");
		animations.add(animated);
		animated = new Animation(250, 300, 64, 128, 10, true, 6, "coins5");
		animations.add(animated);
	}

	public static void main(String[] args) {
		new ControlCenter();
	}
}
