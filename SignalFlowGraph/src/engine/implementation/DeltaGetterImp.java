package engine.implementation;

import engine.DeltaGetter;
import models.DeltaValue;
import models.ForwardPath;
import models.Loop;
import models.implementation.DeltaValueImp;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Faculty of Engineering, Alexandria University
 * Computer and Systems Engineering Department
 * CS 241 : Automatic Linear Control Systems
 * Programming Project I : Signal Flow models.Graph
 * Created by Marc Magdi on 4/21/17.
 */
public class DeltaGetterImp implements DeltaGetter {
    /**.
     * The loops to get the delta of.
     */
    private List<Loop> loopList;

    /**.
     * A set containing the current taken loops in the combination.
     */
    private Set<Loop> taken;

    /**.
     * list of loops combinations, as follows first list contains 2 untouched loops,
     * second list contains three untouched loops, etc...
     */
    private List<List<List<Loop>>> loopsCombinations;

    /**.
     * @param loopList the loop list to extract all delta from
     */
    public DeltaGetterImp(List<Loop> loopList) {
        this.loopList = loopList;
        this.taken = new HashSet<>();
        this.loopsCombinations = new LinkedList<>();
    }

    @Override
    public DeltaValue getDeltaValue() {
        loopsCombinations.clear();
        Validator validator = new ValidatorWithoutForward();
        List<Double> gainsCombinations = getGainsCombinations(validator);
        double value = getFinalValue(gainsCombinations);
        DeltaValue deltaValue = new DeltaValueImp();
        deltaValue.setDeltaValue(value);
        deltaValue.setLoopsCombinations(loopsCombinations);
        return deltaValue;
    }

    @Override
    public double getDeltaValue(ForwardPath forwardPath) {
        Validator validator = new ValidatorWithForward(forwardPath);
        List<Double> gainsCombinations = getGainsCombinations(validator);
        return getFinalValue(gainsCombinations);
    }

    /**.
     * @return a list of values for all available combinations
     * size of the loops, starting the size at 1 up to n.
     */
    private List<Double> getGainsCombinations(Validator validator) {
        List<Double> gains = new LinkedList<>();
        for (int i = 1; i <= loopList.size(); i++) {
            gains.add(comb(0, 0, i, validator));
        }

        return gains;
    }

    /**.
     * Combination algorithm to compute the combinations of valid untouched loops
     * @param depth the current depth of the recursion tree
     * @param lastIndex the lastIndex stopped at
     * @param size the size to choose nCx, where size = x
     * @param validator check if a loop is valid to be added.
     * @return the sum of products of the gain of the untouched loops.
     */
    private double comb(int depth, int lastIndex, int size, Validator validator) {
        if (depth == size) {
            double gain = 1;
            List<Loop> combinations = new LinkedList<>();
            for (Loop loop : taken) {
                combinations.add(loop);
                gain *= loop.getGain();
            }

            if (loopsCombinations.size() <= depth - 1) {
                loopsCombinations.add(new LinkedList<>());
            }
            loopsCombinations.get(depth - 1).add(combinations);
            return gain;
        }

        double gain = 0;

        for (int i = lastIndex; i < loopList.size(); i++) {
            Loop currentLoop = loopList.get(i);
            if (taken.contains(currentLoop)) continue;
            if (validator.isValid(currentLoop)) {
                taken.add(currentLoop);
                gain += comb(depth + 1, i, size, validator);
                taken.remove(currentLoop);
            }
        }

        return gain;
    }

    /**.
     * @param gainsCombinations list of all available gains of the combinations
     * @return the value of the total gain
     */
    private double getFinalValue(List<Double> gainsCombinations) {
        int sign = -1;
        double value = 0;
        for (double currentValue : gainsCombinations) {
            value += (currentValue * sign);
            sign *= -1;
        }

        return value + 1;
    }

    /**.
     * A validator to check if a node can be added to a gain combination.
     */
    private interface Validator {
        /**.
         * Check for the given loop is valid to be added to the gain combination.
         * @param loop the loop to check.
         * @return true iff the given loop is valid to be added to the gain combination.
         */
        boolean isValid(Loop loop);
    }

    private class ValidatorWithForward implements Validator {

        private ForwardPath forwardPath;

        private ValidatorWithForward(ForwardPath forwardPath) {
            this.forwardPath = forwardPath;
        }

        @Override
        public boolean isValid(Loop loop) {
            for (Loop currentLoop : taken) {
                if (currentLoop.intersects(loop))
                    return false;
            }

            return !forwardPath.intersects(loop);
        }
    }

    private class ValidatorWithoutForward implements Validator {

        @Override
        public boolean isValid(Loop loop) {
            for (Loop currentLoop : taken) {
                if (currentLoop.intersects(loop)) return false;
            }

            return true;
        }
    }
}
