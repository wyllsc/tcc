package br.com.csempreendimentos.sgi.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import br.com.csempreendimentos.sgi.dao.GerenteDAO;
import br.com.csempreendimentos.sgi.model.Construtora;
import br.com.csempreendimentos.sgi.model.ConstrutoraEmpreendimento;
import br.com.csempreendimentos.sgi.model.Empreendimento;
import br.com.csempreendimentos.sgi.model.EmpreendimentoImovel;
import br.com.csempreendimentos.sgi.model.Imovel;
import br.com.csempreendimentos.sgi.model.TipoUsuario;
import br.com.csempreendimentos.sgi.model.Usuario;
import br.com.csempreendimentos.sgi.model.enums.EnumPosicao;
import br.com.csempreendimentos.sgi.model.enums.EnumQtdQuartos;
import br.com.csempreendimentos.sgi.model.enums.EnumSexo;
import br.com.csempreendimentos.sgi.model.enums.EnumTipoUsuario;

@ManagedBean(name = "mbGerente")
@SessionScoped
public class GerenteBean extends AuxiliarBean implements Serializable
{
   private static final long serialVersionUID = 5868082165104492899L;

   private static String TELA_LISTAR_CORRETOR ="/paginas/gerente/corretor/listarCorretor?faces-redirect=true";
   private static String TELA_CADASTRAR_CORRETOR = "/paginas/gerente/corretor/manterCorretor?faces-redirect=true";
   
   private static String TELA_LISTAR_CONSTRUTORA = "/paginas/gerente/construtora/listarConstrutora?faces-redirect=true";
   private static String TELA_CADASTRAR_CONSTRUTORA = "/paginas/gerente/construtora/manterConstrutora?faces-redirect=true";
   
   private static String TELA_LISTAR_EMPREENDIMENTO = "/paginas/gerente/empreendimento/listarEmpreendimento?faces-redirect=true";
   private static String TELA_LISTAR_IMOVEL = "/paginas/gerente/imoveis/listarImovel?faces-redirect=true";

   private HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
   
   private boolean telaCadastro;
   private GerenteDAO gerenteDAO;

   private Usuario usuario = new Usuario();
   private Construtora construtora = new Construtora();
   private Empreendimento empreendimento = new Empreendimento();
   private Imovel imovel = new Imovel();
   private ConstrutoraEmpreendimento construtoraEmpreendimento = new ConstrutoraEmpreendimento();
   private EmpreendimentoImovel empreendimentoImovel = new EmpreendimentoImovel();
   
   private List<Usuario> listaCorretores;
   private int contCorretor;

   private List<Construtora> listaConstrutora;
   private List<Empreendimento> listaEmpreendimentosDaConstrutora;
   private int contConstrutora;

   private List<Empreendimento> listaEmpreendimento;
   private List<Imovel> listaImoveisDoEmpreendimento;
   private int contEmpreendimento;

   private List<Imovel> listaImovel;
   private Imovel[] selectedImovels;
   private int contImovel;

   private double percentualEmpresaAlterado;
   private double valorImovelAlterado;
   
   private boolean msg;
   
   public GerenteBean()
   {
      inicializa();
   }
   public void inicializa()
   {
      gerenteDAO = new GerenteDAO();
      session.getAttribute("usuarioSessao");
      usuario = new Usuario();
      construtora = new Construtora();
      empreendimento = new Empreendimento();
      imovel = new Imovel();
      construtoraEmpreendimento = new ConstrutoraEmpreendimento();
      empreendimentoImovel = new EmpreendimentoImovel();
   }

   // GERENCIAR CORRETOR
   public void telaGerenciarCorretor()
   {
      TipoUsuario tpUsuario = new TipoUsuario();

      tpUsuario.setId(EnumTipoUsuario.CORRETOR.getCodBanco());
      usuario.setTipoUsuario(tpUsuario);

      listaCorretores = gerenteDAO.listarTodosUsuarios(usuario);
      if (listaCorretores.isEmpty())
      {
         contCorretor = 0;
      }
      else
      {
         contCorretor = listaCorretores.size();
      }
   }
   public String telaCadastrarCorretor()
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
      return TELA_CADASTRAR_CORRETOR;
   }
   public String comandoSalvarCorretor()
   {
      try
      {
         registroBairro(usuario);
         if (usuario.getId() != null)
         {
            usuario.setEmail(usuario.getEmail().toLowerCase());
            gerenteDAO.updateUsuario(usuario);
            FacesContext.getCurrentInstance().addMessage("mensagem" , new FacesMessage(FacesMessage.SEVERITY_INFO, "Corretor " + usuario.getNome() + " Atualizado com Sucesso!",""));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);// adiciona esta linha.  
         }
         else
         {
            usuario.getTipoUsuario().setId(EnumTipoUsuario.CORRETOR.getCodBanco());
            usuario.setEmail(usuario.getEmail().toLowerCase());
            gerenteDAO.saveUsuario(usuario);
            FacesContext.getCurrentInstance().addMessage("mensagem", new FacesMessage(FacesMessage.SEVERITY_INFO, "Corretor Cadastrado com Sucesso!", ""));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);// adiciona esta linha.  
         }
         inicializa();
         return TELA_LISTAR_CORRETOR;
      }
      catch (Exception e)
      {
         System.out.println("erro ao validar!!");
         return "";
      }

   }
   public String comandoExcluirCorretor()
   {
      gerenteDAO.excluirUsuario(usuario);
      inicializa();
      FacesContext.getCurrentInstance().addMessage("mensagem", new FacesMessage(FacesMessage.SEVERITY_INFO, "Corretor Excluido com Sucesso!", ""));
      FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);// adiciona esta linha.  
      return TELA_LISTAR_CORRETOR;
   }
   public String comandoVoltarCorretor()
   {
      inicializa();
      return TELA_LISTAR_CORRETOR;
   }

   
   
   // GERENCIAR CONSTRUTORA
   public void telaGerenciarConstrutora()
   {
      listaConstrutora = gerenteDAO.listarConstrutora();

      if (listaConstrutora.isEmpty())
      {
         contConstrutora = 0;
      }
      else
      {
         contConstrutora = listaConstrutora.size();

         for (Construtora c : listaConstrutora)
         {
            listaEmpreendimentosDaConstrutora = gerenteDAO.listarEmpreendimento(c.getId());
            c.setListEmpreendimentos(listaEmpreendimentosDaConstrutora);
         }
      }
   }
   public String telaCadastrarConstrutora()
   {
      if (construtora.getId() != null)
      {
         setTelaCadastro(true);
      }
      else
      {
         inicializa();
         setTelaCadastro(false);
      }
      return TELA_CADASTRAR_CONSTRUTORA;
   }
   public String comandoExcluirConstrutora()
   {
      List<Construtora> lista = gerenteDAO.listarConstrutoraVinculada(construtora.getId());
      if(lista.isEmpty())
      {
         gerenteDAO.excluirConstrutora(construtora);
         telaGerenciarConstrutora();
         FacesContext.getCurrentInstance().addMessage("mensagem", new FacesMessage(FacesMessage.SEVERITY_WARN, "Construtora "+ construtora.getNomeConstrutora() + " - Excluida com Sucesso!", ""));
      }
      else
      {
         FacesContext.getCurrentInstance().addMessage("mensagem", new FacesMessage(FacesMessage.SEVERITY_WARN, "Construtora "+ construtora.getNomeConstrutora() + " Não pode ser Excluida - Favor Excluir Empreendimentos Vinculados!", ""));
      }
      inicializa();
      return "";
   }
   public String comandoVoltarConstrutora()
   {
      inicializa();
      return TELA_LISTAR_CONSTRUTORA;
   }
   public String comandoSalvarConstrutora()
   {
      try
      {
         registroBairro(construtora);
         if (construtora.getId() != null)
         {
            construtora.setEmailGerente(construtora.getEmailGerente().toLowerCase());
            gerenteDAO.updateConstrutora(construtora);
            FacesContext.getCurrentInstance().addMessage("mensagem", new FacesMessage(FacesMessage.SEVERITY_INFO, "Construtora " + construtora.getNomeConstrutora() + " Atualizada com Sucesso!", ""));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);// adiciona esta linha.  
         }
         else
         {
            construtora.setEmailGerente(construtora.getEmailGerente().toLowerCase());
            gerenteDAO.saveConstrutora(construtora);
            FacesContext.getCurrentInstance().addMessage("mensagem", new FacesMessage(FacesMessage.SEVERITY_INFO, "Construtora Cadastrada com Sucesso!", ""));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);// adiciona esta linha.  
         }
         inicializa();
         return TELA_LISTAR_CONSTRUTORA;
      }
      catch (Exception e)
      {
         System.out.println("erro ao validar!!");
         return "";
      }
   }

   
   
   
   // GERENCIAR EMPREENDIMENTO
   public String telaGerenciarEmpreendimento()
   {
      listarEmpreendimento();
      
      return TELA_LISTAR_EMPREENDIMENTO;
   }
   private void listarEmpreendimento()
   {
      listaEmpreendimento = gerenteDAO.listarEmpreendimento(construtora.getId());
      setTelaCadastro(false);

      if (listaEmpreendimento.isEmpty())
      {
         contEmpreendimento = 0;
      }
      else
      {
         contEmpreendimento = listaEmpreendimento.size();
         
         for (Empreendimento c : listaEmpreendimento)
         {
            listaImoveisDoEmpreendimento = gerenteDAO.listarImoveis(c.getId());
            c.setContImovelEmpreendimento(listaImoveisDoEmpreendimento.size());
         }
      }
      listarImoveis();
   }
   public void telaCadastrarEmpreendimento()
   {
      if (empreendimento.getId() != null)
      {
         setTelaCadastro(true);
      }
      else
      {
         empreendimento = new Empreendimento();
         setTelaCadastro(false);
      }
      // return TELA_CADASTRAR_EMPREENDIMENTO;
   }
   public String comandoExcluirEmpreendimento()
   {
      List<Empreendimento> lista = gerenteDAO.listarEmpreedimentoVinculada(empreendimento.getId());
      if(lista.isEmpty())
      {
      gerenteDAO.excluirEmpreendimento(empreendimento);
      gerenteDAO.excluirRlConstrutoraEmpreendimento(empreendimento);
      listarEmpreendimento();
         FacesContext.getCurrentInstance().addMessage("mensagem", new FacesMessage(FacesMessage.SEVERITY_WARN, "Empreendimento "+ empreendimento.getNomeEmpreendimento() + " Excluido com Sucesso!", ""));
      }
      else
      {
         FacesContext.getCurrentInstance().addMessage("mensagem", new FacesMessage(FacesMessage.SEVERITY_WARN, "Empreendimento "+ empreendimento.getNomeEmpreendimento() + " Não pode ser Excluido - Favor Excluir Imóveis Vinculados!", ""));
      }
      empreendimento = new Empreendimento();
      return "";
   }
   public String comandoVoltarEmpreendimento()
   {
      return TELA_LISTAR_EMPREENDIMENTO;
   }
   public void comandoSalvarEmpreendimento()
   {
      if (empreendimento.getId() != null)
      {
         gerenteDAO.updateEmpreendimento(empreendimento);
         setTelaCadastro(false);
         FacesContext.getCurrentInstance().addMessage("mensagem", new FacesMessage(FacesMessage.SEVERITY_INFO, "Empreendimento "+ empreendimento.getNomeEmpreendimento() + " Atualizado com Sucesso!", ""));
      }
      else
      {
         gerenteDAO.saveEmpreendimento(empreendimento);

         construtoraEmpreendimento.setConstrutora(construtora.getId());
         construtoraEmpreendimento.setEmpreendimento(empreendimento.getId());
         
         gerenteDAO.saveGenerico(construtoraEmpreendimento);
         
         FacesContext.getCurrentInstance().addMessage("mensagem", new FacesMessage(FacesMessage.SEVERITY_INFO, "Empreendimento Cadastrado com Sucesso!", ""));
      }
      empreendimento = new Empreendimento();
      telaGerenciarEmpreendimento();
   }

   
   
   
   // GERENCIAR IMÓVEIS
   private void listarImoveis()
   {
      listaImovel = gerenteDAO.listarImoveis(empreendimento.getId());

      if (!listaImovel.isEmpty())
      {
         contImovel = listaImovel.size();
      }
      else
      {
         contImovel = 0;
      }
   }
   public String telaGerenciarImoveis()
   {
      imovel = new Imovel();   
      selectedImovels = null;
      listarImoveis();
      return TELA_LISTAR_IMOVEL;
   }
   public void comandoExcluirImoveis()
   {
      if(selectedImovels != null)
      {
         for (Imovel i : selectedImovels)
         {
            gerenteDAO.excluirImovel(i);
            gerenteDAO.excluirRlEmpreendimentoImovel(i);
         }
         FacesContext.getCurrentInstance().addMessage("mensagem", new FacesMessage(FacesMessage.SEVERITY_WARN, "Unidade(s) Excluida(s) com Sucesso!", ""));
         imovel = new Imovel();
         selectedImovels=null;
         listarImoveis();
      }
   }
   public String comandoSalvarImoveis()
   {
      if (imovel.getId() != null)
      {
         gerenteDAO.updateImovel(imovel);
         FacesContext.getCurrentInstance().addMessage("mensagem", new FacesMessage(FacesMessage.SEVERITY_INFO, "Imovel "+ imovel.getLocalizacaoImovel() + " Atualizado com Sucesso!", ""));
         setTelaCadastro(false);
      }
      else
      {
         gerenteDAO.saveImovel(imovel);
         empreendimentoImovel.setEmpreendimento(empreendimento.getId());
         empreendimentoImovel.setImovel(imovel.getId());
         gerenteDAO.saveGenerico(empreendimentoImovel);
        
         FacesContext.getCurrentInstance().addMessage("mensagem", new FacesMessage(FacesMessage.SEVERITY_INFO, "Imovel Cadastrado com Sucesso!",""));
      }
      imovel = new Imovel();
      telaGerenciarImoveis();
      listarImoveis();
      return "";
   }
   public String comandoAlterarImovel()
   {
      if (imovel.getId() != null)
      {
         getOpcoesPosicao().equals(imovel.getPosicionamento());
         getOpcoesQuartos().equals(imovel.getQtdQuartos());
         
         setTelaCadastro(true);
      }
      return TELA_LISTAR_IMOVEL;
   }
   public void comandoAlterarVariasUnidade()
   {
      if(selectedImovels != null)
      {
         for (Imovel i : selectedImovels)
         {
            i.setValorImovel(valorImovelAlterado);
            i.setPercentualEmpresa(percentualEmpresaAlterado);
            
            gerenteDAO.updateGenerico(i);
         }
         FacesContext.getCurrentInstance().addMessage("mensagem", new FacesMessage(FacesMessage.SEVERITY_WARN, "Unidade(s) Alteradas(s) com Sucesso!", ""));
         imovel = new Imovel();
         selectedImovels=null;
         listarImoveis();
      }
   }
  
   
   
   // TESTE DE SELEÇÃO DE CARROS VARIADOS
   public String imprimir()
   {
      if (selectedImovels != null)
      {
         for (Imovel c : selectedImovels)
         {
            System.out.println("Modelo: " + c.getId());
         }
      }
      else
      {
         System.out.println("NULO");
      }
      return "opsd";
   }

   
   

   
   
   
   
   
   
   
   
   
   

   // COMANDOS GERAIS
   public void comandoVisualizar()
   {
      getOpcoesSexo().equals(usuario.getSexo());
   }
   
   public void comandoAlterarLabel()
   {
      setTelaCadastro(true);
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
      return listaCorretores;
   }

   public void setListaGerentes(List<Usuario> listaGerentes)
   {
      this.listaCorretores = listaGerentes;
   }

   public List<EnumSexo> getOpcoesSexo()
   {
      return Arrays.asList(EnumSexo.values());
   }

   public List<EnumPosicao> getOpcoesPosicao()
   {
      return Arrays.asList(EnumPosicao.values());
   }

   public List<EnumQtdQuartos> getOpcoesQuartos()
   {
      return Arrays.asList(EnumQtdQuartos.values());
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
      return contCorretor;
   }

   public void setContGerente(int contGerente)
   {
      this.contCorretor = contGerente;
   }

   public List<Usuario> getListaCorretores()
   {
      return listaCorretores;
   }

   public void setListaCorretores(List<Usuario> listaCorretores)
   {
      this.listaCorretores = listaCorretores;
   }

   public int getContCorretor()
   {
      return contCorretor;
   }

   public void setContCorretor(int contCorretor)
   {
      this.contCorretor = contCorretor;
   }

   public List<Construtora> getListaConstrutora()
   {
      return listaConstrutora;
   }

   public void setListaConstrutora(List<Construtora> listaConstrutora)
   {
      this.listaConstrutora = listaConstrutora;
   }

   public int getContConstrutora()
   {
      return contConstrutora;
   }

   public void setContConstrutora(int contConstrutora)
   {
      this.contConstrutora = contConstrutora;
   }

   public Construtora getConstrutora()
   {
      return construtora;
   }

   public void setConstrutora(Construtora construtora)
   {
      this.construtora = construtora;
   }

   public Empreendimento getEmpreendimento()
   {
      return empreendimento;
   }

   public void setEmpreendimento(Empreendimento empreendimento)
   {
      this.empreendimento = empreendimento;
   }

   public List<Empreendimento> getListaEmpreendimento()
   {
      return listaEmpreendimento;
   }

   public void setListaEmpreendimento(List<Empreendimento> listaEmpreendimento)
   {
      this.listaEmpreendimento = listaEmpreendimento;
   }

   public int getContEmpreendimento()
   {
      return contEmpreendimento;
   }

   public void setContEmpreendimento(int contEmpreendimento)
   {
      this.contEmpreendimento = contEmpreendimento;
   }

   public Imovel getImovel()
   {
      return imovel;
   }

   public void setImovel(Imovel imovel)
   {
      this.imovel = imovel;
   }

   public List<Empreendimento> getListaEmpreendimentosDaConstrutora()
   {
      return listaEmpreendimentosDaConstrutora;
   }

   public void setListaEmpreendimentosDaConstrutora(
      List<Empreendimento> listaEmpreendimentosDaConstrutora)
   {
      this.listaEmpreendimentosDaConstrutora = listaEmpreendimentosDaConstrutora;
   }

   public List<Imovel> getListaImoveisDoEmpreendimento()
   {
      return listaImoveisDoEmpreendimento;
   }

   public void setListaImoveisDoEmpreendimento(List<Imovel> listaImoveisDoEmpreendimento)
   {
      this.listaImoveisDoEmpreendimento = listaImoveisDoEmpreendimento;
   }

   public List<Imovel> getListaImovel()
   {
      return listaImovel;
   }

   public void setListaImovel(List<Imovel> listaImovel)
   {
      this.listaImovel = listaImovel;
   }

   public int getContImovel()
   {
      return contImovel;
   }

   public void setContImovel(int contImovel)
   {
      this.contImovel = contImovel;
   }
   
   public Imovel[] getSelectedImovels()
   {
      return selectedImovels;
   }

   public void setSelectedImovels(Imovel[] selectedImovels)
   {
      this.selectedImovels = selectedImovels;
   }

   public ConstrutoraEmpreendimento getConstrutoraEmpreendimento()
   {
      return construtoraEmpreendimento;
   }

   public void setConstrutoraEmpreendimento(ConstrutoraEmpreendimento construtoraEmpreendimento)
   {
      this.construtoraEmpreendimento = construtoraEmpreendimento;
   }

   public EmpreendimentoImovel getEmpreendimentoImovel()
   {
      return empreendimentoImovel;
   }

   public void setEmpreendimentoImovel(EmpreendimentoImovel empreendimentoImovel)
   {
      this.empreendimentoImovel = empreendimentoImovel;
   }
   public double getPercentualEmpresaAlterado()
   {
      return percentualEmpresaAlterado;
   }
   public void setPercentualEmpresaAlterado(double percentualEmpresaAlterado)
   {
      this.percentualEmpresaAlterado = percentualEmpresaAlterado;
   }
   public double getValorImovelAlterado()
   {
      return valorImovelAlterado;
   }
   public void setValorImovelAlterado(double valorImovelAlterado)
   {
      this.valorImovelAlterado = valorImovelAlterado;
   }
   public boolean isMsg()
   {
      return msg;
   }
   public void setMsg(boolean msg)
   {
      this.msg = msg;
   }
}