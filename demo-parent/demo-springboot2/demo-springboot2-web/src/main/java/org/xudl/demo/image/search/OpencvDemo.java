package org.xudl.demo.image.search;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import nu.pattern.OpenCV;

public class OpencvDemo {

	public static void main(String[] args) {
		// 加载本地库
		OpenCV.loadShared();
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		// 加载图片。注意图片路径不能有中文，否则图片会加载失败
		String path = "D:\\noah\\temp\\";
		Mat imMat = Imgcodecs.imread(path + "5.jpg");
		System.out.println(imMat.empty()); // 判断图片是否被正常加载
		if (imMat.empty()) {
			System.exit(0);
		}

		// 图片剪切。指定坐标(x,y)、宽度和高度。如果高度超过原图高度，会抛出异常
		Mat rectMat = new Mat(imMat,
		        new Rect(0, Double.valueOf(Math.floor(imMat.height() * 0.5)).intValue(), imMat.width(),
		                Double.valueOf(Math.floor(imMat.height() * 0.5)).intValue()));
		Mat thrtyPerMat = new Mat(imMat,
		        new Rect(0, 0, imMat.width(),
		                Double.valueOf(Math.floor(imMat.height() * 0.33)).intValue()));
		Mat destMat = new Mat();
		rectMat.copyTo(destMat);
		Mat thrtyMat = new Mat();
		thrtyPerMat.copyTo(thrtyMat);

		// 将剪切图保存
		Imgcodecs.imwrite(path + "cut-11.jpg", destMat);
		Imgcodecs.imwrite(path + "cut-3.jpg", thrtyMat);

		Mat grayMat = new Mat();
		Imgproc.cvtColor(imMat, grayMat, Imgproc.COLOR_RGB2GRAY); // 灰度化

		// 图片旋转
		Point center = new Point(imMat.width() / 2.0, imMat.height() / 2.0);
		Mat affineTrans = Imgproc.getRotationMatrix2D(center, 10.0, 1.0);
		Imgproc.warpAffine(imMat, destMat, affineTrans, imMat.size(), Imgproc.INTER_NEAREST);
		Imgcodecs.imwrite(path + "1-rotate-1.jpg", destMat, new MatOfInt(0));

		destMat = imMat.clone();
		affineTrans = Imgproc.getRotationMatrix2D(center, -10.0, 1.0);
		Imgproc.warpAffine(imMat, destMat, affineTrans, destMat.size(), Imgproc.INTER_NEAREST);
		Imgcodecs.imwrite(path + "1-rotate-2.jpg", destMat, new MatOfInt(0, 255));

		System.out.println("---success---");
	}
}
