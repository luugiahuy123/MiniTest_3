import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaterialManager implements Serializable {
    static String FILE_PATH = "15ProMax.txt";
    public static List<Material> materials;


    public MaterialManager() {
        materials = new ArrayList<>();
        materials.add(new CrispyFlour("B10", "Bot chien1", LocalDate.of(2024, 1, 1), 10, 5));
        materials.add(new CrispyFlour("B11", "Bot chien2", LocalDate.of(2024, 2, 1), 15, 10));
        materials.add(new CrispyFlour("B12", "Bot chien3", LocalDate.of(2024, 3, 1), 20, 20));
        materials.add(new CrispyFlour("B13", "Bot chien4", LocalDate.of(2024, 4, 1), 25, 25));
        materials.add(new CrispyFlour("B14", "Bo chien5", LocalDate.of(2024, 5, 1), 30, 30));
        materials.add(new Meat("C14", "Thit heo", LocalDate.of(2024, 6, 1), 50, 1));
        materials.add(new Meat("C15", "Thit bo", LocalDate.of(2024, 7, 1), 60, 2));
        materials.add(new Meat("C16", "Thit ga", LocalDate.of(2024, 8, 1), 70, 3));
        materials.add(new Meat("C17", "Thit Lon", LocalDate.of(2024, 9, 1), 80, 4));
        materials.add(new Meat("C18", "Thit Trau", LocalDate.of(2024, 10, 1), 90, 5));
    }

    public static void writeDataToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(materials);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readDataFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            materials = (List<Material>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addMaterial(Material material) {
        materials.add(material);

    }

    public void removeMaterial(Material material) {
        materials.remove(material);
    }

    public void updateMaterial(Material oldMaterial, Material newMaterial) {
        int index = materials.indexOf(oldMaterial);
        if (index != -1) {
            materials.set(index, newMaterial);
        }
    }

    public double getTotalCost() {
        double total = 0;
        for (Material material : materials) {
            total += material.getAmount();
        }
        return total;
    }

    public void sortMaterialsByCost() {
        materials.sort(Comparator.comparing(Material::getAmount));
    }

    public double getDiscountedCost(LocalDate today) {
        double total = 0;
        for (Material material : materials) {
            long daysToExpiry = ChronoUnit.DAYS.between(today, material.getExpiryDate());
            if (material instanceof Meat && daysToExpiry <= 5) {
                total += material.getAmount() * 0.7;
            } else if (material instanceof Meat) {
                total += material.getAmount() * 0.9;
            } else if (material instanceof CrispyFlour && daysToExpiry <= 60) {
                total += material.getAmount() * 0.6;
            } else if (material instanceof CrispyFlour && daysToExpiry <= 120) {
                total += material.getAmount() * 0.8;
            } else {
                total += material.getAmount() * 0.95;
            }
        }
        return total;
    }

    public double getDiscountDifference(LocalDate today) {
        return getTotalCost() - getDiscountedCost(today);
    }

    public void displayMaterial() {
        for (Material material : materials) {
            System.out.println(material);
        }
    }
}


