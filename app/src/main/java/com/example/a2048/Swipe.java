package com.example.a2048;

import android.widget.TextView;

public class Swipe
{
    private int intArrayCards[][];
    private TextView textViewScore;

    private int row , column;
    private int score = 0;
    private int NUMBER_OF_ROWS_AND_COLUMNS;

    private DataHelper dataHelper;
    private Moving moving;


    public Swipe(int[][] intArrayCards, TextView textViewScore, DataHelper dataHelper, Moving moving)
    {
        this.intArrayCards = intArrayCards;
        this.textViewScore = textViewScore;
        this.dataHelper = dataHelper;
        this.moving = moving;

        NUMBER_OF_ROWS_AND_COLUMNS = intArrayCards.length;
    }

    private boolean over = false;
    private boolean findSmaller;



    // DOWN SWEEP

    public void checkButtonForDownSweep()
    {
        setFindSmallerToFalse();

        for(int nextRow = row + 1; (nextRow < NUMBER_OF_ROWS_AND_COLUMNS) && (!findSmaller); ++nextRow)
        {

            if(isImageValueEqualZero(nextRow , column))
                moveDownForOnePlace();

            else if(isCardDownSmallerThanTemporaryValue(nextRow))
            {
                setNoAnimation("down");
                checkWeFoundPlaceWithSmallerValue();
            }

            else
                lookDownForSameCard(nextRow);

        }

        testFunction();

    }

    private void moveDownForOnePlace()
    {
        moving.move(row , column , 1 , "down" , dataHelper.getImage(row , column));
        ++row;
        intArrayCards[row][column] = intArrayCards[row - 1][column];
        intArrayCards[row - 1][column] = 0;
    }

    private boolean isCardDownSmallerThanTemporaryValue(int nextRow)
    {
        return (intArrayCards[nextRow][column]) < (intArrayCards[row][column]);
    }

    private boolean isNextCardOnePlaceDown(int row , int nextRow)
    {
        return row == (nextRow - 1);
    }

    private void lookDownForSameCard(int nextRow)
    {

        prepareValues();

        if(isNextCardOnePlaceDown(row , nextRow))
        {
            for(int j = 2; (j < 2048) && (isNotOver()); j = j * 2)
                if(areCardsHavingSameValues(nextRow , column , row , column , j))
                    moveDownOnSameCard(j);
        }
    }

    private void moveDownOnSameCard(int j)
    {
        moving.move(row , column , 1 , "down" , dataHelper.getImageUsingCurrentNumber(j));
        ++row;
        if(row < intArrayCards.length)
        {
            setNewValue(row , column , j);
            intArrayCards[row - 1][column] = 0;
        }

        calculateAndSetScore(j);
    }



    // UP SWEEP

    public void checkButtonForUpSweep()
    {
        setFindSmallerToFalse();

        for(int previousRow = row - 1; (previousRow > -1) && (!findSmaller); --previousRow)
        {
            if(isImageValueEqualZero(previousRow , column))
                moveUpForOnePlace();

            else if(isCardUpSmallerThanTemporaryValue(previousRow))
            {
                setNoAnimation("down");
                checkWeFoundPlaceWithSmallerValue();
            }

            else
                lookUpForSameCard(previousRow);



        }

        testFunction();

    }

    private void moveUpForOnePlace()
    {
        moving.move(row , column , 1 , "up" , dataHelper.getImage(row , column));
        --row;
        intArrayCards[row][column] = intArrayCards[row + 1][column];
        intArrayCards[row + 1][column] = 0;
    }

    private boolean isCardUpSmallerThanTemporaryValue(int previousRow)
    {
        return (intArrayCards[previousRow][column]) < (intArrayCards[row][column]);
    }

    private boolean isNextCardOnePlaceUp(int row , int previousRow)
    {
        return row == (previousRow + 1);
    }

    private void lookUpForSameCard(int previousRow)
    {
        prepareValues();

        if(isNextCardOnePlaceUp(row , previousRow))
        {
            for(int j = 2; (j < 2048) && (!over); j = j * 2)
                if(areCardsHavingSameValues(previousRow , column , row , column , j))
                    moveUpOnSameCard(j);
        }

    }

    private void moveUpOnSameCard(int j)
    {
        moving.move(row , column , 1 , "up" , dataHelper.getImageUsingCurrentNumber(j));
        --row;
        setNewValue(row , column , j);
        intArrayCards[row + 1][column] = 0;

        setOverToTrue();

        calculateAndSetScore(j);

    }


    // RIGHT SWEEP
    public void checkButtonForRightSweep()
    {
        setFindSmallerToFalse();

        for(int nextColumn = column + 1; (nextColumn < NUMBER_OF_ROWS_AND_COLUMNS) && (!findSmaller); ++nextColumn)
        {
            if(isImageValueEqualZero(row , nextColumn))
                moveRightForOnePlace();

            else if(isCardRightSmallerThanTemporaryValue(nextColumn))
            {
                setNoAnimation("down");
                checkWeFoundPlaceWithSmallerValue();
            }


            else
                lookRightForSameCard(nextColumn);

        }

        testFunction();

    }

    private void moveRightForOnePlace()
    {
        moving.move(row , column , 1 , "right" , dataHelper.getImage(row , column));
        ++column;
        intArrayCards[row][column] = intArrayCards[row][column - 1];
        intArrayCards[row][column - 1] = 0;
    }

    private boolean isCardRightSmallerThanTemporaryValue (int nextColumn)
    {
        return (intArrayCards[row][nextColumn]) < (intArrayCards[row][column]);
    }

    private boolean isNextCardOnePlaceRight(int column , int nextColumn)
    {
        return column == (nextColumn - 1);
    }

    private void lookRightForSameCard(int nextColumn)
    {
        prepareValues();

        if(isNextCardOnePlaceRight(column , nextColumn))
        {
            for(int j = 2; (j < 2048) && (!over); j = j * 2)
                if(areCardsHavingSameValues(row , nextColumn , row , column , j))
                    moveRightOnSameCard(j);
        }
    }

    private void moveRightOnSameCard(int j)
    {
        moving.move(row , column , 1 , "right" , dataHelper.getImageUsingCurrentNumber(j));
        ++column;
        setNewValue(row , column , j);
        intArrayCards[row][column - 1] = 0;

        setOverToTrue();

        calculateAndSetScore(j);
    }


    // LEFT SWEEP
    public void checkButtonForLeftSweep()
    {
        setFindSmallerToFalse();

        for(int previousColumn = column - 1; (previousColumn > -1) && (!findSmaller); --previousColumn)
        {
            if(isImageValueEqualZero(row , previousColumn))
                moveLeftForOnePlace();

            else if(isCardLeftSmallerThanTemporaryValue(previousColumn))
            {
                setNoAnimation("down");
                checkWeFoundPlaceWithSmallerValue();
            }

            else
                lookLeftForSameCard(previousColumn);
        }

        testFunction();
    }

    private void moveLeftForOnePlace()
    {
        moving.move(row , column , 1 , "left" , dataHelper.getImage(row , column));
        --column;
        intArrayCards[row][column] = intArrayCards[row][column + 1];
        intArrayCards[row][column + 1] = 0;
    }

    private boolean isCardLeftSmallerThanTemporaryValue (int previousColumn)
    {
        return (intArrayCards[row][previousColumn]) < (intArrayCards[row][column]);
    }

    private boolean isNextCardOnePlaceLeft(int column , int previousColumn)
    {
        return column == (previousColumn + 1);
    }

    private void lookLeftForSameCard(int previousColumn)
    {
        prepareValues();

        if(isNextCardOnePlaceLeft(column , previousColumn))
        {
            for(int j = 2; (j < 2048) && (!over); j = j * 2)
                if(areCardsHavingSameValues(row , previousColumn , row , column , j))
                    moveLeftOnSameCard(j);
        }
    }


    private void moveLeftOnSameCard(int j)
    {
        moving.move(row , column , 1 , "left" , dataHelper.getImageUsingCurrentNumber(j));
        --column;

        setNewValue(row , column , j);
        intArrayCards[row][column + 1] = 0;

        setOverToTrue();
        calculateAndSetScore(j);
    }


    // OTHER FUNCTIONS


    private void setNoAnimation(String direction)
    {
        moving.move(row , column , 0 , direction , dataHelper.getImage(row , column));
    }

    private void checkWeFoundPlaceWithSmallerValue()
    {
        findSmaller = true;
    }

    private void setFindSmallerToFalse()
    {
        findSmaller = false;
    }

    private void prepareValues()
    {
        over = false;
        findSmaller = true;
    }

    private void setOverToTrue()
    {
        over = true;
    }

    private void setNewValue(int row , int column , int j)
    {
        intArrayCards[row][column] = j * 2;
    }

    private void calculateAndSetScore(int number)
    {
        score += (number * 2);
        textViewScore.setText(Integer.toString(score));
    }


    private boolean isImageValueEqualZero(int row , int column)
    {
        return intArrayCards[row][column] == 0;
    }

    private void testFunction()
    {
        dataHelper.setImages();
    }


    private boolean isNotOver()
    {
        return !over;
    }

    private boolean areCardsHavingSameValues(int rowOne , int columnOne , int rowTwo , int columnTwo , int value)
    {
        return (intArrayCards[rowOne][columnOne] == value) && (intArrayCards[rowTwo][columnTwo] == value);
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void restartScore()
    {
        score = 0;
        textViewScore.setText(String.valueOf(0));
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        textViewScore.setText(String.valueOf(score));
    }



}
