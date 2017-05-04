package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Permissao;
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
public class TestePersistirUsuario {
    
  EntityManagerFactory emf;
    EntityManager em;
    

    public TestePersistirUsuario() {

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
            Usuario obj = new Usuario();
            obj.setNome("Jorge Bavaresco");
            obj.setEmail("jlbavaresco@gmail.com");
            obj.setNascimento(Calendar.getInstance());
            obj.setUsuario("jorge");
            obj.setSenha("123456");
            obj.setSetor(em.find(Setor.class, 1));
            Permissao p = em.find(Permissao.class, "ADMINISTRADOR");
            obj.getPermissoes().add(p);
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
