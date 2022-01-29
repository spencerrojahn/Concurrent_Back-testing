import requests
import pandas as pd
import sys

ticker = sys.argv[1]

url = 'https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol='+ticker+'&apikey=7UKTKN8IXYXAT5KP'
r = requests.get(url)
data = r.json()
df = pd.DataFrame.from_dict(data['Time Series (Daily)'], orient='index')
df.columns = ['OPEN', 'HIGH', 'LOW', 'CLOSE', 'VOLUME']
df = df.iloc[::-1]

url2 = 'https://www.alphavantage.co/query?function=RSI&symbol='+ticker+'&interval=daily&time_period=14&series_type=close&apikey=7UKTKN8IXYXAT5KP'
r2 = requests.get(url2)
data2 = r2.json()
df2 = pd.DataFrame.from_dict(data2['Technical Analysis: RSI'], orient='index')
df2 = df2.iloc[-100:]

df = df2.join(df['CLOSE'])

with open('../data/RSI_close.txt', 'w') as f:
    for index, row in df.iterrows():
        close = row['CLOSE']
        rsi = row['RSI']
        f.write(f'{index},{close},{rsi}\n')
    
