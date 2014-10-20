package br.com.csempreendimentos.sgi;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import br.com.csempreendimentos.sgi.dao.GerenteDAO;
import br.com.csempreendimentos.sgi.model.Imovel;

@ManagedBean
@SessionScoped
public class CarBean
{
   GerenteDAO dao = new GerenteDAO();

   private List<Imovel> Imovels;
   private Imovel[] selectedImovels;

   
   public void imprimir()
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
   }

   public List<Imovel> getImovels()
   {
      Imovels = dao.listarImoveis(new Long(1));
      return Imovels;
   }

   public void setImovels(List<Imovel> Imovels)
   {
      this.Imovels = Imovels;
   }

   public Imovel[] getSelectedImovels()
   {
      return selectedImovels;
   }

   public void setSelectedImovels(Imovel[] selectedImovels)
   {
      this.selectedImovels = selectedImovels;
   }
}