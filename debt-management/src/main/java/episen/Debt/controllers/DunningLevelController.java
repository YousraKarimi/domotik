package episen.Debt.controllers;


import back.models.DebtModel.DunningLevel;
import episen.Debt.services.DunningLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("Debt/dunningLevel")
public class DunningLevelController {

    @Autowired
    private DunningLevelService dunningLevelService;

    @GetMapping("/allLevels")
    public ResponseEntity<List<DunningLevel>> findAllLevels(){
        return new ResponseEntity<>(dunningLevelService.findAllTLevels(), HttpStatus.OK);
    }
    @GetMapping("/getLevelName/{id}")
    public ResponseEntity<String> getLevelNameById(@PathVariable Long id) {
        String levelName = dunningLevelService.getLevelNameById(id);
        return new ResponseEntity<>(levelName, HttpStatus.OK);
    }
}
