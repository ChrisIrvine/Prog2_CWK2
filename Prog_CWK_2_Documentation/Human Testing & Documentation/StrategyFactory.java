/*
 * Author:      ruw12gbu, 100036248
 *
 * Description: The purpose of this class is to determine what Startegy a player
 *              should use. It will ask for user input as to what the desired
 *              strategy is and return the correct strategy; minimising user 
 *              error.
 *
 * Version:     1.0 - Created
 */
package question2;

import java.util.Scanner;

/**
 * @author ruw12gbu, 100036248
 */
public class StrategyFactory 
{
    public Strategy chooseStrategy(String humCmp)
    {
        Scanner scan = new Scanner(System.in);      
        
        if ("H".equals(humCmp) == true)
        {
            return new HumanStrategy();
        }
        else if("C".equals(humCmp) == true)
        {
            System.out.println("You have selected to make a Computer controlled"
            + " Player then please select either; Basic (b), Thinker (t) or "
            + "My (m).\nPlease be advised that only Basic is currently "
                    + "implemented...");
            if("b".equals(scan.next()) == true)
                return new BasicStrategy();
//            else if ("t".equals(scan.next()) == true)
//                return new ThinkerStrategy();
//            else if ("m".equals(scan.next()) == true)
//                return new MyStrategy();
            else
                System.out.println("Please select either b, t or m...");
        }
        else
        {
            System.out.println("Invalid value detected. "
                    + "Setting default value.");
            return new BasicStrategy();
        }
        return new BasicStrategy();
    }
}
