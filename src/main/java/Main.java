public class Main {
    public static void main(String[] args) {
        Adventure controller = new Adventure();

        UserInterFace ui = new UserInterFace(controller);
        ui.startProgram();
    }
}
