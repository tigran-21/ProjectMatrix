package Helper;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CreateUser {

    @org.jetbrains.annotations.NotNull
    public static String[] createUser(){
        JSONParser parser = new JSONParser();
        File userFile = new File(".user.json");

        String[] credentials = new String[2];

        if (userFile.exists()) {
            try {
                Object obj = parser.parse(new FileReader(userFile));
                JSONObject jsonObject = (JSONObject) obj;

                // Read the data from the JSON object
                String username = (String) jsonObject.get("username");
                String password = (String) jsonObject.get("password");

                credentials[0] = username;
                credentials[1] = password;
            } catch (ParseException | FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("File not found. ");

        }
        return credentials;
    }

}
