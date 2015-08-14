package model;

import java.util.ArrayList;

public class Logica {

	private Player p;
//	private ArrayList<Monstro> monstros;
//	private ArrayList<NPC> npcs;
	private Cenario cenario;

	public Logica(Player p, ArrayList<Monstro> monstros,
			ArrayList<NPC> npcs, Cenario cenario) {
		super();
		this.p = p;
//		this.monstros = monstros;
//		this.npcs = npcs;
		this.cenario = cenario;
	}

	public void trataJogo(int tick) {
		moverJogador(tick);
	}

	private void moverJogador(int tick) {
		double posXAnterior = p.getPos().x;
		double posYAnterior = p.getPos().y;
		p.update(tick);
		
		tratarSairTela(p, posXAnterior, posYAnterior);
	}
	private void tratarSairTela(Entidade e,double posXAnterior, double posYAnterior) {

		if ((e.getPos().x < 0) || ((e.getPos().x + e.getPos().width) > cenario.getWidth()*cenario.getTilewidth())) {
			e.getPos().x = posXAnterior;
		}
		if ((e.getPos().y < 0) || ((e.getPos().y + e.getPos().height) > cenario.getHeight()*cenario.getTileheight())) {
			e.getPos().y = posYAnterior;
		}
	}
}
