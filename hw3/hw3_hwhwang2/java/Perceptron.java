package hw3;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import hw3.Data;
import hw3.Dataset;
public class Perceptron extends Machine {
	Perceptron(Dataset train, double learnRate, double gamma,int timesLimit){
		weights = new double[train.getData(0).getAttVector().length];
		java.util.Arrays.fill(weights,0);
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
					learn(train.getData(d), learnRate*label );
					learningError++;
					booErr = true;
				}
			}
			learningTimes++;
		}
	}
	private void learn(Data din,double coeff){
		for(int i = 0; i < weights.length; i++) {
			weights[i] = weights[i] + coeff * din.getAttVector()[i];
		}
	}
	public static void main(String[] args) throws Exception{
		// Get five datasets
		System.out.println("Perceptron");
		Dataset datas = new Dataset("./data/10_100_500_25088_False");
		Dataset test = new Dataset();
		Dataset train = new Dataset();
		datas.randDataSet(train,test,5000);
		Perceptron mchn1 = new Perceptron(train,1,0,20);
		mchn1.errorCorrectTimes(test);
	}
}
