 /*
  * Copyright (c) Olehs company, Inc.
  * This software is provided by one person!
  */
package domain;
 /**
  * It`s class used to create objects based on parsing Json file, nad information placed there.
  * @version 1.0 14 May 2019
  * @author Oleh Zarichnyi
  */
public class Currency {
    /*
        This class has 2 variables, what we need to take from Json file, to make work with it more comfortable.
     */
    private String name;
    private Double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
