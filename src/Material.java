import java.time.LocalDate;

public abstract class Material {
    String id;
    String name;
    LocalDate manufacturingDate;
    int cost;


    public Material(String id, String name, LocalDate manufacturingDate, int cost) {
        this.id = id;
        this.name = name;
        this.manufacturingDate = manufacturingDate;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Material{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", manufacturingDate=" + manufacturingDate +
                ", cost=" + cost +
                '}';
    }

    public abstract double getAmount();
    public abstract LocalDate getExpiryDate();
}

