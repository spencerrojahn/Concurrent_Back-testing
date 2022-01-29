
TICK = SPY
TICK_UPPER  = $(shell echo $(TICK) | tr  '[:lower:]' '[:upper:]')

plot: run
	@echo "Plotting results ..."	
	@python3 plot.py
	
run: collect compile
	@echo "Back-testing on $(TICK_UPPER) ..."
	@java -cp ../bin Main

compile:
	@echo "Compiling Java source code ..."
	@javac -d ../bin Main.java

collect:
	@echo "Collecting data for $(TICK_UPPER) ..."
	@python3 collect_data.py $(TICK_UPPER)