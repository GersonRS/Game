package model;

import java.util.ArrayList;

public class Logica {

	private Jogador p;
//	private ArrayList<Monstro> monstros;
//	private ArrayList<NPC> npcs;
	private Cenario cenario;
	private enum Direcao {
		CIMA, DIREITA, BAIXO, ESQUERDA
	}

	public Logica(Jogador p, ArrayList<Monstro> monstros,
			ArrayList<NPC> npcs, Cenario cenario) {
		super();
		this.p = p;
//		this.monstros = monstros;
//		this.npcs = npcs;
		this.cenario = cenario;
	}

	public void trataJogo() {
		moverJogador();
	}

	private void moverJogador() {
		int posXAnterior = p.getX();
		int posYAnterior = p.getY();

		if (p.isCima()) {
			p.mover(Direcao.CIMA.ordinal());
		}
		if (p.isBaixo()) {
			p.mover(Direcao.BAIXO.ordinal());
		}
		if (p.isDireita()) {
			p.mover(Direcao.DIREITA.ordinal());
		}
		if (p.isEsquerda()) {
			p.mover(Direcao.ESQUERDA.ordinal());
		}
		
		tratarSairTela(p, posXAnterior, posYAnterior);
	}
	private void tratarSairTela(Entidade e,int posX, int posY) {

		if ((e.getX() < 0) || ((e.getX() + e.getWidth()) > cenario.getWidth()*cenario.getTilewidth())) {
			e.setX(posX);
		}
		if ((e.getY() < 0) || ((e.getY() + e.getHeight()) > cenario.getHeight()*cenario.getTileheight())) {
			e.setY(posY);
		}
	}
}
