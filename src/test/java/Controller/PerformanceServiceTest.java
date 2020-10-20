package Controller;

import Model.Mower;
import Model.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PerformanceServiceTest {

    @Test
    void process_oneMower() {
        Mower m1 = new Mower(1, 0, "LFLF", new Position(1, 2, "N"), new Position(1, 2, "N"));
        List<Mower> lstMower = Collections.singletonList(m1);
        int WIDTH = 5;
        int LENGTH = 5;

        PerformanceService perform = new PerformanceService();
        List<Position> lstPositions = perform.process(lstMower,WIDTH, LENGTH);

        Assertions.assertThat(lstPositions.get(0)).isNotNull();
        Assertions.assertThat(lstPositions.get(0).getX()).isEqualTo(0);
        Assertions.assertThat(lstPositions.get(0).getY()).isEqualTo(1);
        Assertions.assertThat(lstPositions.get(0).getOrientation()).isEqualTo("S");
    }

    @Test
    void process_twoMowers(){
        Mower m1 = new Mower(1, 0, "LFLF", new Position(1, 2, "N"), new Position(1, 2, "N"));
        Mower m2 = new Mower(2, 0, "FFRFFR", new Position(3, 3, "E"), new Position(3, 3, "E"));
        List<Mower> lstMower = Arrays.asList(m1,m2);

        int WIDTH = 5;
        int LENGTH = 5;

        PerformanceService perform = new PerformanceService();
        List<Position> lstPositions = perform.process(lstMower,WIDTH, LENGTH);

        Assertions.assertThat(lstPositions.get(0)).isNotNull();
        Assertions.assertThat(lstPositions.get(0).getX()).isEqualTo(0);
        Assertions.assertThat(lstPositions.get(0).getY()).isEqualTo(1);
        Assertions.assertThat(lstPositions.get(0).getOrientation()).isEqualTo("S");

        Assertions.assertThat(lstPositions.get(1)).isNotNull();
        Assertions.assertThat(lstPositions.get(1).getX()).isEqualTo(5);
        Assertions.assertThat(lstPositions.get(1).getY()).isEqualTo(1);
        Assertions.assertThat(lstPositions.get(1).getOrientation()).isEqualTo("W");
    }

    @Test
    void process_threeMowers(){
        Mower m1 = new Mower(1, 0, "LFLF", new Position(1, 2, "N"), new Position(1, 2, "N"));
        Mower m2 = new Mower(2, 0, "FFRFFR", new Position(3, 3, "E"), new Position(3, 3, "E"));
        Mower m3 = new Mower(3, 0, "FFRRLLR", new Position(4, 1, "S"), new Position(4, 1, "S"));
        List<Mower> lstMower = Arrays.asList(m1,m2,m3);

        int WIDTH = 5;
        int LENGTH = 5;

        PerformanceService perform = new PerformanceService();
        List<Position> lstPositions = perform.process(lstMower,WIDTH, LENGTH);

        Assertions.assertThat(lstPositions.get(0)).isNotNull();
        Assertions.assertThat(lstPositions.get(0).getX()).isEqualTo(0);
        Assertions.assertThat(lstPositions.get(0).getY()).isEqualTo(1);
        Assertions.assertThat(lstPositions.get(0).getOrientation()).isEqualTo("S");

        Assertions.assertThat(lstPositions.get(1)).isNotNull();
        Assertions.assertThat(lstPositions.get(1).getX()).isEqualTo(5);
        Assertions.assertThat(lstPositions.get(1).getY()).isEqualTo(1);
        Assertions.assertThat(lstPositions.get(1).getOrientation()).isEqualTo("W");

        Assertions.assertThat(lstPositions.get(2)).isNotNull();
        Assertions.assertThat(lstPositions.get(2).getX()).isEqualTo(4);
        Assertions.assertThat(lstPositions.get(2).getY()).isEqualTo(0);
        Assertions.assertThat(lstPositions.get(2).getOrientation()).isEqualTo("W");
    }

    @Test
    void process_move_outOfLawnSize(){
        Mower m1 = new Mower(1, 0, "FFFFFFF", new Position(2, 3, "N"), new Position(2, 3, "N"));
        Mower m2 = new Mower(2, 0, "FFFFFFFF", new Position(5, 1, "E"), new Position(5, 1, "E"));
        Mower m3 = new Mower(3, 0, "FFFFFFFFFFF", new Position(1, 4, "S"), new Position(1, 4, "S"));
        Mower m4 = new Mower(4, 0, "FFFFFFFF", new Position(1, 1, "W"), new Position(1, 1, "W"));
        List<Mower> lstMower = Arrays.asList(m1,m2,m3,m4);

        int WIDTH = 6;
        int LENGTH = 4;

        PerformanceService perform = new PerformanceService();
        List<Position> lstPositions = perform.process(lstMower,WIDTH, LENGTH);

        Assertions.assertThat(lstPositions.get(0)).isNotNull();
        Assertions.assertThat(lstPositions.get(0).getX()).isEqualTo(2);
        Assertions.assertThat(lstPositions.get(0).getY()).isEqualTo(4);
        Assertions.assertThat(lstPositions.get(0).getOrientation()).isEqualTo("N");

        Assertions.assertThat(lstPositions.get(1)).isNotNull();
        Assertions.assertThat(lstPositions.get(1).getX()).isEqualTo(6);
        Assertions.assertThat(lstPositions.get(1).getY()).isEqualTo(1);
        Assertions.assertThat(lstPositions.get(1).getOrientation()).isEqualTo("E");

        Assertions.assertThat(lstPositions.get(2)).isNotNull();
        Assertions.assertThat(lstPositions.get(2).getX()).isEqualTo(1);
        Assertions.assertThat(lstPositions.get(2).getY()).isEqualTo(0);
        Assertions.assertThat(lstPositions.get(2).getOrientation()).isEqualTo("S");

        Assertions.assertThat(lstPositions.get(3)).isNotNull();
        Assertions.assertThat(lstPositions.get(3).getX()).isEqualTo(0);
        Assertions.assertThat(lstPositions.get(3).getY()).isEqualTo(1);
        Assertions.assertThat(lstPositions.get(3).getOrientation()).isEqualTo("W");
    }

    @Test
    void process_hit_twoMowers(){
        Mower m1 = new Mower(1, 0, "FFFFF", new Position(2, 5, "S"), new Position(2, 5, "S"));
        Mower m2 = new Mower(2, 0, "FFFFFFFFFFF", new Position(2, 1, "N"), new Position(2, 1, "N"));
        List<Mower> lstMower = Arrays.asList(m1,m2);

        int WIDTH = 5;
        int LENGTH = 5;

        PerformanceService perform = new PerformanceService();
        List<Position> lstPositions = perform.process(lstMower,WIDTH, LENGTH);

        Assertions.assertThat(lstPositions.get(0)).isNotNull();
        Assertions.assertThat(lstPositions.get(0).getX()).isEqualTo(2);
        Assertions.assertThat(lstPositions.get(0).getY()).isEqualTo(4);
        Assertions.assertThat(lstPositions.get(0).getOrientation()).isEqualTo("S");

        Assertions.assertThat(lstPositions.get(1)).isNotNull();
        Assertions.assertThat(lstPositions.get(1).getX()).isEqualTo(2);
        Assertions.assertThat(lstPositions.get(1).getY()).isEqualTo(3);
        Assertions.assertThat(lstPositions.get(1).getOrientation()).isEqualTo("N");
    }

    @Test
    void process_hit_twoOutOfThreeMowers(){
        Mower m1 = new Mower(1, 0, "FFFFF", new Position(2, 5, "S"), new Position(2, 5, "S"));
        Mower m2 = new Mower(2, 0, "FFFFFFFFFFF", new Position(2, 1, "N"), new Position(2, 1, "N"));
        Mower m3 = new Mower(3, 0, "FFLLFR", new Position(1, 4, "S"), new Position(1, 4, "S"));

        List<Mower> lstMower = Arrays.asList(m1,m2,m3);

        int WIDTH = 5;
        int LENGTH = 5;

        PerformanceService perform = new PerformanceService();
        List<Position> lstPositions = perform.process(lstMower,WIDTH, LENGTH);

        Assertions.assertThat(lstPositions.get(0)).isNotNull();
        Assertions.assertThat(lstPositions.get(0).getX()).isEqualTo(2);
        Assertions.assertThat(lstPositions.get(0).getY()).isEqualTo(4);
        Assertions.assertThat(lstPositions.get(0).getOrientation()).isEqualTo("S");

        Assertions.assertThat(lstPositions.get(1)).isNotNull();
        Assertions.assertThat(lstPositions.get(1).getX()).isEqualTo(2);
        Assertions.assertThat(lstPositions.get(1).getY()).isEqualTo(3);
        Assertions.assertThat(lstPositions.get(1).getOrientation()).isEqualTo("N");

        Assertions.assertThat(lstPositions.get(2)).isNotNull();
        Assertions.assertThat(lstPositions.get(2).getX()).isEqualTo(1);
        Assertions.assertThat(lstPositions.get(2).getY()).isEqualTo(3);
        Assertions.assertThat(lstPositions.get(2).getOrientation()).isEqualTo("E");
    }

    @Test
    void process_hit_threeMowers(){
        Mower m1 = new Mower(1, 0, "FF", new Position(2, 5, "S"), new Position(2, 5, "S"));
        Mower m2 = new Mower(2, 0, "FF", new Position(2, 1, "N"), new Position(2, 1, "N"));
        Mower m3 = new Mower(3, 0, "FF", new Position(0, 3, "E"), new Position(0, 3, "E"));

        List<Mower> lstMower = Arrays.asList(m1,m2,m3);

        int WIDTH = 5;
        int LENGTH = 5;

        PerformanceService perform = new PerformanceService();
        List<Position> lstPositions = perform.process(lstMower,WIDTH, LENGTH);

        Assertions.assertThat(lstPositions.get(0)).isNotNull();
        Assertions.assertThat(lstPositions.get(0).getX()).isEqualTo(2);
        Assertions.assertThat(lstPositions.get(0).getY()).isEqualTo(4);
        Assertions.assertThat(lstPositions.get(0).getOrientation()).isEqualTo("S");

        Assertions.assertThat(lstPositions.get(1)).isNotNull();
        Assertions.assertThat(lstPositions.get(1).getX()).isEqualTo(2);
        Assertions.assertThat(lstPositions.get(1).getY()).isEqualTo(2);
        Assertions.assertThat(lstPositions.get(1).getOrientation()).isEqualTo("N");

        Assertions.assertThat(lstPositions.get(2)).isNotNull();
        Assertions.assertThat(lstPositions.get(2).getX()).isEqualTo(1);
        Assertions.assertThat(lstPositions.get(2).getY()).isEqualTo(3);
        Assertions.assertThat(lstPositions.get(2).getOrientation()).isEqualTo("E");
    }

    @Test
    public void testReadFile() {
        URL url = this.getClass().getResource("/newInput.txt");
        File file = new File(url.getFile());
        assertTrue(file.exists());
    }

}
