package org.sid.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.sid.dao.UtilisateurRepository;
import org.sid.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@Service
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Transactional
    public void importUtilisateurDepuisCsv(String cheminFicierCSV) throws IOException {
        Reader readerFile = new FileReader(cheminFicierCSV);
        CSVParser csvParser = new CSVParser(readerFile, CSVFormat.DEFAULT.withFirstRecordAsHeader());

        for(CSVRecord csvRecord : csvParser){
            String name = csvRecord.get("name");
            String email = csvRecord.get("email");
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setName(name);
            utilisateur.setEmail(email);
            utilisateurRepository.save(utilisateur);
        }
        csvParser.close();
    }
}
