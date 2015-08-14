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

public class ControlCenter implements LoopSteps{
	
	private MainLoop main;
	private Render render;
	private Logic logica;
	private Player p;
	private ArrayList<Monster> monstros;
	private ArrayList<Animation> animation;
	private ArrayList<NPC> npcs;
	private Scenery cenario;
	
	public ControlCenter() {
		main = new MainLoop(this,60);
		npcs = new ArrayList<NPC>();
		monstros = new ArrayList<Monster>();
		animation = new ArrayList<Animation>();
		p = new Player(100, 100, 23, 55, "personagem", "gerson");
		cenario = new Scenery();
		new Thread(main).start();
	}

	@Override
	public void setup() {
		cenario.carregaCenario("se.tmx");
		cenario.montarMatriz();
		carregarElementos();
		render = new Renderer(p,monstros,npcs,cenario, animation);
		logica = new Logic(p,monstros,npcs,cenario, animation);
		render.loadLayers();
	}

	@Override
	public void processLogics(int tick) {
		logica.trataJogo(tick);
		update();
	}

	private void update() {
		
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
	
	private void carregarElementos(){
		for (int i = 0; i < 5; i++) {
			addMonster(i);
		}
		addAnimation();
	}
	
	private void addMonster(int i){
		Monster monster = new MonsterGoblin(100*i, 100*i, 27, 57, "Monstro", "Goblim");
		monstros.add(monster);
	}
	
	private void addAnimation(){
		Animation animated = new Animation(0, 0, 64, 128, 10, false, 6, "coins100");
		animation.add(animated);
		animated = new Animation(150, 300, 64, 128, 10, true, 6, "coins100");
		animation.add(animated);
		animated = new Animation(200, 300, 64, 128, 10, true, 6, "coins25");
		animation.add(animated);
		animated = new Animation(250, 300, 64, 128, 10, true, 6, "coins5");
		animation.add(animated);
	}
	
	public static void main(String[] args) {
		new ControlCenter();
	}
}
