package hu.rt.veradb.controller;

import hu.rt.veradb.data.Curricula;
import hu.rt.veradb.service.CurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/curriculum")
public class CurriculumController {

    @Autowired
    private CurriculumService curriculumService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    List<Curricula> getCurriculum() {
        List<Curricula> curriculaList = curriculumService.getCurriculum();
        return curriculaList;
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    void addCurricula(@RequestBody Curricula curricula) {
        curriculumService.addCurricula(curricula);
    }

    @RequestMapping(path = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    void updateCurricula(@RequestBody Curricula curricula) {
        curriculumService.updateCurricula(curricula);
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteCurricula(@RequestBody Curricula curricula) {
        curriculumService.deleteCurricula(curricula);
    }
}
