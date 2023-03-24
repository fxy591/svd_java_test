package com.chsi;

import com.chsi.svd.DTO.PomPath;
import com.chsi.svd.pojo.Component;
import com.chsi.svd.utils.GetPoms;
import com.chsi.svd.utils.ParseXML;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, ParserConfigurationException, SAXException {
        List<PomPath> poms = new ArrayList<>();
        GetPoms.findPomFiles("C:\\Users\\win10_chsi\\IdeaProjects\\chsi_svd_package", poms);

        // System.out.println(poms);

        ParseXML parseXML = new ParseXML();
        List<Component> list = new ArrayList<>();

        for (PomPath pom : poms){
            // System.out.println(pom);
            Document document = parseXML.getXML(pom.getPath());
            parseXML.getVersion(document);
        }

        for (PomPath pom : poms){
            System.out.println(pom.getPath());
            Document document = parseXML.getXML(pom.getPath());
            parseXML.getComponent(document);
        }

    }
}
