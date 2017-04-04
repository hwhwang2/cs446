package cs446.homework2;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import cs446.homework2.Data;
public class Dataset{
	List<Data> dataList;
	Dataset(){
		dataList = new ArrayList();
	}
	Dataset(String datapath) throws Exception{
		dataList = new ArrayList();
		Scanner scanner = new Scanner(new File(datapath));
		String line ;
		while ( !scanner.nextLine().contains("@data") ){}
		while (scanner.hasNextLine()) {
		    line = scanner.nextLine();
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
}