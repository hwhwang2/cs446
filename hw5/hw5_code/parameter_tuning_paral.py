import NN, data_loader, perceptron, random
import multiprocessing as mp
# nn function 
# input [batch_size,learning_rate,]
# return 
def nnUnit (inputs):
	#IMPLEMENT THIS!
	print(inputs[0])
	net = NN.create_NN(inputs[0], inputs[1], inputs[2], inputs[3], inputs[4])
	net.train(inputs[5])
	return net.evaluate(inputs[6])
#endDef

def paraSweep (training_data,domain):
	batch_size = [10, 50, 100]
	learning_rate = [0.1,0.01]
	activation_function = ['tanh', 'relu']
	hidden_layer_width = [10, 50]
	# batch_size = [10]
	# learning_rate = [0.01]
	# activation_function = ['tan']
	# hidden_layer_width = [5]

	# split training data to 5
	random.shuffle(training_data)
	numdata = int(len(training_data)/5)
	data = [[]]*5
	test = [[]]*5
	train = [[]]*5
	inputs = [[]]*5
	for m in range(5):
		data[m] = training_data[(m*numdata):((m+1)*numdata)]
	for d in range(5):
		# testing and training data
		test[d] = data[d]
		train[d] = []
		for tr in range(5):
			if tr != d:
				train[d] = train[d] + data[tr] 
	# parameter tuning: # merge input arguments for parallel processing
	file = open("parameter_"+domain+".txt", "w")
	for b in batch_size:
		for l in learning_rate:
			for f in activation_function:
				for h in hidden_layer_width:				
					accurate = []
					# merge input arguments for parallel processing
					for d in range(5):
						inputs[d] = ([domain, b, l, f, h, train[d], test[d]])
					# nn training and evaluation
					num_cores = mp.cpu_count()
					pool = mp.Pool(num_cores)
					accurate = pool.map(nnUnit,inputs)
					# print file
					file.write( str(b)+"_"+f+"_"+str(l)+"_"+str(h)+"_"+str(accurate)+str(sum(accurate)/len(accurate))+"\n")
	file.close()				



