from __future__ import print_function
from gen import gen
import numpy as np
l = 10
m = 20
n = 40
num = 10000
noise = True
(y, x) = gen(l, m, n, num, noise)
strout = ""
for n in range(40,201,40):
	for i in range(y.size):
		f = open("../data/" + str(l)+"_"+str(m)+"_"+str(n)+"_"+str(num)+"_"+str(noise),'w')
		strout = strout+ str(y[i]) + ' ' +' '.join(map(str, x[i]))+ ' '+ "1" + "\n"
	print(strout, file = f)