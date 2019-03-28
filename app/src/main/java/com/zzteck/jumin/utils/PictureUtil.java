package com.zzteck.jumin.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Base64;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class PictureUtil {

	public static Bitmap scaleBitmap(Bitmap bitmap,float percent) {
		Matrix matrix = new Matrix();
		matrix.postScale(percent, percent);
		Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
				bitmap.getHeight(), matrix, true);
		return resizeBmp;
	}

	public static Bitmap bitmapZoomByHeight(Bitmap srcBitmap, int newHeight) {
		
		int srcWidth = srcBitmap.getWidth();
		int srcHeight = srcBitmap.getHeight() ;
		
		float scaleHeight = ((float) newHeight) / srcHeight;
		float scaleWidth = scaleHeight;

		return bitmapZoomByScale(srcBitmap, scaleWidth, scaleHeight);
	}

	public static Bitmap bitmapZoomByScale(Bitmap srcBitmap, float scaleWidth,float scaleHeight) {
		
		int srcWidth = srcBitmap.getWidth();
		int srcHeight = srcBitmap.getHeight();
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		
		Bitmap resizedBitmap = Bitmap.createBitmap(srcBitmap, 0, 0, srcWidth,srcHeight, matrix, true);
		if (resizedBitmap != null) {
			return resizedBitmap;
		} else {
			return srcBitmap;
		}
	}

	public static Bitmap decodeBitmapFile(String filePath) {
		Bitmap bitmap = null;
		if (filePath != null) {
			File file = new File(filePath);
			if (file.exists()) {
				bitmap = PictureUtil.getSmallBitmap(filePath);
			}
		}
		return bitmap;
	}

	public static Bitmap decodePhotoEditorBitmapFile(String filePath) {
		Bitmap bitmap = null;
		if (filePath != null) {
			File file = new File(filePath);
			if (file.exists()) {
				bitmap = PictureUtil.getPhotEditorSmallBitmap(filePath);
			}
		}
		return bitmap;
	}

	public static void deleteAllFiles(File root) {
		File files[] = root.listFiles();
		if (files != null)
			for (File f : files) {
				if (f.isDirectory()) {
					deleteAllFiles(f);
					try {
						f.delete();
					} catch (Exception e) {
					}
				} else {
					if (f.exists()) {
						deleteAllFiles(f);
						try {
							f.delete();
						} catch (Exception e) {
						}
					}
				}
			}
	}

	public static String compressPicture(Context context, String filePath) {
		String retFilePath = null;
		if (filePath != null) {
			try {

				File f = new File(filePath);
				// Bitmap bm = PictureUtil.getSmallBitmap(filePath);
				Bitmap bm = PictureUtil.getPhotEditorSmallBitmap(filePath);
				if (bm == null) {
					return retFilePath;
				}
				File fileThumb = new File(FileUtils.getWanjiaDir(context),
						"small_" + f.getName());
				FileOutputStream fos = new FileOutputStream(fileThumb);
				bm.compress(Bitmap.CompressFormat.JPEG, 100, fos);
				retFilePath = fileThumb.getAbsolutePath();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return retFilePath;
	}

	/**
	 * 把bitmap转换成String
	 * 
	 * @param filePath
	 * @return
	 */
	public static String bitmapToString(String filePath) {

		Bitmap bm = getSmallBitmap(filePath);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.JPEG, 40, baos);
		byte[] b = baos.toByteArray();
		return Base64.encodeToString(b, Base64.DEFAULT);

	}

	public static int computeSampleSize(BitmapFactory.Options options,
			int minSideLength, int maxNumOfPixels) {
		int initialSize = computeInitialSampleSize(options, minSideLength,
				maxNumOfPixels);

		int roundedSize;
		if (initialSize <= 8) {
			roundedSize = 1;
			while (roundedSize < initialSize) {
				roundedSize <<= 1;
			}
		} else {
			roundedSize = (initialSize + 7) / 8 * 8;
		}

		return roundedSize;
	}

	private static int computeInitialSampleSize(BitmapFactory.Options options,
			int minSideLength, int maxNumOfPixels) {
		double w = options.outWidth;
		double h = options.outHeight;

		int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
				.sqrt(w * h / maxNumOfPixels));
		int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
				Math.floor(w / minSideLength), Math.floor(h / minSideLength));

		if (upperBound < lowerBound) {
			// return the larger one when there is no overlapping zone.
			return lowerBound;
		}

		if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
			return 1;
		} else if (minSideLength == -1) {
			return lowerBound;
		} else {
			return upperBound;
		}
	}

	/**
	 * 计算图片的缩放值
	 * 
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			// Calculate ratios of height and width to requested height and
			// width
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);

			// Choose the smallest ratio as inSampleSize value, this will
			// guarantee
			// a final image with both dimensions larger than or equal to the
			// requested height and width.
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}

		return inSampleSize;
	}

	/**
	 * 根据路径获得突破并压缩返回bitmap用于显示
	 * 
	 */
	public static Bitmap getSmallBitmap(String filePath) {

		final BitmapFactory.Options options = new BitmapFactory.Options();

		options.inJustDecodeBounds = true;

		BitmapFactory.decodeFile(filePath, options);

		options.inSampleSize = computeSampleSize(options, -1, 1280 * 720);
		options.inJustDecodeBounds = false;

		Bitmap rotatedBitmap = null;

		try {

			Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);

			int thumbnail_rotation = 0;

			String exif_orientation_s = null;

			try {
				if (exif_orientation_s == null) {
					ExifInterface exif = new ExifInterface(filePath);
					exif_orientation_s = exif
							.getAttribute(ExifInterface.TAG_ORIENTATION);
				}

				int exif_orientation = 0;

				if (exif_orientation_s.equals("0")
						|| exif_orientation_s.equals("1")) {

				} else if (exif_orientation_s.equals("3")) {
					exif_orientation = 180;
				} else if (exif_orientation_s.equals("6")) {
					exif_orientation = 90;
				} else if (exif_orientation_s.equals("8")) {
					exif_orientation = 270;
				}

				thumbnail_rotation = (thumbnail_rotation + exif_orientation) % 360;

			} catch (IOException exception) {
				exception.printStackTrace();
			}

			if (thumbnail_rotation != 0) {

				Matrix m = new Matrix();
				m.setRotate(thumbnail_rotation, bitmap.getWidth() * 0.5f,
						bitmap.getHeight() * 0.5f);
				rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
						bitmap.getWidth(), bitmap.getHeight(), m, true);

				if (bitmap != null) {
					bitmap.recycle();
					bitmap = null;
				}

			} else {
				rotatedBitmap = bitmap;
			}

			System.gc();

		} catch (OutOfMemoryError e) {
			e.printStackTrace();
		}

		return rotatedBitmap;
	}

	public static Bitmap getPhotEditorSmallBitmap(String filePath) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, 480, 800);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;

		Bitmap rotatedBitmap = null;

		try {

			Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);

			int thumbnail_rotation = 0;

			String exif_orientation_s = null;

			try {
				if (exif_orientation_s == null) {
					ExifInterface exif = new ExifInterface(filePath);
					exif_orientation_s = exif
							.getAttribute(ExifInterface.TAG_ORIENTATION);
				}

				int exif_orientation = 0;

				if (exif_orientation_s.equals("0")
						|| exif_orientation_s.equals("1")) {

				} else if (exif_orientation_s.equals("3")) {
					exif_orientation = 180;
				} else if (exif_orientation_s.equals("6")) {
					exif_orientation = 90;
				} else if (exif_orientation_s.equals("8")) {
					exif_orientation = 270;
				}

				thumbnail_rotation = (thumbnail_rotation + exif_orientation) % 360;

			} catch (IOException exception) {
				exception.printStackTrace();
			}

			if (thumbnail_rotation != 0) {

				Matrix m = new Matrix();
				m.setRotate(thumbnail_rotation, bitmap.getWidth() * 0.5f,
						bitmap.getHeight() * 0.5f);
				rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
						bitmap.getWidth(), bitmap.getHeight(), m, true);

				if (bitmap != null) {
					bitmap.recycle();
					bitmap = null;
				}

			} else {
				rotatedBitmap = bitmap;
			}

			System.gc();

		} catch (OutOfMemoryError e) {
			e.printStackTrace();
		}

		return rotatedBitmap;
	}

	/**
	 * 根据路径删除图片
	 * 
	 * @param path
	 */
	public static void deleteTempFile(String path) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}

	/**
	 * 添加到图库
	 */
	public static void galleryAddPic(Context context, String path) {
		Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		if (path != null) {
			File f = new File(path);
			Uri contentUri = Uri.fromFile(f);
			mediaScanIntent.setData(contentUri);
			context.sendBroadcast(mediaScanIntent);
		}
	}

	/**
	 * 获取保存 隐患检查的图片文件夹名称
	 * 
	 * @return
	 */
	public static String getAlbumName() {
		return "sheguantong";
	}

	public static Bitmap createViewBitmap(View v) {
		Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(),
				Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		v.draw(canvas);
		return bitmap;
	}

	public static String compress(Context context, String srcPath) {

		String ret = srcPath;

		float hh = ScreenUtil.getWindowsHeight((Activity) context);
		float ww = ScreenUtil.getWindowsWidth((Activity) context);

		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(srcPath, opts);
		opts.inJustDecodeBounds = false;
		int w = opts.outWidth;
		int h = opts.outHeight;
		int size = 0;
		if (w <= ww && h <= hh) {
			size = 1;
		} else {
			double scale = w >= h ? w / ww : h / hh;
			double log = Math.log(scale) / Math.log(2);
			double logCeil = Math.ceil(log);
			size = (int) Math.pow(2, logCeil);
		}
		opts.inSampleSize = size;
		File file = new File(srcPath);
		bitmap = BitmapFactory.decodeFile(srcPath, opts);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int quality = 100;
		bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
		System.out.println(baos.toByteArray().length);
		while (baos.toByteArray().length > 30 * 1024) {
			baos.reset();
			bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
			quality -= 20;
			System.out.println(baos.toByteArray().length);
		}
		try {
			File fileThumb = new File(FileUtils.getWanjiaDir(context), "small_"
					+ file.getName());
			baos.writeTo(new FileOutputStream(fileThumb.getAbsolutePath()));
			ret = fileThumb.getAbsolutePath();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				baos.flush();
				baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	public static Bitmap getHttpBitmap(String url) {
		Bitmap bitmap = null;
		try {
			URL pictureUrl = new URL(url);
			InputStream in = pictureUrl.openStream();
			bitmap = BitmapFactory.decodeStream(in);
			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	public static String savePicture(Bitmap bitmap) {
		String pictureName = Environment.getExternalStorageDirectory()
				+ File.separator + "share" + ".jpg";
		File file = new File(pictureName);
		FileOutputStream out;
		try {
			out = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pictureName;
	}
	
	/**  
    * Drawable转化为Bitmap  
    */    
	    public static Bitmap drawableToBitmap(Drawable drawable) {    
	       int width = drawable.getIntrinsicWidth();    
	       int height = drawable.getIntrinsicHeight();    
	       Bitmap bitmap = Bitmap.createBitmap(width, height, drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888 : Config.RGB_565);
	       Canvas canvas = new Canvas(bitmap);    
	       drawable.setBounds(0, 0, width, height);    
	       drawable.draw(canvas);    
	       return bitmap;    
	        
	    }    
    /** 
     * Bitmap to Drawable 
     * @param bitmap 
     * @param mcontext 
     * @return 
     */  
	    public static Drawable bitmapToDrawble(Bitmap bitmap,Context mcontext){  
	        Drawable drawable = new BitmapDrawable(mcontext.getResources(), bitmap);  
	        return drawable;  
	    }

    /**
     * 毛玻璃效果
     */
    public static Bitmap getBlurPhoto(Bitmap bitmap,Context context) {
		
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);  
        RenderScript rs = RenderScript.create(context);  
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));  
        Allocation allIn = Allocation.createFromBitmap(rs, bitmap);  
        Allocation allOut = Allocation.createFromBitmap(rs, outBitmap);  
        blurScript.setRadius(5.0f);  
        blurScript.setInput(allIn);  
        blurScript.forEach(allOut);  
        allOut.copyTo(outBitmap);  
        bitmap.recycle();  
        rs.destroy();  
          
        return outBitmap;  
	}
}
