import java.time.LocalDate;

public class CrispyFlour extends Material {
    int quantity;

    public CrispyFlour(String id, String name, LocalDate manufacturingDate, int cost) {
        super(id, name, manufacturingDate, cost);
    }

    public CrispyFlour(String id, String name, LocalDate manufacturingDate, int cost, int quantity) {
        super(id, name, manufacturingDate, cost);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CrispyFlour{" +
                "quantity=" + quantity +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", manufacturingDate=" + manufacturingDate +
                ", cost=" + cost +
                '}';
    }

    @Override
    public double getAmount() {
        return quantity * cost;
    }

    @Override
    public LocalDate getExpiryDate() {
        return manufacturingDate.plusYears(1);
    }
}