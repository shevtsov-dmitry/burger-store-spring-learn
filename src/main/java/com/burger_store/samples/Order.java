package com.burger_store.samples;

import java.util.Date;
import java.util.List;

public class Order {
    private Long id;
    private Date placedAt;
    private String city;
    private String street;
    private String apartment;
    private String creditCardNumber;
    private List<Burger> orderComponents;

    public Order(Date placedAt, String city, String street, String apartment, String creditCardNumber) {
        this.placedAt = placedAt;
        this.city = city;
        this.street = street;
        this.apartment = apartment;
        this.creditCardNumber = creditCardNumber;
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(Date placedAt) {
        this.placedAt = placedAt;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public List<Burger> getOrderComponents() {
        return orderComponents;
    }

    public void setOrderComponents(List<Burger> orderComponents) {
        this.orderComponents = orderComponents;
    }

    public void addBurger(Burger saved){
        this.orderComponents.add(saved);
    }

}
