// JI ZHOU jiz167@pitt.edu cs1501 project 3


 
public class Car implements Comparable<Car> {
    private String Vin = "";
    private String Make = "";
    private String Model = "";
    private String Color = "";
    private double Mileage = 0;
    private double Price = 0;
    private boolean ComparePrice; 
    private boolean CompareMileage; 

    public Car(String vin, String make, String model, int price, int mileage,String color ){
        this.Vin = vin;
        this.Make = make;
        this.Model = model;
        this.Color = color;
        this.Mileage = mileage;
        this.Price = price;
    }

    public String getVin(){
        return Vin;
    }

    public String getMake(){
        return Make;
    }

    public String getModel(){
        return Model;
    }

    public String getColor(){
        return Color;
    }

    public double getMileage(){
        return Mileage;
    }

    public double getPrice(){
        return Price;
    }

    public boolean getCompareMileage() {
        return CompareMileage;
    }

    public boolean getComparePrice() {
        return ComparePrice;
    }

    public void setColor(String newColor){
        this.Color = newColor;
    }

    public void setMileage(double newMileage){
        this.Mileage = newMileage;
    }

    public void setPrice(double newPrice){
        this.Price = newPrice;
    }

    public void setComparePrice(boolean flag) {
        this.ComparePrice = flag;
    }

    public void setCompareMileage(boolean flag) {
        this.CompareMileage = flag;
    }

    public void showResults(){
    	System.out.println("=======================");
    	System.out.println("The candidate is:");
    	System.out.println("");
        System.out.println("VIN:  "+Vin);
        System.out.println("Make:  "+Make);
        System.out.println("Model:  "+Model);
        System.out.println("Price:  $ "+Price);
        System.out.println("Mileage:    "+Mileage);
        System.out.println("Color:  "+Color);
        System.out.println("=======================");
    }
        //@Override 
    public int compareTo(Car car) {
        if(ComparePrice) {
            assert !CompareMileage;
            if(Price > car.getPrice())
                return 1;
            else
                return 0;
        }
        if(CompareMileage) {
            assert !ComparePrice;
            if(Mileage > car.getMileage())
                return 1;
            else
                return 0;
        }
        return 0;
    }

}