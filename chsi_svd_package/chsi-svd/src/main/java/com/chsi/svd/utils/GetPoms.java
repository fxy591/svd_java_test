package com.chsi.svd.utils;

import com.chsi.svd.DTO.PomPath;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetPoms {
    public static void findPomFiles(String dirPath, List<PomPath> pom) {
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        PomPath pomPath = new PomPath();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                findPomFiles(file.getAbsolutePath(), pom);
            } else if (file.getName().equals("pom.xml")) {
                pomPath.setName(file.getParentFile().getName());
                pomPath.setPath(file.getAbsolutePath());
                pom.add(pomPath);
            }
        }
    }

    public static void main(String[] args) {
        List<PomPath> pom = new ArrayList<>();
        GetPoms.findPomFiles("C:\\Users\\win10_chsi\\IdeaProjects\\chsi_svd_package", pom);

        System.out.println(pom);
    }

}
