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
import br.com.csempreendimentos.sgi.dao.CorretorDAO;
import br.com.csempreendimentos.sgi.dao.GerenteDAO;
import br.com.csempreendimentos.sgi.model.Cliente;
import br.com.csempreendimentos.sgi.model.Construtora;
import br.com.csempreendimentos.sgi.model.Empreendimento;
import br.com.csempreendimentos.sgi.model.Imovel;
import br.com.csempreendimentos.sgi.model.Reserva;
import br.com.csempreendimentos.sgi.model.Usuario;
import br.com.csempreendimentos.sgi.model.enums.EnumEstadoCivil;
import br.com.csempreendimentos.sgi.model.enums.EnumSexo;
import br.com.csempreendimentos.sgi.model.enums.EnumSimNao;
import br.com.csempreendimentos.sgi.model.enums.EnumStatusVenda;

@ManagedBean(name = "mbCorretor")
@SessionScoped
public class CorretorBean extends AuxiliarBean implements Serializable
{
   private static final long serialVersionUID = 7824148253888546219L;

   protected HttpSession session;

   private static String INDEX = "index?faces-redirect=true";
   private static String TELA_RESERVAR_IMOVEL ="/paginas/corretor/catalogo/reservarImovel?faces-redirect=true";
   private static String TELA_LISTAR_IMOVEL ="/paginas/corretor/catalogo/listarCatalogo?faces-redirect=true";

   private Usuario usuario = new Usuario();
   private CorretorDAO corretorDAO;
   private GerenteDAO gerenteDAO;

   private boolean telaCadastro;

   private Cliente cliente;
   private Cliente clienteReserva;
   private List<Cliente> listaClientes;
   private int countClientes;

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
   
   private Construtora nomeConstrutora;
   private Empreendimento nomeEmpreendimento;

   private Long idReserva;
   

   private int countReservaStatus;
   
   
   public CorretorBean()
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
      corretorDAO = new CorretorDAO();
      gerenteDAO = new GerenteDAO();
      usuario = new Usuario();
      cliente = new Cliente();
      reserva = new Reserva();
      clienteReserva = new Cliente();

      listaImoveis = new ArrayList<Imovel>();
      listaConstrutora = new ArrayList<Construtora>();
      listaEmpreendimento = new ArrayList<Empreendimento>();

      countClientes = 0;
      countImovel = 0;

      idConstrutora = null;
      idEmpreedimento = null;
   }

   private void pegaDadosCorretor()
   {
      session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
      usuario = (Usuario) session.getAttribute("usuario");
   }

   // GERENCIAR AGENDA DE CLIENTES
   public void telaGerenciarAgenda()
   {
      pegaDadosCorretor();
      listarClientes();
   }

   private void listarClientes()
   {
      listaClientes = corretorDAO.listarClientesDoCorretor(usuario);
      if (listaClientes.isEmpty())
      {
         countClientes = 0;
      }
      else
      {
         countClientes = listaClientes.size();
      }
      telaCadastro = false;
   }

   public void comandoSalvarClientePotencial()
   {
      if (cliente.getId() != null)
      {
         cliente.setEmail(cliente.getEmail().toLowerCase());
         corretorDAO.updateCliente(cliente);

         FacesContext.getCurrentInstance().addMessage("mensagem", new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente " + cliente.getNome() + " Atualizado com Sucesso!", ""));
      }
      else
      {

         cliente.setUsuario((Usuario) session.getAttribute("usuario"));
         corretorDAO.saveCliente(cliente);

         FacesContext.getCurrentInstance().addMessage("mensagem", new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente Cadastrado com Sucesso!", ""));
      }
      inicializa();
   }

   public void comandoExcluirCliente()
   {
      corretorDAO.excluirCliente(cliente);
      FacesContext.getCurrentInstance().addMessage("mensagem", new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente Excluido com Sucesso!", ""));
      listarClientes();
      inicializa();
   }

   
   
   // GERENCIAR CATALOGO DE IMÓVEIS
   public void telaCatalogoImoveis()
   {
      inicializa();
      pegaDadosCorretor();
      listaConstrutora = gerenteDAO.listarConstrutora();
      listaEmpreendimento = new ArrayList<Empreendimento>();
      catalogo = false;
   }

   public void renderizaComboEmpreendimento()
   {
      if (idConstrutora != null)
      {
         listaEmpreendimento = gerenteDAO.listarEmpreendimento(idConstrutora);
      }
      else
      {
         listaEmpreendimento = new ArrayList<Empreendimento>();
      }
   }

   public void renderizaGrid()
   {
      if (idEmpreedimento != null)
      {
         listaImoveis = gerenteDAO.listarImoveis(idEmpreedimento);
         listaReserva = gerenteDAO.listarReservas();

         for (Imovel r : listaReserva)
         {
            listaImoveis.remove(r);
         }

         countImovel = listaImoveis.size();
         catalogo = true;
      }
      else
      {
         listaImoveis = new ArrayList<Imovel>();
         catalogo = true;
      }
   }

   
   
   // GERENCIAR RESERVA DE IMÓVEL
   public String telaReservarImovel()
   {
      cliente = new Cliente();

      setNomeEmpreendimento(corretorDAO.buscaEmpreendimentoPorImovel(imovelSelecionado));
      setNomeConstrutora(corretorDAO.buscaConstrutoraPorEmpreendimento(nomeEmpreendimento));

      countReservaStatus = listaReservaStatus.size();
      return TELA_RESERVAR_IMOVEL;
   }

   public String comandoReservarUnidade()
   {
      registroBairro(clienteReserva);
      if (clienteReserva != null)
      {
         if (fgts == true){
            clienteReserva.setFgts(EnumSimNao.SIM.getCodigo());
         }else{
            clienteReserva.setFgts(EnumSimNao.NAO.getCodigo());
         }

         if (comprador == true){
            clienteReserva.setFgtsConj(EnumSimNao.SIM.getCodigo());
         }else{
            clienteReserva.setFgtsConj(EnumSimNao.NAO.getCodigo());
         }
         
         clienteReserva.setObservacao("Unidade reservada Para Este Cliente...Favor Verificar Andamento da Reserva no Menu Visualizar Vendas!");
         clienteReserva.setUsuario(usuario);
         clienteReserva.setStatusReserva(true);

         if(clienteReserva.getId() == null){
            corretorDAO.saveGenerico(clienteReserva);
         }else {
            corretorDAO.updateCliente(clienteReserva);
         }

         if (clienteReserva.getId() != null)
         {
            Date date = new Date();

            reserva.setCliente(clienteReserva);
            reserva.setImovel(imovelSelecionado);
            reserva.setUsuario(usuario);
            reserva.setStReserva(EnumStatusVenda.ANALISE.getCodigo());
            reserva.setDtReserva(new java.sql.Date(date.getTime()));
            reserva.setValorImovel(imovelSelecionado.getValorImovel());
            reserva.setValorPercentual(imovelSelecionado.getPercentualEmpresa());
            reserva.setComentario("DOCUMENTAÇÃO EM ANÁLISE!");
            corretorDAO.saveGenerico(reserva);
            FacesContext.getCurrentInstance().addMessage("mensagem", new FacesMessage(FacesMessage.SEVERITY_INFO, "Unidade Reservada com Sucesso!", ""));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);// adiciona esta linha.  
            
            return TELA_LISTAR_IMOVEL;
         }
      }

      return "";
   }
   
   public String comandoVoltar()
   {
      telaCatalogoImoveis();
      return TELA_LISTAR_IMOVEL;
   }
   
   public void listarClientesReserva()
   {
      listaClientes = corretorDAO.listarClientesDoCorretor(usuario);
   }
   
   public void renderizaCamposProposta()
   {
      clienteReserva = corretorDAO.buscaCliente(clienteReserva);
   }

   
   
   // GERENCIAR VISUALIZAR VENDAS
   public void telaVisualizarVendas()
   {
      listaReservaStatus = corretorDAO.listarReserva(usuario);
      listados = new ArrayList<Reserva>();
      for (Reserva r : listaReservaStatus)
      {
         r.setEmpreendimento(corretorDAO.buscaEmpreendimentoPorImovel(r.getImovel()));
         r.setConstrutora(corretorDAO.buscaConstrutoraPorEmpreendimento(r.getEmpreendimento()));
         listados.add(r);
      }
      
      countReservaStatus = listaReservaStatus.size();
   }

   // COMANDO GERAIS
   public void comandoVisualizar()
   {
      getOpcoesSexo().equals(usuario.getSexo());
      comprador = true;
   }

   public void comandoAlterarLabel()
   {
      setTelaCadastro(true);
   }

   public String getAcao()
   {
      return telaCadastro ? "Alterar" : "Cadastrar";
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
      return corretorDAO.listarClientesDoCorretor(getUsuario()).size();
   }

   public void setCountClientes(int countClientes)
   {
      this.countClientes = countClientes;
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

   public GerenteDAO getGerenteDAO()
   {
      return gerenteDAO;
   }

   public void setGerenteDAO(GerenteDAO gerenteDAO)
   {
      this.gerenteDAO = gerenteDAO;
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

   public Construtora getNomeConstrutora()
   {
      if (nomeConstrutora == null)
      {
         nomeConstrutora = new Construtora();
      }
      return nomeConstrutora;
   }

   public void setNomeConstrutora(Construtora nomeConstrutora)
   {
      this.nomeConstrutora = nomeConstrutora;
   }

   public Empreendimento getNomeEmpreendimento()
   {
      if (nomeEmpreendimento == null)
      {
         nomeEmpreendimento = new Empreendimento();
      }
      return nomeEmpreendimento;
   }

   public void setNomeEmpreendimento(Empreendimento nomeEmpreendimento)
   {
      this.nomeEmpreendimento = nomeEmpreendimento;
   }

   public Long getIdReserva()
   {
      return idReserva;
   }

   public void setIdReserva(Long idReserva)
   {
      this.idReserva = idReserva;
   }

  

}