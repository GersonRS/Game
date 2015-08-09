package model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Cenario extends Entidade {

	private int tileWidth;
	private int tileHeight;
	private String orientation;
	private String encoding;
	private String compression;
	private ArrayList<int[][]> camadas;
	private ArrayList<String> datas;

	public Cenario() {
		this.datas = new ArrayList<String>();
		this.camadas = new ArrayList<int[][]>();
	}

	public void montarMatriz() {
		try {
			for (String data : datas) {
				if (data != null && data.length() > 0) {
					int camada[][] = new int[height][width];
					StringTokenizer linhas = new StringTokenizer(data, "\n");
					int i = 0;
					while (linhas.hasMoreTokens()) {
						StringTokenizer colunas = new StringTokenizer(
								linhas.nextToken(), ",");
						int j = 0;
						while (colunas.hasMoreTokens()) {
							camada[i][j] = Integer
									.parseInt(colunas.nextToken());
							j++;
						}
						i++;
					}
					camadas.add(camada);
					// print(camada);
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	public static void print(int[][] maze) {
		int height = maze.length;
		int width = maze[0].length;

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(maze[i][j] + "  ");
			}
			System.out.println();
		}
	}

	public void carregaCenario(String resource) {
		InputStream is = getClass().getClassLoader().getResourceAsStream(
				"cenarios/" + resource);
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			factory.setValidating(false);
			DocumentBuilder builder = factory.newDocumentBuilder();
			builder.setEntityResolver(new EntityResolver() {
				public InputSource resolveEntity(String publicId,
						String systemId) throws SAXException, IOException {
					return new InputSource(
							new ByteArrayInputStream(new byte[0]));
				}
			});

			Document doc = builder.parse(is);
			Element docElement = doc.getDocumentElement();

			this.orientation = docElement.getAttribute("orientation");

			this.width = Integer.parseInt(docElement.getAttribute("width"));
			this.height = Integer.parseInt(docElement.getAttribute("height"));
			this.tileWidth = Integer.parseInt(docElement
					.getAttribute("tilewidth"));
			this.tileHeight = Integer.parseInt(docElement
					.getAttribute("tileheight"));
			NodeList layerNodes = docElement.getElementsByTagName("layer");
			for (int i = 0; i < layerNodes.getLength(); i++) {
				Element current = (Element) layerNodes.item(i);
				Element dataNode = (Element) current.getElementsByTagName(
						"data").item(0);
				Node cdata = dataNode.getFirstChild();
				String data = cdata.getNodeValue().trim();
				this.datas.add(data);
				this.encoding = dataNode.getAttribute("encoding");
				this.compression = dataNode.getAttribute("compression");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(width*tileWidth<800 && height*tileHeight<576){
				System.out.println("erro");
			}
		}

	}

	public int getTilewidth() {
		return tileWidth;
	}

	public void setTilewidth(int tilewidth) {
		this.tileWidth = tilewidth;
	}

	public int getTileheight() {
		return tileHeight;
	}

	public void setTileheight(int tileheight) {
		this.tileHeight = tileheight;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getCompression() {
		return compression;
	}

	public void setCompression(String compression) {
		this.compression = compression;
	}

	public ArrayList<int[][]> getCamadas() {
		return camadas;
	}
}
