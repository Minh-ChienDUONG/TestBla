import Controller.PerformanceService;
import Model.Input;
import Model.Mower;
import Model.Position;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Input input = new Input();
        List<Mower> lstMower = input.createMower();
        final int WIDTH = Input.getWIDTH();
        final int LENGTH = Input.getLENGTH();

        PerformanceService perform = new PerformanceService();
        List<Position> result = perform.process(lstMower,WIDTH,LENGTH);

        System.out.println("Output");
        for(Position p:result){
            System.out.println(p.printPosition());
        }
    }
}
