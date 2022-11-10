package tn.esprit.rh.achat.services.operateur;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.junit.runner.RunWith;

import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.IOperateurService;


@SpringBootTest
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class OperateurServiceTest  {
	//kkkkkk
	@MockBean
	private OperateurRepository or;
	
	    Operateur o1=new Operateur(1L,"operateur1","mohamed","123",null);
	    Operateur o2=new Operateur(2L,"operateur2","ahmed","000",null);	  
	@Autowired
	    IOperateurService os;
	
    
    @Test
	public void addOperateurTest() {
    	when(or.save(o1)).thenReturn(o1);
    	assertNotNull(o1);
		assertEquals(o1, os.addOperateur(o1)); 
		System.out.println("add works !");
	}
    
    @Test 
    public void retrieveAllOperateursTest() {
    	when(or.findAll()).thenReturn(Stream
    			.of(o1,o2)
    			.collect(Collectors.toList()));
    	assertEquals(2,os.retrieveAllOperateurs().size());
    	System.out.println("Retrieve operators works !");
    }
    
   
    
    @Test
    public void DeleteOperateurTest() {
    	or.save(o1);
    	os.deleteOperateur(o1.getIdOperateur());
    	verify(or, times(1)).deleteById(o1.getIdOperateur());
    	System.out.println("Delete operator works !");
    	
    }

    
    @Test 
    public void UpdateOperateurTest() {
    	when(or.save(o1)).thenReturn(o1);
    	assertNotNull(o1);
    	assertEquals(o1, os.updateOperateur(o1));
    	System.out.println("Update operator works !");
    }
    
    @Test
    public void retrieveOperateurTest() {
    	when(or.findById(o1.getIdOperateur())).thenReturn(Optional.of(o1));
    	assertEquals(o1, os.retrieveOperateur(o1.getIdOperateur()));
    	System.out.println("Retrieve operator works !");
    }
}