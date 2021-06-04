package model.linearregression.ls;

import Jama.Matrix;
import model.Model;

/**
 * 最小二乘模型
 * @author user
 *
 */
public class LSModel extends Model{

	//最高次幂
	private int n;
	
	private Matrix matrix;
	
	//是否需要常数项
	private boolean constant;
	
	//预测结果
	private double result = 0;
	
	//方差
	private double variance = 0;
	
	
	public double getVariance() {
		return variance;
	}


	public void setVariance(double variance) {
		this.variance = variance;
	}

	//公式
	private StringBuffer formula = new StringBuffer(" 拟合公式为:y = ");
	public boolean isConstant() {
		return constant;
	}


	public void setConstant(boolean constant) {
		this.constant = constant;
	}


	public LSModel(int n, Matrix matrix,boolean constant,double variance) {
		this.n = n;
		this.matrix = matrix;
		this.constant = constant;
		this.variance = variance;
	}


	public Matrix getMatrix() {
		return matrix;
	}


	public void setMatrix(Matrix matrix) {
		this.matrix = matrix;
	}


	public int getN() {
		return n;
	}


	public void setN(int n) {
		this.n = n;
	}

	
	//返回预测值
	@Override
	public double eat(double num) {
		if(this.n==1) {
			return this.matrix.get(0, 0)+this.matrix.get(1, 0)*num;
		}else {
			if(this.constant) {  //存在常数项
				
				for(int i=0;i<=n;i++) {
					result += this.matrix.get(i, 0)*Math.pow(num, i);
				}
				return result;
				
			}else {
				for(int i=0;i<n;i++) {
					result += this.matrix.get(i, 0)*Math.pow(num, i+1);
				}
				return result;
			}
		}
	}
	
	//打印公式
	@Override
	public String toString() {
		if(this.n==1) {
			return "拟合公式为:y = "+this.matrix.get(0, 0)+" + "+this.matrix.get(1, 0)+" * x"
					+ "\r\n 余差为："+this.variance;
		}else {
			if(constant) {
				for(int i=0;i<=n;i++) {
					formula.append("("+this.matrix.get(i, 0)+")");
					formula.append("*x^");
					formula.append(i);
					if(i!=n) {
						formula.append(" + ");
					}
				}
				formula.append("\r\n 余差为："+this.variance);
				return formula.toString();
			}else {
				for(int i=0;i<n;i++) {
					formula.append("("+this.matrix.get(i, 0)+")");
					formula.append("*x^");
					formula.append(i+1);
					if(i+1!=n) {
						formula.append(" + ");
					}
				}
				formula.append("\r\n 余差为："+this.variance);
				return formula.toString();
			}
		}
	}
}
