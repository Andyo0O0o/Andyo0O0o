public class Main {
    public static void main(String[] args) {
        // 获取原文文件和抄袭文件的路径
        String originalFilePath = "data/Original_text.txt";
        String plagiarizedFilePath = "data/The_file_of_the_plagiarized_paper.txt";

        // 调用PlagiarismChecker来执行抄袭检查
        PlagiarismChecker.checkPlagiarism(originalFilePath, plagiarizedFilePath);
    }
}
