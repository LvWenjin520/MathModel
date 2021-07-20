package neuralnetworks.option.anns;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Jama.Matrix;

/***
 * 人工神经网络的配置项
 * @author LvWenJin
 *
 */
public class AnnsOption {
	
	//样本数量
	private int num;

	//隐含层数量，不包括输入输出层
	private int hiddenlayersNum;
	
	//隐含层神经单元数量，索引值对应第几层隐含层
	private int[] hiddenlayerUnitNum;
	
	//输入层神经单元数量
	private int inputUnitNum;
	
	//输出层单元数量
	private int outputUnitNum;
	
	//正则项的lambda
	private int lambda;
	
	//学习率
	private double step;
	
	//文件夹名称
	private String dirName;
	
	//参数矩阵
	private Matrix[] thetas;
	
	//输入层矩阵
	private Matrix a1;
	
	
	public Matrix[] getThetas() {
		return thetas;
	}




	public void setThetas(Matrix[] thetas) {
		this.thetas = thetas;
	}




	public Matrix getA1() {
		return a1;
	}




	public void setA1(Matrix a1) {
		this.a1 = a1;
	}




	public String getDirName() {
		return dirName;
	}




	public void setDirName(String dirName) {
		this.dirName = dirName;
	}




	public int getNum() {
		return num;
	}




	public void setNum(int num) {
		this.num = num;
	}




	public int getHiddenlayersNum() {
		return hiddenlayersNum;
	}




	public void setHiddenlayersNum(int hiddenlayersNum) {
		this.hiddenlayersNum = hiddenlayersNum;
	}




	public int[] getHiddenlayerUnitNum() {
		return hiddenlayerUnitNum;
	}




	public void setHiddenlayerUnitNum(int[] hiddenlayerUnitNum) {
		this.hiddenlayerUnitNum = hiddenlayerUnitNum;
	}




	public int getInputUnitNum() {
		return inputUnitNum;
	}




	public void setInputUnitNum(int inputUnitNum) {
		this.inputUnitNum = inputUnitNum;
	}




	public int getOutputUnitNum() {
		return outputUnitNum;
	}




	public void setOutputUnitNum(int outputUnitNum) {
		this.outputUnitNum = outputUnitNum;
	}




	public int getLambda() {
		return lambda;
	}




	public void setLambda(int lambda) {
		this.lambda = lambda;
	}




	public double getStep() {
		return step;
	}




	public void setStep(double step) {
		this.step = step;
	}


	



	public AnnsOption(String dirName,int hiddenlayersNum, int[] hiddenlayerUnitNum, int outputUnitNum, int lambda,
			double step) throws IOException {
		this.hiddenlayersNum = hiddenlayersNum;
		this.hiddenlayerUnitNum = hiddenlayerUnitNum;
		this.outputUnitNum = outputUnitNum;
		this.lambda = lambda;
		this.step = step;
		this.dirName = dirName;
		
		//获取输入矩阵
		this.a1 = createA1(dirName);
		
		
	}
	
	/**
	 * 创建参数数组
	 * @return
	 */
	private Matrix[] createThetas(Matrix a1) {
		
		
		
		
		return null;
	}
	
	
	/***
	 * 获取输入矩阵，所有的图片矩阵维度为num*n
	 * 此方法将num赋值
	 * @param dirName 图片存储位置
	 * @return 输入矩阵
	 * @throws IOException
	 */
	private Matrix createA1(String dirName) throws IOException {
		
		File dirs = new File(dirName);
		
		if(!dirs.exists()) {
			System.out.println("文件夹不存在");
			return null;
		}else if(!dirs.isDirectory()) {
			System.out.println("不是文件夹");
			return null;
		}
		
		File[] listFiles = dirs.listFiles();
		
		//样本数量
		this.num = listFiles.length;
		
		double[][] input = new double[this.num][];
		int colNum = 0;
		int rowNum = 0;
		//构造输入样本的矩阵,转为m行n列
		for(int n = 0;n <listFiles.length;n++) {
			BufferedImage bufferedImage = ImageIO.read(listFiles[n]);
			//列数
			colNum = bufferedImage.getWidth();
			//行数
			rowNum = bufferedImage.getHeight();
			
			//每一行特征值初始化
			input[n] = new double[colNum*rowNum];
			//转为1行n列	竖着	
			for ( int i = 0 ; i < colNum; i++) {
				for ( int j = 0 ; j < rowNum; j++) {
					input[n][i*rowNum+j] = bufferedImage.getRGB(i, j);
				}
			}
		}
		
		
		return new Matrix(input);
	}



	/**
	 * 
	 * @param hiddenlayersNum 隐含层数量
	 * @param hiddenlayerUnitNum 隐含层每层的单元数量
	 * @param inputUnitNum 输入层单元数量
	 * @param outputUnitNum 输出层单元数量
	 * @param lambda 正则项系数
	 * @param step 学习率
	 */
	public AnnsOption(int hiddenlayersNum, int[] hiddenlayerUnitNum, int inputUnitNum, int outputUnitNum, int lambda,
			double step) {
		this.hiddenlayersNum = hiddenlayersNum;
		this.hiddenlayerUnitNum = hiddenlayerUnitNum;
		this.inputUnitNum = inputUnitNum;
		this.outputUnitNum = outputUnitNum;
		this.lambda = lambda;
		this.step = step;
	}
	
	
	
	
}
