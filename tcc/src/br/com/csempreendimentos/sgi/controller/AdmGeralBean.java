package br.com.csempreendimentos.sgi.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import br.com.csempreendimentos.sgi.dao.AdmGeralDAO;
import br.com.csempreendimentos.sgi.model.TipoUsuario;
import br.com.csempreendimentos.sgi.model.Usuario;
import br.com.csempreendimentos.sgi.model.enums.EnumSexo;
import br.com.csempreendimentos.sgi.model.enums.EnumTipoUsuario;

@ManagedBean(name = "mbAdmGeral")
@SessionScoped
public class AdmGeralBean extends AuxiliarBean implements Serializable
{
   private static final long serialVersionUID = 5868082165104492899L;
   private HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

   private static String TELA_LISTAR_GERENTE = "/paginas/administradorGeral/gerente/listarGerente?faces-redirect=true";
   private static String TELA_CADASTRAR_GERENTE = "/paginas/administradorGeral/gerente/manterGerente?faces-redirect=true";

   private static String TELA_LISTAR_AUX_ADM ="/paginas/administradorGeral/auxAdm/listarAuxAdm?faces-redirect=true";
   private static String TELA_CADASTRAR_AUX_ADM ="/paginas/administradorGeral/auxAdm/manterAuxAdm?faces-redirect=true";

   private List<Usuario> listaGerentes;
   private List<Usuario> listaAuxAdm;

   private AdmGeralDAO admGeralDAO;

   private Usuario usuario = new Usuario();
   private boolean telaCadastro;

   private int contGerente;
   private int contAuxAdm;

   public AdmGeralBean()
   {
      inicializa();
   }

   public void inicializa()
   {
      admGeralDAO = new AdmGeralDAO();
      session.getAttribute("usuarioSessao");
      usuario = new Usuario();
   }

   // GERENCIAR GERENTE
   public void telaGerenciarGerente()
   {
      // TipoUsuario tpUsuario = new TipoUsuario();
      // tpUsuario.setId(EnumTipoUsuario.GERENTE.getCodBanco());
      usuario.setTipoUsuario(new TipoUsuario());
      usuario.getTipoUsuario().setId(EnumTipoUsuario.GERENTE.getCodBanco());

      listaGerentes = admGeralDAO.listarTodos(usuario);
      if (listaGerentes.isEmpty())
      {
         contGerente = 0;
      }
      else
      {
         contGerente = listaGerentes.size();
      }
   }

   public String telaCadastrarGerente()
   {
      if (usuario.getId() != null)
      {
         getOpcoesSexo().equals(usuario.getSexo());
         setTelaCadastro(true);
      }
      else
      {
         inicializa();
         setTelaCadastro(false);
      }
      return TELA_CADASTRAR_GERENTE;
   }

   public String comandoSalvarGerente()
   {
      registroBairro(usuario);
         if (usuario.getId() != null)
         {
            usuario.setEmail(usuario.getEmail().toLowerCase());
            usuario.getTipoUsuario().setId(EnumTipoUsuario.GERENTE.getCodBanco());
            admGeralDAO.updateGerente(usuario);

            FacesContext.getCurrentInstance().addMessage("mensagem" , new FacesMessage(FacesMessage.SEVERITY_INFO, "Gerente " + usuario.getNome() + " Atualizado com Sucesso!",""));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);// adiciona esta linha.  
         }
         else
         {
            usuario.getTipoUsuario().setId(EnumTipoUsuario.GERENTE.getCodBanco());
            usuario.setEmail(usuario.getEmail().toLowerCase());
            admGeralDAO.saveGerente(usuario);

            FacesContext.getCurrentInstance().addMessage("mensagem" ,new FacesMessage(FacesMessage.SEVERITY_INFO, "Gerente Cadastrado com Sucesso!", ""));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);// adiciona esta linha.  
         }
         return TELA_LISTAR_GERENTE;
   }

   public String comandoExcluirGerente()
   {
      admGeralDAO.excluir(usuario);
      inicializa();
      FacesContext.getCurrentInstance().addMessage("mensagem" ,new FacesMessage(FacesMessage.SEVERITY_INFO, "Gerente Excluido com Sucesso!", ""));
      FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);// adiciona esta linha.  
      return TELA_LISTAR_GERENTE;
   }

   public String comandoVoltarGerente()
   {
      inicializa();
      return TELA_LISTAR_GERENTE;
   }

   // GERENCIAR AUXILIAR ADMINISTRATIVO
   public void telaGerenciarAuxAdm()
   {
      // TipoUsuario tpUsuario = new TipoUsuario();
      // tpUsuario.setId(EnumTipoUsuario.AUXILIAR_ADM.getCodBanco());
      //
      usuario.setTipoUsuario(new TipoUsuario());
      usuario.getTipoUsuario().setId(EnumTipoUsuario.AUXILIAR_ADM.getCodBanco());

      listaAuxAdm = admGeralDAO.listarTodos(usuario);

      if (listaAuxAdm.isEmpty())
      {
         contAuxAdm = 0;
      }
      else
      {
         contAuxAdm = listaAuxAdm.size();
      }
   }

   public String telaCadastrarAuxAdm()
   {
      if (usuario.getId() != null)
      {
         getOpcoesSexo().equals(usuario.getSexo());
         setTelaCadastro(true);
      }
      else
      {
         inicializa();
         setTelaCadastro(false);
      }
      return TELA_CADASTRAR_AUX_ADM;
   }

   public String comandoSalvarAuxAdm()
   {
      try
      {
         registroBairro(usuario);
         if (usuario.getId() != null)
         {
            admGeralDAO.updateAuxAdm(usuario);
            FacesContext.getCurrentInstance().addMessage("mensagem" ,new FacesMessage(FacesMessage.SEVERITY_INFO, "Auxiliar Administrativo "+ usuario.getNome() + " Atualizado com Sucesso!", ""));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);// adiciona esta linha.  
         }
         else
         {
            usuario.getTipoUsuario().setId(EnumTipoUsuario.AUXILIAR_ADM.getCodBanco());
            admGeralDAO.saveAuxAdm(usuario);

            FacesContext.getCurrentInstance().addMessage("mensagem" ,new FacesMessage(FacesMessage.SEVERITY_INFO, "Auxiliar Administrativo Cadastrado com Sucesso!",""));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);// adiciona esta linha.  
         }
         inicializa();
         return TELA_LISTAR_AUX_ADM;
      }
      catch (Exception e)
      {
         System.out.println("erro ao validar!!");
         return "";
      }

   }

   public String comandoExcluirAuxAdm()
   {
      admGeralDAO.excluir(usuario);
      inicializa();
      FacesContext.getCurrentInstance().addMessage("mensagem" ,new FacesMessage(FacesMessage.SEVERITY_INFO, "Auxiliar Administrativo Excluido com Sucesso!",""));
      FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);// adiciona esta linha.  

      return TELA_LISTAR_AUX_ADM;
   }

   public String comandoVoltarAuxAdm()
   {
      inicializa();
      return TELA_LISTAR_AUX_ADM;
   }


   // COMANDOS GERAIS
   public void comandoVisualizar()
   {
      getOpcoesSexo().equals(usuario.getSexo());
   }

   public String getAcao()
   {
      return telaCadastro ? "Alterar" : "Cadastrar";
   }

   // GETTERS E SETTERS
   public Usuario getUsuario()
   {
      return usuario;
   }

   public void setUsuario(Usuario usuario)
   {
      this.usuario = usuario;
   }

   public List<Usuario> getListaGerentes()
   {
      return listaGerentes;
   }

   public void setListaGerentes(List<Usuario> listaGerentes)
   {
      this.listaGerentes = listaGerentes;
   }

   public List<Usuario> getListaAuxAdm()
   {
      return listaAuxAdm;
   }

   public void setListaAuxAdm(List<Usuario> listaAuxAdm)
   {
      this.listaAuxAdm = listaAuxAdm;
   }

   public List<EnumSexo> getOpcoesSexo()
   {
      return Arrays.asList(EnumSexo.values());
   }

   public boolean isTelaCadastro()
   {
      return telaCadastro;
   }

   public void setTelaCadastro(boolean telaCadastro)
   {
      this.telaCadastro = telaCadastro;
   }

   public int getContGerente()
   {
      return contGerente;
   }

   public void setContGerente(int contGerente)
   {
      this.contGerente = contGerente;
   }

   public int getContAuxAdm()
   {
      return contAuxAdm;
   }

   public void setContAuxAdm(int contAuxAdm)
   {
      this.contAuxAdm = contAuxAdm;
   }
}