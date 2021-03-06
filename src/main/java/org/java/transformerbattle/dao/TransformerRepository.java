package org.java.transformerbattle.dao;

import org.java.transformerbattle.entity.Transformer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransformerRepository extends JpaRepository<Transformer,Integer> {

    @Query("select t from Transformer t where t.transformerId in : inputList")
    List<Transformer> fetchTransformersById(@Param("inputList") List<Integer> inputList);
}
