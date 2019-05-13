package connection;

import domain.Purchase;

public class Request {

    public String addPurchase(Purchase purchase) {
        String request = "INSERT INTO `PURCHASE` (`Currency`, `DTime`, `Price`, `Item`) VALUES ('"
                + purchase.getCurrency() + "', '" + purchase.getLocalTime().toString() + "', " + purchase.getPrice()
                + ", '" + purchase.getItem() + "')";

        return request;
    }
    public String deletePurchase(String date) {
        String request = "DELETE FROM `PURCHASE` WHERE `DTime` = '"+date+"'";

        return request;
    }


}
