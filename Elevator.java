
import java.util.*;

class Elevator {
    private int floor;
    private Random randFloor;
    public Elevator(){
        randFloor = new Random();
        floor = randFloor.nextInt(10)+1;
        
    }
    
    public void callElevator(String eDirection){
        if(eDirection == null){
            System.out.println("\nThe elevator is already here!\n");
        }
        else{
            System.out.println("\nThe elevator is coming "+ eDirection +
                " from floor " + floor + "\n");
        }
    }
    
    public int getFloor(){
        return floor;
    }
    
    public void setFloor(int floor){
        this.floor = floor;
    }
}
