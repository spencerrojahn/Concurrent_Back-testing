# Concurrent_Back-testing
Back-test RSI stock trading strategy using multiple threads in Java.

COMANDS ARE RUN FROM *src* folder (where the *Makefile* is located).

* simply run *make* command in order to run the programs.

If you would like to run the back-testing strategy on a given ticker symbol, use the below command:
* *make TICK=\<symbol\>*, where *\<symbol\>* is replace by whatever ticker you would like to back-test (the symbol can be uppercase or lowercase).
* The current default is SPY.

Curently there is no error handling for incorrect ticker symbols. I will incorprate that soon.