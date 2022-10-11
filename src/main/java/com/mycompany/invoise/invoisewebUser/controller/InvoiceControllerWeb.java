package com.mycompany.invoise.invoisewebUser.controller;


import com.mycompany.invoise.core.controller.InvoiceControllerInterface;
import com.mycompany.invoise.core.entity.Customer;
import com.mycompany.invoise.core.entity.Invoice;
import com.mycompany.invoise.core.service.InvoiceServiceInterface;
import com.mycompany.invoise.invoisewebUser.form.InvoiceForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping ("/invoice")
public class InvoiceControllerWeb {
    @Autowired
    private InvoiceServiceInterface invoiceService;

    public InvoiceServiceInterface getInvoiceService() {
        return invoiceService;
    }

    public void setInvoiceService(InvoiceServiceInterface invoiceService) {
        this.invoiceService = invoiceService;
    }


    @PostMapping("/create")
    public String createInvoiceUsing(@Valid @ModelAttribute InvoiceForm invoiceForm, BindingResult results){
       // String customerName = "Tesla";                                                                          Le formulaire fournira desormais cette information
        //invoice.setCustomerName(customerName);
        if(results.hasErrors()){
            return "invoice-created-form";
        }
        Invoice invoice = new Invoice();

        Customer customer = new Customer(invoiceForm.getCustomerName());
        invoice.setCustomer(customer);

        invoice.setOrderNumber(invoiceForm.getOrderNumber());
        invoiceService.createInvoice(invoice);
        return "invoice-created";
    }

   @GetMapping("/home")
    public String  displayHome(Model model){
        System.out.println(" La méthode list  a bien été invoqué");
         model.addAttribute("invoices",invoiceService.getInvoiceList());
        //req.setAttribute("invoices",invoices);                                       // Pour fournir un element de model a la vue mais ce n<est pqs le plus courant a utiliser
        return "invoice-home";
    }

     /*@GetMapping("/{id}")
    public String displayInvoice(@PathVariable("id") String number, Model model){                  // pour retourner un model et une vue pour resoudre le probleme de retour de la methode plus utilisation de addObject
        System.out.println(" La méthode display invoice a bien été invoqué");
        model.addAttribute("invoice",invoiceService.getInvoiceByNumber(number));

        //req.setAttribute("invoices",invoices);                                          // Pour fournir un element de model a la vue mais ce n<est pqs le plus courant a utiliser
        return "invoice-details";
    }*/

    @GetMapping("/create-form")
    public String displayInvoiceCreateForm(@ModelAttribute InvoiceForm invoiceForm){
        return "invoice-created-form";
    }
}

