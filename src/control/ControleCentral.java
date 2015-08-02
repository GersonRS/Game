package control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import model.Cenario;
import model.Logica;
import model.MainLoop;
import view.Janela;
import view.Renderizador;

public class ControleCentral extends KeyAdapter implements LoopSteps{
	
	private MainLoop main;
	private Renderizador render;
	private Cenario cenario;
	private Logica logica;
	
	public ControleCentral() {
		main = new MainLoop(this,60);
		new Thread(main).start();
	}

	@Override
	public void setup() {
		cenario = new Cenario();
		cenario.carregaCenario("se.tmx");
		cenario.montarMatriz();
		render = new Renderizador(cenario);
		render.addKeyListener(this);
		logica = new Logica();
		new Janela(render);
	}

	@Override
	public void processLogics() {
		logica.movePersonagem();
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
	}
}
