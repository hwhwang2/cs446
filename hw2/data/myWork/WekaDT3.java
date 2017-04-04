package cs446.homework2;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import cs446.weka.classifiers.trees.Id3;

public class WekaDT3 {

    public static void main(String[] args) throws Exception {
	// Load the data
	Instances[] data = new Instances[5];
	for(int i = 1; i <=5; i++){
		data[i-1] = new Instances(new FileReader(new File("./data/badges.fold"+i+".arff")));
		// The last attribute is the class label
		data[i-1].setClassIndex(data[i-1].numAttributes() - 1);
	} 
	// Cross Validation
	double avgAccuracy = 0;
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
		// Create a new ID3 classifier. This is the modified one where you can
		// set the depth of the tree.
		Id3 classifier = new Id3();
		// An example depth. If this value is -1, then the tree is grown to full
		// depth.
		classifier.setMaxDepth(4);
		// Train
		classifier.buildClassifier(train);
		// Evaluate on the test set
		Evaluation evaluation = new Evaluation(test);
		evaluation.evaluateModel(classifier, test);
		avgAccuracy += 1-evaluation.errorRate();
		// write to resultfile
		PrintWriter writer = new PrintWriter("./result/WekaDT3_test"+t+".txt", "UTF-8");
		// Print the classfier
		writer.println(classifier);
		writer.println();
		// Print the evaluation
		writer.println(evaluation.toSummaryString());
		writer.close();
	}
	System.out.println("Decision tree of depth 4");
	System.out.println("\tAverage Accuracy = "+(avgAccuracy/5));
    }
}
