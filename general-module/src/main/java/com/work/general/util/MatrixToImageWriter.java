package com.work.general.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;


public class MatrixToImageWriter {

	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;

	private MatrixToImageWriter() {
	}

	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}

	public static BufferedImage writeToFile(BitMatrix matrix, String format, File file) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		if (!ImageIO.write(image, format, file)) {
			throw new IOException("Could not write an image of format " + format + " to " + file);
		}
		return image;
	}

	public static BufferedImage writeToFile(BitMatrix matrix, String format) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		return image;
	}

	public static void writeToStream(BitMatrix matrix, String format, OutputStream stream) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		if (!ImageIO.write(image, format, stream)) {
			throw new IOException("Could not write an image of format " + format);
		}
	}

	public static BufferedImage addLogo_QRCode(BufferedImage image, File logoPic, LogoConfig logoConfig) {
		try {
			if (!logoPic.isFile()) {
				System.out.print("file not find !");
				throw new IOException("file not find !");
			}
			/**
			 * 读取二维码图片，并构建绘图对象
			 */
			Graphics2D g = image.createGraphics();

			/**
			 * 读取Logo图片
			 */
			BufferedImage logo = ImageIO.read(logoPic);

			int widthLogo = image.getWidth() / logoConfig.getLogoPart();
			int heightLogo = image.getWidth() / logoConfig.getLogoPart(); // 保持二维码是正方形的

			// 计算图片放置位置
			int x = (image.getWidth() - widthLogo) / 2;
			int y = (image.getHeight() - heightLogo) / 2;

			// 开始绘制图片
			g.drawImage(logo, x, y, widthLogo, heightLogo, null);
			g.drawRoundRect(x, y, widthLogo, heightLogo, 10, 10);
			g.setStroke(new BasicStroke(logoConfig.getBorder()));
			g.setColor(logoConfig.getBorderColor());
			g.drawRect(x, y, widthLogo, heightLogo);

			g.dispose();
			return image;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @为图片添加文字
	 * @param pressText
	 *            文字
	 * @param newImg
	 *            带文字的图片
	 * @param targetImg
	 *            需要添加文字的图片
	 * @param fontStyle
	 * @param color
	 * @param fontSize
	 * @param width
	 * @param heigh
	 */
	public static void pressText(String pressText, String newImg, BufferedImage image, int fontStyle, Color color,
								 int fontSize, int width, int height) {

		// //计算文字开始的位置
		// //x开始的位置：（图片宽度-字体大小*字的个数）/2
		// int startX = (width-(fontSize*pressText.length()))/2;
		// //y开始的位置：图片高度-（图片高度-图片宽度）/2
		// int startY = height-(height-width)/2;

		// 计算文字开始的位置
		// x开始的位置：（图片宽度-字体大小*字的个数）/2
		int startX = (width - (fontSize * pressText.length())) - 50;
		// y开始的位置：图片高度-（图片高度-图片宽度）/2
		int startY = height - (height - width) / 2;

		try {
			int imageW = image.getWidth(null);
			int imageH = image.getHeight(null);
			Graphics g = image.createGraphics();
			g.drawImage(image, 0, 0, imageW, imageH, null);
			g.setColor(color);
			g.setFont(new Font("宋体", fontStyle, fontSize));
			g.drawString(pressText, startX, startY);
			g.dispose();

			FileOutputStream out = new FileOutputStream(newImg);
			ImageIO.write(image, "JPEG", out);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
			out.close();
			System.out.println("image press success");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	static class LogoConfig {
		// logo默认边框颜色
		public static final Color DEFAULT_BORDERCOLOR = Color.WHITE;
		// logo默认边框宽度
		public static final int DEFAULT_BORDER = 2;
		// logo大小默认为照片的1/6
		public static final int DEFAULT_LOGOPART = 4;

		private final int border = DEFAULT_BORDER;
		private final Color borderColor;
		private final int logoPart;

		/**
		 * Creates a default config with on color {@link #BLACK} and off color
		 * {@link #WHITE}, generating normal black-on-white barcodes.
		 */
		public LogoConfig() {
			this(DEFAULT_BORDERCOLOR, DEFAULT_LOGOPART);
		}

		public LogoConfig(Color borderColor, int logoPart) {
			this.borderColor = borderColor;
			this.logoPart = logoPart;
		}

		public Color getBorderColor() {
			return borderColor;
		}

		public int getBorder() {
			return border;
		}

		public int getLogoPart() {
			return logoPart;
		}

	}


	/**
	 * 只生成二维码
	 */
	public static void M1() {
		try {
			//二维码表示的内容
			String content = "http://www.cnblogs.com/";
			// 目标文件
			String targetImage = "D:/M1.jpg";

			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

			@SuppressWarnings("rawtypes")
			Map hints = new HashMap();

			//设置UTF-8， 防止中文乱码
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			//设置二维码四周白色区域的大小
			hints.put(EncodeHintType.MARGIN,1);
			//设置二维码的容错性
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

			//width:图片完整的宽;height:图片完整的高
			//因为要在二维码下方附上文字，所以把图片设置为长方形（高大于宽）
			int width = 400;
			int height = 450;

			//画二维码，记得调用multiFormatWriter.encode()时最后要带上hints参数，不然上面设置无效
			BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);

			//qrcFile用来存放生成的二维码图片（无logo，无文字）
			File qrcFile = new File(targetImage);

			//开始画二维码
			MatrixToImageWriter.writeToFile(bitMatrix, "jpg", qrcFile);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 二维码+logo
	 */
	public static void M2() {
		try {
			//二维码表示的内容
			String content = "http://www.cnblogs.com/";
			// logo地址
			String logopath = "D:/logo.jpg";
			// 目标文件
			String targetImage = "D:/M2.jpg";


			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

			@SuppressWarnings("rawtypes")
			Map hints = new HashMap();

			//设置UTF-8， 防止中文乱码
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			//设置二维码四周白色区域的大小
			hints.put(EncodeHintType.MARGIN,1);
			//设置二维码的容错性
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

			//width:图片完整的宽;height:图片完整的高
			//因为要在二维码下方附上文字，所以把图片设置为长方形（高大于宽）
			int width = 400;
			int height = 450;

			//画二维码，记得调用multiFormatWriter.encode()时最后要带上hints参数，不然上面设置无效
			BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);

			//logoFile用来存放带有logo的二维码图片（二维码+logo，无文字）
			File logoFile = new File(logopath);

			//开始画二维码
			BufferedImage barCodeImage = MatrixToImageWriter.writeToFile(bitMatrix, "jpg");

			//在二维码中加入图片
			LogoConfig logoConfig = new LogoConfig(); //LogoConfig中设置Logo的属性
			BufferedImage image = addLogo_QRCode(barCodeImage, logoFile, logoConfig);
			ImageIO.write(image, "jpeg", new File(targetImage));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 二维码+logo+下标文字
	 */
	public static void M3() {
		try {
			// 二维码表示的内容
			String content = "http://www.cnblogs.com/";
			// logo地址
			String logopath = "D:/logo.jpg";
			// 目标文件
			String targetImage = "D:/M3.jpg";
			// 附加在图片上的文字信息
			String text = "123123123";

			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

			Map hints = new HashMap();

			// 设置UTF-8， 防止中文乱码
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			// 设置二维码四周白色区域的大小
			hints.put(EncodeHintType.MARGIN, 1);
			// 设置二维码的容错性
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

			// width:图片完整的宽;height:图片完整的高
			// 因为要在二维码下方附上文字，所以把图片设置为长方形（高大于宽）
			int width = 400;
			int height = 450;

			// 画二维码，记得调用multiFormatWriter.encode()时最后要带上hints参数，不然上面设置无效
			BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);

			// logo图片路径
			File logoFile = new File(logopath);

			// 开始画二维码
			BufferedImage barCodeImage = MatrixToImageWriter.writeToFile(bitMatrix, "jpg");

			// 在二维码中加入图片
			LogoConfig logoConfig = new LogoConfig(); // LogoConfig中设置Logo的属性
			BufferedImage image = addLogo_QRCode(barCodeImage, logoFile, logoConfig);

			int font = 25; // 字体大小
			int fontStyle = 1; // 字体风格

			// 在二维码下方添加文字（文字居中）
			pressText(text, targetImage, image, fontStyle, Color.BLACK, font, width, height);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//只生成二维码
		MatrixToImageWriter.M1();
		//二维码+logo
		MatrixToImageWriter.M2();
		//二维码+logo+下标文字
		MatrixToImageWriter.M3();
	}

}
