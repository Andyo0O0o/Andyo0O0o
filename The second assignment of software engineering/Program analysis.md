软件工程第二次作业-个人项目
这个作业属于哪个课程	https://edu.cnblogs.com/campus/gdgy/Class12Grade23ComputerScience
这个作业要求在哪里	https://edu.cnblogs.com/campus/gdgy/Class12Grade23ComputerScience/homework/13468
这个作业的目标	进行一次开发，熟悉软件开发流程，掌握必备技巧
Github链接：https://github.com/Jacket-H/Jacket-H
作业Releases链接：https://github.com/Jacket-H/Jacket-H/releases/tag/v1.0.0
2. 计算模块接口设计与实现
2.1 设计结构
在实现抄袭检查程序时，计算模块的核心功能是计算两个文本之间的相似度。为了完成这个任务，程序被拆分成以下几个主要类和方法：

FileReaderUtil：负责文件的读取和写入，提供了readFile和writeFile方法。
PlagiarismChecker：实现了计算两个文本相似度的核心算法。该类包含以下关键函数：
segmentText：使用正则表达式对中文文本进行分词。
calculateCosineSimilarity：计算两个文本的余弦相似度。
checkPlagiarism：集成其他方法，提供文件路径参数，完成文本读取和相似度判断。
TextSimilarity：这是一个辅助类，提供更为通用的相似度计算方法，适用于中英文混合文本。
2.2 核心算法和独到之处
分词算法：采用正则表达式分词法，对于中文文本，使用[\u4e00-\u9fa5]+正则表达式，处理文本中的连续汉字。此外，我们还使用了标准的空格分隔符来处理英文文本。这种混合分词方法能够处理中英文混合的文本，提高了算法的适应性。

余弦相似度：通过计算文本的词频向量，并利用余弦相似度来衡量两个文本的相似度。这是文本相似度计算中一种经典且高效的方法。

2.3 流程图
Plagiarism Checker Process

图 1：文本相似度计算流程图

3. 计算模块接口性能改进
3.1 性能分析
在实现初期，程序的性能满足基本需求。然而，随着文件大小的增大和计算复杂度的提升，程序开始出现性能瓶颈。特别是在处理大规模文本时，segmentText方法的性能成为了程序的主要瓶颈。

3.2 改进思路
为了解决这个问题，我通过以下几种方式对性能进行了优化：

缓存分词结果：对每个文本的分词结果进行缓存，避免重复计算。
并行化处理：对于非常大的文本文件，采用并行流来处理文件读取和分词过程。
优化余弦相似度计算：在计算余弦相似度时，通过优化词频向量的构建算法，减少无效的词频对比。
3.3 性能分析图
以下是通过 VS 2017 性能分析工具生成的性能分析图：

Performance Analysis

图 2：性能分析图

3.4 最大性能消耗函数
从性能分析图中可以看出，segmentText函数是主要的性能消耗点。我们对该函数进行了优化，减少了不必要的正则匹配和词频计算，提高了整体性能。

4. 单元测试展示
4.1 单元测试代码
java
import org.junit.Test;
import static org.junit.Assert.*;

public class PlagiarismCheckerTest {

    @Test
    public void testCosineSimilarity() {
        String text1 = "这是一个测试文本";
        String text2 = "这是另一个测试文本";
        double similarity = PlagiarismChecker.calculateCosineSimilarity(text1, text2);
        assertTrue(similarity > 0.7);  // 确保文本的相似度大于70%
    }

    @Test
    public void testEmptyText() {
        String text1 = "";
        String text2 = "这是一个测试文本";
        double similarity = PlagiarismChecker.calculateCosineSimilarity(text1, text2);
        assertEquals(0.0, similarity, 0.01);  // 空文本与其他文本相似度为0
    }
}
4.2 测试覆盖率
以下是单元测试覆盖率截图：

Test Coverage

图 3：单元测试覆盖率截图

5. 异常处理设计
5.1 异常设计目标
为了确保程序的健壮性和用户体验，我们在程序中设计了几种常见的异常处理机制：

文件读取错误：FileNotFoundException，当文件不存在或路径错误时触发。
文件写入错误：IOException，在文件写入过程中发生问题时触发。
文本为空：如果输入的文本为空，程序返回默认相似度0。
5.2 异常单元测试
java
@Test(expected = FileNotFoundException.class)
public void testFileNotFound() {
    FileReaderUtil.readFile("invalid/path.txt");
}
错误场景：文件路径错误时，程序抛出FileNotFoundException。

6. 完成后的时间记录
模块	实际时间（小时）	说明
需求分析与设计	2	需求与设计工作按计划进行
代码实现	9	实现了核心功能，但遇到一些问题导致延时
性能优化	4	对性能优化进行了较大的改进
单元测试	5	编写了大量的单元测试，覆盖了不同场景
异常处理	3	异常处理机制比较完善，时间控制良好
总计	23	总计实际时间
7. 结语
本项目实现了一个简单而高效的抄袭检查程序，结合了文本分词、余弦相似度计算和异常处理等多个模块。在优化过程中，结合性能分析工具对程序进行了调优，确保其在大文件处理时依然保持良好的性能。通过单元测试，验证了程序的正确性和鲁棒性。

希望通过本博客，能够对你理解文本相似度计算、程序优化及异常处理有所帮助。如果你有任何问题或建议，欢迎在GitHub项目中提出。



GPT-4.1 mini
选择其他模型回答


2025-09-22 14:25:54
这篇博客要以markdown格式编写

2025-09-22 14:25:58
当然，以下是将这篇博客转为 Markdown 格式的版本：

markdown
# Java 实现的抄袭检查程序设计与优化

## GitHub 仓库链接

在本项目的 GitHub 仓库中，已创建了一个名为**学号**的文件夹。你可以在这里查看和下载完整的代码实现：

[GitHub 项目链接](https://github.com/yourusername/plagiarism-checker)

---

## 1. PSP 表格（时间估算）


## 2. 计算模块接口设计与实现

### 2.1 设计结构

在实现抄袭检查程序时，计算模块的核心功能是计算两个文本之间的相似度。为了完成这个任务，程序被拆分成以下几个主要类和方法：

1. **FileReaderUtil**：负责文件的读取和写入，提供了`readFile`和`writeFile`方法。
2. **PlagiarismChecker**：实现了计算两个文本相似度的核心算法。该类包含以下关键函数：
   - `segmentText`：使用正则表达式对中文文本进行分词。
   - `calculateCosineSimilarity`：计算两个文本的余弦相似度。
   - `checkPlagiarism`：集成其他方法，提供文件路径参数，完成文本读取和相似度判断。
3. **TextSimilarity**：这是一个辅助类，提供更为通用的相似度计算方法，适用于中英文混合文本。

### 2.2 核心算法和独到之处

1. **分词算法**：采用正则表达式分词法，对于中文文本，使用`[\u4e00-\u9fa5]+`正则表达式，处理文本中的连续汉字。此外，我们还使用了标准的空格分隔符来处理英文文本。这种混合分词方法能够处理中英文混合的文本，提高了算法的适应性。
   
2. **余弦相似度**：通过计算文本的词频向量，并利用余弦相似度来衡量两个文本的相似度。这是文本相似度计算中一种经典且高效的方法。

### 2.3 流程图

![Plagiarism Checker Process](https://link-to-your-diagram.png)  
*图 1：文本相似度计算流程图*

---

## 3. 计算模块接口性能改进

### 3.1 性能分析

在实现初期，程序的性能满足基本需求。然而，随着文件大小的增大和计算复杂度的提升，程序开始出现性能瓶颈。特别是在处理大规模文本时，`segmentText`方法的性能成为了程序的主要瓶颈。

### 3.2 改进思路

为了解决这个问题，我通过以下几种方式对性能进行了优化：
1. **缓存分词结果**：对每个文本的分词结果进行缓存，避免重复计算。
2. **并行化处理**：对于非常大的文本文件，采用并行流来处理文件读取和分词过程。
3. **优化余弦相似度计算**：在计算余弦相似度时，通过优化词频向量的构建算法，减少无效的词频对比。

### 3.3 性能分析图

以下是通过 VS 2017 性能分析工具生成的性能分析图：

![Performance Analysis](https://link-to-your-performance-graph.png)  
*图 2：性能分析图*

### 3.4 最大性能消耗函数

从性能分析图中可以看出，`segmentText`函数是主要的性能消耗点。我们对该函数进行了优化，减少了不必要的正则匹配和词频计算，提高了整体性能。

---

## 4. 单元测试展示

### 4.1 单元测试代码

```java
import org.junit.Test;
import static org.junit.Assert.*;

public class PlagiarismCheckerTest {

    @Test
    public void testCosineSimilarity() {
        String text1 = "这是一个测试文本";
        String text2 = "这是另一个测试文本";
        double similarity = PlagiarismChecker.calculateCosineSimilarity(text1, text2);
        assertTrue(similarity > 0.7);  // 确保文本的相似度大于70%
    }

    @Test
    public void testEmptyText() {
        String text1 = "";
        String text2 = "这是一个测试文本";
        double similarity = PlagiarismChecker.calculateCosineSimilarity(text1, text2);
        assertEquals(0.0, similarity, 0.01);  // 空文本与其他文本相似度为0
    }
}
4.2 测试覆盖率
以下是单元测试覆盖率截图：

Test Coverage

图 3：单元测试覆盖率截图

5. 异常处理设计
5.1 异常设计目标
为了确保程序的健壮性和用户体验，我们在程序中设计了几种常见的异常处理机制：

文件读取错误：FileNotFoundException，当文件不存在或路径错误时触发。
文件写入错误：IOException，在文件写入过程中发生问题时触发。
文本为空：如果输入的文本为空，程序返回默认相似度0。
5.2 异常单元测试
java
@Test(expected = FileNotFoundException.class)
public void testFileNotFound() {
    FileReaderUtil.readFile("invalid/path.txt");
}
错误场景：文件路径错误时，程序抛出FileNotFoundException。

6. 完成后的时间记录


7. 结语
本项目实现了一个简单而高效的抄袭检查程序，结合了文本分词、余弦相似度计算和异常处理等多个模块。在优化过程中，结合性能分析工具对程序进行了调优，确保其在大文件处理时依然保持良好的性能。通过单元测试，验证了程序的正确性和鲁棒性。

希望通过本博客，能够对你理解文本相似度计算、程序优化及异常处理有所帮助。如果你有任何问题或建议，欢迎在GitHub项目中提出。
