package org.java.transformerbattle.service;

import org.java.transformerbattle.model.TransformerInData;
import org.java.transformerbattle.model.TransformerOutData;

import java.util.List;

public interface DataService {
    void create(TransformerInData request) throws Exception;

    List<TransformerOutData> listAllTransformers() throws Exception;

    void deleteById(int id) throws Exception;

    void updateTransformer(int id, TransformerInData transformer) throws Exception;
}
