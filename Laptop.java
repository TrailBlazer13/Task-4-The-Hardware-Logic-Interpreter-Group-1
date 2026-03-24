
public class Laptop extends Hardware {

    public Laptop(int id, String brand, int spec, String type) {
        super(id, brand, spec, type);
    }

    
    @Override
    public String interpretSpec() {
        return getSpec() + "GB RAM";
    }
}
