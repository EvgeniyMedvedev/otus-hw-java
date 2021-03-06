package homework;

import java.util.Objects;

public final class Customer {
    private final long id;
    private String name;
    private long scores;

    //todo: 1. в этом классе надо исправить ошибки

    public Customer(long id, String name, long scores) {
        this.id = id;
        this.name = name;
        this.scores = scores;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        new Customer(this.id, name, this.scores);
    }

    public long getScores() {
        return scores;
    }

    public void setScores(long scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", scores=" + scores +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            System.out.println("Ссылаются на один объект");
            return true;
        }

        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        System.out.println("Эквивалентны по айди - " + (id == customer.id));
        return id == customer.id;

//        if (id != customer.id) return false;
//        if (scores != customer.scores) return false;
//        return name != null ? name.equals(customer.name) : customer.name == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
//        result = 31 * result + (name != null ? name.hashCode() : 0);
//        result = 31 * result + (int) (scores ^ (scores >>> 32));
        System.out.println("hash - " + this);
        return Objects.hash(id);
    }
}
