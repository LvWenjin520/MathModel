package neuralnetworks.anns;

import Jama.Matrix;
import neuralnetworks.option.anns.AnnsOption;

/**
 * 人工神经网络
 * @author LvWenJin
 *
 */
public class Anns {
	
	
	private AnnsOption option;
	
	public AnnsOption getOption() {
		return option;
	}

	public void setOption(AnnsOption option) {
		this.option = option;
	}

	public Anns(AnnsOption option) {
		this.option = option;
	}
	
	
	
	
	//前向传播
	private void forwordPropagation() {
		
		Matrix a1 = new Matrix(5000,401);
		Matrix theta1 = new Matrix(25,401);
		
		//5000*25
		Matrix z2 = a1.times(theta1.transpose());
		
		Matrix a2 = sigmoidFun(z2);
		
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
				result.set(i, j, 1/(1+Math.exp(array[i][j])));
			}
		}
		return result;
	}
	
	
	
	
	
	
}
