package control;

import model.Cenario;
import model.MainLoop;
import view.Render;

public class ControleCentral implements LoopSteps{
	
	private MainLoop main;
	private Render render;
	private Cenario cenario;
	
	public ControleCentral() {
		main = new MainLoop(this,60);
		new Thread(main).start();
	}

	@Override
	public void setup() {
		cenario = new Cenario();
		cenario.carregaCenario("se.tmx");
		cenario.montarMatriz();
		render = new Render(cenario);
	}

	@Override
	public void processLogics() {
		
	}

	@Override
	public void renderGraphics() {
		render.repaint();
	}

	@Override
	public void paintScreen() {
		
	}

	@Override
	public void tearDown() {
		
	}
	public static void main(String[] args) {
		new ControleCentral();
	}
}
