package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.github.javafaker.Faker;

import api.Data.UserDataGenerator;
import api.TestComponeents.BaseTest;
import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UserTests extends BaseTest {

    @Test(priority = 1, description = "Test user creation with status, headers, and response time validations")
    public void testPostUser() {
        logger.info("********** Creating user ***************");
        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();

        // Assertions on status code, header, response time and response body content.
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        Assert.assertTrue(response.getHeader("Content-Type").contains("application/json"),
                "Response Content-Type should be application/json");
        Assert.assertTrue(response.getTime() < 4000, "Response time should be less than 4000ms");

        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("0"),
                "Response body should be 0");
        
        response.then().log().all();
        logger.info("********** User is created ***************");
    }

    @Test(priority = 2, description = "Test retrieving user details with JSON validations")
    public void testGetUserByName() {
        logger.info("********** Reading User Info ***************");
        Response response = UserEndPoints.readUser(userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 on retrieving user info");
        Assert.assertTrue(response.getHeader("Content-Type").contains("application/json"),
                "Response Content-Type should be application/json");

        // Use JSONPath to validate that the response contains expected user information.
        JsonPath jsonPath = response.jsonPath();
        String usernameFromResponse = jsonPath.getString("username");
        Assert.assertEquals(usernameFromResponse, userPayload.getUsername(),
                "Username in response should match the requested username");

        // Soft assertions for additional fields
        softAssert.assertNotNull(jsonPath.getString("firstName"), "First name should not be null");
        softAssert.assertNotNull(jsonPath.getString("lastName"), "Last name should not be null");
        softAssert.assertNotNull(jsonPath.getString("email"), "Email should not be null");
        softAssert.assertAll();

        logger.info("********** User info is displayed ***************");
    }

    @Test(priority = 3, description = "Test updating user details and verifying the update")
    public void testUpdateUserByName() {
        logger.info("********** Updating User ***************");

        // Update payload data with new random values.
        String updatedFirstName = faker.name().firstName();
        String updatedLastName = faker.name().lastName();
        String updatedEmail = faker.internet().safeEmailAddress();
        userPayload.setFirstName(updatedFirstName);
        userPayload.setLastName(updatedLastName);
        userPayload.setEmail(updatedEmail);

        Response response = UserEndPoints.updateUser(userPayload.getUsername(), userPayload);
        response.then().log().body();

        // Assertions on update response.
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 on updating user");
        Assert.assertTrue(response.getTime() < 2000, "Update response time should be less than 2000ms");

        // Verify update by reading the user again.
        Response responseAfterUpdate = UserEndPoints.readUser(userPayload.getUsername());
        responseAfterUpdate.then().log().body();
        Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200, "Expected status code 200 after update");

        JsonPath jsonPath = responseAfterUpdate.jsonPath();
        Assert.assertEquals(jsonPath.getString("firstName"), updatedFirstName, "First name should be updated");
        Assert.assertEquals(jsonPath.getString("lastName"), updatedLastName, "Last name should be updated");
        Assert.assertEquals(jsonPath.getString("email"), updatedEmail, "Email should be updated");

        logger.info("********** User updated ***************");
    }

    @Test(priority = 4, description = "Test deleting user and verifying the deletion")
    public void testDeleteUserByName() {
        logger.info("********** Deleting User ***************");
        Response response = UserEndPoints.deleteUser(userPayload.getUsername());
        response.then().log().body();

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 on deleting user");

        // Additional assertion: Attempt to read the deleted user should now return a 404 (or appropriate error code)
        Response responseAfterDelete = UserEndPoints.readUser(userPayload.getUsername());
        Assert.assertEquals(responseAfterDelete.getStatusCode(), 404,
                "Expected status code 404 when retrieving a deleted user");

        logger.info("********** User deleted ***************");
    }
}
