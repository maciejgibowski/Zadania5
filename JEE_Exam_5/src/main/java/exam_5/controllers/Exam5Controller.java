package exam_5.controllers;

import java.util.regex.Pattern;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;
import exam_5.beans.Animal;
import exam_5.beans.AnimalDao;

// @SessionAttributes({ "param1", "param2" })
@Controller
public class Exam5Controller {

	// Zadanie 1 -------------------------------------------------
	// Konfiguracja
	// Zadanie 2 -------------------------------------------------

	// 1. Stwórz kontroler o nazwie Exam5Controller.

	// 2. Utwórz akcję dostępną pod adresem /, która będzie wyświetlać widok o
	// nazwie home.jsp

	@RequestMapping("/")
	public String home() {
		return "home";
	}
	// 3. Utwórz akcję dostępną pod adresem /first/<param1>/<param2>

	@RequestMapping("/first/{param1}/{param2}")
	public String first(Model model, HttpSession sess, @PathVariable String param1, @PathVariable String param2) {
		
		// 5. Pobierz parametry param1 oraz param2, przekaż do widoku a następnie wyświetl.
		
		model.addAttribute("param1", param1);
		model.addAttribute("param2", param2);
		
		//6. Oznacz parametry jak przechowywane w sesji.
		
		// To można zrobić jeszcze na inne sposoby:
		// @SessionAttributes({"param1", "param2"})
		// W sygnaturze metody kontrolera możemy umieścić obiekt HttpSession.
		// Następnie możemy pobrać parametry z sesji za pomocą metody getAttribute.
		// @Component
		// @Scope(value = WebApplicationContext.SCOPE_SESSION,
		// proxyMode = ScopedProxyMode.TARGET_CLASS)
		
		sess.setAttribute("param1", param1);
		sess.setAttribute("param2", param2);

		// 4. Akcja ma ma wyświetlać widok first.jsp

		return "first";
	}
		// Utwórz akcję dostępną pod adresem /check, wyświetlającą w widoku check.jsp parametry 
		// param1 oraz param2 zapisane w sesji
		
		@RequestMapping("/check")
			public String check() {
				return "check";
			}			
	// Zadanie 3. -----------------------------------------------

	// 1. W kontrolerze Exam5Controller utwórz akcję dostępną pod adresem
	// /regex/<param1>
	// Tu jest kłopot, bo adres www ma kropkę w rozszerzeniu pliku, stąd regex
	// param1:.+

	// Inna wersja...
	// @RequestMapping("/regex/{param1:.+}")
	// public String regex(Model model, @PathVariable String param1) {
	// String regex = "^[a-z]+\\.(png)?(jpg)?(gif)?";
	// Pattern pattern = Pattern.compile(regex);
	// Matcher matcher = pattern.matcher(param1);
	// System.out.println(param1);
	// if (matcher.matches()) {
	// System.out.println("Done");
	// return String.format("redirect:https://www.google.pl/#q=%s", param1);
	// }
	// return "regex";
	// }
	@GetMapping("/regex/{param1:.+}")
	public String regex(@PathVariable String param1) {
		System.out.println(param1);

		boolean result = Pattern.matches("^[a-z]+\\.(gif|jpg|png)$", param1);
		System.out.println(result);

		if (result == false) {
			return "regex";
		}
		return String.format("redirect:https://www.google.pl/#q=%s", param1);
	}
	// Zadanie 4. ---------------------------------------

	// 1. Dostępną pod adresem /createCookie/{cookieName}/{cookieValue}/{cookieTime} -Ma ona nastawiać
	// ciasteczko o podanej nazwie na podaną wartość. Ciasteczko ma żyć przez cookieTime minut

	@RequestMapping("/createCookie/{cookieName}/{cookieValue}/{cookieTime}")
	@ResponseBody
	public String setCookie(HttpServletResponse response, @PathVariable String cookieName,
			@PathVariable String cookieValue, @PathVariable int cookieTime) {

		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(60 * cookieTime); // nie wiem, czy nie trzeba: (60 * Integer.parseInt(cookieTime))
		cookie.setPath("/");
		response.addCookie(cookie);
		System.out.println(cookie);

		return "ciasteczko ustawione";
	}
// 2. Dostępną pod adresem /deleteCookie/{cookieName} - wyświetla zawartość ciasteczka o podanej 
// nazwie i następnie je usuwa. Jeżeli takiego ciasteczka nie ma – powinien wyświetlać informację "Brak ciasteczka"
	
	 @RequestMapping("/deleteCookie/{cookieName}")
	 @ResponseBody
		 public String deleteCookie(HttpServletRequest request, @PathVariable String cookieName) {
				Cookie cookie = WebUtils.getCookie(request, cookieName);
				if (cookie != null) {
					cookie.setMaxAge(0);
					return "Ciasteczko usunięte";
				} else {
					return "Brak ciasteczka";
				}
		}	 
		// Zadanie 5. ---------------------------------------
	 
// 1.  W kontrolerze Exam5Controller utwórz akcję dostępną pod adresem /animals
	 
	 @GetMapping("/animals")
	 	public String animalView(Model model) { 
	 		String result = "";
	 		for(Animal animal: AnimalDao.getList() ) {
	 			result += animal.toString()+"</br>";
	 			model.addAttribute("lista", result);
	 		}
	 		return "animal";
	 	}
}
