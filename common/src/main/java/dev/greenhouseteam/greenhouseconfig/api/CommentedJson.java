package dev.greenhouseteam.greenhouseconfig.api;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;

import java.util.LinkedHashMap;
import java.util.Map;

public class CommentedJson {
    private final JsonElement json;
    private final String[] comments;

    public static final CommentedJson EMPTY = new CommentedJson(JsonNull.INSTANCE);

    public CommentedJson(JsonElement json) {
        this.json = json;
        this.comments = new String[]{};
    }

    public CommentedJson(JsonElement json, String... comments) {
        this.json = json;
        this.comments = comments;
    }

    public JsonElement json() {
        return json;
    }

    public String[] comments() {
        return comments;
    }

    public static class Object extends CommentedJson {
        private Map<String, CommentedJson> map = new LinkedHashMap<>();

        public static final CommentedJson.Object EMPTY = new Object(Map.of());

        public Object(Map<String, CommentedJson> map) {
            super(null);
            this.map = map;
        }

        public Object(Map<String, CommentedJson> map, String... comments) {
            super(null, comments);
            this.map = map;
        }

        public Object(String... comments) {
            super(null, comments);
        }

        public Map<String, CommentedJson> getMap() {
            return ImmutableMap.copyOf(map);
        }

        public void put(String name, CommentedJson json) {
            map.put(name, json);
        }

        public void putAll(Map<String, CommentedJson> map) {
            this.map.putAll(map);
        }
    }
}
