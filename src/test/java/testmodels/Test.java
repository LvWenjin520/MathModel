package testmodels;

import Jama.Matrix;
import model.linearregression.ls.LSModel;
import model.linearregression.ls.OLS;
import model.logisticregression.LogisticRegression;

public class Test {
	@org.junit.Test
	public void test01() {
		
		double[][] d = new double[3][1];
		d[0][0] = 1;
		d[1][0] = 1;
		d[2][0] = 1;
		
		Matrix a = new Matrix(d);
	
		a.timesEquals(33);
		
		System.out.println(a.get(0,0));
		System.out.println(a.get(1,0));
		System.out.println(a.get(2,0));
		
	
	}
	
	//@org.junit.Test
	public void test() {
		OLS o = new OLS();
		double[][] points = new double[7][2];
		
		double[] L1 = {4.0,1.58};
		double[] L2 = {6.0,2.08};
		double[] L3 = {10.0,2.8};
		double[] L4 = {12.0,3.1};
		double[] L5 = {14.0,3.4};
		double[] L6 = {16.0,3.8};
		double[] L7 = {18.0,4.32};
		
		points[0]=L1;
		points[1]=L2;
		points[2]=L3;
		points[3]=L4;
		points[4]=L5;
		points[5]=L6;
		points[6]=L7;
		
		
		LSModel oLS = o.oLS(points);
		System.out.println(oLS);
		System.out.println("预测结果为"+oLS.eat(3.2));
	}
	
}
