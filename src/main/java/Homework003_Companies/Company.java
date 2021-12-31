package Homework003_Companies;

import java.util.List;

public class Company {
    private Integer id;
    private String name;
    private String address;
    private String phoneNumber;
    private long inn;
    private String founded;
    private List<Share> securities;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getInn() {
        return inn;
    }

    public void setInn(long inn) {
        this.inn = inn;
    }

    public String getFounded() {
        return founded;
    }

    public void setFounded(String founded) {
        this.founded = founded;
    }

    public List<Share> getSecurities() {
        return securities;
    }

    public void setSecurities(List<Share> securities) {
        this.securities = securities;
    }
}
