package br.com.csempreendimentos.sgi.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import br.com.csempreendimentos.sgi.model.Cliente;
import br.com.csempreendimentos.sgi.model.Construtora;
import br.com.csempreendimentos.sgi.model.Empreendimento;
import br.com.csempreendimentos.sgi.model.Imovel;
import br.com.csempreendimentos.sgi.model.Reserva;
import br.com.csempreendimentos.sgi.model.Usuario;
import br.com.csempreendimentos.sgi.util.HibernateUtil;

public class CorretorDAO extends DAO implements Serializable
{
   private static final long serialVersionUID = 1L;

   protected Session session;

   public void saveCliente(Cliente cliente)
   {
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         session.getTransaction().begin();
         session.save(cliente);
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

   public void updateCliente(Cliente cliente)
   {
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         session.getTransaction().begin();
         session.update(cliente);
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
   public List<Cliente> listarClientesDoCorretor(Usuario usuario)
   {
      List<Cliente> list = new ArrayList<Cliente>();
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         Query query = session.createQuery("FROM Cliente c WHERE c.usuario.id = :pUsuario ORDER BY c.nome ");
         query.setParameter("pUsuario", usuario.getId());
         
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

   public void excluirCliente(Cliente cliente)
   {
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         session.getTransaction().begin();
         session.delete(cliente);
         session.getTransaction().commit();      }
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
   public List<Reserva> listarReserva(Usuario usuario)
   {
      List<Reserva> list = new ArrayList<Reserva>();
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         Query query = session.createQuery("" +
         		" FROM Reserva r " +
         		" WHERE r.usuario.id = :pUsuario " +
         		" ORDER BY r.cliente.nome ");
         
         query.setParameter("pUsuario", usuario.getId());
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

   public Cliente buscaCliente(Cliente cliente)
   {
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         Query query = session.createQuery("from Cliente c where c.id = :pId");
         query.setParameter("pId", cliente.getId());

         cliente = (Cliente) query.uniqueResult();
      }
      catch (HibernateException ex)
      {
         ex.printStackTrace();
      }
      finally
      {
         session.close();
      }
      return cliente;
   }

//   @SuppressWarnings("unchecked")
//   public List<Object> listarReservaUsuario(Usuario usuario)
//   {
//      List<Object> list = new ArrayList<Object>();
//      session = HibernateUtil.getSessionfactory().openSession();
//      try
//      {
//         
////         Query query = session.createQuery("SELECT r, emp, const " +
////                  " FROM Usuario u, " +
////                       " Imovel i, " +
////                       " Cliente c, " +
////                       " Reserva r, " +
////                       " EmpreendimentoImovel rl, " +
////                       " Empreendimento emp, " +
////                       " ConstrutoraEmpreendimento rlConstrutora, " +
////                       " Construtora const " +
////                  " WHERE u.id = :pUsuario " +
////                  " AND i.id = rl.imovel " +
////                  " AND rl.empreendimento = emp.id " +
////                  " AND rlConstrutora.empreendimento = rl.empreendimento " +
////                  " AND rlConstrutora.construtora = const.id " +
////                  " ORDER BY r.cliente.nome ");
//         Query query = session.createSQLQuery("");
//         
//         
//         
//         query.setParameter("pUsuario", usuario.getId());
//         list = query.list();
//      }
//      catch (HibernateException ex)
//      {
//         ex.printStackTrace();
//      }
//      finally
//      {
//         session.close();
//      }
//      return list;
//   }

   public Empreendimento buscaEmpreendimentoPorImovel(Imovel imovel)
   {
      Empreendimento emp = new Empreendimento();
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         
         Query query = session.createQuery("SELECT emp" +
                  " FROM Empreendimento emp, EmpreendimentoImovel rl " +
                  " WHERE rl.empreendimento =  emp.id" +
                  " AND rl.imovel = :pImovel ");
         
         query.setParameter("pImovel", imovel.getId());
         emp =  (Empreendimento) query.uniqueResult();

      }
      catch (HibernateException ex)
      {
         ex.printStackTrace();
      }
      finally
      {
         session.close();
      }
      return emp;
   }

   public Construtora buscaConstrutoraPorEmpreendimento(Empreendimento empreendimento)
   {
      Construtora c = new Construtora();
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         
         Query query = session.createQuery("SELECT cons " +
                  " FROM Construtora cons, ConstrutoraEmpreendimento rl" +
                  " WHERE rl.construtora = cons.id " +
                  " and rl.empreendimento= :pEmp ");
         
         query.setParameter("pEmp", empreendimento.getId());
         c = (Construtora) query.uniqueResult();
      }
      catch (HibernateException ex)
      {
         ex.printStackTrace();
      }
      finally
      {
         session.close();
      }
      return c;
   }

   
}