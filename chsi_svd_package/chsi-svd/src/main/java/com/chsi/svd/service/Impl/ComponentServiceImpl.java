package com.chsi.svd.service.Impl;

import com.chsi.framework.service.BaseDbService;
import com.chsi.svd.DTO.PomPath;
import com.chsi.svd.dao.ComponentDao;
import com.chsi.svd.pojo.Component;
import com.chsi.svd.service.ComponentService;
import com.chsi.svd.utils.GetPoms;
import com.chsi.svd.utils.ParseXML;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ComponentServiceImpl extends BaseDbService implements ComponentService {

    private ComponentDao componentDao;
    @Override
    protected void doCreate() {
        componentDao = getDAO("componentDao", ComponentDao.class);

    }

    @Override
    protected void doRemove() {

    }

    /**
     * 传入component 获取cve编号
     * @param component
     */
    @Override
    public void findCVEData(Component component) {
        componentDao.finCVEData(component);
    }

    @Override
    public List<Component> parsePom(String dirPath, List<PomPath> pomPaths) throws IOException, ParserConfigurationException, SAXException {
        GetPoms.findPomFiles(dirPath, pomPaths);
        ParseXML parseXML = new ParseXML();
        List<Component> list = new ArrayList<>();

        for (PomPath pom : pomPaths){
            // System.out.println(pom);
            Document document = parseXML.getXML(pom.getPath());
            parseXML.getVersion(document);
        }

        for (PomPath pom : pomPaths){
            System.out.println(pom.getPath());
            Document document = parseXML.getXML(pom.getPath());
            parseXML.getComponent(document);
        }

        return list;
    }
}
