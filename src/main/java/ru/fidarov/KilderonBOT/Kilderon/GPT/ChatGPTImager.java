package ru.fidarov.KilderonBOT.Kilderon.GPT;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ChatGPTImager {

    private final String URL = "https://api.openai.com/v1/images/generations";
    private final String TOKEN = "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";

    public String chatGPT(String text) throws Exception {
        HttpURLConnection con = (HttpURLConnection) new URL(URL).openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + TOKEN);

        JSONObject data = new JSONObject();
        //collecting post json object;

        data.put("prompt", text);
        data.put("n", 1);
        data.put("size", "256x256");
        data.put("response_format","url");

        con.setDoOutput(true);
        con.getOutputStream().write(data.toString().getBytes());

        //parsing and getting the output
        String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
                .reduce((a, b) -> a + b).get();
        return new JSONObject(output).getJSONArray("data").getJSONObject(0).getString("url");
    }
}
