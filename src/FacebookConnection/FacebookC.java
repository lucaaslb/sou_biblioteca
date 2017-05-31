/*
 * Projeto Integrado 3° Semestre CCO
 * 
 *  Author: Lucas Lacerda
 */
package FacebookConnection;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.User;

public class FacebookC {

	private User user = null;
	private String name;
	private String email;
	private String token;

	@SuppressWarnings("deprecation")
	public void ConnectFB() {
		String domain = "https://github.com/lucaaslb";
		String appId = "1805560713092457";

		String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id=" + appId
				+ "&redirect_uri=" + domain + "&scope=user_about_me,email,"
				+ "user_actions.books,user_actions.fitness,user_actions.music,user_actions.news,user_actions.video,user_birthday,user_education_history,"
				+ "user_events,user_photos,user_friends,user_games_activity,user_hometown,user_likes,user_location,user_photos,user_relationship_details,"
				+ "user_relationships,user_religion_politics,user_status,user_tagged_places,user_videos,user_website,user_work_history,ads_management,ads_read,"
				+ "manage_pages,publish_actions,read_insights,read_page_mailboxes,rsvp_event";

		System.setProperty("webdirver.chrome.driver", "chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.get(authUrl);

		FacebookClient fbClient = null;

		try {
			while (true) {

				if (!driver.getCurrentUrl().contains("facebook.com")) {
					String url = driver.getCurrentUrl();
					this.token = (url.replaceAll(".*#access_token=(.+)&.*", "$1"));

					driver.quit();

					fbClient = new DefaultFacebookClient(token) {
						@Override
						protected String getFacebookGraphEndpointUrl() {
							return "https://graph.facebook.com/v2.0";
						}
					};

					this.user = fbClient.fetchObject("me", User.class,
							Parameter.with("fields", "id,name,email,birthday"));

					this.name = user.getName();
					this.email = user.getEmail();

				}
			}
		} catch (WebDriverException exe) {

		}

	}

	@SuppressWarnings("deprecation")
	public void Share(String msg, String token) {
		FacebookClient fbClient = new DefaultFacebookClient(token);

		fbClient.publish("me/feed", FacebookType.class, Parameter.with("message", msg)); // POST
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getToken() {
		return token;
	}

}
