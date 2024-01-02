import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MaterialManager manager = new MaterialManager();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Menu:");
            System.out.println("1. Thêm vật liệu");
            System.out.println("2. Xoá Vật liệu");
            System.out.println("3. Sửa Vật liệu");
            System.out.println("4. Tổng vật liệu");
            System.out.println("5. Sắp xếp Vật liệu");
            System.out.println("6. Nhận chi phí giảm giá");
            System.out.println("7. Nhận chênh lệch chiết khấu");
            System.out.println("8. Hiển thị danh sách sản pham");
            System.out.println("9. Ghi dữ liệu vào tệp");
            System.out.println("10. Đọc dữ liệu từ tệp");
            System.out.println("0. Exit");
            System.out.println("Enter your choice:");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Nhập loại vật liệu (1 cho Bột , 2 cho thịt):");
                    int type = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter id:");
                    String id = scanner.nextLine();
                    System.out.println("Enter name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter manufacturing date (yyyy-mm-dd):");
                    String date = scanner.nextLine();
                    LocalDate manufacturingDate = LocalDate.parse(date);
                    System.out.println("Enter cost:");
                    int cost = scanner.nextInt();
                    if (type == 1) {
                        System.out.println("Enter quantity:");
                        int quantity = scanner.nextInt();
                        manager.addMaterial(new CrispyFlour(id, name, manufacturingDate, cost, quantity));
                    } else if (type == 2) {
                        System.out.println("Enter weight:");
                        double weight = scanner.nextDouble();
                        manager.addMaterial(new Meat(id, name, manufacturingDate, cost, weight));
                    }
                    break;
                case 2:
                    System.out.println("Nhập ID của tài liệu để xóa:");
                    id = scanner.nextLine();
                    Material materialToRemove = null;
                    for (Material m : manager.materials) {
                        if (m.id.equals(id)) {
                            materialToRemove = m;
                            break;
                        }
                    }

                    if (materialToRemove != null) {
                        manager.removeMaterial(materialToRemove);
                        System.out.println("Vật liệu đã xoá");
                    } else {
                        System.out.println("Vật liệu không tìm thấy");
                    }
                    break;
                case 3:
                    System.out.println("Nhập Id muốn sửa");
                    String oldId = scanner.nextLine();
                    Material oldMaterial = null;
                    for (Material m : manager.materials) {
                        if (m.id.equals(oldId)) {
                            oldMaterial = m;
                            break;
                        }
                    }
                    if (oldMaterial != null) {
                        System.out.println("Nhập loại vật liệu (1 cho Bột , 2 cho thịt):");
                        int newType = scanner.nextInt();
                        scanner.nextLine();  // consume newline left-over
                        System.out.println("Enter id:");
                        String newId = scanner.nextLine();
                        System.out.println("Enter name:");
                        String newName = scanner.nextLine();
                        System.out.println("Enter manufacturing date (yyyy-mm-dd):");
                        String newDate = scanner.nextLine();
                        LocalDate newManufacturingDate = LocalDate.parse(newDate);
                        System.out.println("Enter cost:");
                        int newCost = scanner.nextInt();
                        if (newType == 1) {
                            System.out.println("Enter quantity:");
                            int newQuantity = scanner.nextInt();
                            manager.updateMaterial(oldMaterial, new CrispyFlour(newId, newName, newManufacturingDate, newCost, newQuantity));
                        } else if (newType == 2) {
                            System.out.println("Enter weight:");
                            double newWeight = scanner.nextDouble();
                            manager.updateMaterial(oldMaterial, new Meat(newId, newName, newManufacturingDate, newCost, newWeight));
                        }
                    } else {
                        System.out.println("Vật liệu không tìm thấy");
                    }
                    break;
                case 4:
                    System.out.println("Tổng cost: " + manager.getTotalCost());
                    break;
                case 5:
                    manager.sortMaterialsByCost();
                    System.out.println("Vật liệu được sắp xếp theo chi phí.");
                    break;
                case 6:
                    System.out.println("Nhập ngày hôm nay (Yyyy-MM-DD):");
                    String date2 = scanner.nextLine();
                    LocalDate today = LocalDate.parse(date2);
                    System.out.println("Chi phí giảm giá: " + manager.getDiscountedCost(today));
                    break;
                case 7:
                    System.out.println("Nhập ngày hôm nay (Yyyy-MM-DD):");
                    String date3 = scanner.nextLine();
                    LocalDate today2 = LocalDate.parse(date3);
                    System.out.println("Chi phí giảm giá: " + manager.getDiscountDifference(today2));
                    break;
                case 8:
                    manager.displayMaterial();
                    break;
                case 9:
                    manager.writeDataToFile();
                    System.out.println("Đã lưu sản phẩm vào tệp!");
                    break;
                case 10:
                    manager.readDataFromFile();
                    System.out.println("Đã đọc sản phẩm từ tệp!");
                    break;
                case 0:
                    System.out.println("Thoát");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        } while (choice != 0);
    }
}