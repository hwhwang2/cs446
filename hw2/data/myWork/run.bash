#   data/__________badges/
#			|
#			|_____decision-trees/
#			|
#			|_____badges.example.arff
#			|
#			|_____myCode/ (folder for this code)__________README
#													|
#													|_____run.bash
#													|
#													|_____Data.java	
#													|
#													|_____Dataset.java
#													|
#													|_____Sgd.java	
#													|
#													|_____FeatureGenerator_mod.java
#													|
#													|_____WekaDT2.java
#													|
#													|_____WekaDT3.java
#													|
#													|_____WekaDT4.java	
#													|
#													|_____WekaDT_Sgd.java
# Create folders
mkdir data
mkdir bin
mkdir result # in which tree information files are put
# Compile *.arff generator and Execute
javac -cp ../decision-trees/lib/weka.jar -d ./bin FeatureGenerator_mod.java 
java -cp ../decision-trees/lib/weka.jar:./bin FeatureGenerator_mod ../badges/badges.modified.data.fold1 ./data/badges.fold1.arff
java -cp ../decision-trees/lib/weka.jar:./bin FeatureGenerator_mod ../badges/badges.modified.data.fold2 ./data/badges.fold2.arff
java -cp ../decision-trees/lib/weka.jar:./bin FeatureGenerator_mod ../badges/badges.modified.data.fold3 ./data/badges.fold3.arff
java -cp ../decision-trees/lib/weka.jar:./bin FeatureGenerator_mod ../badges/badges.modified.data.fold4 ./data/badges.fold4.arff
java -cp ../decision-trees/lib/weka.jar:./bin FeatureGenerator_mod ../badges/badges.modified.data.fold5 ./data/badges.fold5.arff

# Compile stochastic gradient descent and Execute
javac -cp ../decision-trees/lib/weka.jar:bin -d ./bin Data.java			
javac -cp ../decision-trees/lib/weka.jar:bin -d ./bin Dataset.java			
javac -cp ../decision-trees/lib/weka.jar:bin -d ./bin Sgd.java
java -cp ./bin cs446.homework2.Sgd

# Compile stochastic gradient descent and Execute
javac -cp ../decision-trees/lib/weka.jar:bin -d bin Id3.java
javac -cp ../decision-trees/lib/weka.jar:bin -d bin WekaDT2.java
javac -cp ../decision-trees/lib/weka.jar:bin -d bin WekaDT3.java
javac -cp ../decision-trees/lib/weka.jar:bin -d bin WekaDT4.java
java -cp ../decision-trees/lib/weka.jar:bin cs446.homework2.WekaDT2 
java -cp ../decision-trees/lib/weka.jar:bin cs446.homework2.WekaDT3 
java -cp ../decision-trees/lib/weka.jar:bin cs446.homework2.WekaDT4 

# Compile stochastic gradient descent and Execute 
javac -cp ../decision-trees/lib/weka.jar:bin -d bin WekaDT_Sgd.java
java -cp ../decision-trees/lib/weka.jar:bin cs446.homework2.WekaDT_Sgd 
