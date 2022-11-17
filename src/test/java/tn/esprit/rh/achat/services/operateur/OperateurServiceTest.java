package tn.esprit.rh.achat.services.operateur;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.IOperateurService;
import tn.esprit.rh.achat.services.OperateurServiceImpl;
import org.junit.jupiter.api.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
@Slf4j
public class OperateurServiceTest  {
	@Mock
	private OperateurRepository or;
	@Autowired
	private OperateurRepository ior;
	@InjectMocks
	OperateurServiceImpl os;
	@Autowired
	IOperateurService ios;

	Operateur operateur = Operateur.builder().idOperateur(1L).nom("salah").prenom("sleh").password("111").build();
	List<Operateur> operateurs = new ArrayList<Operateur>(){
		{
			add(Operateur.builder().idOperateur(1L).nom("mohamed").prenom("sleh").password("222").build());
			add(Operateur.builder().idOperateur(2L).nom("mourad").prenom("mabrouk").password("333").build());
		}
	};
	//Mockito
	@Test
	@Order(1)
	public void addOperateur() {
		Mockito.when(or.save(Mockito.any(Operateur.class))).thenReturn(operateur);
		Operateur operateur1 = os.addOperateur(operateur);
		assertAll("addOperateur",
				() -> assertEquals(operateur1.getNom(), operateur.getNom()),
				() -> assertEquals(operateur1.getPrenom(), operateur.getPrenom()),
				() -> assertEquals(operateur1.getPassword(), operateur.getPassword())
		);
		verify(or, times(1)).save(Mockito.any(Operateur.class));
	}
	@Test
	@Order(2)
	public void retriveAllOperateur() {
		Mockito.when(or.findAll()).thenReturn(operateurs);
		List<Operateur> operateurs1 = os.retrieveAllOperateurs();
		assertEquals(operateurs1.size(), operateurs.size());
		verify(or, times(1)).findAll();
	}
	@Test
	@Order(3)
	public  void updateOperateur() {
		operateur.setNom("updated");
		Mockito.when(or.save(Mockito.any(Operateur.class))).thenReturn(operateur);
		Operateur operateur1 = os.updateOperateur(operateur);
		assertEquals(operateur1.getNom(),"updated" );
		verify(or, times(1)).save(Mockito.any(Operateur.class));
	}
	@Test
	@Order(4)
	public  void retriveOperateur() {
		Mockito.when(or.findById(Mockito.anyLong())).thenReturn(Optional.of(operateur));
		Operateur operateur1 = os.retrieveOperateur(1L);
		assertAll("retriveOperateur",
				() -> assertEquals(operateur1.getNom(), operateur.getNom()),
				() -> assertEquals(operateur1.getPrenom(), operateur.getPrenom()),
				() -> assertEquals(operateur1.getPassword(), operateur.getPassword())
		);
		verify(or, times(1)).findById(Mockito.anyLong());
	}


	//Junit
	static List<Operateur> savedOperateurs = new ArrayList<Operateur>();

	@Test
	@Order(5)
	public void addOp() {
		operateurs.forEach(operateur -> {
			Operateur o = ios.addOperateur(operateur);
			assertAll("add operator",
					() -> assertNotNull(o.getIdOperateur()),
					() -> assertEquals(o.getNom(), operateur.getNom()),
					() -> assertEquals(o.getPrenom(), operateur.getPassword())
			);
		});
		savedOperateurs = ios.retrieveAllOperateurs();
		assertEquals(savedOperateurs.size(), operateurs.size());
		log.info("operator added successfully");
	}


	@Test
	@Order(6)
	public void deleteop() {
		savedOperateurs.forEach(operateur -> {
			ios.deleteOperateur(operateur.getIdOperateur());
		});
		assertEquals(0, ios.retrieveAllOperateurs().size());
		log.info("operator deleted successfully");
	}

	@Test
	@Order(7)
	public void retrieveop() {
		savedOperateurs.forEach(operateur -> {
			ios.retrieveOperateur(operateur.getIdOperateur());
		});
		assertEquals(operateur, ios.retrieveOperateur(operateur.getIdOperateur()));
		log.info("operator deleted successfully");
	}


}