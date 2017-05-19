package edu.ithaca.dragonlab.ckc.conceptgraph;

/**
 * Created by Mia Kimmich Mitchell on 4/25/2017.
 */

import edu.ithaca.dragonlab.ckc.suggester.LearningObjectSuggestion;
import edu.ithaca.dragonlab.ckc.suggester.LearningObjectSuggestionComparator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class learningObjectSuggestionComparatorTest {
    static Logger logger = LogManager.getLogger(ConceptGraphTest.class);

    //order them: right, wrong, incomplete
    //then by pathNum val

    @Test
    public void equalTest(){
        List<LearningObjectSuggestion> suggestList = new ArrayList<>();
        suggestList.add(new LearningObjectSuggestion("Q1", 1, LearningObjectSuggestion.Level.RIGHT) );
        suggestList.add(new LearningObjectSuggestion("Q4", 1, LearningObjectSuggestion.Level.RIGHT) );

        new LearningObjectSuggestionComparator();
        Collections.sort(suggestList, new LearningObjectSuggestionComparator());

        List<LearningObjectSuggestion> suggestListTest = new ArrayList<>();
        suggestListTest.add(new LearningObjectSuggestion("Q1", 1, LearningObjectSuggestion.Level.RIGHT) );
        suggestListTest.add(new LearningObjectSuggestion("Q4", 1, LearningObjectSuggestion.Level.RIGHT) );

        for (int i =0; i<suggestList.size(); i++){

            Assert.assertEquals(suggestList.get(i).getId(),suggestListTest.get(i).getId());
            Assert.assertEquals(suggestList.get(i).getPathNum(),suggestListTest.get(i).getPathNum());
            Assert.assertEquals(suggestList.get(i).getLevel(),suggestListTest.get(i).getLevel());

        }

    }

    @Test
    public void SecondPathHigher(){
        List<LearningObjectSuggestion> suggestList = new ArrayList<>();
        suggestList.add(new LearningObjectSuggestion("Q1", 1, LearningObjectSuggestion.Level.WRONG) );
        suggestList.add(new LearningObjectSuggestion("Q4", 2, LearningObjectSuggestion.Level.WRONG) );

        Collections.sort(suggestList, new LearningObjectSuggestionComparator());

        List<LearningObjectSuggestion> suggestListTest = new ArrayList<>();
        suggestListTest.add(new LearningObjectSuggestion("Q4", 2, LearningObjectSuggestion.Level.WRONG) );
        suggestListTest.add(new LearningObjectSuggestion("Q1", 1, LearningObjectSuggestion.Level.WRONG) );

        for (int i =0; i<suggestList.size(); i++){

            Assert.assertEquals(suggestList.get(i).getId(),suggestListTest.get(i).getId());
            Assert.assertEquals(suggestList.get(i).getPathNum(),suggestListTest.get(i).getPathNum());
            Assert.assertEquals(suggestList.get(i).getLevel(),suggestListTest.get(i).getLevel());

        }

    }

    @Test
    public void secondPathLower(){
        List<LearningObjectSuggestion> suggestList = new ArrayList<>();
        suggestList.add(new LearningObjectSuggestion("Q1", 5, LearningObjectSuggestion.Level.RIGHT) );
        suggestList.add(new LearningObjectSuggestion("Q4", 1, LearningObjectSuggestion.Level.RIGHT) );

        Collections.sort(suggestList, new LearningObjectSuggestionComparator());

        List<LearningObjectSuggestion> suggestListTest = new ArrayList<>();
        suggestListTest.add(new LearningObjectSuggestion("Q1", 5, LearningObjectSuggestion.Level.RIGHT) );
        suggestListTest.add(new LearningObjectSuggestion("Q4", 1, LearningObjectSuggestion.Level.RIGHT) );


        for (int i =0; i<suggestList.size(); i++){

            Assert.assertEquals(suggestList.get(i).getId(),suggestListTest.get(i).getId());
            Assert.assertEquals(suggestList.get(i).getPathNum(),suggestListTest.get(i).getPathNum());
            Assert.assertEquals(suggestList.get(i).getLevel(),suggestListTest.get(i).getLevel());

        }
    }

    @Test
    public void firstLevelLower(){
        List<LearningObjectSuggestion> suggestList = new ArrayList<>();
        suggestList.add(new LearningObjectSuggestion("Q1", 1, LearningObjectSuggestion.Level.INCOMPLETE) );
        suggestList.add(new LearningObjectSuggestion("Q4", 1, LearningObjectSuggestion.Level.RIGHT) );

        Collections.sort(suggestList, new LearningObjectSuggestionComparator());

        List<LearningObjectSuggestion> suggestListTest = new ArrayList<>();
        suggestListTest.add(new LearningObjectSuggestion("Q4", 1, LearningObjectSuggestion.Level.RIGHT) );
        suggestListTest.add(new LearningObjectSuggestion("Q1", 1, LearningObjectSuggestion.Level.INCOMPLETE) );


        for (int i =0; i<suggestList.size(); i++){

            Assert.assertEquals(suggestList.get(i).getId(),suggestListTest.get(i).getId());
            Assert.assertEquals(suggestList.get(i).getPathNum(),suggestListTest.get(i).getPathNum());
            Assert.assertEquals(suggestList.get(i).getLevel(),suggestListTest.get(i).getLevel());

        }
    }

    @Test
    public void firstLevelHigher(){
        List<LearningObjectSuggestion> suggestList = new ArrayList<>();
        suggestList.add(new LearningObjectSuggestion("Q4", 1, LearningObjectSuggestion.Level.RIGHT) );
        suggestList.add(new LearningObjectSuggestion("Q1", 1, LearningObjectSuggestion.Level.INCOMPLETE) );

        Collections.sort(suggestList, new LearningObjectSuggestionComparator());

        List<LearningObjectSuggestion> suggestListTest = new ArrayList<>();
        suggestListTest.add(new LearningObjectSuggestion("Q4", 1, LearningObjectSuggestion.Level.RIGHT) );
        suggestListTest.add(new LearningObjectSuggestion("Q1", 1, LearningObjectSuggestion.Level.INCOMPLETE) );


        for (int i =0; i<suggestList.size(); i++){

            Assert.assertEquals(suggestList.get(i).getId(),suggestListTest.get(i).getId());
            Assert.assertEquals(suggestList.get(i).getPathNum(),suggestListTest.get(i).getPathNum());
            Assert.assertEquals(suggestList.get(i).getLevel(),suggestListTest.get(i).getLevel());

        }
    }

    @Test
    public void firstLevelLower2(){
        List<LearningObjectSuggestion> suggestList = new ArrayList<>();
        suggestList.add(new LearningObjectSuggestion("Q4", 1, LearningObjectSuggestion.Level.WRONG) );
        suggestList.add(new LearningObjectSuggestion("Q1", 1, LearningObjectSuggestion.Level.INCOMPLETE) );

        Collections.sort(suggestList, new LearningObjectSuggestionComparator());

        List<LearningObjectSuggestion> suggestListTest = new ArrayList<>();
        suggestListTest.add(new LearningObjectSuggestion("Q4", 1, LearningObjectSuggestion.Level.WRONG) );
        suggestListTest.add(new LearningObjectSuggestion("Q1", 1, LearningObjectSuggestion.Level.INCOMPLETE) );


        for (int i =0; i<suggestList.size(); i++){

            Assert.assertEquals(suggestList.get(i).getId(),suggestListTest.get(i).getId());
            Assert.assertEquals(suggestList.get(i).getPathNum(),suggestListTest.get(i).getPathNum());
            Assert.assertEquals(suggestList.get(i).getLevel(),suggestListTest.get(i).getLevel());

        }
    }



    @Test
    public void LevelsAreDiff(){
        List<LearningObjectSuggestion> suggestList = new ArrayList<>();
        suggestList.add(new LearningObjectSuggestion("Q1", 1, LearningObjectSuggestion.Level.WRONG) );
        suggestList.add(new LearningObjectSuggestion("Q2", 1, LearningObjectSuggestion.Level.INCOMPLETE) );
        suggestList.add(new LearningObjectSuggestion("Q3", 1, LearningObjectSuggestion.Level.RIGHT) );


        Collections.sort(suggestList, new LearningObjectSuggestionComparator());

        List<LearningObjectSuggestion> suggestListTest = new ArrayList<>();
        suggestListTest.add(new LearningObjectSuggestion("Q3", 1, LearningObjectSuggestion.Level.RIGHT) );
        suggestListTest.add(new LearningObjectSuggestion("Q1", 1, LearningObjectSuggestion.Level.WRONG) );
        suggestListTest.add(new LearningObjectSuggestion("Q2", 1, LearningObjectSuggestion.Level.INCOMPLETE) );



        for (int i =0; i<suggestList.size(); i++){

            Assert.assertEquals(suggestList.get(i).getId(),suggestListTest.get(i).getId());
            Assert.assertEquals(suggestList.get(i).getPathNum(),suggestListTest.get(i).getPathNum());
            Assert.assertEquals(suggestList.get(i).getLevel(),suggestListTest.get(i).getLevel());

        }
    }




    @Test
    public void suggestionComparatorTest(){

        List<LearningObjectSuggestion> suggestList = new ArrayList<>();
        suggestList.add(new LearningObjectSuggestion("Q1", 2, LearningObjectSuggestion.Level.INCOMPLETE) );
        suggestList.add(new LearningObjectSuggestion("Q2", 1, LearningObjectSuggestion.Level.WRONG) );
        Collections.sort(suggestList, new LearningObjectSuggestionComparator());


        List<LearningObjectSuggestion> suggestListTest = new ArrayList<>();
        suggestListTest.add(new LearningObjectSuggestion("Q2", 1, LearningObjectSuggestion.Level.WRONG) );
        suggestListTest.add(new LearningObjectSuggestion("Q1", 2, LearningObjectSuggestion.Level.INCOMPLETE) );


        for (int i =0; i<suggestListTest.size(); i++){

            Assert.assertEquals(suggestList.get(i).getId(),suggestListTest.get(i).getId());
            Assert.assertEquals(suggestList.get(i).getPathNum(),suggestListTest.get(i).getPathNum());
            Assert.assertEquals(suggestList.get(i).getLevel(),suggestListTest.get(i).getLevel());

        }
//
//        suggestListTest.add(new LearningObjectSuggestion("Q3", 2,LearningObjectSuggestion.Level.INCOMPLETE) );
//        suggestListTest.add(new LearningObjectSuggestion("Q4", 3,LearningObjectSuggestion.Level.INCOMPLETE) );

    }
}

