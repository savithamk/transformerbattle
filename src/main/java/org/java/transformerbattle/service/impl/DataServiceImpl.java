package org.java.transformerbattle.service.impl;

import org.java.transformerbattle.dao.TransformerRepository;
import org.java.transformerbattle.entity.Transformer;
import org.java.transformerbattle.model.TransformerInData;
import org.java.transformerbattle.model.TransformerOutData;
import org.java.transformerbattle.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service("dataService")
public class DataServiceImpl implements DataService {

    @Autowired
    private TransformerRepository dataRepository;

    @Override
    public void create(TransformerInData request) throws Exception{
        if(!(request.getType().equalsIgnoreCase("A") || request.getType().equalsIgnoreCase("D")))
            throw new Exception("Invalid transformer type");
        Transformer transformer = new Transformer();
        transformer.setName(request.getName());
        transformer.setType(request.getType());
        transformer.setStrength(request.getStrength());
        transformer.setIntelligence(request.getIntelligence());
        transformer.setSpeed(request.getSpeed());
        transformer.setEndurance(request.getEndurance());
        transformer.setRank(request.getRank());
        transformer.setCourage(request.getCourage());
        transformer.setFirePower(request.getFirePower());
        transformer.setSkill(request.getSkill());
        dataRepository.save(transformer);
    }

    @Override
    public List<TransformerOutData> listAllTransformers() throws Exception{
        return dataRepository.findAll().stream().filter(Objects::nonNull).map(transformer -> {
            TransformerOutData data = new TransformerOutData();
            data.setId(transformer.getTransformerId());
            data.setName(transformer.getName());
            data.setType(transformer.getType());
            data.setStrength(transformer.getStrength());
            data.setIntelligence(transformer.getIntelligence());
            data.setSpeed(transformer.getSpeed());
            data.setEndurance(transformer.getEndurance());
            data.setRank(transformer.getRank());
            data.setCourage(transformer.getCourage());
            data.setFirePower(transformer.getFirePower());
            data.setSkill(transformer.getSkill());
            return data;
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteById(int id) throws Exception {
        dataRepository.deleteById(id);
    }

    @Override
    public void updateTransformer(int id, TransformerInData transformer) throws Exception {
        if(!(transformer.getType().equalsIgnoreCase("A") || transformer.getType().equalsIgnoreCase("D")))
            throw new Exception("Invalid transformer type");

        Transformer existing = dataRepository.getOne(id);
        if(null!=existing){
            Transformer updateRequest = new Transformer();
            updateRequest.setTransformerId(id);
            updateRequest.setName(transformer.getName());
            updateRequest.setType(transformer.getType());
            updateRequest.setStrength(transformer.getStrength());
            updateRequest.setIntelligence(transformer.getIntelligence());
            updateRequest.setSpeed(transformer.getSpeed());
            updateRequest.setEndurance(transformer.getEndurance());
            updateRequest.setRank(transformer.getRank());
            updateRequest.setCourage(transformer.getCourage());
            updateRequest.setFirePower(transformer.getFirePower());
            updateRequest.setSkill(transformer.getSkill());
            dataRepository.save(updateRequest);
        }else{
            throw new Exception("Transformer not found with given identifier");
        }
    }
}
