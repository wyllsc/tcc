package br.com.csempreendimentos.sgi.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import br.com.csempreendimentos.sgi.model.Construtora;
import br.com.csempreendimentos.sgi.model.Empreendimento;
import br.com.csempreendimentos.sgi.model.Imovel;
import br.com.csempreendimentos.sgi.model.Reserva;
import br.com.csempreendimentos.sgi.model.Usuario;
import br.com.csempreendimentos.sgi.model.enums.EnumStatusVenda;
import br.com.csempreendimentos.sgi.util.HibernateUtil;

public class GerenteDAO extends DAO implements Serializable
{
   private static final long serialVersionUID = 1L;
   protected Session session;
   
   // CORRETOR
   public void saveUsuario(Usuario usuario)
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
   public void updateUsuario(Usuario usuario)
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
   public List<Usuario> listarTodosUsuarios(Usuario usuario)
   {
      List<Usuario> list = new ArrayList<Usuario>();
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         Query query =
            session
               .createQuery("from Usuario u where u.tipoUsuario.id = :pTipoUsuario order by u.login ");
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
   public void excluirUsuario(Usuario usuario)
   {
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         session.getTransaction().begin();
         session.delete(usuario);
         session.getTransaction().commit();
//         Query query = session.createQuery("delete from Usuario u where u.id = :pId");
//         query.setParameter("pId", usuario.getId());
//
//         query.executeUpdate();
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

   // CONSTRUTORA
   @SuppressWarnings("unchecked")
   public List<Construtora> listarConstrutora()
   {
      List<Construtora> list = new ArrayList<Construtora>();
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         Query query = session.createQuery("from Construtora order by nomeConstrutora ");
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

   public void excluirConstrutora(Construtora construtora)
   {
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         session.getTransaction().begin();
         session.delete(construtora);
         session.getTransaction().commit();

         // Query query =
         // session.createQuery("delete from Construtora c, Empreendimento e  INNER JOIwhere c.id = :pId");
         // query.setParameter("pId", construtora.getId());

         // DELETE ct,ep FROM csempreendimentos.tb_construtora ct
         // INNER JOIN csempreendimentos.tb_empreendimento ep
         // ON(ct.ID_CONSTRUTORA = ep.ID_CONSTRUTORA)
         // WHERE ct.ID_CONSTRUTORA = 6

         // query.executeUpdate();
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

   public void saveConstrutora(Construtora construtora)
   {
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         session.getTransaction().begin();
         session.save(construtora);
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

   public void updateConstrutora(Construtora construtora)
   {
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         session.getTransaction().begin();
         session.update(construtora);
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
   public List<Construtora> listarConstrutoraVinculada(Long construtora)
   {
      List<Construtora> list = new ArrayList<Construtora>();
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         Query query = session.createQuery("" +
               " SELECT c" +
               " FROM Construtora c, ConstrutoraEmpreendimento ce " +
               " WHERE ce.construtora = :pConstrutora " +
               " AND c.id = ce.construtora " +
               " ORDER BY c.id ");
         query.setParameter("pConstrutora", construtora);

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
   
   // EMPREENDIMENTO
   @SuppressWarnings("unchecked")
   public List<Empreendimento> listarEmpreendimento(Long construtora)
   {
      List<Empreendimento> list = new ArrayList<Empreendimento>();
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         Query query = session.createQuery("" +
         		" SELECT e" +
         		" FROM Empreendimento e, ConstrutoraEmpreendimento ce " +
         		" WHERE ce.construtora = :pConstrutora " +
         		" AND e.id = ce.empreendimento " +
         		" ORDER BY e.nomeEmpreendimento ");
         query.setParameter("pConstrutora", construtora);

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

   public void excluirEmpreendimento(Empreendimento empreendimento)
   {
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         session.getTransaction().begin();
         session.delete(empreendimento);
         session.getTransaction().commit();

//         Query query = session.createQuery("delete from Empreendimento e where e.id = :pId");
//         query.setParameter("pId", empreendimento.getId());
//         
//         query.executeUpdate();
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

   public void updateEmpreendimento(Empreendimento empreendimento)
   {
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         session.getTransaction().begin();
         session.update(empreendimento);
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

   public void saveEmpreendimento(Empreendimento empreendimento)
   {
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         session.getTransaction().begin();
         session.save(empreendimento);
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
   public List<Empreendimento> listarEmpreedimentoVinculada(Long empreendimento)
   {
      List<Empreendimento> list = new ArrayList<Empreendimento>();
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         Query query = session.createQuery("" +
               " SELECT e" +
               " FROM Empreendimento e, EmpreendimentoImovel ei " +
               " WHERE ei.empreendimento = :pEmpreendimento " +
               " AND e.id = ei.empreendimento " +
               " ORDER BY e.id ");
         query.setParameter("pEmpreendimento", empreendimento);

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
   
   // IMÓVEL
   @SuppressWarnings("unchecked")
   public List<Imovel> listarImoveis(Long empreendimento)
   {
      List<Imovel> list = new ArrayList<Imovel>();
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         Query query = session.createQuery("" +
         		" SELECT i " +
         		" FROM Imovel i, EmpreendimentoImovel e " +
         		" WHERE e.empreendimento = :pEmpreendimento " +
         		" AND i.id = e.imovel " +
         		" ORDER BY i.localizacaoImovel ");
         query.setParameter("pEmpreendimento", empreendimento);

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
   public List<Imovel> listarReservas()
   {
      List<Imovel> list = new ArrayList<Imovel>();
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         Query query = session.createQuery(" SELECT imovel FROM Imovel imovel, Reserva reserva WHERE reserva.imovel.id = imovel.id " +
                                           " AND reserva.stReserva != :pStatus ");
         query.setParameter("pStatus", EnumStatusVenda.RECUSADO.getCodigo());
         
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
   public List<Imovel> listarImoveisReserva(Long empreendimento)
   {
      List<Imovel> list = new ArrayList<Imovel>();
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         Query query = session.createQuery("" +
                  " SELECT i " +
                  " FROM Imovel i, EmpreendimentoImovel e, Reserva r" +
                  " WHERE e.empreendimento = :pEmpreendimento " +
                  " AND i.id = e.imovel " +
                  " AND i.id <> r.imovel.id" +
                  " ORDER BY i.localizacaoImovel ");
         query.setParameter("pEmpreendimento", empreendimento);
         
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
   public List<Imovel> listarImoveisTodos()
   {
      List<Imovel> list = new ArrayList<Imovel>();
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         Query query = session.createQuery("" +
                  " SELECT i " +
                  " FROM Imovel i, EmpreendimentoImovel e" +
                  " WHERE i.id = e.imovel " +
                  " ORDER BY i.localizacaoImovel ");
         
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

   public void saveImovel(Imovel imovel)
   {
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         session.getTransaction().begin();
         session.save(imovel);
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
   
   public void updateImovel(Imovel imovel)
   {
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         session.getTransaction().begin();
         session.update(imovel);
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
   
   public void excluirImovel(Imovel imovel)
   {
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         session.getTransaction().begin();
         session.delete(imovel);
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


   // DELETAR RLS
   
//   public void excluirConstrutoraRls(Construtora construtora)
//   {
//      session = HibernateUtil.getSessionfactory().openSession();
//      try
//      {
//         session.getTransaction().begin();
//         
//         Query query = session.createQuery("delete from EmpreendimentoImovel e where e.imovel = :pId");
//         query.setParameter("pId", construtora.getId());
//         
//         query.executeUpdate();
//      }
//      catch (HibernateException ex)
//      {
//         ex.printStackTrace();
//      }
//      finally
//      {
//         session.close();
//      }
//   }
   public void excluirRlEmpreendimentoImovel(Imovel imovel)
   {
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         session.getTransaction().begin();
         
         Query query = session.createQuery("delete from EmpreendimentoImovel e where e.imovel = :pId");
         query.setParameter("pId", imovel.getId());
         
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
   public void excluirRlConstrutoraEmpreendimento(Empreendimento empreendimento)
   {
      session = HibernateUtil.getSessionfactory().openSession();
      try
      {
         session.getTransaction().begin();
         
         Query query = session.createQuery("delete from ConstrutoraEmpreendimento e where e.empreendimento = :pId");
         query.setParameter("pId", empreendimento.getId());
         
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