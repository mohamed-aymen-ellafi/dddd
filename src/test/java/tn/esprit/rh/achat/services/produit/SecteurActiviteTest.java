package tn.esprit.rh.achat.services.produit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;
import tn.esprit.rh.achat.services.ISecteurActiviteService;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class SecteurActiviteTest {
	
	@Autowired
	ISecteurActiviteService secteurActiviteService;
	
	@MockBean
	SecteurActiviteRepository secteurActiviteRepository;
	
	SecteurActivite s= new SecteurActivite((long) 1,"100","libelle 1",null);
	SecteurActivite s2= new SecteurActivite((long) 2,"200","libelle 2",null);
	SecteurActivite s3= new SecteurActivite((long) 3,"300","libelle 3",null);
	
	@Test
	void retrieveAllSecteurTest() {
    	when(secteurActiviteRepository.findAll()).thenReturn(Stream
    			.of(s,s2,s3)
    			.collect(Collectors.toList()));
    	assertEquals(3,secteurActiviteService.retrieveAllSecteurActivite().size());
        
	}
	
	@Test
	public void retrieveSecteurTest() {
		when(secteurActiviteRepository.findById(s.getIdSecteurActivite())).thenReturn(Optional.of(s));
		assertEquals(s,secteurActiviteService.retrieveSecteurActivite(s.getIdSecteurActivite()));
	}
	
	@Test
	void createSecteurTest(){     
    	when(secteurActiviteRepository.save(s)).thenReturn(s);
    	assertNotNull(s);
		assertEquals(s,secteurActiviteService.addSecteurActivite(s));
    }
	
	@Test
	void updateSecteurTest(){
		when(secteurActiviteRepository.save(s)).thenReturn(s);
		assertNotNull(s);
		assertEquals(s, secteurActiviteService.updateSecteurActivite(s));
    }
	
	@Test
	void deleteSecteurTest(){
		secteurActiviteRepository.save(s);
		secteurActiviteService.deleteSecteurActivite(s.getIdSecteurActivite());
		verify(secteurActiviteRepository, times(1)).deleteById(s.getIdSecteurActivite());
    }
}
