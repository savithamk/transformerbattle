package org.java.transformerbattle.service.impl;

import org.java.transformerbattle.dao.TransformerRepository;
import org.java.transformerbattle.entity.Transformer;
import org.java.transformerbattle.model.BattleInData;
import org.java.transformerbattle.model.BattleResponse;
import org.java.transformerbattle.service.BattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Service("battleService")
public class BattleServiceImpl implements BattleService {

    @Autowired
    private TransformerRepository dataRepository;

    @Override
    public BattleResponse battle(BattleInData battleInput) throws Exception {
        BattleResponse response = new BattleResponse();
        List<Transformer> autobots = new ArrayList<>();
        List<Transformer> decepticons = new ArrayList<>();
        StringJoiner autobotWinners = new StringJoiner(",","","");
        StringJoiner decepticonWinners = new StringJoiner(",","","");
        if(null!=battleInput.getTransformerIds() && battleInput.getTransformerIds().size()>0){
            fetchTransformers(autobots,decepticons,battleInput.getTransformerIds());
            int autobotWins=0,decepticonWins=0,noOfBattles=0;

            if(autobots.size()>0 && decepticons.size() >0){
                if(autobots.size()>=decepticons.size()){
                    for(int i=0;i<decepticons.size();i++){
                        int nameRule=evaluateNameRule(autobots.get(i),decepticons.get(i));
                        if(nameRule==Integer.MAX_VALUE){
                            response.setNoOfBattles(1);
                            response.setWinningTeam("Optimus Prime and Predaking were pitted against each other");
                            response.setLosingTeamSurvivors("No Survivors");
                            return response;
                        }else{
                            if(nameRule > 0){
                                noOfBattles++;
                                autobotWins++;
                                autobotWinners.add(autobots.get(i).getName());
                            }else if(nameRule < 0){
                                noOfBattles++;
                                decepticonWins++;
                                decepticonWinners.add(decepticons.get(i).getName());
                            }else{
                                int courageAndStrengthRule = evaluateCourageAndStrength(autobots.get(i),decepticons.get(i));
                                if(courageAndStrengthRule>0){
                                    noOfBattles++;
                                    autobotWins++;
                                    autobotWinners.add(autobots.get(i).getName());
                                }else if(courageAndStrengthRule < 0){
                                    noOfBattles++;
                                    decepticonWins++;
                                    decepticonWinners.add(decepticons.get(i).getName());
                                }else{
                                    int skillRule = evaluateSkillRule(autobots.get(i),decepticons.get(i));
                                    if(skillRule>0){
                                        noOfBattles++;
                                        autobotWins++;
                                        autobotWinners.add(autobots.get(i).getName());
                                    }else if(skillRule<0){
                                        noOfBattles++;
                                        decepticonWins++;
                                        decepticonWinners.add(decepticons.get(i).getName());
                                    }else{
                                        int overallRatingRule = evaluateOverallRatingRule(autobots.get(i),decepticons.get(i));
                                        if(overallRatingRule>0){
                                            noOfBattles++;
                                            autobotWins++;
                                            autobotWinners.add(autobots.get(i).getName());
                                        }else if(overallRatingRule<0){
                                            noOfBattles++;
                                            decepticonWins++;
                                            decepticonWinners.add(decepticons.get(i).getName());
                                        }else{
                                            noOfBattles++;
                                        }
                                    }
                                }
                            }
                        }
                    } //end of forloop


                }else{
                    for(int i=0;i<autobots.size();i++){
                        int nameRule=evaluateNameRule(autobots.get(i),decepticons.get(i));
                        if(nameRule==Integer.MAX_VALUE){
                            response.setNoOfBattles(1);
                            response.setWinningTeam("Optimus Prime and Predaking were pitted against each other");
                            response.setLosingTeamSurvivors("No Survivors");
                            return response;
                        }else{
                            if(nameRule > 0){
                                noOfBattles++;
                                autobotWins++;
                                autobotWinners.add(autobots.get(i).getName());
                            }else if(nameRule < 0){
                                noOfBattles++;
                                decepticonWins++;
                                decepticonWinners.add(decepticons.get(i).getName());
                            }else{
                                int courageAndStrengthRule = evaluateCourageAndStrength(autobots.get(i),decepticons.get(i));
                                if(courageAndStrengthRule>0){
                                    noOfBattles++;
                                    autobotWins++;
                                    autobotWinners.add(autobots.get(i).getName());
                                }else if(courageAndStrengthRule < 0){
                                    noOfBattles++;
                                    decepticonWins++;
                                    decepticonWinners.add(decepticons.get(i).getName());
                                }else{
                                    int skillRule = evaluateSkillRule(autobots.get(i),decepticons.get(i));
                                    if(skillRule>0){
                                        noOfBattles++;
                                        autobotWins++;
                                        autobotWinners.add(autobots.get(i).getName());
                                    }else if(skillRule<0){
                                        noOfBattles++;
                                        decepticonWins++;
                                        decepticonWinners.add(decepticons.get(i).getName());
                                    }else{
                                        int overallRatingRule = evaluateOverallRatingRule(autobots.get(i),decepticons.get(i));
                                        if(overallRatingRule>0){
                                            noOfBattles++;
                                            autobotWins++;
                                            autobotWinners.add(autobots.get(i).getName());
                                        }else if(overallRatingRule<0){
                                            noOfBattles++;
                                            decepticonWins++;
                                            decepticonWinners.add(decepticons.get(i).getName());
                                        }else{
                                            noOfBattles++;
                                        }
                                    }
                                }
                            }
                        }
                    } //end of forloop

                }

                if(autobotWins>decepticonWins){
                    response.setNoOfBattles(noOfBattles);
                    response.setWinningTeam("(Autobots): "+autobotWinners.toString());
                    response.setLosingTeamSurvivors("(Decepticons): No Survivors");
                }else if(autobotWins<decepticonWins){
                    response.setNoOfBattles(noOfBattles);
                    response.setWinningTeam("(Decepticons): "+decepticonWinners.toString());
                    String losingTeamString =autobots.subList(decepticons.size(),autobots.size())
                            .stream().map(transformer -> transformer.getName())
                            .collect(Collectors.joining(","));
                    response.setLosingTeamSurvivors("(Autobots): "+ (losingTeamString.isEmpty() ? "No Survivors" : losingTeamString));
                }else{
                    response.setNoOfBattles(noOfBattles);
                    response.setWinningTeam("Equal number of wins for both teams");
                    response.setLosingTeamSurvivors("Equal number of wins for both teams");
                }

            }else{
                response.setNoOfBattles(0);
                response.setWinningTeam("No transformers to battle");
                response.setLosingTeamSurvivors("No Survivors");
            }
        }else{
            response.setNoOfBattles(0);
            response.setWinningTeam("No transformers to battle");
            response.setLosingTeamSurvivors("No Survivors");
        }
        return response;
    }

    private int evaluateOverallRatingRule(Transformer autobot, Transformer decepticon) {
        if(autobot.getOverallRating()>decepticon.getOverallRating())
            return 1;
        else if(decepticon.getOverallRating()>autobot.getOverallRating())
            return -1;
        return 0;
    }

    private int evaluateNameRule(Transformer autobot, Transformer decepticon) {
        if(autobot.getName().trim().equalsIgnoreCase("Optimus Prime")
                && decepticon.getName().trim().equalsIgnoreCase("Predaking"))
            return Integer.MAX_VALUE;
        else if(autobot.getName().trim().equalsIgnoreCase("Optimus Prime")
                && !decepticon.getName().trim().equalsIgnoreCase("Predaking"))
            return 1;
        else if(!autobot.getName().trim().equalsIgnoreCase("Optimus Prime")
                && decepticon.getName().trim().equalsIgnoreCase("Predaking"))
            return -1;
        return 0;
    }

    private int evaluateSkillRule(Transformer autobot,Transformer decepticon){
        if(autobot.getSkill()-decepticon.getSkill() >= 3)
            return 1;
        else if(decepticon.getSkill()-autobot.getSkill() >= 3)
            return -1;
        return 0;
    }

    private int evaluateCourageAndStrength(Transformer autobot, Transformer decepticon) {
        if((autobot.getCourage()-decepticon.getCourage()>=4)&&(autobot.getStrength()-decepticon.getStrength()>=3))
            return 1;
        else if((decepticon.getCourage()-autobot.getCourage()>=4)&&(decepticon.getStrength()-autobot.getStrength()>=3))
            return -1;
        return 0;
    }

    private void fetchTransformers(List<Transformer> autobots,List<Transformer> decepticons,List<Integer> inputList){
        List<Transformer> transformerList = dataRepository.fetchTransformersById(inputList);
        if(null!=transformerList && transformerList.size()>0){
            transformerList.forEach(transformer -> {
                if(transformer.getType().equalsIgnoreCase("A"))
                    autobots.add(transformer);
                else
                    decepticons.add(transformer);
            });
            Comparator<Transformer> comparator = new Comparator<Transformer>() {
                @Override
                public int compare(Transformer o1, Transformer o2) {
                    return o1.getRank()-o2.getRank();
                }
            };
            autobots.sort(comparator);
            decepticons.sort(comparator);
        }
    }




}
