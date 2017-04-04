#!/bin/bash
#   hw3_hwhwang2/_____README
#			|
#			|_____run.bash (This file)
#			|
#			|_____gendata.bash
#			|
#			|_____hw3_hwhwang2.pdf
#			|
#			|_____exp.xls
#			|
#			|_____python/
#			|
#			|_____ java/ 

#### Run gendata.bash to generate datasets needed before running this bash file ####
# Create folders
mkdir ./bin
mkdir ./result
# Compile and Execute
javac -d ./bin ./java/Data.java
javac -cp ./bin -d ./bin ./java/Dataset.java 
javac -cp ./bin -d ./bin ./java/Machine.java 
javac -cp ./bin -d ./bin ./java/Perceptron.java 
javac -cp ./bin -d ./bin ./java/Winnow.java 
javac -cp ./bin -d ./bin ./java/AdaGrad.java 
javac -cp ./bin -d ./bin ./java/Exp1b.java 
javac -cp ./bin -d ./bin ./java/Exp1c.java 
javac -cp ./bin -d ./bin ./java/Exp2a.java 
javac -cp ./bin -d ./bin ./java/Exp2b.java 
javac -cp ./bin -d ./bin ./java/Exp3b.java 
javac -cp ./bin -d ./bin ./java/Exp3c.java 
javac -cp ./bin -d ./bin ./java/Bonus.java 
# Put all result files in /result folder
echo Working on Exp1b
java -cp ./bin hw3.Exp1b > ./result/Exp1b.txt
echo Working on Exp1c
java -cp ./bin hw3.Exp1c
echo Working on Exp2a
java -cp ./bin hw3.Exp2a > ./result/Exp2a.txt 
echo Working on Exp2b
java -cp ./bin hw3.Exp2b > ./result/Exp2b.txt
echo Working on Exp3b
java -cp ./bin hw3.Exp3b > ./result/Exp3b.txt 
echo Working on Exp3c
java -cp ./bin hw3.Exp3c > ./result/Exp3c.txt
echo Working on Bonus
java -cp ./bin hw3.Bonus > ./result/Bonus.txt

