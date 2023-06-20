package org.keyin.passenger;

public class Passenger {

    private Long id;

    private String firstName;

    private String lastName;

    private int phoNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPhoNum() {
        return phoNum;
    }

    public void setPhoNum(int phoNum) {
        this.phoNum = phoNum;
    }
}

