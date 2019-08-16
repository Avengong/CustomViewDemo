package com.aven.demo.annotation_compile;

import com.aven.demo.annotations.BindView;
import com.google.auto.service.AutoService;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * Created by ${Aven.Gong} on 2019/7/1 0001.
 */
@AutoService(Processor.class)
public class BindViewProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Messager messager = processingEnv.getMessager();
        for (Element element : roundEnvironment.getElementsAnnotatedWith(BindView.class)) {
            messager.printMessage(Diagnostic.Kind.NOTE,"我是测试");
            System.out.println("我是测试");
            if (element.getKind() == ElementKind.FIELD) {
                messager.printMessage(Diagnostic.Kind.NOTE, "anno printMessage:" + element.toString());
            }
        }

        return false;
    }
}
