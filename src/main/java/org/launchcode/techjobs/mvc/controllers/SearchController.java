package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        ArrayList<Job> jobs = new ArrayList<>();
        model.addAttribute("jobs", jobs);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
//    @RequestMapping(value = "/results", method = {RequestMethod.POST, RequestMethod.GET})
//    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
//
//        ArrayList<Job> jobs;
//        if (searchType.equals("all") || searchTerm.equals("all")){
//            jobs = JobData.findAll();
//            model.addAttribute("title", "All Jobs");
//            model.addAttribute("searchedFor", "Searched for all jobs");
//        } else {
//            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
////            if (jobs.size() == 0) {
////                model.addAttribute("noResults", "No results found :/");
////            }
//            if(searchType.equals("coreCompetency")) {
//                searchType = "Skill";
//            }
//            if (searchType.equals("positionType")) {
//                searchType = "Position Type";
//            }
//            model.addAttribute("searchedFor", "Searched for jobs with " + searchType + ": " + searchTerm);
//        }
//        model.addAttribute("jobs", jobs);
//        model.addAttribute("columns", columnChoices);
//        return "search";
//    }

    @PostMapping(value = "results")
    public String displaySearchResults(Model model, @RequestParam(defaultValue = "all") String searchType, @RequestParam String searchTerm) {
        model.addAttribute("enteredSearchType", searchType);
        model.addAttribute("enteredSearchTerm", searchTerm);
        ArrayList<Job> jobs;
        if (searchTerm.equals("all")){
            jobs = JobData.findAll();
            model.addAttribute("title", "All Jobs");
            model.addAttribute("searchedFor", "Searched for all jobs");
        } else if (searchType.equals("all")){
            jobs = JobData.findByValue(searchTerm);
            model.addAttribute("title", "All Jobs");
            model.addAttribute("searchedFor", "Searched for all jobs containing: " + searchTerm);
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
//            if (jobs.size() == 0) {
//                model.addAttribute("noResults", "No results found :/");
//            }
            if(searchType.equals("coreCompetency")) {
                searchType = "Skill";
            }
            if (searchType.equals("positionType")) {
                searchType = "Position Type";
            }
            model.addAttribute("searchedFor", "Searched for jobs with " + searchType + ": " + searchTerm);
        }
        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", columnChoices);
        return "search";
    }
}
