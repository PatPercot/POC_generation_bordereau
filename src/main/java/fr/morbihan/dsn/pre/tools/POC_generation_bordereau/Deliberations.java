/**
 * 
 */
package fr.morbihan.dsn.pre.tools.POC_generation_bordereau;

import java.time.LocalDate;

import javax.swing.text.AbstractDocument.Content;

import org.slf4j.LoggerFactory;

import fr.gouv.vitam.tools.sedalib.inout.SIPBuilder;
import fr.gouv.vitam.tools.sedalib.metadata.content.*;
import fr.gouv.vitam.tools.sedalib.metadata.management.AccessRule;
import fr.gouv.vitam.tools.sedalib.metadata.management.AppraisalRule;
import fr.gouv.vitam.tools.sedalib.metadata.management.DisseminationRule;
import fr.gouv.vitam.tools.sedalib.metadata.management.Management;
import fr.gouv.vitam.tools.sedalib.metadata.namedtype.ComplexListType;
import fr.gouv.vitam.tools.sedalib.metadata.namedtype.RuleType;
import fr.gouv.vitam.tools.sedalib.utils.SEDALibProgressLogger;
/**
 * @author Patrick PERCOT chef de projet département du Morbihan
 *
 */
public class Deliberations {
	/*
    static private String stripFileName(String fileName) {
        String tmp = fileName.substring(fileName.indexOf("-") + 1);
        if (tmp.lastIndexOf('.') >= 0)
            return (tmp.substring(0, tmp.lastIndexOf('.')));
        else
            return tmp;
    }
    */
    
    private static void addKeyword(Content content, String keywordType, String keywordReference, String keywordContent) 
    		throws Exception {
        Keyword keyword = new Keyword();
        keyword.addNewMetadata("KeywordType", keywordType);
        keyword.addNewMetadata("KeywordReference", keywordReference);
        keyword.addNewMetadata("KeywordContent", keywordContent);
        ((ComplexListType) content).addMetadata(keyword);
    }

    static void run() throws Exception {
        SEDALibProgressLogger pl = new SEDALibProgressLogger(LoggerFactory.getLogger("sedalibsamples"), SEDALibProgressLogger.OBJECTS_GROUP);
        try (SIPBuilder sb = new SIPBuilder("sedalib-samples/samples/deliberations.zip", pl)) {
            String sessionId = "Session";
            String ArchiveTitle = "Transfert des délibérations et des rapports des sessions du département et des commissions permanentes.";
            String sessionTitle = "Commission permanente du 04/07/2022";
            String sessiondate = "2022-07-04";
            
            sb.setComment(ArchiveTitle);
            sb.setAgencies("FRCOL_0000", "FRCOL_632", "FRCOL_632", "FRCOL_632");
            sb.setArchivalAgreement("INT_DEPT56_DELIB_ACCORD");
            sb.createRootArchiveUnit(sessionId, "RecordGrp", sessionTitle, null);
/*            sb.createRootArchiveUnit(sessionId, "RecordGrp", sessionTitle,
            		sessionTitle);*/
/*
            sb.addNewSubArchiveUnit("Racine", sessionId, "RecordGrp", sessionTitle,
            		sessionTitle);
*/
            Content content = (Content) sb.getContent(sessionId);
            ((ComplexListType) content).addNewMetadata("StartDate", sessiondate);
            ((ComplexListType) content).addNewMetadata("EndDate", sessiondate);
            ((ComplexListType) content).addNewMetadata("Language", "fra");
            
            addKeyword ( content, "subject", "matiere", "Organe délibérant");
            addKeyword ( content, "subject", "matiere", "Département");
            addKeyword ( content, "subject", "matiere_ad56", "commission permanente");
                        
            Management management = sb.getManagement(sessionId);
            //AcquisitionInformation acquisitionInformation
            DisseminationRule disseminationRule = new DisseminationRule();
            disseminationRule.addRule("Diffusion sur internet");

            AppraisalRule appraisalRule = new AppraisalRule();
            appraisalRule.addRule("P2Y", LocalDate.parse(sessiondate));
            appraisalRule.setFinalAction("Keep");
            management.addMetadata(appraisalRule);
            
            AccessRule accessRule = new AccessRule();
            accessRule.addRule("AR038", LocalDate.parse(sessiondate));
            management.addMetadata(accessRule);
            sb.setManagement(sessionId, management);
            
            String deliberationId = "CP-2022-07-04-001";
            String delibTitle = "Acquisition des titres de la société \"Etudes Applications Services (EAS)\" par la société d'économie mixte (SEM) Atout Ports";
            sb.addNewSubArchiveUnit(sessionId, deliberationId, "RecordGrp", delibTitle, null);
/*            sb.addNewSubArchiveUnit(sessionId, deliberationId, "RecordGrp", delibTitle,
                    "Ensemble des fichiers de la délibération " + deliberationId);*/

            content = (Content) sb.getContent(deliberationId);
            ((ComplexListType) content).addNewMetadata("StartDate", sessiondate);
            ((ComplexListType) content).addNewMetadata("EndDate", sessiondate);
            ((ComplexListType) content).addNewMetadata("Language", "fra");
            
            addKeyword ( content, "subject", "matiere_ad56", "Finances locales");
            addKeyword ( content, "subject", "matiere_ad56", "Prise de participation (SEM, etc...)");
            addKeyword ( content, "subject", "matiere_ad56", "Ressources et transferts");
            addKeyword ( content, "subject", "matiere_ad56", "Dette et autres mouvements financiers");
            addKeyword ( content, "corpname", "corpname", "Direction générale des services");
            addKeyword ( content, "corpname", "corpname", "Secrétariat général");
            addKeyword ( content, "corpname", "corpname", "Service de l'assemblée et des affaires juridiques");
            
            String fileName = "//cg56.fr/dfs2/BW/PROD/POSACTES/CIBLE/SAE/2022-07-04/DOCS/deliberation_CP-2022-07-04-001.PDF";
            sb.addFileSubArchiveUnit(deliberationId, fileName, delibTitle + "01", "File",
                    "Délibération " + deliberationId, null);

            fileName = "//cg56.fr/dfs2/BW/PROD/POSACTES/CIBLE/SAE/2022-07-04/DOCS/rapport_CP-2022-07-04-001.PDF";
            sb.addFileSubArchiveUnit(deliberationId, fileName, delibTitle + "02", "File",
                    "Rapport " + deliberationId, null);
            
            deliberationId = "CP-2022-07-04-002";
            delibTitle = "Convention de partenariat avec la CPAM relative à l'échange de données dans le cadre du versement de la PCH";
            sb.addNewSubArchiveUnit(sessionId, deliberationId, "RecordGrp", delibTitle, null);
/*            sb.addNewSubArchiveUnit(sessionId, deliberationId, "RecordGrp", delibTitle,
                    "Ensemble des fichiers de la délibération " + deliberationId);*/

            content = (Content) sb.getContent(deliberationId);
            ((ComplexListType) content).addNewMetadata("StartDate", sessiondate);
            ((ComplexListType) content).addNewMetadata("EndDate", sessiondate);
            ((ComplexListType) content).addNewMetadata("Language", "fra");
            
            addKeyword ( content, "subject", "matiere_ad56", "Domaines de competences par themes");
            addKeyword ( content, "subject", "matiere_ad56", "Aide sociale");
            addKeyword ( content, "subject", "matiere_ad56", "Solidarité, action sociale et santé");
            addKeyword ( content, "subject", "matiere_ad56", "Personnes handicapées");
            addKeyword ( content, "corpname", "corpname", "Direction générales des interventions sanitaires et sociales");
            addKeyword ( content, "corpname", "corpname", "Direction de l'autonomie");
            addKeyword ( content, "corpname", "corpname", "Service des prestations individuelles");
            
            fileName = "//cg56.fr/dfs2/BW/PROD/POSACTES/CIBLE/SAE/2022-07-04/DOCS/deliberation_CP-2022-07-04-002.PDF";
            sb.addFileSubArchiveUnit(deliberationId, fileName, delibTitle + "01", "File",
                    "Délibération " + deliberationId, null);

            fileName = "//cg56.fr/dfs2/BW/PROD/POSACTES/CIBLE/SAE/2022-07-04/DOCS/rapport_CP-2022-07-04-002.PDF";
            sb.addFileSubArchiveUnit(deliberationId, fileName, delibTitle + "02", "File",
                    "Rapport " + deliberationId, null);
            /*
            sb.addNewSubArchiveUnit("Racine", "Contexte", "RecordGrp", "Contexte",
                    "Ensemble des fichiers donnant le contexte de la procédure Cerfa-1244771");
            sb.addDiskSubTree("Contexte", "sedalib-samples/src/main/resources/Procedure/Contexte");
            sb.addNewSubArchiveUnit("Racine", "Dossiers", "RecordGrp", "Dossiers",
                    "Ensemble des dossiers archivés de la procédure Cerfa-1244771");
            sb.addNewContentMetadataInArchiveUnit("Dossiers", "FilePlanPosition", "Dossiers-Cerfa-1244771");

            // iterate through csv
            Path procDir = Paths.get("sedalib-samples/src/main/resources/Procedure/Dossiers");
            Scanner scanner = new Scanner(new File("sedalib-samples/src/main/resources/Procedure.csv"));
            String procId = null;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                StringTokenizer st = new StringTokenizer(line, ";");
                String id = st.nextToken();
                LocalDateTime registeredDate = SEDAXMLEventReader.getDateTimeFromString(st.nextToken());
                String requirerId = st.nextToken();
                String birthname = st.nextToken();
                String firstname = st.nextToken();
                LocalDateTime resultDate = SEDAXMLEventReader.getDateTimeFromString(st.nextToken());
                String result = st.nextToken();
                procId = "Cerfa-1244771-" + id;

                // set metadata from csv on the procId ArchiveUnit
                sb.addNewSubArchiveUnit("Dossiers", procId, "RecordGrp", procId,
                        "Ensemble des fichiers de dossier " + procId);
                Content content = sb.getContent(procId);
                content.addNewMetadata("OriginatingSystemId", procId);
                content.addNewMetadata("RegisteredDate", registeredDate);
                Event event = new Event();
                event.addNewMetadata("EventTypeCode", "Avis administratif");
                event.addNewMetadata("EventDateTime", resultDate);
                event.addNewMetadata("Outcome", result);
                content.addMetadata(event);
                AgentType requirer = new AgentType("Requirer");
                requirer.addNewMetadata("Identifier", requirerId);
                requirer.addNewMetadata("FirstName", firstname);
                requirer.addNewMetadata("BirthName", birthname);
                content.addMetadata(requirer);
                sb.setContent(procId, content);
                Management management = sb.getManagement(procId);
                AppraisalRule appraisalRule = new AppraisalRule();
                appraisalRule.addRule("APP-1069001", resultDate.toLocalDate());
                appraisalRule.setFinalAction("Destroy");
                management.addMetadata(appraisalRule);
                sb.setManagement(procId, management);

                // put all proc files in the procId ArchiveUnit
                try (DirectoryStream<Path> ds = Files.newDirectoryStream(procDir, new DirectoryStream.Filter<Path>() {
                    @Override
                    public boolean accept(Path entry) throws IOException {
                        return entry.getFileName().toString().startsWith(id);
                    }
                })) {
                    for (Path p : ds) {
                        if (!p.getFileName().toString().endsWith(".xml"))
                            sb.addFileSubArchiveUnit(procId, p.toString(), p.getFileName().toString(), "File",
                                    stripFileName(p.getFileName().toString()), null);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            scanner.close();

            // keep last file for history in Contexte part and without AppraisalRuleOld
            if (procId != null) {
                Content c = sb.getContent(procId);
                sb.addNewSubArchiveUnit("Contexte", "Exemple de dossier",
                        "RecordGrp", "Exemplaire de dossier",
                        "Ensemble des fichiers d'un dossier pour en conserver la forme.\nTitre original:"+
                                c.getSimpleMetadata("Title")+"\nDescription originale:"+
                                c.getSimpleMetadata("Description"));
                sb.addArchiveUnitSubTree("Exemple de dossier", procId);
            }
*/
            sb.generateSIP();
        }
    }

}
