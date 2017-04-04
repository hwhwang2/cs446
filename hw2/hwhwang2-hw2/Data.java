package cs446.homework2;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Data{
	double[] attributes;
	double label;
	Data(double[] att, double la){
		attributes = att;
		label = la;
	}
	Data(String line){
		String[] lineParts = line.split(",");
		attributes = new double[lineParts.length-1];
		for(int i = 0; i < lineParts.length-1; i++) attributes[i] = Double.parseDouble(lineParts[i]); 
		label = (lineParts[lineParts.length-1].equals("+"))? 1:-1;
	}
	public double[] getAttVector(){
		return attributes;
	}
	public double getLabel(){
		return label;
	}
}