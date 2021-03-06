package org.java.transformerbattle.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.java.transformerbattle.model.BattleInData;
import org.java.transformerbattle.model.BattleResponse;
import org.java.transformerbattle.model.StatusResponse;
import org.java.transformerbattle.service.BattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "Battles",description = "Battle operations")
public class BattleController {

    @Autowired
    private BattleService battleService;

    @PostMapping("/battle")
    @ApiOperation(value = "Simulate Battle")
    public ResponseEntity simulateBattle(@RequestBody BattleInData battleInput){
        try{
            BattleResponse battleResponse = new BattleResponse();
            battleResponse = battleService.battle(battleInput);
            return ResponseEntity.ok(battleResponse);
        }catch (Exception e){
            StatusResponse response = new StatusResponse();
            response.setMessage("Exception occurred while executing the battle");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
