from __future__ import print_function
from gen import gen
import numpy as np
l = 10
m = 100
n = 1000
num = 50000;
noise = False
for m in  [100,500,1000]:
	for noise in [True,False]:
		if noise:
			num = 50000
			(y, x) = gen(l, m, n, num, noise)
		else:
			num = 10000
			(y, x) = gen(l, m, n, 10000, noise)
		strout = ""
		for i in range(y.size):
			f = open("../data/" + str(l)+"_"+str(m)+"_"+str(n)+"_"+str(num)+"_"+str(noise),'w')
			strout = strout+ str(y[i]) + ' ' +' '.join(map(str, x[i]))+ ' '+ "1" + "\n"
		print(strout, file = f)

