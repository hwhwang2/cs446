package cs446.homework2;
import java.util.Random;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import cs446.weka.classifiers.trees.Id3;
import cs446.homework2.Data;
import cs446.homework2.Dataset;
import cs446.homework2.Sgd;
public class WekaDT_Sgd {

    public static void main(String[] args) throws Exception {
	// Parameters could be modified
	int numTree = 100;
	double sampleRatio = 0.5; // 1>=sampleRatio>0
	int treeDepth = 4;
	// Load the data
	Instances[] data = new Instances[5];
	for(int i = 1; i <=5; i++){
		data[i-1] = new Instances(new FileReader(new File("./data/badges.fold"+i+".arff")));
		// The last attribute is the class label
		data[i-1].setClassIndex(data[i-1].numAttributes() - 1);
	} 
	// Cross Validation
	double avgErrorRate = 0;
	System.out.println("Decision stumps + SGD");
	System.out.println( "testset\tError times \tCorrect times\tAccuracy" );
	for(int t = 0; t < 5; t++){
		//test dataset = data[t] train dataset = sum of others
		Instances test = data[t];
		Instances train;
		if(t == 0) train = new Instances(data[1]); else train = new Instances(data[0]);
		// adding other datasets to train
		for(int i = 1; i<5 ; i++){
			if(i != t && (t != 0 || i!=1) ){
				for(int j = 0; j < data[i].numInstances(); j++){
					train.add( data[i].instance(j) );
				}
			}
		}
		
		// Create a 100 new ID3 classifiers. 
		Id3[] classifier = new Id3[numTree];
		Random ran = new Random(System.currentTimeMillis());
		for(int i = 0; i<numTree;i++){
			// copy @train dataset. Then abandon @sampleRatio of instances in the dataset
			Instances train_sub = new Instances(train);
			for(int j = 0; j < train.numInstances()*(1-sampleRatio); j++) train_sub.delete( ran.nextInt(train_sub.numInstances()) );
			// Initialization
			classifier[i] = new Id3();
			// set the depth of the tree = 4.
			classifier[i].setMaxDepth(treeDepth);
			// Train the tree
			classifier[i].buildClassifier(train_sub);
		}
		// generate new attribute vectors from 100 trees.
		// for each instance in @train, generate new 100 attributes + '1' which means theta
		Dataset train_SGD = new Dataset();
		for(int i = 0; i< train.numInstances();i++){
			double[] newAttributes = new double[numTree+1]; 
			newAttributes[numTree] = 1; // last one is for theta
			for(int j = 0; j<numTree;j++){
				newAttributes[j] = classifier[j].classifyInstance( train.instance(i) );
			}
			train_SGD.add( new Data(newAttributes, -2 * (train.instance(i).classValue()-0.5) ));
		}
		// generate new attribute vectors from 100 trees.
		// for each instance in @test, generate new 100 attributes + '1' which means theta
		Dataset test_SGD = new Dataset();
		for(int i = 0; i< test.numInstances();i++){
			double[] newAttributes = new double[numTree+1]; 
			newAttributes[numTree] = 1; // last one is for theta
			for(int j = 0; j<numTree;j++){
				newAttributes[j] = classifier[j].classifyInstance( test.instance(i) );
			}
			test_SGD.add( new Data(newAttributes, -2 * (test.instance(i).classValue()-0.5) ));
		}
		// train sgd
		Sgd mchn1 = new Sgd(train_SGD,0.005,0.05);
		int[] result = mchn1.errorCorrectTimes(test_SGD);
		System.out.println( (t+1)+"\t"+result[0]+"\t\t"+result[1]+"\t\t"+((double)result[1])/(result[0]+result[1]));
	}
    }
}
