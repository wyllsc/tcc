package br.com.csempreendimentos.sgi.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import br.com.csempreendimentos.sgi.model.Usuario;
import br.com.csempreendimentos.sgi.util.HibernateUtil;

public class AdmGeralDAO implements Serializable
{
   private static final long serialVersionUID = 1L;

   protected Session session;

   public void saveGerente(Usuario usuario)
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

   public void updateGerente(Usuario usuario)
   {
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         session.getTransaction().begin();
         session.update(usuario);
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
   public void saveAuxAdm(Usuario usuario)
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
   
   public void updateAuxAdm(Usuario usuario)
   {
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         session.getTransaction().begin();
         session.update(usuario);
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
   public List<Usuario> listarTodos(Usuario usuario)
   {
      List<Usuario> list = new ArrayList<Usuario>();
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         Query query = session.createQuery("from Usuario u where u.tipoUsuario.id = :pTipoUsuario order by u.login ");
         query.setParameter("pTipoUsuario", usuario.getTipoUsuario().getId());
         
         list = query.list();
      }
      catch (HibernateException ex)
      {
         ex.printStackTrace();
      }
      finally
      {
         session.close();
      }
      return list;
   }

   public void excluir(Usuario usuario)
   {
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         session.getTransaction().begin();
         
         Query query = session.createQuery("delete from Usuario u where u.id = :pId");
         query.setParameter("pId", usuario.getId());
         
         query.executeUpdate();
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
}