package control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import model.Cenario;
import model.Logica;
import model.MainLoop;
import model.Monstro;
import model.NPC;
import model.Jogador;
import view.Janela;
import view.MonstroView;
import view.PersonagemView;
import view.Renderizador;

public class ControleCentral extends KeyAdapter implements LoopSteps{
	
	private MainLoop main;
	private Renderizador render;
	private Logica logica;
	private Jogador p;
	private PersonagemView pView;
	private ArrayList<Monstro> monstros;
	private ArrayList<MonstroView> monstrosView;
	private ArrayList<NPC> npcs;
	private Cenario cenario;
	
	public ControleCentral() {
		main = new MainLoop(this,10);
		new Thread(main).start();
	}

	@Override
	public void setup() {
		p = new Jogador(100, 100, 23, 55, "personagem.png", 20, "gerson");
		pView = new PersonagemView(p);
		cenario = new Cenario();
		monstros = new ArrayList<Monstro>();
		monstrosView = new ArrayList<MonstroView>();
		npcs = new ArrayList<NPC>();
		cenario.carregaCenario("se.tmx");
		cenario.montarMatriz();
		render = new Renderizador(pView, monstrosView, npcs, cenario);
		render.addKeyListener(this);
		logica = new Logica(p,monstros,npcs,cenario);
		new Janela(render);
	}

	@Override
	public void processLogics() {
		logica.trataJogo();
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
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP)
			p.setCima(true);
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
			p.setBaixo(true);
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
			p.setEsquerda(true);
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
			p.setDireita(true);
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP)
			p.setCima(false);
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
			p.setBaixo(false);
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
			p.setEsquerda(false);
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
			p.setDireita(false);
	}
}
