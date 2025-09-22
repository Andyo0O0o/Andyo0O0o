import java.io.*;
import java.nio.file.*;

public class FileReaderUtil {
    // 读取文件内容并返回为字符串
    public static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try {
            // 使用Files类读取文件，得到一个List，每个元素代表一行文本
            Files.lines(Paths.get(filePath)).forEach(line -> content.append(line).append("\n"));
        } catch (IOException e) {
            // 如果文件读取出错，打印错误信息
            System.err.println("Error reading file: " + filePath);
            e.printStackTrace();
        }
        return content.toString();
    }

    // 将字符串内容写入文件
    public static void writeFile(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // 写入内容到指定的文件
            writer.write(content);
        } catch (IOException e) {
            // 如果写文件出错，打印错误信息
            System.err.println("Error writing to file: " + filePath);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 仅用于测试
        String originalText = readFile("data/Original_text.txt");
        String plagiarizedText = readFile("data/The_file_of_the_plagiarized_paper.txt");

        // 显示读取到的文本内容（如果需要）
        System.out.println("Original Text:\n" + originalText);
        System.out.println("Plagiarized Text:\n" + plagiarizedText);
    }
}
