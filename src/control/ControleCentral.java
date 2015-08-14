package control;

import java.util.ArrayList;

import model.Cenario;
import model.InputManager;
import model.Player;
import model.Logica;
import model.MainLoop;
import model.Monstro;
import model.NPC;
import view.Janela;
import view.MonstroView;
import view.PersonagemView;
import view.Renderizador;

public class ControleCentral implements LoopSteps{
	
	private MainLoop main;
	private Renderizador render;
	private Logica logica;
	private Player p;
	private PersonagemView pView;
	private ArrayList<Monstro> monstros;
	private ArrayList<MonstroView> monstrosView;
	private ArrayList<NPC> npcs;
	private Cenario cenario;
	
	public ControleCentral() {
		main = new MainLoop(this,60);
		new Thread(main).start();
	}

	@Override
	public void setup() {
		p = new Player(100, 300, 23, 55, "personagem.png", "gerson");
		pView = new PersonagemView(p);
		cenario = new Cenario();
		monstros = new ArrayList<Monstro>();
		monstrosView = new ArrayList<MonstroView>();
		npcs = new ArrayList<NPC>();
		cenario.carregaCenario("se.tmx");
		cenario.montarMatriz();
		render = new Renderizador(pView, monstrosView, npcs, cenario);
		render.addKeyListener(InputManager.getInstance());
		logica = new Logica(p,monstros,npcs,cenario);
		new Janela(render);
	}

	@Override
	public void processLogics(int tick) {
		logica.trataJogo(tick);
	}

	@Override
	public void renderGraphics() {
		render.renderiza();
	}

	@Override
	public void paintScreen() {
		render.pinta();
	}

	@Override
	public void tearDown() {
		
	}
	public static void main(String[] args) {
		new ControleCentral();
	}
}
