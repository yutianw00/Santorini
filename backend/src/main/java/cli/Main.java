package cli;

public class Main {

    private static Control ctrl = new ControlImpl();

    /**
     * function to play the game
     */
    public static void play() {
        ctrl.play();
    }

    public static void main(String[] args) {
        Main.play();
    }
}
