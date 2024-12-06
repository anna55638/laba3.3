public class Main {
    public static void main(String[] args) {
        try {
            Menu.showMenu();
        } catch (Exception e) {
            System.out.println("Критическая ошибка: " + e.getMessage());
        }
    }
}