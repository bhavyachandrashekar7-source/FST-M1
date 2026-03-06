package activities;
import static java.time.Duration.ofMillis;
import static org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;

import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.Sequence;

import io.appium.java_client.AppiumDriver;

public class ActionsBase1 {
	private final static PointerInput pointer = new PointerInput(Kind.TOUCH, "pointer");
	public static void doLongPress(AppiumDriver driver, Point start) {
	    // Create s sequence of actions
	    Sequence longPress = new Sequence(pointer, 1)
	            .addAction(pointer.createPointerMove(ofMillis(0), viewport(), start.getX(), start.getY()))
	            .addAction(pointer.createPointerDown(LEFT.asArg()))
	            .addAction(new Pause(pointer, Duration.ofMillis(1100)))
	            .addAction(pointer.createPointerUp(LEFT.asArg()));

	    // Perform the actions
	    driver.perform(Arrays.asList(longPress));
	}


}
