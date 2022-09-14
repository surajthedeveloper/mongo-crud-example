package com.mongo.crud.controllers;

import com.mongo.crud.models.Tutorial;
import com.mongo.crud.services.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tutorials")
public class TutorialController {

    @Autowired
    private TutorialService tutorialService;

    @GetMapping("")
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(value = "title", required = false) String title) {
        final List<Tutorial> allTutorials = tutorialService.getAllTutorials(title);
        if (allTutorials.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(allTutorials, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") String id) {
        return new ResponseEntity<>(tutorialService.getTutorialById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        return new ResponseEntity<>(tutorialService.createTutorial(tutorial), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") String id, @RequestBody Tutorial tutorial) {
        return new ResponseEntity<>(tutorialService.updateTutorial(id, tutorial), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTutorial(@PathVariable("id") String id) {
        tutorialService.deleteTutorial(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("")
    public ResponseEntity<Object> deleteAllTutorials() {
        tutorialService.deleteAllTutorials();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/published")
    public ResponseEntity<List<Tutorial>> findByPublished() {
        List<Tutorial> byPublished = tutorialService.findByPublished();
        if (byPublished.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(byPublished, HttpStatus.OK);
        }
    }
}
