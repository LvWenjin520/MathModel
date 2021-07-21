package neuralnetworks.anns;

import Jama.Matrix;
import neuralnetworks.option.anns.AnnsOption;

/**
 * 人工神经网络
 * @author LvWenJin
 *
 */
public class Anns {
	
	//配置项
	private AnnsOption option;
	
	public AnnsOption getOption() {
		return option;
	}

	public void setOption(AnnsOption option) {
		this.option = option;
	}

	public Anns(AnnsOption option) {
		this.option = option;
		this.z = new Matrix[option.getHiddenlayersNum()+2];
		
		//加了偏置的输入层
		this.z[0] = addBias(option.getA1());
		
		this.a = new Matrix[option.getHiddenlayersNum()+2];
		
		//加了偏置的输入层
		this.a[0] = addBias(option.getA1());
		
		//每一层的偏差
		this.d = new Matrix[option.getHiddenlayersNum()+1];
		
	}
	
	//每层的偏差
	private Matrix[] d;
	
	//中间结果
	private Matrix[] z;
	
	//中间结果,经过sigmod的结果
	private Matrix[] a;
	
	
	public Matrix[] getD() {
		return d;
	}

	public void setD(Matrix[] d) {
		this.d = d;
	}

	public Matrix[] getZ() {
		return z;
	}

	public void setZ(Matrix[] z) {
		this.z = z;
	}

	public Matrix[] getA() {
		return a;
	}

	public void setA(Matrix[] a) {
		this.a = a;
	}

	/***
	 * 反向传播
	 * @param option  配置项
	 * @param theta1      第一层            26行
	 * @param theta2      第二层	  10行
	 * @param y 结果
	 * @return
	 */
	public Matrix[] backPropagation(Matrix y) {
		
		Matrix[] thetas = this.option.getThetas();
		
		//获取所有的除了第一层的网络，当作中间结果
		//计算所有的z(假设函数)和a(概率函数)
		//结果包含第一层输入层
		for(int i = 0;i<thetas.length;i++) {
			z[i+1] = this.a[i].times(thetas[i].transpose());
			if(i == thetas.length-1) {
				a[i+1] = sigmoidFun(z[i+1]);
				continue;
			}
			a[i+1] = addBias(sigmoidFun(z[i+1]));
		}
		
		//从后往前传递偏差，相对于z的导数，没有第一层的
		for(int i = d.length-1 ; i>=0 ; i--) {
			if(i==d.length-1) {
				d[i] = a[i+1].minus(y);
				continue;
			}
			d[i] = moveBias((d[i+1].times(thetas[i+1]))).arrayTimes(diffSigmoid(z[i+1]));
		}
		
		
		/*//第一层输入层
		Matrix a_1 = this.option.getA1();
		
		//5000*401
		Matrix a1 = addBias(a_1);
		
		//5000*25
		Matrix z2 = a1.times(theta1.transpose());
		//所有的参数
		
		//5000*26,第二层,加入了偏置列
		Matrix a2 = addBias(sigmoidFun(z2));
		
		//第三层z 5000*10
		Matrix z3 = a2.times(theta2.transpose());
		
		//第三层，不需要偏置  5000*10
		Matrix a3 = sigmoidFun(z3);
		
		//第三层的偏差
		Matrix delta_3 = a3.minus(y);
		
		//第二层偏差,带偏置的
		Matrix delta_2 = (delta_3.times(theta2)).arrayTimes(diffSigmoid(z2));
		
		//去掉偏置的偏差
		delta_2 = moveBias(delta_2);	
		
		//第一层的所有的系数的偏差
		Matrix delta1 = (delta_2.transpose()).times(a1);
		
		//第二层的所有系数的偏差
		Matrix delta2 = (delta_3.transpose()).times(a2);
		
		//第一层的系数梯度,加上正则项
		Matrix gradTheta_1 = (delta1.times(1/option.getNum()))
				.plus(
						theta1.times(
								option.getLambda()/option.getNum()
								)
						);
		
		//第一层的系数梯度
		Matrix gradTheta_2 = (delta2.times(1/option.getNum()))
				.plus(
						theta2.times(
								option.getLambda()/option.getNum()
								)
						);
		
		
		Matrix gradTheta1 = noRegularBias(gradTheta_1,delta1,theta1,option.getNum(),option.getLambda());
		Matrix gradTheta2 = noRegularBias(gradTheta_2,delta2,theta2,option.getNum(),option.getLambda());
		
		Matrix[] result = new Matrix[2];
		
		result[0] = gradTheta1;
		result[1] = gradTheta2;*/
		return null;
	}
	
	
	
	/**
	 * 添加偏置列
	 * @param argument
	 * @return 左边多一列都是1的偏置
	 */
	private Matrix addBias(Matrix argument) {
		double[][] result = new double[argument.getRowDimension()][argument.getColumnDimension()+1];
		for(int i = 0;i<result.length;i++) {
			result[i][0] = 1;
			for(int j = 0;j<result[0].length-1;j++) {
				result[i][j+1] = argument.get(i, j);
			}
		}
		return new Matrix(result);
	}
	
	/**
	 * 去掉最左列偏置列
	 * @param argument
	 * @return
	 */
	private Matrix moveBias(Matrix argument) {
		double[][] result = new double[argument.getRowDimension()][argument.getColumnDimension()-1];
		for(int i = 0;i<result.length;i++) {
			for(int j = 0;j<result[0].length;j++) {
				result[i][j] = argument.get(i, j+1);
			}
		}
		return new Matrix(result);
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
			for(int j = 0;j<argument.getColumnDimension();j++) {
				result.set(i, j, 1/(1+Math.exp(0-array[i][j])));
			}
		}
		return result;
	}
	
	/**  
	 * sigmoid函数的导数
	 * @param argument
	 * @return 导数值
	 * 
	 */
	private Matrix diffSigmoid(Matrix argument) {
		double[][] array = argument.getArray();
		Matrix result = new Matrix(argument.getRowDimension(), argument.getColumnDimension());
		double ele = 0;
		double sigmodEle = 0;
		
		for(int i = 0;i<argument.getRowDimension();i++) {
			for(int j = 0;j<argument.getColumnDimension();j++) {
				ele = array[i][j];
				sigmodEle = 1/(1+Math.exp(0-ele));
				result.set(i, j, sigmodEle*(1-sigmodEle));
			}
		}
		return result;
	}
	
	/**
	 * 去除偏置的正则化
	 * @return
	 */
	public Matrix noRegularBias(Matrix grad,Matrix delta,Matrix theta,int num,double lambda) {
		for(int i = 0;i<grad.getRowDimension();i++) {
			grad.set(i, 0,delta.get(i, 0)-((lambda/num)*theta.get(i, 0)));
		}
		return grad;
	}
	
	
	
}
