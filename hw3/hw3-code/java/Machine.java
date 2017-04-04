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
public class Machine {
	double[] weights;
	int learningError;
	int learningTimes;
	List<Integer> errors = new ArrayList<Integer>();
	Machine(){
	
	}
	public double dot(double[] a, double[] b){
		double sum = 0;
		for(int i = 0; i < a.length; i++) sum += a[i] * b[i];
		return sum;
	}

	public double errorRate(Dataset test){
		int errorTimes = 0;
		for(int d = 0; d < test.size(); d++){
			double[] vect = test.getData(d).getAttVector();
			double product = dot(weights,vect);
			double label = test.getData(d).getLabel();
			if( product * label < 0 ) errorTimes++;
		}
		return ((double)errorTimes)/test.size(); 
	}

	public String errorCorrectTimes(Dataset test){
		int[] errorCorrectTimes = new int[2];
		for(int d = 0; d < test.size(); d++){
			double[] vect = test.getData(d).getAttVector();
			double product = dot(weights,vect);
			double label = test.getData(d).getLabel();
			if( product * label <= 0 ) errorCorrectTimes[0]++;
		}
		errorCorrectTimes[1] = test.size() - errorCorrectTimes[0];
		return errorCorrectTimes[1]+"\t"+errorCorrectTimes[0]+"\t"+((double)errorCorrectTimes[1])/(errorCorrectTimes[0]+errorCorrectTimes[1])+"\t"; 
	}
	public void printArray(double[] in){
		for(double d : in){
			System.out.print(d+" ");
		}
		System.out.print("\n");
	}

}
