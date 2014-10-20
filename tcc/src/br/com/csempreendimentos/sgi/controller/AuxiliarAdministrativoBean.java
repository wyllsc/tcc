package br.com.csempreendimentos.sgi.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import br.com.csempreendimentos.sgi.dao.AuxiliarAdministrativoDAO;
import br.com.csempreendimentos.sgi.model.Cliente;
import br.com.csempreendimentos.sgi.model.Construtora;
import br.com.csempreendimentos.sgi.model.Empreendimento;
import br.com.csempreendimentos.sgi.model.Imovel;
import br.com.csempreendimentos.sgi.model.Reserva;
import br.com.csempreendimentos.sgi.model.Usuario;
import br.com.csempreendimentos.sgi.model.Venda;
import br.com.csempreendimentos.sgi.model.enums.EnumEstadoCivil;
import br.com.csempreendimentos.sgi.model.enums.EnumSexo;
import br.com.csempreendimentos.sgi.model.enums.EnumStatusVenda;

@ManagedBean(name = "mbAuxAdm")
@SessionScoped
public class AuxiliarAdministrativoBean extends AuxiliarBean implements Serializable 
{
   private static final long serialVersionUID = 7824148253888546219L;

   protected HttpSession session;

   private static String INDEX = "index?faces-redirect=true";

   private Usuario usuario = new Usuario();
   private AuxiliarAdministrativoDAO dao;

   private boolean telaCadastro;

   private Cliente cliente;
   private Cliente clienteReserva;
   private List<Cliente> listaClientes;

   private Long idConstrutora;
   private Long idEmpreedimento;
   private List<Construtora> listaConstrutora;
   private List<Empreendimento> listaEmpreendimento;
   private List<Imovel> listaImoveis;
   private List<Imovel> listaReserva;
   private int countImovel;
   private Imovel imovelSelecionado;

   private boolean fgts;
   private boolean fgtsConj;
   private boolean comprador;
   private boolean catalogo;

   private Reserva reserva;
   private List<Reserva> listaReservaStatus;
   private List<Reserva> listados;

   private int countReservaStatus;
   
   private Venda venda;
   private Long idReserva;
   private Construtora nomeConstrutora;
   private Empreendimento nomeEmpreendimento;


   
   public AuxiliarAdministrativoBean()
   {
      index();
   }

   private String index()
   {
      inicializa();
      return INDEX;
   }

   public void inicializa()
   {
      dao = new AuxiliarAdministrativoDAO();
      usuario = new Usuario();
      cliente = new Cliente();
      reserva = new Reserva();
      clienteReserva = new Cliente();
      venda = new Venda();
      
      listaImoveis = new ArrayList<Imovel>();
      listaConstrutora = new ArrayList<Construtora>();
      listaEmpreendimento = new ArrayList<Empreendimento>();

      countImovel = 0;

      idConstrutora = null;
      idEmpreedimento = null;
   }
   
   
   // GERENCIAR VISUALIZAR VENDAS
   public void telaVisualizarVendas()
   {
      listaReservaStatus = dao.listarReserva();
      listados = new ArrayList<Reserva>();
      for (Reserva r : listaReservaStatus)
      {
         r.setEmpreendimento(dao.buscaEmpreendimentoPorImovel(r.getImovel()));
         r.setConstrutora(dao.buscaConstrutoraPorEmpreendimento(r.getEmpreendimento()));
         listados.add(r);
      }
      
      countReservaStatus = listaReservaStatus.size();
   }


   public void comandoBloquearVenda()
   {
      reserva.setStReserva(EnumStatusVenda.RECUSADO.getCodigo());
      dao.updateGenerico(reserva);
      
      int v ;
      v = dao.verificaVenda(reserva).size();
      
      if (v != 0)
      {
         dao.excluirGenerico(v);
      }

      telaVisualizarVendas();
      FacesContext.getCurrentInstance().addMessage("mensagem", new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente Recusado com Sucesso!", ""));
   }

   public void comandoAprovarVenda()
   {
      reserva.setStReserva(EnumStatusVenda.APROVADO.getCodigo());
      venda.setReserva(reserva);

      Date date = new Date();
      venda.setDtVenda(new java.sql.Date(date.getTime()));

      dao.updateGenerico(reserva);
      dao.saveGenerico(venda);

      telaVisualizarVendas();
      FacesContext.getCurrentInstance().addMessage("mensagem", new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente Aprovado com Sucesso!", ""));
   }   
   
   
   
   // COMANDO GERAIS
//   public void comandoVisualizar()
//   {
//      getOpcoesSexo().equals(usuario.getSexo());
//   }

   public void comandoAlterarLabel()
   {
      setTelaCadastro(true);
   }

   public String getAcao()
   {
      return telaCadastro ? "Alterar" : "Cadastrar";
   }
   
   public void comandoVisualizar()
   {
      getOpcoesSexo().equals(usuario.getSexo());
      comprador = true;
   }


   // public String comandoAlterarImovel()
   // {
   // if (imovel.getId() != null)
   // {
   // getOpcoesPosicao().equals(imovel.getPosicionamento());
   // getOpcoesQuartos().equals(imovel.getQtdQuartos());
   //
   // setTelaCadastro(true);
   // }
   // return TELA_LISTAR_IMOVEL;
   // }

   // GETTERS E SETTERS

   public Usuario getUsuario()
   {
      return usuario;
   }

   public void setUsuario(Usuario usuario)
   {
      this.usuario = usuario;
   }

   public Cliente getCliente()
   {
      return cliente;
   }

   public void setCliente(Cliente cliente)
   {
      this.cliente = cliente;
   }

   public List<EnumSexo> getOpcoesSexo()
   {
      return Arrays.asList(EnumSexo.values());
   }

   public List<EnumEstadoCivil> getOpcoesEstadoCivil()
   {
      return Arrays.asList(EnumEstadoCivil.values());
   }

   public boolean isTelaCadastro()
   {
      return telaCadastro;
   }

   public void setTelaCadastro(boolean telaCadastro)
   {
      this.telaCadastro = telaCadastro;
   }

   public int getCountClientes()
   {
      return dao.listarClientesDoCorretor(getUsuario()).size();
   }

   public List<Cliente> getListaClientes()
   {
      return listaClientes;
   }

   public void setListaClientes(List<Cliente> listaClientes)
   {
      this.listaClientes = listaClientes;
   }

   public Long getIdConstrutora()
   {
      return idConstrutora;
   }

   public void setIdConstrutora(Long idConstrutora)
   {
      this.idConstrutora = idConstrutora;
   }

   public Long getIdEmpreedimento()
   {
      return idEmpreedimento;
   }

   public void setIdEmpreedimento(Long idEmpreedimento)
   {
      this.idEmpreedimento = idEmpreedimento;
   }

   public List<Imovel> getListaImoveis()
   {
      return listaImoveis;
   }

   public void setListaImoveis(List<Imovel> listaImoveis)
   {
      this.listaImoveis = listaImoveis;
   }

   public List<Construtora> getListaConstrutora()
   {
      return listaConstrutora;
   }

   public void setListaConstrutora(List<Construtora> listaConstrutora)
   {
      this.listaConstrutora = listaConstrutora;
   }

   public List<Empreendimento> getListaEmpreendimento()
   {
      return listaEmpreendimento;
   }

   public void setListaEmpreendimento(List<Empreendimento> listaEmpreendimento)
   {
      this.listaEmpreendimento = listaEmpreendimento;
   }

   public int getCountImovel()
   {
      return countImovel;
   }

   public void setCountImovel(int countImovel)
   {
      this.countImovel = countImovel;
   }

   public Imovel getImovelSelecionado()
   {
      return imovelSelecionado;
   }

   public void setImovelSelecionado(Imovel imovelSelecionado)
   {
      this.imovelSelecionado = imovelSelecionado;
   }

   public boolean isFgts()
   {
      return fgts;
   }

   public void setFgts(boolean fgts)
   {
      this.fgts = fgts;
   }

   public boolean isFgtsConj()
   {
      return fgtsConj;
   }

   public void setFgtsConj(boolean fgtsConj)
   {
      this.fgtsConj = fgtsConj;
   }

   public boolean isComprador()
   {
      return comprador;
   }

   public void setComprador(boolean comprador)
   {
      this.comprador = comprador;
   }

   public Reserva getReserva()
   {
      return reserva;
   }

   public void setReserva(Reserva reserva)
   {
      this.reserva = reserva;
   }

   public List<Imovel> getListaReserva()
   {
      return listaReserva;
   }

   public void setListaReserva(List<Imovel> listaReserva)
   {
      this.listaReserva = listaReserva;
   }

   public boolean isCatalogo()
   {
      return catalogo;
   }

   public void setCatalogo(boolean catalogo)
   {
      this.catalogo = catalogo;
   }

   public int getCountReservaStatus()
   {
      return countReservaStatus;
   }

   public void setCountReservaStatus(int countReservaStatus)
   {
      this.countReservaStatus = countReservaStatus;
   }

   public List<Reserva> getListaReservaStatus()
   {
      return listaReservaStatus;
   }

   public void setListaReservaStatus(List<Reserva> listaReservaStatus)
   {
      this.listaReservaStatus = listaReservaStatus;
   }

   public List<Reserva> getListados()
   {
      return listados;
   }

   public void setListados(List<Reserva> listados)
   {
      this.listados = listados;
   }

   public Cliente getClienteReserva()
   {
      return clienteReserva;
   }

   public void setClienteReserva(Cliente clienteReserva)
   {
      this.clienteReserva = clienteReserva;
   }

   public Venda getVenda()
   {
      return venda;
   }

   public void setVenda(Venda venda)
   {
      this.venda = venda;
   }

   public Long getIdReserva()
   {
      return idReserva;
   }

   public void setIdReserva(Long idReserva)
   {
      this.idReserva = idReserva;
   }

   public Construtora getNomeConstrutora()
   {
      return nomeConstrutora;
   }

   public void setNomeConstrutora(Construtora nomeConstrutora)
   {
      this.nomeConstrutora = nomeConstrutora;
   }

   public Empreendimento getNomeEmpreendimento()
   {
      return nomeEmpreendimento;
   }

   public void setNomeEmpreendimento(Empreendimento nomeEmpreendimento)
   {
      this.nomeEmpreendimento = nomeEmpreendimento;
   }

}