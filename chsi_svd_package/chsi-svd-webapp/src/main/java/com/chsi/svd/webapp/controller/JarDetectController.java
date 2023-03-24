package com.chsi.svd.webapp.controller;

import com.chsi.svd.pojo.Component;
import com.chsi.svd.pojo.Cve;
import com.chsi.svd.service.ComponentService;
import com.chsi.svd.service.CveService;
import com.chsi.svd.webapp.utils.CheckLogin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

// 表明为controller 也被成为handler
@Controller
// 地址栏访问路径
@RequestMapping("jarDetect")
public class JarDetectController {
    @Resource
    private ComponentService componentService;

    @Resource
    private CveService cveService;

    // 参数可以为HttpServletRequest request, HttpServletResponse response,  相当于原servlet的参数
    //  返回值为string 表示跳转路径
    // 地址栏访问路径
    // 与Servlet API 耦合 不推荐
    @RequestMapping("index")
    public String index(Model model, HttpSession session){
        // 1.接受client请求参数
        // 2.业务处理
        // 3.页面跳转    返回跳转路径
        if (!CheckLogin.check(session)){
            return "notLogin";
        }
        Component component1 = new Component();
        component1.setGroupId("log4j");
        component1.setArtifactId("log4j");
        component1.setVersion("1.2.17-atlassian-16");

        componentService.findCVEData(component1);
        String cve = component1.getCve();

        if (cve != null){
            model.addAttribute("status", "danger");
            cve = cve.substring(1, cve.length()-1);
            cve = cve.replaceAll("\\[|\\]|\\s", "");
            cve = cve.replaceAll("\'","");
            // 切分为个单cve
            String[] strArray = cve.split(",");
            List<String> cveList = Arrays.asList(strArray);

            // 查询每个cve
            for(String str : cveList){
                 Cve cve1 = cveService.queryMatchCve(str);
                // System.out.println(cve1);
                 if (cve1 != null && !cve1.getDesc().contains("** REJECT **")) {
                     // 将cve详细信息加入组件的cve list中
                     component1.getCveList().add(cve1);

                 }
            }
            if (component1.getCveList() != null){
                System.out.println(component1.getCveList());

                model.addAttribute("message", "漏洞详细信息获取成功");
            } else {
                model.addAttribute("message", "漏洞详细信息获取失败");
            }
        }
        else {
            model.addAttribute("status", "safe");
        }

        model.addAttribute("component", component1);

        // 逻辑上不变的内容 使用view resolver
        return "jarDetect";
    }

    @RequestMapping("jarDetect")
    @ResponseBody
    public Component jarDetect(@RequestBody Component component){
        // 1.接受client请求参数
        // 2.业务处理
        // 3.页面跳转    返回跳转路径
        System.out.println(component);

        componentService.findCVEData(component);
        String cve = component.getCve();

        if (cve != null){
            // model.addAttribute("status", "danger");
            cve = cve.substring(1, cve.length()-1);
            cve = cve.replaceAll("\\[|\\]|\\s", "");
            cve = cve.replaceAll("\'","");
            // 切分为个单cve
            String[] strArray = cve.split(",");
            List<String> cveList = Arrays.asList(strArray);

            // 查询每个cve
            for(String str : cveList){
                Cve cve1 = cveService.queryMatchCve(str);
                if (cve1 != null && !cve1.getDesc().contains("** REJECT **")) {
                    // 将cve详细信息加入组件的cve list中
                    component.getCveList().add(cve1);
                }
            }
        }
        return component;
    }

    // @RequestMapping("index")
    // @ResponseBody
    // public ModelAndView jarDetect(@RequestBody Component component, ModelAndView modelAndView){
    //     // 1.接受client请求参数
    //     // 2.业务处理
    //     // 3.页面跳转    返回跳转路径
    //     System.out.println(component);
    //
    //     componentService.findCVEData(component);
    //     String cve = component.getCve();
    //
    //     if (cve != null){
    //         // model.addAttribute("status", "danger");
    //         cve = cve.substring(1, cve.length()-1);
    //         cve = cve.replaceAll("\\[|\\]|\\s", "");
    //         cve = cve.replaceAll("\'","");
    //         // 切分为个单cve
    //         String[] strArray = cve.split(",");
    //         List<String> cveList = Arrays.asList(strArray);
    //
    //         // 查询每个cve
    //         for(String str : cveList){
    //             Cve cve1 = cveService.queryMatchCve(str);
    //             if (cve1 != null && !cve1.getDesc().contains("** REJECT **")) {
    //                 // 将cve详细信息加入组件的cve list中
    //                 component.getCveList().add(cve1);
    //             }
    //         }
    //     }
    //     modelAndView.addObject("component", component);
    //     modelAndView.setViewName("jarDetect");
    //     return modelAndView;
    // }
}
