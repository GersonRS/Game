package view;

import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class ImageManager {

	static private ImageManager instance;
	private HashMap<String, BufferedImage> images;

	private ImageManager() {
		images = new HashMap<String, BufferedImage>();
	}

	static public ImageManager getInstance() {
		if (instance == null) {
			instance = new ImageManager();
		}
		return instance;
	}
	
	public BufferedImage getImage(String img){
		return images.get(img);
	}

	public void loadImage(String fileName){
		URL url = getClass().getClassLoader().getResource("imagens/" + fileName + ".png");
		if (url == null) {
			throw new RuntimeException("A imagem /" + fileName
					+ " não foi encontrada.");
		} else {
			String path = url.getPath();
			if (images.containsKey(path)) {
				throw new RuntimeException("A imagem /" + fileName
						+ " ja foi carregada.");
			} else {
				BufferedImage img=null;
				try {
					img = ImageIO.read(url);
				} catch (IOException e) {
					e.printStackTrace();
				}
				images.put(fileName, img);
			}
		}
	}

	public BufferedImage loadImage(String fileName, int x, int y, int w, int h)
			throws IOException {
		BufferedImage sheet = getImage(fileName);
		BufferedImage img = sheet.getSubimage(x, y, w, h);
		return img;
	}

	public BufferedImage flipImage(BufferedImage image, boolean flipHorizontal,
			boolean flipVertical) {
		int w = image.getWidth();
		int h = image.getHeight();
		BufferedImage img = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice().getDefaultConfiguration()
				.createCompatibleImage(w, h, BufferedImage.BITMASK);
		if (flipHorizontal) {
			if (flipVertical) {
				img.getGraphics().drawImage(image, w, h, -w, -h, null);
			} else {
				img.getGraphics().drawImage(image, w, 0, -w, h, null);
			}
		} else if (flipVertical) {
			img.getGraphics().drawImage(image, 0, h, w, -h, null);
		} else {
			img.getGraphics().drawImage(image, 0, 0, w, h, null);
		}
		return img;
	}
}
