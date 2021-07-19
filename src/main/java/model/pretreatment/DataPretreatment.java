package model.pretreatment;

import java.util.Arrays;

import Jama.Matrix;

/***
 * 数据预处理
 * @author LvWenJin
 *
 */
public class DataPretreatment {
	
	/***
	 * 获取归一化的数据
	 * @return 归一化的数据
	 */
	public static Matrix getNormalizationData(Matrix matrix){
		
		//特征数据归一化
		double[][] feature = matrix.transpose().getArray();
		return (new Matrix(normalization(feature))).transpose();
	}
	
	/**
	 * 获取
	 * @param array
	 * @return
	 */
	private static double[][] normalization(double[][] array) {
		
		double[][] arr = array.clone();
		
		//平均值
		double[] avg = new double[arr.length];
		
		for(int i = 0;i<arr[0].length;i++) {
			for(int j = 0;j<arr.length;j++) {
				avg[j] += (arr[j][i]/arr[0].length);
			}
		}
		
		//最大最小值的差值
		double[] result= new double[arr.length];
		
		for(int i = 0;i<arr.length;i++) {
			result[i] = 
					Arrays.stream(arr[i]).max().getAsDouble()
					-
					Arrays.stream(arr[i]).min().getAsDouble();
		}
		
		
		
		
		for(int i = 0;i<arr[0].length;i++) {
			for(int j = 0;j<arr.length;j++) {
				if(result[j] == 0) {
					arr[j][i] = 0;
					continue;
				}
				arr[j][i] = (array[j][i]-avg[j])/result[j];
			}
		}
		
		
		return arr;
	}
	
}
