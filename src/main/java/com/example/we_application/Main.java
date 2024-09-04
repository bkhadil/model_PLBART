package com.example.we_application;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            String filePath = "JavaFile.java";
            List<JavaCodeAnalyzer.MethodInfo> methods = JavaCodeAnalyzer.analyzeCode(filePath);

            CodeBERTClient codeBERTClient = new CodeBERTClient();
            DocumentationGenerator docGenerator = new DocumentationGenerator(codeBERTClient);
            
            String documentation = docGenerator.generateDocumentation(methods);
            System.out.println(documentation);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}