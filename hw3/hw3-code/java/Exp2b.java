package hw3;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import hw3.Data;
import hw3.Dataset;
public class Exp2b{
	public static void main(String[] args) throws Exception{
		Exp2b exp = new Exp2b();
		System.out.println("File\tPerceptron\tPerceptron w Margin\tWinnow\tWinnow w Margin\tAdaGrad");
		exp.runExp("./data/10_20_40_50000_False");
		exp.runExp("./data/10_20_80_50000_False");
		exp.runExp("./data/10_20_120_50000_False");
		exp.runExp("./data/10_20_160_50000_False");
		exp.runExp("./data/10_20_200_50000_False");
	}
	private void runExp(String path)throws Exception{
		Machine mchn1;
		// Get datasets
		System.out.print("Input file path: " + path+ "\t");
		Dataset datas = new Dataset(path);
		Dataset test = new Dataset();
		Dataset train = new Dataset();
		datas.randDataSet(train,test,2000);
		//// Perceptron wo Margin
		mchn1 = new Perceptron(train,1,0,2000);
		System.out.print( mchn1.learningError + "\t");
		//// Perceptron w Margin and learnRate
		double gamma = 1;
		mchn1 = new Perceptron(train,0.03,gamma,2000);
		System.out.print( mchn1.learningError + "\t");
		//// Winnow
		gamma = 0;
		mchn1 = new Winnow(train,1.1, gamma,2000);
		System.out.print( mchn1.learningError + "\t");
		//// Winnow w Margin
		mchn1 = new Winnow(train,1.1,2,2000);
		System.out.print( mchn1.learningError + "\t");
		//// AdaGrad
		mchn1 = new AdaGrad(train,1.5,2000);
		System.out.print( mchn1.learningError + "\n");
	}
}
