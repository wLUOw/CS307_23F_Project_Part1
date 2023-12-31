# CS307_23F_Project_Part1
CS307 数据库原理2023秋季 Project Part 1

得分：93/100


## 项目内容

- Task 1: 根据给定的数据，分析并画出 E-R 图
- Task 2: 设计数据库
- Task 3: 将数据导入到数据库，并尝试对导入脚本进行优化
- Task 4: 比较 File I/O 和 DBMS 的效率差异



## 小组成员分工

Task 1 & 2  @[ElyAi](https://github.com/ElyAi)

Task 3  @[Yao1OoO](https://github.com/Yao1OoO)

Task 4  @[wLUOw](https://github.com/wLUOw) @[Yao1OoO](https://github.com/Yao1OoO) @[ElyAi](https://github.com/ElyAi)



## 项目结构

```
CS307_23F_Project_Part1
│
├── data
│   └── *.csv
│
├── doc    # project document and report
│
├── pic    # the picture used in report
│
├── properties
│   └── jdbc.properties
│
├── src
│   ├── plot
│   │   └── *.py
│   ├── task2
│   │   └── DDL.sql
│   ├── task3
│   │   └── *.java
│   └── task4
│       ├── *.java
│       └── *.py
│
├── .gitignore
├── LICENSE
└── README.md
```



## 完成任务列表

### Task 1

- [x] E-R 图

### Task 2

- [x] 设计数据库
- [x] 建表代码

### Task 3

- [x] 导入脚本
- [x] 预编译优化
- [x] 字符串处理优化
- [x] 多线程优化
- [ ] 采用Rust语言进行导入

### Task 4

- [x] 对比 File I/O 和 DBMS 的插入、删除、修改、查询
- [x] 对比使用 B-Tree 的 File I/O 和 DBMS 索引
- [x] 对比不同数据库系统的效率（pgSQL & MYSQL）
- [ ] 对比不同操作系统下的效率
- [x] 对比不同编程语言操作数据库的效率（Java & Python）
- [x] 进行数据库的并发性能测试

## 总结

这个 project 的总体难度不算高，工作量也一般。基础分比较好拿，但是想要高分还是需要比较卷的。虽然理论上分数和工作量大致呈正比，但是报告质量也非常非常重要！
其实并没有拿到一个预期的分数，~~因为总体都压分了~~，主要原因还是在于报告质量上。除了需要把自己做了些什么讲清楚，并且罗列得到的结果之外，
还需要加入一些内部和底层方面的分析，才能使报告看起来充实~~并且很长~~。
