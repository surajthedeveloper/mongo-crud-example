package com.mongo.crud.services;

import com.mongo.crud.exceptions.ResourceNotFoundException;
import com.mongo.crud.models.Tutorial;
import com.mongo.crud.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
public class TutorialService {

    @Autowired
    private TutorialRepository tutorialRepository;

    public List<Tutorial> getAllTutorials(String title) {
        return ObjectUtils.isEmpty(title) ? tutorialRepository.findAll() : tutorialRepository.findByTitleContaining(title);
    }

    public Tutorial getTutorialById(String id) {
        return tutorialRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tutorial/" +id + " not found"));
    }

    public Tutorial createTutorial(Tutorial tutorial) {
        return tutorialRepository.save(new Tutorial(tutorial.getId(),tutorial.getTitle(),tutorial.getDescription(),tutorial.isPublished()));
    }

    public Tutorial updateTutorial(String id, Tutorial tutorial) {
        Optional<Tutorial> tutorialByIdoption = tutorialRepository.findById(id);
        if (tutorialByIdoption.isPresent()){
            Tutorial tutorialById = tutorialByIdoption.get();
            tutorialById.setDescription(tutorial.getDescription());
            tutorialById.setTitle(tutorial.getTitle());
            tutorialById.setPublished(tutorial.isPublished());
            return tutorialRepository.save(tutorial);
        }
        throw new ResourceNotFoundException("Tutorial/" +id + " not found") ;
    }

    public void deleteTutorial(String id) {
        tutorialRepository.deleteById(id);
    }

    public void deleteAllTutorials() {
        tutorialRepository.deleteAll();
    }

    public List<Tutorial> findByPublished() {
        return tutorialRepository.findByPublished(true);
    }
}
