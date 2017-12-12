# MeetyouCost

一个功能极其单一和简单的方法耗时统计

## Usage

1、在project的build.gradle添加classpath

	classpath "com.meiyou:meetyoucostplugin:1.0.1"

2、在app/build.gradle添加

	apply plugin: 'meetyoucost'

在dependencies里添加

	 compile "com.meiyou:meetyoucost:1.0.1"
	 
	 
3、在需要统计的方法上加上@Cost

4、运行后在在Logcat搜索字样"MeetyouCost会输出耗时统计信息"

```java
12-12 13:45:07.676 31050-31050/com.meiyou.frameworkapp I/System.out: Usopp MeetyouCost Method:==> init()V ==>Cost:48 ms
12-12 13:45:07.887 31050-31050/com.meiyou.frameworkapp I/System.out: Usopp MeetyouCost Method:==> onCreate(Landroid/os/Bundle;)V ==>Cost:138 ms
```	 