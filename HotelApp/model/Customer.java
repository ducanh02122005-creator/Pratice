package model;

public class Customer {
    private final String firstName;
    private final String lastName;
    private final String email;

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$";

    public Customer(String firstName, String lastName, String email) {
        if (!email.matches(EMAIL_REGEX)) {
            throw new IllegalArgumentException(
                    "Invalid email address: '" + email + "'. Please use a valid format like name@domain.com"
            );
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}