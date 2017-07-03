package edu.ithaca.dragonlab.ckc.io;

import edu.ithaca.dragonlab.ckc.conceptgraph.ConceptNode;
import edu.ithaca.dragonlab.ckc.util.DataUtil;

/**
 * @author tdragon
 *         2/20/17
 *         This class represents all the information that should be recorded about a concept when serializing
 */
public class ConceptRecord {
    private String id;
    private String label;

    private double knowledgeEstimate;
    private double knowledgePrediction;
    private double knowledgeDistFromAvg;
    private double dataImportance;

    public ConceptRecord() {
        this.id = null;
        this.label = null;
        this.knowledgeEstimate = 0;
        this.knowledgePrediction = 0;
        this.knowledgeDistFromAvg = 0;
        this.dataImportance = 0;

    }

    public ConceptRecord(ConceptNode conceptToRecord, String newId) {
        this.id = newId;
        this.label = conceptToRecord.getLabel();
        this.knowledgeEstimate = conceptToRecord.getKnowledgeEstimate();
        this.knowledgePrediction = 0;
        this.knowledgeDistFromAvg = conceptToRecord.getKnowledgeDistanceFromAvg();
        this.dataImportance = conceptToRecord.getDataImportance();
    }

    public ConceptRecord(ConceptNode conceptToRecord) {
        this(conceptToRecord, conceptToRecord.getID());
    }

    public ConceptRecord(String id) {
        this.id = id;
        this.label = id;
        this.knowledgeEstimate = 0;
        this.knowledgePrediction = 0;
        this.knowledgeDistFromAvg = 0;
        this.dataImportance = 0;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public double getKnowledgeEstimate() {
        return knowledgeEstimate;
    }

    public double getKnowledgePrediction() {
        return knowledgePrediction;
    }

    public double getKnowledgeDistFromAvg() {
        return knowledgeDistFromAvg;
    }

    public double getDataImportance(){ return dataImportance; }

    public void setId(String id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setKnowledgeEstimate(double knowledgeEstimate) {
        this.knowledgeEstimate = knowledgeEstimate;
    }

    public void setKnowledgePrediction(double knowledgePrediction) {
        this.knowledgePrediction = knowledgePrediction;
    }

    public void setKnowledgeDistFromAvg(double knowledgeDistFromAvg) {
        this.knowledgeDistFromAvg = knowledgeDistFromAvg;
    }

    public void setDataImportance(double dataImportance){
        this.dataImportance = dataImportance;
    }

    public boolean equals(Object other){
        if(other == null){
            return false;
        }
        if(!ConceptRecord.class.isAssignableFrom(other.getClass())){
            return false;
        }
        ConceptRecord otherNode = (ConceptRecord) other;
        if(this.id.equals(otherNode.id) && DataUtil.equalsDoubles(this.knowledgeEstimate, otherNode.knowledgeEstimate)
                && DataUtil.equalsDoubles(this.knowledgePrediction, otherNode.knowledgePrediction)
                && DataUtil.equalsDoubles(this.knowledgeDistFromAvg, otherNode.knowledgeDistFromAvg)
                && DataUtil.equalsDoubles(this.dataImportance, otherNode.dataImportance)){
            return true;
        } else {
            return false;
        }
    }

    public String toString(){
        return "ID: "+ getId() + "  Label: " + getLabel() + "  Est.: " + getKnowledgeEstimate();
    }
}
