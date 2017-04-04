javac -cp ../decision-trees/lib/weka.jar -d ./bin FeatureGenerator_mod.java 
java -cp ../decision-trees/lib/weka.jar:./bin FeatureGenerator_mod ../badges/badges.modified.data.fold1 ./data/badges.fold1.arff
java -cp ../decision-trees/lib/weka.jar:./bin FeatureGenerator_mod ../badges/badges.modified.data.fold2 ./data/badges.fold2.arff
java -cp ../decision-trees/lib/weka.jar:./bin FeatureGenerator_mod ../badges/badges.modified.data.fold3 ./data/badges.fold3.arff
java -cp ../decision-trees/lib/weka.jar:./bin FeatureGenerator_mod ../badges/badges.modified.data.fold4 ./data/badges.fold4.arff
java -cp ../decision-trees/lib/weka.jar:./bin FeatureGenerator_mod ../badges/badges.modified.data.fold5 ./data/badges.fold5.arff
