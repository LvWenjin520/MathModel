package testmodels;

import model.linearregression.LinearRegression;
import model.linearregression.ls.LSModel;
import model.linearregression.ls.OLS;

public class Test {
	@org.junit.Test
	public void test01() {
		
		double[][] featureData = new double[5][1];
		double[][] resultData = new double[5][1];
		
		featureData[0][0] = 1.0;
		featureData[1][0] = 2.0;
		featureData[2][0] = 3.0;
		featureData[3][0] = 4.0;
		featureData[4][0] = 5.0;
		resultData[0][0] = 1.8;
		resultData[1][0] = 2.7;
		resultData[2][0] = 3.4;
		resultData[3][0] = 3.8;
		resultData[4][0] = 3.9;
		
		LinearRegression l = new LinearRegression(featureData, resultData);
		l.test();
	}
	
	//@org.junit.Test
	public void test() {
		OLS o = new OLS();
		double[][] points = new double[5][2];
		
		double[] L1 = {1.0,1.8};
		double[] L2 = {2.0,2.7};
		double[] L3 = {3.0,3.4};
		double[] L4 = {4.0,3.8};
		double[] L5 = {5.0,3.9};
		
		points[0]=L1;
		points[1]=L2;
		points[2]=L3;
		points[3]=L4;
		points[4]=L5;
		
		
		LSModel oLS = o.oLS(points);
		System.out.println(oLS);
		System.out.println("预测结果为"+oLS.eat(3.2));
	}
	
}
