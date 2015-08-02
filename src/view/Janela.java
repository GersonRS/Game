package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Janela extends JFrame {
	private static final long serialVersionUID = 5313726042349955929L;

	public Janela(JPanel p) {
		this.setSize(p.getWidth(), p.getHeight());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.add(p);
		this.setVisible(true);
	}
}
