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
import br.com.csempreendimentos.sgi.model.Venda;
import br.com.csempreendimentos.sgi.util.HibernateUtil;

public class AuxiliarAdministrativoDAO extends DAO implements Serializable
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
   public List<Reserva> listarReserva()
   {
      List<Reserva> list = new ArrayList<Reserva>();
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         Query query = session.createQuery("" +
         		" FROM Reserva r ORDER BY r.cliente.nome ");
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

   public List<Venda>verificaVenda(Reserva reserva)
   {
      List<Venda> c = new ArrayList<Venda>();
      session = HibernateUtil.getSessionfactory().openSession();
      Query query = session.createQuery("" +
      		   " FROM Venda venda" +
               " WHERE venda.reserva.id = :pReserva ");
      
      query.setParameter("pReserva", reserva.getId());
      c = query.list();
      session.close();
      return c;
   }
   
}