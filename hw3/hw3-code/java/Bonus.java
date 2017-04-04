package hw3;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import hw3.Data;
import hw3.Dataset;
public class Bonus{
	public static void main(String[] args) throws Exception{
		Bonus exp = new Bonus();
		exp.runExp("./data/10_20_40_10000_True");
		exp.runExp("./data/10_20_80_10000_True");
		exp.runExp("./data/10_20_120_10000_True");
		exp.runExp("./data/10_20_160_10000_True");
		exp.runExp("./data/10_20_200_10000_True");
	}
	private void runExp(String trainpath)throws Exception{
		AdaGrad mchn1;
		// Get datasets
		System.out.println("Input file path: " + trainpath);
		Dataset train = new Dataset(trainpath);
		mchn1 = new AdaGrad(train,0.25,50);
		System.out.print( "rounds"+"\t");
		for(int i = 1; i <= mchn1.lossHinge.size();i++) System.out.print( i+"\t");
		System.out.print( "\n"+"Hinge loss"+"\t");
		for(double out : mchn1.lossHinge) System.out.print( out+"\t");
		System.out.print( "\n"+"0-1 loss"+"\t");
		for(double out : mchn1.loss01) System.out.print( out+"\t");
		System.out.print( "\n");	
	}
}
