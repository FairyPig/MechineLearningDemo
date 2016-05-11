package BP;

import java.util.Random;

public class BP {
	int innum;
	int hiddennum;
	int outnum;
	//输入、隐藏、输出层
	public double[] input;
	public double[] hidden;
	public double[] output;
	
	//用户输出真实值
	public double[] realoutput;
	
	//v[i,j]表示输入层i到隐层j  w[i,j]表示隐层i到输出层j
	public double[][] v;
	public double[][] w;
	
	//beta为隐层的阈值，afa为输出层阈值
	public double[] beta;
	public double[] afa;
	
	//学习率
	public double eta;
	//步长
	public double momentum;
	public final Random random;
	
	public BP(int inputnum,int hiddennum,int outputnum,double learningrate){
		innum = inputnum;
		this.hiddennum = hiddennum;
		outnum = outputnum;
		
		input = new double[inputnum + 1];
		hidden = new double[hiddennum + 1];
		output = new double[outputnum + 1];
		realoutput = new double[outputnum + 1];
		
		v = new double[inputnum + 1][hiddennum + 1];
		w = new double[hiddennum + 1][outputnum + 1];
		
		beta = new double[outputnum + 1];
		afa = new double[hiddennum + 1];
		for(int i=0;i<outputnum;i++)
			beta[i] = 0.0;
		for(int i=0;i<hiddennum;i++)
			afa[i] = 0.0;
		
		eta = learningrate;
		random = new Random(19950326);
		randomizeWeights(w);  
        randomizeWeights(v);
	}
	
	public void testData(double[] in){
		input = in;
		getNetOutput();
	}
	public int predict(double[] in){
		testData(in);
		if(output[0]>0.5)
			return 1;
		else
			return 0;
	}
	public double getAccuracy(double[][] in,double[][] out){
		int rightans = 0,wrongans = 0;
		for(int i=0;i<in.length;i++){
			if(predict(in[i])==(out[i][0])){
				//System.out.println("预测结果："+predict(in[i])+" 实际结果为："+out[i][0]);
				rightans++;
			}else{
				//System.out.println("预测结果："+predict(in[i])+" 实际结果为："+out[i][0]);
				wrongans++;
			}
		}
		System.out.println("对："+rightans+" 错："+wrongans);
		return (double)rightans/(double)(rightans+wrongans);
	}
	public void train(int times){
		TrainData t = new TrainData();
		double wu = 0.0,acc = 0.0;
		int n = t.traindata.length;
		for(int i=0;i<times;i++){
			wu = 0.0;
			for(int j=0;j<n;j++){
				traindata(t.traindata[j],t.traindataoutput[j]);
				wu += getDeviation();
			}
			wu = wu/((double)n);
			System.out.println("第"+i+"轮训练："+wu);
			acc = getAccuracy(t.testdata,t.testdataoutput);
			System.out.println("预测正确率为： "+acc);
		}
	}
	public void traindata(double[] in,double[] out){
		input = in;
		realoutput = out;
		getNetOutput();
		adjustParameter();
	}
	public double getDeviation(){
		double e = 0.0;
		for(int i=0;i<outnum;i++)
			e += (output[i] - realoutput[i])*(output[i] - realoutput[i]);
		e *= 0.5;
		return e;
	}
	public void adjustParameter(){
		double g[],e = 0.0;
		g = new double[outnum];
		int i,j;
		for(i=0;i<outnum;i++){
			g[i] = output[i]*(1-output[i])*(realoutput[i]-output[i]);
			beta[i] -= eta * g[i];
			for(j=0;j<hiddennum;j++){
				w[j][i] += eta * g[i] * hidden[j];
			}
		}
		for(i=0;i<hiddennum;i++){
			e = 0.0;
			for(j=0;j<outnum;j++)
				e += g[j]*w[i][j];
			e = hidden[i]*(1-hidden[i])*e;
			afa[i] -= eta * e;
			for(j=0;j<innum;j++)
				v[j][i] += eta * e * input[j];
		}
	}
	public void getNetOutput(){
		int i,j;
		double tmp=0.0;
		for(i=0;i<hiddennum;i++){
			tmp = 0.0;
			for(j=0;j<innum;j++)
				tmp += v[j][i]*input[j];
			hidden[i] = sigmoid(tmp-afa[i]);
		}
		for(i=0;i<outnum;i++){
			tmp = 0.0;
			for(j=0;j<hiddennum;j++)
				tmp += w[j][i]*hidden[j];
			output[i] = sigmoid(tmp-beta[i]);
		}
	}
	
	private void randomizeWeights(double[][] matrix) {  
        for (int i = 0, len = matrix.length; i != len; i++)  
            for (int j = 0, len2 = matrix[i].length; j != len2; j++) {  
                double real = random.nextDouble();  
                matrix[i][j] = random.nextDouble() > 0.5 ? real : -real;  
            }
    }
	public void debug(){
		System.out.println("========begin=======");
		for(int i=0;i<innum;i++){
			for(int j=0;j<hiddennum;j++)
				System.out.print(v[i][j]+" ");
			System.out.println();
		}
		System.out.println();
		for(int i=0;i<hiddennum;i++){
			for(int j=0;j<outnum;j++)
				System.out.print(w[i][j]+" ");
			System.out.println();
		}
		System.out.println("========end=======");
	}
	public double sigmoid(double z){
		double s = 0.0;
		s = 1d/(1d + Math.exp(-z));
		return s;
	}
	
	public static void main(String[] args){
		BP bp = new BP(8,10,1,0.1);
		bp.train(50);
	}
}
