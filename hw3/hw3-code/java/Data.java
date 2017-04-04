package hw3;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Data{
	double[] attributes;
	double label;
	Data(double[] attributes, double label){
		this.attributes = attributes;
		this.label = label;
	}
	Data(String line){
		String[] lineParts = line.split(" ");
		attributes = new double[lineParts.length-1];
		for(int i = 1; i < lineParts.length; i++) attributes[i-1] = Double.parseDouble(lineParts[i]); 
		label = Double.parseDouble(lineParts[0]);
	}
	public double[] getAttVector(){
		return attributes;
	}
	public double getLabel(){
		return label;
	}
}