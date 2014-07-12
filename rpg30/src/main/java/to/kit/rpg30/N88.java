package to.kit.rpg30;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class N88 {
	/** パレット. */
	public static final Color[] PAL = { Color.BLACK, Color.BLUE, Color.RED,
			Color.PINK, Color.GREEN, Color.CYAN, Color.YELLOW, Color.WHITE,
			new Color(230, 230, 230), new Color(0, 0, 128), new Color(128, 0, 0),
			new Color(128, 0, 128), new Color(0, 128, 0),
			new Color(0, 128, 128), new Color(128, 128, 0), Color.LIGHT_GRAY };

	/**
	 * Convert to String from JIS code.
	 * @param jis JIS code
	 * @return String
	 */
	public static String jisToString(int jis) {
		int hi = (jis >> 8) -0x21;
		int hi7 = hi >> 1;
		int lo = jis & 0x00ff;

		// HI
		if (hi7 <= 0x1e) {
			hi7 += 0x81;
		} else {
			hi7 += 0xc1;
		}
		// LOW
		if (hi % 2 == 0) {
			lo += 0x1f;
		} else {
			lo += 0x7e;
		}
		if (0x7f <= lo) {
			lo++;
		}
		// Shift_JIS
		byte[] bytes = { (byte) hi7, (byte) lo };
		String str = null;
		try {
			str = new String(bytes, "Shift_JIS");
		} catch (UnsupportedEncodingException e) {
			// NOP
		}
		return str;
	}

	/**
	 * [N88-BASIC]<br/>
	 * 	EEEE EEEE SMMM MMMM MMMM MMMM MMMM MMMM<br/>
	 * [IEEE]<br/>
	 * 	SEEE EEEE EMMM MMMM MMMM MMMM MMMM MMMM<br/>
	 * @param bytes
	 * @return
	 */
	public static float toFloat(byte[] bytes) {
		int sign = bytes[2] & 0x0080;
		int lo = (bytes[3] & 0x0001) << 7;
		byte b2 = (byte) ((bytes[2] & 0x07f) | lo);
		int exponent = (((bytes[3] & 0x00ff) - 0x80) >> 1) + 0x3f;
		byte b3 = (byte) (exponent | sign);
//System.out.format("%02x\n", Byte.valueOf(bytes[3]));
//System.out.format("%02x\n", Byte.valueOf((byte) (bytes[3] >>> 1)));
//System.out.format("%02x\n", Byte.valueOf((byte) exponent));

		byte[] buff = { b3, b2, bytes[1], bytes[0] };
		return ByteBuffer.wrap(buff).getFloat();
	}

	/**
	 * Get short.
	 * @param bytes
	 * @param pos
	 * @return
	 */
	public static int toShort(byte[] bytes, int pos) {
		byte[] buff = { bytes[pos], bytes[pos + 1] };

		return ByteBuffer.wrap(buff).order(ByteOrder.LITTLE_ENDIAN).getShort();
	}

	/**
	 * Get back N88-Image.
	 * @param img
	 * @return
	 */
	public static Image toImage(byte[] img) {
		int width = N88.toShort(img, 0);
		int height = N88.toShort(img, 2);
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = image.createGraphics();
		int matrix[][] = new int[height][width];
		int ix = 4;
		int w = width / 8;

		for (int py = 0; py < height; py++) {
			for (int layer = 0; layer < 4; layer++) {
				int weight = (int) Math.pow(2, layer);

				for (int px = 0; px < w; px++) {
					int ox = px * 8;
					byte b = img[ix];
					int mask = 0x0080;
					for (int bit = 0; bit < 8; bit++) {
						if (0 < (b & mask)) {
							matrix[py][ox + bit] |= weight;
						}
						mask = mask >> 1;
					}
					ix++;
				}
			}
		}
		for (int py = 0; py < height; py++) {
			for (int px = 0; px < width; px++) {
				int pal = matrix[py][px];

				g2d.setColor(PAL[pal]);
				g2d.drawLine(px, py, px, py);
			}
		}
		return image;
	}
	/**
	 * Get back N88-Image(640*400).
	 * @param img
	 * @return
	 */
	public static Image toImage(byte[][] img) {
		int width = 640;
		int height = 400;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = image.createGraphics();
		int matrix[][] = new int[height][width];
		int w = width / 8;

		for (int layer = 0; layer < 3; layer++) {
			int ix = 0;
			int weight = (int) Math.pow(2, layer);

			for (int py = 0; py < height; py++) {
				for (int px = 0; px < w; px++) {
					int ox = px * 8;
					byte b = img[layer][ix];
					int mask = 0x0080;
					for (int bit = 0; bit < 8; bit++) {
						if (0 < (b & mask)) {
							matrix[py][ox + bit] |= weight;
						}
						mask = mask >> 1;
					}
					ix++;
				}
			}
		}
		for (int py = 0; py < height; py++) {
			for (int px = 0; px < width; px++) {
				int pal = matrix[py][px];

				g2d.setColor(PAL[pal]);
				g2d.drawLine(px, py, px, py);
			}
		}
		return image;
	}

	//-------------------------------------------------------------------------
	/**
	 * For DEBUG.
	 * @param args
	 */
	public static void main(String[] args) {
		ByteBuffer buff = ByteBuffer.allocate(4);

		for (byte b : buff.putFloat(36.0f).array()) {
			System.out.format("%02x", Byte.valueOf(b));
		}
		System.out.println();
		buff.clear();
		for (byte b : buff.putFloat(35.0f).array()) {
			System.out.format("%02x", Byte.valueOf(b));
		}
	}
}
