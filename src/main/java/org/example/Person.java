package org.example;

abstract public class Person {
    protected String firstName;
    protected String lastName;
    protected Integer age;
    protected Person partner;

    public String getMaidenName() {
        return maidenName;
    }

    private String maidenName;

    Person(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.maidenName= lastName;

    }
    abstract boolean isRetired();
    abstract void registerPartnership(Person person);

    abstract void deregisterPartnership(boolean shouldReturnToMaidenName);

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;

    }
    public Integer getAge(){
        return this.age;
    }
    public void setAge(Integer age){
        this.age = age;
    }

}