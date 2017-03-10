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
## screenshot
<img src='https://github.com/zuijinbuzai/TLogger/blob/master/image/log.png'/>
