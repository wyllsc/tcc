package br.com.csempreendimentos.sgi.util.converter;

import java.text.ParseException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.swing.text.MaskFormatter;
import org.apache.commons.lang3.StringUtils;

@FacesConverter("cepConverter")
public class CEPConverter implements Converter
{
   public Object getAsObject(FacesContext context, UIComponent component, String value)throws ConverterException
   {
      String valor = value;
      if (StringUtils.isNotBlank(value))
      {
         valor = value.replaceAll("[.]", "").replaceAll("-", "");
      }
      return valor;
   }

   public String getAsString(FacesContext context, UIComponent component, Object value)throws ConverterException
   {
      String valor = null;
      valor = value.toString();
      if (StringUtils.isNotBlank(valor) && valor.length() == 8)
      {
         try
         {
            MaskFormatter mf = new MaskFormatter("##.###-###");
            mf.setValueContainsLiteralCharacters(false);
            valor = mf.valueToString(valor);
         }
         catch (ParseException e)
         {
            e.printStackTrace();
         }
      }
      return valor;
   }
}