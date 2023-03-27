package ru.fidarov.KilderonBOT.Kilderon.GPT;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ChatGPTModel {

    private final String URL = "https://api.openai.com/v1/completions";
    private final String TOKEN = "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";

    private String UserId;

    public ChatGPTModel(String userId) {
        UserId = userId;
    }

    public ChatGPTModel() {
    }

    public String chatGPT(String text) throws Exception {
        HttpURLConnection con = (HttpURLConnection) new URL(URL).openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + TOKEN);

        JSONObject data = new JSONObject();
        data.put("model", "text-davinci-003");
        data.put("prompt", text);
        data.put("max_tokens", 4000);
        data.put("temperature", 1.0);
        con.setDoOutput(true);
        con.getOutputStream().write(data.toString().getBytes());

        //parsing and getting the output
        String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
                .reduce((a, b) -> a + b).get();
        System.out.println(output);
        return new JSONObject(output)
                .getJSONArray("choices")
                .getJSONObject(0)
                .getString("text");
    }
}
