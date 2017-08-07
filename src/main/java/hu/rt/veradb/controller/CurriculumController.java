package hu.rt.veradb.controller;

import hu.rt.veradb.data.Curriculum;
import hu.rt.veradb.service.CurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/curriculum")
public class CurriculumController {

    @Autowired
    private CurriculumService curriculumService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    List<Curriculum> getCurriculum() {
        List<Curriculum> curriculumList = curriculumService.getCurricula();
        return curriculumList;
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    void addCurricula(@RequestBody Curriculum curriculum) {
        curriculumService.addCurriculum(curriculum);
    }

    @RequestMapping(path = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    void updateCurricula(@RequestBody Curriculum curriculum) {
        curriculumService.updateCurriculum(curriculum);
    }

    @RequestMapping(path = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteCurricula(@RequestBody Curriculum curriculum) {
        curriculumService.deleteCurriculum(curriculum);
    }
}
