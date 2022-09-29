package com.mycompany.invoise.invoisewebUser.api;


import com.mycompany.invoise.core.entity.Invoice;
import com.mycompany.invoise.core.service.InvoiceServiceInterface;
import com.mycompany.invoise.invoisewebUser.form.InvoiceForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping ("/invoice")
public class InvoiceRessource {
    @Autowired
    private InvoiceServiceInterface invoiceService;

    public InvoiceServiceInterface getInvoiceService() {
        return invoiceService;
    }

    public void setInvoiceService(InvoiceServiceInterface invoiceService) {
        this.invoiceService = invoiceService;
    }


  @PostMapping
    public  Invoice create(@RequestBody Invoice invoice){

        return  invoiceService.createInvoice(invoice);
    }

    @GetMapping
    public List<Invoice> list(){
        System.out.println(" La méthode display home a bien été invoqué");

        return invoiceService.getInvoiceList();
    }

    @GetMapping("/{id}")
    public Invoice displayInvoice(@PathVariable("id") String number, Model model){                  // pour retourner un model et une vue pour resoudre le probleme de retour de la methode plus utilisation de addObject
        System.out.println(" La méthode display invoice a bien été invoqué");

        return invoiceService.getInvoiceByNumber(number);
    }

    /*@GetMapping("/create-form")
    public String displayInvoiceCreateForm(@ModelAttribute InvoiceForm invoiceForm){
        return "invoice-created-form";
    }*/
}

