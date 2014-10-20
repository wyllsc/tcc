package br.com.csempreendimentos.sgi.util.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("numeroValidator")
public class numeroValidator implements Validator
{
   @Override
   public void validate(FacesContext context, UIComponent component, Object value)
      throws ValidatorException
   {
      // if(value.)
      //
      // if (!email.contains("@"))
      // {
      // FacesMessage message = new FacesMessage();
      // message.setSeverity(FacesMessage.SEVERITY_ERROR);
      // message.setSummary("Email com formato inválido");
      // context.addMessage("userForm:Email", message);
      // throw new ValidatorException(message);
      // }

      if (isDigit(value.toString()) == true)
      {
          FacesMessage message = new FacesMessage();
          
          message.setSeverity(FacesMessage.SEVERITY_ERROR);
          message.setSummary("Campo aceita apenas Números");
          context.addMessage("userForm:Email", message);
          
          throw new ValidatorException(message);
      }

   }

   boolean isDigit(String value)
   {
      boolean v = false;

      value.replaceAll(".", "").replaceAll(",", "");
      for (int i = 0; i < value.length(); i++)
      {
         char valor = value.charAt(i);
         if (valor < 48 || valor > 57)
         {
            v = true;
         }
         else
         {
            v = false;
         }
      }
      return v;
   }
}
