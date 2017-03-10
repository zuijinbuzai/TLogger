# TLogger
a class help to record the code used time.

## Install ##

### From repositories ###
Add jcenter to project repositories

```groovy
maven {
    url 'https://dl.bintray.com/zuijinbuzai/android/'
}
```

Add compile line to project dependencies

```groovy
compile 'com.zuijinbuzai:TLogger:1.0.0'
```

# Usage
```java
TLogger mTLogger = new TLogger();
mTLogger.begin("doTask");
//doTaskA
mTLogger.addSplit("doTaskA");
//doTaskB
mTLogger.addSplit("doTaskB");
mTLogger.dumpToLog();
```

```java
I/TLogger: at MainActivity$2.run(MainActivity.java:33)             doTask: begin ----------------------------------------------------
I/TLogger: at MainActivity.doTaskA(MainActivity.java:45)           doTask:           87 ms, doTaskA
I/TLogger: at MainActivity.doTaskB(MainActivity.java:50)           doTask:           10 ms, doTaskB
I/TLogger: at MainActivity.doTaskVeryLongHaha(MainActivity.java:55)doTask:           79 ms, doTaskVeryLongHaha
I/TLogger: at MainActivity.doTaskC(MainActivity.java:60)           doTask:           26 ms, doTaskC
I/TLogger: at MainActivity$2.run(MainActivity.java:38)             doTask: end,     202 ms ------------------------------------------
```
