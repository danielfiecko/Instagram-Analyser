package com.dfiecko.instagramAnalyser.api;

import com.dfiecko.instagramAnalyser.dto.AnalysedInstagramProfile;
import com.dfiecko.instagramAnalyser.dto.UserAlgorithmSettings;
import com.dfiecko.instagramAnalyser.service.AnalysesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MainApplicationController {

    @Autowired
    private AnalysesService analysesService;


    @ModelAttribute("userAlgorithmSettings")
    public UserAlgorithmSettings setUpLinkForm() {
        return new UserAlgorithmSettings();
    }


    @GetMapping("/")
    public String homePage() {
        return "homepage";
    }

    @PostMapping("/analyseProfile")
    public String analyseProfile(@ModelAttribute("userAlgorithmSettings") UserAlgorithmSettings userAlgorithmSettings, Model model) {
        AnalysedInstagramProfile analysedInstagramProfile = analysesService.getAnalyses(userAlgorithmSettings);
        model.addAttribute("analysedInstagramProfile", analysedInstagramProfile);
        return "results";
    }
}