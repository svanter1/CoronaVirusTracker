package com.shashi.covidtracker.Controller;

import com.shashi.covidtracker.Models.LocationStaff;
import com.shashi.covidtracker.services.CoronaVirusData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusData coronaVirusData;

    @GetMapping("/")
    public String home(Model model) {
         List<LocationStaff> allStats = coronaVirusData.getAllStats();
         int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
         int totalNewCases  = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "home";
    }
}
