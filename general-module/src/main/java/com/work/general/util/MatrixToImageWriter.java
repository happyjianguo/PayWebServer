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
			 * ��ȡ��ά��ͼƬ����������ͼ����
			 */
			Graphics2D g = image.createGraphics();

			/**
			 * ��ȡLogoͼƬ
			 */
			BufferedImage logo = ImageIO.read(logoPic);

			int widthLogo = image.getWidth() / logoConfig.getLogoPart();
			int heightLogo = image.getWidth() / logoConfig.getLogoPart(); // ���ֶ�ά���������ε�

			// ����ͼƬ����λ��
			int x = (image.getWidth() - widthLogo) / 2;
			int y = (image.getHeight() - heightLogo) / 2;

			// ��ʼ����ͼƬ
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
	 * @ΪͼƬ�������
	 * @param pressText
	 *            ����
	 * @param newImg
	 *            �����ֵ�ͼƬ
	 * @param targetImg
	 *            ��Ҫ������ֵ�ͼƬ
	 * @param fontStyle
	 * @param color
	 * @param fontSize
	 * @param width
	 * @param heigh
	 */
	public static void pressText(String pressText, String newImg, BufferedImage image, int fontStyle, Color color,
			int fontSize, int width, int height) {

		// //�������ֿ�ʼ��λ��
		// //x��ʼ��λ�ã���ͼƬ���-�����С*�ֵĸ�����/2
		// int startX = (width-(fontSize*pressText.length()))/2;
		// //y��ʼ��λ�ã�ͼƬ�߶�-��ͼƬ�߶�-ͼƬ��ȣ�/2
		// int startY = height-(height-width)/2;

		// �������ֿ�ʼ��λ��
		// x��ʼ��λ�ã���ͼƬ���-�����С*�ֵĸ�����/2
		int startX = (width - (fontSize * pressText.length())) - 50;
		// y��ʼ��λ�ã�ͼƬ�߶�-��ͼƬ�߶�-ͼƬ��ȣ�/2
		int startY = height - (height - width) / 2;

		try {
			int imageW = image.getWidth(null);
			int imageH = image.getHeight(null);
			Graphics g = image.createGraphics();
			g.drawImage(image, 0, 0, imageW, imageH, null);
			g.setColor(color);
			g.setFont(new Font("����", fontStyle, fontSize));
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
		// logoĬ�ϱ߿���ɫ
		public static final Color DEFAULT_BORDERCOLOR = Color.WHITE;
		// logoĬ�ϱ߿���
		public static final int DEFAULT_BORDER = 2;
		// logo��СĬ��Ϊ��Ƭ��1/6
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
	 * ֻ���ɶ�ά��
	 */
	public static void M1() {
	       try {
	            //��ά���ʾ������
	            String content = "http://www.cnblogs.com/";
				// Ŀ���ļ�
				String targetImage = "D:/M1.jpg";

	            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

	            @SuppressWarnings("rawtypes")
	            Map hints = new HashMap();
	            
	            //����UTF-8�� ��ֹ��������
	            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
	            //���ö�ά�����ܰ�ɫ����Ĵ�С
	            hints.put(EncodeHintType.MARGIN,1);
	            //���ö�ά����ݴ���
	            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); 
	            
	            //width:ͼƬ�����Ŀ�;height:ͼƬ�����ĸ�
	            //��ΪҪ�ڶ�ά���·��������֣����԰�ͼƬ����Ϊ�����Σ��ߴ��ڿ�
	            int width = 400;
	            int height = 450;
	            
	            //����ά�룬�ǵõ���multiFormatWriter.encode()ʱ���Ҫ����hints��������Ȼ����������Ч
	            BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
	            
	            //qrcFile����������ɵĶ�ά��ͼƬ����logo�������֣�
	            File qrcFile = new File(targetImage);
	            
	            //��ʼ����ά��
	            MatrixToImageWriter.writeToFile(bitMatrix, "jpg", qrcFile);


	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	/**
	 * ��ά��+logo
	 */
	public static void M2() {
	       try {
	            //��ά���ʾ������
	            String content = "http://www.cnblogs.com/";
				// logo��ַ
				String logopath = "D:/logo.jpg";
				// Ŀ���ļ�
				String targetImage = "D:/M2.jpg";
				
				
	            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

	            @SuppressWarnings("rawtypes")
	            Map hints = new HashMap();
	            
	            //����UTF-8�� ��ֹ��������
	            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
	            //���ö�ά�����ܰ�ɫ����Ĵ�С
	            hints.put(EncodeHintType.MARGIN,1);
	            //���ö�ά����ݴ���
	            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); 
	            
	            //width:ͼƬ�����Ŀ�;height:ͼƬ�����ĸ�
	            //��ΪҪ�ڶ�ά���·��������֣����԰�ͼƬ����Ϊ�����Σ��ߴ��ڿ�
	            int width = 400;
	            int height = 450;
	            
	            //����ά�룬�ǵõ���multiFormatWriter.encode()ʱ���Ҫ����hints��������Ȼ����������Ч
	            BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
	            
	            //logoFile������Ŵ���logo�Ķ�ά��ͼƬ����ά��+logo�������֣�
	            File logoFile = new File(logopath);
	            
	            //��ʼ����ά��
	            BufferedImage barCodeImage = MatrixToImageWriter.writeToFile(bitMatrix, "jpg");

	            //�ڶ�ά���м���ͼƬ
	            LogoConfig logoConfig = new LogoConfig(); //LogoConfig������Logo������
	            BufferedImage image = addLogo_QRCode(barCodeImage, logoFile, logoConfig);
	            ImageIO.write(image, "jpeg", new File(targetImage));
	    
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	
	/**
	 * ��ά��+logo+�±�����
	 */
	public static void M3() {
		try {
			// ��ά���ʾ������
			String content = "http://www.cnblogs.com/";
			// logo��ַ
			String logopath = "D:/logo.jpg";
			// Ŀ���ļ�
			String targetImage = "D:/M3.jpg";
			// ������ͼƬ�ϵ�������Ϣ
			String text = "123123123";
			
			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

			Map hints = new HashMap();

			// ����UTF-8�� ��ֹ��������
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			// ���ö�ά�����ܰ�ɫ����Ĵ�С
			hints.put(EncodeHintType.MARGIN, 1);
			// ���ö�ά����ݴ���
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

			// width:ͼƬ�����Ŀ�;height:ͼƬ�����ĸ�
			// ��ΪҪ�ڶ�ά���·��������֣����԰�ͼƬ����Ϊ�����Σ��ߴ��ڿ�
			int width = 400;
			int height = 450;

			// ����ά�룬�ǵõ���multiFormatWriter.encode()ʱ���Ҫ����hints��������Ȼ����������Ч
			BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);

			// logoͼƬ·��
			File logoFile = new File(logopath);

			// ��ʼ����ά��
			BufferedImage barCodeImage = MatrixToImageWriter.writeToFile(bitMatrix, "jpg");

			// �ڶ�ά���м���ͼƬ
			LogoConfig logoConfig = new LogoConfig(); // LogoConfig������Logo������
			BufferedImage image = addLogo_QRCode(barCodeImage, logoFile, logoConfig);

			int font = 25; // �����С
			int fontStyle = 1; // ������

			// �ڶ�ά���·�������֣����־��У�
			pressText(text, targetImage, image, fontStyle, Color.BLACK, font, width, height);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//ֻ���ɶ�ά��
		MatrixToImageWriter.M1();
		//��ά��+logo
		MatrixToImageWriter.M2();
		//��ά��+logo+�±�����
		MatrixToImageWriter.M3();
	}

}
