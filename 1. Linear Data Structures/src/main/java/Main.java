import implementations.ArrayList;
import interfaces.List;

public class Main {
    public static void main(String[] args) {

        List<String> elements = new ArrayList<>();

        elements.add("1");
        elements.add("3");
        elements.add("4");
        elements.add(1, "Two");

        for (String element : elements) {
            System.out.println(element);
        }
    }
}
