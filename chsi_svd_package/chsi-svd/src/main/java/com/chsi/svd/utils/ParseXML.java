package com.chsi.svd.utils;

import com.chsi.svd.pojo.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParseXML {

    /**
     * 获取pom配置文件为document对象
     * @param path
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    private Map<String,String> versionMap = new HashMap<>();

    public Document getXML(String path) throws IOException, SAXException, ParserConfigurationException {
        File pomFile = new File(path);
        // System.out.println(pomFile);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(pomFile);
        document.getDocumentElement().normalize();
        return document;
    }

    /**
     * 获取pom中的parent信息
     * @param doc
     * @return
     */
    public Component getParent(Document doc){
        Element parent = (Element) doc.getElementsByTagName("parent").item(0);
        Component component = new Component();
        component.setGroupId(parent.getElementsByTagName("groupId").item(0).getTextContent());
        component.setArtifactId(parent.getElementsByTagName("artifactId").item(0).getTextContent());
        String version = parent.getElementsByTagName("version").item(0).getTextContent();
        if (version.contains(".version")){
            version = versionMap.get(version.replace("[","").replace("]", "").replace("$", ""));
        }
        component.setVersion(version);
        return component;
    }

    /**
     * 获取pom中项目信息
     * @param doc
     * @return
     */
    public Component getProject(Document doc){
        Component component = new Component();
        component.setArtifactId(doc.getElementsByTagName("artifactId").item(0).getTextContent());
        String version = doc.getElementsByTagName("version").item(0).getTextContent();
        if (version.contains(".version")){
            version = versionMap.get(version.replace("[","").replace("]", "").replace("$", ""));
        }
        component.setVersion(version);
        return component;
    }

    /**
     * 获取dependence中的配置文件信息
     * @param doc
     * @return
     */
    public List<Component> getComponent(Document doc){
        NodeList dependencies = doc.getElementsByTagName("dependency");
        List<Component> list = new ArrayList<Component>();
        for (int i = 0; i < dependencies.getLength(); i++) {
            Element dependency = (Element) dependencies.item(i);
            Component component = new Component();

            Node groupId = dependency.getElementsByTagName("groupId").item(0);
            if (groupId!=null){
                String depGroupId = groupId.getTextContent();
                component.setGroupId(depGroupId);
            }

            Node artifactId = dependency.getElementsByTagName("artifactId").item(0);
            if (artifactId!=null){
                String depArtifactId = artifactId.getTextContent();
                component.setArtifactId(depArtifactId);
            }

            Node version = dependency.getElementsByTagName("version").item(0);
            if (version!=null){
                String depVersion = version.getTextContent();
                if (depVersion.contains(".version")){
                    depVersion = depVersion.replace("{","").replace("}", "").replace("$", "");
                    depVersion = this.versionMap.get(depVersion);
                }
                component.setVersion(depVersion);
            }

            list.add(component);
            System.out.println(component);
        }
        return list;
    }

    public void getVersion(Document document){
        NodeList nodeList = document.getElementsByTagName("properties").item(0).getChildNodes();
        for (int i=0; i<nodeList.getLength(); i++){
            if (nodeList.item(i).getNodeType() != Node.ELEMENT_NODE){
                continue;
            }
            Element element = (Element) nodeList.item(i);
            String key = element.getTagName();
            String v = element.getTextContent();
            if(key.endsWith(".version")){
                versionMap.put(key,v);
            }
        }
        System.out.println(versionMap);
    }


    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        ParseXML parseXML = new ParseXML();
        String path = "C:\\Users\\win10_chsi\\IdeaProjects\\chsi_svd_package\\chsi-svd\\pom.xml";
        Document xml = parseXML.getXML(path);
        List<Component> list = parseXML.getComponent(xml);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("group:"+ list.get(i).getGroupId() + "arti"+ list.get(i).getArtifactId() + "version" + list.get(i).getVersion());
        }
    }
}
