# MeetyouCost

一个功能极其单一和简单的方法耗时统计

## Usage

####1、在project的build.gradle添加classpath

	classpath "com.meiyou:meetyoucostplugin:1.0.19"

####2、在app/build.gradle添加

	apply plugin: 'meetyoucost'

在dependencies里添加

	 compile "com.meiyou:meetyoucost:1.0.19@aar"
	 
	 
####3、在需要统计的方法上加上@Cost

```java
@Cost
public void init() {
}
```
运行后在在Logcat搜索字样"MeetyouCost会输出耗时统计信息"

```java
 I/System.out: Usopp MeetyouCost Method:==> init()V ==>Cost:48 ms
 I/System.out: Usopp MeetyouCost Method:==> onCreate(Landroid/os/Bundle;)V ==>Cost:138 ms
```

####4、可设置日志实时监听
```java
MeetyouCost.setLogListener(new MeetyouCost.onLogListener() {
      @Override
      public void log(String log) {
      }
});
```

####4、可设置日志实时缓存
```java
MeetyouCost.openLogCache(true);
```
然后可以在任意位置通过MeetyouCost.getLogCache获取整体日志

####4、可视化UI
```java
MeetyouCost.openLogUI(getApplicationContext(),true);
```


## [License Apache-2.0](LICENSE)