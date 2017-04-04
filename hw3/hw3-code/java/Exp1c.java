package hw3;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import hw3.Data;
import hw3.Dataset;
import java.io.PrintWriter;
public class Exp1c{
	public static void main(String[] args) throws Exception{
		Exp1c exp = new Exp1c();
		exp.runExp("./data/10_100_500_50000_False", "./result/Exp1c_n_500.txt",0.005);
		exp.runExp("./data/10_100_1000_50000_False", "./result/Exp1c_n_1000.txt",0.03);
	}
	private void runExp(String filepath,String writepath,double lrP)throws Exception{
		PrintWriter writer = new PrintWriter(writepath, "UTF-8");
		Machine mchn1;
		StringBuffer sb;
		// Get datasets
		writer.println("Input file path: " + filepath);
		writer.print("N\t");
		for(int i = 0; i < 500; i++){
			writer.print(i*100 + "\t");
		}
		writer.print("\n");
		Dataset train = new Dataset(filepath);
		//// Perceptron wo Margin
		writer.print("Perceptron\t");
		mchn1 = new Perceptron(train,1,0,1);
		sb= new StringBuffer();
		for(int e : mchn1.errors) sb.append(e+"\t");
		writer.println(sb.toString());
		//// Perceptron w Margin and learnRate
		writer.print("Perceptron w Margin\t");
		mchn1 = new Perceptron(train,lrP,1,1);
		sb= new StringBuffer();
		for(int e : mchn1.errors) sb.append(e+"\t");
		writer.println(sb.toString());
		//// Winnow
		writer.print("Winnow\t");
		mchn1 = new Winnow(train,1.1,0,1);
		sb= new StringBuffer();
		for(int e : mchn1.errors) sb.append(e+"\t");
		writer.println(sb.toString());
		//// Winnow w Margin
		writer.print("Winnow w Margin\t");
		mchn1 = new Winnow(train,1.1,2,1);
		sb= new StringBuffer();
		for(int e : mchn1.errors) sb.append(e+"\t");
		writer.println(sb.toString());
		//// AdaGrad
		writer.print("AdaGrad\t");
		mchn1 = new AdaGrad(train,0.25,1);
		sb= new StringBuffer();
		for(int e : mchn1.errors) sb.append(e+"\t");
		writer.println(sb.toString());
		writer.close();	
	}
}
