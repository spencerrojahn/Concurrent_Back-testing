
TICK = SPY

plot: run
	@echo "Plotting results ..."	
	@python3 plot.py
	
run: collect compile
	@echo "Back-testing on $(TICK) ..."
	@java -cp ../bin Main

compile:
	@echo "Compiling Java source code ..."
	@javac -d ../bin Main.java

collect:
	@echo "Collecting data for $(TICK) ..."
	@python3 collect_data.py $(TICK)