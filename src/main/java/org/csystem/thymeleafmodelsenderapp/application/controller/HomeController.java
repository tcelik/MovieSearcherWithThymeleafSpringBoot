package org.csystem.thymeleafmodelsenderapp.application.controller;

import com.mashape.unirest.http.Unirest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("home")
    public String home()
    {
        return "index";
    }

    @GetMapping("showMyMovies")
    public String findMovieByTextAndShowImage(@RequestParam(name = "query") String query, Model model)
    {
        //Get the own controller
        HomeRestController hrc = new HomeRestController();

        //controller search the movie by keyword(jack), get path /1231231231.jpg\n/1231231231231.jpg
        String resultPosterPath = hrc.searchMoviet(query);

        //split with /n character get the str array
        String [] paths = resultPosterPath.split("\n");

        List<String> result = new ArrayList<>();
        //foreach path to orijinal url and add list.
        for (String path :paths) {
            result.add(String.format("https://image.tmdb.org/t/p/w500%s", path));
        }

        //Model list send the test.html by the framework. Liste gönderiyorum.
        model.addAttribute("postersPathFullQualiedURL", result);
        model.addAttribute("queryforyoutube", query);

        return "test.html";
    }
}
