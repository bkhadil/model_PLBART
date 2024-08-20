package com.example.we_application;

import java.util.List;

public class DocumentationGenerator {

    private final CodeBERTClient codeBERTClient;

    public DocumentationGenerator(CodeBERTClient codeBERTClient) {
        this.codeBERTClient = codeBERTClient;
    }

    public String generateDocumentation(List<JavaCodeAnalyzer.MethodInfo> methods) throws Exception {
        StringBuilder documentation = new StringBuilder();

        for (JavaCodeAnalyzer.MethodInfo method : methods) {
            documentation.append("### Method: ").append(method.getName()).append("\n");
            documentation.append("**Signature:** ").append(method.getSignature()).append("\n");
            documentation.append("**Parameters:** ").append(method.getParameters()).append("\n");
            documentation.append("**Return Type:** ").append(method.getReturnType()).append("\n");

            String doc = codeBERTClient.generateDocumentation(method.getSignature());
            documentation.append("**Generated Documentation:**\n").append(doc).append("\n\n");
        }

        return documentation.toString();
    }
}
