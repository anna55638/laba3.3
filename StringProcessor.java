public class StringProcessor {
    private String input;

    // Конструктор, принимающий строку в качестве параметра
    public StringProcessor(String input) {
        this.input = input;
    }
    //пустой конструктор
    public StringProcessor() {
        this.input = "";
    }
    //конструктор клонирования
    public StringProcessor(StringProcessor other) {
        this.input = new String(other.input);
    }

    //Метод для получения значения поля input
    public String getInput() {
        return this.input;
    }

    // Метод для проверки слов в строке на наличие чисел
    public void checkWordsForNumbers() {
        int start = 0;
        int length = input.length();
        for (int i = 0; i <= length; i++) {
            if (i == length || input.charAt(i) == ' ') {
                String word = input.substring(start, i); //Извлечение слова из строки
                boolean isNumber = isNumeric(word); //проверка, является ли слово числом
                System.out.println(word + " - " + isNumber);
                start = i + 1;
            }
        }
    }

    // метод для проверки, является ли строка числом
    public static boolean isNumeric(String word) {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i); // Получение текущего символа
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return word.length() > 0;
    }

    // Метод для поиска самого часто встречающегося символа в строке
    public char findMostFrequentCharacter() {
        char mostFrequentChar = input.charAt(0);
        int maxCount = 0;
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            int count = 0;
            for (int j = 0; j < input.length(); j++) {
                if (input.charAt(j) == currentChar) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                mostFrequentChar = currentChar;
            }
        }
        return mostFrequentChar;
    }

    //Метод для удаления знаков препинания
    public String removePunctuation() {
        String result = "";
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == ' ') {
                result += c;
            }
        }
        return result;
    }
}