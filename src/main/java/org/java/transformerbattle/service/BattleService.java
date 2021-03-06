package org.java.transformerbattle.service;

import org.java.transformerbattle.model.BattleInData;
import org.java.transformerbattle.model.BattleResponse;

public interface BattleService {
    BattleResponse battle(BattleInData battleInput) throws Exception;
}
