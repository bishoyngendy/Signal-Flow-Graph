package models.implementation;

import models.DeltaValue;
import models.Loop;
import java.util.List;

/**
 * Faculty of Engineering, Alexandria University
 * Computer and Systems Engineering Department
 * CS 241 : Automatic Linear Control Systems
 * Programming Project I : Signal Flow models.Graph
 * Created by Marc Magdi on 4/23/17.
 */
public class DeltaValueImp implements DeltaValue{
    private double value;

    private List<List<List<Loop>>> loopsCombinations;
    @Override
    public double getDeltaValue() {
        return value;
    }

    @Override
    public List<List<List<Loop>>> getLoopsCombination() {
        return loopsCombinations;
    }

    @Override
    public void setDeltaValue(Double value) {
        this.value = value;
    }

    @Override
    public void setLoopsCombinations(List<List<List<Loop>>> loopsCombinations) {
        this.loopsCombinations = loopsCombinations;
    }
}
