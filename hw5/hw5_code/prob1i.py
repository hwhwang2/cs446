import NN, data_loader, perceptron, random
import multiprocessing as mp
import parameter_tuning_paral as tune

# training_data, test_data = data_loader.load_mnist_data()
# domain = 'mnist'
# tune.paraSweep (training_data,domain)
training_data, test_data = data_loader.load_circle_data()
domain = 'circles'
tune.paraSweep (training_data,domain)