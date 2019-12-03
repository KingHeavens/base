## JSR-133使用happens-before来阐述线程间操作内存可见性

### 1、程序顺序规则
    一个线程中的每个操作happens-before于该线程中的后续操作。
### 2、监视器锁规则
    对于一个锁的解锁happens-before于随后对该锁的加锁。
### 3、volatile变量规则
    对于一个volatile域的写happens-before于任意后续对这个volatile域的读。
### 4、传递性
    A happens-before B   B happends-before C  -> A happends-before C
    
 
### 可见性

### 程序真实执行顺序
#### 1、重排序
编译器和处理器为了优化程序的性能而对指令序列红心排序的一种手段。
#### 2、顺序一致性
顺序一致性内存模型是一个理论内存模型，处理器的内存模型和编程语言的内存模型都会以顺序一致性内存模型作为参照。

顺序一致性问题
在一个线程中写一个变量，在另一个线程中读同一个变量，写和读没有通过同步来排序。


### 常用同步原语
synchronized volatile final

