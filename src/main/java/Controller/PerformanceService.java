package Controller;

import Model.Action;
import Model.Mower;
import Model.Orientation;
import Model.Position;

import java.util.ArrayList;
import java.util.List;

public class PerformanceService {

    private void nextTurnLeft(Mower m){
        String orientation = m.getCurrentPosition().getOrientation();
        if(orientation.equals(Orientation.N.name())){
            m.getNextPosition().setOrientation(Orientation.W.name());
        } else if(orientation.equals(Orientation.W.name())){
            m.getNextPosition().setOrientation(Orientation.S.name());
        } else if(orientation.equals(Orientation.S.name())){
            m.getNextPosition().setOrientation(Orientation.E.name());
        } else if(orientation.equals(Orientation.E.name())){
            m.getNextPosition().setOrientation(Orientation.N.name());
        }
    }

    private void nextTurnRight(Mower m){
        String orientation = m.getCurrentPosition().getOrientation();
        if(orientation.equals(Orientation.N.name())){
            m.getNextPosition().setOrientation(Orientation.E.name());
        } else if (orientation.equals(Orientation.E.name())){
            m.getNextPosition().setOrientation(Orientation.S.name());
        } else if (orientation.equals(Orientation.S.name())){
            m.getNextPosition().setOrientation(Orientation.W.name());
        } else if (orientation.equals(Orientation.W.name())){
            m.getNextPosition().setOrientation(Orientation.N.name());
        }
    }

    private void nextMoveForward(Mower m, int width, int length) {
        String orientation = m.getCurrentPosition().getOrientation();
        int x = m.getCurrentPosition().getX();
        int y = m.getCurrentPosition().getY();

        if (orientation.equals(Orientation.N.name())) {
            if (y + 1 <= length) {
                m.getNextPosition().setY(y+1);
            }
        } else if (orientation.equals(Orientation.S.name())) {
            if (y - 1 >= 0) {
                m.getNextPosition().setY(y-1);
            }
        } else if (orientation.equals(Orientation.E.name())) {
            if (x + 1 <= width) {
                m.getNextPosition().setX(x+1);
            }
        } else if (orientation.equals(Orientation.W.name())) {
            if (x - 1 >= 0) {
                m.getNextPosition().setX(x-1);
            }
        }
    }

    private void calculateNextOrder(Mower m, int width, int length){
        if(m.getNextIndexOrder() < m.getInstruction().toCharArray().length){
            char nextOrder = m.getInstruction().charAt(m.getNextIndexOrder());
            if (nextOrder == Action.F.getName()){
                nextMoveForward(m,width, length);
            } else if (nextOrder == Action.L.getName()){
                nextTurnLeft(m);
            } else if (nextOrder == Action.R.getName()){
                nextTurnRight(m);
            }
        }
    }

    private void updatePosition(Mower m, boolean isGoodMove){
        if (isGoodMove){
            m.getCurrentPosition().setX(m.getNextPosition().getX());
            m.getCurrentPosition().setY(m.getNextPosition().getY());
            m.getCurrentPosition().setOrientation(m.getNextPosition().getOrientation());
        } else {
            m.getNextPosition().setX(m.getCurrentPosition().getX());
            m.getNextPosition().setY(m.getCurrentPosition().getY());
            m.getNextPosition().setOrientation(m.getCurrentPosition().getOrientation());
            m.setGoodOrder(true);
        }
        System.out.println(this.getPosition(m));
    }

    private String getFinalPosition(Mower m){
        return "Mower" + m.getId() + " " + m.getCurrentPosition().getX() + " " + m.getCurrentPosition().getY() + " " + m.getCurrentPosition().getOrientation();
    }

    private String getPosition(Mower m){
        return "Mower" + m.getId() + " " + m.getCurrentPosition().getX() + " " + m.getCurrentPosition().getY() + " " + m.getCurrentPosition().getOrientation() + " " + m.getInstruction() + " nextIndex:" + m.getNextIndexOrder();
    }

    private boolean hasSamePosition(Mower m1, Mower m2) {
        return m1.getNextPosition().getX() == m2.getNextPosition().getX() && m1.getNextPosition().getY() == m2.getNextPosition().getY();
    }

    public List<Position> process(List<Mower> lstMower, int WIDTH, int LENGTH){
        List<Position> lstPosition= new ArrayList<>();
        int nextIndexOrder = 0;
        int maxLengthInstruction = 0;

        for(Mower m:lstMower){
            if(maxLengthInstruction <= m.getInstruction().toCharArray().length){
                maxLengthInstruction =  m.getInstruction().toCharArray().length;
            }
        }

        if(lstMower.size() == 1){
            Mower unique = lstMower.get(0);
            for(int i=0;i < unique.getInstruction().toCharArray().length; i++){
                this.calculateNextOrder(unique, WIDTH,LENGTH);
                nextIndexOrder++;
                unique.setNextIndexOrder(nextIndexOrder);
                System.out.println("step: " + nextIndexOrder);
                this.updatePosition(unique, unique.isGoodOrder());
            }
        } else {
            for(int index=0; index<maxLengthInstruction; index++ ){
                for(int i=0; i< lstMower.size()-1;i++){
                    Mower m1 = lstMower.get(i);
                    this.calculateNextOrder(m1, WIDTH,LENGTH);
                    for(int j=i+1; j< lstMower.size(); j++){
                        Mower m2 = lstMower.get(j);
                        this.calculateNextOrder(m2, WIDTH,LENGTH);
                        if( this.hasSamePosition(m1,m2) ){
                            m1.setGoodOrder(false);
                            m2.setGoodOrder(false);
                        }
                    }
                }
                nextIndexOrder++;

                System.out.println("step: " + nextIndexOrder);
                for(Mower m:lstMower){
                    m.setNextIndexOrder(nextIndexOrder);
                    this.updatePosition(m, m.isGoodOrder());
                }
            }
        }

        //Print Final Positions for Code Review
        System.out.println("Final Positions");
        for(Mower m:lstMower){
            System.out.println(this.getFinalPosition(m));
            lstPosition.add(m.getCurrentPosition());
        }
        return lstPosition;
    }
}
