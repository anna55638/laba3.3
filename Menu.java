import java.util.Scanner;

public class Menu {
    private static Scanner scanner = new Scanner(System.in);
    private static StringProcessor stringProcessor;
    private static StringMatrix stringMatrix;
    private static final PrintMatrix printMatrix = new PrintMatrix();

    public static void showMenu() {
        boolean isRunning = true;
        while (isRunning) {
            try {
                System.out.println("\n=== Главное меню ===");
                System.out.println("1. Работа со строкой");
                System.out.println("2. Работа с матрицей");
                System.out.println("3. Выход");
                System.out.print("Выберите пункт меню (1-3): ");

                int choice = getIntInput();
                switch (choice) {
                    case 1:
                        handleStringMenu();
                        break;
                    case 2:
                        handleMatrixMenu();
                        break;
                    case 3:
                        isRunning = false;
                        System.out.println("Программа завершена.");
                        break;
                    default:
                        System.out.println("Неверный выбор. Пожалуйста, выберите 1-3.");
                }
            } catch (Exception e) {
                System.out.println("Произошла ошибка: " + e.getMessage());
                scanner.nextLine(); // Очистка буфера
            }
        }
    }

    private static void handleStringMenu() {
        boolean isStringMenuRunning = true;
        while (isStringMenuRunning) {
            System.out.println("\n=== Меню работы со строкой ===");
            System.out.println("1. Ввести новую строку");
            System.out.println("2. Проверить слова на числа");
            System.out.println("3. Найти самый частый символ");
            System.out.println("4. Удалить знаки препинания");
            System.out.println("5. Создать клон строки");
            System.out.println("6. Сохранить текущую строку в файл");
            System.out.println("7. Загрузить строку из файла");
            System.out.println("8. Использовать обработанную строку");
            System.out.println("9. Вернуться в главное меню");
            System.out.print("Выберите пункт меню (1-9): ");

            try {
                int choice = getIntInput();
                switch (choice) {
                    case 1:
                        inputString();
                        break;
                    case 2:
                        if (checkStringProcessor()) {
                            stringProcessor.checkWordsForNumbers();
                        }
                        break;
                    case 3:
                        if (checkStringProcessor()) {
                            char mostFrequent = stringProcessor.findMostFrequentCharacter();
                            System.out.println("Самый частый символ: " + mostFrequent);
                        }
                        break;
                    case 4:
                        if (checkStringProcessor()) {
                            String cleaned = stringProcessor.removePunctuation();
                            System.out.println("Строка без знаков препинания: " + cleaned);
                        }
                        break;
                    case 5:
                        if (checkStringProcessor()) {
                            StringProcessor cloned = new StringProcessor(stringProcessor);
                            System.out.println("Клонированная строка: " + cloned.getInput());
                        }
                        break;
                    case 6:
                        if (checkStringProcessor()) {
                            saveStringToFile(stringProcessor.getInput());
                        }
                        break;
                    case 7:
                        loadStringFromFile();
                        break;
                    case 8:
                        handleProcessedString();
                        break;
                    case 9:
                        isStringMenuRunning = false;
                        break;
                    default:
                        System.out.println("Неверный выбор. Пожалуйста, выберите 1-9.");
                }
            } catch (Exception e) {
                System.out.println("Произошла ошибка: " + e.getMessage());
                scanner.nextLine(); // Очистка буфера
            }
        }
    }

    private static void handleMatrixMenu() {
        boolean isMatrixMenuRunning = true;
        while (isMatrixMenuRunning) {
            System.out.println("\n=== Меню работы с матрицей ===");
            System.out.println("1. Создать новую матрицу");
            System.out.println("2. Показать матрицу");
            System.out.println("3. Проверить на числа");
            System.out.println("4. Создать клон матрицы");
            System.out.println("5. Сохранить текущую матрицу в файл");
            System.out.println("6. Загрузить матрицу из файла");
            System.out.println("7. Вернуться в главное меню");
            System.out.print("Выберите пункт меню (1-7): ");

            try {
                int choice = getIntInput();
                switch (choice) {
                    case 1:
                        createMatrix();
                        break;
                    case 2:
                        if (checkMatrix()) {
                            System.out.println("Текущая матрица:");
                            printMatrix.printResultMatrix(stringMatrix.getMatrix());
                        }
                        break;
                    case 3:
                        if (checkMatrix()) {
                            int[][] result = stringMatrix.checkMatrixForNumbers();
                            System.out.println("Результат проверки на числа:");
                            printMatrix.printResultMatrix(result);
                            System.out.println("Хотите сохранить результат проверки? (да/нет)");
                            scanner.nextLine(); // Очистка буфера
                            String answer = scanner.nextLine().toLowerCase();
                            if (answer.equals("да")) {
                                saveMatrixToFile(result);
                            }
                        }
                        break;
                    case 4:
                        if (checkMatrix()) {
                            StringMatrix cloned = new StringMatrix(stringMatrix);
                            System.out.println("Клонированная матрица:");
                            printMatrix.printResultMatrix(cloned.getMatrix());
                        }
                        break;
                    case 5:
                        if (checkMatrix()) {
                            saveMatrixToFile(stringMatrix.getMatrix());
                        }
                        break;
                    case 6:
                        loadMatrixFromFile();
                        break;
                    case 7:
                        isMatrixMenuRunning = false;
                        break;
                    default:
                        System.out.println("Неверный выбор. Пожалуйста, выберите 1-7.");
                }
            } catch (Exception e) {
                System.out.println("Произошла ошибка: " + e.getMessage());
                scanner.nextLine(); // Очистка буфера
            }
        }
    }

    private static void saveStringToFile(String content) {
        try {
            scanner.nextLine(); // Очистка буфера
            System.out.print("Введите имя файла (без расширения): ");
            String fileName = scanner.nextLine();
            FileOperations.saveStringToFile(content, fileName + ".txt");
            System.out.println("Данные успешно сохранены в файл: " + fileName + ".txt");
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении в файл: " + e.getMessage());
        }
    }

    private static void loadStringFromFile() {
        try {
            scanner.nextLine(); // Очистка буфера
            System.out.print("Введите имя файла для загрузки: ");
            String fileName = scanner.nextLine();
            String content = FileOperations.loadStringFromFile(fileName);
            stringProcessor = new StringProcessor(content);
            System.out.println("Строка успешно загружена из файла: " + content);
        } catch (Exception e) {
            System.out.println("Ошибка при загрузке из файла: " + e.getMessage());
        }
    }

    private static void saveMatrixToFile(String[][] matrix) {
        try {
            scanner.nextLine(); // Очистка буфера
            System.out.print("Введите имя файла: ");
            String fileName = scanner.nextLine();

            System.out.println("Выберите формат сохранения:");
            System.out.println("1. Текстовый файл");
            System.out.println("2. Бинарный файл");
            int formatChoice = getIntInput();

            switch (formatChoice) {
                case 1:
                    FileOperations.saveMatrixToTextFile(matrix, fileName + ".txt");
                    System.out.println("Матрица успешно сохранена в текстовый файл: " + fileName + ".txt");
                    break;
                case 2:
                    FileOperations.saveMatrixToBinaryFile(matrix, fileName + ".bin");
                    System.out.println("Матрица успешно сохранена в бинарный файл: " + fileName + ".bin");
                    break;
                default:
                    System.out.println("Неверный выбор формата.");
            }
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении в файл: " + e.getMessage());
        }
    }

    private static void saveMatrixToFile(int[][] matrix) {
        try {
            scanner.nextLine(); // Очистка буфера
            System.out.print("Введите имя файла: ");
            String fileName = scanner.nextLine();

            System.out.println("Выберите формат сохранения:");
            System.out.println("1. Текстовый файл");
            System.out.println("2. Бинарный файл");
            int formatChoice = getIntInput();

            switch (formatChoice) {
                case 1:
                    FileOperations.saveMatrixToTextFile(matrix, fileName + ".txt");
                    System.out.println("Результат проверки успешно сохранен в текстовый файл: " + fileName + ".txt");
                    break;
                case 2:
                    FileOperations.saveMatrixToBinaryFile(matrix, fileName + ".bin");
                    System.out.println("Результат проверки успешно сохранен в бинарный файл: " + fileName + ".bin");
                    break;
                default:
                    System.out.println("Неверный выбор формата.");
            }
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении в файл: " + e.getMessage());
        }
    }

    private static void loadMatrixFromFile() {
        try {
            scanner.nextLine(); // Очистка буфера
            System.out.print("Введите имя файла для загрузки: ");
            String fileName = scanner.nextLine();

            System.out.println("Выберите формат файла:");
            System.out.println("1. Текстовый файл");
            System.out.println("2. Бинарный файл");
            int formatChoice = getIntInput();

            String[][] matrix;
            switch (formatChoice) {
                case 1:
                    matrix = FileOperations.loadMatrixFromFile(fileName);
                    break;
                case 2:
                    matrix = FileOperations.loadMatrixFromBinaryFile(fileName);
                    break;
                default:
                    System.out.println("Неверный выбор формата.");
                    return;
            }

            stringMatrix = new StringMatrix(matrix);
            System.out.println("Матрица успешно загружена из файла:");
            printMatrix.printResultMatrix(matrix);
        } catch (Exception e) {
            System.out.println("Ошибка при загрузке из файла: " + e.getMessage());
        }
    }

    private static void handleProcessedString() {
        if (checkStringProcessor()) {
            scanner.nextLine(); // Очистка буфера
            System.out.println("Хотите использовать обработанную строку для дальнейшей работы? (да/нет)");
            String answer = scanner.nextLine().toLowerCase();
            if (answer.equals("да")) {
                String processed = stringProcessor.removePunctuation();
                stringProcessor = new StringProcessor(processed);
                System.out.println("Теперь используется обработанная строка: " + processed);
            }
        }
    }

    private static void inputString() {
        scanner.nextLine(); // Очистка буфера
        System.out.print("Введите строку: ");
        String input = scanner.nextLine();
        stringProcessor = new StringProcessor(input);
        System.out.println("Строка успешно сохранена.");
    }

    private static void createMatrix() {
        try {
            System.out.print("Введите количество строк: ");
            int rows = getIntInput();
            System.out.print("Введите количество столбцов: ");
            int cols = getIntInput();

            if (rows <= 0 || cols <= 0) {
                throw new IllegalArgumentException("Размеры матрицы должны быть положительными числами");
            }

            String[][] matrix = new String[rows][cols];
            scanner.nextLine(); // Очистка буфера

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    System.out.printf("Введите элемент [%d][%d]: ", i, j);
                    matrix[i][j] = scanner.nextLine();
                }
            }

            stringMatrix = new StringMatrix(matrix);
            System.out.println("Матрица успешно создана.");
        } catch (Exception e) {
            System.out.println("Ошибка при создании матрицы: " + e.getMessage());
        }
    }

    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Пожалуйста, введите число.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static boolean checkStringProcessor() {
        if (stringProcessor == null) {
            System.out.println("Сначала введите строку (пункт 1)");
            return false;
        }
        return true;
    }

    private static boolean checkMatrix() {
        if (stringMatrix == null) {
            System.out.println("Сначала создайте матрицу (пункт 1)");
            return false;
        }
        return true;
    }
}