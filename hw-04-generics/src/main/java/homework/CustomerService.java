package homework;


import java.util.*;

public class CustomerService {

    private final NavigableMap<Customer, String> storage = new TreeMap<>(Comparator.comparingLong(Customer::getScores));
    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны

    public Map.Entry<Customer, String> getSmallest() {
        //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk

        return storage.firstEntry(); // это "заглушка, чтобы скомилировать"
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        return storage.higherEntry(customer) != null ? storage.higherEntry(customer) : storage.get(customer) == null ? null : storage.lastEntry();
    }

    public void add(Customer customer, String data) {
        storage.put(customer, data);
    }


    public static void main(String[] args) {
        Entry entry = new Entry(1, "name");
        NavigableMap<Entry, String> list = new TreeMap<>(Comparator.comparingInt(Entry::getId));
        list.put(entry, "data");
        Map.Entry<Entry, String> key = list.firstEntry();
        key.getKey().name = "new";

        System.out.println(entry.name);
        System.out.println(entry.equals(list.firstEntry().getKey()));
        System.out.println(entry.name.equals(list.firstEntry().getKey().name));

        Customer customer = new Customer(1, "name", 12);
        NavigableMap<Customer, String> list2 = new TreeMap<>(Comparator.comparingLong(Customer::getScores));
        list2.put(customer, "data");
        list2.put(new Customer(2,"", 14),"");
        list2.put(new Customer(3,"", 13),"");
        Map.Entry<Customer, String> key1 = list2.firstEntry();
        key1.getKey().setName("new");

        System.out.println(customer.getName());
        System.out.println(customer.equals(list2.firstEntry().getKey()));
        System.out.println(customer.getName().equals(list2.firstEntry().getKey().getName()));

    }

    public static class Entry {
        public int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String name;

        public Entry(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                System.out.println("Равны по ссылкам");
                return true;
            }
            if (o == null || getClass() != o.getClass()) return false;
            Entry entry = (Entry) o;
            return id == entry.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }
}
