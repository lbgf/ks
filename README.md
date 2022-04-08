# ks (kid script)

#### 介绍
一种基于jvm的脚本语言，内置中文化支持，可扩展第三方语系 [快速体验](#setup目录说明)

#### 版本
v0.7.17

#### 运行环境
jdk1.8

#### 脚本例子
1. 简单的例子

```
变量1 = 1; 
变量2 = 变量1 + 3; 
变量3 = "结果是：" + 变量2; // 注释 
打印 变量3;
```

2. 与java结合使用的例子

```
导入 java.lang.System;
导入 java.util.ArrayList;

变量 列表1 = 创建 ArrayList(); 
列表1.add("1");

打印 列表1.size();
System.out.println(列表1.size());
```

#### 使用说明

脚本引擎的执行方式分解释（DV）和编译（BC）两种，其中BC会生成java字节码（$\color{#FF0000}{注:如运行中报java.lang.VerifyError，可以加入vm参数-noverify后再运行}$），默认使用解释执行；

DV的使用：

1. KsRunner第二个参数是扩展语言，具体使用参考例子

```
String code = "ks脚本代码";
KsRunner kr = new KsRunner(code, null);
kr.exec();
```

BC的使用：

1. KsRunner第二个参数是扩展语言，具体使用参考例子
2. KsRunner第三个参数是脚本名称，生成的字节码会以（Script+脚本名称）的类名进行加载
3. KsRunner第四个参数是保存路径，把生成的字节码文件保存指定的目录内（注：为null时不保存，保存了也只用于查看和调试，脚本执行时不需要这些文件）

```
String code = "ks脚本代码";
KsRunner kr = new KsRunner(code, null, "脚本名称", null);
kr.exec();
```

支持关键字

<table>
	<tr>
	  <th>关键字（英）</th>
	  <th>关键字（中）</th>
	  <th>说明</th>
	  <th>DV模式</th>
	  <th>BC模式</th>
	</tr>
	<tr>
    <th>import</th>
    <th>导入</th>
    <th>导入java类，不支持*</th>
    <th>√</th>
    <th>√</th>
	</tr>
	<tr>
    <th>include</th>
    <th>包含</th>
    <th>包括其他ks脚本</th>
    <th>√</th>
    <th>×</th>
	</tr>
	<tr>
    <th>var</th>
    <th>变量</th>
    <th>变量定义</th>
    <th>√</th>
    <th>√</th>
	</tr>
	<tr>
    <th>if...else</th>
    <th>如果...否则</th>
    <th>条件判断</th>
    <th>√</th>
    <th>√</th>
	</tr>
	<tr>
    <th>while</th>
    <th>迭代</th>
    <th>while循环</th>
    <th>√</th>
    <th>√</th>
	</tr>
	<tr>
    <th>for</th>
    <th>循环</th>
    <th>for循环</th>
    <th>√</th>
    <th>√</th>
	</tr>
	<tr>
    <th>break</th>
    <th>中断</th>
    <th>中断当前循环</th>
    <th>√</th>
    <th>√</th>
	</tr>
	<tr>
    <th>continue</th>
    <th>继续</th>
    <th>回头循环开头继续执行</th>
    <th>√</th>
    <th>√</th>
	</tr>
	<tr>
    <th>class</th>
    <th>类</th>
    <th>实现类和对象功能</th>
    <th>√</th>
    <th>√</th>
	</tr>
	<tr>
    <th>return</th>
    <th>返回</th>
    <th>返回和中断方法或程序</th>
    <th>√</th>
    <th>√</th>
	</tr>
	<tr>
    <th>switch...case</th>
    <th>开关...门锁</th>
    <th>条件分支判断</th>
    <th>√</th>
    <th>×</th>
	</tr>
	<tr>
    <th>function</th>
    <th>函数</th>
    <th>实现函数功能</th>
    <th>√</th>
    <th>√</th>
	</tr>
	<tr>
    <th>extends</th>
    <th>扩展</th>
    <th>继承并扩展新的类<br>（注：不能继承java原生类）</th>
    <th>√</th>
    <th>×</th>
	</tr>
	<tr>
    <th>closure</th>
    <th>闭包</th>
    <th>闭包</th>
    <th>√</th>
    <th>×</th>
	</tr>
</table>

运算符

<table>
	<tr>
	  <th>运算符</th>
	  <th>说明</th>
	  <th>DV模式</th>
	  <th>BC模式</th>
	</tr>
	<tr>
    <th>=</th>
    <th>赋值</th>
    <th>√</th>
    <th>√</th>
	</tr>
	<tr>
    <th>==</th>
    <th>两边相等</th>
    <th>√</th>
    <th>√</th>
	</tr>
	<tr>
    <th>!=</th>
    <th>两边不等</th>
    <th>√</th>
    <th>√</th>
	</tr>
	<tr>
    <th>></th>
    <th>大于</th>
    <th>√</th>
    <th>√</th>
	</tr>
	<tr>
    <th><</th>
    <th>小于</th>
    <th>√</th>
    <th>√</th>
	</tr>
	<tr>
    <th>>=</th>
    <th>大于等于</th>
    <th>√</th>
    <th>√</th>
	</tr>
	<tr>
    <th><=</th>
    <th>小于等于</th>
    <th>√</th>
    <th>√</th>
	</tr>
	<tr>
    <th>+</th>
    <th>加</th>
    <th>√</th>
    <th>√</th>
	</tr>
	<tr>
    <th>一</th>
    <th>减</th>
    <th>√</th>
    <th>√</th>
	</tr>
	<tr>
    <th>*</th>
    <th>乘</th>
    <th>√</th>
    <th>√</th>
	</tr>
	<tr>
    <th>/</th>
    <th>除</th>
    <th>√</th>
    <th>√</th>
	</tr>
	<tr>
    <th>%</th>
    <th>取模</th>
    <th>√</th>
    <th>√</th>
	</tr>
	<tr>
    <th>||</th>
    <th>或</th>
    <th>√</th>
    <th>√</th>
	</tr>
	<tr>
    <th>&&</th>
    <th>与</th>
    <th>√</th>
    <th>√</th>
	</tr>
	<tr>
    <th><=></th>
    <th>归属</th>
    <th>√</th>
    <th>×</th>
	</tr>
</table>

基础数据类型

<table>
	<tr>
		<th>类型</th>
		<th>说明</th>
	  <th>DV模式</th>
	  <th>BC模式</th>
	</tr>
	<tr>
		<th>int</th>
		<th>整型</th>
    <th>√</th>
    <th>√</th>
	</tr>
	<tr>
		<th>long</th>
		<th>长整型</th>
    <th>√</th>
    <th>√</th>
	</tr>
	<tr>
		<th>float</th>
		<th>小数型</th>
    <th>√</th>
    <th>√</th>
	</tr>
	<tr>
		<th>double</th>
		<th>双精小数型</th>
    <th>√</th>
    <th>√</th>
	</tr>
	<tr>
		<th>boolean</th>
		<th>布尔型</th>
    <th>√</th>
    <th>√</th>
	</tr>
</table>

外部变量

<table>
	<tr>
	  <th>DV模式</th>
	  <th>BC模式</th>
	</tr>
	<tr>
    <th>√</th>
    <th>√</th>
	</tr>
</table>

数组

<table>
	<tr>
	  <th>DV模式</th>
	  <th>BC模式</th>
	</tr>
	<tr>
    <th>√</th>
    <th>×</th>
	</tr>
</table>

其它

<table>
	<tr>
		<th>标记</th>
	  <th>说明</th>
	  <th>DV模式</th>
	  <th>BC模式</th>
	</tr>
	<tr>
		<th>//</th>
	  <th>单行注释</th>
    <th>√</th>
    <th>√</th>
	</tr>
	<tr>
		<th>/* */</th>
	  <th>块注释</th>
    <th>√</th>
    <th>√</th>
	</tr>
	<tr>
		<th>" "</th>
	  <th>单行字符串</th>
    <th>√</th>
    <th>√</th>
	</tr>
	<tr>
		<th>"""<br>"""</th>
	  <th>多行字符串</th>
    <th>√</th>
    <th>√</th>
	</tr>
</table>

#### demo目录说明
>|-- src

>>|-- demo

>>>|-- astview (一个可以查看语法树的工具)

>>>|-- bc (以BC模式运行的例子)

>>>|-- dv (以DV模式运行的例子)

>>>|-- extfunc (扩展内置函数的例子)

>>>|-- language (扩展语系的例子，里面是一个日语的demo)

>>>|-- perf (性能测试，主要执行1亿次加法运算，里面有三种不同情况的测试，分别是DV模式，BC模式，BC定义值类型模式)


#### setup目录说明
为了方便使用打了个压缩包（[ks.zip](https://gitee.com/lbgf/ks/tree/master/setup)），解压后可以直接在windows运行，可以作为命令行使用，如下图

![a](https://gitee.com/lbgf/ks/raw/master/setup/1.png)

#### 参考资料
<http://www.groovy-lang.org>  
<https://github.com/chibash>  
<https://asm.ow2.io>  
