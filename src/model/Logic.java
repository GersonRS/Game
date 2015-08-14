package model;

import java.util.ArrayList;

public class Logic {

	private Player p;
	private ArrayList<Monster> monsters;
//	private ArrayList<NPC> npcs;
	private ArrayList<Animation> animations;
	private Scenery scene;

	public Logic(Player p, ArrayList<Monster> monsters,
			ArrayList<NPC> npcs, Scenery scene, ArrayList<Animation> animations) {
		super();
		this.p = p;
		this.monsters = monsters;
//		this.npcs = npcs;
		this.animations = animations;
		this.scene = scene;
	}

	public void trataJogo(int currentTick) {
		moverJogador(currentTick);
		moverMonstros(currentTick);
		animations(currentTick);
	}

	private void animations(int currentTick) {
		for (int i = 0; i < animations.size(); i++) {
			Animation a = (Animation) animations.get(i);
			if (a.isVisible()) {
				a.update(currentTick);
			} else {
				animations.remove(i);
				a = null;
			}
		}
	}

	private void moverMonstros(int currentTick) {
		for (Monster monster : monsters) {
			double posXAnterior = monster.getPos().x;
			double posYAnterior = monster.getPos().y;
			monster.update(currentTick);
			tratarSairTela(monster, posXAnterior, posYAnterior);
		}
	}


	private void moverJogador(int tick) {
		double posXAnterior = p.getPos().x;
		double posYAnterior = p.getPos().y;
		p.update(tick);
		
		tratarSairTela(p, posXAnterior, posYAnterior);
		
		if(p.getPos().x>=400 && p.getPos().x<=scene.getPos().width-400)
			scene.getPos().x-=p.getSpeed().x;
		
		if(p.getPos().y>=300 && p.getPos().y<=scene.getPos().height-300)
			scene.getPos().y-=p.getSpeed().y;
		
	}
	private void tratarSairTela(Entity e,double posXAnterior, double posYAnterior) {

		if ((e.getPos().x < 0) || ((e.getPos().x + e.getPos().width) > scene.getWidth()*scene.getTilewidth())) {
			e.getPos().x = posXAnterior;
		}
		if ((e.getPos().y < 0) || ((e.getPos().y + e.getPos().height) > scene.getHeight()*scene.getTileheight())) {
			e.getPos().y = posYAnterior;
		}
	}
}
