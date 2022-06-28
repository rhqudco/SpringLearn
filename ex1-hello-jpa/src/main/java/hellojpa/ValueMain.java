package hellojpa;

public class ValueMain {
    public static void main(String[] args) {
        Address address1 = new Address("city", "address", "10000");
        Address address2 = new Address("city", "address", "10000");
        System.out.println("address1 equals address2 : " + address1.equals(address2));
    }
}
