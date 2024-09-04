
package com.example.we_application;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JavaCodeAnalyzer {

    public static List<MethodInfo> analyzeCode(String filePath) throws IOException {
        JavaParser parser = new JavaParser();
        FileInputStream fis = new FileInputStream(filePath);
        ParseResult<CompilationUnit> result = parser.parse(fis);

        if (!result.isSuccessful() || result.getResult().isEmpty()) {
            result.getProblems().forEach(problem -> System.err.println("Parsing Problem: " + problem.getMessage()));
            throw new RuntimeException("Failed to parse code");
        }

        CompilationUnit cu = result.getResult().get();

        List<MethodInfo> methods = new ArrayList<>();
        cu.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(MethodDeclaration md, Void arg) {
                super.visit(md, arg);
                String methodName = md.getNameAsString();
                String parameters = md.getParameters().toString();
                String returnType = md.getType().toString();
                String signature = md.getDeclarationAsString();

                methods.add(new MethodInfo(methodName, parameters, returnType, signature));
            }
        }, null);

        return methods;
    }

    public static class MethodInfo {
        private final String name;
        private final String parameters;
        private final String returnType;
        private final String signature;

        public MethodInfo(String name, String parameters, String returnType, String signature) {
            this.name = name;
            this.parameters = parameters;
            this.returnType = returnType;
            this.signature = signature;
        }

        public String getName() {
            return name;
        }

        public String getParameters() {
            return parameters;
        }

        public String getReturnType() {
            return returnType;
        }

        public String getSignature() {
            return signature;
        }
    }
}
