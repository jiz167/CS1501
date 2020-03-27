// JI ZHOU jiz167@pitt.edu cs1501 project 3
import java.util.*;
import java.util.Scanner;

public class CarTracker{
    private static MinPQ<Car> pricePQ = new MinPQ<Car>();
	private static MinPQ<Car> mileagePQ = new MinPQ<Car>();

	public static void main(String[] args){

        System.out.println("");
        System.out.println("");
        System.out.println("             Welcome to use my CarTracker~");
        System.out.println("");
        System.out.println("");
	    while (true){
        
        System.out.println("-----------------------");
	    System.out.println("1. Add a car");
	    System.out.println("2. Update a car");
	    System.out.println("3. Remove a car");
	    System.out.println("4. Retrieve the lowest priced car");
	    System.out.println("5. Retrieve the lowest mileage car");
	    System.out.println("6. Retrieve the lowest priced car by make/model");
	    System.out.println("7. Retrieve the lowest mileage car by make/model");
	    System.out.println("8. Exit");
        System.out.println("-----------------------");
        System.out.println("");
	    System.out.print("Please select one function below by input its number(1-8):");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        // determine for all options
 		if(input.equals("1")){
                addACar();
            } else if(input.equals("2")){
                updateACar();
            } else if(input.equals("3")){
                removeACar();
            } else if(input.equals("4")){
                retriLPCar();
            } else if(input.equals("5")){
                retriLMileCar();
            } else if(input.equals("6")){
                retriLPriceCarByMakeModel();
            } else if(input.equals("7")){
                retriLMileCarByMakeModel();
            } else if(input.equals("8")){
                System.out.println("-----------------------");
                System.out.println("");
                System.out.println("Thanks for using my CarTracker! Have a nice day~");
                System.exit(0); //exit when the user wants to
            } else{
                System.out.println("Invalid input. Please only enter numbers between 1-8.");
            }
		}

	}		

	public static void addACar(){ //ask the user to enter a new car
        // check whether the input is valid
        Scanner sc = new Scanner(System.in);
		boolean invalid = true;
        String vin = ""; // initialize vin
            while (invalid){
                while(true && invalid){
                System.out.print("Please enter its VIN: ");
                vin = sc.nextLine();
                if(vin.length() != 17) {
                    System.out.println("VIN must be an exact 17 string. Could you try again?  ");
                    break;
                }
                vin = vin.toUpperCase();
                // check whether there are some not allowed char
                if (vin.contains("I")||vin.contains("O")||vin.contains("Q")){ 
                    System.out.println("VIN should not contain 'I' or 'O'. Could you try again?  ");
                    break;
                }
                invalid = false;
            }
        }
        //entering informaiton
        System.out.print("Please enter its make(e.g., Ford, Toyota, Honda): ");
        String make = sc.nextLine();

        System.out.print("Please enter its model(e.g., Fiesta, Camry, Civic): ");
        String model = sc.nextLine();

        System.out.print("Please enter its price(in $): ");
        int price = Integer.parseInt(sc.nextLine());
        
        System.out.print("Please enter its mileage: ");
        int mileage = Integer.parseInt(sc.nextLine());

        System.out.print("Please enter its color: ");
        String color = sc.nextLine();
        //save the information
        Car newCar1 = new Car(vin,make,model,price,mileage,color);
        Car newCar2 = newCar1;
        newCar1.setComparePrice(true);
    	newCar2.setCompareMileage(true);
        pricePQ.insert(newCar1);
        mileagePQ.insert(newCar2);
    }
    // update the car's information
	public static void updateACar(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the car's VIN:   ");
        String vin = sc.nextLine();
        Map<String, Integer> priceMap = MinPQ.getPriceMap();
  		Map<String, Integer> mileageMap = MinPQ.getMileageMap();
        while(!priceMap.containsKey(vin)){ 
            System.out.println("I cannot identify it, please try again, or enter 'b' to back");
            System.out.print("Please enter the car's VIN:   "); 
            vin = sc.nextLine();
            if(vin.equals("b")) return; 
        }

        System.out.println("Which do you want to update:   1) Price    2) Mileage    3) Color    4)No update ");
        String update = sc.nextLine();
     	int index1 = priceMap.get(vin);
    	int index2 = mileageMap.get(vin);

        if(update.equals("1")){
            System.out.print("Please enter the new price (in $): ");
            int price = Integer.parseInt(sc.nextLine());
            pricePQ.keyOf(index1).setPrice(price);
	        mileagePQ.keyOf(index2).setPrice(price);
	        pricePQ.changeKey(index1, pricePQ.keyOf(index1));
        } else if(update.equals("2")){ //Update the mileage of the car
            System.out.print("Please enter the new mileage: ");
            int mileage = Integer.parseInt(sc.nextLine());
            pricePQ.keyOf(index1).setMileage(mileage);
        	mileagePQ.keyOf(index2).setMileage(mileage);
        mileagePQ.changeKey(index2, mileagePQ.keyOf(index2));
        } else if(update.equals("3")){
            System.out.print("Please enter the new color: ");
            String color = sc.nextLine();
            pricePQ.keyOf(index1).setColor(color);
 	       mileagePQ.keyOf(index2).setColor(color);
        } else if(update.equals("4")){
            return;
        }
    }
    // using the vin to remove a car
	public static void removeACar(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter the car's VIN: ");
        String vin = sc.nextLine();
        Map<String, Integer> priceMap = MinPQ.getPriceMap();    
   		Map<String, Integer> mileageMap = MinPQ.getMileageMap();
        while(!priceMap.containsKey(vin)){ 
            System.out.println("I cannot identify it, please try again, or enter 'b' to back");
            System.out.print("Please enter the car's VIN:   "); 
            vin = sc.nextLine();
            if(vin.equals("b")) return; 
        }
            int index1 = priceMap.get(vin);
            int index2 = mileageMap.get(vin);
            pricePQ.delete(index1);
            mileagePQ.delete(index2);
            priceMap.remove(vin);
            mileageMap.remove(vin);
            System.out.println("That car has been removed!");
	}

    // Retrieve the lowest price car
	public static void retriLPCar(){
        Car retriLPCar = pricePQ.min();
        if(pricePQ.numElements() == 0) {
	    System.out.println("Sorry, but there is no car in the database.");
	    return;
    	} else{
        	retriLPCar.showResults();
		}
	}
    // Retrieve the lowest mileage car
	public static void retriLMileCar(){
        Car retriLMileCar = mileagePQ.min();
        if(pricePQ.numElements() == 0){ 
        	System.out.println("Sorry, but there is no car in the database.");
        } else{
        	retriLMileCar.showResults();
		}
	}
    //Retrieve the lowest price car by make and model
	public static void retriLPriceCarByMakeModel(){
        Scanner sc = new Scanner(System.in);
        // ask the user's perference
        System.out.print("Please enter the make: ");
        String make = sc.nextLine();
        System.out.print("Enter a model: ");
        String model = sc.nextLine();
		if(pricePQ.numElements() == 0) {
		      System.out.println("Sorry, but there is no such car in the database.");
		      return;
		    } else{
		    	pricePQ.priceMakeModel(make, model);
		}
	}

    //Retrieve the lowest mileage car by make and model
	public static void retriLMileCarByMakeModel(){
        Scanner sc = new Scanner(System.in);
        // the same, ask the user's preference
        System.out.print("Please enter the make: ");
        String make = sc.nextLine();
        System.out.print("Enter a model: ");
        String model = sc.nextLine();
		if(pricePQ.numElements() == 0) {
		      System.out.println("Sorry, but there is no such car in the database.");
		      return;
		    } else{
		    	mileagePQ.priceMakeModel(make, model);
		}
	}
}