package org.java.transformerbattle.service;


import org.java.transformerbattle.dao.TransformerRepository;
import org.java.transformerbattle.entity.Transformer;
import org.java.transformerbattle.model.TransformerInData;
import org.java.transformerbattle.model.TransformerOutData;
import org.java.transformerbattle.service.impl.DataServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class DataServiceImplTest {

    @Mock
    private TransformerRepository dataRepo;

    @InjectMocks
    private DataServiceImpl dataService;

    private TransformerInData transformerInData;
    private List<Transformer> transformers = new ArrayList<>();

    private void dataSetUp(String type){
        transformerInData = new TransformerInData();
        transformerInData.setCourage(1);
        transformerInData.setEndurance(0);
        transformerInData.setFirePower(0);
        transformerInData.setIntelligence(0);
        transformerInData.setName("ABC");
        transformerInData.setRank(1);
        transformerInData.setSpeed(0);
        transformerInData.setStrength(0);
        transformerInData.setType(type);
        Transformer transformer  = new Transformer();
        transformer.setTransformerId(1);
        transformer.setCourage(1);
        transformer.setEndurance(1);
        transformer.setFirePower(1);
        transformer.setIntelligence(1);
        transformer.setName("ABC");
        transformer.setRank(1);
        transformer.setSpeed(1);
        transformer.setStrength(1);
        transformer.setType(type);
        transformers.add(transformer);
    }

    @Test
    public void create_happyPath() throws Exception{
        dataSetUp("A");
        when(dataRepo.save(any(Transformer.class))).thenReturn(transformers.get(0));
        dataService.create(transformerInData);
    }

    @Test(expected = Exception.class)
    public void create_InvalidTypeThrowsException() throws Exception{
        dataSetUp("S");
        dataService.create(transformerInData);
    }

    @Test
    public void deleteById_happyPath() throws Exception{
        Mockito.doNothing().when(dataRepo).deleteById(1);
    }

    @Test(expected = Exception.class)
    public void deleteById_throwsException() throws Exception{
        Mockito.doThrow(new Exception()).when(dataRepo).deleteById(1);
    }

    @Test
    public void listAllTransformers_happyPath() throws Exception{
        dataSetUp("A");
        when(dataRepo.findAll()).thenReturn(transformers);
        List<TransformerOutData> response = dataService.listAllTransformers();
        Assert.assertNotNull(response);
    }

    @Test
    public void listAllTransformers_dbReturnsEmptyList() throws Exception{
        when(dataRepo.findAll()).thenReturn(transformers);
        List<TransformerOutData> response = dataService.listAllTransformers();
        Assert.assertEquals(0,response.size());
    }

    @Test(expected = Exception.class)
    public void listAllTransformers_dbReturnsNull() throws Exception{
        when(dataRepo.findAll()).thenReturn(null);
        List<TransformerOutData> response = dataService.listAllTransformers();
    }

    @Test(expected = Exception.class)
    public void listAllTransformers_dbThrowsException() throws Exception{
        when(dataRepo.findAll()).thenThrow(new Exception());
        List<TransformerOutData> response = dataService.listAllTransformers();
    }

    @Test
    public void updateTransformer_happyPath() throws Exception{
        dataSetUp("A");
        when(dataRepo.getOne(1)).thenReturn(transformers.get(0));
        when(dataRepo.save(any(Transformer.class))).thenReturn(transformers.get(0));
        dataService.updateTransformer(1,transformerInData);
    }

    @Test(expected = Exception.class)
    public void updateTransformer_invalidType() throws Exception{
        dataSetUp("S");
        dataService.updateTransformer(1,transformerInData);
    }

    @Test(expected = Exception.class)
    public void updateTransformer_transformerNotFound() throws Exception{
        dataSetUp("A");
        when(dataRepo.getOne(1)).thenReturn(null);
        dataService.updateTransformer(1,transformerInData);
    }

    @Test(expected = Exception.class)
    public void updateTransformer_getOneThrowsException() throws Exception{
        dataSetUp("A");
        when(dataRepo.getOne(1)).thenThrow(new Exception());
        dataService.updateTransformer(1,transformerInData);
    }

    @Test(expected = Exception.class)
    public void updateTransformer_saveThrowsException() throws Exception{
        dataSetUp("A");
        when(dataRepo.getOne(1)).thenReturn(transformers.get(0));
        when(dataRepo.save(any(Transformer.class))).thenThrow(new Exception());
        dataService.updateTransformer(1,transformerInData);
    }

}
