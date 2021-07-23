package testmodels;

import java.io.IOException;
import java.util.Map;

import Jama.Matrix;
import datamanager.json.JsonDataManager;
import model.linearregression.LinearRegression;
import model.linearregression.ls.LSModel;
import model.linearregression.ls.OLS;
import model.logisticregression.LogisticRegression;
import neuralnetworks.anns.Anns;
import neuralnetworks.option.anns.AnnsOption;
import neuralnetworks.utils.Utils;

public class Test {
	
	@org.junit.Test
	public void test04() throws IOException {
		//图片灰度化成功
		//Utils.imageToGray("D:\\image\\1", "jpeg");
		
		//Utils.testImageToGray("D:\\image\\1\\2", "jpeg");
		
		//Utils.imageToGray("D:\\1", "jpeg");
		AnnsOption a = new AnnsOption("D:\\1", 1, new int[] {100}, 3, 2, 0.3);
		
		//System.out.println();
		
		Anns anns = new Anns(a);
		
		
		Matrix y = Utils.createLabelMatrix("D:\\1");
		
		
		
		//Matrix randomMatrix = Utils.randomMatrix(22, 10, 10);
		
		anns.train(y);
		
		Matrix predict = anns.predict("D:\\2");
		
		for(int i = 0;i<predict.getRowDimension();i++) {
			System.out.println();
			for(int j = 0;j<predict.getColumnDimension();j++) {
				System.out.print(predict.get(i, j)+"  ");
			}
		}
		
	}
	//@org.junit.Test
	public void test05() throws IOException {
		Utils.testImageToGray("D:\\2", "jpeg");
	}
	
	//@org.junit.Test
	public void test02() {
		
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
		
	
	public void test03() throws IOException {
		Map<String, Matrix> trainingDataSet = JsonDataManager.getTrainingDataSet("D:\\MachineLearing\\DataSet\\iris-copy.json");
		LogisticRegression lr = new LogisticRegression();
		Matrix theta = lr.twoFlagClassification(trainingDataSet.get("feature"), trainingDataSet.get("flag"), 0.002);
		
		Map<String, Matrix> testDataSet = JsonDataManager.getTrainingDataSet("D:\\MachineLearing\\DataSet\\iris-test.json");
		Matrix testData = testDataSet.get("feature");
		Matrix times = testData.times(theta);
		for(int i = 0;i<times.getRowDimension();i++) {
			double exp = 1/(1+Math.exp(0-times.get(i, 0)));
			times.set(i, 0, exp);
			System.out.println(times.get(i, 0));
		}
	}
	
	//@org.junit.Test
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
