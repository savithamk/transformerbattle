package org.java.transformerbattle.model;


import java.io.Serializable;
import java.util.List;

public class TransformerResponse implements Serializable {

    private List<TransformerOutData> transformers;

    public List<TransformerOutData> getTransformers() {
        return transformers;
    }

    public void setTransformers(List<TransformerOutData> transformers) {
        this.transformers = transformers;
    }
}
