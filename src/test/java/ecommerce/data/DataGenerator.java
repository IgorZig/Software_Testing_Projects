package ecommerce.data;

import com.github.javafaker.Faker;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class DataGenerator {

    // Method to generate the data
    public List<HashMap<String, String>> generateData(int numberOfRecords) throws IOException {
        Faker faker = new Faker();
        List<HashMap<String, String>> dataList = new ArrayList<>();

        // Generate the specified number of records
        for (int i = 0; i < numberOfRecords; i++) {
            HashMap<String, String> data = new HashMap<>();
            data.put("firstName", faker.name().firstName());
            data.put("lastName", faker.name().lastName());
            data.put("email", faker.internet().emailAddress());
            data.put("phoneNumber", generatePhoneNumber(faker));
            data.put("occupation", faker.job().title());
            data.put("gender", faker.options().option("Male", "Female"));
            data.put("password", generatePassword());
            data.put("confirmPassword", data.get("password"));
            data.put("isAdult", "true");

            dataList.add(data);
        }

        // Use Jackson to convert the list of data to JSON and write it to a file
        ObjectMapper objectMapper = new ObjectMapper();
        String filePath = System.getProperty("user.dir") + "//src//test//java//ecommerce//data//RegistrationName.json";
        objectMapper.writeValue(new File(filePath), dataList);

        System.out.println("Test data generated and saved to: " + filePath);

        return dataList; // Return the generated data
    }

    // Password generation logic
    private static String generatePassword() {
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digits = "0123456789";
        String specialChars = "!@#$%^&*()-_=+<>?";

        Random random = new Random();
        char lowerChar = lowercase.charAt(random.nextInt(lowercase.length()));
        char upperChar = uppercase.charAt(random.nextInt(uppercase.length()));
        char digitChar = digits.charAt(random.nextInt(digits.length()));
        char specialChar = specialChars.charAt(random.nextInt(specialChars.length()));

        List<Character> passwordChars = new ArrayList<>();
        passwordChars.add(lowerChar);
        passwordChars.add(upperChar);
        passwordChars.add(digitChar);
        passwordChars.add(specialChar);

        for (int i = 0; i < 4; i++) {
            char randomChar = lowercase.charAt(random.nextInt(lowercase.length()));
            passwordChars.add(randomChar);
        }

        Collections.shuffle(passwordChars);

        StringBuilder password = new StringBuilder();
        for (char c : passwordChars) {
            password.append(c);
        }

        return password.toString();
    }

    // Phone number generation logic
    private static String generatePhoneNumber(Faker faker) {
        String phoneNumber = faker.number().digits(9);
        phoneNumber = "1" + phoneNumber;  // Ensure it doesn't start with 0
        return phoneNumber;
    }
}
