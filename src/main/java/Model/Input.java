package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Input {
    private static int WIDTH;
    private static int LENGTH;
    static private int id = 1;

    public List<Mower> createMower(){
        List<Mower> lstMover = new ArrayList<>();
        File file = new File("src/main/resources/input.txt");
        BufferedReader br = null;
        try{
            FileReader fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line, line1, line2;
            line = br.readLine();
            if(line!= null){
                String[] row = line.split(" ");
                WIDTH = Integer.parseInt(row[0]);
                LENGTH = Integer.parseInt(row[1]);
            }

            while( (line1=br.readLine())!=null && (line2=br.readLine())!=null){
                String[] row1 = line1.split(" ");
                String instruction = line2;
                int nextIndexOrder = 0;

                int x = Integer.parseInt(row1[0]) ;
                int y = Integer.parseInt(row1[1]);
                String orientation = row1[2];

                Position currentPosition = new Position(x,y,orientation);
                Position nextPosition = new Position(x,y,orientation);

                Mower m = new Mower(id,nextIndexOrder, instruction , currentPosition, nextPosition);
                id++;
                lstMover.add(m);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.toString());
        } catch (IOException e) {
            System.out.println("Unable to read file: " + file.toString());
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                System.out.println("Unable to close file: " + file.toString());
            }
        }
        return lstMover;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getLENGTH() {
        return LENGTH;
    }
}
