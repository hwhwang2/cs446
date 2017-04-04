package hw3;
import java.lang.Math;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import hw3.Data;
import hw3.Dataset;
import hw3.Machine;
public class AdaGrad extends Machine {
	List<Integer> loss01 = new ArrayList<Integer>();
	List<Double> lossHinge = new ArrayList<Double>();
	AdaGrad(Dataset train, double learnRate, int timesLimit){
		weights = new double[train.getData(0).getAttVector().length];
		java.util.Arrays.fill(weights,0);
		double[] accuG = new double[train.getData(0).getAttVector().length];
		learningError = 0;
		learningTimes = 0;
		boolean booErr = true;
		while(booErr && learningTimes < timesLimit){
			booErr = false;
			for(int d = 0; d < train.size(); d++){
				if(d % 100 == 0) errors.add(learningError);
				double[] vect = train.getData(d).getAttVector();
				double product = dot(weights,vect);
				double label = train.getData(d).getLabel();
				if( product * label <= 1  ){
					for(int i = 0; i < vect.length; i++) {
						accuG[i] = accuG[i] + (vect[i] * vect[i]);						
					}
					learn(train.getData(d), learnRate*label, accuG );
					learningError++;
					booErr = true;
				}	
			}
			learningTimes++;
			lossTest(train);
		}
	}
	public void lossTest(Dataset test){
		double[] errorCorrectTimes = new double[2];
		for(int d = 0; d < test.size(); d++){
			double[] vect = test.getData(d).getAttVector();
			double product = dot(weights,vect);
			double label = test.getData(d).getLabel();
			if( product * label <= 0 ) {
				errorCorrectTimes[0]++;
			}
			errorCorrectTimes[1]+=Math.max(0,1-product * label);
		}
		loss01.add((int)errorCorrectTimes[0]);
		lossHinge.add(errorCorrectTimes[1]);
	}

	private void learn(Data din,double coeff, double[] accuG){
		for(int i = 0; i < weights.length; i++) {
			if (accuG[i] != 0) weights[i] = weights[i] + coeff * din.getAttVector()[i] / Math.sqrt(accuG[i]);
		}
	}
	public static void main(String[] args) throws Exception{
		// Get five datasets
		System.out.println("AdaGrad");
		Dataset datas = new Dataset("./data/10_100_500_50000_False");
		Dataset test = new Dataset();
		Dataset train = new Dataset();
		datas.randDataSet(train,test,5000);
		AdaGrad mchn1 = new AdaGrad(train,1,20);
		System.out.println(mchn1.errorCorrectTimes(test));
	}
}
