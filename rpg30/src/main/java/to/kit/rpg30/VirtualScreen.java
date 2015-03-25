package to.kit.rpg30;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;

public final class VirtualScreen {
	public static final int SCREEN_WIDTH = 640;
	public static final int SCREEN_HEIGHT = 480;
	private static final int CHAR_WIDTH = 8;
	private static final int CHAR_HEIGHT = 16;
	private static final int CLIP_X = CHAR_WIDTH * 2;
	private static final Font DEFAULT_FONT = new Font(Font.DIALOG, Font.PLAIN, 14);

	private BufferedImage image = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int startRow;
	private int scrollRow;
	private int x;
	private int y;

	public VirtualScreen() {
		cls();
	}

	public void console(int start, int rows) {
		this.startRow = start;
		this.scrollRow = rows;
	}

	public BufferedImage getImage() {
		return this.image;
	}

	public void cls() {
		Graphics2D g2d = this.image.createGraphics();

		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		g2d.setColor(Color.WHITE);
	}

	/**
	 * PUT@ (x, y), KANJI(c).
	 * @param p (x, y)
	 * @param code JIS code
	 */
	public void put(Point p, int code) {
		String str = N88.jisToString(code);

		this.x = p.x;
		this.y = p.y;
		print(str);
	}

	/**
	 * ファンクションキー表示.
	 * PUT@ (x, y), KANJI(c).
	 * @param p (x, y)
	 * @param kanji String
	 */
	public void put(Point p, String kanji) {
		char[] data = kanji.toCharArray();
		Graphics g = this.image.getGraphics();
		g.setFont(DEFAULT_FONT);
		FontMetrics metrics = g.getFontMetrics();
		int width = metrics.charsWidth(data, 0, data.length);
		g.setColor(Color.WHITE);
		g.fillRect(p.x, p.y, width, CHAR_HEIGHT);
		g.setColor(Color.BLACK);
		g.drawString(kanji, p.x + 2, p.y + metrics.getHeight() / 2 + 1);
	}

	public void put(int bx, int by, byte[] img) {
		Image n88img = N88.toImage(img);
		Graphics g = this.image.getGraphics();

		g.drawImage(n88img, bx, by, null);
	}
	public void put(int bx, int by, byte[][] img) {
		Image n88img = N88.toImage(img);
		Graphics g = this.image.getGraphics();

		g.drawImage(n88img, bx, by, null);
	}

	public void locate(int lx, int ly) {
		this.x = lx * CHAR_WIDTH;
		this.y = ly * CHAR_HEIGHT;
	}

	public void print(String text, Align align) {
		Graphics g = this.image.getGraphics();

		g.setFont(DEFAULT_FONT);
		FontMetrics metrics = g.getFontMetrics();
		int dx = 0;
		if (align == Align.RIGHT) {
			char[] data = text.toCharArray();
			dx = metrics.charsWidth(data, 0, data.length);
		}
		int ly = this.y / CHAR_HEIGHT;
		if (this.scrollRow <= ly) {
			scroll(g);
			this.y = (this.scrollRow - 1) * CHAR_HEIGHT;
		}
		g.drawString(text, this.x - dx, this.y + metrics.getHeight() / 2 + 1);
		this.y += CHAR_HEIGHT;
	}
	public void print(int val, Align align) {
		print(String.valueOf(val), align);
	}
	public void print(String text) {
		if (text.indexOf('\n') != -1) {
			for (String str : text.split("\\n")) {
				print(str, Align.LEFT);
			}
			return;
		}
		print(text, Align.LEFT);
	}

	private void scroll(Graphics g) {
		int py = (this.startRow + 1) * CHAR_HEIGHT;
		int width = SCREEN_WIDTH - CHAR_WIDTH * 4;
		int dy = CHAR_HEIGHT;
		int height = (this.scrollRow - this.startRow - 1) * CHAR_HEIGHT;

		clipScroll(g);
		g.copyArea(CLIP_X, py, width, height, 0, -dy);
		py = (this.scrollRow - 1) * CHAR_HEIGHT;
		clearRect(CLIP_X, py, width, CHAR_HEIGHT - 1);
	}

	private void clipScroll(Graphics g) {
		int py = this.startRow * CHAR_HEIGHT;
		int width = SCREEN_WIDTH - CHAR_WIDTH * 4;
		int height = (this.scrollRow - this.startRow ) * CHAR_HEIGHT;
//		Color old = g2d.getColor();
//		g2d.setColor(Color.RED);
//		g2d.drawRect(CLIP_X, py, width, height);
//		g2d.setColor(old);
		g.setClip(CLIP_X, py, width, height);
	}

	public void line(Point s, Point e, Color c) {
		Graphics g = this.image.getGraphics();

		g.setColor(c);
		g.drawLine(s.x, s.y, e.x, s.y);
		g.drawLine(e.x, s.y, e.x, e.y);
		g.drawLine(e.x, e.y, s.x, e.y);
		g.drawLine(s.x, e.y, s.x, s.y);
	}

	public void clearRect(int px, int py, int width, int height) {
		Graphics g = this.image.getGraphics();
		Color old = g.getColor();

		g.setColor(Color.BLACK);
//		g2d.setColor(Color.BLUE);
		g.fillRect(px, py, width, height);
		g.setColor(old);
	}
}
