package exam_5.beans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class AnimalDao {
	private static List<Animal> list = new ArrayList<Animal>();

	public static List<Animal> getList() {
		list.add(new Animal("Dorsz", "Ryba"));
		list.add(new Animal("Żaba", "Płaz"));
		list.add(new Animal("Goryl", "Ssak"));
		return list;
	}
}
