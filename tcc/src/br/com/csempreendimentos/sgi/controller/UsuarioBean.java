package br.com.csempreendimentos.sgi.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.FileUploadEvent;
import br.com.csempreendimentos.sgi.dao.UsuarioDAO;
import br.com.csempreendimentos.sgi.model.Usuario;
import br.com.csempreendimentos.sgi.model.enums.EnumSexo;
import br.com.csempreendimentos.sgi.model.enums.EnumTipoUsuario;
import br.com.csempreendimentos.sgi.util.UploadArquivo;

@ManagedBean(name = "mbUsuario")
@SessionScoped
public class UsuarioBean extends AuxiliarBean implements Serializable
{
   private static final long serialVersionUID = 7824148253888546219L;

   private static String INDEX = "index?faces-redirect=true";
   private static String TELA_ADMINISTRADOR_GERAL ="paginas/administradorGeral/principalAdministradorGeral?faces-redirect=true";
   private static String TELA_GERENTE = "paginas/gerente/principalGerente?faces-redirect=true";
   private static String TELA_AUXILIAR_ADMINISTRATIVO = "paginas/auxiliarAdministrativo/principalAuxiliarAdministrativo?faces-redirect=true";
   private static String TELA_CORRETOR = "paginas/corretor/principalCorretor?faces-redirect=true";

   private Usuario usuario = new Usuario();
   private UsuarioDAO usuarioDAO;
   protected HttpSession session;
   
   private UploadArquivo arquivo;

   
   public UsuarioBean()
   {
      index();
   }

   private String index()
   {
      inicializa();
      return INDEX;
   }

   public String verificaTipoUsuario()
   {
      if (validaUsuario())
      {
         session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
         session.setAttribute("usuarioSessao", usuario.getNome());
         session.setAttribute("cpf", usuario.getCpf());
         session.setAttribute("usuario", usuario);
         session.setAttribute("perfil", usuario.getImg_perfil());
         
         if (usuario.getTipoUsuario().getId().equals(EnumTipoUsuario.ADM_GERAL.getCodBanco()))
         {
            return TELA_ADMINISTRADOR_GERAL;
         }
         else if (usuario.getTipoUsuario().getId().equals(EnumTipoUsuario.GERENTE.getCodBanco()))
         {
            return TELA_GERENTE;
         }
         else if (usuario.getTipoUsuario().getId().equals(EnumTipoUsuario.AUXILIAR_ADM.getCodBanco()))
         {
            return TELA_AUXILIAR_ADMINISTRATIVO;
         }
         else if (usuario.getTipoUsuario().getId().equals(EnumTipoUsuario.CORRETOR.getCodBanco()))
         {
            return TELA_CORRETOR;
         }
      }
      else
      {
         FacesContext.getCurrentInstance().addMessage("login", new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuário ou Senha não Cadastrado",""));
         index();
      }
      return "";
   }

   private boolean validaUsuario()
   {
      boolean verificador = true;

      usuario = usuarioDAO.verificaUsuario(getUsuario());

      if (usuario == null)
      {
         verificador = false;
      }
      return verificador;
   }

   public void inicializa()
   {
      usuarioDAO = new UsuarioDAO();
      usuario = new Usuario();
   }

   
   // MODAL RECUPERAÇÃO DE LOGIN PELO CPF
   public Usuario buscaLogin()
   {
      usuario = usuarioDAO.buscaLogin(getUsuario());
      getOpcoesSexo().equals(usuario.getSexo());
      
      if (usuario == null)
      {
            FacesContext.getCurrentInstance().addMessage("msg",new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuário não encontrado",""));
      }
      return usuario;
   }
   
   public String sair()
   {
      inicializa();
      return INDEX;
   }
   
   
   
   // carrega a imagem, mas não a salva.
   public void uploadAction(FileUploadEvent event)
   {
      arquivo = new UploadArquivo();
      usuario = (Usuario) session.getAttribute("usuario");

      arquivo.fileUpload(event, "/resources/img/perfil/");
      
      usuario.setImg_perfil(arquivo.getNome());
   }

   // Salva o nome da imagem no banco de dados e grava a imagem no diretório /image/
   public void alterarFoto()
   {
      usuario.setCpf((String) session.getAttribute("cpf"));

      if (usuarioDAO.alterarFoto(usuario))
      {
         arquivo.gravar();

         session.removeAttribute("perfil");
         verificaTipoUsuario();
         FacesContext.getCurrentInstance().addMessage("mensagemPerfil",new FacesMessage(FacesMessage.SEVERITY_INFO, "Foto Alterada com Sucesso",""));
      }
      else
      {
         FacesContext.getCurrentInstance().addMessage("mensagemPerfil",new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro ao alterar a foto. Favor tentar novamente",""));
      }
   }

   public String comandoUpdate()
   {
      registroBairro(usuario);

      usuarioDAO.updateGenerico(usuario);
      FacesContext.getCurrentInstance().addMessage("mensagemPerfil",new FacesMessage(FacesMessage.SEVERITY_WARN, "Dados Alterados com Sucesso",""));
      return verificaTipoUsuario();
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
   
   public List<EnumSexo> getOpcoesSexo()
   {
      return Arrays.asList(EnumSexo.values());
   }

}