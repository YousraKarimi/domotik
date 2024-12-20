package episen.Debt.controllers;


import back.models.DebtModel.ActionChannel;
import back.models.DebtModel.DunningTemplate;
import back.models.Exceptions.ResourceNotFoundException;
import episen.Debt.services.DunningTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("Debt/dunningTemplate")
public class DunningTemplateController {

    @Autowired
    private DunningTemplateService dunningTemplateService;

    @GetMapping("/allTemplates")
    public ResponseEntity<List<DunningTemplate>> findAllTemplates(){
        return new ResponseEntity<>(dunningTemplateService.findAllTemplates(), HttpStatus.OK);
    }
    @GetMapping("/getTemplateName/{id}")
    public ResponseEntity<String> getTemplateNameById(@PathVariable Long id) {
        String TemplateName = dunningTemplateService.getTemplateNameById(id);
        return new ResponseEntity<>(TemplateName, HttpStatus.OK);
    }

    @GetMapping("/getTemplate/{id}")
    public ResponseEntity<DunningTemplate> getTemplateById(@PathVariable Long id) {
        Optional<DunningTemplate> template = dunningTemplateService.getTemplateById(id);
        if (template.isPresent()) {
            return new ResponseEntity<>(template.get(), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Template not found with id " + id);
        }
    }
    @PutMapping("/updateTemplate")
    public ResponseEntity<DunningTemplate> updateTemplate(@RequestBody DunningTemplate dunningTemplate) {
        DunningTemplate updatedTemplate = dunningTemplateService.updateDunningTemplate(dunningTemplate);
        return new ResponseEntity<>(updatedTemplate, HttpStatus.OK);
    }

    @GetMapping("/getlanguageBytemplateId/{templateId}")
    public ResponseEntity<String> getlanguageBytemplateId(@PathVariable Long templateId) {
        String language = dunningTemplateService.getlanguageByTemplateId(templateId);
        return new ResponseEntity<>(language, HttpStatus.OK);
    }
    @GetMapping("/gettemplateNameBytemplateId/{templateId}")
    public ResponseEntity<String> gettemplateNameBytemplateId(@PathVariable Long templateId) {
        String templateName = dunningTemplateService.gettemplateNameByTemplateId(templateId);
        return new ResponseEntity<>(templateName, HttpStatus.OK);
    }
    @GetMapping("/getTextBytemplateId/{templateId}")
    public ResponseEntity<String> getTextBytemplateId(@PathVariable Long templateId) {
        String Text = dunningTemplateService.getTextByTemplateId(templateId);
        return new ResponseEntity<>(Text, HttpStatus.OK);
    }
    @GetMapping("/getchannelBytemplateId/{templateId}")
    public ResponseEntity<ActionChannel> getchannelBytemplateId(@PathVariable Long templateId) {
        ActionChannel channel = dunningTemplateService.getchannelByTemplateId(templateId);
        return new ResponseEntity<>(channel, HttpStatus.OK);
    }

    @PutMapping("/updateText/{id}")
    public ResponseEntity<Void> updateTemplateText(@PathVariable Long id, @RequestBody Map<String, String> requestBody) {
        try {
            String text = requestBody.get("text");
            dunningTemplateService.updateTemplateText(id, text);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getActionNameByTemplateId/{templateId}")
    public ResponseEntity<String> getActionNameByTemplateId(@PathVariable Long templateId) {
        String actionName = dunningTemplateService.getActionNameByTemplateId(templateId);
        return new ResponseEntity<>(actionName, HttpStatus.OK);
    }

    @GetMapping("/getLevelNameByTemplateId/{templateId}")
    public ResponseEntity<String> getLevelNameByTemplateId(@PathVariable Long templateId) {
        String levelName = dunningTemplateService.getLevelNameByTemplateId(templateId);
        return new ResponseEntity<>(levelName, HttpStatus.OK);
    }

}
