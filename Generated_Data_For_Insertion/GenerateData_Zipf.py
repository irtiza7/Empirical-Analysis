from numpy import random

FILE_NAME = r'data_10000000.txt'
N = 5000000
data = random.zipf(a = 2, size = N)

with open(FILE_NAME, 'a') as f:
    for value in data:
        f.write(str(value))
        f.write('\n')
