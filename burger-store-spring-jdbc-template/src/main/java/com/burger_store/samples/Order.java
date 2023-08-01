package com.burger_store.samples;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;

public class Order {
    private Integer id;
    private Date placedAt;
    @NotBlank(message = "city required")
    private String city;
    @NotBlank(message = "street required")
    private String street;
    @NotBlank(message = "apartment required")
    private String apartment;
    @Digits(integer = 16, fraction = 0, message = "you need to enter only numbers")
    @Size(min = 16, max = 16, message = "you need to enter exactly 16 numbers")
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public List<Burger> setOrderComponents(List<Burger> orderComponents) {
        this.orderComponents = orderComponents;
        return orderComponents;
    }

    public void addBurger(Burger saved){
        this.orderComponents.add(saved);
    }

}
