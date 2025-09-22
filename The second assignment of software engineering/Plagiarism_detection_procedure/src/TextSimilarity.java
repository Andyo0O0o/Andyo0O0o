import java.util.*;

public class TextSimilarity {

    // 计算两篇文本的余弦相似度
    public static double calculateCosineSimilarity(String text1, String text2) {
        // 将文本转换为词频向量
        Map<String, Integer> vector1 = textToVector(text1);
        Map<String, Integer> vector2 = textToVector(text2);

        // 获取所有词汇
        Set<String> allWords = new HashSet<>();
        allWords.addAll(vector1.keySet());
        allWords.addAll(vector2.keySet());

        // 计算点积 (Dot product) 和各自的模 (Magnitude)
        double dotProduct = 0;
        double magnitude1 = 0;
        double magnitude2 = 0;

        for (String word : allWords) {
            int count1 = vector1.getOrDefault(word, 0);
            int count2 = vector2.getOrDefault(word, 0);

            dotProduct += count1 * count2;
            magnitude1 += count1 * count1;
            magnitude2 += count2 * count2;
        }

        // 计算余弦相似度
        double magnitude = Math.sqrt(magnitude1) * Math.sqrt(magnitude2);
        if (magnitude == 0) {
            return 0.0;  // 如果模为0，返回0
        }

        return dotProduct / magnitude;
    }

    // 将文本转换为词频向量
    private static Map<String, Integer> textToVector(String text) {
        // 清理文本：英文只保留字母，中文保留字符
        String cleanedText = cleanText(text);

        // 分词
        String[] words = cleanedText.split("\\s+");

        // 统计词频
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        return wordCount;
    }

    // 清理文本：英文去除非字母，中文去除非汉字
    private static String cleanText(String text) {
        // 先处理中文和英文字符
        String cleanedText = text.replaceAll("[^a-zA-Z\\u4e00-\\u9fa5]", " ").toLowerCase();
        return cleanedText;
    }

    // 用于测试的 main 方法
    public static void main(String[] args) {
        // 从文件中读取两篇文本
        String originalText = FileReaderUtil.readFile("data/Original_text.txt");
        String plagiarizedText = FileReaderUtil.readFile("data/The_file_of_the_plagiarized_paper.txt");

        // 计算余弦相似度
        double similarity = calculateCosineSimilarity(originalText, plagiarizedText);

        // 输出相似度结果
        System.out.println("Cosine Similarity: " + similarity);

        // 如果需要将结果写入文件
        String result = "Cosine Similarity between the two texts: " + similarity;
        FileReaderUtil.writeFile("data/Plagiarism_Result.txt", result);
    }
}
