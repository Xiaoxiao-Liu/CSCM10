import java.util.Arrays;

import com.google.api.services.translate.Translate;
import com.google.api.services.translate.Translate.Translations;
import com.google.api.services.translate.model.TranslationsListResponse;
import com.google.api.services.translate.model.TranslationsResource;

public class TranslateMe {


    public static void main(String[] args) {

        try {           
            // See comments on 
            //   https://developers.google.com/resources/api-libraries/documentation/translate/v2/java/latest/
            // on options to set
            Translate t = new Translate.Builder(
                    com.google.api.client.googleapis.javanet.GoogleNetHttpTransport.newTrustedTransport()
                    , com.google.api.client.json.gson.GsonFactory.getDefaultInstance(), null)                                   
                    //Need to update this to your App-Name
                    .setApplicationName("ShakesVis")                    
                    .build();           
            Translate.Translations.List list = t.new Translations().list(
                    Arrays.asList(
                            //Pass in list of strings to be translated
                            "Hello World",
                            "How to use Google Translate from Java"), 
                        //Target language   
                        "ES");  
            //Set your API-Key from https://console.developers.google.com/
            list.setKey("AIzaSyAs48FHTLNCZlmNLzTPPnpCjkgIz6THIFU");
            TranslationsListResponse response = list.execute();
            for(TranslationsResource tr : response.getTranslations()) {
                System.out.println(tr.getTranslatedText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}