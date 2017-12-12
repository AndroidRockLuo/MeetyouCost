# MeetyouCost

一个功能极其单一和简单的方法耗时统计

## Usage

1、在project的build.gradle添加classpath

	classpath "com.meiyou:meetyoucostplugin:1.0.2"

2、在app/build.gradle添加

	apply plugin: 'meetyoucost'

在dependencies里添加

	 compile "com.meiyou:meetyoucost:1.0.2"
	 
	 
3、在需要统计的方法上加上@Cost

4、运行后在在Logcat搜索字样"MeetyouCost会输出耗时统计信息"

```java
 I/System.out: Usopp MeetyouCost Method:==> init()V ==>Cost:48 ms
 I/System.out: Usopp MeetyouCost Method:==> onCreate(Landroid/os/Bundle;)V ==>Cost:138 ms
```

## [License Apache-2.0](LICENSE)