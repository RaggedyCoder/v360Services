package com.thevolume360.audit;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws Exception {
		Solution solution = new Solution();
		Scanner scanner = new Scanner(System.in);
		int rows = scanner.nextInt();
		int columns = scanner.nextInt();
		int coefficient = scanner.nextInt();
		int newwidth = scanner.nextInt();
		int newheight = scanner.nextInt();
		Image image = new Image(columns, rows);
		for (int i = 0; i < columns; i++) {
			for (int j = 0; j < rows; j++) {
				String rgb = scanner.next();
				String[] rgbSplitted = rgb.split(",");
				image.setRGB(i, j, Integer.parseInt(rgbSplitted[0]), Integer.parseInt(rgbSplitted[1]),
						Integer.parseInt(rgbSplitted[2]));
			}
		}
		solution.setMinsupport(coefficient);
		Image output = solution.scaleImage(image, newwidth, newheight);
		for (int i = 0; i < newwidth; i++) {
			for (int j = 0; j < newheight; j++) {
				System.out.print(output.getR(i, j) + "," + output.getG(i, j) + "," + output.getB(i, j) + " ");
			}
			System.out.println();
		}
		scanner.close();
	}

	/**
	 * inner class: Kernel
	 */
	private class Kernel {
		int size, normalizer, coefs[];

		Kernel(int size, int[] c, int n) {
			this.size = size;
			this.coefs = c;
			this.normalizer = n;
		}
	}

	private int minsupport = 2;
	private Kernel[] xkernels = null;
	private Kernel[] ykernels = null;
	private int[] tmpbuffer_r = new int[0];
	private int[] tmpbuffer_g = new int[0];
	private int[] tmpbuffer_b = new int[0];
	private Image image;

	public Image scaleImage(Image image, int newwidth, int newheight) throws Exception {
		this.image = image;


		this.xkernels = new Kernel[100];
		this.ykernels = new Kernel[100];

		double xfactor = ((newwidth * 1.0) / image.getWidth());
		double yfactor = ((newheight * 1.0) / image.getHeight());
		Image output = new Image(newwidth, newheight);

		
		for (int y = 0; y < newheight; y++) {
			for (int x = 0; x < newwidth; x++) {

				double xo = x / xfactor;
				double yo = y / yfactor;

				int xsample = (int) xo;
				double x_frac = xo - xsample;
				int ysample = (int) yo;
				double y_frac = yo - ysample;


				Kernel kx = getKernel(xkernels, x, image.getWidth(), xfactor, x_frac);
				Kernel ky = getKernel(ykernels, y, image.getHeight(), yfactor, y_frac);


				int rgb = fastconvolve(xsample, ysample, kx, ky);

				output.setRGB(x, y, rgb);
			}
		}

		return output;
	}

	private double lanczos(double support, double d) {
		if (d == 0)
			return 1.0;
		if (d >= support)
			return 0.0;
		double t = d * Math.PI;
		return support * Math.sin(t) * Math.sin(t / support) / (t * t);
	}

	private Kernel precompute(int index, int range, double scale, double frac) {

		int support = (int) (1 + 1.0 / scale);

		if (support < getMinsupport())
			support = getMinsupport();

		if (support % 2 == 0)
			support++;

		scale = Math.min(scale, 1.0);

		Kernel kernel = new Kernel(support, new int[support], 0);

		int i = 0;
		int halfwindow = support / 2;
		for (int dx = -halfwindow; dx <= halfwindow; dx++) {

			int c = 0;

			if (index + dx >= 0 && index + dx <= range) {

				double x = scale * (dx + frac);

				double coef = lanczos(halfwindow, x);

				c = (int) (1000 * coef + 0.5);
			}

			kernel.coefs[i++] = c;
			kernel.normalizer += c;
		}

		return kernel;
	}

	private Kernel getKernel(Kernel[] cache, int index, int range, double scale, double frac) {
		int kernalIndex = (int) (frac * 100);
		Kernel k = cache[kernalIndex];

		if (k != null) {
			return k;
		}

		k = precompute(index, range, scale, frac);
		cache[kernalIndex] = k;
		if (k.size > tmpbuffer_r.length)
			tmpbuffer_r = new int[k.size];
		if (k.size > tmpbuffer_g.length)
			tmpbuffer_g = new int[k.size];
		if (k.size > tmpbuffer_b.length)
			tmpbuffer_b = new int[k.size];

		return k;
	}


	private int fastconvolve(int x, int y, Kernel kernelx, Kernel kernely) {
		int halfwindowy = kernely.size / 2; 
		int halfwindowx = kernelx.size / 2; 

		
		Arrays.fill(tmpbuffer_r, 0);
		Arrays.fill(tmpbuffer_g, 0);
		Arrays.fill(tmpbuffer_b, 0);

		
		for (int dy = -halfwindowy; dy <= halfwindowy; dy++) {
			if (y + dy < 0 || y + dy >= image.getHeight())
				continue;
			for (int dx = -halfwindowx; dx <= halfwindowx; dx++) {
				if (x + dx < 0 || x + dx >= image.getWidth())
					continue;

				int rgb = image.getRGB(x + dx, y + dy);
				int r = (rgb >> 16) & 0xFF;
				int g = (rgb >> 8) & 0xFF;
				int b = (rgb) & 0xFF;

				tmpbuffer_r[halfwindowy + dy] += kernelx.coefs[halfwindowx + dx] * r;
				tmpbuffer_g[halfwindowy + dy] += kernelx.coefs[halfwindowx + dx] * g;
				tmpbuffer_b[halfwindowy + dy] += kernelx.coefs[halfwindowx + dx] * b;

			}
		}


		double rc = 0, gc = 0, bc = 0;
		for (int dy = -halfwindowy; dy <= halfwindowy; dy++) {
			rc += kernely.coefs[halfwindowy + dy] * tmpbuffer_r[halfwindowy + dy];
			gc += kernely.coefs[halfwindowy + dy] * tmpbuffer_g[halfwindowy + dy];
			bc += kernely.coefs[halfwindowy + dy] * tmpbuffer_b[halfwindowy + dy];
		}


		double norm = kernelx.normalizer * kernely.normalizer;
		rc /= norm;
		gc /= norm;
		bc /= norm;


		int r = (int) Math.min(255, Math.max(0, rc + 0.5));
		int g = (int) Math.min(255, Math.max(0, gc + 0.5));
		int b = (int) Math.min(255, Math.max(0, bc + 0.5));
		return 0xFF000000 + (r << 16) + (g << 8) + b;
	}

	public int getMinsupport() {
		return minsupport;
	}

	public void setMinsupport(int minsupport) {
		this.minsupport = minsupport;
	}
}

class Image {
	private int width;
	private int height;
	private int[][] rgb;

	public Image(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		rgb = new int[width][height];
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getRGB(int x, int y) {
		return rgb[x][y];
	}

	public void setRGB(int x, int y, int rgb) {
		this.rgb[x][y] = rgb;
	}

	public void setRGB(int x, int y, int r, int g, int b) {
		setRGB(x, y, 0xFF000000 + (r << 16) + (g << 8) + b);
	}

	public int getR(int x, int y) {
		return (this.rgb[x][y] >> 16) & 0xFF;
	}

	public int getG(int x, int y) {
		return (this.rgb[x][y] >> 8) & 0xFF;
	}

	public int getB(int x, int y) {
		return (this.rgb[x][y]) & 0xFF;
	}

}