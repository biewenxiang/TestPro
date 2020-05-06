package com.bwx.vm;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;


public class TemplateAgent {

    public static HashMap<String, HashMap<Template, File>> template_map = new HashMap<String, HashMap<Template, File>>();
    public static HashMap<Template, File> map = new HashMap<>();
    public static  VelocityEngine ve = new VelocityEngine();

    static {

        try {
            ve.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
            ve.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
            ve.setProperty(Velocity.RESOURCE_LOADER, "file");
            ve.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "D:\\IdeaProjects\\TestPro\\src\\main\\resources");

            ve.init();


            Template template = ve.getTemplate("helloworld.vm");
            map.put(template, new File("./"));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static HashMap<Template, File> getTemplates(String type) {
        return template_map.get(type);
    }


    public static void main(String[] args) throws IOException {
        VelocityContext context = new VelocityContext();
        List<String> aa = new ArrayList<>();
        for (int i =0;i<10;i++){
            aa.add("hello"+i);
        }
        context.put("hello2",aa);
        context.put("hello","hello all");
        context.put("name","hello tom");
        for (Map.Entry entry : map.entrySet()) {
            StringWriter stringWriter = new StringWriter();

            Template template = (Template) entry.getKey();
             File path = (File) entry.getValue();
            template.merge(context,stringWriter);
            stringWriter.close();
            System.out.println(stringWriter.toString());

        }
        System.out.println("---------------");
//        reset();
//        System.out.println(map.size());
//        for (Map.Entry entry : map.entrySet()) {
//            StringWriter stringWriter = new StringWriter();
//            System.out.println("");
//            Template template = (Template) entry.getKey();
//            File path = (File) entry.getValue();
//            template.merge(context,stringWriter);
//            stringWriter.close();
//            System.out.println(stringWriter.toString());
//
//        }



    }
    public static void reset(){
        Template template = ve.getTemplate("rightindex.vm");
        map.put(template, new File("./"));
    }
}
