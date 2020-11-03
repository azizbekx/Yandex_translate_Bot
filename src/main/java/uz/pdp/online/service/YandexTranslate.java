package uz.pdp.online.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uz.pdp.online.enums.ResponseCode;
import uz.pdp.online.model.ResponseModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;


public class YandexTranslate {
    private static final Logger logger = LoggerFactory.getLogger(YandexTranslate.class);

    public static ResponseModel getTranslation(String lang, String text) throws IOException {

        String API_KEY = "dict.1.1.20201101T140121Z.e41f08a8ee256a5e.126e7d5d768cda1bba93b2fd1ac9abc81bf5a751";
        String path = "https://dictionary.yandex.net/api/v1/dicservice.json/lookup?";
        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(path + "key=" + API_KEY + "&lang=" + lang + "&text=" + text);
        HttpResponse response = client.execute(httpGet);

        setLogStatusCode(response);
        InputStream inputStream = response.getEntity().getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type type = new TypeToken<ResponseModel>() {
        }.getType();

        ResponseModel responseModel = gson.fromJson(reader, type);
        return responseModel;
    }

    public static void setLogStatusCode(HttpResponse response) {
        ResponseCode responseCode = ResponseCode.findByCode(response.getStatusLine().getStatusCode());

        if (responseCode == null) {
            logger.error("[ERROR not valid api], don't get response");
        } else if (responseCode.code==200) {
            logger.info("[SUCCESS get response]");
        }else {
            logger.error("[ERROR] status code: "+responseCode.code+" Description: "+responseCode.description);
        }

    }
}
