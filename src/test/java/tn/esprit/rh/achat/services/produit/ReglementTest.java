/*package tn.esprit.rh.achat.services.produit;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.repositories.ReglementRepository;
import tn.esprit.rh.achat.services.IFactureService;
import tn.esprit.rh.achat.services.IReglementService;

import org.junit.runner.RunWith;

import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;


@SpringBootTest
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class ReglementServiceTest {
 
    @Autowired
    IReglementService ps;
    
    @Autowired
    IFactureService ss;

    @MockBean
	private ReglementRepository pr;
    
     @MockBean
     private FactureRepository SR;
   
   
    Reglement p1=new Produit(1L,"p1",2.400,3.400,"True",new Date(),null);
    Reglement p2=new Produit(1L,"p1",2.600,3.600,"True",new Date(),null);
    Facture S=new Stock(1L,"S1",5.6,7.6,new Date(),new Date(),True,null,null,null);
    
   
    
    @Test 
    public void retrieveAllProduitsTest() {
    	when(pr.findAll()).thenReturn(Stream
    			.of(p1,p2)
    			.collect(Collectors.toList()));
    	assertEquals(2,ps.retrieveAllProduits().size());
    	System.out.println("Retrieve  All produits works !");
    }
    
    
    @Test
	public void addProduitTest() {
    	when(pr.save(p1)).thenReturn(p1);
    	assertNotNull(p1);
		assertEquals(p1, ps.addProduit(p1));
    	System.out.println("Add produits works !");
	}

@Test
public void DeleteProduitTest() {
	pr.save(p1);
	ps.deleteProduit(p1.getIdProduit());
	verify(pr, times(1)).deleteById(p1.getIdProduit());
	System.out.println("Delete produits works !");
	
}

@Test
public void retrieveProduitTest() {
	when(pr.findById(p1.getIdProduit())).thenReturn(Optional.of(p1));
	assertEquals(p1, ps.retrieveProduit(p1.getIdProduit()));
	System.out.println("Retrieve produit works !");
}


@Test 
public void UpdatePorduitTest() {
	when(pr.save(p1)).thenReturn(p1);
	assertNotNull(p1);
	assertEquals(p1, ps.updateProduit(p1));

	System.out.println("update produits works !");
}




}
*/
