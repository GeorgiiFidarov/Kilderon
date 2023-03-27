package ru.fidarov.KilderonBOT.Kilderon.GPT;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ChatGPT {
    private final String URL = "https://api.openai.com/v1/chat/completions";
    private final String TOKEN = "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";

    private String UserId;

    public ChatGPT(String userId) {
        UserId = userId;
    }

    public ChatGPT() {
    }

    public String chatGPT(String text) throws Exception {
        HttpURLConnection con = (HttpURLConnection) new URL(URL).openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer "+TOKEN);

        JSONObject data = new JSONObject();
        //collecting post json object
        Map<String,String> map = new HashMap<>();
        map.put("role","assistant");
        map.put("content",text);
        List<Map<String,String>> listOfMap = List.of(map);
        data.put("model", "gpt-3.5-turbo");
        data.put("messages",listOfMap);
        data.put("temperature", 1.0);
        data.put("top_p",1);
        data.put("frequency_penalty",2);
        data.put("presence_penalty",2);
        data.put("user",String.valueOf(UserId));
        System.out.println(UserId);
        con.setDoOutput(true);
        con.getOutputStream().write(data.toString().getBytes());

        //parsing and getting the output
        String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
                .reduce((a, b) -> a + b).get();
        return new JSONObject(output)
                .getJSONArray("choices")
                .getJSONObject(0)
                .getJSONObject("message")
                .getString("content");
    }
}
