import java.util.*;
import java.util.regex.*;
public class PlagiarismChecker {

    // 使用正则表达式进行中文分词
    public static List<String> segmentText(String text) {
        List<String> words = new ArrayList<>();
        // 匹配所有中文字符
        Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            words.add(matcher.group());
        }
        return words;
    }

    public static double calculateCosineSimilarity(String text1, String text2) {
        if (text1 == null || text1.isEmpty() || text2 == null || text2.isEmpty()) {
            return 0;  // 如果文本为空，直接返回0表示完全不相似
        }

        List<String> words1 = segmentText(text1);
        List<String> words2 = segmentText(text2);

        // 处理中文和英文文本
        List<String> combinedWords1 = new ArrayList<>();
        combinedWords1.addAll(segmentText(text1));
        combinedWords1.addAll(Arrays.asList(text1.split("\\s+")));

        List<String> combinedWords2 = new ArrayList<>();
        combinedWords2.addAll(segmentText(text2));
        combinedWords2.addAll(Arrays.asList(text2.split("\\s+")));

        // 如果分词后的文本为空，直接返回0相似度
        if (combinedWords1.isEmpty() || combinedWords2.isEmpty()) {
            return 0;
        }

        // 创建词频向量
        HashMap<String, Integer> vector1 = buildWordVector(combinedWords1);
        HashMap<String, Integer> vector2 = buildWordVector(combinedWords2);

        // 计算余弦相似度
        return cosineSimilarity(vector1, vector2);
    }

    // 构建词频向量
    private static HashMap<String, Integer> buildWordVector(List<String> words) {
        HashMap<String, Integer> vector = new HashMap<>();
        for (String word : words) {
            vector.put(word, vector.getOrDefault(word, 0) + 1);
        }
        return vector;
    }

    // 计算两个词频向量的余弦相似度
    private static double cosineSimilarity(HashMap<String, Integer> vector1, HashMap<String, Integer> vector2) {
        double dotProduct = 0;
        double magnitude1 = 0;
        double magnitude2 = 0;

        // 计算点积
        for (String word : vector1.keySet()) {
            if (vector2.containsKey(word)) {
                dotProduct += vector1.get(word) * vector2.get(word);
            }
        }

        // 计算向量的模
        for (int value : vector1.values()) {
            magnitude1 += value * value;
        }
        for (int value : vector2.values()) {
            magnitude2 += value * value;
        }

        // 防止除以零的情况
        if (magnitude1 == 0 || magnitude2 == 0) {
            return 0;  // 如果向量模为零，返回0，表示没有相似度
        }

        // 计算余弦相似度
        return dotProduct / (Math.sqrt(magnitude1) * Math.sqrt(magnitude2));
    }

    public static void checkPlagiarism(String originalFilePath, String plagiarizedFilePath) {
        // 读取原文和抄袭论文
        String originalText = FileReaderUtil.readFile(originalFilePath);
        String plagiarizedText = FileReaderUtil.readFile(plagiarizedFilePath);

        // 计算余弦相似度
        double similarity = calculateCosineSimilarity(originalText, plagiarizedText);

        // 设置保留两位小数，并四舍五入
        String formattedValue = String.format("%.2f", similarity);

        // 判断相似度并输出结果
        System.out.println("相似度: " + formattedValue);

        // 设置一个阈值来判断是否为抄袭
        double plagiarismThreshold = 0.7; // 设定相似度阈值，0.7表示70%以上的相似度视为抄袭

        if (similarity >= plagiarismThreshold) {
            System.out.println("这篇文章可能是抄袭的");
        } else {
            System.out.println("这篇文章是原创的");
        }

        // 如果需要将结果写入文件
        String result = "Cosine Similarity: " + similarity + "\n" + (similarity >= plagiarismThreshold ? "Plagiarized" : "Original");
        FileReaderUtil.writeFile("data/Plagiarism_Result.txt", result);
    }
}
