public class App {
    public static void main(String[] args) {
        Maquina maquina = new Maquina();
        Sistema sis = new Sistema(maquina);
        sis.ejecuta();
    }
}
