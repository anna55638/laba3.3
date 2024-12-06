import java.io.*;

public class FileOperations {
    private static final int BUFFER_SIZE = 8192;  // 8KB buffer size

    public static void saveStringToFile(String content, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName), BUFFER_SIZE)) {
            writer.write(content);
            writer.flush();
        }
    }

    public static String loadStringFromFile(String fileName) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName), BUFFER_SIZE)) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString().trim();
    }

    public static void saveMatrixToTextFile(String[][] matrix, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName), BUFFER_SIZE)) {
            writer.write(matrix.length + "\n");
            writer.write(matrix[0].length + "\n");

            for (String[] row : matrix) {
                for (String element : row) {
                    writer.write(element + "\n");
                }
            }
            writer.flush();
        }
    }

    public static void saveMatrixToTextFile(int[][] matrix, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName), BUFFER_SIZE)) {
            writer.write(matrix.length + "\n");
            writer.write(matrix[0].length + "\n");

            for (int[] row : matrix) {
                for (int element : row) {
                    writer.write(element + "\n");
                }
            }
            writer.flush();
        }
    }

    public static void saveMatrixToBinaryFile(String[][] matrix, String fileName) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileName), BUFFER_SIZE);
             DataOutputStream dos = new DataOutputStream(bos)) {

            dos.writeInt(matrix.length);
            dos.writeInt(matrix[0].length);

            for (String[] row : matrix) {
                for (String element : row) {
                    dos.writeUTF(element);
                }
            }
            dos.flush();
        }
    }

    public static void saveMatrixToBinaryFile(int[][] matrix, String fileName) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileName), BUFFER_SIZE);
             DataOutputStream dos = new DataOutputStream(bos)) {

            dos.writeInt(matrix.length);
            dos.writeInt(matrix[0].length);

            for (int[] row : matrix) {
                for (int element : row) {
                    dos.writeInt(element);
                }
            }
            dos.flush();
        }
    }

    public static String[][] loadMatrixFromFile(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName), BUFFER_SIZE)) {
            int rows = Integer.parseInt(reader.readLine());
            int cols = Integer.parseInt(reader.readLine());

            String[][] matrix = new String[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = reader.readLine();
                }
            }
            return matrix;
        }
    }

    public static String[][] loadMatrixFromBinaryFile(String fileName) throws IOException {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName), BUFFER_SIZE);
             DataInputStream dis = new DataInputStream(bis)) {

            int rows = dis.readInt();
            int cols = dis.readInt();

            String[][] matrix = new String[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = dis.readUTF();
                }
            }
            return matrix;
        }
    }
}