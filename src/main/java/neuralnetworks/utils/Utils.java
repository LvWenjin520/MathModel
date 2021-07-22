package neuralnetworks.utils;

import java.awt.Color;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import javax.imageio.ImageIO;

import Jama.Matrix;

/***
 * 工具类
 * @author user
 *
 */
public class Utils {
	
	/***
	 * 获取随机矩阵
	 * @param n
	 * @param m
	 * @param random  范围[-random,random]
	 * @return
	 */
	public static Matrix randomMatrix(int n,int m,double random) {
		Matrix m1 = Matrix.random(n, m);
		for(int i = 0;i<m1.getRowDimension();i++) {
			for(int j = 0;j<m1.getColumnDimension();j++) {
				m1.set(i, j, (m1.get(i, j)*2*random)-random);
			}
		}
		return m1;
	}
	
	
	/***
	 * 对目录下的所有图片进行灰度化
	 * @param dir  图像所在的目录
	 * @param endStr  文件后缀，文件的类型
	 * @throws IOException
	 */
	public static void testImageToGray(String dir,String endStr) throws IOException {
		
		File dirs = new File(dir);
		if(!dirs.exists()) {
			System.out.println("文件夹不存在");
			return;
		}else if(!dirs.isDirectory()) {
			System.out.println("不是文件夹");
			return;
		}
		
		//过滤出所需要的文件
		File[] filesList = dirs.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				String fileName = name.toLowerCase();

                if (fileName.endsWith("."+endStr)) {
                    return true;
                }
                return false;
			}
		});
		for(int m = 0;m<filesList.length;m++) {
			BufferedImage gray = new ColorConvertOp(
					ColorSpace.getInstance(ColorSpace.CS_GRAY), null)
					.filter(ImageIO.read(filesList[m]), null);
			String name = filesList[m].getName();
			name = name.substring(0,name.indexOf("."));
			//在原路径下生成灰度图文件
			File newFile = new File(dirs.getPath()+File.separator+File.separator+name+"-gray."+endStr);
			
			ImageIO.write(gray, endStr , newFile);
		}
			
	}
	
	
	/***
	 * 对目录下的所有图片进行灰度化
	 * @param dir  图像所在的目录
	 * @param endStr  文件后缀，文件的类型
	 * @throws IOException
	 */
	public static void imageToGray(String dir,String endStr) throws IOException {
		
		File dirs = new File(dir);
		if(!dirs.exists()) {
			System.out.println("文件夹不存在");
			return;
		}else if(!dirs.isDirectory()) {
			System.out.println("不是文件夹");
			return;
		}
		
		//过滤出所需要的文件
		File[] filesList = dirs.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				String fileName = name.toLowerCase();

                if (fileName.endsWith("."+endStr)) {
                    return true;
                }
                return false;
			}
		});
		for(int m = 0;m<filesList.length;m++) {
			BufferedImage bufferedImage = ImageIO.read(filesList[m]);
			BufferedImage gredImage = new BufferedImage(bufferedImage.getWidth(),
					bufferedImage.getHeight(),
					bufferedImage.getType()); //TYPE_3BYTE_BGR 类型
			Color color;
			for ( int i = 0 ; i < bufferedImage.getWidth(); i++) {
			   for ( int j = 0 ; j < bufferedImage.getHeight(); j++) {
				   color = new Color(bufferedImage.getRGB(i, j));
				    int gray = ( int ) ( 0.3 * color.getRed() + 0.59 * color.getGreen() + 0.11 * color.getBlue());
				    color = new Color(gray,gray,gray);
				    int newPixel = color.getRGB();
				    gredImage.setRGB(i, j, newPixel);
			   }
			}
			String name = filesList[m].getName();
			name = name.substring(0,name.indexOf("."));
			//在原路径下生成灰度图文件
			File newFile = new File(dirs.getPath()+File.separator+File.separator+name+"-gray."+endStr);
			ImageIO.write(gredImage, endStr , newFile);
		}
	}
	
}
