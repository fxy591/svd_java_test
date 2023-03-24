package com.chsi.svd.webapp.controller;

import com.chsi.svd.pojo.Cwe;
import com.chsi.svd.pojo.PageBean;
import com.chsi.svd.service.CweService;
import com.chsi.svd.webapp.utils.CheckLogin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("cwe")
public class CweController {

    @Resource
    private CweService cweService;

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public String queryAllCwe(Model model){
        List<Cwe> cwes = cweService.queryAllCwe();
        model.addAttribute("cwes", cwes);
        return "cveType";
    }

    @RequestMapping("index")
    public String index(HttpSession session){
        if (!CheckLogin.check(session)){
            return "notLogin";
        }
        return "cveTypePage";
    }

    @RequestMapping(value = "query")
    @ResponseBody
    public PageBean<Cwe> queryCweByPage(Integer currentPage, Integer pageSize, Model model){
        System.out.println(currentPage);
        System.out.println(pageSize);
        PageBean<Cwe> pageBean = cweService.selectPyPage(currentPage, pageSize);
        return pageBean;
    }

}
