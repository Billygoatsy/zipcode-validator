# zipcode-validator

多国邮政编码格式校验器 —— 用**对象适配器模式（Object Adapter）**在不改动原有代码的前提下接入中国邮编校验。

课程项目：《设计模式 / Java 面向对象程序设计》作业 3.2-2。

## 功能

| 国家 | 格式 | 合法示例 | 非法示例 |
| --- | --- | --- | --- |
| 美国 | `ddddd-dddd`（5 位 + 连字符 + 4 位） | `12345-6789` | `123456789`、`1234-67890` |
| 加拿大 | `CDC DCD`（字母数字交替，空格可省略） | `K1A 0B1`、`K1A0B1` | `K1A-0B1`、`K1A 0B` |
| 中国 | 6 位数字 | `150001`、`150 001` | `15000`、`1500011`、`15000a` |

输入中的空格会被忽略（与美/加两国的处理保持一致）。

## 目录结构

```
zipcode-validator/
├── src/
│   ├── ZipCodeValidator.java          // 目标接口 Target
│   ├── USAZipCode.java                // 美国格式（直接实现接口）
│   ├── CanadianPostalCode.java        // 加拿大格式（被适配者，课程原始代码）
│   ├── CAPostalCodeAdapter.java       // 加拿大适配器（课程原始代码）
│   ├── ChinesePostalCode.java         // 中国格式（被适配者 Adaptee，本次新增）
│   ├── ChinesePostalCodeAdapter.java  // 中国适配器（Adapter，本次新增）
│   ├── Customer.java                  // 客户端：按国家分派
│   ├── Provinces.java                 // 省/州下拉数据
│   └── ZipCodeTester.java             // Swing 界面
├── docs/
│   └── class-diagram.png              // 类图
└── README.md
```

## 编译与运行

环境：JDK 8 及以上（开发使用 JDK 19，按 Java 8 字节码编译）。

```bash
cd src
javac -encoding UTF-8 *.java
java ZipCodeTester
```

界面操作：选择 Country（US / Canada / China）→ 选择 State/Province → 输入 Zip/Postal Code → 点击 Validate，Result 显示 `Valid Zip/Postal Code Format` 或 `Invalid Zip/Postal Code Format`。

## 设计要点

- **目标接口** `ZipCodeValidator.isValidZipCode(String zip, String state)`；
- **被适配者** `ChinesePostalCode.isValidChinesePCode(String pcode)`：单参数、不实现接口；
- **适配器** `ChinesePostalCodeAdapter`：实现目标接口，内部持有 `ChinesePostalCode` 实例，把双参数请求转换成单参数调用（`state` 对中国邮编无意义，被裁剪掉）；
- **客户端** `Customer` 只依赖 `ZipCodeValidator` 接口，新增国家格式只需增加一个适配器类，无需修改已有校验逻辑。

## 测试用例

共 23 条用例，全部通过（中国 10 / 美国 4 / 加拿大 4 / 跨国家分派与未知国家 5），边界覆盖：

- `150001` 合法、`150 001`（含空格）合法
- `15000`（5 位）、`1500011`（7 位）、`15000a`（含字母）、`1500-01`（含连字符）、空串 均判为非法
- 美国/加拿大原有逻辑回归测试 0 失败

## 备注

`CanadianPostalCode`、`CAPostalCodeAdapter`、`USAZipCode`、`Provinces`、`ZipCodeTester` 的骨架代码来自课程作业材料，`ChinesePostalCode` 与 `ChinesePostalCodeAdapter` 为本人实现；同时对 `ZipCodeTester` 做了 JDK 9+ 兼容修改（`com.sun.java.swing.plaf.windows` 内部包不再导出），把 `Provinces.java` 由 GBK 转存为 UTF-8。
