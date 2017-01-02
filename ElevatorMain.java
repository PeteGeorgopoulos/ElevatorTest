
import java.util.*;
import java.util.concurrent.TimeUnit;

/*
 * Pete Georgopoulos :D
 * Elevator Challenge
 *     [x] - person can call/ride elevator
 *     [x] - msg telling which floor elevator is coming from
 *     [x] - msg telling which direction elevator is coming from
 *     [x] - user can ride elevator to different floor
      *additional challenges*
 *     [sorta] - user can explore current floor
 *     [ ] - user can leave elevator then recall. elevator will be moved from
             random thread(random rider).
 *     [ ] - allow multiple riders
 *     [ ] - set a max capacity that only the user can surpass which breaks elevator
 *     [ ] - 
*/
public class ElevatorMain {
    private String eDirection;
    private boolean running = true;
    Elevator elevator = new Elevator();
    Rider rider = new Rider();
    Scanner userInput;
    String msgCall = "Choose [1] to call elevator\n"
             + "Choose [2] to stay on floor.\n"
             + "Choose [3] to exit program.\n"
             + "Choice : ";
    
    String msgRide = "\nChoose which floor you'd like to go to (1 - 10)\n";
    //input which floor the user wishes to go to
    //put logic to change eDirection to userchoice vs rider
        //to do this, block off if statement and use choice if-block or call if-block
    //have random rider thead(s) to move elevator floor allowing user to get off
        //and then re-call elevator
    //have message that tells how many people are currently on elevator
    //set elevator capacity allowing only user to break it :D 
        //if random riders = # no new threads can enter
    
    public ElevatorMain(){
        elevatorOptions();
    }
    
    public void elevatorOptions(){
        System.out.println("      Welcome to the worlds coolest elevator.\n"+
                "Okay, not really but this elevator will tell you\n" +
                "where it's coming from, which direction it's going\n" + 
                "and all the other wonderfullness of an elevator.\n" +
                "Simply choose what to do from the following options.\n");
        while(running){
            System.out.println(msgCall);
            userInput = new Scanner(System.in);
            try{
            switch(userInput.nextInt()){
                case 1 :
                    callElevator();
                    rideElevator();
                    break;
                case 2 :
                    exploreFloor(); 
                    break;
                case 3 :
                    running = false;
                }
            }catch(Exception e){
                System.out.println("\nPlease choose an option between 1 - 3. thank you\n");
            }
            
                    
        }
    }
    
    public void callElevator(){
        if(elevator.getFloor()>rider.getFloor()){
            eDirection = "down";
            elevator.callElevator(eDirection);
            while(elevator.getFloor() != rider.getFloor()){
                elevator.setFloor(elevator.getFloor() -1);
                System.out.print(elevator.getFloor() + "\n");
                try {
                    Thread.sleep(1000);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("\nYour elevator has arrived\n");
        }
        else if(elevator.getFloor()<rider.getFloor()){
            eDirection = "up";
            elevator.callElevator(eDirection);
            while(elevator.getFloor() != rider.getFloor()){
                elevator.setFloor(elevator.getFloor() +1);
                System.out.print(elevator.getFloor() + "\n");
                try {
                    Thread.sleep(1000);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        else{
            eDirection = null;
            elevator.callElevator(eDirection);
        }
        
    }
    
    public void rideElevator(){
        System.out.println(msgRide + "\n");
        int userDestination;
        
        while(true){
            userInput = new Scanner(System.in);
            try{
                userDestination = userInput.nextInt();
                if(userDestination >= 1 && userDestination <= 10){
                    break;
                }
                else{
                    System.out.println("\nThere are only 10 floors. please choose again\n");
                }
            
            }catch(Exception e){
                System.out.println("\nNot a valid choice. Try again chosing 1-10\n");
            }
            
        }
        
        if(userDestination > rider.getFloor()){
            System.out.println("\nGoing up!!\n");
            while(rider.getFloor() != userDestination){
                System.out.println(rider.getFloor());
                try {
                    Thread.sleep(1000);
                    } catch(InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                rider.setFloor(rider.getFloor() + 1);
                elevator.setFloor(elevator.getFloor() + 1);
            }
            System.out.println("\nYou have arrived at floor " +
                    elevator.getFloor()+"\n");
        }
        else if(userDestination < rider.getFloor()){
            System.out.println("\nGoing Down!!\n");
            while(rider.getFloor() != userDestination){
                System.out.println(rider.getFloor());
                try {
                    Thread.sleep(1000);
                    } catch(InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                rider.setFloor(rider.getFloor() - 1);
                elevator.setFloor(elevator.getFloor() - 1);
            }
            System.out.println("\nYou have arrived at floor " +
                    elevator.getFloor() + "\n"); 
        }
        else if(userDestination == rider.getFloor()){
            System.out.println("\nYou're already on that floor, dumbass!\n");
            rideElevator();
        }
        
        
    }
    
    public void exploreFloor(){
        System.out.println("you step off the elevator...");
        try{
            Thread.sleep(2000);}
        catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("You look around a bit...");
        try{
            Thread.sleep(2000);}
        catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("nothing to see here.. ");
        try{
            Thread.sleep(2000);}
        catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("*you get bored and go back to the elevator*\n\n");
        try{
            Thread.sleep(2000);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        Random newFloor = new Random();
        elevator.setFloor(newFloor.nextInt(10)+1);
    }
    public static void main(String[] args){
        ElevatorMain mainApp = new ElevatorMain();
        
    }
}
