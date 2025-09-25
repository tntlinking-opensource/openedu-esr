package org.apache.struts2.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 二维码工具类
 */
public class ZxingUtil {
	private static final Map<EncodeHintType, ErrorCorrectionLevel> encodeMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
	private static final Map<DecodeHintType, ErrorCorrectionLevel> decodeMap=new HashMap<DecodeHintType, ErrorCorrectionLevel>();
	private static final String charset="UTF-8",format="png";
	private static final int size=300;
	private ZxingUtil() {}

	/**
	 * 生成二维码图片
	 */
	public static void createQRCode(String data, File file)
			throws WriterException, IOException {
		MatrixToImageWriter.writeToFile(new MultiFormatWriter().encode(
				new String(data.getBytes(charset), charset),
				BarcodeFormat.QR_CODE, size, size, encodeMap), format, file);
	}

	/**
	 * 读取二维码信息
	 */
	public static String readQRCode(String filePath)
			throws FileNotFoundException, IOException, NotFoundException {
		return new MultiFormatReader().decode(new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(filePath))))),decodeMap).getText();
	}
	
	public static void main(String [] arg){
		try{
			createQRCode("http://blog.csdn.net/xmtblog", new File("D:\\111.png"));
			System.out.println(readQRCode("D:\\111.png"));
			//源码下载地址：http://download.csdn.net/detail/xmt1139057136/8886473
			//欢迎大家关注我的博客！如有疑问,请加QQ群：454796847共同学习！
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
