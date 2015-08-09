package model;


public final class Jogador extends Personagem {

	private int strength;
	private int intelligence;
	private int agility;
	private int dexterity;
	private int hp, hpMax;
	private int mp, mpMax;
	private String name;
	private int nivel;
	private int exp, expMax;
	private boolean cima,baixo,direita,esquerda;

	public Jogador() {
	}

	public Jogador(int x, int y, int width, int height, String imagem,
			int velocidade, String name) {
		super(x, y, width, height, imagem, velocidade);
		this.name = name;
		this.nivel = 1;
		this.expMax = 100;
		this.hpMax = 100;
		this.mpMax = 100;
	}

	@Override
	public void mover(int novaDirecao) {
		estado = novaDirecao;
		anima++;
		switch (novaDirecao) {
		case 0:
			y -= (velocidade * MainLoop.temp) / 100;
			break;
		case 1:
			x += (velocidade * MainLoop.temp) / 100;
			break;
		case 2:
			y += (velocidade * MainLoop.temp) / 100;
			break;
		case 3:
			x -= (velocidade * MainLoop.temp) / 100;
			break;
		}
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getDexterity() {
		return dexterity;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getHpMax() {
		return hpMax;
	}

	public void setHpMax(int hpMax) {
		this.hpMax = hpMax;
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public int getMpMax() {
		return mpMax;
	}

	public void setMpMax(int mpMax) {
		this.mpMax = mpMax;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getExpMax() {
		return expMax;
	}

	public void setExpMax(int expMax) {
		this.expMax = expMax;
	}

	public boolean isCima() {
		return cima;
	}

	public void setCima(boolean cima) {
		this.cima = cima;
	}

	public boolean isBaixo() {
		return baixo;
	}

	public void setBaixo(boolean baixo) {
		this.baixo = baixo;
	}

	public boolean isDireita() {
		return direita;
	}

	public void setDireita(boolean direita) {
		this.direita = direita;
	}

	public boolean isEsquerda() {
		return esquerda;
	}

	public void setEsquerda(boolean esquerda) {
		this.esquerda = esquerda;
	}
	
}
