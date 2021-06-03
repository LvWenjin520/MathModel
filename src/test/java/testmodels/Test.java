package testmodels;

import Jama.Matrix;
import model.linearregression.ls.OLS;

public class Test {

	@org.junit.Test
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
		
		
		Matrix oLS1 = o.oLS(points,3,false);
		
		for(int i = 0;i<oLS1.getRowDimension();i++) {
			System.out.println(oLS1.get(i, 0));
		}
	}
	
}
