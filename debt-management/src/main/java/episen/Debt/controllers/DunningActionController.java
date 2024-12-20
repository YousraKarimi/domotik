package episen.Debt.controllers;

import back.models.DebtModel.DunningAction;
import episen.Debt.services.DunningActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("Debt/dunningAction")
public class DunningActionController {

    @Autowired
    private DunningActionService dunningActionService;

    @GetMapping("/allActions")
    public ResponseEntity<List<DunningAction>> findAllActions(){
        return new ResponseEntity<>(dunningActionService.findAllActions(), HttpStatus.OK);
    }

}
