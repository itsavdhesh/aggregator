
# aggregator
AppLauncher is the main class for this project, please download the project and run AppLauncher.
This application recieves the trades in 100-500 ms and process aggregate it if trade with for same client and stock and  buy/sell.

If you want to release the aggregated trade to downstream, please press Enter key to publish the data to downstream (in this case, I am writing to text file)
for simulating the UI , all the received trades are displayed on the console and also printing the aggregated block if trade is getting merged.
