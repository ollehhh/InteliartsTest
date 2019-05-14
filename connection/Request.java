 /*
  * Copyright (c) Olehs company, Inc.
  * This software is provided by one person!
  */
package connection;

import domain.Purchase;
 /**
  * It`s class with realization of some database requests.
  * @version 1.0 14 May 2019
  * @author Oleh Zarichnyi
  */
public class Request {

    /*
    This class implements generator of database Requests.
     */

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
