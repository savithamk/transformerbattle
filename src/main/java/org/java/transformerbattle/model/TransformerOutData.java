package org.java.transformerbattle.model;


import java.io.Serializable;

public class TransformerOutData extends TransformerInData implements Serializable {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
