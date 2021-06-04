package model.linearregression.ls;

import Jama.Matrix;

/**
 * 普通最小二乘法
 * @author LvWenJin
 *
 */
public class OLS {
	
	private LSModel lsModel;
	
	private double variance=0;
	
	//直线拟合法
	//最小二乘法的方法
	public LSModel oLS(double[][] points) {
		int row = points.length;
		//两列
		double[][] X = new double[row][2];
		//一列
		double[][] y = new double[row][1];
		for(int i=0;i<row;i++) {
			X[i][0] = 1;
			X[i][1] = points[i][0];
			y[i][0] = points[i][1];
		}
		
		lsModel = new LSModel(1, getSLResult(X,y),true,variance);
		//获取结果
		return lsModel;
	}
	
	/**
	 *    n次多项式曲线拟合
	 * @param points
	 * @param n 最高次幂,n要大于等于2
	 * @param constant  是否需要常数项
	 * @return
	 */
	public LSModel oLS(double[][] points,int n,boolean constant) {
		if(n<2) {
			return null;
		}
		int row = points.length;
		//如果需要常数项
		if(constant) {
			//n+1列
			double[][] X = new double[row][n+1];
			//一列，y列
			double[][] y = new double[row][1];
			for(int i=0;i<row;i++) {
				for(int j=0;j<=n;j++) {
					X[i][j] = Math.pow(points[i][0], j);
				}
				y[i][0] = points[i][1];
			}
			//获取结果
			lsModel = new LSModel(n, getSLResult(X,y),true,variance);
			//获取结果
			return lsModel;
		}else {  //不需要常数项
			//n列
			double[][] X = new double[row][n];
			//一列，y列
			double[][] y = new double[row][1];
			for(int i=0;i<row;i++) {
				for(int j=0;j<n;j++) {
					X[i][j] = Math.pow(points[i][0], j+1);
				}
				y[i][0] = points[i][1];
			}
			//获取结果
			lsModel = new LSModel(n, getSLResult(X,y),false,variance);
			//获取结果
			return lsModel;
		}
	}
	
	/**
	 * 求最小二乘解
	 * @param X
	 * @param y
	 * @return  最终的系数
	 */
	private Matrix getSLResult(double[][] X,double[][] y) {
		//矩阵X
		Matrix matrixX = new Matrix(X);
		//结果向量y
		Matrix matrixY = new Matrix(y);
		//x的置转矩阵
		Matrix transposeX = matrixX.transpose();
		//乘法之后的矩阵
		Matrix XtX = transposeX.times(matrixX);
		//置转矩阵乘以结果向量
		Matrix Xty = transposeX.times(matrixY);
		//求逆
		Matrix inverse = XtX.inverse();
		
		Matrix result = inverse.times(Xty);
		
		Matrix slResult = matrixX.times(result);
		variance = matrixY.minus(slResult).normF();
		return result;
	}
}
