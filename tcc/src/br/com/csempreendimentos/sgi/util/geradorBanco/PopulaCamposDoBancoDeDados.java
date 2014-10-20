package br.com.csempreendimentos.sgi.util.geradorBanco;

import java.io.Serializable;
import java.text.ParseException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import br.com.csempreendimentos.sgi.model.Bairro;
import br.com.csempreendimentos.sgi.model.Endereco;
import br.com.csempreendimentos.sgi.model.Telefone;
import br.com.csempreendimentos.sgi.model.TipoUsuario;
import br.com.csempreendimentos.sgi.model.Usuario;
import br.com.csempreendimentos.sgi.util.HibernateUtil;

public class PopulaCamposDoBancoDeDados implements Serializable
{
   private static final long serialVersionUID = 1L;


   private TipoUsuario tipoUsuario = new TipoUsuario();
   private Endereco endereco = new Endereco();
   private Telefone telefone = new Telefone();
   private Usuario usuario = new Usuario();
   private Bairro bairro = new Bairro();

   public static void main(String[] args) throws ParseException
   {
      new PopulaCamposDoBancoDeDados().populaTipoUsuario1();
      new PopulaCamposDoBancoDeDados().populaTipoUsuario2();
      new PopulaCamposDoBancoDeDados().populaTipoUsuario3();
      new PopulaCamposDoBancoDeDados().populaTipoUsuario4();

      new PopulaCamposDoBancoDeDados().populaBairro1();
      new PopulaCamposDoBancoDeDados().populaBairro2();
      new PopulaCamposDoBancoDeDados().populaBairro3();
      
      new PopulaCamposDoBancoDeDados().populaEndereco1();
      new PopulaCamposDoBancoDeDados().populaEndereco2();
      new PopulaCamposDoBancoDeDados().populaEndereco3();
      new PopulaCamposDoBancoDeDados().populaEndereco4();

      new PopulaCamposDoBancoDeDados().populaTelefone1();
      new PopulaCamposDoBancoDeDados().populaTelefone2();
      new PopulaCamposDoBancoDeDados().populaTelefone3();
      new PopulaCamposDoBancoDeDados().populaTelefone4();

      new PopulaCamposDoBancoDeDados().populaUsuario1();
      new PopulaCamposDoBancoDeDados().populaUsuario2();
      new PopulaCamposDoBancoDeDados().populaUsuario3();
      new PopulaCamposDoBancoDeDados().populaUsuario4();
      
      new PopulaCamposDoBancoDeDados().populaConstrutora1();
      new PopulaCamposDoBancoDeDados().populaConstrutora2();
   }

   private void populaTipoUsuario1()
   {
      Session session = HibernateUtil.getSessionfactory().openSession();

      try
      {
         session.getTransaction().begin();
         tipoUsuario.setId((long) 1);
         tipoUsuario.setNome("ADM GERAL");
         session.saveOrUpdate(tipoUsuario);
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

   private void populaTipoUsuario2()
   {
      Session session = HibernateUtil.getSessionfactory().openSession();

      try
      {
         session.getTransaction().begin();
         tipoUsuario.setId((long) 2);
         tipoUsuario.setNome("GERENTE");
         session.saveOrUpdate(tipoUsuario);
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

   private void populaTipoUsuario3()
   {
      Session session = HibernateUtil.getSessionfactory().openSession();

      try
      {
         session.getTransaction().begin();
         tipoUsuario.setId((long) 3);
         tipoUsuario.setNome("AUX ADM");
         session.saveOrUpdate(tipoUsuario);
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

   private void populaTipoUsuario4()
   {
      Session session = HibernateUtil.getSessionfactory().openSession();

      try
      {
         session.getTransaction().begin();
         tipoUsuario.setId((long) 4);
         tipoUsuario.setNome("CORRETOR");
         session.saveOrUpdate(tipoUsuario);
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

   private void populaEndereco1()
   {
      Session session = HibernateUtil.getSessionfactory().openSession();

      try
      {
         session.getTransaction().begin();
         endereco.getBairro().setId(new Long(1));
         endereco.setCep("72.152-315");
         endereco.setComplemento("Casa de Fundos");
         endereco.setEndereco("QNL 23 Bloco E Casa 07");
         session.saveOrUpdate(endereco);
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

   private void populaEndereco2()
   {
      Session session = HibernateUtil.getSessionfactory().openSession();

      try
      {
         session.getTransaction().begin();
         endereco.getBairro().setId(new Long(2));
         endereco.setCep("72.154-862");
         endereco.setComplemento("Edificio Andrade");
         endereco.setEndereco("SQ 502 Quadra 3 lote 3 Apartamento 302");
         session.saveOrUpdate(endereco);
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

   private void populaEndereco3()
   {
      Session session = HibernateUtil.getSessionfactory().openSession();

      try
      {
         session.getTransaction().begin();
         endereco.getBairro().setId(new Long(3));
         endereco.setCep("72.154-540");
         endereco.setComplemento("Edificio Ibirapuera");
         endereco.setEndereco("SQ 875 Quadra 3 lote 5 Apartamento 302");
         session.saveOrUpdate(endereco);
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

   private void populaEndereco4()
   {
      Session session = HibernateUtil.getSessionfactory().openSession();

      try
      {
         session.getTransaction().begin();
         endereco.getBairro().setId(new Long(1));
         endereco.setCep("72.154-879");
         endereco.setComplemento("");
         endereco.setEndereco("QNM 31 Conj A Casa 12");
         session.saveOrUpdate(endereco);
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

   private void populaBairro1()
   {
      Session session = HibernateUtil.getSessionfactory().openSession();
      
      try
      {
         session.getTransaction().begin();
         bairro.setDescricaoBairro("Taguatinga");
         session.saveOrUpdate(bairro);
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
   private void populaBairro2()
   {
      Session session = HibernateUtil.getSessionfactory().openSession();
      
      try
      {
         session.getTransaction().begin();
         bairro.setDescricaoBairro("Ceilandia");
         session.saveOrUpdate(bairro);
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
   private void populaBairro3()
   {
      Session session = HibernateUtil.getSessionfactory().openSession();
      
      try
      {
         session.getTransaction().begin();
         bairro.setDescricaoBairro("Aguas CLaras");
         session.saveOrUpdate(bairro);
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

   private void populaTelefone1()
   {
      Session session = HibernateUtil.getSessionfactory().openSession();

      try
      {
         session.getTransaction().begin();
         telefone.setDddCoorporativo(61);
         telefone.setDddPreferencial(61);
         telefone.setDddRecado(61);
         telefone.setTelefoneCoorporativo(34755079);
         telefone.setTelefonePreferencial(82325976);
         telefone.setTelefoneRecado(38772917);
         session.saveOrUpdate(telefone);
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

   private void populaTelefone2()
   {
      Session session = HibernateUtil.getSessionfactory().openSession();

      try
      {
         session.getTransaction().begin();
         telefone.setDddCoorporativo(61);
         telefone.setDddPreferencial(61);
         telefone.setDddRecado(61);
         telefone.setTelefoneCoorporativo(44444444);
         telefone.setTelefonePreferencial(99999999);
         telefone.setTelefoneRecado(22222222);
         session.saveOrUpdate(telefone);
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

   private void populaTelefone3()
   {
      Session session = HibernateUtil.getSessionfactory().openSession();

      try
      {
         session.getTransaction().begin();
         telefone.setDddCoorporativo(61);
         telefone.setDddPreferencial(61);
         telefone.setTelefoneCoorporativo(33333333);
         telefone.setTelefonePreferencial(99999999);
         session.saveOrUpdate(telefone);
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

   private void populaTelefone4()
   {
      Session session = HibernateUtil.getSessionfactory().openSession();

      try
      {
         session.getTransaction().begin();
         telefone.setDddRecado(61);
         telefone.setTelefoneRecado(38772917);
         session.saveOrUpdate(telefone);
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

   private void populaUsuario1() throws ParseException
   {
      Session session = HibernateUtil.getSessionfactory().openSession();

      try
      {
         session.getTransaction().begin();

         Query query =
            session
               .createSQLQuery("INSERT INTO csempreendimentos.tb_usuario "
                  + "(`ID_USUARIO`, `DS_CPF`, `DS_SENHA`, `DT_NASCIMENTO`, `DS_EMAIL`, `DS_LOGIN`, `DS_NOME`, `DS_SEXO`, `ID_ENDERECO`, `ID_TELEFONE`, `ID_TIPO`)"
                  + "VALUES "
                  + "(1, '11111111111', '0801', '1991/01/08', 'admin@gmail.com', 'ADMIN', 'Wylliam SantClair', 'M', 1, 1, 1)");

         query.executeUpdate();

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

   private void populaUsuario2() throws ParseException
   {
      Session session = HibernateUtil.getSessionfactory().openSession();

      try
      {
         session.getTransaction().begin();

         Query query =
            session
               .createSQLQuery("INSERT INTO csempreendimentos.tb_usuario "
                  + "(`ID_USUARIO`, `DS_CPF`, `DS_SENHA`, `DT_NASCIMENTO`, `DS_EMAIL`, `DS_LOGIN`, `DS_NOME`,`DS_SEXO`, `ID_ENDERECO`, `ID_TELEFONE`, `ID_TIPO`)"
                  + "VALUES "
                  + "(2, '22222222222', '0801', '1991/01/08', 'sonia@gmail.com', 'GERENTE', 'Sonia Alves','F', 2, 2, 2)");

         query.executeUpdate();
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

   private void populaUsuario3() throws ParseException
   {
      Session session = HibernateUtil.getSessionfactory().openSession();

      try
      {
         session.getTransaction().begin();
         Query query =
            session
               .createSQLQuery("INSERT INTO csempreendimentos.tb_usuario "
                  + "(`ID_USUARIO`, `DS_CPF`, `DS_SENHA`, `DT_NASCIMENTO`, `DS_EMAIL`, `DS_LOGIN`, `DS_NOME`,`DS_SEXO`, `ID_ENDERECO`, `ID_TELEFONE`, `ID_TIPO`)"
                  + "VALUES "
                  + "(3, '33333333333', '0801', '1991/01/08', 'fernanda@gmail.com', 'AUXILIAR', 'Fernanda Rodrigues','F', 3, 3, 3)");

         query.executeUpdate();
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

   private void populaUsuario4() throws ParseException
   {
      Session session = HibernateUtil.getSessionfactory().openSession();

      try
      {
         session.getTransaction().begin();
         Query query =
            session
               .createSQLQuery("INSERT INTO csempreendimentos.tb_usuario "
                  + "(`ID_USUARIO`, `DS_CPF`, `DS_SENHA`, `DT_NASCIMENTO`, `DS_EMAIL`, `DS_LOGIN`, `DS_NOME`,`DS_SEXO`, `ID_ENDERECO`, `ID_TELEFONE`, `ID_TIPO`)"
                  + "VALUES "
                  + "(4, '44444444444', '0801', '1991/01/08', 'amanda@gmail.com', 'CORRETOR', 'Amanda Gilbrth','F', 4, 4, 4)");

         query.executeUpdate();
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

   private void populaConstrutora1() throws ParseException
   {
      Session session = HibernateUtil.getSessionfactory().openSession();
      
      try
      {
         session.getTransaction().begin();
         Query query =
                  session.createSQLQuery("INSERT INTO csempreendimentos.tb_construtora " +
                  		"(`ID_CONSTRUTORA`,`DS_EMAIL`,`DS_NOME_CONSTRUTORA`,`DS_NOME_GERENTE`,`ID_ENDERECO`,`ID_TELEFONE`) " +
                  		" VALUES " +
                  		"(1,'direcional@direcional.com.br','DIRECIONAL ENGENHARIA LTDA','Lacy de Moura Guilbetrh',3,3)");
         
         query.executeUpdate();
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

   private void populaConstrutora2() throws ParseException
   {
      Session session = HibernateUtil.getSessionfactory().openSession();
      
      try
      {
         session.getTransaction().begin();
         Query query =
                  session.createSQLQuery("INSERT INTO csempreendimentos.tb_construtora " +
                  		"(`ID_CONSTRUTORA`,`DS_EMAIL`,`DS_NOME_CONSTRUTORA`,`DS_NOME_GERENTE`,`ID_ENDERECO`,`ID_TELEFONE`) " +
                  		" VALUES " +
                  		"(2,'mrv@mrv.com.br','MRV ENGENHARIA LTDA','Allonso Alberto',2,2)");
         
         query.executeUpdate();
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

   // GETTERS E SETTERS

   public TipoUsuario getTipoUsuario()
   {
      return tipoUsuario;
   }

   public void setTipoUsuario(TipoUsuario tipoUsuario)
   {
      this.tipoUsuario = tipoUsuario;
   }

   public Endereco getEndereco()
   {
      return endereco;
   }

   public void setEndereco(Endereco endereco)
   {
      this.endereco = endereco;
   }

   public Telefone getTelefone()
   {
      return telefone;
   }

   public void setTelefone(Telefone telefone)
   {
      this.telefone = telefone;
   }

   public Usuario getUsuario()
   {
      return usuario;
   }

   public void setUsuario(Usuario usuario)
   {
      this.usuario = usuario;
   }

   public Bairro getBairro()
   {
      return bairro;
   }

   public void setBairro(Bairro bairro)
   {
      this.bairro = bairro;
   }
}