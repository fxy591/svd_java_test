package com.chsi.svd.webapp.controller;

import com.chsi.svd.DTO.PieData;
import com.chsi.svd.service.CveService;
import com.chsi.svd.webapp.utils.CheckLogin;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("cve")
public class CveInfoController {

    @Resource
    private CveService cveService;

    @RequestMapping("index")
    public String index(Model model, HttpSession session){

        if (!CheckLogin.check(session)){
            return "notLogin";
        }

        List<PieData> nistPieData = new ArrayList<PieData>();
        nistPieData.add(cveService.queryLevelNum("NIST","LOW"));
        nistPieData.add(cveService.queryLevelNum("NIST","MEDIUM"));
        nistPieData.add(cveService.queryLevelNum("NIST","HIGH"));
        nistPieData.add(cveService.queryLevelNum("NIST","CRITICAL"));
        nistPieData.add(cveService.queryNullNum("NIST"));

        model.addAttribute("nistPieData", nistPieData);

        List<PieData> cnaPieData = new ArrayList<PieData>();
        cnaPieData.add(cveService.queryLevelNum("CNA","LOW"));
        cnaPieData.add(cveService.queryLevelNum("CNA","MEDIUM"));
        cnaPieData.add(cveService.queryLevelNum("CNA","HIGH"));
        cnaPieData.add(cveService.queryLevelNum("CNA","CRITICAL"));
        cnaPieData.add(cveService.queryNullNum("CNA"));

        model.addAttribute("cnaPieData", cnaPieData);

        String yesterday = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        // val cveList = cveService.queryTodayCve("11/14/2022");
        // System.out.println(today);
        val cveList = cveService.queryTodayCve(yesterday);

        model.addAttribute("cves", cveList);

        return "cveInfo";
    }


    // @RequestMapping("indexNot")
    // public String index1(Model model){
    //     List<PieData> nistPieData = new ArrayList<PieData>();
    //     nistPieData.add(cveService.queryLevelNum("NIST","LOW"));
    //     nistPieData.add(cveService.queryLevelNum("NIST","MEDIUM"));
    //     nistPieData.add(cveService.queryLevelNum("NIST","HIGH"));
    //     nistPieData.add(cveService.queryLevelNum("NIST","CRITICAL"));
    //     nistPieData.add(cveService.queryNullNum("NIST"));
    //
    //     model.addAttribute("nistPieData", nistPieData);
    //
    //     List<PieData> cnaPieData = new ArrayList<PieData>();
    //     cnaPieData.add(cveService.queryLevelNum("CNA","LOW"));
    //     cnaPieData.add(cveService.queryLevelNum("CNA","MEDIUM"));
    //     cnaPieData.add(cveService.queryLevelNum("CNA","HIGH"));
    //     cnaPieData.add(cveService.queryLevelNum("CNA","CRITICAL"));
    //     cnaPieData.add(cveService.queryNullNum("CNA"));
    //
    //     model.addAttribute("cnaPieData", cnaPieData);
    //
    //     String yesterday = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    //     // val cveList = cveService.queryTodayCve("11/14/2022");
    //     // System.out.println(today);
    //     val cveList = cveService.queryTodayCve(yesterday);
    //
    //     model.addAttribute("cves", cveList);
    //
    //     return "cveInfo2";
    // }cveInfo2
}
