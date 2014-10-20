package br.com.csempreendimentos.sgi.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import br.com.csempreendimentos.sgi.model.Usuario;
import br.com.csempreendimentos.sgi.util.HibernateUtil;

public class UsuarioDAO extends DAO implements Serializable
{
   private static final long serialVersionUID = 1L;

   protected Session session;

   public void save(Usuario usuario)
   {
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         session.getTransaction().begin();
         session.save(usuario);
         session.getTransaction().commit();
      }
      catch (HibernateException ex)
      {
         ex.printStackTrace();
      }
      finally
      {
         session.close();
      }
   }

   @SuppressWarnings("unchecked")
   public List<Usuario> listarTodos()
   {
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         return session.createCriteria(Usuario.class, "usuario").list();
      }
      catch (HibernateException ex)
      {
         ex.printStackTrace();
      }
      finally
      {
         session.close();
      }
      return null;
   }

   public Usuario verificaUsuario(Usuario usuario)
   {
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         session.getTransaction().begin();

         Query query = session.createQuery("from Usuario u where u.login = :pLogin and u.senha = :pSenha");
         query.setParameter("pLogin", usuario.getLogin());
         query.setParameter("pSenha", usuario.getSenha());
         
         usuario = (Usuario) query.uniqueResult();
      }
      catch (HibernateException ex)
      {
         ex.printStackTrace();
      }
      finally
      {
         session.close();
      }
      return usuario;
   }

   public Usuario buscaLogin(Usuario usuario)
   {
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         session.getTransaction().begin();

         Query query = session.createQuery("" +
                       "from Usuario u where u.cpf = :pCpf " +
                       "and u.dataNascimento = :pDtNasci " +
                       "and u.login = :pLogin");
         
         query.setParameter("pCpf", usuario.getCpf());
         query.setParameter("pDtNasci", usuario.getDataNascimento());
         query.setParameter("pLogin", usuario.getLogin());
         
         usuario = (Usuario) query.uniqueResult();
      }
      catch (HibernateException ex)
      {
         ex.printStackTrace();
      }
      finally
      {
         session.close();
      }
      return usuario;
   }
   
   public boolean alterarFoto(Object entidade)
   {
      session = HibernateUtil.getSessionfactory().openSession();
      boolean ft;
      try
      {
         session.getTransaction().begin();
         session.update(entidade);
         session.getTransaction().commit();
         ft = true;
      }
      catch (HibernateException ex)
      {
         ex.printStackTrace();
         ft = false;
      }
      finally
      {
         session.close();
      }
      return ft;
   }
}