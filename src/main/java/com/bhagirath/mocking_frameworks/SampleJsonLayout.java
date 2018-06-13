package com.bhagirath.mocking_frameworks;


import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.LayoutBase;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/*import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.layout.AbstractStringLayout;*/
import wiremock.org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

//@Plugin(name = "SimpleJSONLayout", category = Node.CATEGORY,  printObject = true)
public class SampleJsonLayout extends LayoutBase<ILoggingEvent> {

    private final ObjectMapper objectMapper;
    private SimpleDateFormat simpleDateFormat;

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss,SSS";

    public SampleJsonLayout() {
       // super(Charset.forName("UTF-8"));
        this.objectMapper = new ObjectMapper();
        this.objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        this.simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
    }


    private String buildJson(Map<String, Object> objectMap) {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        builder.append(StringUtils.join(objectMap.entrySet().stream().map(e ->
                String.format("\"%s\":%s", e.getKey(), String.format("\"%s\"", e.getValue())
        )).collect(Collectors.toList()), ","));
        builder.append("}");
        return builder.toString();
    }

    private String toJson(Map<String, Object> objectMap) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectMap);
        } catch (JsonProcessingException ex) {
            return buildJson(objectMap);
        }
    }


    @Override
    public String doLayout(ILoggingEvent event) {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("level", event.getLevel().levelStr);
        objectMap.put("thread", event.getThreadName());
        objectMap.put("logger", event.getLoggerName());
        objectMap.put("keyValue", event.getMDCPropertyMap().get("keyValue"));
        objectMap.put("body", event.getMessage());
        return toJson(objectMap);
    }
}
