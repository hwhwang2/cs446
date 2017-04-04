import NN, data_loader, perceptron

training_data, test_data = data_loader.load_circle_data()
# training_data, test_data = data_loader.load_mnist_data()

domain = 'circles'

# domain = 'mnist'
batch_size = 10
learning_rate = 0.01
activation_function = 'tanh'
hidden_layer_width = 10
data_dim = len(training_data[0][0])

filename = ["learnCurve",str(batch_size),activation_function, str(learning_rate),str(hidden_layer_width)]
filename = "_".join(filename) + ".txt"
net = NN.create_NN(domain, batch_size, learning_rate, activation_function, hidden_layer_width)
curve = net.train_with_learning_curve(training_data)
result = net.evaluate(test_data)
print(result)
file = open(filename, "w")
for pts in curve:
	file.write( str(pts[0]) +" "+ str(pts[1])+ "\n" )
print(result)
file.write( str(result) )	
file.close()

perc = perceptron.Perceptron(data_dim)
print perc.train_with_learning_curve(training_data)
print perc.evaluate(test_data)

