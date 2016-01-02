## ClassLoader 简介 ##

ClassLoader 是用来加载 class 文件到 JVM 以供程序使用的。Java 程序可以动态加载类定义，而这个动态加载的机制就是通过 ClassLoader 来实现的。

程序在启动的时候，并不会一次性加载程序所要用到的所有 class 文件，而是根据需要，通过Java 的类加载机制来动态加载某个 class 文件到 JVM 中，只有 class 文件被装入到内存之后，才能被其他 class所引用。


Java默认提供的三个ClassLoader

* Bootstrap ClassLoader

	启动类加载器，是 Java 类加载层次中最顶层的类加载器，负责加载 JDK 中的核心类库，主要是 %JAVA_HOME%/jre/lib 目录下的 jar 包和 class。

* Extension ClassLoader

	扩展类加载器，负责加载 Java 的扩展类库，默认加载 %JAVA_HOME%/jre/lib/ext 目录下的 jar 包和 class。

* App ClassLoader

	系统类加载器，负责加载应用程序 classpath 目录下的所有 jar 和 class 文件。


当运行一个程序的时候，JVM 启动，运行 BootstrapClassLoader，加载 Java 核心 API（ExtClassLoader 和 AppClassLoader 也在此时被加载），然后调用 ExtClassLoader 加载扩展 API，最后 AppClassLoader 加载 classpath 目录下定义的 class，这就是一个程序基本的加载流程。需要注意的是，BootstrapClassLoader 是 JVM 级别的，由 C++ 撰写，而另外两个都是 Java 类。


### ClassLoader加载类原理

* ClassLoader 加载类用的是全盘负责委托机制

	即当一个 ClassLoader 加载一个 class 的时候，这个 class 所依赖和引用的所有 class 也由这个 ClassLoader 负责载入，除非是显式的使用另外一个 ClassLoader。

* ClassLoader 使用双亲委托模型来搜索类

	每个 ClassLoader 实例都有一个父加载器的引用（是加载它的那个 ClassLoader，不是它继承的 ClassLoader），BootstrapClassLoader 没有父加载器，但可以作为其他 ClassLoader 实例的父加载器。

	当一个 ClassLoader 实例需要加载某个类时，它会先把这个任务委托给它的父加载器，这个过程是由上至下依次检查的，首先由最顶层的类加载器 BootstrapClassLoader 尝试加载，如果没有加载到，则把任务转交给 ExtClassLoader，若没加载到则转交给 AppClassLoader……如果都没加载到则一直转交到委托发起者，由它去加载。如果都没有加载到这个类的话，则抛出 ClassNotFoundException。

	使用双亲委托模型的好处是避免重复加载，当父加载器已经加载过某类之后，子加载器就没有必要再加载一次。另外这种方式还可以提高安全性，避免自定义的类替换 Java 核心 API 的可能。

* 自定义 ClassLoader

	因为 Java 中提供的默认 ClassLoader 只能加载指定目录下的 jar 和 class，如果想加载其他位置的类或 jar 时，比如要加载网络上的一个 class 文件，则需要定义自己的 ClassLoader。

	自定义类加载器主要用途有：运行时决定是否加载某类、改变 class 文件字节码的加载方式（使得 class 文件字节码可以加密存储）、运行时修改已加载的字节码（比如 AOP 实现切面织入）。
	
	目前常用 web 服务器中都定义了自己的类加载器，用于加载 web 应用指定目录下的类库。具体示例见 NetworkClassLoader。
