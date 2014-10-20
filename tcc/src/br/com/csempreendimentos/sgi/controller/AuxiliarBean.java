package br.com.csempreendimentos.sgi.controller;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import br.com.csempreendimentos.sgi.dao.DAO;
import br.com.csempreendimentos.sgi.model.Bairro;
import br.com.csempreendimentos.sgi.model.Cliente;
import br.com.csempreendimentos.sgi.model.Construtora;
import br.com.csempreendimentos.sgi.model.Usuario;

public class AuxiliarBean
{
   private DAO dao = new DAO();

   public List<String> comandoAutoCompleteBairro()
   {
      List<Bairro> bairro = dao.buscaBairro();
      List<String> list = new ArrayList<String>();
      for (Bairro b : bairro)
      {
         list.add(b.getDescricaoBairro());
      }
      return list;
   }

   public List<String> comandoAutoCompleteCliente()
   {
      List<Cliente> clientes = dao.buscaCliente();
      List<String> list = new ArrayList<String>();
      for (Cliente b : clientes)
      {
         list.add(b.getNome());
      }
      return list;
   }

   public List<String> comandoAutoCompleteRegiao()
   {
      List<String> list = dao.buscaRegiao();
      return list;
   }

   public void registroBairro(Usuario usuario)
   {
      List<Bairro> listBairro = dao.buscaBairro();
      int b = 0;
      Long id = null;
      
      
      for (Bairro br : listBairro)
      {
         if(usuario.getEndereco().getBairro().getDescricaoBairro().toUpperCase().equals(br.getDescricaoBairro().toUpperCase()))
         {
            id = br.getId();
            b++;
         }
      }
      
      if(b != 1)
      {
         usuario.getEndereco().getBairro().setId(null);
      }
      else
      {
         usuario.getEndereco().getBairro().setId(id);
      }
   }

   public void registroBairro(Cliente cliente)
   {
      List<Bairro> listBairro = dao.buscaBairro();
      int b = 0;
      Long id = null;
      
      
      for (Bairro br : listBairro)
      {
         if(StringUtils.isNotEmpty(cliente.getEndereco().getBairro().getDescricaoBairro()))
         {
            if(cliente.getEndereco().getBairro().getDescricaoBairro().toUpperCase().equals(br.getDescricaoBairro().toUpperCase()))
            {
               id = br.getId();
               b++;
            }
         }
      }
      
      if(b != 1)
      {
         cliente.getEndereco().getBairro().setId(null);
      }
      else
      {
         cliente.getEndereco().getBairro().setId(id);
      }
   }

   public void registroBairro(Construtora construtora)
   {
      List<Bairro> listBairro = dao.buscaBairro();
      int b = 0;
      Long id = null;
      
      
      for (Bairro br : listBairro)
      {
         if(construtora.getEndereco().getBairro().getDescricaoBairro().toUpperCase().equals(br.getDescricaoBairro().toUpperCase()))
         {
            id = br.getId();
            b++;
         }
      }
      
      if(b != 1)
      {
         construtora.getEndereco().getBairro().setId(null);
      }
      else
      {
         construtora.getEndereco().getBairro().setId(id);
      }
   }

//   public void registroRegiao(Empreendimento empreendimento)
//   {
//      List<String> list = dao.buscaRegiao();
//      int b = 0;
//      Long id = null;
//      
//      
//      for (String br : list)
//      {
//         if(empreendimento.getRegiao().toUpperCase().equals(br.toUpperCase()))
//         {
//            b++;
//         }
//      }
//      
//      if(b != 1)
//      {
//         construtora.getEndereco().getBairro().setId(null);
//      }
//      else
//      {
//         construtora.getEndereco().getBairro().setId(id);
//      }
//   }
   
}
