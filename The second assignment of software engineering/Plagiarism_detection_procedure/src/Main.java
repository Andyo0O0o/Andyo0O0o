public class Main {
    public static void main(String[] args) {
        // 检查命令行参数是否正确
        if (args.length != 2) {
            System.out.println("Usage: java Main <originalFilePath> <plagiarizedFilePath>");
            return;
        }

        // 获取原文文件和抄袭文件的路径
        String originalFilePath = args[0];
        String plagiarizedFilePath = args[1];

        // 调用PlagiarismChecker来执行抄袭检查
        PlagiarismChecker.checkPlagiarism(originalFilePath, plagiarizedFilePath);
    }
}
