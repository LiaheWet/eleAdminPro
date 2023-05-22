package com.eleadmin.generator.engine;

import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * Beetl模板引擎实现文件输出
 *
 * @author EleAdmin
 * @since 2021-09-05 00:30:28
 */
public class BeetlTemplateEnginePlus extends AbstractTemplateEngine {
    private GroupTemplate groupTemplate;

    @Override
    public AbstractTemplateEngine init(ConfigBuilder configBuilder) {
        super.init(configBuilder);
        try {
            Configuration cfg = Configuration.defaultConfiguration();
            groupTemplate = new GroupTemplate(new FileResourceLoader(), cfg);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return this;
    }

    @Override
    public void writer(Map<String, Object> objectMap, String templatePath, String outputFile) throws Exception {
        Template template = groupTemplate.getTemplate(templatePath);
        try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
            template.binding(objectMap);
            template.renderTo(fileOutputStream);
        }
        logger.debug("模板:" + templatePath + ";  文件:" + outputFile);
    }

    @Override
    public String templateFilePath(String filePath) {
        return filePath + ".btl";
    }

}
