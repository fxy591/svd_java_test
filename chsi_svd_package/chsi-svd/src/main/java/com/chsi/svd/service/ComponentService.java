package com.chsi.svd.service;

import com.chsi.svd.DTO.PomPath;
import com.chsi.svd.pojo.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface ComponentService {
    void findCVEData(Component component);

    List<Component> parsePom(String dirPath, List<PomPath> pomPaths) throws IOException, ParserConfigurationException, SAXException;
}
