package models;

import java.util.List;
/**
 * Faculty of Engineering, Alexandria University
 * Computer and Systems Engineering Department
 * CS 241 : Automatic Linear Control Systems
 * Programming Project I : Signal Flow models.Graph
 * Created by Marc Magdi on 4/23/17.
 */
public interface DeltaValue {
    /**.
     * @return the value of the delta
     */
    double getDeltaValue();

    /**.
     * @return list of loops combinations, as follows first list contains 2 untouched loops,
     * second list contains three untouched loops, etc...
     */
    List<List<List<Loop>>> getLoopsCombination();

    /**.
     * @param value set the delta value with the given one.
     */
    void setDeltaValue(Double value);

    /**.
     * @param loopsCombinations set the loops combination with the given value
     */
    void setLoopsCombinations(List<List<List<Loop>>> loopsCombinations);
}
