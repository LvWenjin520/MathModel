package neuralnetworks.option.anns;

/***
 * 人工神经网络的配置项
 * @author LvWenJin
 *
 */
public class AnnsOption {
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
