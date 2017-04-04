javac -cp lib/weka.jar:bin -d ./bin Data.java			
javac -cp lib/weka.jar:bin -d ./bin Dataset.java			
javac -cp lib/weka.jar:bin -d ./bin Sgd.java
javac -cp lib/weka.jar:bin -d bin WekaDT_Sgd.java
java -cp lib/weka.jar:bin cs446.homework2.WekaDT_Sgd 