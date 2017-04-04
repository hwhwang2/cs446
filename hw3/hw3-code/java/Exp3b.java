package hw3;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import hw3.Data;
import hw3.Dataset;
public class Exp3b{
	public static void main(String[] args) throws Exception{
		Exp3b exp = new Exp3b();
		exp.runExp("./data/10_100_1000_50000_True");
		exp.runExp("./data/10_500_1000_50000_True");
		exp.runExp("./data/10_1000_1000_50000_True");
	}
	private void runExp(String path)throws Exception{
		Machine mchn1;
		// Get datasets
		System.out.println("Input file path: " + path);
		Dataset datas = new Dataset(path);
		Dataset test = new Dataset();
		Dataset train = new Dataset();
		datas.randDataSet(train,test,5000);
		//// Perceptron wo Margin
		System.out.println("Perceptron");
		// print correct error accuracy
		System.out.println("Correct Times"+"\t"+"Error Times"+"\t"+"Accuracy"+"\t");
		mchn1 = new Perceptron(train,1,0,20);
		System.out.println( mchn1.errorCorrectTimes(test));
		//// Perceptron w Margin and learnRate
		double gamma = 1;
		double[] learnRate = new double[]{1.5,0.25,0.03,0.005,0.001};
		System.out.println("Perceptron w Margin");
		System.out.println("learnRate" +"\t"+ "Correct Times"+"\t"+"Error Times"+"\t"+"Accuracy"+"\t");
		for(double lr : learnRate){
			mchn1 = new Perceptron(train,lr,gamma,20);
			System.out.println( lr + "\t" + mchn1.errorCorrectTimes(test));
		}
		//// Winnow
		double[] alpha = new double[]{1.1,1.01,1.005,1.0005,1.0001};
		gamma = 0;
		System.out.println("Winnow");
		System.out.println("alpha" +"\t"+ "Correct Times"+"\t"+"Error Times"+"\t"+"Accuracy"+"\t");
		for(double a : alpha){
			mchn1 = new Winnow(train,a, gamma,20);
			System.out.println( a + "\t" + mchn1.errorCorrectTimes(test));
		}	
		//// Winnow w Margin
		double[] gammas = new double[]{2,0.3,0.04,0.006,0.001};
		System.out.println("Winnow w Margin");
		System.out.println("gamma" +"\t"+"alpha" +"\t"+ "Correct Times"+"\t"+"Error Times"+"\t"+"Accuracy"+"\t");
		for(double g : gammas){
			for(double a : alpha){
				mchn1 = new Winnow(train,a, g,20);
				System.out.println( g + "\t" +a + "\t" + mchn1.errorCorrectTimes(test));
			}
		}
		//// AdaGrad
		System.out.println("AdaGrad");
		System.out.println("learnRate" +"\t"+ "Correct Times"+"\t"+"Error Times"+"\t"+"Accuracy"+"\t");
		for(double lr : learnRate){
			mchn1 = new AdaGrad(train,lr,20);
			System.out.println( lr + "\t" + mchn1.errorCorrectTimes(test));
		}	
	}
}
