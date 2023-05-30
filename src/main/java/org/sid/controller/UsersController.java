package org.sid.controller;

import org.sid.service.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class UsersController {
    private UtilisateurService utilisateurService;

    public UsersController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }
    @PostMapping("/import-users")
    public ResponseEntity<String> importUsers(@RequestParam("csvFile") MultipartFile csvFile) throws IOException {
       try {
           String tempFilePath = "chemin/vers/fichier/temporaire.csv";
           csvFile.transferTo(new File(tempFilePath));
           utilisateurService.importUtilisateurDepuisCsv(tempFilePath);
           return ResponseEntity.ok("Les Utilisateur ont ete importe avec sucess");
       }
       catch (IOException e){
           e.printStackTrace();
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une error c'est produite lors de " +
                   "l'importation des Utilisateur");
       }

    }
}
