package numble.beginningcarrot;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	@GetMapping
	public ResponseEntity<String> welcomeMainPage() {
		return ResponseEntity.ok("hello world!");
	}
}
