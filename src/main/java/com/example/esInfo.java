package com.example;

public class esInfo {
    public static final String MAPPING_TEMPLATE = "{\n" +
            "  \"mappings\": {\n" +
            "    \"properties\": {\n" +
            "      \"id\": {\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"name\": {\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\"\n" +
            "      },\n" +
            "      \"address\": {\n" +
            "        \"type\": \"keyword\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"type_id\": {\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"images\": {\n" +
            "        \"type\": \"keyword\", \n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"area\": {\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_smart\"\n" +
            "      },\n" +
            "      \"x\": {\n" +
            "        \"type\": \"geo_point\"\n" +
            "      },\n" +
            "      \"y\": {\n" +
            "        \"type\": \"geo_shape\"\n" +
            "      },\n" +
            "      \"avg_price\": {\n" +
            "        \"type\": \"long\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"sold\": {\n" +
            "        \"type\": \"integer\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"comments\": {\n" +
            "        \"type\": \"integer\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"score\": {\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"open_hours\": {\n" +
            "        \"type\": \"text\"\n" +
            "      },\n" +
            "      \"creatr_time\": {\n" +
            "        \"type\": \"date\", \n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"update_time\": {\n" +
            "        \"type\": \"date\",\n" +
            "        \"index\": false\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";
}
