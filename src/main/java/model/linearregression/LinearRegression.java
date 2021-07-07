package model.linearregression;

import Jama.Matrix;

/**
 * 一般的线性回归
 * @author LvWenJin
 *
 */
public class LinearRegression {
	
	//特征数组
	private double[][] featureData;
	
	//结果数组，用来训练的系数数组
	private double[][] resultData;
	//输入数据的行列数,行数+1是加上了x0行
	private int inputRow;
	private int inputclu;
	
	//新产生的输入矩阵，多一列，用来放置x0
	private double[][] input;
	
	//输出的结果数组，是各个系数，长度加一，放第零个常数项
	private double[][] coefficient;
	
	//系数矩阵
	private Matrix coefficientVector; 
	public LinearRegression(double[][] featureData, double[][] resultData) {
		this.featureData = featureData;
		this.resultData = resultData;
		this.inputRow = this.featureData.length;
		this.inputclu = this.featureData[0].length+1;
		//新产生的输入矩阵，多一列，用来放置x0
		input=new double[inputRow][inputclu];
		coefficient = new double[1][inputclu];
		//系数矩阵
		coefficientVector = new Matrix(coefficient);
	}
	
	//转换一下输入矩阵
	private Matrix getInputDataMaxtri() {
		
		for(int i=0;i<featureData.length;i++) {
			for(int j=0;j<featureData[0].length;j++) {
				input[i][j+1] = featureData[i][j];
			}
		}
		//凑一下x0，放在第一列
		for(int i=0;i<input.length;i++) {
			input[i][0] = 1;
		}
		Matrix inputMatrix = new Matrix(input);
		return inputMatrix;
	}
	
	
	
	
	//线性回归,返回的是更新后的系数值
	public Matrix linearRegression(double step) {
		//输入参数
		Matrix input = this.getInputDataMaxtri();
		
		//将输入矩阵置转一下方便矩阵相乘
		Matrix transInput = input.transpose();
		
		//系数矩阵左乘输入矩阵,再置转一次
		Matrix functionResult = (coefficientVector.times(transInput)).transpose();
		
		//计算结果减去训练结果，得到结果差
		Matrix minus = functionResult.minus(new Matrix(resultData));
		
		for(int i = 0;i<inputclu;i++) {
			//第i个参数的偏微分
			Matrix times = minus.transpose().times(input);
			double d = coefficientVector.get(0, i);
			d = d-((step/inputRow)*times.get(0, i));
			coefficientVector.set(0, i, d);
		}
		return coefficientVector;
	}
	
	public void test() {
		while(true) {
			Matrix linearRegression = linearRegression(0.02);
			System.out.println(linearRegression.get(0, 0)+"----"+linearRegression.get(0, 1));
		
		}
	}
	
}
