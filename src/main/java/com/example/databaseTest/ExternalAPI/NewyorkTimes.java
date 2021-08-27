package com.example.databaseTest.ExternalAPI;

import com.example.databaseTest.Services.NewsService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.io.IOException;
import java.util.*;

public class NewyorkTimes {
    private String apikey;
    public NewyorkTimes(String apikey ) {
        this.apikey=apikey;
    }

    public static void main(String[] args) {

    }

    public JSONObject getLatestNews (){

        String baseurl = "https://api.nytimes.com/svc/topstories/v2/business.json?api-key=%s";
        String apiurl= String.format(baseurl, this.apikey);

        try {
            return JsonReader.readJsonFromUrl(apiurl);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static List<String> getNews(){
        String apikey= "2f7BY2fttOEL1mHAkFhJsSZQMs9HplDR";
        NewyorkTimes nytimes = new NewyorkTimes(apikey);
        List<String> results = new ArrayList<>();
        try {
            JSONObject response = nytimes.getLatestNews();
            JSONArray news = response.getJSONArray("results");
            for (int i = 0 ; i < news.length(); i++) {
                JSONObject obj = news.getJSONObject(i);
                String headline = obj.getString("title");
                results.add(headline);
            }
            return results;
        }catch (Exception e){
            return null;
        }
    }
}
