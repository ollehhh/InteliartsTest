package domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Purchase {
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
