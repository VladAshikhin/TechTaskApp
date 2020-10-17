package com.app;

import com.app.objects.CorporateStyle;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TestData {

    private static final String DATA = "{\"type\":\"corporateStyle\"," +
            "\"data\":" +
            "{" +
            "\"companyName\":\"Apple\"," +
            "\"maketSize\":\"200x400\"," +
            "\"maketOrientation\":\"Horizontal\"," +
            "\"info\":\"Inform clients about promo\"," +
            "\"platform\":\"Slider on company website\"," +
            "\"deadline\":\"2020-10-17\"," +
            "\"buttonRequired\":\"yes\"," +
            "\"buttonText\":\"See more\"," +
            "\"primaryMaketText\":\"Be patient and get your reward!\"," +
            "\"secondaryMaketText\":\"Get a car for free.\"," +
            "\"contacts\":\"+79997654321\"," +
            "\"examplesReady\":\"yes\"}" +
            "}";


    @Test
    public void dataTest() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> dataObject = mapper.readValue(DATA, Map.class);

        if (dataObject.containsKey("data")) {
            Map<String, String> data = (Map<String, String>) dataObject.get("data");

            CorporateStyle cs = mapper.convertValue(data, CorporateStyle.class);

            String companyName = cs.getCompanyName();
            Assert.assertEquals("Apple", companyName);
        }
    }
}