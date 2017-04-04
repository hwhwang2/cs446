import NN, data_loader, perceptron, random

#training_data, test_data = data_loader.load_circle_data()
training_data, test_data = data_loader.load_mnist_data()
#domain = circles
domain = 'mnist'
batch_size = [10, 50, 100]
learning_rate = [0.1,0.01]
activation_function = ['tanh', 'relu']
hidden_layer_width = [10, 50]
# domain = 'mnist'
# batch_size = [10]
# learning_rate = [0.01]
# activation_function = ['relu']
# hidden_layer_width = [5]

# split training data to 5
random.shuffle(training_data)
numdata = int(len(training_data)/5)
data = [[]]*5
for m in range(5):
	data[m] = training_data[(m*numdata):((m+1)*numdata)]
# parameter tuning
file = open("parameter.txt", "w")
for b in batch_size:
	for l in learning_rate:
		for f in activation_function:
			for h in hidden_layer_width:				
				accurate = 0
				for d in range(5):
					# testing and training data
					test = data[d]
					train = []
					for tr in range(5):
						if tr != d:
							train = train + data[tr] 
					# nn training and evaluation
					net = NN.create_NN(domain, b, l, f, h)
					curve = net.train(train)
					accurate += net.evaluate(test)
				# print file
				file.write( str(b)+"_"+f+"_"+str(l)+"_"+str(h)+"\n")
				file.write( str(accurate/5)+"\n" )	
file.close()				
