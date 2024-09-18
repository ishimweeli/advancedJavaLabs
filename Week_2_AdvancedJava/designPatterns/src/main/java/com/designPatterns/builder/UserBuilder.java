package com.designPatterns.builder;

public class UserBuilder {
    public static class User {
        private final String name;
        private final int age;
        private String email;
        private String phoneNumber;

        private User(UserBuilder builder) {
            this.name = builder.name;
            this.age = builder.age;
            this.email = builder.email;
            this.phoneNumber = builder.phoneNumber;
        }

        @Override
        public String toString() {
            return "User{name='" + name + "', age=" + age +
                    ", email='" + email + "', phoneNumber='" + phoneNumber + "'}";
        }
    }

    private final String name;
    private final int age;
    private String email;
    private String phoneNumber;

    public UserBuilder(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public User build() {
        return new User(this);
    }

    public static void main(String[] args) {
        User user1 = new UserBuilder("John Doe", 30)
                .setEmail("john@example.com")
                .build();

        User user2 = new UserBuilder("Jane Smith", 25)
                .setPhoneNumber("123-456-7890")
                .build();

        System.out.println(user1);
        System.out.println(user2);
    }
}