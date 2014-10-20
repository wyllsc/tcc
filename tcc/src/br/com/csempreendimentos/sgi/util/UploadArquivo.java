package br.com.csempreendimentos.sgi.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.primefaces.event.FileUploadEvent;

public class UploadArquivo
{

   private String diretorio;
   private String caminho;
   private byte[] arquivo;
   private String nome;

   public UploadArquivo()
   {
   }

   public String getDiretorio()
   {
      return this.diretorio;
   }

   public void setDiretorio(String diretorio)
   {
      this.diretorio = diretorio;
   }

   public byte[] getArquivo()
   {
      return arquivo;
   }

   public void setArquivo(byte[] arquivo)
   {
      this.arquivo = arquivo;
   }

   public String getNome()
   {
      return nome;
   }

   public void setNome(String nome)
   {
      this.nome = nome;
   }

   // O método getRealPath pega o diretório completo da aplicação no servidor
   public String getRealPath()
   {
      ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
      HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

      FacesContext aFacesContext = FacesContext.getCurrentInstance();
      ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();

      return context.getRealPath("/");
   }

   // O método fileUpload irá fazer o carregamento do arquivo e prepará-lo para ser gravado
   public void fileUpload(FileUploadEvent event, String diretorio)
   {
      try
      {

         FacesContext context = FacesContext.getCurrentInstance();
         HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
         String cpf = (String) session.getAttribute("cpf");

         this.nome = cpf;
         this.caminho = getRealPath() + diretorio + getNome();
         this.arquivo = event.getFile().getContents();

         File file = new File(getRealPath() + diretorio);
         file.mkdirs();
      }
      catch (Exception ex)
      {
         System.out.println("Erro no upload do arquivo" + ex);
      }
   }

   // O método salvar, grava o arquivo no diretório informado.
   // Os arquivos serão armazenados na pasta build/web/nomediretorio do projeto
   public void gravar()
   {
      try
      {

         FileOutputStream fos;
         fos = new FileOutputStream(this.caminho);
         fos.write(this.arquivo);
         fos.close();

      }
      catch (Exception ex)
      {
         Logger.getLogger(UploadArquivo.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   public void deletar(String nome, String diretorio)
   {
      try
      {
         this.nome = nome;
         this.caminho = getRealPath() + diretorio + getNome();

         File file = new File(getRealPath() + diretorio + nome);

         file.delete();
      }
      catch (Exception ex)
      {
         System.out.println("Erro ao deletar a foto!" + ex);
      }
   }
}