public class StringMatrix {
    private String[][] matrix;

    // Конструктор с аргументами
    public StringMatrix(String[][] matrix) {
        this.matrix = matrix;
    }

    // Пустой конструктор
    public StringMatrix() {
        this.matrix = new String[0][0];
    }

    // Конструктор клонирования
    public StringMatrix(StringMatrix other) {
        int rows = other.matrix.length;
        int cols = other.matrix[0].length;
        this.matrix = new String[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.matrix[i][j] = new String(other.matrix[i][j]); // Клонируем каждую строку
            }
        }
    }

    // Метод для получения значения матрицы
    public String[][] getMatrix() {
        return this.matrix;
    }

    // Метод для проверки матрицы на наличие чисел
    public int[][] checkMatrixForNumbers() {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = StringProcessor.isNumeric(matrix[i][j]) ? 1 : 0;
            }
        }
        return result;
    }
}