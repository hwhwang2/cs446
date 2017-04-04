from __future__ import print_function
from gen import gen
import numpy as np
l = 10
m = 100
n = 500
num = 50000
noise = False
for n in  [500,1000]:
	(y, x) = gen(l, m, n, num, noise)
	strout = ""
	for i in range(y.size):
		f = open("../data/" + str(l)+"_"+str(m)+"_"+str(n)+"_"+str(num)+"_"+str(noise),'w')
		strout = strout+ str(y[i]) + ' ' +' '.join(map(str, x[i]))+ ' '+ "1" + "\n"
	print(strout, file = f)