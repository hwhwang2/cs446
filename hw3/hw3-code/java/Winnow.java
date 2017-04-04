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
public class Winnow extends Machine {
	Winnow(Dataset train, double alpha, double gamma,int timesLimit){
		int n = train.getData(0).getAttVector().length;
		weights = new double[n];
		java.util.Arrays.fill(weights,1);
		weights[n-1] = -n+1; 
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
				if( product * label <= gamma  ){
					learn(train.getData(d), alpha, label );
					learningError++;
					booErr = true;
				}
			}
			learningTimes++;
		}
	}
	private void learn(Data din, double alpha, double label ){
		// W theta is not updated 
		for(int i = 0; i < weights.length-1; i++) {
			weights[i] = weights[i] *  Math.pow(alpha, din.getAttVector()[i]*label);
		}
	}
	public static void main(String[] args) throws Exception{
		// Get five datasets
		System.out.println("Winnow");
		Dataset datas = new Dataset("../data/10_100_500_25088_False");
		Dataset test = new Dataset();
		Dataset train = new Dataset();
		datas.randDataSet(train,test,5000);
		Winnow mchn1 = new Winnow(train,1.1,0,20);
		mchn1.errorCorrectTimes(test);
		
	}
}
