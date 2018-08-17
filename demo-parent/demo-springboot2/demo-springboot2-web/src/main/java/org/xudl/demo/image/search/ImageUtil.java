package org.xudl.demo.image.search;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.sun.scenario.effect.GaussianBlur;

import net.coobird.thumbnailator.Thumbnails;
import nu.pattern.OpenCV;

public class ImageUtil {
	public static void main(String[] args) throws Exception {
		/*float[][] kernals = get2DKernalData(1, 1.5F);
		normalization(kernals);*/

		OpenCV.loadShared();
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		String path = "D:\\noah\\temp\\";
		Mat im = Imgcodecs.imread(path + "1.jpg");
		System.out.println(im.empty());

		Mat roi_img = new Mat(im, new Rect(10, 15, im.height(), im.width()));

		Mat cutImg = new Mat();
		roi_img.copyTo(cutImg);
		Imgcodecs.imwrite(path + "1-6.jpg", cutImg);

		Mat dst = new Mat();
		Imgproc.GaussianBlur(im, dst, new Size(15, 15), 0);
		Imgproc.cvtColor(im, dst, Imgproc.COLOR_BGR2GRAY);

		/*Imgcodecs.imwrite(path + "1-1.jpg", dst);
		GaussianBlurFilter filter = new GaussianBlurFilter();
		filter.setSigma(10);
		
		RxImageData.bitmap(bitmap).addFilter(filter).into(image2);*/

	}

	/**
	 * 当前拍摄搜索前处理。将图片按指定位置进行切割。如果搜索不到，则重新切割后再搜索。
	 * 
	 * @throws Exception
	 */
	public static void imageDealInJavaApi() throws Exception {
		String path = "D:\\noah\\拍书\\测试书本和图片\\英语\\抽测图片-人教版英语八年级上册";
		File dir = new File(path);
		for (File file : dir.listFiles()) {
			if (file.isDirectory()) {
				continue;
			}
			String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
			BufferedImage inputImage = ImageIO.read(file);
			java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
			ImageIO.write(inputImage, "jpg", out);
			ImageInputStream inputStream = ImageIO.createImageInputStream(new ByteArrayInputStream(out.toByteArray()));

			// 获取注册的图片读取器。ImageReader 可以对图片进行转换和解码
			Iterator<ImageReader> imageReaderIterator = ImageIO.getImageReadersByFormatName("jpg");
			ImageReader imageReader = imageReaderIterator.next();
			imageReader.setInput(inputStream, true);

			// 截图的坐标: x，y,width,height
			/*int[][] cutDatas = new int[][] {
			        { 1, 12, 765, 575 },
			        { 1, 12, 765, 379 },
			
			        { 135, 0, 496, 599 },
			        { 135, 0, 496, 399 },
			        { 135, 100, 496, 299 }
			};*/

			int[][] cutDatas = new int[][] {
			        { 135, 0, 496, 599 }
			};

			for (int i = 0; i < cutDatas.length; i++) {
				int[] data = cutDatas[i];
				Rectangle rect = new Rectangle(data[0], data[1], data[2], data[3]);
				ImageReadParam param = imageReader.getDefaultReadParam();
				param.setSourceRegion(rect);

				BufferedImage cutImage = imageReader.read(0, param);
				File cutFile = new File(path + "\\剪切图\\" + fileName + "-cut-" + (i + 1) + ".jpg");
				if (!cutFile.getParentFile().exists()) {
					cutFile.getParentFile().mkdirs();
				}
				ImageIO.write(cutImage, "jpg", cutFile);
			}
		}
		System.out.println("----cut end-----");
	}

	public static void test3() throws IOException {
		String path = "D:\\noah\\拍书\\人教版-初中-数学-七年级-下学期-12版\\校验测试图\\";

		GaussianBlur blur = new GaussianBlur();

		BufferedImage fromImage = ImageIO.read(new File(path + "8_1.jpg"));
		BufferedImage toImage = new BufferedImage(fromImage.getWidth(), fromImage.getHeight(),
		        BufferedImage.TYPE_INT_RGB);
		ImageIO.write(toImage, "jpg", new File(path + "8_03.jpg"));
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
		ImageIO.write(img, "jpg", new File(path + "8_02.jpg"));
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
				if (i == 1 && j == 1) {
					continue;
				}
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

	// 进行归一化处理，使得所有元素的和为1
	public static float[][] normalization(float[][] kernal) {
		// 计算所有的值的合
		float sum = 0F;
		for (int i = 0; i < kernal.length; i++) {
			float[] x = kernal[i];
			for (int j = 0; j < kernal.length; j++) {
				sum += x[j];
			}
		}

		float normalSum = 0F;
		float[][] normalizKernal = new float[kernal.length][kernal.length];
		for (int i = 0; i < kernal.length; i++) {
			float[] x = kernal[i];
			for (int j = 0; j < kernal.length; j++) {
				normalizKernal[i][j] = x[j] / sum;
				normalSum += normalizKernal[i][j];
				System.out.print("\t" + normalizKernal[i][j]);
			}
			System.out.println("\n\t---------------------------");
		}
		System.out.println(String.format("sum:%s,normal-sum:%s", sum, normalSum));
		return normalizKernal;
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
