
buy_rsi = []
sell_rsi = []
profits = []

with open('../data/results.txt', 'r') as f:
    line = f.readline().strip()
    while line:
        row = line.split(',')
        buy_rsi.append(int(row[0]))
        sell_rsi.append(int(row[1]))
        profits.append(float(row[2]))
        line = f.readline().strip()

# libraries
from mpl_toolkits.mplot3d import Axes3D
import matplotlib.pyplot as plt

# plot
fig = plt.figure()
ax = fig.add_subplot(111, projection='3d')
ax.scatter(buy_rsi, sell_rsi, profits, c='blue', s=30)
ax.set_xlabel('buy RSI')
ax.set_ylabel('sell RSI')
ax.set_zlabel('Return ($)')
ax.view_init(25, 215)
plt.savefig('../data/results_plot.jpg')
plt.show()