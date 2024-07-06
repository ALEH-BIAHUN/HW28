import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.nio.file.Files.readAllLines;

public class Main {

    private static final long KB_SIZE = 1024;
    private static final long MB_SIZE = 1024*KB_SIZE;
    private static final long GB_SIZE = 1024*MB_SIZE;

    public static void main(String[] args) throws IOException {
        System.out.println("============= ДЗ 2 =============");
        System.out.println("Введите путь к файлу или папке: ");
        String path = new Scanner(System.in).nextLine();
        printSize(path);

        System.out.println("\n ============= ДЗ 3 =============");
        setSale();
    }
    private static void setSale() {
        try {
            List<String> strings = Files.readAllLines(Path.of("HW28/data/car_price.txt"));
            List<Car> cars = strings.stream()
                    .map(s -> {
                        String[] elements = s.split("\\s");
                        return new Car(elements[0],
                                Integer.parseInt(elements[1]),
                                Double.parseDouble(elements[2]));
            })
                    .collect(Collectors.toList());

            LocalDate actualDate = LocalDate.now();
            List<String> discountedPrice = cars.stream()
                    .map(car -> {
                        int carAge = actualDate.getYear() - car.getYearCar();
                        double discount = carAge > 5 ? 0.05 : 0.02;
                        double discountedPriceCar = car.getPriceCar() * (1 - discount);
                        return car.getModelCar() + " " + car.getYearCar() + " " + discountedPriceCar;
                    }).collect(Collectors.toList());

            Files.write(Path.of("HW28/data/car_price2.txt"), discountedPrice);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printSize(String path) {
        File file = new File(path);
        long fileSize;
        try {
            if (file.isFile()) {
                fileSize = file.length();
            } else {
                fileSize = Files.walk(Path.of(path))
                        .filter(p -> p.toFile().isFile())
                        .mapToLong(p -> p.toFile().length())
                        .sum();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sizeConverter(fileSize);
    }

    public static void sizeConverter(double fileSize) {
        if(fileSize <= KB_SIZE) {
            System.out.printf("Size of your folder or file: %.2f bytes \n", fileSize);
        } else if(fileSize <= MB_SIZE) {
            System.out.printf("Size of your folder or file: %.2f KB \n", fileSize/KB_SIZE);
        } else if(fileSize <= GB_SIZE) {
            System.out.printf("Size of your folder or file: %.2f MB \n", fileSize/MB_SIZE);
        } else {
            System.out.printf("Size of your folder or file: %.2f GB \n", fileSize/GB_SIZE);
        }
    }
}