package io.lindstrom.m3u8.parser;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractLineParser<T> implements Parser<T> {
    private static final Pattern ATTRIBUTE_LIST_PATTERN = Pattern.compile("([A-Z0-9\\-]+)=(?:(?:\"([^\"]+)\")|([^,]+))");
    private final String tag;

    AbstractLineParser(String tag) {
        this.tag = tag;
    }

    @Override
    public void write(T value, StringBuilder stringBuilder) {
        stringBuilder.append(tag).append(':');
        stringBuilder.append(writeAttributes(value));
        stringBuilder.append('\n');
    }

    @Override
    public T parse(String attributes) {
        return parse(attributes, Collections.emptyMap());
    }

    @Override
    public T parse(String attributes, Map<String, String> moreAttributes) {
        if (attributes.isEmpty()) {
            return parseAttributes(Collections.emptyMap());
        } else {
            Map<String, String> attributeMap = parseAttributes(attributes);
            attributeMap.putAll(moreAttributes);
            return parseAttributes(attributeMap);
        }
    }

    protected T parseAttributes(Map<String, String> attributes) {
        throw new UnsupportedOperationException("Not implemented");
    }

    protected abstract String writeAttributes(T value);

    static Map<String, String> parseAttributes(String attributeList) {
        Matcher matcher = ATTRIBUTE_LIST_PATTERN.matcher(attributeList);
        Map<String, String> attributes = new HashMap<>();
        while (matcher.find()) {
            attributes.put(matcher.group(1), matcher.group(2) != null ? matcher.group(2) : matcher.group(3));
        }
        return attributes;
    }
}
