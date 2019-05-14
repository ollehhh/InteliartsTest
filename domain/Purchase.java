 /*
  * Copyright (c) Olehs company, Inc.
  * This software is provided by one person!
  */
package domain;

import java.time.LocalDate;
import java.util.Objects;
 /**
  * It`s class used to create objects based on a database table.
  * @version 1.0 14 May 2019
  * @author Oleh Zarichnyi
  */
public class Purchase {
    /*This class implements database table params.
    Every variable duplicate the same column in database table.
    All methods used for taking access to variables, or implements other important things ;)
     */
    private LocalDate localTime;
    private String item;
    private String currency;
    private Double price;

    public LocalDate getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalDate localTime) {
        this.localTime = localTime;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase)) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equals(localTime, purchase.localTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(localTime);
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "localTime=" + localTime +
                ", item='" + item + '\'' +
                ", currency='" + currency + '\'' +
                ", price=" + price +
                '}';
    }
}
