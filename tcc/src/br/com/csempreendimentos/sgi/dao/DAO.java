package br.com.csempreendimentos.sgi.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import br.com.csempreendimentos.sgi.model.Bairro;
import br.com.csempreendimentos.sgi.model.Cliente;
import br.com.csempreendimentos.sgi.util.HibernateUtil;

public class DAO implements Serializable
{
   private static final long serialVersionUID = 8513192532903117113L;
   protected Session session;

   public void saveGenerico(Object entidade)
   {
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         session.getTransaction().begin();
         session.save(entidade);
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

   public void updateGenerico(Object entidade)
   {
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         session.getTransaction().begin();
         session.update(entidade);
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

   public void excluirGenerico(Object entidade)
   {
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         session.getTransaction().begin();
         session.delete(entidade);
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
   public List<Bairro> buscaBairro()
   {
      List<Bairro> list = new ArrayList<Bairro>();
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         session.getTransaction().begin();

         Query query = session.createQuery("from Bairro b order by b.descricaoBairro ASC");
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

   @SuppressWarnings("unchecked")
   public List<Cliente> buscaCliente()
   {
      List<Cliente> list = new ArrayList<Cliente>();
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         session.getTransaction().begin();
         
         Query query = session.createQuery("from Cliente c order by c.nome ASC");
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

   @SuppressWarnings("unchecked")
   public List<String> buscaRegiao()
   {
      List<String> list = new ArrayList<String>();
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         session.getTransaction().begin();
         
         Query query = session.createQuery("select DISTINCT(e.regiao) from Empreendimento e order by e.regiao ASC");
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
   
}