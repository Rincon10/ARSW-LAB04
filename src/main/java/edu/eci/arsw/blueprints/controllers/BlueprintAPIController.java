/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hcadavid
 */

@RestController
@RequestMapping(value = "/blueprints")
public class BlueprintAPIController {

    @Autowired
    BlueprintsServices services;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllBluePrints(){
        try {
            //obtener datos que se enviarán a través del API
            Set<Blueprint> data = services.getAllBlueprints();
            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error inesperado",HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping( value = "/{author}" , method=RequestMethod.GET)
    public ResponseEntity<?> getByAuthor(@PathVariable("author") String author){
        try {
            //obtener datos que se enviarán a través del API
            Set<Blueprint> data = services.getBlueprintsByAuthor(author);
            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No existe el autor mencionado",HttpStatus.valueOf(404));
        }
    }

    @RequestMapping( value = "/{author}/{bpName}" , method=RequestMethod.GET)
    public ResponseEntity<?> getByAuthorBPname(@PathVariable("author") String author, @PathVariable("bpName") String bpName){
        try {
            //obtener datos que se enviarán a través del API
            Blueprint data = services.getBlueprint(author, bpName);
            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("No existe el autor "+author+" cuyo plano se llame "+bpName,HttpStatus.valueOf(404));
        }
    }
}

