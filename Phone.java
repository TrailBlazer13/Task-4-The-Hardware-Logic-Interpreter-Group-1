public class Phone extends Hardware {

    public Phone(int id, String brand, int spec, String type) {
        super(id, brand, spec, type);
    }

    @Override
    public String interpretSpec() {
        return getSpec() + " Megapixels";
    }
}
