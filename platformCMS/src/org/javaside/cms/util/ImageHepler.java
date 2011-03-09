package org.javaside.cms.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//图片剪裁类
public class ImageHepler {

	private static BufferedImage makeThumbnail(Image img, int width, int height) {
		BufferedImage tag = new BufferedImage(width, height, 1);
		Graphics g = tag.getGraphics();
		g.drawImage(img.getScaledInstance(width, height, 4), 0, 0, null);
		g.dispose();
		return tag;
	}

	private static void saveSubImage(BufferedImage image, Rectangle subImageBounds, File subImageFile)
			throws IOException {
		String fileName = subImageFile.getName();
		String formatName = fileName.substring(fileName.lastIndexOf('.') + 1);
		BufferedImage subImage = new BufferedImage(subImageBounds.width, subImageBounds.height, 1);
		Graphics g = subImage.getGraphics();
		if (subImageBounds.width > image.getWidth() || subImageBounds.height > image.getHeight()) {
			int left = subImageBounds.x;
			int top = subImageBounds.y;
			if (image.getWidth() < subImageBounds.width) {
				left = (subImageBounds.width - image.getWidth()) / 2;
			}
			if (image.getHeight() < subImageBounds.height) {
				top = (subImageBounds.height - image.getHeight()) / 2;
			}
			g.setColor(Color.white);
			g.fillRect(0, 0, subImageBounds.width, subImageBounds.height);
			g.drawImage(image, left, top, null);
			ImageIO.write(image, formatName, subImageFile);
			System.out.println((new StringBuilder("if is running left:")).append(left).append(" top: ").append(top)
					.toString());
		} else {
			g.drawImage(image.getSubimage(subImageBounds.x, subImageBounds.y, subImageBounds.width,
					subImageBounds.height), 0, 0, null);
			System.out.println("else is running");
		}
		g.dispose();
		ImageIO.write(subImage, formatName, subImageFile);
	}

	public static void cut(File srcImageFile, File descDir, int width, int height, Rectangle rect) throws IOException {
		Image image = ImageIO.read(srcImageFile);
		BufferedImage bImage = makeThumbnail(image, width, height);
		saveSubImage(bImage, rect, descDir);
	}

}
