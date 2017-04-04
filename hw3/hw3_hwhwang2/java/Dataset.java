package hw3;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Collections;
import hw3.Data;
public class Dataset{
	List<Data> dataList;
	Dataset(){
		dataList = new ArrayList<Data>();
	}
	Dataset(String datapath) throws Exception{
		dataList = new ArrayList<Data>();
		Scanner scanner = new Scanner(new File(datapath));
		String line ;
		while (scanner.hasNextLine()) {
		    line = scanner.nextLine();
		    if(line.length() > 5)
		    	dataList.add(new Data(line));
		}
	}
	public void add(Data d){
		this.dataList.add(d);
	}
	public void append(Dataset list){
		this.dataList.addAll(list.dataList);
	}
	public Data getData(int i){
		return dataList.get(i);
	}
	public int size(){
		return dataList.size();
	}
	public void randDataSet(Dataset train, Dataset test, int num){
		if(num*2 <= dataList.size()){
			ArrayList<Integer> list = new ArrayList<Integer>();
	        for (int i = 0; i<dataList.size(); i++) list.add(new Integer(i));
	        Collections.shuffle(list);
	    	for(int i = 0; i < num; i++ ){
	    		train.dataList.add( this.dataList.get( list.get(i) ) );
	    		test.dataList.add( this.dataList.get( list.get(i + num) ) );
	    	}
		}
	}
}