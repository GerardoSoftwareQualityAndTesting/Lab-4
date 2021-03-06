package mx.tec.lab;

import static org.junit.jupiter.api.Assertions.*;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mx.tec.lab.entity.Dragon;
import mx.tec.lab.repository.DragonRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DragonRepositoryTest {
	@Resource
	private DragonRepository dragonRepository;
	
	@Test
	public void givenDragon_whenSave_thenRetrieveSame() {
		Dragon dragon = new Dragon(1, "Meraxes");
		dragonRepository.save(dragon);
		
		Dragon retrievedDragon = dragonRepository.findById(1L).orElse(null);
		assertEquals("Meraxes", retrievedDragon.getName());
	}
	
	@Test
	public void givenDragon_whenSave_thenRename() {
		Dragon dragon = new Dragon(2, "Fafnir");
		dragonRepository.save(dragon);
		
		Dragon modifyingDragon = dragonRepository.findById(2L).orElse(null);
		modifyingDragon.setName("Quetzalcoatl");
		dragonRepository.save(modifyingDragon);
		
		Dragon retrievedDragon = dragonRepository.findById(2L).orElse(null);
		assertEquals("Quetzalcoatl", retrievedDragon.getName());
	}
	
	@Test
	public void givenDragon_whenSave_thenDelete() {
		Dragon dragon = new Dragon(2, "Smaug");
		dragonRepository.save(dragon);
		
		dragonRepository.deleteById(2L);
		
		Dragon retrievedDragon = dragonRepository.findById(2L).orElse(null);
		assertNull(retrievedDragon);
	}
}
