package engine;

import models.DeltaValue;
import models.ForwardPath;

/**
 * Faculty of Engineering, Alexandria University
 * Computer and Systems Engineering Department
 * CS 241 : Automatic Linear Control Systems
 * Programming Project I : Signal Flow models.Graph
 * Created by Marc Magdi on 4/21/17.
 */
public interface DeltaGetter {
    /**.
     * @return the value of the delta as
     * 1 - (sum of gain of individual loops)
     * + (sum of gains of all possible combinations of two non touching loops)
     * - (sum of gains of all possible combinations of three non touching loops)
     */
    DeltaValue getDeltaValue();

    /**.
     * @param forwardPath the forward path to get loops non touching it.
     * @return the value of the delta that is not touching the given path as
     * 1 - (sum of gain of individual loops)
     * + (sum of gains of all possible combinations of two non touching loops)
     * - (sum of gains of all possible combinations of three non touching loops)
     * */
    double getDeltaValue(ForwardPath forwardPath);
}
