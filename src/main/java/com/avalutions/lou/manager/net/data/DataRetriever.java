package com.avalutions.lou.manager.net.data;

import com.avalutions.lou.manager.net.data.lookups.Lookup;
import com.avalutions.lou.manager.net.data.lookups.parsers.BasicParser;
import com.avalutions.lou.manager.net.data.lookups.parsers.Parser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Copyright (c) 2012 All Right Reserved,
 * Developer: Benny Thompson
 * Date: 9/18/12
 * Summary:
 */
public class DataRetriever {
    private static final Pattern startPattern = Pattern.compile("^.*GAMEDATA=(\\{.*)\"\\);</script>$");
    private static final Pattern contentPattern = Pattern.compile("<script type=\"text/javascript\">s\\(\"(.*)\"\\);</script>");
    private static final Pattern endPattern = Pattern.compile("<script type=\"text/javascript\">s\\(\"(.*});if.*");
    private final ObjectMapper mapper = new ObjectMapper();

    public Map<String, Lookup> parse(InputStream inputStream) {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Map<String, Lookup> result = new HashMap<String, Lookup>();

        String line;
        try {
            if (reader != null) {
                while ((line = reader.readLine()) != null) {
                    String toAppend = getStart(line);
                    if (toAppend == null) {
                        toAppend = getEnd(line);
                        if (toAppend != null) {
                            builder.append(toAppend);
                            break;
                        }
                    }
                    if (toAppend == null) {
                        toAppend = getContent(line);
                    }

                    if (toAppend != null) {
                        builder.append(toAppend);
                    }
                }

                JsonNode root = mapper.readTree(builder.toString());

                for (Parser parser : getParsers()) {
                    JsonNode item = root.get(parser.getName());
                    result.put(parser.getName(), parser.parse(item));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private Parser[] getParsers() {
        return new Parser[]{
                new BasicParser(World.BUILDINGS),
                new BasicParser(World.DUNGEONS),
                new BasicParser(World.ITEMS),
                new BasicParser(World.PLAYER_TITLES),
                new BasicParser(World.QUEST_GROUPS),
                new BasicParser(World.QUESTS),
                new BasicParser(World.REPORTS),
                new BasicParser(World.RESOURCES),
                new BasicParser(World.UNIT_TYPES),
                new BasicParser(World.UNITS)
        };
    }

    private String getStart(String line) {
        Matcher matcher = startPattern.matcher(line);
        return getAndClean(matcher);
    }

    private String getContent(String line) {
        Matcher matcher = contentPattern.matcher(line);
        return getAndClean(matcher);
    }

    private String getEnd(String line) {
        Matcher matcher = endPattern.matcher(line);
        return getAndClean(matcher);
    }

    private String getAndClean(Matcher matcher) {
        if (matcher.find()) {
            return clean(matcher.group(1));
        }
        return null;
    }

    private String clean(String json) {
        return json.replace("\\\"", "\"").replace("\\\\", "\\");
    }
}
