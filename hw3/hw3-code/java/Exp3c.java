package hw3;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import hw3.Data;
import hw3.Dataset;
public class Exp3c{
	public static void main(String[] args) throws Exception{
		Exp3c exp = new Exp3c();
		exp.runExp("./data/10_100_1000_50000_True", "./data/10_100_1000_10000_False", 0.005,1.1,1.1, 0.006,0.25);
		exp.runExp("./data/10_500_1000_50000_True", "./data/10_500_1000_10000_False", 0.03,1.1,1.1, 0.04,1.5);
		exp.runExp("./data/10_1000_1000_50000_True", "./data/10_1000_1000_10000_False",0.03,1.1,1.1, 0.04,1.5);
	}
	private void runExp(String trainpath, String testpath, double lrP, double alpha,double alphaM, double gammaM,double lrAdaG )throws Exception{
		Machine mchn1;
		// Get datasets
		System.out.println("Input file path: " + trainpath);
		Dataset test = new Dataset(testpath);
		Dataset train = new Dataset(trainpath);
		//// Perceptron wo Margin
		System.out.println("Perceptron");
		// print correct error accuracy
		System.out.println("Correct Times"+"\t"+"Error Times"+"\t"+"Accuracy"+"\t");
		mchn1 = new Perceptron(train,1,0,20);
		System.out.println( mchn1.errorCorrectTimes(test));
		//// Perceptron w Margin and learnRate
		System.out.println("Perceptron w Margin");
		System.out.println("learnRate" +"\t"+ "Correct Times"+"\t"+"Error Times"+"\t"+"Accuracy"+"\t");
		mchn1 = new Perceptron(train,lrP,1,20);
		System.out.println(  mchn1.errorCorrectTimes(test));
		
		//// Winnow
		System.out.println("Winnow");
		System.out.println("alpha" +"\t"+ "Correct Times"+"\t"+"Error Times"+"\t"+"Accuracy"+"\t");
		mchn1 = new Winnow(train,alpha, 0,20);
		System.out.println(mchn1.errorCorrectTimes(test));

		//// Winnow w Margin
		System.out.println("Winnow w Margin");
		mchn1 = new Winnow(train,alphaM, gammaM,20);
		System.out.println( mchn1.errorCorrectTimes(test));
		//// AdaGrad
		System.out.println("AdaGrad");
		mchn1 = new AdaGrad(train,lrAdaG,20);
		System.out.println(  mchn1.errorCorrectTimes(test));
			
	}
}
