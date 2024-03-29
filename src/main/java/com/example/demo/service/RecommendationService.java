package com.example.demo.service;

import com.example.demo.Recommendation;
import com.example.demo.entity.AutoPart;
import com.example.demo.entity.Brand;
import com.example.demo.entity.Generation;
import com.example.demo.entity.Model;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.example.demo.Information.*;

@Service
public class RecommendationService {


    public RecommendationService() {
    }

    public  Recommendation recommend(AutoPart autoPart, Brand brand, Model model, Generation generation) throws IOException {
        String autoPartsUri = autoPart.getNameKey();
        String brandUri = brand.getNameKey();
        String modelUri = model.getNameKey();
        String generationUri = generation.getNameKey();
        String uri = BASE_URL+autoPartsUri+DEL+brandUri+DEL+modelUri+DEL+generationUri;

        Document document = Jsoup.connect(uri).get();
        Elements tables = document.getElementsByTag("h3");

        Elements elements0 = document.getElementsByAttributeValue("class", "repair-price");
        String price2 = elements0.text();

        //<span class="repair-price">1 040</span>
        String price = tables.get(2).text();

        String clockRate = tables.get(3).text();

        Recommendation recommendation = new Recommendation();

        if(clockRate.equals(NULL_MESSAGE)){
            recommendation.setMessage(MESSAGE_NULL_PRICE);
        }
        else{
            recommendation.setPrice(price2);
            recommendation.setClockRate(clockRate);}


        return recommendation;
    }

}
