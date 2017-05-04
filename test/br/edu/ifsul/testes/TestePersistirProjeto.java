package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Colaborador;
import br.edu.ifsul.modelo.Permissao;
import br.edu.ifsul.modelo.Projeto;
import br.edu.ifsul.modelo.Setor;
import br.edu.ifsul.modelo.Usuario;
import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jorge
 */
public class TestePersistirProjeto {
    
  EntityManagerFactory emf;
    EntityManager em;
    

    public TestePersistirProjeto() {

    }

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("TA-2017-1-6N1-ModelPU");
        em = emf.createEntityManager();
    }
    @After
    public void tearDown() {
        em.close();
        emf.close();
    }
    @Test
    public void testePersistirPais() {
        boolean exception = false;
        try {
            Projeto obj = new Projeto();
            obj.setNome("Projeto de Cursos sobre Java");
            obj.setDescricao("Projeto de cursos sobre desenvolvimento em Java");
            obj.setSetor(em.find(Setor.class, 1));
            obj.setAtivo(true);
            obj.setInicio(Calendar.getInstance());
            obj.setFim(Calendar.getInstance());
            Colaborador c = new Colaborador();
            c.setUsuario(em.find(Usuario.class, 1));
            c.setCargaHoraria(10);
            c.setGestor(true);
            obj.adicionarColaborador(c);
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            exception = true;
            
        }
        // verificando se o resultado é igual ao esperado
        Assert.assertEquals(false, exception);
    }
    
}
