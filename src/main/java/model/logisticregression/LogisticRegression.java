package model.logisticregression;

import Jama.Matrix;

/**
 * 逻辑回归,处理分类问题
 * @author LvWenJin
 *
 */
public class LogisticRegression {
	
	/**
	 * 二元分类算法
	 * @param feature 特征集
	 * @param flag  结果集
	 * @return theta矩阵，从theta0到thetaN
	 */
	public Matrix twoFlagClassification(Matrix feature,Matrix flag,double step) {
		
		//特征参数向量
		double[][] thetas = new double[feature.getColumnDimension()][1];
		
		//theta的矩阵
		Matrix theta = new Matrix(thetas);
		
		//假设函数h(x)
		Matrix hx;
		
		//sigmoid函数
		Matrix gx;
		
		//梯度
		Matrix gradient;
		
		//每步下降的步长
		Matrix down = Matrix.constructWithCopy(thetas);
		//初始化为一个很大的步长
		for(int i = 0;i<down.getRowDimension();i++) {
			down.set(i, 0, 999.999);
		}
		
		//样本与标签的差
		Matrix cast;
		
		//当下降的步长小于0.0000001时停止迭代 =1E-7
		while((down.transpose().times(down).get(0, 0))>1E-7) {
			
			//系数乘以样本再取负数
			hx=feature.times(theta).uminus();
			
			//sigmooid函数
			gx = sigmoidFun(hx);
			
			//训练集与标签的差值
			cast = gx.minus(flag);
			
			//梯度
			gradient = (cast.transpose()).times(feature);
			
			//theta-变化
			down = (gradient.transpose()).times(step/feature.getRowDimension());
			
			//更新参数
			theta = theta.minus(down);
		}
		System.out.println("训练完成");
		for(int i = 0;i<theta.getRowDimension();i++) {
			System.out.println("theta("+i+") = "+theta.get(i, 0));
			System.out.println("================================");
		}
		return theta;
	}
	
	/***
	 * sigmoid函数，激活函数
	 * @param argument h(x)的计算结果
	 * @return 激活函数计算值
	 */
	private Matrix sigmoidFun(Matrix argument) {
		double[][] array = argument.getArray();
		Matrix result = new Matrix(argument.getRowDimension(), argument.getColumnDimension());
		for(int i = 0;i<argument.getRowDimension();i++) {
			result.set(i, 0, 1/(1+Math.exp(array[i][0])));
		}
		return result;
	}
	
}
