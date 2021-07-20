package neuralnetworks.option.anns;

/***
 * 人工神经网络的配置项
 * @author LvWenJin
 *
 */
public class AnnsOption {
	
	//样本数量
	private int num;

	//隐含层数量，不包括输入输出层
	private int hiddenlayersNum;
	
	//隐含层神经单元数量，索引值对应第几层隐含层
	private int[] hiddenlayerUnitNum;
	
	//输入层神经单元数量
	private int inputUnitNum;
	
	//输出层单元数量
	private int outputUnitNum;
	
	//正则项的lambda
	private int lambda;
	
	//学习率
	private double step;

	
	
	
	public int getNum() {
		return num;
	}




	public void setNum(int num) {
		this.num = num;
	}




	public int getHiddenlayersNum() {
		return hiddenlayersNum;
	}




	public void setHiddenlayersNum(int hiddenlayersNum) {
		this.hiddenlayersNum = hiddenlayersNum;
	}




	public int[] getHiddenlayerUnitNum() {
		return hiddenlayerUnitNum;
	}




	public void setHiddenlayerUnitNum(int[] hiddenlayerUnitNum) {
		this.hiddenlayerUnitNum = hiddenlayerUnitNum;
	}




	public int getInputUnitNum() {
		return inputUnitNum;
	}




	public void setInputUnitNum(int inputUnitNum) {
		this.inputUnitNum = inputUnitNum;
	}




	public int getOutputUnitNum() {
		return outputUnitNum;
	}




	public void setOutputUnitNum(int outputUnitNum) {
		this.outputUnitNum = outputUnitNum;
	}




	public int getLambda() {
		return lambda;
	}




	public void setLambda(int lambda) {
		this.lambda = lambda;
	}




	public double getStep() {
		return step;
	}




	public void setStep(double step) {
		this.step = step;
	}


	/***
	 * 测试版的
	 * @param num
	 * @param inputUnitNum
	 * @param lambda
	 * @param step
	 */
	public AnnsOption(int num, int inputUnitNum, int lambda, double step) {
		this.num = num;
		this.inputUnitNum = inputUnitNum;
		this.lambda = lambda;
		this.step = step;
	}




	/**
	 * @param num 样本数量
	 * @param hiddenlayersNum 隐含层数量
	 * @param hiddenlayerUnitNum 隐含层每层的单元数量
	 * @param inputUnitNum 输入层单元数量
	 * @param outputUnitNum 输出层单元数量
	 * @param lambda 正则项系数
	 * @param step 学习率
	 */
	public AnnsOption(int num, int hiddenlayersNum, int[] hiddenlayerUnitNum, int inputUnitNum, int outputUnitNum,
			int lambda, double step) {
		this.num = num;
		this.hiddenlayersNum = hiddenlayersNum;
		this.hiddenlayerUnitNum = hiddenlayerUnitNum;
		this.inputUnitNum = inputUnitNum;
		this.outputUnitNum = outputUnitNum;
		this.lambda = lambda;
		this.step = step;
	}




	/**
	 * 
	 * @param hiddenlayersNum 隐含层数量
	 * @param hiddenlayerUnitNum 隐含层每层的单元数量
	 * @param inputUnitNum 输入层单元数量
	 * @param outputUnitNum 输出层单元数量
	 * @param lambda 正则项系数
	 * @param step 学习率
	 */
	public AnnsOption(int hiddenlayersNum, int[] hiddenlayerUnitNum, int inputUnitNum, int outputUnitNum, int lambda,
			double step) {
		this.hiddenlayersNum = hiddenlayersNum;
		this.hiddenlayerUnitNum = hiddenlayerUnitNum;
		this.inputUnitNum = inputUnitNum;
		this.outputUnitNum = outputUnitNum;
		this.lambda = lambda;
		this.step = step;
	}
}
