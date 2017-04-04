package cs446.homework2;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import cs446.homework2.Data;
import cs446.homework2.Dataset;
public class Sgd{
	double[] weighs;
	double learnRate ;
	double threshold ;
	Sgd(Dataset train, double lr, double thre){
		learnRate = lr;
		threshold = thre;
		weighs = new double[train.getData(0).getAttVector().length];
		for(int i = 0; i < train.getData(0).getAttVector().length; i++) weighs[i]=0;
		int times = 0;
		while(true){
			// train through all set
			for(int d = 0; d < train.size(); d++){
				double[] vect = train.getData(d).getAttVector();
				double product = dot(weighs,vect);
				double label = train.getData(d).getLabel();
				learn(train.getData(d), learnRate*(product-label) );
			}
			// System.out.println(errorRate(train));
			if(times > 10000 || errorRate(train) <= threshold) break;
			times++;
		}
		 // System.out.println("Training iteration times = "+times);
	}
	private double dot(double[] a, double[] b){
		double sum = 0;
		for(int i = 0; i < a.length; i++) sum += a[i] * b[i];
		return sum;
	}
	private void learn(Data din,double coeff){
		for(int i = 0; i < weighs.length; i++) {
			weighs[i] = weighs[i] - coeff * din.getAttVector()[i];
		}
	}
	public double errorRate(Dataset test){
		int errorTimes = 0;
		for(int d = 0; d < test.size(); d++){
			double[] vect = test.getData(d).getAttVector();
			double product = dot(weighs,vect);
			double label = test.getData(d).getLabel();
			if( product * label < 0 ) errorTimes++;
		}
		return ((double)errorTimes)/test.size(); 
	}
	public int[] errorCorrectTimes(Dataset test){
		int[] errorCorrectTimes = new int[2];
		for(int d = 0; d < test.size(); d++){
			double[] vect = test.getData(d).getAttVector();
			double product = dot(weighs,vect);
			double label = test.getData(d).getLabel();
			if( product * label <= 0 ) errorCorrectTimes[0]++;
		}
		errorCorrectTimes[1] = test.size() - errorCorrectTimes[0];
		return errorCorrectTimes; 
	}

	private void printArray(double[] in){
		for(double d : in){
			System.out.print(d+" ");
		}
		System.out.print("\n");
	}
	public static void main(String[] args) throws Exception{
		// Get five datasets
		Dataset[] datas = new Dataset[5];
		System.out.println("SGD");
		for(int i = 1; i <=5; i++) datas[i-1] = new Dataset("./data/badges.fold"+i+".arff");
		// Sweep learning rate and threshold
		double[] l = new double[]{0.005};
		double[] t = new double[]{0.05};
		for(double lr :l){
			for(double th :t){
				System.out.println("Learn Rate = "+lr+", Error threshold = "+th);
				System.out.println( "testset\tError times \tCorrect times\tAccuracy" );
				double accu = 0;
				// Cross Validation for five combination
				for(int i = 0; i < 5; i++){
					Dataset test = new Dataset();
					Dataset train = new Dataset();
					for(int j = 0; j < 5; j++) if(j != i) train.append(datas[j]);
					test.append(datas[i]);
					Sgd mchn1 = new Sgd(train,lr,th);
					int[] result = mchn1.errorCorrectTimes(test);
					System.out.println( (i+1)+"\t"+result[1]+"\t\t"+result[0]+"\t\t"+((double)result[1])/(result[0]+result[1]));
					accu += ((double)result[1])/(result[0]+result[1]);
				}
				// print result 
				// System.out.println( accu/5 );
			}
		}
	}
}