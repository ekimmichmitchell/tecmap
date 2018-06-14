package edu.ithaca.dragon.tecmap.data;

import edu.ithaca.dragon.tecmap.Settings;
import edu.ithaca.dragon.tecmap.SuggestingTecmap;
import edu.ithaca.dragon.tecmap.SuggestingTecmapAPI;
import edu.ithaca.dragon.tecmap.io.Json;
import edu.ithaca.dragon.tecmap.io.record.LearningResourceRecord;
import edu.ithaca.dragon.tecmap.io.record.TecmapDataFilesRecord;
import edu.ithaca.dragon.tecmap.io.record.TecmapFileDatastoreRecord;
import edu.ithaca.dragon.tecmap.tecmapstate.TecmapState;
import edu.ithaca.dragon.tecmap.ui.TecmapUserAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.*;

public class TecmapFileDatastore implements TecmapDatastore {
    private static final Logger logger = LogManager.getLogger(TecmapFileDatastore.class);

    Map<String, TecmapFileData> idToMap;
    String rootPath;

    public TecmapFileDatastore(TecmapFileDatastoreRecord recordIn, String rootPath){
        this.rootPath = rootPath;
        idToMap = new TreeMap<>();
        for (TecmapDataFilesRecord dataFiles : recordIn.getAllRecords()){
            idToMap.put(dataFiles.getId(), new TecmapFileData(dataFiles));
        }
    }

    @Override
    public SuggestingTecmapAPI retrieveTecmapForId(String idToRetrieve) {
        TecmapFileData files = idToMap.get(idToRetrieve);
        if (files != null) {
            return retrieveTecmapForId(idToRetrieve, files.getAvailableState());
        }
        else{
            logger.info("Map not found in fileDatastore for id: "+ idToRetrieve);
            return null;
        }
    }

    @Override
    public SuggestingTecmapAPI retrieveTecmapForId(String idToRetrieve, TecmapState desiredState) {
        TecmapFileData files = idToMap.get(idToRetrieve);
        if (files != null){
            try {
                if (desiredState == TecmapState.assessmentConnected) {
                    return new SuggestingTecmap(files.getGraphFile(), files.getResourceFiles(), files.getAssessmentFiles());
                }
                else if (desiredState == TecmapState.assessmentAdded) {
                    return new SuggestingTecmap(files.getGraphFile(), null, files.getAssessmentFiles());
                }
                else if (desiredState == TecmapState.noAssessment) {
                    return new SuggestingTecmap(files.getGraphFile(), null, null);
                }
                else {
                    throw new RuntimeException("Unrecognized state desired, can't retrieve tecmap");
                }
            }
            catch (IOException e){
                logger.info("IOException when trying to create map for id: "+ idToRetrieve +"\tError:", e);
                return null;
            }
        }
        else {
            logger.info("Map not found in fileDatastore for id: "+ idToRetrieve);
            return null;
        }
    }

    @Override
    public Map<String, List<TecmapUserAction>> retrieveValidIdsAndActions() {
        //TODO: make functional style to allow parallelism
        Map<String, List<TecmapUserAction>> idToActions = new TreeMap<>();
        for (TecmapFileData fileData : idToMap.values()){
            idToActions.put(fileData.getId(), fileData.getAvailableState().getAvailableActions());
        }
        return idToActions;
    }

    @Override
    //ALWAYS WRITES TO THE SAME DEFAULT FILENAME, COPIES TO A DIFFERENT FILENAME
    public String updateTecmapResources(String idToUpdate, List<LearningResourceRecord> learningResourceRecords) {
        if (idToMap.containsKey(idToUpdate)) {
            if (learningResourceRecords != null && learningResourceRecords.size() > 0) {
                try {
                    //Copies old file with defaultFilename (if exists) to a new backup and overwrites the default
                    int i = 0;
                    String defaultFilename = rootPath + idToUpdate + "/" + idToUpdate + "Resources.json";
                    FileCheck.backup(defaultFilename);
                    Json.toJsonFile(defaultFilename, learningResourceRecords);
                    idToMap.get(idToUpdate).updateResourceFiles(defaultFilename);

                    //Copies old datastore with default filename to a new backup and overwrites the default
                    String defaultDatastoreFilename = rootPath + Settings.DEFAULT_DATASTORE_FILENAME;
                    FileCheck.backup(defaultDatastoreFilename);
                    Json.toJsonFile(defaultDatastoreFilename, createTecmapFileDatastoreRecord());

                    return defaultFilename;
                } catch (IOException exception) {
                    return null;
                }
            }
        }
        return null;
    }

    public static TecmapFileDatastore buildFromJsonFile(String rootPath) throws IOException {
        String filename = rootPath + Settings.DEFAULT_DATASTORE_FILENAME;
        return new TecmapFileDatastore(Json.fromJsonFile(filename, TecmapFileDatastoreRecord.class), rootPath);
    }


    public TecmapFileDatastoreRecord createTecmapFileDatastoreRecord() {
        Collection<TecmapFileData> allData = idToMap.values();
        List<TecmapDataFilesRecord> allDataRecords = new ArrayList<>();
        for (TecmapFileData data: allData) {
            allDataRecords.add(new TecmapDataFilesRecord(data.getId(), data.getGraphFile(), data.getResourceFiles(), data.getAssessmentFiles()));
        }
        return new TecmapFileDatastoreRecord(allDataRecords);
    }

}
