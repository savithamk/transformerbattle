package org.java.transformerbattle.service;

import org.java.transformerbattle.dao.TransformerRepository;
import org.java.transformerbattle.entity.Transformer;
import org.java.transformerbattle.model.BattleInData;
import org.java.transformerbattle.model.BattleResponse;
import org.java.transformerbattle.service.impl.BattleServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class BattleServiceImplTest {

    @Mock
    private TransformerRepository dataRepo;

    @InjectMocks
    private BattleServiceImpl battleService;

    private  Transformer autobot,decepticon;
    private  BattleInData battleInData;


    @Before
    public void dataSetUp(){
        autobot = new Transformer();
        autobot.setName("Bluestreak");
        autobot.setType("A");
        autobot.setTransformerId(1);
        autobot.setStrength(6);
        autobot.setIntelligence(6);
        autobot.setSpeed(7);
        autobot.setEndurance(9);
        autobot.setRank(5);
        autobot.setCourage(2);
        autobot.setFirePower(9);
        autobot.setSkill(7);
        decepticon = new Transformer();
        decepticon.setName("Soundwave");
        decepticon.setType("D");
        decepticon.setTransformerId(2);
        decepticon.setStrength(8);
        decepticon.setIntelligence(9);
        decepticon.setSpeed(2);
        decepticon.setEndurance(6);
        decepticon.setRank(7);
        decepticon.setCourage(5);
        decepticon.setFirePower(6);
        decepticon.setSkill(10);
        battleInData = new BattleInData();
        battleInData.setTransformerIds(Arrays.asList(1,2));
    }

    @Test
    public void battle_happyPath() throws Exception{
        when(dataRepo.fetchTransformersById(battleInData.getTransformerIds())).thenReturn(Arrays.asList(autobot,decepticon));
        BattleResponse response = battleService.battle(battleInData);
        assertNotNull(response);
        assertEquals(1,response.getNoOfBattles());
        assertEquals("(Decepticons): Soundwave",response.getWinningTeam());
        assertEquals("(Autobots): No Survivors",response.getLosingTeamSurvivors());
    }

    @Test(expected = Exception.class)
    public void battle_dbCallReturnsException() throws Exception{
        when(dataRepo.fetchTransformersById(battleInData.getTransformerIds())).thenThrow(new Exception());
        BattleResponse response = battleService.battle(battleInData);
    }

    @Test
    public void battle_invalidIdsForInput() throws Exception{
        when(dataRepo.fetchTransformersById(battleInData.getTransformerIds())).thenReturn(new ArrayList<>());
        BattleResponse response = battleService.battle(battleInData);
        assertNotNull(response);
        assertEquals(0,response.getNoOfBattles());
        assertEquals("No transformers to battle",response.getWinningTeam());
        assertEquals("No Survivors",response.getLosingTeamSurvivors());
    }

    @Test
    public void battle_nameRuleWithOptimusPrime() throws Exception{
        autobot.setName("Optimus Prime");
        when(dataRepo.fetchTransformersById(battleInData.getTransformerIds())).thenReturn(Arrays.asList(autobot,decepticon));
        BattleResponse response = battleService.battle(battleInData);
        assertNotNull(response);
        assertEquals(1,response.getNoOfBattles());
        assertEquals("(Autobots): Optimus Prime",response.getWinningTeam());
        assertEquals("(Decepticons): No Survivors",response.getLosingTeamSurvivors());
    }

    @Test
    public void battle_nameRuleWithPredaking() throws Exception{
        decepticon.setName("Predaking");
        when(dataRepo.fetchTransformersById(battleInData.getTransformerIds())).thenReturn(Arrays.asList(autobot,decepticon));
        BattleResponse response = battleService.battle(battleInData);
        assertNotNull(response);
        assertEquals(1,response.getNoOfBattles());
        assertEquals("(Decepticons): Predaking",response.getWinningTeam());
        assertEquals("(Autobots): No Survivors",response.getLosingTeamSurvivors());
    }

    @Test
    public void battle_nameRuleWithBothPrimeAndPedaking() throws Exception{
        autobot.setName("Optimus Prime");
        decepticon.setName("Predaking");
        when(dataRepo.fetchTransformersById(battleInData.getTransformerIds())).thenReturn(Arrays.asList(autobot,decepticon));
        BattleResponse response = battleService.battle(battleInData);
        assertNotNull(response);
        assertEquals(1,response.getNoOfBattles());
        assertEquals("Optimus Prime and Predaking were pitted against each other",response.getWinningTeam());
        assertEquals("No Survivors",response.getLosingTeamSurvivors());
    }

    @Test
    public void battle_courageRuleAutobotWin() throws Exception{
        autobot.setCourage(9);decepticon.setCourage(0);
        autobot.setStrength(9);decepticon.setStrength(0);
        when(dataRepo.fetchTransformersById(battleInData.getTransformerIds())).thenReturn(Arrays.asList(autobot,decepticon));
        BattleResponse response = battleService.battle(battleInData);
        assertNotNull(response);
        assertEquals(1,response.getNoOfBattles());
        assertEquals("(Autobots): Bluestreak",response.getWinningTeam());
        assertEquals("(Decepticons): No Survivors",response.getLosingTeamSurvivors());
    }

    @Test
    public void battle_courageRuleDecepticonWin() throws Exception{
        autobot.setCourage(0);decepticon.setCourage(9);
        autobot.setStrength(0);decepticon.setStrength(9);
        when(dataRepo.fetchTransformersById(battleInData.getTransformerIds())).thenReturn(Arrays.asList(autobot,decepticon));
        BattleResponse response = battleService.battle(battleInData);
        assertNotNull(response);
        assertEquals(1,response.getNoOfBattles());
        assertEquals("(Decepticons): Soundwave",response.getWinningTeam());
        assertEquals("(Autobots): No Survivors",response.getLosingTeamSurvivors());
    }

    @Test
    public void battle_skillRuleDecepticonWin() throws Exception{
        autobot.setSkill(0);decepticon.setSkill(9);
        when(dataRepo.fetchTransformersById(battleInData.getTransformerIds())).thenReturn(Arrays.asList(autobot,decepticon));
        BattleResponse response = battleService.battle(battleInData);
        assertNotNull(response);
        assertEquals(1,response.getNoOfBattles());
        assertEquals("(Decepticons): Soundwave",response.getWinningTeam());
        assertEquals("(Autobots): No Survivors",response.getLosingTeamSurvivors());
    }
    @Test
    public void battle_skillRuleAutobotWin() throws Exception{
        autobot.setSkill(9);decepticon.setSkill(0);
        when(dataRepo.fetchTransformersById(battleInData.getTransformerIds())).thenReturn(Arrays.asList(autobot,decepticon));
        BattleResponse response = battleService.battle(battleInData);
        assertNotNull(response);
        assertEquals(1,response.getNoOfBattles());
        assertEquals("(Autobots): Bluestreak",response.getWinningTeam());
        assertEquals("(Decepticons): No Survivors",response.getLosingTeamSurvivors());
    }



}
