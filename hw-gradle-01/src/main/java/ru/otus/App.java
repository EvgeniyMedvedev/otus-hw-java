/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ru.otus;


import com.google.common.base.MoreObjects;

public class App {
    private String lastName = "Akakiy";
    private String firstName = "Javovich";
    private Integer age = 100;
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());

        System.out.println(new App());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .omitNullValues()
                .add("lastName", lastName)
                .add("firstName", firstName)
                .toString();
    }
}