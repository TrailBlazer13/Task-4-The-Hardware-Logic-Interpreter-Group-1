import java.util.List;

public class Main {

    public static void main(String[] args) {
        HardwareRepository repo = new HardwareRepository();
        repo.createTable();   
        repo.seedData();      
      
        List<Hardware> hardwareList = repo.findAll();

        System.out.println();
        System.out.println("========================================");
        System.out.println("         HARDWARE MASTERLIST            ");
        System.out.println("========================================");
        System.out.printf("%-5s %-20s %-6s %-10s %-20s%n",
                "ID", "Brand", "Spec", "Type", "Interpretation");
        System.out.println("-".repeat(65));

        for (Hardware h : hardwareList) {
            System.out.printf("%-5d %-20s %-6d %-10s %-20s%n",
                    h.getId(),
                    h.getBrand(),
                    h.getSpec(),
                    h.getType(),
                    h.interpretSpec());
        }

        System.out.println();

        int laptops16  = 0;
        int laptops32  = 0;
        int phones50   = 0;

        for (Hardware h : hardwareList) {
            if (h instanceof Laptop) {
                if (h.getSpec() == 16) laptops16++;
                if (h.getSpec() == 32) laptops32++;
            } else if (h instanceof Phone) {
                if (h.getSpec() == 50) phones50++;
            }
        }

        System.out.println("========================================");
        System.out.println("       LAPTOP AND PHONE INVENTORY       ");
        System.out.println("========================================");
        System.out.println("Total 16GB Laptops  : " + laptops16);
        System.out.println("Total 32GB Laptops  : " + laptops32);
        System.out.println("Total 50MP Phones   : " + phones50);
        System.out.println("========================================");
    }
}
