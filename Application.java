
 /*
 * Copyright (c) Olehs company, Inc.
 * This software is provided by one person!
 */

import ui.Menu;

 /**
  * It`s main class of program. It used for starting console application.
  * @version 1.0 14 May 2019
  * @author Oleh Zarichnyi
  */


public class Application {
    public static void main(String args[]) {
        Menu start = new Menu();
        start.startConnection();
        start.startMenu();


    }
}
