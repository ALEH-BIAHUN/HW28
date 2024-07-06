public class Car {
    private String modelCar;
    private int yearCar;
    private double priceCar;

    public Car() {
    }

    public Car(String modelCar, int yearCar, double priceCar) {
        this.modelCar = modelCar;
        this.yearCar = yearCar;
        this.priceCar = priceCar;
    }

    public String getModelCar() {
        return modelCar;
    }

    public void setModelCar(String modelCar) {
        this.modelCar = modelCar;
    }

    public int getYearCar() {
        return yearCar;
    }

    public void setYearCar(int yearCar) {
        this.yearCar = yearCar;
    }

    public double getPriceCar() {
        return priceCar;
    }

    public void setPriceCar(double priceCar) {
        this.priceCar = priceCar;
    }
}