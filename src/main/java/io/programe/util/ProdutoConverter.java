//package io.programe.util;
//
//import io.programe.model.Produto;
//import io.programe.service.ProdutoService;
//import jakarta.faces.convert.FacesConverter;
//import jakarta.inject.Inject;
//
//@FacesConverter("produtoConverter")
//public class ProdutoConverter implements Converter {
//
//   @Inject
//    private ProdutoService produtoService;
//
//    public Produto getAsObject(FacesContext context, UIComponent component, String value) {
//        if (value != null && value.trim().length() > 0) {
//            try {
//                return produtoService.getCountriesAsMap().get(Integer.parseInt(value));
//            }
//            catch (NumberFormatException e) {
//                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid Produto."));
//            }
//        }
//        else {
//            return null;
//        }
//    }
//
//    public String getAsString(FacesContext context, UIComponent component, Produto value) {
//        if (value != null) {
//            return String.valueOf(value.getId());
//        }
//        else {
//            return null;
//        }
//    }    
    
//    public Object getAsObject(FacesContext context, UIComponent component, String value) {
//        if (value != null && !value.isEmpty()) {
//           try{
//               ProdutoService service = (ProdutoService) context.getExternalContext().getApplicationMap().get("produtoService");
//               return service.getProdutos().get(Interger.parseInt(value));
//           }catch{
//               throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de conversão", "Produto não é valido."));
//           }
////            produto = produtos.stream().filter(p -> p.getDescricao().contains(value)).findFirst();
//        }
//        return produto.orElse(new Produto());
//    }
//
//    public String getAsString(FacesContext context, UIComponent component, Object value) {
//        if (value != null){
//         return String.valueOf(((Produto) value).getId());           
//        }else{
//           return null; 
//        }
//    }

//}