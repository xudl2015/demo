package org.xudl.demo.image.search;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageUtil {
	public static void main(String[] args) throws Exception {
		/*float[][] kernals = get2DKernalData(1, 1);
		String path = "D:\\\\noah\\\\拍书\\\\人教版-初中-数学-七年级-下学期-12版\\\\校验测试图\\\\";
		BufferedImage image = ImageIO.read(new File(path + "8_1.jpg"));
		float[] data = new float[9];
		int idx = 0;
		for (int i = 0; i < kernals.length; i++) {
			float[] cols = kernals[i];
			for (int j = 0; j < cols.length; j++) {
				System.out.println(idx);
				data[idx++] = cols[j];
			}
		}
		Kernel kernel = new Kernel(3, 3, data);
		ConvolveOp imageOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null); // 创建卷积变换操作对象
		BufferedImage filteredBufImage = new BufferedImage(image.getWidth(), image.getHeight(),
		        BufferedImage.TYPE_INT_ARGB); // 过滤后的缓冲区图像
		imageOp.filter(image, filteredBufImage);// 过滤图像，目标图像在filteredBufImage
		ImageIO.write(filteredBufImage, "jpg", new File(path + "8_02.jpg"));
		System.out.println("----success------");*/
		test2();
	}

	public static void test2() throws Exception {
		float[][] kernals = get2DKernalData(1, 0.75F);
		String path = "D:\\noah\\拍书\\人教版-初中-数学-七年级-下学期-12版\\校验测试图\\";
		BufferedImage img = ImageIO.read(new File(path + "8_1.jpg"));
		int height = img.getHeight();
		int width = img.getWidth();
		int[][] martrix = new int[3][3];
		int[] values = new int[9];
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				readPixel(img, i, j, values);
				fillMatrix(martrix, values);
				img.setRGB(i, j, avgMatrix(kernals, martrix));
			}
		ImageIO.write(img, "jpg", new File(path + "8_01.jpg"));
		System.out.println("----success------");
	}

	private static void readPixel(BufferedImage img, int x, int y, int[] pixels) {
		int xStart = x - 1;
		int yStart = y - 1;
		int current = 0;
		for (int i = xStart; i < 3 + xStart; i++)
			for (int j = yStart; j < 3 + yStart; j++) {
				int tx = i;
				if (tx < 0) {
					tx = -tx;
				} else if (tx >= img.getWidth()) {
					tx = x;
				}
				int ty = j;
				if (ty < 0) {
					ty = -ty;
				} else if (ty >= img.getHeight()) {
					ty = y;
				}
				pixels[current++] = img.getRGB(tx, ty);
			}
	}

	private static void fillMatrix(int[][] matrix, int[] values) {
		int filled = 0;
		for (int i = 0; i < matrix.length; i++) {
			int[] x = matrix[i];
			for (int j = 0; j < x.length; j++) {
				x[j] = values[filled++];
			}
		}
	}

	private static int avgMatrix(float[][] kernals, int[][] matrix) {
		int r = 0;
		int g = 0;
		int b = 0;
		for (int i = 0; i < matrix.length; i++) {
			int[] x = matrix[i];
			for (int j = 0; j < x.length; j++) {
				float kernal = kernals[i][j];
				Color c = new Color(x[j]);
				r += c.getRed() * kernal;
				g += c.getGreen() * kernal;
				b += c.getBlue() * kernal;
			}
		}
		Color c = new Color(r, g, b);
		return c.getRGB();
	}

	/**
	 * 计算一维高斯函数的java代码
	 * 
	 * @param n
	 *            范围设置。取数范围为[-n,n]
	 * @param sigma
	 *            标准方差。一般取值为1
	 * @return
	 */
	private static float[] get1DKernalData(int n, float sigma) {
		float sigma22 = 2 * sigma * sigma;
		float Pi2 = 2 * (float) Math.PI;
		float sqrtSigmaPi2 = (float) Math.sqrt(Pi2) * sigma;
		int size = 2 * n + 1;
		int index = 0;
		float[] kernalData = new float[size];
		for (int i = -n; i <= n; i++) {
			float distance = i * i;
			kernalData[index] = (float) Math.exp((-distance) / sigma22) / sqrtSigmaPi2;
			System.out.println("\t" + kernalData[index]);
			index++;
		}
		return kernalData;
	}

	/**
	 * 计算二维高斯函数的java代码
	 * 
	 * @param n
	 *            范围设置。取数范围为[-n,n]
	 * @param sigma
	 *            标准方差。一般取值为1
	 * @return
	 */
	public static float[][] get2DKernalData(int n, float sigma) {
		int size = 2 * n + 1;
		float sigma22 = 2 * sigma * sigma;
		float sigma22PI = (float) Math.PI * sigma22;
		float[][] kernalData = new float[size][size];
		int row = 0;
		for (int i = -n; i <= n; i++) {
			int column = 0;
			for (int j = -n; j <= n; j++) {
				float xDistance = i * i;
				float yDistance = j * j;
				kernalData[row][column] = (float) Math.exp(-(xDistance + yDistance) / sigma22) / sigma22PI;
				column++;
			}
			row++;
		}
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print("\t" + kernalData[i][j]);
			}
			System.out.println();
			System.out.println("\t ---------------------------");
		}
		return kernalData;
	}

}