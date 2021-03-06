package org.java.transformerbattle.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BattleInData implements Serializable {

    private List<Integer> transformerIds = new ArrayList<>();

    public List<Integer> getTransformerIds() {
        return transformerIds;
    }

    public void setTransformerIds(List<Integer> transformerIds) {
        this.transformerIds = transformerIds;
    }
}
